package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.HomeAdResultBean;
import com.ibeef.cowboying.bean.HomeAllVideoResultBean;
import com.ibeef.cowboying.bean.HomeBannerResultBean;
import com.ibeef.cowboying.bean.HomeVideoResultBean;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 首页
 * @package com.ranhan.cowboying.base
 **/
public class HomeBannerBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getHomeBanner(HomeBannerResultBean homeBannerResultBean);
        void getHomeVideo(HomeVideoResultBean homeAdResultBean);
        void getAllVideo(HomeAllVideoResultBean homeAllVideoResultBean);
        void showLoading();
        void hideLoading();
    }

    public interface IPresenter {
        void getHomeBanner(String version);
        void getHomeVideo(String version);
        void getAllVideo(String version,int currentPage);
    }

    public interface IModel {
        Subscription getHomeBanner(String version,ResponseCallback<HomeBannerResultBean> callback);
        Subscription getHomeVideo(String version,ResponseCallback<HomeVideoResultBean> callback);
        Subscription getAllVideo(String version,int currentPage,ResponseCallback<HomeAllVideoResultBean> callback);
    }
}
