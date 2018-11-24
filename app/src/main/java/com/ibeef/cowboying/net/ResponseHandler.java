package com.ibeef.cowboying.net;

import android.content.Intent;

import com.ibeef.cowboying.application.CowboyingApplication;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.utils.ActivityCollector;
import com.ibeef.cowboying.view.activity.LoginActivity;
import com.orhanobut.hawk.Hawk;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

public class ResponseHandler {

    public static String get(Throwable e){
        if (e instanceof HttpException) {

            HttpException httpException = (HttpException) e;

            int code = httpException.code();

            int i=500,j=404;
            if (code == i || code == j) {
                return "服务器出错";
            }

            if(code==401){
                if(!ActivityCollector.isForeground(CowboyingApplication.getInstance(),"LoginActivity")){
                    Hawk.put(HawkKey.TOKEN, "");
                    CowboyingApplication.getInstance().startActivity(new Intent(CowboyingApplication.getInstance(), LoginActivity.class));
                }
                return "未登录";
            }
        } else if (e instanceof ConnectException) {
            return "网络断开,请打开网络!";
        } else if (e instanceof SocketTimeoutException) {
            return "网络连接超时!";
        } else if(e instanceof UnknownHostException){
            return "无法连接到服务器，请检查您的网络或稍后重试!";
        } else if(e instanceof IOException){
            return "连接服务器失败";
        }

        return "发生未知错误" + e.getMessage();
    }

    public static String apiError(int code){
        switch(code){
            case 404:
                return "未找到API地址";
            case 500:
                return "服务器错误";
            default:
                break;
        }

        return "未知错误";
    }
}
