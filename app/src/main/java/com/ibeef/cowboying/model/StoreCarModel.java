package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.StoreCarBase;
import com.ibeef.cowboying.bean.AddShopCarResultBean;
import com.ibeef.cowboying.bean.AddStoreCarResultBean;
import com.ibeef.cowboying.bean.StoreCarNumResultBean;
import com.ibeef.cowboying.bean.StoreInfoListResultBean;
import com.ibeef.cowboying.bean.StoreOneResultBean;
import com.ibeef.cowboying.bean.StorePriductIdParamBean;
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
 * @describe 商城 购物车
 * @package com.ranhan.cowboying.model
 **/
public class StoreCarModel implements StoreCarBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public StoreCarModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }


    @Override
    public Subscription getStoreOneInfo(Map<String, String> headers, int productId, final ResponseCallback<StoreOneResultBean> callback) {
        Observable<StoreOneResultBean> observable = service.getStoreOneInfo(headers,productId);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<StoreOneResultBean>() {
                    @Override
                    public void call(StoreOneResultBean result) {
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
    public Subscription getStoreInfoList(Map<String, String> headers,  StorePriductIdParamBean storePriductIdParamBean,final ResponseCallback<StoreInfoListResultBean> callback) {
        Observable<StoreInfoListResultBean> observable = service.getStoreInfoList(headers,storePriductIdParamBean);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<StoreInfoListResultBean>() {
                    @Override
                    public void call(StoreInfoListResultBean result) {
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
    public Subscription getStoreCarNum(Map<String, String> headers,final ResponseCallback<StoreCarNumResultBean> callback) {
        Observable<StoreCarNumResultBean> observable = service.getStoreCarNum(headers);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<StoreCarNumResultBean>() {
                    @Override
                    public void call(StoreCarNumResultBean result) {
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
    public Subscription addStoreCar(Map<String, String> headers, AddShopCarResultBean addShopCarResultBean, final ResponseCallback<AddStoreCarResultBean> callback) {
        Observable<AddStoreCarResultBean> observable = service.addStoreCar(headers,addShopCarResultBean);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<AddStoreCarResultBean>() {
                    @Override
                    public void call(AddStoreCarResultBean result) {
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
