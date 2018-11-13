package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.SmsCodeParamBean;
import com.ibeef.cowboying.bean.SmsCodeResultBean;
import com.ibeef.cowboying.bean.ValidateSmsCodeParamBean;

import retrofit2.http.Body;
import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 注册
 * @package com.ranhan.cowboying.base
 **/
public class SmscodeBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getSms(SmsCodeResultBean smsCodeResultBean);
        void getValidateSms(SmsCodeResultBean smsCodeResultBean);
        void countNumber(String msg);
        void setClickable(boolean clickable);
    }

    public interface IPresenter {
        void getSms(String version,SmsCodeParamBean smsCodeParamBean);
        void getValidateSms(String version,ValidateSmsCodeParamBean validateSmsCodeParamBean);
    }

    public interface IModel {
        Subscription getSms(String version,SmsCodeParamBean smsCodeParamBean, ResponseCallback<SmsCodeResultBean> callback);
        Subscription getValidateSms(String version, ValidateSmsCodeParamBean validateSmsCodeParamBean, ResponseCallback<SmsCodeResultBean> callback);
    }
}
