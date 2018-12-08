package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.GetApplyReturnParameterBean;
import com.ibeef.cowboying.base.GetEditApplyReturnParameterBean;
import com.ibeef.cowboying.base.MyCowsOrderBase;
import com.ibeef.cowboying.base.MyCowsOrderDeleteBean;
import com.ibeef.cowboying.base.MyOrderListBase;
import com.ibeef.cowboying.bean.CreatOderResultBean;
import com.ibeef.cowboying.bean.MyAfterSaleDetailBean;
import com.ibeef.cowboying.bean.MyAfterSaleListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListDetailBean;
import com.ibeef.cowboying.bean.MyOrderListBean;
import com.ibeef.cowboying.bean.MyOrderListCancelBean;
import com.ibeef.cowboying.bean.MyOrderListDetailBean;
import com.ibeef.cowboying.model.MyCowsOrderModel;
import com.ibeef.cowboying.model.MyOrderListModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author aa
 **/
public class MyOrderListPresenter extends BasePresenter implements MyOrderListBase.IPresenter  {
    private MyOrderListBase.IView mView;
    private MyOrderListBase.IModel mModel;

    public MyOrderListPresenter(MyOrderListBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new MyOrderListModel();
    }

    @Override
    public void getMyOrderList(Map<String, String> headers, int pageSize, int curPage, String status) {
        mView.showLoading();
        addSubscription(mModel.getMyOrderList(headers,pageSize,curPage,status,new ResponseCallback<MyOrderListBean>() {
            @Override
            public void onSuccess(MyOrderListBean result) {
                mView.hideLoading();
                mView.getMyOrderList(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.hideLoading();
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getMyOrderListDetail(Map<String, String> headers, String orderId) {
        mView.showLoading();
        addSubscription(mModel.getMyOrderListDetail(headers,orderId,new ResponseCallback<MyOrderListDetailBean>() {
            @Override
            public void onSuccess(MyOrderListDetailBean result) {
                mView.hideLoading();
                mView.getMyOrderListDetail(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.hideLoading();
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getMyOrderListDelete(Map<String, String> headers, String orderId) {
        mView.showLoading();
        addSubscription(mModel.getMyOrderListDelete(headers,orderId,new ResponseCallback<MyOrderListCancelBean>() {
            @Override
            public void onSuccess(MyOrderListCancelBean result) {
                mView.hideLoading();
                mView.getMyOrderListDelete(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.hideLoading();
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getMyOrderListCancel(Map<String, String> headers, String orderId) {
        mView.showLoading();
        addSubscription(mModel.getMyOrderListCancel(headers,orderId,new ResponseCallback<MyOrderListCancelBean>() {
            @Override
            public void onSuccess(MyOrderListCancelBean result) {
                mView.hideLoading();
                mView.getMyOrderListCancel(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.hideLoading();
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getMyOrderListOk(Map<String, String> headers, String orderId) {
        mView.showLoading();
        addSubscription(mModel.getMyOrderListOk(headers,orderId,new ResponseCallback<MyOrderListCancelBean>() {
            @Override
            public void onSuccess(MyOrderListCancelBean result) {
                mView.hideLoading();
                mView.getMyOrderListOk(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.hideLoading();
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getAfterSaleList(Map<String, String> headers, int pageSize, int curPage) {
        mView.showLoading();
        addSubscription(mModel.getAfterSaleList(headers,pageSize,curPage,new ResponseCallback<MyAfterSaleListBean>() {
            @Override
            public void onSuccess(MyAfterSaleListBean result) {
                mView.hideLoading();
                mView.getAfterSaleList(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.hideLoading();
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getAfterSaleDetail(Map<String, String> headers, String refundId) {
        mView.showLoading();
        addSubscription(mModel.getAfterSaleDetail(headers,refundId,new ResponseCallback<MyAfterSaleDetailBean>() {
            @Override
            public void onSuccess(MyAfterSaleDetailBean result) {
                mView.hideLoading();
                mView.getAfterSaleDetail(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.hideLoading();
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getApplyReturn(Map<String, String> headers, GetApplyReturnParameterBean getApplyReturnParameterBean) {
        mView.showLoading();
        addSubscription(mModel.getApplyReturn(headers,getApplyReturnParameterBean,new ResponseCallback<MyOrderListCancelBean>() {
            @Override
            public void onSuccess(MyOrderListCancelBean result) {
                mView.hideLoading();
                mView.getApplyReturn(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.hideLoading();
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getCancelApplyReturn(Map<String, String> headers, String refundId) {
        mView.showLoading();
        addSubscription(mModel.getCancelApplyReturn(headers,refundId,new ResponseCallback<MyOrderListCancelBean>() {
            @Override
            public void onSuccess(MyOrderListCancelBean result) {
                mView.hideLoading();
                mView.getCancelApplyReturn(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.hideLoading();
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getEditApplyReturn(Map<String, String> headers, GetEditApplyReturnParameterBean getEditApplyReturnParameterBean) {
        mView.showLoading();
        addSubscription(mModel.getEditApplyReturn(headers,getEditApplyReturnParameterBean,new ResponseCallback<MyOrderListCancelBean>() {
            @Override
            public void onSuccess(MyOrderListCancelBean result) {
                mView.hideLoading();
                mView.getEditApplyReturn(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.hideLoading();
                mView.showMsg(msg);
            }
        }));
    }

}
