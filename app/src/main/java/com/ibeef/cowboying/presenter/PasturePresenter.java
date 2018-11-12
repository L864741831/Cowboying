package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.HomeAdBase;
import com.ibeef.cowboying.base.PastureBase;
import com.ibeef.cowboying.bean.HomeAdResultBean;
import com.ibeef.cowboying.bean.PastureAllResultBean;
import com.ibeef.cowboying.bean.PastureDetelResultBean;
import com.ibeef.cowboying.model.HomeAdModel;
import com.ibeef.cowboying.model.PastureModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 牧场
 * @package com.ranhan.cowboying.presenter
 **/
public class PasturePresenter extends BasePresenter implements PastureBase.IPresenter  {
    private PastureBase.IView mView;
    private PastureBase.IModel mModel;

    public PasturePresenter(PastureBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new PastureModel();
    }


    @Override
    public void getPastureAllVideo(Map<String, String> headers) {
        mView.showLoading();
        addSubscription(mModel.getPastureAllVideo(headers,new ResponseCallback<PastureAllResultBean>() {
            @Override
            public void onSuccess(PastureAllResultBean result) {
                mView.hideLoading();
                mView.getPastureAllVideo(result);

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
    public void getPastureDetelVideo(Map<String, String> headers,int pastureId) {
        mView.showLoading();
        addSubscription(mModel.getPastureDetelVideo(headers,pastureId,new ResponseCallback<PastureDetelResultBean>() {
            @Override
            public void onSuccess(PastureDetelResultBean result) {
                mView.hideLoading();
                mView.getPastureDetelVideo(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.hideLoading();
                mView.showMsg(msg);
            }
        }));
    }
}
