package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.AccountRegisterBase;
import com.ibeef.cowboying.base.AccountSecurityBase;
import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.BindThirdCountParamBean;
import com.ibeef.cowboying.bean.BindThirdCountResultBean;
import com.ibeef.cowboying.bean.SafeInfoResultBean;
import com.ibeef.cowboying.model.AccountRegisetModel;
import com.ibeef.cowboying.model.AccountSecurityModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 账号安全 第三方绑定 解绑
 * @package com.ranhan.cowboying.presenter
 **/
public class AccountSecurityPresenter extends BasePresenter implements AccountSecurityBase.IPresenter  {
    private AccountSecurityBase.IView mView;
    private AccountSecurityBase.IModel mModel;

    public AccountSecurityPresenter(AccountSecurityBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new AccountSecurityModel();
    }


    @Override
    public void getSafeInfo(Map<String, String> headers) {
        addSubscription(mModel.getSafeInfo(headers,new ResponseCallback<SafeInfoResultBean>() {
            @Override
            public void onSuccess(SafeInfoResultBean result) {
                mView.getSafeInfo(result);

        }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getBindThidCount(Map<String, String> headers, BindThirdCountParamBean bindThirdCountParamBean) {
        addSubscription(mModel.getBindThidCount(headers,bindThirdCountParamBean,new ResponseCallback<BindThirdCountResultBean>() {
            @Override
            public void onSuccess(BindThirdCountResultBean result) {
                mView.getBindThidCount(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getUnBindThidCount(Map<String, String> headers, String thirdUserId) {
        addSubscription(mModel.getUnBindThidCount(headers,thirdUserId,new ResponseCallback<BindThirdCountResultBean>() {
            @Override
            public void onSuccess(BindThirdCountResultBean result) {
                mView.getUnBindThidCount(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
