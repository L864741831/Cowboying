package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.AddMoneyBase;
import com.ibeef.cowboying.base.MyCowsOrderBase;
import com.ibeef.cowboying.base.MyCowsOrderDeleteBean;
import com.ibeef.cowboying.bean.AddMoneyResultBean;
import com.ibeef.cowboying.bean.CreatOderResultBean;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListDetailBean;
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
 * @author aa
 **/
public class MyCowsOrderModel implements MyCowsOrderBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public MyCowsOrderModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }

    @Override
    public Subscription geMyCowsOrderList(Map<String, String> headers, int currentPage, String status, final ResponseCallback<MyCowsOrderListBean> callback) {
        Observable<MyCowsOrderListBean> observable = service.geMyCowsOrderList(headers,currentPage,status);
        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<MyCowsOrderListBean>() {
                    @Override
                    public void call(MyCowsOrderListBean result) {
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
    public Subscription geMyCowsOrderListDetail(Map<String, String> headers, String orderCode, final ResponseCallback<MyCowsOrderListDetailBean> callback) {
        Observable<MyCowsOrderListDetailBean> observable = service.geMyCowsOrderListDetail(headers,orderCode);
        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<MyCowsOrderListDetailBean>() {
                    @Override
                    public void call(MyCowsOrderListDetailBean result) {
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
    public Subscription getMyCowsOrderDelete(Map<String, String> headers, String orderCode, final ResponseCallback<MyCowsOrderDeleteBean> callback) {
        Observable<MyCowsOrderDeleteBean> observable = service.getMyCowsOrderDelete(headers,orderCode);
        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<MyCowsOrderDeleteBean>() {
                    @Override
                    public void call(MyCowsOrderDeleteBean result) {
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
    public Subscription getMyCowsOrderCancel(Map<String, String> headers, String orderCode, final ResponseCallback<MyCowsOrderDeleteBean> callback) {
        Observable<MyCowsOrderDeleteBean> observable = service.getMyCowsOrderCancel(headers,orderCode);
        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<MyCowsOrderDeleteBean>() {
                    @Override
                    public void call(MyCowsOrderDeleteBean result) {
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
    public Subscription getMyCowsToPay(Map<String, String> headers, String orderCode, final ResponseCallback<CreatOderResultBean> callback) {
        Observable<CreatOderResultBean> observable = service.getMyCowsToPay(headers,orderCode);
        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<CreatOderResultBean>() {
                    @Override
                    public void call(CreatOderResultBean result) {
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
