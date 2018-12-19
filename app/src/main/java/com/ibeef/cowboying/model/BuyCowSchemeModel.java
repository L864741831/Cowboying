package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.BuyCowSchemeBase;
import com.ibeef.cowboying.bean.ActiveSchemeResultBean;
import com.ibeef.cowboying.bean.HistorySchemeResultBean;
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
 * @describe 养牛方案列表
 * @package com.ranhan.cowboying.model
 **/
public class BuyCowSchemeModel implements BuyCowSchemeBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public BuyCowSchemeModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }

    @Override
    public Subscription getActiveSchemeInfo(Map<String, String> headers, int currentPage, final ResponseCallback<ActiveSchemeResultBean> callback) {
        Observable<ActiveSchemeResultBean> observable = service.getActiveSchemeInfo(headers,currentPage);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<ActiveSchemeResultBean>() {
                    @Override
                    public void call(ActiveSchemeResultBean result) {
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
    public Subscription getHistorySchemeInfo(Map<String, String> headers, int currentPage, final ResponseCallback<HistorySchemeResultBean> callback) {
        Observable<HistorySchemeResultBean> observable = service.getHistorySchemeInfo(headers,currentPage);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<HistorySchemeResultBean>() {
                    @Override
                    public void call(HistorySchemeResultBean result) {
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
