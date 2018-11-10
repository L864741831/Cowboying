package com.ibeef.cowboying.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Bundle;
import android.text.TextUtils;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.HomeAdBase;
import com.ibeef.cowboying.bean.HomeAdResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.presenter.HomeAdPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;

import rxfamily.view.BaseActivity;

/**
 * 启动页
 */
public class WelcomeActivity extends BaseActivity implements HomeAdBase.IView {
    private SharedPreferences mPref;
    //使用SharedPreferences记录是否第一次打开app
    public static final String KEY_HISTORY_KEYWORD = "key_welcome_keyword";
    private HomeAdPresenter homeAdPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }

    public void init(){
        homeAdPresenter=new HomeAdPresenter(this);

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
                    homeAdPresenter.getHomeAd(getVersionCodes());

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
