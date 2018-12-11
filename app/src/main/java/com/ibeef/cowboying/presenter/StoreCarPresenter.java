package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.StoreCarBase;
import com.ibeef.cowboying.bean.AddShopCarResultBean;
import com.ibeef.cowboying.bean.AddStoreCarResultBean;
import com.ibeef.cowboying.bean.StoreCarNumResultBean;
import com.ibeef.cowboying.bean.StoreInfoListResultBean;
import com.ibeef.cowboying.bean.StoreOneResultBean;
import com.ibeef.cowboying.bean.StorePriductIdParamBean;
import com.ibeef.cowboying.model.StoreCarModel;

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
    public void getStoreOneInfo(Map<String, String> headers, int productId) {
        addSubscription(mModel.getStoreOneInfo(headers,productId,new ResponseCallback<StoreOneResultBean>() {
            @Override
            public void onSuccess(StoreOneResultBean result) {
                mView.getStoreOneInfo(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getStoreInfoList(Map<String, String> headers, StorePriductIdParamBean storePriductIdParamBean) {
        mView.showLoading();
        addSubscription(mModel.getStoreInfoList(headers,storePriductIdParamBean,new ResponseCallback<StoreInfoListResultBean>() {
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
    public void addStoreCar(Map<String, String> headers, AddShopCarResultBean addShopCarResultBean) {
        addSubscription(mModel.addStoreCar(headers,addShopCarResultBean,new ResponseCallback<AddStoreCarResultBean>() {
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
