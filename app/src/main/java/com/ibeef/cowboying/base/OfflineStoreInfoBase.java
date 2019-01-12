package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.OfflineStoreInfoResultBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 线下门店详情
 * @package com.ranhan.cowboying.base
 **/
public class OfflineStoreInfoBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getOfflinestoreInfo(OfflineStoreInfoResultBean offlineStoreInfoResultBean);
    }

    public interface IPresenter {
        void getOfflinestoreInfo(Map<String, String> headers);
    }

    public interface IModel {
        Subscription getOfflinestoreInfo(Map<String, String> headers, ResponseCallback<OfflineStoreInfoResultBean> callback);
    }
}
