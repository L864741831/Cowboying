package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.CouponNumParamBean;
import com.ibeef.cowboying.bean.CouponNumResultBean;
import com.ibeef.cowboying.bean.UseCouponListResultBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe s使用优惠券
 * @package com.ranhan.cowboying.base
 **/
public class UseCouponListBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getCouponNum(CouponNumResultBean couponNumResultBean);
        void getUseCouponList(UseCouponListResultBean useCouponListResultBean);
        void showLoading();
        void hideLoading();
    }

    public interface IPresenter {
        void getCouponNum(Map<String, String> headers,CouponNumParamBean couponNumParamBean);
        void getUseCouponList(Map<String, String> headers,CouponNumParamBean couponNumParamBean);
    }

    public interface IModel {
        Subscription getCouponNum(Map<String, String> headers,CouponNumParamBean couponNumParamBean, ResponseCallback<CouponNumResultBean> callback);
        Subscription getUseCouponList(Map<String, String> headers,CouponNumParamBean couponNumParamBean, ResponseCallback<UseCouponListResultBean> callback);
    }
}
