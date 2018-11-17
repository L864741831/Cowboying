package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.LoginBase;
import com.ibeef.cowboying.bean.LoginBean;
import com.ibeef.cowboying.bean.LoginParamBean;
import com.ibeef.cowboying.model.LoginModel;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 登录
 * @package com.ranhan.cowboying.presenter
 **/
public class LoginPresenter extends BasePresenter implements LoginBase.IPresenter  {
    private LoginBase.IView mView;
    private LoginBase.IModel mModel;

    public LoginPresenter(LoginBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new LoginModel();
    }

    @Override
    public void getUserLogin(String version, LoginParamBean loginParamBean) {
        addSubscription(mModel.getUserLogin(version,loginParamBean,new ResponseCallback<LoginBean>() {
            @Override
            public void onSuccess(LoginBean result) {
                mView.getUserLogin(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
