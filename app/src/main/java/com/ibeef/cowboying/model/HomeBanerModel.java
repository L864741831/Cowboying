package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.HomeAdBase;
import com.ibeef.cowboying.base.HomeBannerBase;
import com.ibeef.cowboying.bean.HomeAdResultBean;
import com.ibeef.cowboying.bean.HomeAllVideoResultBean;
import com.ibeef.cowboying.bean.HomeBannerResultBean;
import com.ibeef.cowboying.bean.HomeVideoResultBean;
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
 * @describe 首页banner
 * @package com.ranhan.cowboying.model
 **/
public class HomeBanerModel implements HomeBannerBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public HomeBanerModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }


    @Override
    public Subscription getHomeBanner(Map<String, String> headers, final ResponseCallback<HomeBannerResultBean> callback) {
        Observable<HomeBannerResultBean> observable = service.getHomeBanner(headers);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<HomeBannerResultBean>() {
                    @Override
                    public void call(HomeBannerResultBean result) {
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
    public Subscription getHomeVideo(Map<String, String> headers,final ResponseCallback<HomeVideoResultBean> callback) {
        Observable<HomeVideoResultBean> observable = service.getHomeVideo(headers);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<HomeVideoResultBean>() {
                    @Override
                    public void call(HomeVideoResultBean result) {
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
    public Subscription getAllVideo(Map<String, String> headers,int currentPage,final ResponseCallback<HomeAllVideoResultBean> callback) {
        Observable<HomeAllVideoResultBean> observable = service.getAllVideo(headers,currentPage);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<HomeAllVideoResultBean>() {
                    @Override
                    public void call(HomeAllVideoResultBean result) {
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
