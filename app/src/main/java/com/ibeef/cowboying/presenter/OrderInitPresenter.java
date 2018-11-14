package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.AccountRegisterBase;
import com.ibeef.cowboying.base.OrderInitBase;
import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.CreatOderResultBean;
import com.ibeef.cowboying.bean.CreatOrderParamBean;
import com.ibeef.cowboying.bean.PayInitParamBean;
import com.ibeef.cowboying.bean.PayInitResultBean;
import com.ibeef.cowboying.model.AccountRegisetModel;
import com.ibeef.cowboying.model.OrderInitModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 买牛创建订单
 * @package com.ranhan.cowboying.presenter
 **/
public class OrderInitPresenter extends BasePresenter implements OrderInitBase.IPresenter  {
    private OrderInitBase.IView mView;
    private OrderInitBase.IModel mModel;

    public OrderInitPresenter(OrderInitBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new OrderInitModel();
    }


    @Override
    public void getCreatOder(Map<String, String> headers, CreatOrderParamBean creatOrderParamBean) {
        addSubscription(mModel.getCreatOder(headers,creatOrderParamBean,new ResponseCallback<CreatOderResultBean>() {
            @Override
            public void onSuccess(CreatOderResultBean result) {
                mView.getCreatOder(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getPayInit(Map<String, String> headers, PayInitParamBean payInitParamBean) {
        addSubscription(mModel.getPayInit(headers,payInitParamBean,new ResponseCallback<PayInitResultBean>() {
            @Override
            public void onSuccess(PayInitResultBean result) {
                mView.getPayInit(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
