package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.OfflineStoreInfoBase;
import com.ibeef.cowboying.bean.OfflineStoreInfoResultBean;
import com.ibeef.cowboying.model.OfflineStoreInfoModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 线下门店详情
 * @package com.ranhan.cowboying.presenter
 **/
public class OfflineStoreInfoPresenter extends BasePresenter implements OfflineStoreInfoBase.IPresenter  {
    private OfflineStoreInfoBase.IView mView;
    private OfflineStoreInfoBase.IModel mModel;

    public OfflineStoreInfoPresenter(OfflineStoreInfoBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new OfflineStoreInfoModel();
    }

    @Override
    public void getOfflinestoreInfo(Map<String, String> headers) {
        addSubscription(mModel.getOfflinestoreInfo(headers,new ResponseCallback<OfflineStoreInfoResultBean>() {
            @Override
            public void onSuccess(OfflineStoreInfoResultBean result) {
                mView.getOfflinestoreInfo(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
