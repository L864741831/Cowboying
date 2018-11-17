package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.CowManInfoBase;
import com.ibeef.cowboying.bean.CowManInfosResultBean;
import com.ibeef.cowboying.model.CowManInfoModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 牛人界面数据展示
 * @package com.ranhan.cowboying.presenter
 **/
public class CowManInfoPresenter extends  BasePresenter implements CowManInfoBase.IPresenter  {
    private CowManInfoBase.IView mView;
    private CowManInfoBase.IModel mModel;

    public CowManInfoPresenter(CowManInfoBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new CowManInfoModel();
    }

    @Override
    public void getCowManInfos(Map<String, String> headers) {
        addSubscription(mModel.getCowManInfos(headers,new ResponseCallback<CowManInfosResultBean>() {
            @Override
            public void onSuccess(CowManInfosResultBean result) {
                mView.getCowManInfos(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
