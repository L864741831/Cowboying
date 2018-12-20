package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.JgParamBean;
import com.ibeef.cowboying.bean.JgResultBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 获取极光注册ID
 * @package com.ranhan.cowboying.base
 **/
public class GetJgRegisterIdBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getJgRegisteId(JgResultBean jgResultBean);
    }

    public interface IPresenter {
        void getJgRegisteId(Map<String, String> headers, JgParamBean jgParamBean);
    }

    public interface IModel {
        Subscription getJgRegisteId(Map<String, String> headers, JgParamBean jgParamBean, ResponseCallback<JgResultBean> callback);
    }
}
