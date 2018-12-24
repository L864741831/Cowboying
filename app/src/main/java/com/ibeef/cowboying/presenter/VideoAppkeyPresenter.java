package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.VideoAppkeyBase;
import com.ibeef.cowboying.bean.VideoAppkeyResultBean;
import com.ibeef.cowboying.model.VideoAppkeyModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 获取萤石云appkey
 * @package com.ranhan.cowboying.presenter
 **/
public class VideoAppkeyPresenter extends BasePresenter implements VideoAppkeyBase.IPresenter  {
    private VideoAppkeyBase.IView mView;
    private VideoAppkeyBase.IModel mModel;

    public VideoAppkeyPresenter(VideoAppkeyBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new VideoAppkeyModel();
    }

    @Override
    public void getVideoAppKey(Map<String, String> headers) {
        addSubscription(mModel.getVideoAppKey(headers,new ResponseCallback<VideoAppkeyResultBean>() {
            @Override
            public void onSuccess(VideoAppkeyResultBean result) {
                mView.getVideoAppKey(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
