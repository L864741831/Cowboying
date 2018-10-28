package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.HomeAdBase;
import com.ibeef.cowboying.base.LoginBase;
import com.ibeef.cowboying.bean.HomeAdResultBean;
import com.ibeef.cowboying.bean.WeixinAuthFirstBean;
import com.ibeef.cowboying.model.HomeAdModel;
import com.ibeef.cowboying.model.LoginModel;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 启动广告
 * @package com.ranhan.cowboying.presenter
 **/
public class HomeAdPresenter extends BasePresenter implements HomeAdBase.IPresenter  {
    private HomeAdBase.IView mView;
    private HomeAdBase.IModel mModel;

    public HomeAdPresenter(HomeAdBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new HomeAdModel();
    }

    @Override
    public void getHomeAd(String version) {
        mView.showLoading();
        addSubscription(mModel.getHomeAd(version,new ResponseCallback<HomeAdResultBean>() {
            @Override
            public void onSuccess(HomeAdResultBean result) {
                mView.hideLoading();
                mView.getHomeAd(result);

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
