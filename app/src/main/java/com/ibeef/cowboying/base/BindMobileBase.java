package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.BindMobileParamBean;
import com.ibeef.cowboying.bean.BindMobileResultBean;
import com.ibeef.cowboying.bean.CheckThirdLoginParamBean;
import com.ibeef.cowboying.bean.CheckThirdLoginResultBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 绑定手机号
 * @package com.ranhan.cowboying.base
 **/
public class BindMobileBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getBindMobile(BindMobileResultBean bindMobileResultBean);
        void getCheckThirLogin(CheckThirdLoginResultBean checkThirdLoginResultBean);
    }

    public interface IPresenter {
        void getBindMobile(Map<String, String> headers,BindMobileParamBean bindMobileParamBean);
        void getCheckThirLogin(Map<String, String> headers,CheckThirdLoginParamBean checkThirdLoginParamBean);
    }

    public interface IModel {
        Subscription getBindMobile(Map<String, String> headers,BindMobileParamBean bindMobileParamBean, ResponseCallback<BindMobileResultBean> callback);
        Subscription getCheckThirLogin(Map<String, String> headers, CheckThirdLoginParamBean checkThirdLoginParamBean, ResponseCallback<CheckThirdLoginResultBean> callback);
    }
}
