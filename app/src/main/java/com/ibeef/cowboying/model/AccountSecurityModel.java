package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.AccountSecurityBase;
import com.ibeef.cowboying.bean.BindThirdCountParamBean;
import com.ibeef.cowboying.bean.BindThirdCountResultBean;
import com.ibeef.cowboying.bean.SafeInfoResultBean;
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
 * @describe 账号安全 第三方绑定 解绑
 * @package com.ranhan.cowboying.model
 **/
public class AccountSecurityModel implements AccountSecurityBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public AccountSecurityModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }

    @Override
    public Subscription getSafeInfo(Map<String, String> headers,final ResponseCallback<SafeInfoResultBean> callback) {
        Observable<SafeInfoResultBean> observable = service.getSafeInfo(headers);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<SafeInfoResultBean>() {
                    @Override
                    public void call(SafeInfoResultBean result) {
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
    public Subscription getBindThidCount(Map<String, String> headers, BindThirdCountParamBean bindThirdCountParamBean, final ResponseCallback<BindThirdCountResultBean> callback) {
        Observable<BindThirdCountResultBean> observable = service.getBindThidCount(headers,bindThirdCountParamBean);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<BindThirdCountResultBean>() {
                    @Override
                    public void call(BindThirdCountResultBean result) {
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
    public Subscription getUnBindThidCount(Map<String, String> headers, String thirdUserId, final ResponseCallback<BindThirdCountResultBean> callback) {
        Observable<BindThirdCountResultBean> observable = service.getUnBindThidCount(headers,thirdUserId);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<BindThirdCountResultBean>() {
                    @Override
                    public void call(BindThirdCountResultBean result) {
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
