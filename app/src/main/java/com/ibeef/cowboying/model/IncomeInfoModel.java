package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.CowManInfoBase;
import com.ibeef.cowboying.base.IncomeInfoBase;
import com.ibeef.cowboying.bean.CowManInfosResultBean;
import com.ibeef.cowboying.bean.IncomeInfoResultBean;
import com.ibeef.cowboying.bean.WalletRecordResultBean;
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
 * @describe 总资产数据展示 钱包流水
 * @package com.ranhan.cowboying.model
 **/
public class IncomeInfoModel implements IncomeInfoBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public IncomeInfoModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }


    @Override
    public Subscription getMoneyInfo(Map<String, String> headers,final ResponseCallback<IncomeInfoResultBean> callback) {
        Observable<IncomeInfoResultBean> observable = service.getMoneyInfo(headers);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<IncomeInfoResultBean>() {
                    @Override
                    public void call(IncomeInfoResultBean result) {
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
    public Subscription getWalletRecord(Map<String, String> headers, int currentPage, String amountType, final ResponseCallback<WalletRecordResultBean> callback) {
        Observable<WalletRecordResultBean> observable = service.getWalletRecord(headers,currentPage,amountType);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<WalletRecordResultBean>() {
                    @Override
                    public void call(WalletRecordResultBean result) {
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
