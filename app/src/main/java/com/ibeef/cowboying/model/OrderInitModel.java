package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.AccountRegisterBase;
import com.ibeef.cowboying.base.OrderInitBase;
import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.CreatOderResultBean;
import com.ibeef.cowboying.bean.CreatOrderParamBean;
import com.ibeef.cowboying.bean.PayInitParamBean;
import com.ibeef.cowboying.bean.PayInitResultBean;
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
 * @describe 买牛订单
 * @package com.ranhan.cowboying.model
 **/
public class OrderInitModel implements OrderInitBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public OrderInitModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }
    @Override
    public Subscription getCreatOder(Map<String, String> headers, CreatOrderParamBean creatOrderParamBean, final ResponseCallback<CreatOderResultBean> callback) {
        Observable<CreatOderResultBean> observable = service.getCreatOder(headers,creatOrderParamBean);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<CreatOderResultBean>() {
                    @Override
                    public void call(CreatOderResultBean result) {
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
    public Subscription getPayInit(Map<String, String> headers, PayInitParamBean payInitParamBean,final ResponseCallback<PayInitResultBean> callback) {
        Observable<PayInitResultBean> observable = service.getPayInit(headers,payInitParamBean);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<PayInitResultBean>() {
                    @Override
                    public void call(PayInitResultBean result) {
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
