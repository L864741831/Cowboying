package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.HomeAdResultBean;
import com.ibeef.cowboying.bean.WeixinAuthFirstBean;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 登录
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
        void getHomeAd(String version);
    }

    public interface IModel {
        Subscription getHomeAd(String version, ResponseCallback<HomeAdResultBean> callback);
    }
}
