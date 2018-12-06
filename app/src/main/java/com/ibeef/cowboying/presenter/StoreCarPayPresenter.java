package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.StoreCarPayBase;
import com.ibeef.cowboying.bean.AddStoreCarParamBean;
import com.ibeef.cowboying.bean.CarListResultBean;
import com.ibeef.cowboying.bean.DeleteCarResultBean;
import com.ibeef.cowboying.bean.NowBuyOrderResultBean;
import com.ibeef.cowboying.bean.NowPayOrderParamBean;
import com.ibeef.cowboying.bean.NowPayOrderResultBean;
import com.ibeef.cowboying.model.StoreCarPayModel;

import java.util.List;
import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe  购物车 支付
 * @package com.ranhan.cowboying.presenter
 **/
public class StoreCarPayPresenter extends BasePresenter implements StoreCarPayBase.IPresenter  {
    private StoreCarPayBase.IView mView;
    private StoreCarPayBase.IModel mModel;

    public StoreCarPayPresenter(StoreCarPayBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new StoreCarPayModel();
    }

    @Override
    public void nowBuyOrder(Map<String, String> headers, List<AddStoreCarParamBean> addStoreCarParamBeans) {
        addSubscription(mModel.nowBuyOrder(headers,addStoreCarParamBeans,new ResponseCallback<NowBuyOrderResultBean>() {
            @Override
            public void onSuccess(NowBuyOrderResultBean result) {
                mView.nowBuyOrder(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void deleteStoreCar(Map<String, String> headers, List<AddStoreCarParamBean> addStoreCarParamBeans) {
        addSubscription(mModel.deleteStoreCar(headers,addStoreCarParamBeans,new ResponseCallback<DeleteCarResultBean>() {
            @Override
            public void onSuccess(DeleteCarResultBean result) {
                mView.deleteStoreCar(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }


    @Override
    public void nowPayOrder(Map<String, String> headers, NowPayOrderParamBean noPayOrderParamBean) {
        addSubscription(mModel.nowPayOrder(headers,noPayOrderParamBean,new ResponseCallback<NowPayOrderResultBean>() {
            @Override
            public void onSuccess(NowPayOrderResultBean result) {
                mView.nowPayOrder(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getCarList(Map<String, String> headers, int currentPage) {
        mView.showLoading();
        addSubscription(mModel.getCarList(headers,currentPage,new ResponseCallback<CarListResultBean>() {
            @Override
            public void onSuccess(CarListResultBean result) {
                mView.hideLoading();
                mView.getCarList(result);

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
