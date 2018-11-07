package com.ibeef.cowboying.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ibeef.cowboying.R;

import rxfamily.view.BaseActivity;

/**
 * 立即认领已实名认证的界面
 */
public class ClaimCertificationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_certification);
        init();
    }
    private void init(){

    }
}
