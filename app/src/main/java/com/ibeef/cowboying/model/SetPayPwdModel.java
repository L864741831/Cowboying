package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.SetPayPwdBase;
import com.ibeef.cowboying.bean.ResetPayPwdParamBean;
import com.ibeef.cowboying.bean.ResetPayPwdResultBean;
import com.ibeef.cowboying.bean.SetPayPwdParamBean;
import com.ibeef.cowboying.bean.SetPayPwdResultBean;
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
 * @describe 设置支付密码
 * @package com.ranhan.cowboying.model
 **/
public class SetPayPwdModel implements SetPayPwdBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public SetPayPwdModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }

    @Override
    public Subscription getSetPayPwd(Map<String, String> headers, SetPayPwdParamBean setPayPwdParamBean, final ResponseCallback<SetPayPwdResultBean> callback) {
        Observable<SetPayPwdResultBean> observable = service.getSetPayPwd(headers,setPayPwdParamBean);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<SetPayPwdResultBean>() {
                    @Override
                    public void call(SetPayPwdResultBean result) {
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
    public Subscription getResetPayPwd(Map<String, String> headers, ResetPayPwdParamBean resetPayPwdParamBean,final ResponseCallback<ResetPayPwdResultBean> callback) {
        Observable<ResetPayPwdResultBean> observable = service.getResetPayPwd(headers,resetPayPwdParamBean);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<ResetPayPwdResultBean>() {
                    @Override
                    public void call(ResetPayPwdResultBean result) {
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
