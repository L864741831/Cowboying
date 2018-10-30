package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.BindThirdCountParamBean;
import com.ibeef.cowboying.bean.BindThirdCountResultBean;
import com.ibeef.cowboying.bean.SafeInfoResultBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 账号安全 第三方绑定 解绑
 * @package com.ranhan.cowboying.base
 **/
public class AccountSecurityBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getSafeInfo(SafeInfoResultBean safeInfoResultBean);
        void getBindThidCount(BindThirdCountResultBean bindThirdCountResultBean);
        void getUnBindThidCount(BindThirdCountResultBean bindThirdCountResultBean);
    }

    public interface IPresenter {
        void getSafeInfo(Map<String, String> headers);
        void getBindThidCount(Map<String, String> headers,BindThirdCountParamBean bindThirdCountParamBean);
        void getUnBindThidCount(Map<String, String> headers, String thirdUserId);
    }

    public interface IModel {
        Subscription getSafeInfo(Map<String, String> headers, ResponseCallback<SafeInfoResultBean> callback);
        Subscription getBindThidCount(Map<String, String> headers, BindThirdCountParamBean bindThirdCountParamBean,ResponseCallback<BindThirdCountResultBean> callback);
        Subscription getUnBindThidCount(Map<String, String> headers, String thirdUserId,ResponseCallback<BindThirdCountResultBean> callback);
    }
}
