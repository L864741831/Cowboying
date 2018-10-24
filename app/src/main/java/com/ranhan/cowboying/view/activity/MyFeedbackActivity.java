package com.ranhan.cowboying.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ranhan.cowboying.R;

import rxfamily.view.BaseActivity;

/**
 * 我的反馈界面
 */
public class MyFeedbackActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_feedback);
    }
}
