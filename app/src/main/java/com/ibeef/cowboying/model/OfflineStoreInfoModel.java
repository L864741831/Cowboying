package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.OfflineStoreInfoBase;
import com.ibeef.cowboying.bean.OfflineStoreInfoResultBean;
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
 * @describe 线下门店详情
 * @package com.ranhan.cowboying.model
 **/
public class OfflineStoreInfoModel implements OfflineStoreInfoBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public OfflineStoreInfoModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }

    @Override
    public Subscription getOfflinestoreInfo(Map<String, String> headers, final ResponseCallback<OfflineStoreInfoResultBean> callback) {
        Observable<OfflineStoreInfoResultBean> observable = service.getOfflinestoreInfo(headers);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<OfflineStoreInfoResultBean>() {
                    @Override
                    public void call(OfflineStoreInfoResultBean result) {
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
