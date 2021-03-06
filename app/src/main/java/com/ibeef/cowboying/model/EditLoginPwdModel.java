package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.EditLogionPwdBase;
import com.ibeef.cowboying.bean.EditLoginPwdParamBean;
import com.ibeef.cowboying.bean.EditLoginPwdResultBean;
import com.ibeef.cowboying.bean.RestLoginParamBean;
import com.ibeef.cowboying.bean.RestLoginPwdResultBean;
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
 * @describe 重置登录密码
 * @package com.ranhan.cowboying.model
 **/
public class EditLoginPwdModel implements EditLogionPwdBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public EditLoginPwdModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }

    @Override
    public Subscription getEditLoginPwd(Map<String, String> headers, EditLoginPwdParamBean editLoginPwdParamBean, final ResponseCallback<EditLoginPwdResultBean> callback) {
        Observable<EditLoginPwdResultBean> observable = service.getEditLoginPwd(headers,editLoginPwdParamBean);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<EditLoginPwdResultBean>() {
                    @Override
                    public void call(EditLoginPwdResultBean result) {
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
    public Subscription getRestLoginPwd(Map<String, String> headers, RestLoginParamBean restLoginParamBean,final ResponseCallback<RestLoginPwdResultBean> callback) {
        Observable<RestLoginPwdResultBean> observable = service.getRestLoginPwd(headers,restLoginParamBean);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<RestLoginPwdResultBean>() {
                    @Override
                    public void call(RestLoginPwdResultBean result) {
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
