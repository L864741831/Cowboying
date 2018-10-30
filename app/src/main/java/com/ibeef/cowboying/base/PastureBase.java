package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.HomeAdResultBean;
import com.ibeef.cowboying.bean.PastureAllResultBean;
import com.ibeef.cowboying.bean.PastureDetelResultBean;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 牧场
 * @package com.ranhan.cowboying.base
 **/
public class PastureBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getPastureAllVideo(PastureAllResultBean pastureAllResultBean);
        void getPastureDetelVideo(PastureDetelResultBean pastureDetelResultBean);
        void showLoading();
        void hideLoading();
    }

    public interface IPresenter {
        void getPastureAllVideo(String version);
        void getPastureDetelVideo(String version,int pastureId);
    }

    public interface IModel {
        Subscription getPastureAllVideo(String version,ResponseCallback<PastureAllResultBean> callback);
        Subscription getPastureDetelVideo(String version,int pastureId,ResponseCallback<PastureDetelResultBean> callback);
    }
}
