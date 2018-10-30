package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.HomeAdBase;
import com.ibeef.cowboying.base.HomeBannerBase;
import com.ibeef.cowboying.bean.HomeAdResultBean;
import com.ibeef.cowboying.bean.HomeAllVideoResultBean;
import com.ibeef.cowboying.bean.HomeBannerResultBean;
import com.ibeef.cowboying.bean.HomeVideoResultBean;
import com.ibeef.cowboying.model.HomeAdModel;
import com.ibeef.cowboying.model.HomeBanerModel;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 首页banner
 * @package com.ranhan.cowboying.presenter
 **/
public class HomeBannerPresenter extends BasePresenter implements HomeBannerBase.IPresenter  {
    private HomeBannerBase.IView mView;
    private HomeBannerBase.IModel mModel;

    public HomeBannerPresenter(HomeBannerBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new HomeBanerModel();
    }

    @Override
    public void getHomeBanner(String version) {
        addSubscription(mModel.getHomeBanner(version,new ResponseCallback<HomeBannerResultBean>() {
            @Override
            public void onSuccess(HomeBannerResultBean result) {
                mView.getHomeBanner(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getHomeVideo(String version) {
        addSubscription(mModel.getHomeVideo(version,new ResponseCallback<HomeVideoResultBean>() {
            @Override
            public void onSuccess(HomeVideoResultBean result) {
                mView.getHomeVideo(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getAllVideo(String version,int currentPage) {
        mView.showLoading();
        addSubscription(mModel.getAllVideo(version,currentPage,new ResponseCallback<HomeAllVideoResultBean>() {
            @Override
            public void onSuccess(HomeAllVideoResultBean result) {
                mView.hideLoading();
                mView.getAllVideo(result);

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
