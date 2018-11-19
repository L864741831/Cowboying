package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.EditLogionPwdBase;
import com.ibeef.cowboying.bean.EditLoginPwdParamBean;
import com.ibeef.cowboying.bean.EditLoginPwdResultBean;
import com.ibeef.cowboying.bean.RestLoginParamBean;
import com.ibeef.cowboying.bean.RestLoginPwdResultBean;
import com.ibeef.cowboying.model.EditLoginPwdModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 重置登录密码
 * @package com.ranhan.cowboying.presenter
 **/
public class EditLoginPwdPresenter extends BasePresenter implements EditLogionPwdBase.IPresenter  {
    private EditLogionPwdBase.IView mView;
    private EditLogionPwdBase.IModel mModel;

    public EditLoginPwdPresenter(EditLogionPwdBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new EditLoginPwdModel();
    }

    @Override
    public void getEditLoginPwd(Map<String, String> headers, EditLoginPwdParamBean editLoginPwdParamBean) {
        addSubscription(mModel.getEditLoginPwd(headers,editLoginPwdParamBean,new ResponseCallback<EditLoginPwdResultBean>() {
            @Override
            public void onSuccess(EditLoginPwdResultBean result) {
                mView.getEditLoginPwd(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getRestLoginPwd(Map<String, String> headers, RestLoginParamBean restLoginParamBean) {
        addSubscription(mModel.getRestLoginPwd(headers,restLoginParamBean,new ResponseCallback<RestLoginPwdResultBean>() {
            @Override
            public void onSuccess(RestLoginPwdResultBean result) {
                mView.getRestLoginPwd(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
