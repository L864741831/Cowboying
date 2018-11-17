package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.FeedbackBase;
import com.ibeef.cowboying.base.MdUploadImgBean;
import com.ibeef.cowboying.bean.MyFeedbackResultBean;
import com.ibeef.cowboying.bean.SubmitFeedbackParamBean;
import com.ibeef.cowboying.bean.SubmitFeedbackResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.net.ResponseHandler;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.HeaderMap;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rxfamily.net.HttpService;
import rxfamily.net.ResponseCallback;
import rxfamily.net.RetryWithDelay;

/**
 * @author ls
 * @date on 2018/10/7 14:05
 * @describe 意见反馈
 * @package com.ranhan.cowboying.model
 **/
public class FeedbackModel implements FeedbackBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public FeedbackModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }
    @Override
    public Subscription getMyFeedback(@HeaderMap Map<String, String> headers, int currentPage,final ResponseCallback<MyFeedbackResultBean> callback) {
        Observable<MyFeedbackResultBean> observable = service.getMyFeedback(headers,currentPage);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<MyFeedbackResultBean>() {
                    @Override
                    public void call(MyFeedbackResultBean result) {
                        callback.onSuccess(result);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        callback.onFaild(ResponseHandler.get(throwable));
                    }
                });
        return sub;
    }

    @Override
    public Subscription getSubmitFeedback(@HeaderMap Map<String, String> headers,SubmitFeedbackParamBean submitFeedbackParamBean,final ResponseCallback<SubmitFeedbackResultBean> callback) {
        Observable<SubmitFeedbackResultBean> observable = service.getSubmitFeedback(headers,submitFeedbackParamBean);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(2, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<SubmitFeedbackResultBean>() {
                    @Override
                    public void call(SubmitFeedbackResultBean result) {
                        callback.onSuccess(result);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        callback.onFaild(ResponseHandler.get(throwable));
                    }
                });
        return sub;
    }

    @Override
    public Subscription getUploadImg(@HeaderMap Map<String, String> headers, List<MultipartBody.Part> parts, final ResponseCallback<MdUploadImgBean> callback) {
        Observable<MdUploadImgBean> observable = service.getUploadImg(headers,parts);

        Subscription sub = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(3, 3000))
                //总共重试3次，重试间隔3秒
                .subscribe(new Action1<MdUploadImgBean>() {
                    @Override
                    public void call(MdUploadImgBean result) {
                        callback.onSuccess(result);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        callback.onFaild(ResponseHandler.get(throwable));
                    }
                });
        return sub;
    }
}
