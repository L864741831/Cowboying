package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibeef.cowboying.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 牛只详情
 */
public class CowsDesInfoActivity extends BaseActivity {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.cows_identify_id)
    TextView cowsIdentifyId;
    @Bind(R.id.cows_mouth_id)
    TextView cowsMouthId;
    @Bind(R.id.cows_weight_id)
    TextView cowsWeightId;
    @Bind(R.id.cows_img_id)
    ImageView cowsImgId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cows_des_info);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("牛只查询");
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
    }
}
