package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.VideoAppkeyBase;
import com.ibeef.cowboying.bean.VideoAppkeyResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.net.ResponseHandler;

import java.util.Map;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rxfamily.net.HttpService;
import rxfamily.net.ResponseCallback;
import rxfamily.net.RetryWithDelay;

/**
 * @author ls
 * @date on 2018/10/7 14:05
 * @describe 获取萤石云appkey
 * @package com.ranhan.cowboying.model
 **/
public class VideoAppkeyModel implements VideoAppkeyBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public VideoAppkeyModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }

    @Override
    public Subscription getVideoAppKey(Map<String, String> headers,final ResponseCallback<VideoAppkeyResultBean> callback) {
        Observable<VideoAppkeyResultBean> observable = service.getVideoAppKey(headers);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<VideoAppkeyResultBean>() {
                    @Override
                    public void call(VideoAppkeyResultBean result) {
                        callback.onSuccess(result);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        callback.onFaild(ResponseHandler.get(throwable));
                    }
                });
        return sub;
    }
}
