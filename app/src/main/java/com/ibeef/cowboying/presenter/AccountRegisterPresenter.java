package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.AccountRegisterBase;
import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.model.AccountRegisetModel;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 注册
 * @package com.ranhan.cowboying.presenter
 **/
public class AccountRegisterPresenter extends BasePresenter implements AccountRegisterBase.IPresenter  {
    private AccountRegisterBase.IView mView;
    private AccountRegisterBase.IModel mModel;

    public AccountRegisterPresenter(AccountRegisterBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new AccountRegisetModel();
    }


    @Override
    public void getAccoutRegister(String version, AccountRegisterParamBean accountRegisterParamBean) {
        addSubscription(mModel.getAccoutRegister(version,accountRegisterParamBean,new ResponseCallback<AccountRegisterResultBean>() {
            @Override
            public void onSuccess(AccountRegisterResultBean result) {
                mView.getAccoutRegister(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
