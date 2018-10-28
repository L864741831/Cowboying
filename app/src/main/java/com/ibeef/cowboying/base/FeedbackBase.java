package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.HomeAdResultBean;
import com.ibeef.cowboying.bean.MyFeedbackResultBean;
import com.ibeef.cowboying.bean.SubmitFeedbackParamBean;
import com.ibeef.cowboying.bean.SubmitFeedbackResultBean;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 意见反馈
 * @package com.ranhan.cowboying.base
 **/
public class FeedbackBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getMyFeedback(MyFeedbackResultBean myFeedbackResultBean);
        void getSubmitFeedback(SubmitFeedbackResultBean submitFeedbackResultBean);
        void showLoading();
        void hideLoading();
    }

    public interface IPresenter {
        void getMyFeedback(String jwt);
        void getSubmitFeedback(String jwt,SubmitFeedbackParamBean submitFeedbackParamBean);
    }

    public interface IModel {
        Subscription getMyFeedback(String jwt, ResponseCallback<MyFeedbackResultBean> callback);
        Subscription getSubmitFeedback(String jwt, SubmitFeedbackParamBean submitFeedbackParamBean, ResponseCallback<SubmitFeedbackResultBean> callback);
    }
}
