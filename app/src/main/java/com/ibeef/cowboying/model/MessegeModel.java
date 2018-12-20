package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.AccountRegisterBase;
import com.ibeef.cowboying.base.MessegeBase;
import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.DeleteMessegeResultBean;
import com.ibeef.cowboying.bean.MessegeListReslutBean;
import com.ibeef.cowboying.bean.MessegeNumResultBean;
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
 * @describe 消息管理
 * @package com.ranhan.cowboying.model
 **/
public class MessegeModel implements MessegeBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public MessegeModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }
    @Override
    public Subscription getMessegeList(Map<String, String> headers, int type, int curPage, final ResponseCallback<MessegeListReslutBean> callback) {
        Observable<MessegeListReslutBean> observable = service.getMessegeList(headers,type,curPage);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<MessegeListReslutBean>() {
                    @Override
                    public void call(MessegeListReslutBean result) {
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
    public Subscription getMessegeNum(Map<String, String> headers,final ResponseCallback<MessegeNumResultBean> callback) {
        Observable<MessegeNumResultBean> observable = service.getMessegeNum(headers);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<MessegeNumResultBean>() {
                    @Override
                    public void call(MessegeNumResultBean result) {
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
    public Subscription getMessegeDelete(Map<String, String> headers, int messageId, final ResponseCallback<DeleteMessegeResultBean> callback) {
        Observable<DeleteMessegeResultBean> observable = service.getMessegeDelete(headers,messageId);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<DeleteMessegeResultBean>() {
                    @Override
                    public void call(DeleteMessegeResultBean result) {
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
