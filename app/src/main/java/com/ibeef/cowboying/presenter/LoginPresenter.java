package com.ibeef.cowboying.presenter;

import com.ibeef.cowboying.base.LoginBase;
import com.ibeef.cowboying.model.LoginModel;

import rxfamily.mvp.BasePresenter;

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

}
