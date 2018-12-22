package com.ibeef.cowboying.base;

import com.ibeef.cowboying.bean.VideoAppkeyResultBean;

import java.util.Map;

import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 获取萤石云appkey
 * @package com.ranhan.cowboying.base
 **/
public class VideoAppkeyBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getVideoAppKey(VideoAppkeyResultBean videoAppkeyResultBean);
    }

    public interface IPresenter {
        void getVideoAppKey(Map<String, String> headers);
    }

    public interface IModel {
        Subscription getVideoAppKey(Map<String, String> headers, ResponseCallback<VideoAppkeyResultBean> callback);
    }
}
