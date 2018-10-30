package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.CheckVersionBean;
import com.ibeef.cowboying.bean.CheckVersionParamBean;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 检查更新
 * @package com.ranhan.cowboying.base
 **/
public class CheckVersionBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getCheckVersion(CheckVersionBean checkVersionBean);
    }

    public interface IPresenter {
        void getCheckVersion(String version, CheckVersionParamBean checkVersionParamBean);
    }

    public interface IModel {
        Subscription getCheckVersion(String version, CheckVersionParamBean checkVersionParamBean, ResponseCallback<CheckVersionBean> callback);
    }
}
