package com.ranhan.cowboying.view.activity;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.ranhan.cowboying.R;

import rxfamily.view.BaseActivity;

/**
 * 启动页
 */
public class WelcomeActivity extends BaseActivity {
    private SharedPreferences mPref;
    //使用SharedPreferences记录是否第一次打开app
    public static final String KEY_HISTORY_KEYWORD = "key_welcome_keyword";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }

    public void init(){
        mPref = getSharedPreferences("isfirstopen", Activity.MODE_PRIVATE);
        final String history = mPref.getString(KEY_HISTORY_KEYWORD, "");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //第一次启动进入引导页
                if(TextUtils.isEmpty(history)){
                    startActivity(new Intent(WelcomeActivity.this,GuideActivity.class));
                    SharedPreferences.Editor editor = mPref.edit();
                    editor.putString(KEY_HISTORY_KEYWORD, "firstopen");
                    editor.commit();
                    finish();
                }else {
                    //非第一次启动进入广告页
//                    startActivity(new Intent(WelcomeActivity.this,AdActivity.class));
                    // TODO: 2018/10/13  需要请求服务器携带广告地址和title
                    startActivity(MainActivity.class);
                    finish();
                }

            }
        },1000);


    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
