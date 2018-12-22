package com.ibeef.cowboying.application;

import android.os.StrictMode;
import android.util.Log;

import com.ibeef.cowboying.config.Constant;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;
import com.videogo.openapi.EZOpenSDK;

import cn.jpush.android.api.JPushInterface;
import rxfamily.application.RxApplication;

public class CowboyingApplication extends RxApplication {
    private static CowboyingApplication sInstance;
    public static String registrationId;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        Hawk.init(this)
                .setStorage(HawkBuilder.newSqliteStorage(this))
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.HIGHEST)
                .setPassword("RanHanSgyt20170718AAABBBCCC")
                .setLogLevel(LogLevel.FULL)
                .build();

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

//        MobSDK.init(this);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        registrationId= JPushInterface.getRegistrationID(this);
        Log.e("--main--",registrationId+"????????");
        /** * sdk日志开关，正式发布需要去掉 */
        EZOpenSDK.showSDKLog(true);
       /** * 设置是否支持P2P取流,详见api */
        EZOpenSDK.enableP2P(false);
       /** * APP_KEY请替换成自己申请的 */
        EZOpenSDK.initLib(this, Constant.APPKEYYHC);

    }

    public synchronized static CowboyingApplication getInstance(){
        return sInstance;
    }
}
