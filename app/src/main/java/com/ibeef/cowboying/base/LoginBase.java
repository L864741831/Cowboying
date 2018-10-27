package com.ibeef.cowboying.base;

import rxfamily.mvp.BaseView;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 登录
 * @package com.ranhan.cowboying.base
 **/
public class LoginBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
    }

    public interface IPresenter {

    }

    public interface IModel {

    }
}
