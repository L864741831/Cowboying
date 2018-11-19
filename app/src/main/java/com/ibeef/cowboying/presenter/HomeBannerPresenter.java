package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.HomeBannerBase;
import com.ibeef.cowboying.bean.HomeAllVideoResultBean;
import com.ibeef.cowboying.bean.HomeBannerResultBean;
import com.ibeef.cowboying.bean.HomeSellCowNumResultBean;
import com.ibeef.cowboying.bean.HomeVideoResultBean;
import com.ibeef.cowboying.model.HomeBanerModel;

import java.util.Map;

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
    public void getHomeBanner(Map<String, String> headers) {
        addSubscription(mModel.getHomeBanner(headers,new ResponseCallback<HomeBannerResultBean>() {
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
    public void getHomeVideo(Map<String, String> headers) {
        addSubscription(mModel.getHomeVideo(headers,new ResponseCallback<HomeVideoResultBean>() {
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
    public void getHomeSellCowsNum(Map<String, String> headers) {
        addSubscription(mModel.getHomeSellCowsNum(headers,new ResponseCallback<HomeSellCowNumResultBean>() {
            @Override
            public void onSuccess(HomeSellCowNumResultBean result) {
                mView.getHomeSellCowsNum(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getAllVideo(Map<String, String> headers,int currentPage) {
        mView.showLoading();
        addSubscription(mModel.getAllVideo(headers,currentPage,new ResponseCallback<HomeAllVideoResultBean>() {
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
