package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.InitThirdLoginBase;
import com.ibeef.cowboying.bean.ThirdLoginResultBean;
import com.ibeef.cowboying.model.InitThirdLoginModel;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 初始化第三方登录
 * @package com.ranhan.cowboying.presenter
 **/
public class InitThirdLoginPresenter extends BasePresenter implements InitThirdLoginBase.IPresenter  {
    private InitThirdLoginBase.IView mView;
    private InitThirdLoginBase.IModel mModel;

    public InitThirdLoginPresenter(InitThirdLoginBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new InitThirdLoginModel();
    }

    @Override
    public void getInitThirdLogin(String version, String loginType) {
        addSubscription(mModel.getInitThirdLogin(version,loginType,new ResponseCallback<ThirdLoginResultBean>() {
            @Override
            public void onSuccess(ThirdLoginResultBean result) {
                mView.getInitThirdLogin(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
