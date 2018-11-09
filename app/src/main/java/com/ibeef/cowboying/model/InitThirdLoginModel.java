package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.InitThirdLoginBase;
import com.ibeef.cowboying.bean.ThirdLoginResultBean;
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
 * @describe 初始化第三方登录
 * @package com.ranhan.cowboying.model
 **/
public class InitThirdLoginModel implements InitThirdLoginBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public InitThirdLoginModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }


    @Override
    public Subscription getInitThirdLogin(String version, String loginType,final ResponseCallback<ThirdLoginResultBean> callback) {
        Observable<ThirdLoginResultBean> observable = service.getInitThirdLogin(version,loginType);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<ThirdLoginResultBean>() {
                    @Override
                    public void call(ThirdLoginResultBean result) {
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
