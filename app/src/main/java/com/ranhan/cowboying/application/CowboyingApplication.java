package com.ranhan.cowboying.application;

import android.os.StrictMode;


import com.mob.MobSDK;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;

import rxfamily.application.RxApplication;

public class CowboyingApplication extends RxApplication {
    private static CowboyingApplication sInstance;

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

        MobSDK.init(this);

    }

    public synchronized static CowboyingApplication getInstance(){
        return sInstance;
    }
}
