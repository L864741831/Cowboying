package rxfamily.application;


import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDex;

import java.util.ArrayList;
import java.util.List;

import rxfamily.utils.NetWorkUtils;


public class RxApplication  extends android.support.multidex.MultiDexApplication{

    private static RxApplication sInstance;
    public static int mNetWorkState;
    private List<Activity> oList;//用于存放所有启动的Activity的集合
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mNetWorkState = NetWorkUtils.getNetworkState(this);
        oList = new ArrayList<Activity>();
    }

    public synchronized static  RxApplication getInstance(){
        return sInstance;
    }

        @Override
        protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        }


    /**
     * 添加Activity
     */
    public void addActivity_(Activity activity) {
// 判断当前集合中不存在该Activity
        if (!oList.contains(activity)) {
            oList.add(activity);//把当前Activity添加到集合中
        }
    }

    /**
     * 销毁单个Activity
     */
    public void removeActivity_(Activity activity) {
//判断当前集合中存在该Activity
        if (oList.contains(activity)) {
            oList.remove(activity);//从集合中移除
            activity.finish();//销毁当前Activity
        }
    }

    /**
     * 销毁所有的Activity
     */
    public void removeALLActivity_() {
        //通过循环，把集合中的所有Activity销毁
        for (Activity activity : oList) {
            activity.finish();
        }
    }

}
