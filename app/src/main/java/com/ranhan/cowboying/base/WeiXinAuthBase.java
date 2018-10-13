package com.ranhan.cowboying.base;
import com.ranhan.cowboying.bean.WeixinAuthFirstBean;
import com.ranhan.cowboying.bean.WeixinAuthSecondeBean;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/12 16:16
 * @describe
 * @package com.ranhan.yabei.base
 **/
public class WeiXinAuthBase {
    public interface IView extends BaseView {
        void getWeixinAuthFirst(WeixinAuthFirstBean weixinAuthFirstBean);
        void getWeixinAuthSeconde(WeixinAuthSecondeBean weixinAuthSecondeBean);
        void showMsg(String msg);
    }

    public interface IPresenter {
        void getWeixinAuthFirst(String appid, String secret, String code, String grant_type);
        void getWeixinAuthSeconde(String access_token, String openid);

    }

    public interface IModel {
        /**
         * 微信授权第一步
         * @param appid
         * @param secret
         * @param code
         * @param grant_type
         * @param callback
         * @return 通过code获取access_token
         */
        Subscription getWeixinAuthFirst(String appid, String secret, String code, String grant_type, ResponseCallback<WeixinAuthFirstBean> callback);

        /**
         * 微信授权第二步
         * @param access_token
         * @param openid
         * @param callback
         * @return 根据access_token再去获取UserInfo
         */
        Subscription getWeixinAuthSeconde(String access_token, String openid, ResponseCallback<WeixinAuthSecondeBean> callback);
    }
}
