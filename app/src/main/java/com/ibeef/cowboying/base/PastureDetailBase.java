package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.JionPersonInfoResultBean;
import com.ibeef.cowboying.bean.SchemeDetailReultBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 牧场详情
 * @package com.ranhan.cowboying.base
 **/
public class PastureDetailBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getJionPersonInfo(JionPersonInfoResultBean jionPersonInfoResultBean);
        void getSchemeDetail(SchemeDetailReultBean schemeDetailReultBean);
        void showLoading();
        void hideLoading();
    }

    public interface IPresenter {
        void getJionPersonInfo(Map<String, String> headers,int schemeId,int currentPage);
        void getSchemeDetail(Map<String, String> headers,int schemeId);
    }

    public interface IModel {
        Subscription getJionPersonInfo(Map<String, String> headers,int schemeId,int currentPage, ResponseCallback<JionPersonInfoResultBean> callback);
        Subscription getSchemeDetail(Map<String, String> headers,int schemeId, ResponseCallback<SchemeDetailReultBean> callback);
    }
}
