package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.OssResultBean;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 阿里云直传图片
 * @package com.ranhan.cowboying.base
 **/
public class GetOssImgBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getOssImg(OssResultBean ossResultBean);
    }

    public interface IPresenter {
        void getOssImg(String version);
    }

    public interface IModel {
        Subscription getOssImg(String version, ResponseCallback<OssResultBean> callback);
    }
}
