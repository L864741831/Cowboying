package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.HomeAdResultBean;
import com.ibeef.cowboying.bean.HomeAllVideoResultBean;
import com.ibeef.cowboying.bean.HomeBannerResultBean;
import com.ibeef.cowboying.bean.HomeVideoResultBean;

import java.util.Map;

import retrofit2.http.HeaderMap;
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
        void getHomeBanner(Map<String, String> headers);
        void getHomeVideo(Map<String, String> headers);
        void getAllVideo(Map<String, String> headers,int currentPage);
    }

    public interface IModel {
        Subscription getHomeBanner(Map<String, String> headers,ResponseCallback<HomeBannerResultBean> callback);
        Subscription getHomeVideo(Map<String, String> headers,ResponseCallback<HomeVideoResultBean> callback);
        Subscription getAllVideo(Map<String, String> headers, int currentPage, ResponseCallback<HomeAllVideoResultBean> callback);
    }
}
