package com.ranhan.cowboying.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ranhan.cowboying.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 广告页
 */
public class AdActivity extends BaseActivity {

    @Bind(R.id.bg_img)
    ImageView bgImg;

    @Bind(R.id.go_main_activity)
    RelativeLayout goMainActivity;
    int timeCount = 0;
    boolean continueCount = true;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @SuppressWarnings("unused")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            countNum();
            if (continueCount) {
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
    // TODO: 2018/10/13  网络请求没有数据，跳入下个页面
}
