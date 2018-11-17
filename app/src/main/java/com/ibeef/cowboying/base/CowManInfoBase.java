package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.CowManInfosResultBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 牛人界面数据展示
 * @package com.ranhan.cowboying.base
 **/
public class CowManInfoBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getCowManInfos(CowManInfosResultBean cowManInfosResultBean);
    }

    public interface IPresenter {
        void getCowManInfos(Map<String, String> headers);
    }

    public interface IModel {
        Subscription getCowManInfos(Map<String, String> headers, ResponseCallback<CowManInfosResultBean> callback);
    }
}
