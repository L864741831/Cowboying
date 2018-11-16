package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.AccountRegisterBase;
import com.ibeef.cowboying.base.AddMoneyBase;
import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.AddMoneyResultBean;
import com.ibeef.cowboying.bean.YesterdayIncomeResultBean;
import com.ibeef.cowboying.model.AccountRegisetModel;
import com.ibeef.cowboying.model.AddMoneyModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 累计收益
 * @package com.ranhan.cowboying.presenter
 **/
public class AddMoneyPresenter extends BasePresenter implements AddMoneyBase.IPresenter  {
    private AddMoneyBase.IView mView;
    private AddMoneyBase.IModel mModel;

    public AddMoneyPresenter(AddMoneyBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new AddMoneyModel();
    }
    @Override
    public void getAddMoney(Map<String, String> headers,int currentPage,String interestType) {
        mView.showLoading();
        addSubscription(mModel.getAddMoney(headers,currentPage,interestType,new ResponseCallback<AddMoneyResultBean>() {
            @Override
            public void onSuccess(AddMoneyResultBean result) {
                mView.hideLoading();
                mView.getAddMoney(result);

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
    public void getYesterdayIncome(Map<String, String> headers, String incomeType) {
        addSubscription(mModel.getYesterdayIncome(headers,incomeType,new ResponseCallback<YesterdayIncomeResultBean>() {
            @Override
            public void onSuccess(YesterdayIncomeResultBean result) {
                mView.getYesterdayIncome(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
