package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.AccountRegisterBase;
import com.ibeef.cowboying.base.StoreCarBase;
import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.AddStoreCarParamBean;
import com.ibeef.cowboying.bean.AddStoreCarResultBean;
import com.ibeef.cowboying.bean.StoreCarNumResultBean;
import com.ibeef.cowboying.bean.StoreInfoListResultBean;
import com.ibeef.cowboying.model.AccountRegisetModel;
import com.ibeef.cowboying.model.StoreCarModel;

import java.util.List;
import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 商城 购物车
 * @package com.ranhan.cowboying.presenter
 **/
public class StoreCarPresenter extends BasePresenter implements StoreCarBase.IPresenter  {
    private StoreCarBase.IView mView;
    private StoreCarBase.IModel mModel;

    public StoreCarPresenter(StoreCarBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new StoreCarModel();
    }

    @Override
    public void getStoreInfoList(Map<String, String> headers, int currentPage) {
        mView.showLoading();
        addSubscription(mModel.getStoreInfoList(headers,currentPage,new ResponseCallback<StoreInfoListResultBean>() {
            @Override
            public void onSuccess(StoreInfoListResultBean result) {
                mView.hideLoading();
                mView.getStoreInfoList(result);

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
    public void getStoreCarNum(Map<String, String> headers) {
        addSubscription(mModel.getStoreCarNum(headers,new ResponseCallback<StoreCarNumResultBean>() {
            @Override
            public void onSuccess(StoreCarNumResultBean result) {
                mView.getStoreCarNum(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void addStoreCar(Map<String, String> headers, List<AddStoreCarParamBean> addStoreCarParamBeans) {
        addSubscription(mModel.addStoreCar(headers,addStoreCarParamBeans,new ResponseCallback<AddStoreCarResultBean>() {
            @Override
            public void onSuccess(AddStoreCarResultBean result) {
                mView.addStoreCar(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}