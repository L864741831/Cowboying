package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.AccountRegisterBase;
import com.ibeef.cowboying.base.CattleDetailBase;
import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.AdoptInfosResultBean;
import com.ibeef.cowboying.bean.CattleDetailResultBean;
import com.ibeef.cowboying.model.AccountRegisetModel;
import com.ibeef.cowboying.model.CattleDetailModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 牛只详请
 * @package com.ranhan.cowboying.presenter
 **/
public class CattleDetailPresenter extends BasePresenter implements CattleDetailBase.IPresenter  {
    private CattleDetailBase.IView mView;
    private CattleDetailBase.IModel mModel;

    public CattleDetailPresenter(CattleDetailBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new CattleDetailModel();
    }


    @Override
    public void getCattleDetail(Map<String, String> headers, int cattleId) {
        addSubscription(mModel.getCattleDetail(headers,cattleId,new ResponseCallback<CattleDetailResultBean>() {
            @Override
            public void onSuccess(CattleDetailResultBean result) {
                mView.getCattleDetail(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getAdoptInfos(Map<String, String> headers, String code, Integer schemeId, Integer currentPage) {
        mView.showLoading();
        addSubscription(mModel.getAdoptInfos(headers,code,schemeId,currentPage,new ResponseCallback<AdoptInfosResultBean>() {
            @Override
            public void onSuccess(AdoptInfosResultBean result) {
                mView.hideLoading();
                mView.getAdoptInfos(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.hideLoading();
                mView.showMsg(msg);
            }
        }));
    }
}
