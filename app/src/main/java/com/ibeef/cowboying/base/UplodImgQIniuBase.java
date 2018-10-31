package com.ibeef.cowboying.base;


import com.ibeef.cowboying.bean.QiniuUploadImg;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @Author $ls
 * @Date 2018/3/29 16:02
 * @Description
 */

public class UplodImgQIniuBase {
    public interface IView extends BaseView {
        void setMsg(QiniuUploadImg qiniuUploadImg);
        void showLoading();
        void hideLoading();
    }

    public interface IPresenter {
        void UploadImg(String bucket);

    }

    public interface IModel {
        Subscription UploadImg(String bucket, ResponseCallback<QiniuUploadImg> callback);

    }
}
