package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.MyCowsOrderBase;
import com.ibeef.cowboying.base.MyCowsOrderDeleteBean;
import com.ibeef.cowboying.bean.CreatOderResultBean;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListDetailBean;
import com.ibeef.cowboying.model.MyCowsOrderModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author aa
 **/
public class MyCowsOrderPresenter extends BasePresenter implements MyCowsOrderBase.IPresenter  {
    private MyCowsOrderBase.IView mView;
    private MyCowsOrderBase.IModel mModel;

    public MyCowsOrderPresenter(MyCowsOrderBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new MyCowsOrderModel();
    }

    @Override
    public void geMyCowsOrderList(Map<String, String> headers, int currentPage, String status) {
        mView.showLoading();
        addSubscription(mModel.geMyCowsOrderList(headers,currentPage,status,new ResponseCallback<MyCowsOrderListBean>() {
            @Override
            public void onSuccess(MyCowsOrderListBean result) {
                mView.hideLoading();
                mView.geMyCowsOrderList(result);

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
    public void geMyCowsOrderListDetail(Map<String, String> headers, String orderCode) {
        mView.showLoading();
        addSubscription(mModel.geMyCowsOrderListDetail(headers,orderCode,new ResponseCallback<MyCowsOrderListDetailBean>() {
            @Override
            public void onSuccess(MyCowsOrderListDetailBean result) {
                mView.hideLoading();
                mView.geMyCowsOrderListDetail(result);

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
    public void getMyCowsOrderDelete(Map<String, String> headers, String orderCode) {
        mView.showLoading();
        addSubscription(mModel.getMyCowsOrderDelete(headers,orderCode,new ResponseCallback<MyCowsOrderDeleteBean>() {
            @Override
            public void onSuccess(MyCowsOrderDeleteBean result) {
                mView.hideLoading();
                mView.getMyCowsOrderDelete(result);

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
    public void getMyCowsOrderCancel(Map<String, String> headers, String orderCode) {
        mView.showLoading();
        addSubscription(mModel.getMyCowsOrderCancel(headers,orderCode,new ResponseCallback<MyCowsOrderDeleteBean>() {
            @Override
            public void onSuccess(MyCowsOrderDeleteBean result) {
                mView.hideLoading();
                mView.getMyCowsOrderCancel(result);

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
    public void getMyCowsToPay(Map<String, String> headers, String orderCode) {
        mView.showLoading();
        addSubscription(mModel.getMyCowsToPay(headers,orderCode,new ResponseCallback<CreatOderResultBean>() {
            @Override
            public void onSuccess(CreatOderResultBean result) {
                mView.hideLoading();
                mView.getMyCowsToPay(result);

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
