package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.AccountRegisterBase;
import com.ibeef.cowboying.base.SetPayPwdBase;
import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.ResetPayPwdParamBean;
import com.ibeef.cowboying.bean.ResetPayPwdResultBean;
import com.ibeef.cowboying.bean.SetPayPwdParamBean;
import com.ibeef.cowboying.bean.SetPayPwdResultBean;
import com.ibeef.cowboying.model.AccountRegisetModel;
import com.ibeef.cowboying.model.SetPayPwdModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 设置支付密码
 * @package com.ranhan.cowboying.presenter
 **/
public class SetPayPwdPresenter extends BasePresenter implements SetPayPwdBase.IPresenter  {
    private SetPayPwdBase.IView mView;
    private SetPayPwdBase.IModel mModel;

    public SetPayPwdPresenter(SetPayPwdBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new SetPayPwdModel();
    }

    @Override
    public void getSetPayPwd(Map<String, String> headers, SetPayPwdParamBean setPayPwdParamBean) {
        addSubscription(mModel.getSetPayPwd(headers,setPayPwdParamBean,new ResponseCallback<SetPayPwdResultBean>() {
            @Override
            public void onSuccess(SetPayPwdResultBean result) {
                mView.getSetPayPwd(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getResetPayPwd(Map<String, String> headers, ResetPayPwdParamBean resetPayPwdParamBean) {
        addSubscription(mModel.getResetPayPwd(headers,resetPayPwdParamBean,new ResponseCallback<ResetPayPwdResultBean>() {
            @Override
            public void onSuccess(ResetPayPwdResultBean result) {
                mView.getResetPayPwd(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
