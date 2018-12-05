package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.ModifyAddressBase;
import com.ibeef.cowboying.bean.AddAddressParamBean;
import com.ibeef.cowboying.bean.DeleteCarResultBean;
import com.ibeef.cowboying.bean.ShowAddressResultBean;
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
 * @describe 收货地址管理
 * @package com.ranhan.cowboying.model
 **/
public class ModifyAddressModel implements ModifyAddressBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public ModifyAddressModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }

    @Override
    public Subscription addAddress(Map<String, String> headers, AddAddressParamBean addAddressResultBean, final ResponseCallback<DeleteCarResultBean> callback) {
        Observable<DeleteCarResultBean> observable = service.addAddress(headers,addAddressResultBean);

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
    public Subscription showAddressList(Map<String, String> headers, int currentPage, final ResponseCallback<ShowAddressResultBean> callback) {
        Observable<ShowAddressResultBean> observable = service.showAddressList(headers,currentPage);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<ShowAddressResultBean>() {
                    @Override
                    public void call(ShowAddressResultBean result) {
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
    public Subscription updateAddress(Map<String, String> header, AddAddressParamBean addAddressResultBean, final ResponseCallback<DeleteCarResultBean> callback) {
        Observable<DeleteCarResultBean> observable = service.updateAddress(header,addAddressResultBean);

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
    public Subscription deleteAddress(Map<String, String> header, int addressId,final ResponseCallback<DeleteCarResultBean> callback) {
        Observable<DeleteCarResultBean> observable = service.deleteAddress(header,addressId);

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
}
