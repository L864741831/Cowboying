package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.ModifyAddressBase;
import com.ibeef.cowboying.bean.AddAddressParamBean;
import com.ibeef.cowboying.bean.DeleteCarResultBean;
import com.ibeef.cowboying.bean.ShowAddressResultBean;
import com.ibeef.cowboying.model.ModifyAddressModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe  收货地址管理
 * @package com.ranhan.cowboying.presenter
 **/
public class ModifyAddressPresenter extends BasePresenter implements ModifyAddressBase.IPresenter  {
    private ModifyAddressBase.IView mView;
    private ModifyAddressBase.IModel mModel;

    public ModifyAddressPresenter(ModifyAddressBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new ModifyAddressModel();
    }

    @Override
    public void addAddress(Map<String, String> headers, AddAddressParamBean addAddressResultBean) {
        addSubscription(mModel.addAddress(headers,addAddressResultBean,new ResponseCallback<DeleteCarResultBean>() {
            @Override
            public void onSuccess(DeleteCarResultBean result) {
                mView.addAddress(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void showAddressList(Map<String, String> headers, int currentPage) {
        mView.showLoading();
        addSubscription(mModel.showAddressList(headers,currentPage,new ResponseCallback<ShowAddressResultBean>() {
            @Override
            public void onSuccess(ShowAddressResultBean result) {
                mView.hideLoading();
                mView.showAddressList(result);

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
    public void updateAddress(Map<String, String> headers, AddAddressParamBean addAddressResultBean) {
        addSubscription(mModel.updateAddress(headers,addAddressResultBean,new ResponseCallback<DeleteCarResultBean>() {
            @Override
            public void onSuccess(DeleteCarResultBean result) {
                mView.updateAddress(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void deleteAddress(Map<String, String> headers, int addressId) {
        addSubscription(mModel.deleteAddress(headers,addressId,new ResponseCallback<DeleteCarResultBean>() {
            @Override
            public void onSuccess(DeleteCarResultBean result) {
                mView.deleteAddress(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
