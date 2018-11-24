package com.ibeef.cowboying.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

import java.util.List;

/**
 * @author ls
 * @date on 2018/11/24 11:01
 * @describe
 * @package com.ibeef.cowboying.utils
 **/
public class ActivityCollector {
    /**
     * 判断某个界面是否在前台
     *
     * @param context  Context
     * @param className 界面的类名
     * @return 是否在前台显示
     */
    public static boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className))
            return false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
//    boolean flag=false;
        for (ActivityManager.RunningTaskInfo taskInfo : list) {
            if (taskInfo.topActivity.getShortClassName().contains(className)) { // 说明它已经启动了
//        flag = true;
                return true;
            }
        }
        return false;
    }
}
