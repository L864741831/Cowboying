package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.MyOrderListBase;
import com.ibeef.cowboying.bean.MyOrderListBean;
import com.ibeef.cowboying.bean.MyOrderListCancelBean;
import com.ibeef.cowboying.bean.MyOrderListDetailBean;
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

//    @Override
//    public void geMyCowsOrderList(Map<String, String> headers, int currentPage, String status) {
//        mView.showLoading();
//        addSubscription(mModel.geMyCowsOrderList(headers,currentPage,status,new ResponseCallback<MyCowsOrderListBean>() {
//            @Override
//            public void onSuccess(MyCowsOrderListBean result) {
//                mView.hideLoading();
//                mView.geMyCowsOrderList(result);
//
//            }
//
//            @Override
//            public void onFaild(String msg) {
//                Log.e("onFaild", msg + "");
//                mView.hideLoading();
//                mView.showMsg(msg);
//            }
//        }));
//    }
//
//    @Override
//    public void geMyCowsOrderListDetail(Map<String, String> headers, String orderCode) {
//        mView.showLoading();
//        addSubscription(mModel.geMyCowsOrderListDetail(headers,orderCode,new ResponseCallback<MyCowsOrderListDetailBean>() {
//            @Override
//            public void onSuccess(MyCowsOrderListDetailBean result) {
//                mView.hideLoading();
//                mView.geMyCowsOrderListDetail(result);
//
//            }
//
//            @Override
//            public void onFaild(String msg) {
//                Log.e("onFaild", msg + "");
//                mView.hideLoading();
//                mView.showMsg(msg);
//            }
//        }));
//    }
//
//    @Override
//    public void getMyCowsOrderDelete(Map<String, String> headers, String orderCode) {
//        mView.showLoading();
//        addSubscription(mModel.getMyCowsOrderDelete(headers,orderCode,new ResponseCallback<MyCowsOrderDeleteBean>() {
//            @Override
//            public void onSuccess(MyCowsOrderDeleteBean result) {
//                mView.hideLoading();
//                mView.getMyCowsOrderDelete(result);
//
//            }
//
//            @Override
//            public void onFaild(String msg) {
//                Log.e("onFaild", msg + "");
//                mView.hideLoading();
//                mView.showMsg(msg);
//            }
//        }));
//    }
//
//    @Override
//    public void getMyCowsOrderCancel(Map<String, String> headers, String orderCode) {
//        mView.showLoading();
//        addSubscription(mModel.getMyCowsOrderCancel(headers,orderCode,new ResponseCallback<MyCowsOrderDeleteBean>() {
//            @Override
//            public void onSuccess(MyCowsOrderDeleteBean result) {
//                mView.hideLoading();
//                mView.getMyCowsOrderCancel(result);
//
//            }
//
//            @Override
//            public void onFaild(String msg) {
//                Log.e("onFaild", msg + "");
//                mView.hideLoading();
//                mView.showMsg(msg);
//            }
//        }));
//    }
//
//    @Override
//    public void getMyCowsToPay(Map<String, String> headers, String orderCode) {
//        mView.showLoading();
//        addSubscription(mModel.getMyCowsToPay(headers,orderCode,new ResponseCallback<CreatOderResultBean>() {
//            @Override
//            public void onSuccess(CreatOderResultBean result) {
//                mView.hideLoading();
//                mView.getMyCowsToPay(result);
//
//            }
//
//            @Override
//            public void onFaild(String msg) {
//                Log.e("onFaild", msg + "");
//                mView.hideLoading();
//                mView.showMsg(msg);
//            }
//        }));
//    }
}
