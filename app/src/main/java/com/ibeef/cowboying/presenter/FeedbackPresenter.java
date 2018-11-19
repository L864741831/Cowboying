package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.FeedbackBase;
import com.ibeef.cowboying.base.MdUploadImgBean;
import com.ibeef.cowboying.bean.MyFeedbackResultBean;
import com.ibeef.cowboying.bean.SubmitFeedbackParamBean;
import com.ibeef.cowboying.bean.SubmitFeedbackResultBean;
import com.ibeef.cowboying.model.FeedbackModel;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 意见反馈
 * @package com.ranhan.cowboying.presenter
 **/
public class FeedbackPresenter extends BasePresenter implements FeedbackBase.IPresenter  {
    private FeedbackBase.IView mView;
    private FeedbackBase.IModel mModel;

    public FeedbackPresenter(FeedbackBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new FeedbackModel();
    }

    @Override
    public void getMyFeedback(Map<String, String> headers,int currentPage) {
        mView.showLoading();
        addSubscription(mModel.getMyFeedback(headers,currentPage,new ResponseCallback<MyFeedbackResultBean>() {
            @Override
            public void onSuccess(MyFeedbackResultBean result) {
                mView.hideLoading();
                mView.getMyFeedback(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.hideLoading();
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getSubmitFeedback(Map<String, String> headers, SubmitFeedbackParamBean submitFeedbackParamBean) {
        addSubscription(mModel.getSubmitFeedback(headers,submitFeedbackParamBean,new ResponseCallback<SubmitFeedbackResultBean>() {
            @Override
            public void onSuccess(SubmitFeedbackResultBean result) {
                mView.getSubmitFeedback(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getUploadImg(Map<String, String> headers, List<MultipartBody.Part> parts) {
        addSubscription(mModel.getUploadImg(headers,parts,new ResponseCallback<MdUploadImgBean>() {
            @Override
            public void onSuccess(MdUploadImgBean result) {
                mView.getUploadImg(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
            }
        }));
    }
}
