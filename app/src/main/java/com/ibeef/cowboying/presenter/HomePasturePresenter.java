package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.HomePastureBase;
import com.ibeef.cowboying.bean.HomeBuyCowPlanResultBean;
import com.ibeef.cowboying.bean.HomeParstureMessegeResultBean;
import com.ibeef.cowboying.bean.HomeParstureResultBean;
import com.ibeef.cowboying.model.HomeParstureModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 首页我的牧场
 * @package com.ranhan.cowboying.presenter
 **/
public class HomePasturePresenter extends BasePresenter implements HomePastureBase.IPresenter  {
    private HomePastureBase.IView mView;
    private HomePastureBase.IModel mModel;

    public HomePasturePresenter(HomePastureBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new HomeParstureModel();
    }
    @Override
    public void getHomeParsture(Map<String, String> headers) {
        addSubscription(mModel.getHomeParsture(headers,new ResponseCallback<HomeParstureResultBean>() {
            @Override
            public void onSuccess(HomeParstureResultBean result) {
                mView.getHomeParsture(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getHomeParstureMessege(Map<String, String> headers) {
        addSubscription(mModel.getHomeParstureMessege(headers,new ResponseCallback<HomeParstureMessegeResultBean>() {
            @Override
            public void onSuccess(HomeParstureMessegeResultBean result) {
                mView.getHomeParstureMessege(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getHomeBuyCowPlay(Map<String, String> headers) {
        addSubscription(mModel.getHomeBuyCowPlay(headers,new ResponseCallback<HomeBuyCowPlanResultBean>() {
            @Override
            public void onSuccess(HomeBuyCowPlanResultBean result) {
                mView.getHomeBuyCowPlay(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getHomeParstureMoreMessege(Map<String, String> headers,int currentPage) {
        mView.showLoading();
        addSubscription(mModel.getHomeParstureMoreMessege(headers,currentPage,new ResponseCallback<HomeParstureMessegeResultBean>() {
            @Override
            public void onSuccess(HomeParstureMessegeResultBean result) {
                mView.getHomeParstureMoreMessege(result);
                mView.hideLoading();

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
                mView.hideLoading();
            }
        }));
    }
}
