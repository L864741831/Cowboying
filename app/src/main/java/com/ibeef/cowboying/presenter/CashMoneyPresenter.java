package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.AccountRegisterBase;
import com.ibeef.cowboying.base.CashMoneyBase;
import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.CashMoneyParamBean;
import com.ibeef.cowboying.bean.CashMoneyRecordResultBean;
import com.ibeef.cowboying.bean.CashMoneyResultBean;
import com.ibeef.cowboying.bean.CashMoneyUserInfoResultBean;
import com.ibeef.cowboying.model.AccountRegisetModel;
import com.ibeef.cowboying.model.CashMoneyModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 提现
 * @package com.ranhan.cowboying.presenter
 **/
public class CashMoneyPresenter extends BasePresenter implements CashMoneyBase.IPresenter  {
    private CashMoneyBase.IView mView;
    private CashMoneyBase.IModel mModel;

    public CashMoneyPresenter(CashMoneyBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new CashMoneyModel();
    }

    @Override
    public void getCashMoney(Map<String, String> headers, CashMoneyParamBean cashMoneyParamBean) {
        addSubscription(mModel.getCashMoney(headers,cashMoneyParamBean,new ResponseCallback<CashMoneyResultBean>() {
            @Override
            public void onSuccess(CashMoneyResultBean result) {
                mView.getCashMoney(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getCashMoneyRecord(Map<String, String> headers,int currentPage) {
        mView.showLoading();
        addSubscription(mModel.getCashMoneyRecord(headers,currentPage,new ResponseCallback<CashMoneyRecordResultBean>() {
            @Override
            public void onSuccess(CashMoneyRecordResultBean result) {
                mView.hideLoading();
                mView.getCashMoneyRecord(result);

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
    public void getCashMoneyUserInfo(Map<String, String> headers) {
        addSubscription(mModel.getCashMoneyUserInfo(headers,new ResponseCallback<CashMoneyUserInfoResultBean>() {
            @Override
            public void onSuccess(CashMoneyUserInfoResultBean result) {
                mView.getCashMoneyUserInfo(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
