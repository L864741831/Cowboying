package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.SellCowsBase;
import com.ibeef.cowboying.bean.CreatSellCowsParamBean;
import com.ibeef.cowboying.bean.CreatSellCowsResultBean;
import com.ibeef.cowboying.bean.SellCowsResultBean;
import com.ibeef.cowboying.model.SellCowsModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 卖牛
 * @package com.ranhan.cowboying.presenter
 **/
public class SellCowsPresenter extends BasePresenter implements SellCowsBase.IPresenter  {
    private SellCowsBase.IView mView;
    private SellCowsBase.IModel mModel;

    public SellCowsPresenter(SellCowsBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new SellCowsModel();
    }

    @Override
    public void getSellCows(Map<String, String> headers, String orderId) {
        addSubscription(mModel.getSellCows(headers,orderId,new ResponseCallback<SellCowsResultBean>() {
            @Override
            public void onSuccess(SellCowsResultBean result) {
                mView.getSellCows(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getCreatSellCows(Map<String, String> headers, CreatSellCowsParamBean creatSellCowsParamBean) {
        addSubscription(mModel.getCreatSellCows(headers,creatSellCowsParamBean,new ResponseCallback<CreatSellCowsResultBean>() {
            @Override
            public void onSuccess(CreatSellCowsResultBean result) {
                mView.getCreatSellCows(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
