package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.SmscodeBase;
import com.ibeef.cowboying.bean.SmsCodeParamBean;
import com.ibeef.cowboying.bean.SmsCodeResultBean;
import com.ibeef.cowboying.bean.ValidateSmsCodeParamBean;
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
 * @author ls
 * @date on 2018/10/7 14:05
 * @describe短信验证码
 * @package com.ranhan.cowboying.model
 **/
public class SmsCodeModel implements SmscodeBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public SmsCodeModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }



    @Override
    public Subscription getSms(String version, SmsCodeParamBean smsCodeParamBean,final ResponseCallback<SmsCodeResultBean> callback) {
        Observable<SmsCodeResultBean> observable = service.getSms(version,smsCodeParamBean);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<SmsCodeResultBean>() {
                    @Override
                    public void call(SmsCodeResultBean result) {
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
    public Subscription getValidateSms(String version, ValidateSmsCodeParamBean validateSmsCodeParamBean,final ResponseCallback<SmsCodeResultBean> callback) {
        Observable<SmsCodeResultBean> observable = service.getValidateSms(version,validateSmsCodeParamBean);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<SmsCodeResultBean>() {
                    @Override
                    public void call(SmsCodeResultBean result) {
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
