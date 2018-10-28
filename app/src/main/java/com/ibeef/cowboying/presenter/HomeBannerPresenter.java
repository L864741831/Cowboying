package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.HomeAdBase;
import com.ibeef.cowboying.base.HomeBannerBase;
import com.ibeef.cowboying.bean.HomeAdResultBean;
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
    public void getHomeBanner() {
        addSubscription(mModel.getHomeBanner(new ResponseCallback<HomeBannerResultBean>() {
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
    public void getHomeVideo() {
        addSubscription(mModel.getHomeVideo(new ResponseCallback<HomeVideoResultBean>() {
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
    public void getAllVideo() {
        addSubscription(mModel.getAllVideo(new ResponseCallback<HomeVideoResultBean>() {
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
}
