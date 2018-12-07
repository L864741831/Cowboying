package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.MyOrderListBean;
import com.ibeef.cowboying.bean.MyOrderListCancelBean;
import com.ibeef.cowboying.bean.MyOrderListDetailBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author aaa
 * @package com.ranhan.cowboying.base
 **/
public class MyOrderListBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void showLoading();
        void hideLoading();
        void getMyOrderList(MyOrderListBean myOrderListBean);
        void getMyOrderListDetail(MyOrderListDetailBean MyOrderListDetailBean);
        void getMyOrderListDelete(MyOrderListCancelBean myOrderListCancelBean);
       void getMyOrderListCancel(MyOrderListCancelBean myOrderListCancelBean);
    }

    public interface IPresenter {
        void getMyOrderList(Map<String, String> headers, int pageSize, int curPage,String status);
        void getMyOrderListDetail(Map<String, String> headers, String orderId);
        void getMyOrderListDelete(Map<String, String> headers, String orderCode);
        void getMyOrderListCancel(Map<String, String> headers, String orderId);
    }

    public interface IModel {
       Subscription getMyOrderList(Map<String, String> headers, int pageSize, int curPage, String status, ResponseCallback<MyOrderListBean> callback);
       Subscription getMyOrderListDetail(Map<String, String> headers, String orderId, ResponseCallback<MyOrderListDetailBean> callback);
       Subscription getMyOrderListDelete(Map<String, String> headers, String orderCode, ResponseCallback<MyOrderListCancelBean> callback);
       Subscription getMyOrderListCancel(Map<String, String> headers, String orderId, ResponseCallback<MyOrderListCancelBean> callback);
    }
}
