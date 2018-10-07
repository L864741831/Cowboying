package rxfamily.application;


import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import rxfamily.utils.NetWorkUtils;


public class RxApplication  extends android.support.multidex.MultiDexApplication{

    private static RxApplication sInstance;
    public static int mNetWorkState;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mNetWorkState = NetWorkUtils.getNetworkState(this);
    }

    public synchronized static  RxApplication getInstance(){
        return sInstance;
    }

        @Override
        protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        }
}
