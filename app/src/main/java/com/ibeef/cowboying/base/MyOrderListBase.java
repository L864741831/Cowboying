package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.CreatOderResultBean;
import com.ibeef.cowboying.bean.MyAfterSaleDetailBean;
import com.ibeef.cowboying.bean.MyAfterSaleListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListDetailBean;
import com.ibeef.cowboying.bean.MyOrderListBean;
import com.ibeef.cowboying.bean.MyOrderListCancelBean;
import com.ibeef.cowboying.bean.MyOrderListDetailBean;
import com.ibeef.cowboying.bean.ShowDeleveryResultBean;

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
        void getMyOrderListOk(MyOrderListCancelBean myOrderListCancelBean);
        void getAfterSaleList(MyAfterSaleListBean myAfterSaleListBean);
        void getAfterSaleDetail(MyAfterSaleDetailBean myAfterSaleDetailBean);
        void getApplyReturn(MyOrderListCancelBean myOrderListCancelBean);
        void getCancelApplyReturn(MyOrderListCancelBean myOrderListCancelBean);
        void getEditApplyReturn(MyOrderListCancelBean myOrderListCancelBean);
        void showDelevery(ShowDeleveryResultBean showDeleveryResultBean);
    }

    public interface IPresenter {
        void getMyOrderList(Map<String, String> headers, int pageSize, int curPage,String status);
        void getMyOrderListDetail(Map<String, String> headers, String orderId);
        void getMyOrderListDelete(Map<String, String> headers, String orderCode);
        void getMyOrderListCancel(Map<String, String> headers, String orderId);
        void showDelevery(Map<String, String> headers, String orderId);
        void getMyOrderListOk(Map<String, String> headers, String orderId);
        void getAfterSaleList(Map<String, String> headers, int pageSize, int curPage);
        void getAfterSaleDetail(Map<String, String> headers,String refundId);
        void getApplyReturn(Map<String, String> headers,GetApplyReturnParameterBean getApplyReturnParameterBean);
        void getCancelApplyReturn(Map<String, String> headers,String refundId);
        void getEditApplyReturn(Map<String, String> headers,GetEditApplyReturnParameterBean getEditApplyReturnParameterBean);
    }

    public interface IModel {
       Subscription getMyOrderList(Map<String, String> headers, int pageSize, int curPage, String status, ResponseCallback<MyOrderListBean> callback);
       Subscription getMyOrderListDetail(Map<String, String> headers, String orderId, ResponseCallback<MyOrderListDetailBean> callback);
       Subscription getMyOrderListDelete(Map<String, String> headers, String orderCode, ResponseCallback<MyOrderListCancelBean> callback);
       Subscription getMyOrderListCancel(Map<String, String> headers, String orderId, ResponseCallback<MyOrderListCancelBean> callback);
        Subscription getMyOrderListOk(Map<String, String> headers, String orderId, ResponseCallback<MyOrderListCancelBean> callback);
        Subscription getAfterSaleList(Map<String, String> headers, int pageSize, int curPage, ResponseCallback<MyAfterSaleListBean> callback);
        Subscription getAfterSaleDetail(Map<String, String> headers, String refundId, ResponseCallback<MyAfterSaleDetailBean> callback);
        Subscription getApplyReturn(Map<String, String> headers, GetApplyReturnParameterBean getApplyReturnParameterBean, ResponseCallback<MyOrderListCancelBean> callback);
        Subscription getCancelApplyReturn(Map<String, String> headers, String refundId, ResponseCallback<MyOrderListCancelBean> callback);
        Subscription getEditApplyReturn(Map<String, String> headers, GetEditApplyReturnParameterBean getEditApplyReturnParameterBean, ResponseCallback<MyOrderListCancelBean> callback);
       Subscription showDelevery(Map<String, String> headers, String orderId, ResponseCallback<ShowDeleveryResultBean> callback);
    }
}
