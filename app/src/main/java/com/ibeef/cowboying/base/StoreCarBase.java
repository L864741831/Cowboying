package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.AddStoreCarParamBean;
import com.ibeef.cowboying.bean.AddStoreCarResultBean;
import com.ibeef.cowboying.bean.StoreCarNumResultBean;
import com.ibeef.cowboying.bean.StoreInfoListResultBean;

import java.util.List;
import java.util.Map;

import retrofit2.http.Query;
import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 商城 购物车
 * @package com.ranhan.cowboying.base
 **/
public class StoreCarBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getStoreInfoList(StoreInfoListResultBean storeInfoListResultBean);
        void getStoreCarNum(StoreCarNumResultBean storeCarNumResultBean);
        void addStoreCar(AddStoreCarResultBean addStoreCarResultBean);
        void showLoading();
        void hideLoading();
    }

    public interface IPresenter {
        void getStoreInfoList(Map<String, String> headers, int currentPage);
        void getStoreCarNum(Map<String, String> headers);
        void addStoreCar(Map<String, String> headers,List<AddStoreCarParamBean> addStoreCarParamBeans);
    }

    public interface IModel {
        Subscription getStoreInfoList(Map<String, String> headers,int currentPage, ResponseCallback<StoreInfoListResultBean> callback);
        Subscription getStoreCarNum(Map<String, String> headers, ResponseCallback<StoreCarNumResultBean> callback);
        Subscription addStoreCar(Map<String, String> headers, List<AddStoreCarParamBean> addStoreCarParamBeans, ResponseCallback<AddStoreCarResultBean> callback);
    }
}
