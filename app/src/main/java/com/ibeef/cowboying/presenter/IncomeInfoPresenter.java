package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.CowManInfoBase;
import com.ibeef.cowboying.base.IncomeInfoBase;
import com.ibeef.cowboying.bean.CowManInfosResultBean;
import com.ibeef.cowboying.bean.IncomeInfoResultBean;
import com.ibeef.cowboying.bean.WalletRecordResultBean;
import com.ibeef.cowboying.model.CowManInfoModel;
import com.ibeef.cowboying.model.IncomeInfoModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 总资产数据展示 钱包流水
 * @package com.ranhan.cowboying.presenter
 **/
public class IncomeInfoPresenter extends  BasePresenter implements IncomeInfoBase.IPresenter  {
    private IncomeInfoBase.IView mView;
    private IncomeInfoBase.IModel mModel;

    public IncomeInfoPresenter(IncomeInfoBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new IncomeInfoModel();
    }


    @Override
    public void getMoneyInfo(Map<String, String> headers) {
        addSubscription(mModel.getMoneyInfo(headers,new ResponseCallback<IncomeInfoResultBean>() {
            @Override
            public void onSuccess(IncomeInfoResultBean result) {
                mView.getMoneyInfo(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getWalletRecord(Map<String, String> headers, int currentPage, String amountType) {
        mView.showLoading();
        addSubscription(mModel.getWalletRecord(headers,currentPage, amountType,new ResponseCallback<WalletRecordResultBean>() {
            @Override
            public void onSuccess(WalletRecordResultBean result) {
                mView.hideLoading();
                mView.getWalletRecord(result);

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
