package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.CreatOderResultBean;
import com.ibeef.cowboying.bean.CreatOrderParamBean;
import com.ibeef.cowboying.bean.PayInitParamBean;
import com.ibeef.cowboying.bean.PayInitResultBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 买牛订单
 * @package com.ranhan.cowboying.base
 **/
public class OrderInitBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getCreatOder(CreatOderResultBean creatOderResultBean);
        void getPayInit(PayInitResultBean payInitResultBean);
        void getStorePayInit(PayInitResultBean payInitResultBean);
    }

    public interface IPresenter {
        void getCreatOder(Map<String, String> headers, CreatOrderParamBean creatOrderParamBean);
        void getPayInit(Map<String, String> headers, PayInitParamBean payInitParamBean);
        void getStorePayInit(Map<String, String> headers, PayInitParamBean payInitParamBean);
    }

    public interface IModel {
        Subscription getCreatOder(Map<String, String> headers, CreatOrderParamBean creatOrderParamBean, ResponseCallback<CreatOderResultBean> callback);
        Subscription getPayInit(Map<String, String> headers, PayInitParamBean payInitParamBean, ResponseCallback<PayInitResultBean> callback);
        Subscription getStorePayInit(Map<String, String> headers, PayInitParamBean payInitParamBean, ResponseCallback<PayInitResultBean> callback);
    }
}
