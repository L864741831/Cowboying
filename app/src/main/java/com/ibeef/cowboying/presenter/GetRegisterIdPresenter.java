package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.AccountRegisterBase;
import com.ibeef.cowboying.base.GetJgRegisterIdBase;
import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.JgParamBean;
import com.ibeef.cowboying.bean.JgResultBean;
import com.ibeef.cowboying.model.AccountRegisetModel;
import com.ibeef.cowboying.model.GetJgRegisterIdModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 获取极光注册ID
 * @package com.ranhan.cowboying.presenter
 **/
public class GetRegisterIdPresenter extends BasePresenter implements GetJgRegisterIdBase.IPresenter  {
    private GetJgRegisterIdBase.IView mView;
    private GetJgRegisterIdBase.IModel mModel;

    public GetRegisterIdPresenter(GetJgRegisterIdBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new GetJgRegisterIdModel();
    }
    @Override
    public void getJgRegisteId(Map<String, String> headers, JgParamBean jgParamBean) {
        addSubscription(mModel.getJgRegisteId(headers,jgParamBean,new ResponseCallback<JgResultBean>() {
            @Override
            public void onSuccess(JgResultBean result) {
                mView.getJgRegisteId(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
