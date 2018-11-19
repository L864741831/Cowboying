package com.ibeef.cowboying.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.HomeAdBase;
import com.ibeef.cowboying.bean.HomeAdResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.HomeAdPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import rxfamily.view.BaseActivity;

/**
 * 启动页
 */
public class WelcomeActivity extends BaseActivity implements HomeAdBase.IView {
    private SharedPreferences mPref;
    //使用SharedPreferences记录是否第一次打开app
    public static final String KEY_HISTORY_KEYWORD = "key_welcome_keyword";
    private HomeAdPresenter homeAdPresenter;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }

    public void init(){
        homeAdPresenter=new HomeAdPresenter(this);
        token= Hawk.get(HawkKey.TOKEN);
        mPref = getSharedPreferences("isfirstopen", Activity.MODE_PRIVATE);
        final String history = mPref.getString(KEY_HISTORY_KEYWORD, "");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(TextUtils.isEmpty(token)){
                    startActivity(LoginActivity.class);
                    finish();
                }else {
                    //第一次启动进入引导页
                    if (TextUtils.isEmpty(history)) {
                        startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
                        SharedPreferences.Editor editor = mPref.edit();
                        editor.putString(KEY_HISTORY_KEYWORD, "firstopen");
                        editor.commit();
                        finish();
                    } else {
                        Map<String, String> reqData = new HashMap<>();
                        reqData.put("Authorization", token);
                        reqData.put("version", getVersionCodes());
                        homeAdPresenter.getHomeAd(reqData);

                    }
                }

            }
        },1000);
    }
    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getHomeAd(HomeAdResultBean homeAdResultBean) {
        if("000000".equals(homeAdResultBean.getCode())){
            if(!SDCardUtil.isNullOrEmpty(homeAdResultBean.getBizData())){
                //非第一次启动进入广告页
                Intent intent=new Intent(WelcomeActivity.this,AdActivity.class);
                intent.putExtra("info",homeAdResultBean);
                startActivity(intent);
                finish();
            }else {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }

        }else {
            showToast(homeAdResultBean.getMessage());
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
