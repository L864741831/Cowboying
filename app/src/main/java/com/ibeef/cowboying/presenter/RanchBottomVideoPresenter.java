package com.ibeef.cowboying.presenter;

import android.util.Log;

import com.ibeef.cowboying.base.AccountRegisterBase;
import com.ibeef.cowboying.base.RanchBottomVideoBase;
import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.RanchBottomVideoResultBean;
import com.ibeef.cowboying.model.AccountRegisetModel;
import com.ibeef.cowboying.model.RanchBottomVideoModel;

import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 牧场随机视频
 * @package com.ranhan.cowboying.presenter
 **/
public class RanchBottomVideoPresenter extends BasePresenter implements RanchBottomVideoBase.IPresenter  {
    private RanchBottomVideoBase.IView mView;
    private RanchBottomVideoBase.IModel mModel;

    public RanchBottomVideoPresenter(RanchBottomVideoBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new RanchBottomVideoModel();
    }

    @Override
    public void getRanchBottomVideo(Map<String, String> headers) {
        addSubscription(mModel.getRanchBottomVideo(headers,new ResponseCallback<RanchBottomVideoResultBean>() {
            @Override
            public void onSuccess(RanchBottomVideoResultBean result) {
                mView.getRanchBottomVideo(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
