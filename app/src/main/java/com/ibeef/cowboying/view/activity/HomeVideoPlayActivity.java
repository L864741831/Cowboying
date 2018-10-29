package com.ibeef.cowboying.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ibeef.cowboying.R;

import rxfamily.view.BaseActivity;

/**
 * 首页视频播放
 */
public class HomeVideoPlayActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_video_play);
        init();
    }
    private void init(){

    }
}
