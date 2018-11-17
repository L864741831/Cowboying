package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.MyFeedbackResultBean;
import com.ibeef.cowboying.bean.SubmitFeedbackParamBean;
import com.ibeef.cowboying.bean.SubmitFeedbackResultBean;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.HeaderMap;
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
        void getUploadImg(MdUploadImgBean mdUploadImgBean);
    }

    public interface IPresenter {
        void getMyFeedback(@HeaderMap Map<String, String> headers,int currentPage);
        void getSubmitFeedback(@HeaderMap Map<String, String> headers,SubmitFeedbackParamBean submitFeedbackParamBean);
        void getUploadImg(@HeaderMap Map<String, String> headers, List<MultipartBody.Part> parts);
    }

    public interface IModel {
        Subscription getMyFeedback(@HeaderMap Map<String, String> headers,int currentPage, ResponseCallback<MyFeedbackResultBean> callback);
        Subscription getSubmitFeedback(@HeaderMap Map<String, String> headers, SubmitFeedbackParamBean submitFeedbackParamBean, ResponseCallback<SubmitFeedbackResultBean> callback);
        Subscription getUploadImg(@HeaderMap Map<String, String> headers, List<MultipartBody.Part> parts, ResponseCallback<MdUploadImgBean> callback);
    }
}
