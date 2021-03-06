package rxfamily.download.retrofit;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rxfamily.download.DownLoadService;
import rxfamily.download.config.AuthenticatorManager;
import rxfamily.download.config.CommonInterceptor;
import rxfamily.download.config.CookieManager;
import rxfamily.download.config.LoggingInterceptor;
import rxfamily.download.util.NetErrStringUtils;

/**
 * Created by hly on 16/3/30.
 * email hugh_hly@sina.cn
 */
public class NetWorkRequest {

    public static final String TAG = NetWorkRequest.class.getSimpleName();

    private NetWorkRequest() {

    }

    private static NetWorkRequest sInstance = new NetWorkRequest();

    public static NetWorkRequest getInstance() {
        return sInstance;
    }

    private Map<String, Map<Integer, Call>> mRequestMap = new ConcurrentHashMap<>();

    public Context mContext;

    private Retrofit mRetrofit;

    private OkHttpClient mOkHttpClient;

    private DownLoadService mDownLoadService;

    /**
     * 初始化Retrofit
     *
     * @param context
     */
    public NetWorkRequest init(Context context, String baseURL) {
        this.mContext = context;
        synchronized (NetWorkRequest.this) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .cache(new Cache(new File(context.getExternalCacheDir(), "http_cache"), 1024 * 1024 * 100))
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(new CommonInterceptor())
                    .addInterceptor(new LoggingInterceptor())
                    .cookieJar(new CookieManager())
                    .authenticator(new AuthenticatorManager())
                    .build();
            mRetrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(baseURL)
                    //主机地址
                    .client(mOkHttpClient)
                    .build();
            mDownLoadService = mRetrofit.create(DownLoadService.class);
        }
        return this;
    }

    public DownLoadService getDownLoadService() {
        return mDownLoadService;
    }

    public <T> T create(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }

    public void clearCookie() {
        ((CookieManager) mOkHttpClient.cookieJar()).clearCookie();
    }

    /**
     * 异步请求
     *
     * @param tag
     * @param requestCall
     * @param responseListener
     * @param <T>
     * @return
     */
    public <T extends BaseResponseEntity> void asyncNetWork(final String tag, final int requestCode, final Call<T> requestCall, final NetworkResponse<T> responseListener) {
        if (responseListener == null) {
            return;
        }

        Call<T> call;

        if (requestCall.isExecuted()) {
            call = requestCall.clone();
        } else {
            call = requestCall;
        }
        addCall(tag, requestCode, call);
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                cancelCall(tag, requestCode);
                if (response.isSuccessful()) {
                    T result = response.body();
                    if (result == null) {
                        responseListener.onDataError(requestCode, response.code(), "");
                        return;
                    }
                    result.requestCode = requestCode;
                    result.serverTip = response.message();
                    result.responseCode = response.code();
                    responseListener.onDataReady(result);
                } else {
                    responseListener.onDataError(requestCode, response.code(), NetErrStringUtils.getErrString(mContext, response.code()));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                cancelCall(tag, requestCode);
                responseListener.onDataError(requestCode, 0, NetErrStringUtils.getErrString(mContext, t));
            }
        });
    }

    /**
     * 同步请求
     *
     * @param tag
     * @param requestCall
     * @param responseListener
     * @param <T>
     * @return
     */
    public <T extends BaseResponseEntity> void syncNetWork(final String tag, final int requestCode, final Call<T> requestCall, final NetworkResponse<T> responseListener) {
        if (responseListener == null) {
            return;
        }
        Call<T> call;
        try {
            if (requestCall.isExecuted()) {
                call = requestCall.clone();
            } else {
                call = requestCall;
            }

            Response<T> response = call.execute();
            addCall(tag, requestCode, call);
            if (response.isSuccessful()) {
                T result = response.body();
                if (result == null) {
                    responseListener.onDataError(requestCode, response.code(), "");
                    return;
                }
                result.requestCode = requestCode;
                result.serverTip = response.message();
                result.responseCode = response.code();
                responseListener.onDataReady(result);
            } else {
                responseListener.onDataError(requestCode, response.code(), NetErrStringUtils.getErrString(mContext, response.code()));
            }
        } catch (IOException e) {
            responseListener.onDataError(requestCode, 0, NetErrStringUtils.getErrString(mContext, e));
        } finally {
            cancelCall(tag, requestCode);
        }
    }

    /**
     * 添加call到Map
     *
     * @param tag
     * @param call
     */
    private void addCall(String tag, Integer code, Call call) {
        if (tag == null) {
            return;
        }
        if (mRequestMap.get(tag) == null) {
            Map<Integer, Call> map = new ConcurrentHashMap<>();
            map.put(code, call);
            mRequestMap.put(tag, map);
        } else {
            mRequestMap.get(tag).put(code, call);
        }
    }

    /**
     * 取消某个call
     *
     * @param tag
     * @param code
     */
    public boolean cancelCall(String tag, Integer code) {
        if (tag == null) {
            return false;
        }
        Map<Integer, Call> map = mRequestMap.get(tag);
        if (map == null) {
            return false;
        }
        if (code == null) {
            //取消整个context请求
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                Integer key = (Integer) iterator.next();

                Call call = map.get(key);
                if (call == null) {
                    continue;
                }
                call.cancel();
            }
            mRequestMap.remove(tag);
            return false;
        } else {
            //取消一个请求
            if (map.containsKey(code)) {
                Call call = map.get(code);
                if (call != null) {
                    call.cancel();
                }
                map.remove(code);
            }
            if (map.size() == 0) {
                mRequestMap.remove(tag);
                return false;
            }
        }
        return true;
    }

    /**
     * 取消整个tag请求，关闭页面时调用
     *
     * @param tag
     */
    public void cancelTagCall(String tag) {
        cancelCall(tag, null);
    }
}
