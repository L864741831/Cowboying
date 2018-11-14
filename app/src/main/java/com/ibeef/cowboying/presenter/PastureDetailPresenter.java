package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.AccountRegisterBase;
import com.ibeef.cowboying.base.PastureDetailBase;
import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.JionPersonInfoResultBean;
import com.ibeef.cowboying.bean.SchemeDetailReultBean;
import com.ibeef.cowboying.model.AccountRegisetModel;
import com.ibeef.cowboying.model.PastureDetailModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 牧场详情
 * @package com.ranhan.cowboying.presenter
 **/
public class PastureDetailPresenter extends BasePresenter implements PastureDetailBase.IPresenter  {
    private PastureDetailBase.IView mView;
    private PastureDetailBase.IModel mModel;

    public PastureDetailPresenter(PastureDetailBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new PastureDetailModel();
    }

    @Override
    public void getJionPersonInfo(Map<String, String> headers, int schemeId,int currentPage) {
        mView.showLoading();
        addSubscription(mModel.getJionPersonInfo(headers,schemeId,currentPage,new ResponseCallback<JionPersonInfoResultBean>() {
            @Override
            public void onSuccess(JionPersonInfoResultBean result) {
                mView.hideLoading();
                mView.getJionPersonInfo(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.hideLoading();
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getSchemeDetail(Map<String, String> headers, int schemeId) {
        addSubscription(mModel.getSchemeDetail(headers,schemeId,new ResponseCallback<SchemeDetailReultBean>() {
            @Override
            public void onSuccess(SchemeDetailReultBean result) {
                mView.getSchemeDetail(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
