package com.ibeef.cowboying.presenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.ibeef.cowboying.base.UserInfoBase;
import com.ibeef.cowboying.bean.ModifyHeadParamBean;
import com.ibeef.cowboying.bean.ModifyHeadResultBean;
import com.ibeef.cowboying.bean.ModifyNickParamBean;
import com.ibeef.cowboying.bean.ModifyNickResultBean;
import com.ibeef.cowboying.bean.RealNameParamBean;
import com.ibeef.cowboying.bean.RealNameReaultBean;
import com.ibeef.cowboying.bean.UserInfoResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.model.UserInfoModel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 个人信息
 * @package com.ranhan.cowboying.presenter
 **/
public class UserInfoPresenter extends BasePresenter implements UserInfoBase.IPresenter  {
    private UserInfoBase.IView mView;
    private UserInfoBase.IModel mModel;

    public UserInfoPresenter(UserInfoBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new UserInfoModel();
    }


    @Override
    public void getModifyHead(Map<String, String> headers, ModifyHeadParamBean modifyHeadParamBean) {
        addSubscription(mModel.getModifyHead(headers,modifyHeadParamBean,new ResponseCallback<ModifyHeadResultBean>() {
            @Override
            public void onSuccess(ModifyHeadResultBean result) {
                mView.getModifyHead(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getModifNick(Map<String, String> headers, ModifyNickParamBean modifyNickParamBean) {
        addSubscription(mModel.getModifNick(headers,modifyNickParamBean,new ResponseCallback<ModifyNickResultBean>() {
            @Override
            public void onSuccess(ModifyNickResultBean result) {
                mView.getModifNick(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getRealName(Map<String, String> headers, RealNameParamBean realNameParamBean) {
        addSubscription(mModel.getRealName(headers,realNameParamBean,new ResponseCallback<RealNameReaultBean>() {
            @Override
            public void onSuccess(RealNameReaultBean result) {
                mView.getRealName(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getUserInfo(Map<String, String> headers) {
        mView.showLoading();
        addSubscription(mModel.getUserInfo(headers,new ResponseCallback<UserInfoResultBean>() {
            @Override
            public void onSuccess(UserInfoResultBean result) {
                mView.hideLoading();
                mView.getUserInfo(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.hideLoading();
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public Intent cropHeadPhoto(Uri uri) {
        /**
         * 调用系统裁剪
         */
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 16);
        intent.putExtra("aspectY", 16);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 800);
        intent.putExtra("outputY", 800);

        intent.putExtra("noFaceDetection", false);
        File out = new File(Environment.getExternalStorageDirectory()+"/"+System.currentTimeMillis() + ".jpg");
        if (!out.getParentFile().exists()) {
            out.getParentFile().mkdirs();
        }
        Log.e(Constant.TAG,out.getAbsolutePath()+"<?????????????");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(out));
        intent.putExtra("cropImg" , out.getAbsolutePath());
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        return intent;
    }

    @Override
    public void setPicToView(Bitmap mBitmap, String path, FileOutputStream b) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
            // 检测sd是否可用
            mView.showMsg("请插入SD卡");
        }
//        File file = new File(path);
//        file.mkdirs();// 创建文件夹
//        String fileName = path + System.currentTimeMillis()+"head.jpg";
//
//        try {
//            b = new FileOutputStream(fileName);
//            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
//            // 把数据写入文件
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // 关闭流
//                b.flush();
//                b.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        Log.e(Constant.TAG,path+"<夏明?????????????");
        // 图片名字
        mView.isTakePhoeto(path);
    }
}
