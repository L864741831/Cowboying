package com.ibeef.cowboying.model;


import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.UplodImgQIniuBase;
import com.ibeef.cowboying.bean.QiniuUploadImg;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.net.ResponseHandler;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rxfamily.net.HttpService;
import rxfamily.net.ResponseCallback;
import rxfamily.net.RetryWithDelay;

/**
 * @Author $ls
 * @Date 2018/3/29 16:07
 * @Description
 */

public class UploadImgQiNiuModel implements UplodImgQIniuBase.IModel {

    private HttpService httpService;
    private ApiService service;

    public UploadImgQiNiuModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL3,false);
        service = httpService.getHttpService().create(ApiService.class);
    }

    @Override
    public Subscription UploadImg(String bucket,final ResponseCallback<QiniuUploadImg> callback) {
        Observable<QiniuUploadImg> observable = service.qiNiuUpload(bucket);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(3, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<QiniuUploadImg>() {
                    @Override
                    public void call(QiniuUploadImg result) {
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
