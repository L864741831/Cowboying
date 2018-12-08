package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.PayPwdBase;
import com.ibeef.cowboying.bean.PayPwdResultBean;
import com.ibeef.cowboying.model.PayPwdModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 是否设置支付密码
 * @package com.ranhan.cowboying.presenter
 **/
public class IsPayPwdPresenter extends BasePresenter implements PayPwdBase.IPresenter  {
    private PayPwdBase.IView mView;
    private PayPwdBase.IModel mModel;

    public IsPayPwdPresenter(PayPwdBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new PayPwdModel();
    }

    @Override
    public void isPayPwd(Map<String, String> headers) {
        addSubscription(mModel.isPayPwd(headers,new ResponseCallback<PayPwdResultBean>() {
            @Override
            public void onSuccess(PayPwdResultBean result) {
                mView.isPayPwd(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
