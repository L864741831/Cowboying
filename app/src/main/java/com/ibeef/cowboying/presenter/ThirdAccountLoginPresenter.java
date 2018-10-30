package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.AccountRegisterBase;
import com.ibeef.cowboying.base.ThirdLoginBase;
import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.ThirdCountLoginParamBean;
import com.ibeef.cowboying.bean.ThirdCountLoginResultBean;
import com.ibeef.cowboying.model.AccountRegisetModel;
import com.ibeef.cowboying.model.ThirdAccountLoginModel;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 第三方账号登录
 * @package com.ranhan.cowboying.presenter
 **/
public class ThirdAccountLoginPresenter extends BasePresenter implements ThirdLoginBase.IPresenter  {
    private ThirdLoginBase.IView mView;
    private ThirdLoginBase.IModel mModel;

    public ThirdAccountLoginPresenter(ThirdLoginBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new ThirdAccountLoginModel();
    }



    @Override
    public void getThirdCountLogin(String version, ThirdCountLoginParamBean thirdCountLoginParamBean) {
        addSubscription(mModel.getThirdCountLogin(version,thirdCountLoginParamBean,new ResponseCallback<ThirdCountLoginResultBean>() {
            @Override
            public void onSuccess(ThirdCountLoginResultBean result) {
                mView.getThirdCountLogin(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
