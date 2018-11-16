package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.AddMoneyResultBean;
import com.ibeef.cowboying.bean.CreatOderResultBean;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListDetailBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author aaa
 * @package com.ranhan.cowboying.base
 **/
public class MyCowsOrderBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void showLoading();
        void hideLoading();
        void geMyCowsOrderList(MyCowsOrderListBean myCowsOrderListBean);
        void geMyCowsOrderListDetail(MyCowsOrderListDetailBean myCowsOrderListDetailBean);
        void getMyCowsOrderDelete(MyCowsOrderDeleteBean myCowsOrderDeleteBean);
        void getMyCowsOrderCancel(MyCowsOrderDeleteBean myCowsOrderDeleteBean);
        void getMyCowsToPay(CreatOderResultBean creatOderResultBean);
    }

    public interface IPresenter {
        void geMyCowsOrderList(Map<String,String> headers,int currentPage,String status);
        void geMyCowsOrderListDetail(Map<String,String> headers,String orderCode);
        void getMyCowsOrderDelete(Map<String,String> headers,String orderCode);
        void getMyCowsOrderCancel(Map<String,String> headers,String orderCode);
        void getMyCowsToPay(Map<String,String> headers,String orderCode);
    }

    public interface IModel {
        Subscription geMyCowsOrderList(Map<String, String> headers,int currentPage,String status, ResponseCallback<MyCowsOrderListBean> callback);
        Subscription geMyCowsOrderListDetail(Map<String, String> headers,String orderCode, ResponseCallback<MyCowsOrderListDetailBean> callback);
        Subscription getMyCowsOrderDelete(Map<String, String> headers,String orderCode, ResponseCallback<MyCowsOrderDeleteBean> callback);
        Subscription getMyCowsOrderCancel(Map<String, String> headers,String orderCode, ResponseCallback<MyCowsOrderDeleteBean> callback);
        Subscription getMyCowsToPay(Map<String, String> headers,String orderCode, ResponseCallback<CreatOderResultBean> callback);
    }
}
