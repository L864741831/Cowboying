package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.DeleteMessegeResultBean;
import com.ibeef.cowboying.bean.MessegeListReslutBean;
import com.ibeef.cowboying.bean.MessegeNumResultBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 消息管理
 * @package com.ranhan.cowboying.base
 **/
public class MessegeBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getMessegeList(MessegeListReslutBean messegeListReslutBean);
        void getMessegeNum(MessegeNumResultBean numResultBean);
        void getMessegeDelete(DeleteMessegeResultBean deleteMessegeResultBean);
        void showLoading();
        void hideLoading();
    }

    public interface IPresenter {
        void getMessegeList(Map<String, String> headers,int type, int curPage);
        void getMessegeNum(Map<String, String> headers);
        void getMessegeDelete(Map<String, String> headers, int messageId);
    }

    public interface IModel {
        Subscription getMessegeList(Map<String, String> headers,int type, int curPage, ResponseCallback<MessegeListReslutBean> callback);
        Subscription getMessegeNum(Map<String, String> headers, ResponseCallback<MessegeNumResultBean> callback);
        Subscription getMessegeDelete(Map<String, String> headers,  int messageId,ResponseCallback<DeleteMessegeResultBean> callback);
    }
}
