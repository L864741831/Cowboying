package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.SellCowsBase;
import com.ibeef.cowboying.bean.CreatSellCowsParamBean;
import com.ibeef.cowboying.bean.CreatSellCowsResultBean;
import com.ibeef.cowboying.bean.SellCowsResultBean;
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
 * @describe 卖牛
 * @package com.ranhan.cowboying.model
 **/
public class SellCowsModel implements SellCowsBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public SellCowsModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }

    @Override
    public Subscription getSellCows(Map<String, String> headers, String orderId, final ResponseCallback<SellCowsResultBean> callback) {
        Observable<SellCowsResultBean> observable = service.getSellCows(headers,orderId);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<SellCowsResultBean>() {
                    @Override
                    public void call(SellCowsResultBean result) {
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
    public Subscription getCreatSellCows(Map<String, String> headers, CreatSellCowsParamBean creatSellCowsParamBean, final ResponseCallback<CreatSellCowsResultBean> callback) {
        Observable<CreatSellCowsResultBean> observable = service.getCreatSellCows(headers,creatSellCowsParamBean);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<CreatSellCowsResultBean>() {
                    @Override
                    public void call(CreatSellCowsResultBean result) {
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
