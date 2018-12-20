package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.AccountRegisterBase;
import com.ibeef.cowboying.base.MessegeBase;
import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.DeleteMessegeResultBean;
import com.ibeef.cowboying.bean.MessegeListReslutBean;
import com.ibeef.cowboying.bean.MessegeNumResultBean;
import com.ibeef.cowboying.model.AccountRegisetModel;
import com.ibeef.cowboying.model.MessegeModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 消息管理
 * @package com.ranhan.cowboying.presenter
 **/
public class MessegePresenter extends BasePresenter implements MessegeBase.IPresenter  {
    private MessegeBase.IView mView;
    private MessegeBase.IModel mModel;

    public MessegePresenter(MessegeBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new MessegeModel();
    }

    @Override
    public void getMessegeList(Map<String, String> headers, int type, int curPage) {
        mView.showLoading();
        addSubscription(mModel.getMessegeList(headers,type,curPage,new ResponseCallback<MessegeListReslutBean>() {
            @Override
            public void onSuccess(MessegeListReslutBean result) {
                mView.hideLoading();
                mView.getMessegeList(result);

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
    public void getMessegeNum(Map<String, String> headers) {
        addSubscription(mModel.getMessegeNum(headers,new ResponseCallback<MessegeNumResultBean>() {
            @Override
            public void onSuccess(MessegeNumResultBean result) {
                mView.getMessegeNum(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getMessegeDelete(Map<String, String> headers, int messageId) {
        addSubscription(mModel.getMessegeDelete(headers,messageId,new ResponseCallback<DeleteMessegeResultBean>() {
            @Override
            public void onSuccess(DeleteMessegeResultBean result) {
                mView.getMessegeDelete(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
