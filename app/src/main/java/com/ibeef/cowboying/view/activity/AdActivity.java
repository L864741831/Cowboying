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
import com.ibeef.cowboying.base.HomeAdBase;
import com.ibeef.cowboying.bean.HomeAdResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.presenter.HomeAdPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;

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
    @Bind(R.id.go_main_activity)
    RelativeLayout goMainActivity;
    @Bind(R.id.go_txt_show)
    TextView goTxtShow;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    int timeCount = 0,countTime=6;
    boolean continueCount = true;
    boolean isShow = false;
    private HomeAdPresenter homeAdPresenter;
    private HomeAdResultBean homeAdResultBean;
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
        homeAdPresenter=new HomeAdPresenter(this);
        homeAdPresenter.getHomeAd(getVersionCodes());
    }

    @OnClick({R.id.bg_img, R.id.go_main_activity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bg_img:
                continueCount = false;
                // url广告webview地址，广告title
                if(isShow){
                    Intent intent=new Intent(AdActivity.this, AdWebviewActivity.class);
                    intent.putExtra("url",homeAdResultBean.getBizData().getLinkUrl());
                    intent.putExtra("title","口袋牧场");
                    startActivity(intent);
                    finish();
                }else {
                    showMsg("暂无广告详情~");
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

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getHomeAd(HomeAdResultBean homeAdResultBean) {
        if("000000".equals(homeAdResultBean.getCode())){
            if(!SDCardUtil.isNullOrEmpty(homeAdResultBean.getBizData())){
                RequestOptions options = new RequestOptions()
                        .error(R.mipmap.startup)
                        //加载错误之后的错误图
                        .skipMemoryCache(true)
                        //跳过内存缓存
                        ;
                if(!TextUtils.isEmpty(homeAdResultBean.getBizData().getLinkUrl())){
                    isShow=true;
                }
                this.homeAdResultBean=homeAdResultBean;
                Glide.with(this).load(Constant.imageDomain+homeAdResultBean.getBizData().getImageUrl()).apply(options).into(bgImg);
            }else {
                continueCount = false;
                startActivity(new Intent(AdActivity.this, MainActivity.class));
                finish();
            }

        }else {
            showToast(homeAdResultBean.getMessage());
        }

    }

    @Override
    public void showLoading(){
        loadingLayout.setVisibility(View.VISIBLE);
        bgImg.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        bgImg.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        if(homeAdPresenter != null){
            homeAdPresenter.detachView();
        }
        super.onDestroy();
    }
}
