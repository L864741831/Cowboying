package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.ThirdCountLoginParamBean;
import com.ibeef.cowboying.bean.ThirdCountLoginResultBean;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 第三方账号登录
 * @package com.ranhan.cowboying.base
 **/
public class ThirdLoginBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getThirdCountLogin(ThirdCountLoginResultBean thirdCountLoginResultBean);
    }

    public interface IPresenter {
        void getThirdCountLogin(String version, ThirdCountLoginParamBean thirdCountLoginParamBean);
    }

    public interface IModel {
        Subscription getThirdCountLogin(String version, ThirdCountLoginParamBean thirdCountLoginParamBean, ResponseCallback<ThirdCountLoginResultBean> callback);
    }
}
