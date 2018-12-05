package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.MyContractListBean;
import com.ibeef.cowboying.bean.MyContractURLBean;
import com.ibeef.cowboying.bean.MyDiscountCouponListBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author aaa
 * @package com.ranhan.cowboying.base
 **/
public class MyContractBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void showLoading();
        void hideLoading();
        void getMyContrantList(MyContractListBean myContractListBean);
        void getMyContrantURL(MyContractURLBean myContractURLBean);
        void getMyDiscountCouponList(MyDiscountCouponListBean myDiscountCouponListBean);
    }

    public interface IPresenter {
        void getMyContrantList(Map<String, String> headers,int currentPage);
        void getMyContrantURL(Map<String, String> headers, String type, String fileName);
        void getMyDiscountCouponList(Map<String, String> headers,int currentPage,int pageSize, String findType);
    }

    public interface IModel {
        Subscription getMyContrantList(Map<String, String> headers,int currentPage, ResponseCallback<MyContractListBean> callback);
        Subscription getMyContrantURL(Map<String, String> headers, String type, String fileName, ResponseCallback<MyContractURLBean> callback);
        Subscription getMyDiscountCouponList(Map<String, String> headers,int currentPage,int pageSize, String findType, ResponseCallback<MyDiscountCouponListBean> callback);
    }
}
