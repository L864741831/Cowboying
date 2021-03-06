package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.GetApplyReturnParameterBean;
import com.ibeef.cowboying.base.GetEditApplyReturnParameterBean;
import com.ibeef.cowboying.base.MyOrderListBase;
import com.ibeef.cowboying.bean.MyAfterSaleDetailBean;
import com.ibeef.cowboying.bean.MyAfterSaleListBean;
import com.ibeef.cowboying.bean.MyOrderListBean;
import com.ibeef.cowboying.bean.MyOrderListCancelBean;
import com.ibeef.cowboying.bean.MyOrderListDetailBean;
import com.ibeef.cowboying.bean.ShowDeleveryResultBean;
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
public class MyOrderListModel implements MyOrderListBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public MyOrderListModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }

    @Override
    public Subscription getMyOrderList(Map<String, String> headers, int curPage, String status, final ResponseCallback<MyOrderListBean> callback) {
        Observable<MyOrderListBean> observable = service.getMyOrderList(headers,curPage,status);
        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<MyOrderListBean>() {
                    @Override
                    public void call(MyOrderListBean result) {
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
    public Subscription getMyOrderListDetail(Map<String, String> headers, String orderId, final ResponseCallback<MyOrderListDetailBean> callback) {
        Observable<MyOrderListDetailBean> observable = service.getMyOrderListDetail(headers,orderId);
        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<MyOrderListDetailBean>() {
                    @Override
                    public void call(MyOrderListDetailBean result) {
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
    public Subscription getMyOrderListDelete(Map<String, String> headers, String orderCode, final ResponseCallback<MyOrderListCancelBean> callback) {
        Observable<MyOrderListCancelBean> observable = service.getMyOrderListDelete(headers,orderCode);
        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<MyOrderListCancelBean>() {
                    @Override
                    public void call(MyOrderListCancelBean result) {
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
    public Subscription getMyOrderListCancel(Map<String, String> headers, String orderId, final ResponseCallback<MyOrderListCancelBean> callback) {
        Observable<MyOrderListCancelBean> observable = service.getMyOrderListCancel(headers,orderId);
        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<MyOrderListCancelBean>() {
                    @Override
                    public void call(MyOrderListCancelBean result) {
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
    public Subscription getMyOrderListOk(Map<String, String> headers, String orderId, final ResponseCallback<MyOrderListCancelBean> callback) {
        Observable<MyOrderListCancelBean> observable = service.getMyOrderListOk(headers,orderId);
        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<MyOrderListCancelBean>() {
                    @Override
                    public void call(MyOrderListCancelBean result) {
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
    public Subscription getAfterSaleList(Map<String, String> headers, int pageSize, int curPage, final ResponseCallback<MyAfterSaleListBean> callback) {
        Observable<MyAfterSaleListBean> observable = service.getAfterSaleList(headers,pageSize,curPage);
        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<MyAfterSaleListBean>() {
                    @Override
                    public void call(MyAfterSaleListBean result) {
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
    public Subscription getAfterSaleDetail(Map<String, String> headers, String refundId, final ResponseCallback<MyAfterSaleDetailBean> callback) {
        Observable<MyAfterSaleDetailBean> observable = service.getAfterSaleDetail(headers,refundId);
        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<MyAfterSaleDetailBean>() {
                    @Override
                    public void call(MyAfterSaleDetailBean result) {
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
    public Subscription getApplyReturn(Map<String, String> headers, GetApplyReturnParameterBean getApplyReturnParameterBean, final ResponseCallback<MyOrderListCancelBean> callback) {
        Observable<MyOrderListCancelBean> observable = service.getApplyReturn(headers,getApplyReturnParameterBean);
        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<MyOrderListCancelBean>() {
                    @Override
                    public void call(MyOrderListCancelBean result) {
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
    public Subscription getCancelApplyReturn(Map<String, String> headers, String refundId, final ResponseCallback<MyOrderListCancelBean> callback) {
        Observable<MyOrderListCancelBean> observable = service.getCancelApplyReturn(headers,refundId);
        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<MyOrderListCancelBean>() {
                    @Override
                    public void call(MyOrderListCancelBean result) {
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
    public Subscription getEditApplyReturn(Map<String, String> headers, GetEditApplyReturnParameterBean getEditApplyReturnParameterBean, final ResponseCallback<MyOrderListCancelBean> callback) {
        Observable<MyOrderListCancelBean> observable = service.getEditApplyReturn(headers,getEditApplyReturnParameterBean);
        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<MyOrderListCancelBean>() {
                    @Override
                    public void call(MyOrderListCancelBean result) {
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
    public Subscription showDelevery(Map<String, String> headers, String orderId,final ResponseCallback<ShowDeleveryResultBean> callback) {
        Observable<ShowDeleveryResultBean> observable = service.showDelevery(headers,orderId);
        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<ShowDeleveryResultBean>() {
                    @Override
                    public void call(ShowDeleveryResultBean result) {
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
