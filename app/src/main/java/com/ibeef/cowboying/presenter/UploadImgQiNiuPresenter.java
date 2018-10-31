package com.ibeef.cowboying.presenter;

import com.ibeef.cowboying.base.UplodImgQIniuBase;
import com.ibeef.cowboying.bean.QiniuUploadImg;
import com.ibeef.cowboying.model.UploadImgQiNiuModel;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @Author $ls
 * @Date 2018/3/29 16:09
 * @Description
 */

public class UploadImgQiNiuPresenter extends BasePresenter implements UplodImgQIniuBase.IPresenter {

    private UplodImgQIniuBase.IView mView;
    private UplodImgQIniuBase.IModel mModel;

    public UploadImgQiNiuPresenter(UplodImgQIniuBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new UploadImgQiNiuModel();
    }

    @Override
    public void UploadImg(String bucket) {
        addSubscription(mModel.UploadImg(bucket, new ResponseCallback<QiniuUploadImg>() {
            @Override
            public void onSuccess(QiniuUploadImg result) {

                mView.setMsg(result);
//                mView.hideLoading();


            }

            @Override
            public void onFaild(String msg) {


            }
        }));
    }
}
