package com.ibeef.cowboying.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.HomeAdResultBean;
import com.ibeef.cowboying.config.Constant;

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
    @Bind(R.id.go_txt_show)
    TextView goTxtShow;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    int timeCount = 0,countTime=6;
    boolean continueCount = true;
    private HomeAdResultBean info;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @SuppressWarnings("unused")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (continueCount) {
                countNum();
                goTxtShow.setText("跳过("+countTime+"s)");
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
        info= (HomeAdResultBean) getIntent().getSerializableExtra("info");

        RequestOptions options = new RequestOptions()
                .error(R.mipmap.startup)
                //加载错误之后的错误图
                .skipMemoryCache(true)
                //跳过内存缓存
                ;

        Glide.with(this).load(Constant.imageDomain+info.getBizData().getImageUrl()).apply(options).into(bgImg);
    }

    @OnClick({R.id.bg_img, R.id.go_main_activity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bg_img:
                // url广告webview地址，广告title
                if(!TextUtils.isEmpty(info.getBizData().getLinkUrl())){
                    continueCount = false;
                    Intent intent=new Intent(AdActivity.this, AdWebviewActivity.class);
                    intent.putExtra("url",info.getBizData().getLinkUrl());
                    intent.putExtra("title","口袋牧场");
                    startActivity(intent);
                    finish();
                }else {
                    showToast("暂无广告详情~");
                }
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
        countTime--;
        if(timeCount==5){
            continueCount = false;
            //超过三秒进入下个页面
            startActivity(new Intent(AdActivity.this, MainActivity.class));
            finish();
        }
        return timeCount;
    }
}
