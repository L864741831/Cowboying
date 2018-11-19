package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.UpdateMobileParamBean;
import com.ibeef.cowboying.bean.UpdateMobileResultBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 换绑手机号
 * @package com.ranhan.cowboying.base
 **/
public class UpdateMobileBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getUpdateMobile(UpdateMobileResultBean updateMobileResultBean);
    }

    public interface IPresenter {
        void getUpdateMobile(Map<String, String> headers, UpdateMobileParamBean updateMobileParamBean);
    }

    public interface IModel {
        Subscription getUpdateMobile(Map<String, String> headers, UpdateMobileParamBean updateMobileParamBean, ResponseCallback<UpdateMobileResultBean> callback);
    }
}
