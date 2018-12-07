package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.PayPwdResultBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 是否设置支付密码
 * @package com.ranhan.cowboying.base
 **/
public class PayPwdBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void isPayPwd(PayPwdResultBean payPwdResultBean);
    }

    public interface IPresenter {
        void isPayPwd( Map<String, String> headers);
    }

    public interface IModel {
        Subscription isPayPwd(Map<String, String> headers, ResponseCallback<PayPwdResultBean> callback);
    }
}
