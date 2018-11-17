package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.BindMobileBase;
import com.ibeef.cowboying.bean.BindMobileParamBean;
import com.ibeef.cowboying.bean.BindMobileResultBean;
import com.ibeef.cowboying.model.BindMobileModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 绑定手机号
 * @package com.ranhan.cowboying.presenter
 **/
public class BindMobilePresenter extends BasePresenter implements BindMobileBase.IPresenter  {
    private BindMobileBase.IView mView;
    private BindMobileBase.IModel mModel;

    public BindMobilePresenter(BindMobileBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new BindMobileModel();
    }


    @Override
    public void getBindMobile(Map<String, String> headers, BindMobileParamBean bindMobileParamBean) {
        addSubscription(mModel.getBindMobile(headers,bindMobileParamBean,new ResponseCallback<BindMobileResultBean>() {
            @Override
            public void onSuccess(BindMobileResultBean result) {
                mView.getBindMobile(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
