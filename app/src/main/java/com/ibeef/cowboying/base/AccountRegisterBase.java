package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.MyFeedbackResultBean;
import com.ibeef.cowboying.bean.SubmitFeedbackParamBean;
import com.ibeef.cowboying.bean.SubmitFeedbackResultBean;

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
public class AccountRegisterBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getAccoutRegister(AccountRegisterResultBean accountRegisterResultBean);
    }

    public interface IPresenter {
        void getAccoutRegister(String version,AccountRegisterParamBean accountRegisterParamBean);
    }

    public interface IModel {
        Subscription getAccoutRegister(String version,AccountRegisterParamBean accountRegisterParamBean, ResponseCallback<AccountRegisterResultBean> callback);
    }
}
