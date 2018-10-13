
package com.ranhan.cowboying.presenter;

import android.util.Log;

import com.ranhan.cowboying.base.WeiXinAuthBase;
import com.ranhan.cowboying.bean.WeixinAuthFirstBean;
import com.ranhan.cowboying.bean.WeixinAuthSecondeBean;
import com.ranhan.cowboying.model.WeixinAuthModel;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @Author $ls
 * @Date 2018/3/29 16:09
 * @Description
 */

public class WeixinAuthPresenter extends BasePresenter implements WeiXinAuthBase.IPresenter {

    private WeiXinAuthBase.IView mView;
    private WeiXinAuthBase.IModel mModel;

    public WeixinAuthPresenter(WeiXinAuthBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new WeixinAuthModel();
    }

    @Override
    public void getWeixinAuthFirst(String appid, String secret, String code, String grant_type) {
        addSubscription(mModel.getWeixinAuthFirst(appid,secret,code,grant_type,new ResponseCallback<WeixinAuthFirstBean>() {
            @Override
            public void onSuccess(WeixinAuthFirstBean result) {
                mView.getWeixinAuthFirst(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getWeixinAuthSeconde(String access_token, String openid) {
        addSubscription(mModel.getWeixinAuthSeconde(access_token,openid,new ResponseCallback<WeixinAuthSecondeBean>() {
            @Override
            public void onSuccess(WeixinAuthSecondeBean result) {
                mView.getWeixinAuthSeconde(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
