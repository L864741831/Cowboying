package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.AccountRegisterBase;
import com.ibeef.cowboying.base.CheckVersionBase;
import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.CheckVersionBean;
import com.ibeef.cowboying.bean.CheckVersionParamBean;
import com.ibeef.cowboying.config.Constant;

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
 * @author ls
 * @date on 2018/10/7 14:05
 * @describe 版本升级
 * @package com.ranhan.cowboying.model
 **/
public class CheckVersionModel implements CheckVersionBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public CheckVersionModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }
    @Override
    public Subscription getCheckVersion(String version, CheckVersionParamBean checkVersionParamBean, final ResponseCallback<CheckVersionBean> callback) {
        Observable<CheckVersionBean> observable = service.getCheckVersion(version,checkVersionParamBean);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<CheckVersionBean>() {
                    @Override
                    public void call(CheckVersionBean result) {
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
