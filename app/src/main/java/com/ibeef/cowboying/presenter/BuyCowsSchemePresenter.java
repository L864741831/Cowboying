package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.BuyCowSchemeBase;
import com.ibeef.cowboying.bean.ActiveSchemeResultBean;
import com.ibeef.cowboying.bean.HistorySchemeResultBean;
import com.ibeef.cowboying.model.BuyCowSchemeModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 买牛方案列表
 * @package com.ranhan.cowboying.presenter
 **/
public class BuyCowsSchemePresenter extends BasePresenter implements BuyCowSchemeBase.IPresenter  {
    private BuyCowSchemeBase.IView mView;
    private BuyCowSchemeBase.IModel mModel;

    public BuyCowsSchemePresenter(BuyCowSchemeBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new BuyCowSchemeModel();
    }

    @Override
    public void getActiveSchemeInfo(Map<String, String> headers, int currentPage) {
        mView.showLoading();
        addSubscription(mModel.getActiveSchemeInfo(headers,currentPage,new ResponseCallback<ActiveSchemeResultBean>() {
            @Override
            public void onSuccess(ActiveSchemeResultBean result) {
                mView.hideLoading();
                mView.getActiveSchemeInfo(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.hideLoading();
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getHistorySchemeInfo(Map<String, String> headers, int currentPage) {
        mView.showLoading();
        addSubscription(mModel.getHistorySchemeInfo(headers,currentPage,new ResponseCallback<HistorySchemeResultBean>() {
            @Override
            public void onSuccess(HistorySchemeResultBean result) {
                mView.hideLoading();
                mView.getHistorySchemeInfo(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.hideLoading();
                mView.showMsg(msg);
            }
        }));
    }
}
