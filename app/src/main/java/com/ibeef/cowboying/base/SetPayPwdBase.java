package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.ResetPayPwdParamBean;
import com.ibeef.cowboying.bean.ResetPayPwdResultBean;
import com.ibeef.cowboying.bean.SetPayPwdParamBean;
import com.ibeef.cowboying.bean.SetPayPwdResultBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 设置支付密码
 * @package com.ranhan.cowboying.base
 **/
public class SetPayPwdBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getSetPayPwd(SetPayPwdResultBean setPayPwdResultBean);
        void getResetPayPwd(ResetPayPwdResultBean resetPayPwdResultBean);
    }

    public interface IPresenter {
        void getSetPayPwd(Map<String, String> headers, SetPayPwdParamBean setPayPwdParamBean);
        void getResetPayPwd(Map<String, String> headers, ResetPayPwdParamBean resetPayPwdParamBean);
    }

    public interface IModel {
        Subscription getSetPayPwd(Map<String, String> headers, SetPayPwdParamBean setPayPwdParamBean, ResponseCallback<SetPayPwdResultBean> callback);
        Subscription getResetPayPwd(Map<String, String> headers, ResetPayPwdParamBean resetPayPwdParamBean, ResponseCallback<ResetPayPwdResultBean> callback);
    }
}
