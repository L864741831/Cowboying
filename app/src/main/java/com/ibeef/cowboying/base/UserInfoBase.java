package com.ibeef.cowboying.base;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.ModifyHeadParamBean;
import com.ibeef.cowboying.bean.ModifyHeadResultBean;
import com.ibeef.cowboying.bean.ModifyNickParamBean;
import com.ibeef.cowboying.bean.ModifyNickResultBean;
import com.ibeef.cowboying.bean.RealNameParamBean;
import com.ibeef.cowboying.bean.RealNameReaultBean;
import com.ibeef.cowboying.bean.UserInfoResultBean;

import java.io.FileOutputStream;
import java.util.Map;

import retrofit2.http.Body;
import rx.Subscription;
import rxfamily.mvp.BaseView;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe 个人信息
 * @package com.ranhan.cowboying.base
 **/
public class UserInfoBase {
    public interface IView extends BaseView {
        void showMsg(String msg);
        void getModifyHead(ModifyHeadResultBean modifyHeadResultBean);
        void getModifNick(ModifyNickResultBean modifyNickResultBean);
        void getRealName(RealNameReaultBean realNameReaultBean);
        void getUserInfo(UserInfoResultBean userInfoResultBean);
        void  isTakePhoeto(String msg);
    }

    public interface IPresenter {
        void getModifyHead(Map<String, String> headers, ModifyHeadParamBean modifyHeadParamBean);
        void getModifNick(Map<String, String> headers,  ModifyNickParamBean modifyNickParamBean);
        void getRealName(Map<String, String> headers,   RealNameParamBean realNameParamBean);
        void getUserInfo(Map<String, String> headers);
        Intent cropHeadPhoto(Uri uri);
        void setPicToView(Bitmap mBitmap, String path, FileOutputStream b);
    }

    public interface IModel {
        Subscription getModifyHead(Map<String, String> headers, ModifyHeadParamBean modifyHeadParamBean, ResponseCallback<ModifyHeadResultBean> callback);
        Subscription getModifNick(Map<String, String> headers,  ModifyNickParamBean modifyNickParamBean, ResponseCallback<ModifyNickResultBean> callback);
        Subscription getRealName(Map<String, String> headers, RealNameParamBean realNameParamBean, ResponseCallback<RealNameReaultBean> callback);
        Subscription getUserInfo(Map<String, String> headers, ResponseCallback<UserInfoResultBean> callback);
    }
}
