package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.HomeAdResultBean;
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
        void getAllVideo(HomeVideoResultBean homeAdResultBean);
    }

    public interface IPresenter {
        void getHomeBanner();
        void getHomeVideo();
        void getAllVideo();
    }

    public interface IModel {
        Subscription getHomeBanner(ResponseCallback<HomeBannerResultBean> callback);
        Subscription getHomeVideo(ResponseCallback<HomeVideoResultBean> callback);
        Subscription getAllVideo(ResponseCallback<HomeVideoResultBean> callback);
    }
}
