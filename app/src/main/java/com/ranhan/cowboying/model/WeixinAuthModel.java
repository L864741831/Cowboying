package com.ranhan.cowboying.model;

import com.ranhan.cowboying.api.ApiService;
import com.ranhan.cowboying.base.WeiXinAuthBase;
import com.ranhan.cowboying.bean.WeixinAuthFirstBean;
import com.ranhan.cowboying.bean.WeixinAuthSecondeBean;
import com.ranhan.cowboying.config.Constant;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rxfamily.net.HttpService;
import rxfamily.net.ResponseCallback;
import rxfamily.net.ResponseHandler;
import rxfamily.net.RetryWithDelay;

/**
 * @Author $ls
 * @Date 2018/3/29 16:07
 * @Description
 */

public class WeixinAuthModel implements WeiXinAuthBase.IModel {

    private HttpService httpService;
    private ApiService service;

    public WeixinAuthModel() {
        httpService = HttpService.getInstance(Constant.weixinUrl,false);
        service = httpService.getHttpService().create(ApiService.class);
    }


    @Override
    public Subscription getWeixinAuthFirst(String appid, String secret, String code, String grant_type,final ResponseCallback<WeixinAuthFirstBean> callback) {
        Observable<WeixinAuthFirstBean> observable = service.getWeixinAuthFirst(appid,secret,code,grant_type);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(3, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<WeixinAuthFirstBean>() {
                    @Override
                    public void call(WeixinAuthFirstBean result) {
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

    @Override
    public Subscription getWeixinAuthSeconde(String access_token, String openid,final ResponseCallback<WeixinAuthSecondeBean> callback) {
        Observable<WeixinAuthSecondeBean> observable = service.getWeixinAuthSeconde(access_token,openid);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(3, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<WeixinAuthSecondeBean>() {
                    @Override
                    public void call(WeixinAuthSecondeBean result) {
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
