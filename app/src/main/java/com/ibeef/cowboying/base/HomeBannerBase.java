package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.HomeAdResultBean;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 登录
 * @package com.ranhan.cowboying.base
 **/
public class HomeBannerBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getHomeBanner(HomeAdResultBean homeAdResultBean);
    }

    public interface IPresenter {
        void getHomeBanner();
    }

    public interface IModel {
        Subscription getHomeBanner(ResponseCallback<HomeAdResultBean> callback);
    }
}
