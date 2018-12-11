package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.AddShopCarResultBean;
import com.ibeef.cowboying.bean.AddStoreCarResultBean;
import com.ibeef.cowboying.bean.StoreCarNumResultBean;
import com.ibeef.cowboying.bean.StoreInfoListResultBean;
import com.ibeef.cowboying.bean.StoreOneResultBean;
import com.ibeef.cowboying.bean.StorePriductIdParamBean;

import java.util.Map;

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
        void getStoreOneInfo(StoreOneResultBean storeOneResultBean);
        void showLoading();
        void hideLoading();
    }

    public interface IPresenter {
        void getStoreOneInfo(Map<String, String> headers,int productId);
        void getStoreInfoList(Map<String, String> headers, StorePriductIdParamBean storePriductIdParamBean);
        void getStoreCarNum(Map<String, String> headers);
        void addStoreCar(Map<String, String> headers,AddShopCarResultBean addShopCarResultBean);
    }

    public interface IModel {
        Subscription getStoreOneInfo(Map<String, String> headers,int productId, ResponseCallback<StoreOneResultBean> callback);
        Subscription getStoreInfoList(Map<String, String> headers,StorePriductIdParamBean storePriductIdParamBean, ResponseCallback<StoreInfoListResultBean> callback);
        Subscription getStoreCarNum(Map<String, String> headers, ResponseCallback<StoreCarNumResultBean> callback);
        Subscription addStoreCar(Map<String, String> headers, AddShopCarResultBean addShopCarResultBean, ResponseCallback<AddStoreCarResultBean> callback);
    }
}
