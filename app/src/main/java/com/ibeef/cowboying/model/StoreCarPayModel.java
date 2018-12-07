package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.StoreCarPayBase;
import com.ibeef.cowboying.bean.AddShopCarResultBean;
import com.ibeef.cowboying.bean.CarListResultBean;
import com.ibeef.cowboying.bean.DeleteCarResultBean;
import com.ibeef.cowboying.bean.NowBuyOrderResultBean;
import com.ibeef.cowboying.bean.NowPayOrderParamBean;
import com.ibeef.cowboying.bean.NowPayOrderResultBean;
import com.ibeef.cowboying.bean.StoreAddrResultBean;
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
 * @describe 购物车 购买
 * @package com.ranhan.cowboying.model
 **/
public class StoreCarPayModel implements StoreCarPayBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public StoreCarPayModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }


    @Override
    public Subscription nowBuyOrder(Map<String, String> headers, AddShopCarResultBean addShopCarResultBean, final ResponseCallback<NowBuyOrderResultBean> callback) {
        Observable<NowBuyOrderResultBean> observable = service.nowBuyOrder(headers,addShopCarResultBean);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<NowBuyOrderResultBean>() {
                    @Override
                    public void call(NowBuyOrderResultBean result) {
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
    public Subscription deleteStoreCar(Map<String, String> headers,AddShopCarResultBean addShopCarResultBean,final ResponseCallback<DeleteCarResultBean> callback) {
        Observable<DeleteCarResultBean> observable = service.deleteStoreCar(headers,addShopCarResultBean);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<DeleteCarResultBean>() {
                    @Override
                    public void call(DeleteCarResultBean result) {
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
    public Subscription nowPayOrder(Map<String, String> headers, NowPayOrderParamBean noPayOrderParamBean, final ResponseCallback<NowPayOrderResultBean> callback) {
        Observable<NowPayOrderResultBean> observable = service.nowPayOrder(headers,noPayOrderParamBean);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<NowPayOrderResultBean>() {
                    @Override
                    public void call(NowPayOrderResultBean result) {
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
    public Subscription getCarList(Map<String, String> header, int currentPage,final ResponseCallback<CarListResultBean> callback) {
        Observable<CarListResultBean> observable = service.getCarList(header,currentPage);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<CarListResultBean>() {
                    @Override
                    public void call(CarListResultBean result) {
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
    public Subscription storeAddrList(Map<String, String> header, final ResponseCallback<StoreAddrResultBean> callback) {
        Observable<StoreAddrResultBean> observable = service.storeAddrList(header);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<StoreAddrResultBean>() {
                    @Override
                    public void call(StoreAddrResultBean result) {
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
