package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.AccountSecurityBase;
import com.ibeef.cowboying.base.CattleDetailBase;
import com.ibeef.cowboying.bean.AdoptInfosResultBean;
import com.ibeef.cowboying.bean.BindThirdCountParamBean;
import com.ibeef.cowboying.bean.BindThirdCountResultBean;
import com.ibeef.cowboying.bean.CattleDetailResultBean;
import com.ibeef.cowboying.bean.SafeInfoResultBean;
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
 * @describe 牛只详请
 * @package com.ranhan.cowboying.model
 **/
public class CattleDetailModel implements CattleDetailBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public CattleDetailModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }

    @Override
    public Subscription getCattleDetail(Map<String, String> headers, int cattleId, final ResponseCallback<CattleDetailResultBean> callback) {
        Observable<CattleDetailResultBean> observable = service.getCattleDetail(headers,cattleId);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<CattleDetailResultBean>() {
                    @Override
                    public void call(CattleDetailResultBean result) {
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
    public Subscription getAdoptInfos(Map<String, String> headers, String code, Integer schemeId, Integer currentPage, final ResponseCallback<AdoptInfosResultBean> callback) {
        Observable<AdoptInfosResultBean> observable = service.getAdoptInfos(headers,code,schemeId,currentPage);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<AdoptInfosResultBean>() {
                    @Override
                    public void call(AdoptInfosResultBean result) {
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
