package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.EditLoginPwdParamBean;
import com.ibeef.cowboying.bean.EditLoginPwdResultBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 设置登录密码
 * @package com.ranhan.cowboying.base
 **/
public class EditLogionPwdBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getEditLoginPwd(EditLoginPwdResultBean editLoginPwdResultBean);
    }

    public interface IPresenter {
        void getEditLoginPwd(Map<String, String> headers,EditLoginPwdParamBean editLoginPwdParamBean);
    }

    public interface IModel {
        Subscription getEditLoginPwd(Map<String, String> headers, EditLoginPwdParamBean editLoginPwdParamBean, ResponseCallback<EditLoginPwdResultBean> callback);
    }
}
