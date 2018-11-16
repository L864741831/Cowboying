package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.AccountRegisterBase;
import com.ibeef.cowboying.base.AddMoneyBase;
import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.AddMoneyResultBean;
import com.ibeef.cowboying.bean.YesterdayIncomeResultBean;
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
 * @describe 累计收益
 * @package com.ranhan.cowboying.model
 **/
public class AddMoneyModel implements AddMoneyBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public AddMoneyModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }

    @Override
    public Subscription getAddMoney(Map<String, String> headers,int currentPage,String interestType, final ResponseCallback<AddMoneyResultBean> callback) {
        Observable<AddMoneyResultBean> observable = service.getAddMoney(headers,currentPage,interestType);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<AddMoneyResultBean>() {
                    @Override
                    public void call(AddMoneyResultBean result) {
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
    public Subscription getYesterdayIncome(Map<String, String> headers, String incomeType,final ResponseCallback<YesterdayIncomeResultBean> callback) {
        Observable<YesterdayIncomeResultBean> observable = service.getYesterdayIncome(headers,incomeType);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<YesterdayIncomeResultBean>() {
                    @Override
                    public void call(YesterdayIncomeResultBean result) {
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
