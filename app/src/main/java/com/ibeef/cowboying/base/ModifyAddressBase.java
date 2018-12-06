package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.AddAddressParamBean;
import com.ibeef.cowboying.bean.DeleteCarResultBean;
import com.ibeef.cowboying.bean.ShowAddressResultBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 收货地址管理
 * @package com.ranhan.cowboying.base
 **/
public class ModifyAddressBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void addAddress(DeleteCarResultBean deleteCarResultBean);
        void showAddressList(ShowAddressResultBean showAddressResultBean);
        void updateAddress(DeleteCarResultBean deleteCarResultBean);
        void deleteAddress(DeleteCarResultBean deleteCarResultBean);
        void showLoading();
        void hideLoading();
    }

    public interface IPresenter {
        void addAddress(Map<String, String> headers, AddAddressParamBean addAddressResultBean);
        void showAddressList(Map<String, String> headers,  int currentPage);
        void updateAddress(Map<String, String> headers, AddAddressParamBean addAddressResultBean);
        void deleteAddress(Map<String, String> headers, int addressId);
    }

    public interface IModel {
        Subscription addAddress(Map<String, String> headers, AddAddressParamBean addAddressResultBean, ResponseCallback<DeleteCarResultBean> callback);
        Subscription showAddressList(Map<String, String> headers, int currentPage, ResponseCallback<ShowAddressResultBean> callback);
        Subscription updateAddress(Map<String, String> header, AddAddressParamBean addAddressResultBean, ResponseCallback<DeleteCarResultBean> callback);
        Subscription deleteAddress(Map<String, String> header,  int addressId, ResponseCallback<DeleteCarResultBean> callback);
    }
}
