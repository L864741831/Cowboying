package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.AccountRegisterBase;
import com.ibeef.cowboying.base.SmscodeBase;
import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.SmsCodeParamBean;
import com.ibeef.cowboying.bean.SmsCodeResultBean;
import com.ibeef.cowboying.bean.ValidateSmsCodeParamBean;
import com.ibeef.cowboying.model.AccountRegisetModel;
import com.ibeef.cowboying.model.SmsCodeModel;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 短信验证码
 * @package com.ranhan.cowboying.presenter
 **/
public class SmsCodePresenter extends BasePresenter implements SmscodeBase.IPresenter  {
    private SmscodeBase.IView mView;
    private SmscodeBase.IModel mModel;

    public SmsCodePresenter(SmscodeBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new SmsCodeModel();
    }

    @Override
    public void getSms(String version, SmsCodeParamBean smsCodeParamBean) {
        addSubscription(mModel.getSms(version,smsCodeParamBean,new ResponseCallback<SmsCodeResultBean>() {
            @Override
            public void onSuccess(SmsCodeResultBean result) {
                mView.getSms(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getValidateSms(String version, ValidateSmsCodeParamBean validateSmsCodeParamBean) {
        addSubscription(mModel.getValidateSms(version,validateSmsCodeParamBean,new ResponseCallback<SmsCodeResultBean>() {
            @Override
            public void onSuccess(SmsCodeResultBean result) {
                mView.getValidateSms(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
