package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.GetOssImgBase;
import com.ibeef.cowboying.bean.OssResultBean;
import com.ibeef.cowboying.model.GetOssImgModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 阿里云直传图片
 * @package com.ranhan.cowboying.presenter
 **/
public class GetOssImgPresenter extends BasePresenter implements GetOssImgBase.IPresenter  {
    private GetOssImgBase.IView mView;
    private GetOssImgBase.IModel mModel;

    public GetOssImgPresenter(GetOssImgBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new GetOssImgModel();
    }

    @Override
    public void getOssImg(Map<String, String> headers) {
        addSubscription(mModel.getOssImg(headers,new ResponseCallback<OssResultBean>() {
            @Override
            public void onSuccess(OssResultBean result) {
                mView.getOssImg(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
