package rxfamily.mvp;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rxfamily.bean.BaseBean;
import rxfamily.net.BaseHttpResult;
import rxfamily.net.HttpService;
import rxfamily.net.ResponseCallback;
import rxfamily.net.ResponseHandler;
import rxfamily.net.RetryWithDelay;


public class MvpBaseModel {

    public HttpService httpService;

    public MvpBaseModel(String baseUrl, boolean hasCache) {
        httpService = HttpService.getInstance(baseUrl,hasCache);
    }

    public <T> T getApiService(Class<T> api){
        return httpService.getApiService(api);
    }

    public Subscription getData(Observable observable,final ResponseCallback callback){
        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(3, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<BaseBean>() {
                    @Override
                    public void call(BaseBean result) {
                        callback.onSuccess(result);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        callback.onFaild(ResponseHandler.get(throwable));
                    }
                });
        return  sub;
    }

    public Subscription get(Observable observable,final ResponseCallback<BaseHttpResult> callback){
        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(3, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<BaseHttpResult>() {
                    @Override
                    public void call(BaseHttpResult result) {
                        int i=200;
                        if(result.code == i){
                            callback.onSuccess(result);
                        }else{
                            callback.onFaild(ResponseHandler.apiError(result.code));
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        callback.onFaild(ResponseHandler.get(throwable));
                    }
                });
        return  sub;
    }
}
