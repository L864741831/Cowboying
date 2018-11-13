package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.ActiveSchemeResultBean;
import com.ibeef.cowboying.bean.HistorySchemeResultBean;

import java.util.Map;

import retrofit2.http.Query;
import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 买牛方案列表
 * @package com.ranhan.cowboying.base
 **/
public class BuyCowSchemeBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getActiveSchemeInfo(ActiveSchemeResultBean activeSchemeResultBean);
        void getHistorySchemeInfo(HistorySchemeResultBean historySchemeResultBean);
        void showLoading();
        void hideLoading();
    }

    public interface IPresenter {
        void getActiveSchemeInfo(Map<String, String> headers,int currentPage);
        void getHistorySchemeInfo(Map<String, String> headers,int currentPage);
    }

    public interface IModel {
        Subscription getActiveSchemeInfo(Map<String, String> headers,int currentPage,  ResponseCallback<ActiveSchemeResultBean> callback);
        Subscription getHistorySchemeInfo(Map<String, String> headers,int currentPage,  ResponseCallback<HistorySchemeResultBean> callback);
    }
}
