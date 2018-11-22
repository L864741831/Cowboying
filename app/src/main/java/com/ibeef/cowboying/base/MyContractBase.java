package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.CreatOderResultBean;
import com.ibeef.cowboying.bean.MyContractListBean;
import com.ibeef.cowboying.bean.MyContractURLBean;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListDetailBean;

import java.util.Map;

import retrofit2.http.Query;
import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author aaa
 * @package com.ranhan.cowboying.base
 **/
public class MyContractBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void showLoading();
        void hideLoading();
        void getMyContrantList(MyContractListBean myContractListBean);
        void getMyContrantURL(MyContractURLBean myContractURLBean);
    }

    public interface IPresenter {
        void getMyContrantList(Map<String, String> headers,int currentPage);
        void getMyContrantURL(Map<String, String> headers, String type, String fileName);
    }

    public interface IModel {
        Subscription getMyContrantList(Map<String, String> headers,int currentPage, ResponseCallback<MyContractListBean> callback);
        Subscription getMyContrantURL(Map<String, String> headers, String type, String fileName, ResponseCallback<MyContractURLBean> callback);
    }
}
