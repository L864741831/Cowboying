package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ibeef.cowboying.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 买牛方案列表
 */
public class BuyCowsPlanActivity extends BaseActivity {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.show_img_id)
    ImageView showImgId;
    @Bind(R.id.periods_txt_id)
    TextView periodsTxtId;
    @Bind(R.id.vip_level_txt_id)
    TextView vipLevelTxtId;
    @Bind(R.id.percent_txt_id)
    TextView percentTxtId;
    @Bind(R.id.percent_way_txt_id)
    TextView percentWayTxtId;
    @Bind(R.id.minlevle_txt_id)
    TextView minlevleTxtId;
    @Bind(R.id.minpercent_txt_id)
    TextView minpercentTxtId;
    @Bind(R.id.percent_zero_txt_id)
    TextView percentZeroTxtId;
    @Bind(R.id.seekbar_id)
    SeekBar seekbarId;
    @Bind(R.id.percent_hundred_txt_id)
    TextView percentHundredTxtId;
    @Bind(R.id.now_claim_btn_id)
    TextView nowClaimBtnId;
    @Bind(R.id.show_img_id2)
    ImageView showImgId2;
    @Bind(R.id.periods_txt_id1)
    TextView periodsTxtId1;
    @Bind(R.id.vip_level_txt_id1)
    TextView vipLevelTxtId1;
    @Bind(R.id.percent_txt_id1)
    TextView percentTxtId1;
    @Bind(R.id.percent_way_txt_id1)
    TextView percentWayTxtId1;
    @Bind(R.id.minlevle_txt_id1)
    TextView minlevleTxtId1;
    @Bind(R.id.minpercent_txt_id1)
    TextView minpercentTxtId1;
    @Bind(R.id.percent_zero_txt_id1)
    TextView percentZeroTxtId1;
    @Bind(R.id.seekbar_id1)
    SeekBar seekbarId1;
    @Bind(R.id.percent_hundred_txt_id1)
    TextView percentHundredTxtId1;
    @Bind(R.id.now_claim_btn_id1)
    TextView nowClaimBtnId1;
    @Bind(R.id.parsture_addr_id1)
    TextView parstureAddrId1;
    @Bind(R.id.parsture_addr_id)
    TextView parstureAddrId;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_cows_plan);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("买牛方案列表");
        actionNewQuestionTv.setText("往期记录");
        actionNewQuestionTv.setVisibility(View.VISIBLE);
        seekbarId.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    @OnClick({R.id.back_id, R.id.now_claim_btn_id, R.id.now_claim_btn_id1,R.id.action_new_question_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.now_claim_btn_id:
                startActivity(CowsClaimActivity.class);
                break;
            case R.id.now_claim_btn_id1:
                startActivity(CowsClaimActivity.class);
                break;
            case R.id.action_new_question_tv:

                break;
            default:
                break;
        }
    }
}
