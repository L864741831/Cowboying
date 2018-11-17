package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.UpdateMobileBase;
import com.ibeef.cowboying.bean.UpdateMobileParamBean;
import com.ibeef.cowboying.bean.UpdateMobileResultBean;
import com.ibeef.cowboying.model.UpdateMobileModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 换绑手机号
 * @package com.ranhan.cowboying.presenter
 **/
public class UpdateMobilePresenter extends BasePresenter implements UpdateMobileBase.IPresenter  {
    private UpdateMobileBase.IView mView;
    private UpdateMobileBase.IModel mModel;

    public UpdateMobilePresenter(UpdateMobileBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new UpdateMobileModel();
    }

    @Override
    public void getUpdateMobile(Map<String, String> headers, UpdateMobileParamBean updateMobileParamBean) {
        addSubscription(mModel.getUpdateMobile(headers,updateMobileParamBean,new ResponseCallback<UpdateMobileResultBean>() {
            @Override
            public void onSuccess(UpdateMobileResultBean result) {
                mView.getUpdateMobile(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
