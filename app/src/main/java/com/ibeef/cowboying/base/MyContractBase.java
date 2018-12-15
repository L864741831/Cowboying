package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.MyContractListBean;
import com.ibeef.cowboying.bean.MyContractURLBean;
import com.ibeef.cowboying.bean.MyDiscountCouponListBean;
import com.ibeef.cowboying.bean.PayCodeBean;
import com.ibeef.cowboying.bean.VipCardBean;
import com.ibeef.cowboying.bean.VipCardListBean;

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
        void showPayCode(PayCodeBean payCodeBean);
        void showVipCard(VipCardBean vipCardBean);
        void showVipCardHistory(VipCardListBean vipCardListBean);
    }

    public interface IPresenter {
        void getMyContrantList(Map<String, String> headers,int currentPage);
        void getMyContrantURL(Map<String, String> headers, String type, String fileName);
        void getMyDiscountCouponList(Map<String, String> headers,int currentPage,int pageSize, String findType);
        void showPayCode(Map<String, String> headers,String payType);
        void showVipCard(Map<String, String> headers);
        void showVipCardHistory(Map<String, String> headers,int curPage);
    }

    public interface IModel {
        Subscription getMyContrantList(Map<String, String> headers,int currentPage, ResponseCallback<MyContractListBean> callback);
        Subscription getMyContrantURL(Map<String, String> headers, String type, String fileName, ResponseCallback<MyContractURLBean> callback);
        Subscription getMyDiscountCouponList(Map<String, String> headers,int currentPage,int pageSize, String findType, ResponseCallback<MyDiscountCouponListBean> callback);
        Subscription showPayCode(Map<String, String> headers,String payType, ResponseCallback<PayCodeBean> callback);
        Subscription showVipCard(Map<String, String> headers, ResponseCallback<VipCardBean> callback);
        Subscription showVipCardHistory(Map<String, String> headers,int curPage, ResponseCallback<VipCardListBean> callback);
    }
}
