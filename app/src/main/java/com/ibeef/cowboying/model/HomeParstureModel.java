package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.HomePastureBase;
import com.ibeef.cowboying.bean.HomeBuyCowPlanResultBean;
import com.ibeef.cowboying.bean.HomeParstureMessegeResultBean;
import com.ibeef.cowboying.bean.HomeParstureResultBean;
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
 * @describe 首页我的牧场
 * @package com.ranhan.cowboying.model
 **/
public class HomeParstureModel implements HomePastureBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public HomeParstureModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }

    @Override
    public Subscription getHomeParsture(Map<String, String> headers, final ResponseCallback<HomeParstureResultBean> callback) {
        Observable<HomeParstureResultBean> observable = service.getHomeParsture(headers);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<HomeParstureResultBean>() {
                    @Override
                    public void call(HomeParstureResultBean result) {
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
    public Subscription getHomeParstureMessege(Map<String, String> headers, final ResponseCallback<HomeParstureMessegeResultBean> callback) {
        Observable<HomeParstureMessegeResultBean> observable = service.getHomeParstureMessege(headers);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<HomeParstureMessegeResultBean>() {
                    @Override
                    public void call(HomeParstureMessegeResultBean result) {
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
    public Subscription getHomeBuyCowPlay(Map<String, String> headers,final ResponseCallback<HomeBuyCowPlanResultBean> callback) {
        Observable<HomeBuyCowPlanResultBean> observable = service.getHomeBuyCowPlay(headers);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<HomeBuyCowPlanResultBean>() {
                    @Override
                    public void call(HomeBuyCowPlanResultBean result) {
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
    public Subscription getHomeParstureMoreMessege(Map<String, String> headers, int currentPage,final ResponseCallback<HomeParstureMessegeResultBean> callback) {
        Observable<HomeParstureMessegeResultBean> observable = service.getHomeParstureMoreMessege(headers,currentPage);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<HomeParstureMessegeResultBean>() {
                    @Override
                    public void call(HomeParstureMessegeResultBean result) {
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
