package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.AddShopCarResultBean;
import com.ibeef.cowboying.bean.CarListResultBean;
import com.ibeef.cowboying.bean.DeleteCarResultBean;
import com.ibeef.cowboying.bean.NowBuyOrderResultBean;
import com.ibeef.cowboying.bean.NowPayOrderParamBean;
import com.ibeef.cowboying.bean.NowPayOrderResultBean;
import com.ibeef.cowboying.bean.StoreAddrResultBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 购物车支付
 * @package com.ranhan.cowboying.base
 **/
public class StoreCarPayBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void nowBuyOrder(NowBuyOrderResultBean nowBuyOrderResultBean);
        void nowPayOrder(NowPayOrderResultBean nowPayOrderResultBean);
        void getCarList(CarListResultBean carListResultBean);
        void deleteStoreCar(DeleteCarResultBean deleteCarResultBean);
        void storeAddrList(StoreAddrResultBean storeAddrResultBean);
        void showLoading();
        void hideLoading();
    }

    public interface IPresenter {
        void nowBuyOrder(Map<String, String> headers,AddShopCarResultBean addShopCarResultBean);
        void deleteStoreCar(Map<String, String> headers,AddShopCarResultBean addShopCarResultBean);
        void nowPayOrder(Map<String, String> headers,NowPayOrderParamBean noPayOrderParamBean);
        void getCarList(Map<String, String> headers,int currentPage);
        void storeAddrList(Map<String, String> headers);
    }

    public interface IModel {
        Subscription nowBuyOrder(Map<String, String> headers,AddShopCarResultBean addShopCarResultBean, ResponseCallback<NowBuyOrderResultBean> callback);
        Subscription deleteStoreCar(Map<String, String> headers,AddShopCarResultBean addShopCarResultBean, ResponseCallback<DeleteCarResultBean> callback);
        Subscription nowPayOrder(Map<String, String> headers, NowPayOrderParamBean noPayOrderParamBean, ResponseCallback<NowPayOrderResultBean> callback);
        Subscription getCarList(Map<String, String> header, int currentPage,ResponseCallback<CarListResultBean> callback);
        Subscription storeAddrList(Map<String, String> header,ResponseCallback<StoreAddrResultBean> callback);
    }
}
