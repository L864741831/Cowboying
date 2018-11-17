package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.CheckVersionBase;
import com.ibeef.cowboying.bean.CheckVersionBean;
import com.ibeef.cowboying.bean.CheckVersionParamBean;
import com.ibeef.cowboying.model.CheckVersionModel;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 版本升级
 * @package com.ranhan.cowboying.presenter
 **/
public class CheckVersionPresenter extends BasePresenter implements CheckVersionBase.IPresenter  {
    private CheckVersionBase.IView mView;
    private CheckVersionBase.IModel mModel;

    public CheckVersionPresenter(CheckVersionBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new CheckVersionModel();
    }


    @Override
    public void getCheckVersion(String version, CheckVersionParamBean checkVersionParamBean) {
        addSubscription(mModel.getCheckVersion(version,checkVersionParamBean,new ResponseCallback<CheckVersionBean>() {
            @Override
            public void onSuccess(CheckVersionBean result) {
                mView.getCheckVersion(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
