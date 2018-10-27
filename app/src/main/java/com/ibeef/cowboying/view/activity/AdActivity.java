package com.ibeef.cowboying.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.classic.common.MultipleStatusView;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.HomeAdBase;
import com.ibeef.cowboying.bean.HomeAdResultBean;
import com.ibeef.cowboying.presenter.HomeAdPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 广告页
 */
public class AdActivity extends BaseActivity implements HomeAdBase.IView {

    @Bind(R.id.bg_img)
    ImageView bgImg;
    @Bind(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;

    @Bind(R.id.go_main_activity)
    RelativeLayout goMainActivity;
    int timeCount = 0;
    boolean continueCount = true;
    private HomeAdPresenter homeAdPresenter;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @SuppressWarnings("unused")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (continueCount) {
                countNum();
                handler.sendMessageDelayed(handler.obtainMessage(-1),1000);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        ButterKnife.bind(this);
        handler.sendMessageDelayed(handler.obtainMessage(-1),1000);
        homeAdPresenter=new HomeAdPresenter(this);
        homeAdPresenter.getHomeAd(getVersionCode());
    }

    @OnClick({R.id.bg_img, R.id.go_main_activity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bg_img:
                continueCount = false;
                // url广告webview地址，广告title
                Intent intent=new Intent(AdActivity.this, AdWebviewActivity.class);
                intent.putExtra("url","https://www.baidu.com/");
                intent.putExtra("title","口袋牧场");
                startActivity(intent);
                finish();
                break;
            case R.id.go_main_activity:
                continueCount = false;
                startActivity(new Intent(AdActivity.this, MainActivity.class));
                finish();
                break;
            default:
                break;
        }
    }

    private int countNum() {//数秒
        timeCount++;
        if(timeCount==3){
            continueCount = false;
            //超过三秒进入下个页面
            startActivity(new Intent(AdActivity.this, MainActivity.class));
            finish();
        }
        return timeCount;
    }

    @Override
    public void showMsg(String msg) {
        multipleStatusView.showLoading();
    }

    @Override
    public void getHomeAd(HomeAdResultBean homeAdResultBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        multipleStatusView.showContent();
    }

    public String getVersionCode() {
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            String versionName = packageInfo.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    protected void onDestroy() {
        if(homeAdPresenter != null){
            homeAdPresenter.detachView();
        }
        super.onDestroy();
    }
}
