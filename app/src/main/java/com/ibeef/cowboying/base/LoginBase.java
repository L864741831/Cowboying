package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.HomeVideoResultBean;
import com.ibeef.cowboying.bean.LoginBean;
import com.ibeef.cowboying.bean.LoginParamBean;

import retrofit2.http.Body;
import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 登录
 * @package com.ranhan.cowboying.base
 **/
public class LoginBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getUserLogin(LoginBean loginBean);
    }

    public interface IPresenter {
        void getUserLogin(String version,LoginParamBean loginParamBean);
    }

    public interface IModel {
        Subscription getUserLogin(String version,LoginParamBean loginParamBean,ResponseCallback<LoginBean> callback);
    }
}
