package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.HomeBuyCowPlanResultBean;
import com.ibeef.cowboying.bean.HomeParstureMessegeResultBean;
import com.ibeef.cowboying.bean.HomeParstureResultBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 首页我的牧场
 * @package com.ranhan.cowboying.base
 **/
public class HomePastureBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getHomeParsture(HomeParstureResultBean homeParstureResultBean);
        void getHomeParstureMessege(HomeParstureMessegeResultBean homeParstureMessegeResultBean);
        void getHomeParstureMoreMessege(HomeParstureMessegeResultBean homeParstureMessegeResultBean);
        void getHomeBuyCowPlay(HomeBuyCowPlanResultBean homeBuyCowPlanResultBean);
        void showLoading();
        void hideLoading();
    }

    public interface IPresenter {
        void getHomeParsture(Map<String, String> headers);
        void getHomeParstureMessege(Map<String, String> headers);
        void getHomeBuyCowPlay(Map<String, String> headers);
        void getHomeParstureMoreMessege(Map<String, String> headers,int currentPage);
    }

    public interface IModel {
        Subscription getHomeParsture(Map<String, String> headers, ResponseCallback<HomeParstureResultBean> callback);
        Subscription getHomeParstureMessege(Map<String, String> headers, ResponseCallback<HomeParstureMessegeResultBean> callback);
        Subscription getHomeBuyCowPlay(Map<String, String> headers, ResponseCallback<HomeBuyCowPlanResultBean> callback);
        Subscription getHomeParstureMoreMessege(Map<String, String> headers,int currentPage, ResponseCallback<HomeParstureMessegeResultBean> callback);
    }
}
