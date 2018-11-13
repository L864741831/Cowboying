package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.RanchBottomVideoResultBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 牧场随机视频
 * @package com.ranhan.cowboying.base
 **/
public class RanchBottomVideoBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getRanchBottomVideo(RanchBottomVideoResultBean ranchBottomVideoResultBean);
    }

    public interface IPresenter {
        void getRanchBottomVideo(Map<String, String> headers);
    }

    public interface IModel {
        Subscription getRanchBottomVideo(Map<String, String> headers, ResponseCallback<RanchBottomVideoResultBean> callback);
    }
}
