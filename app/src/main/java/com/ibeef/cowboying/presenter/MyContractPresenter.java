package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.MyContractBase;
import com.ibeef.cowboying.base.MyCowsOrderBase;
import com.ibeef.cowboying.base.MyCowsOrderDeleteBean;
import com.ibeef.cowboying.bean.CreatOderResultBean;
import com.ibeef.cowboying.bean.MyContractListBean;
import com.ibeef.cowboying.bean.MyContractURLBean;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListDetailBean;
import com.ibeef.cowboying.model.MyContractModel;
import com.ibeef.cowboying.model.MyCowsOrderModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author aa
 **/
public class MyContractPresenter extends BasePresenter implements MyContractBase.IPresenter  {
    private MyContractBase.IView mView;
    private MyContractBase.IModel mModel;

    public MyContractPresenter(MyContractBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel =  new MyContractModel();
    }

    @Override
    public void getMyContrantList(Map<String, String> headers,int currentPage) {
        mView.showLoading();
        addSubscription(mModel.getMyContrantList(headers,currentPage,new ResponseCallback<MyContractListBean>() {
            @Override
            public void onSuccess(MyContractListBean result) {
                mView.hideLoading();
                mView.getMyContrantList(result);

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
    public void getMyContrantURL(Map<String, String> headers, String type, String fileName) {
        mView.showLoading();
        addSubscription(mModel.getMyContrantURL(headers,type,fileName,new ResponseCallback<MyContractURLBean>() {
            @Override
            public void onSuccess(MyContractURLBean result) {
                mView.hideLoading();
                mView.getMyContrantURL(result);

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
