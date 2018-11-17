package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.HomeAdResultBean;

import java.util.Map;

import retrofit2.http.HeaderMap;
import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 开屏广告
 * @package com.ranhan.cowboying.base
 **/
public class HomeAdBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getHomeAd(HomeAdResultBean homeAdResultBean);
        void showLoading();
        void hideLoading();
    }

    public interface IPresenter {
        void getHomeAd(@HeaderMap Map<String, String> headers);
    }

    public interface IModel {
        Subscription getHomeAd(@HeaderMap Map<String, String> headers, ResponseCallback<HomeAdResultBean> callback);
    }
}
