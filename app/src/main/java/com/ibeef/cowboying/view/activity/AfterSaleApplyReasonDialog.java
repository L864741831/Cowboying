package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibeef.cowboying.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AfterSaleApplyReasonDialog extends AppCompatActivity {


    @Bind(R.id.rb_1)
    RadioButton rb1;
    @Bind(R.id.rb_2)
    RadioButton rb2;
    @Bind(R.id.rb_3)
    RadioButton rb3;
    @Bind(R.id.rb_4)
    RadioButton rb4;
    @Bind(R.id.rb_5)
    RadioButton rb5;
    @Bind(R.id.rb_6)
    RadioButton rb6;
    @Bind(R.id.rg)
    RadioGroup rg;
    @Bind(R.id.btn_cpmmit)
    TextView btnCpmmit;
    @Bind(R.id.rvs_id)
    RelativeLayout rvsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_sale_aooly_reaxon_dialog);
        ButterKnife.bind(this);
        init();
    }

    private void init() {


    }

    @OnClick(R.id.btn_cpmmit)
    public void onViewClicked() {
        finish();
    }
}