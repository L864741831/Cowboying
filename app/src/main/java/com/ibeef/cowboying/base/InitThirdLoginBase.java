package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.ThirdLoginResultBean;

import retrofit2.http.Query;
import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 初始化第三方登录
 * @package com.ranhan.cowboying.base
 **/
public class InitThirdLoginBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getInitThirdLogin(ThirdLoginResultBean thirdLoginResultBean);
    }

    public interface IPresenter {
        void getInitThirdLogin(String version,String loginType);
    }

    public interface IModel {
        Subscription getInitThirdLogin(String version, String loginType, ResponseCallback<ThirdLoginResultBean> callback);
    }
}
