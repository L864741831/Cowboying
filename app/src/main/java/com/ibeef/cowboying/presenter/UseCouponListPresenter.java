package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.UseCouponListBase;
import com.ibeef.cowboying.bean.CouponNumParamBean;
import com.ibeef.cowboying.bean.CouponNumResultBean;
import com.ibeef.cowboying.bean.UseCouponListResultBean;
import com.ibeef.cowboying.model.UseCouponListModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe s使用优惠券
 * @package com.ranhan.cowboying.presenter
 **/
public class UseCouponListPresenter extends BasePresenter implements UseCouponListBase.IPresenter  {
    private UseCouponListBase.IView mView;
    private UseCouponListBase.IModel mModel;

    public UseCouponListPresenter(UseCouponListBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new UseCouponListModel();
    }

    @Override
    public void getCouponNum(Map<String, String> headers, CouponNumParamBean couponNumParamBean) {
        addSubscription(mModel.getCouponNum(headers,couponNumParamBean,new ResponseCallback<CouponNumResultBean>() {
            @Override
            public void onSuccess(CouponNumResultBean result) {
                mView.getCouponNum(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getUseCouponList(Map<String, String> headers, CouponNumParamBean couponNumParamBean) {
        mView.showLoading();
        addSubscription(mModel.getUseCouponList(headers,couponNumParamBean,new ResponseCallback<UseCouponListResultBean>() {
            @Override
            public void onSuccess(UseCouponListResultBean result) {
                mView.hideLoading();
                mView.getUseCouponList(result);

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
