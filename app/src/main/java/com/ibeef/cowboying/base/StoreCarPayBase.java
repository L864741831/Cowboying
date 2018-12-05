package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.AddStoreCarParamBean;
import com.ibeef.cowboying.bean.AddStoreCarResultBean;
import com.ibeef.cowboying.bean.CarListResultBean;
import com.ibeef.cowboying.bean.NowBuyOrderResultBean;
import com.ibeef.cowboying.bean.NowPayOrderParamBean;
import com.ibeef.cowboying.bean.NowPayOrderResultBean;
import com.ibeef.cowboying.bean.StoreCarNumResultBean;
import com.ibeef.cowboying.bean.StoreInfoListResultBean;

import java.util.List;
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
        void showLoading();
        void hideLoading();
    }

    public interface IPresenter {
        void nowBuyOrder(Map<String, String> headers,List<AddStoreCarParamBean> addStoreCarParamBeans);
        void nowPayOrder(Map<String, String> headers,NowPayOrderParamBean noPayOrderParamBean);
        void getCarList(Map<String, String> headers,int currentPage);
    }

    public interface IModel {
        Subscription nowBuyOrder(Map<String, String> headers,List<AddStoreCarParamBean> addStoreCarParamBeans, ResponseCallback<NowBuyOrderResultBean> callback);
        Subscription nowPayOrder(Map<String, String> headers, NowPayOrderParamBean noPayOrderParamBean, ResponseCallback<NowPayOrderResultBean> callback);
        Subscription getCarList(Map<String, String> header, int currentPage,ResponseCallback<CarListResultBean> callback);
    }
}
