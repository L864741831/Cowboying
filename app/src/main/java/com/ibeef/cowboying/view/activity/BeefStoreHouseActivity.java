package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibeef.cowboying.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 牛肉仓库
 */
public class BeefStoreHouseActivity extends BaseActivity {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.get_goods_record)
    ImageView getGoodsRecord;
    @Bind(R.id.beef_money_id)
    TextView beefMoneyId;
    @Bind(R.id.question_show_id)
    TextView questionShowId;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    @Bind(R.id.ry_id)
    RecyclerView ryId;
    @Bind(R.id.limit_weight_delevery_id)
    TextView limitWeightDeleveryId;
    @Bind(R.id.order_weight_id)
    TextView orderWeightId;
    @Bind(R.id.get_goods_btn)
    TextView getGoodsBtn;
    @Bind(R.id.rv_bottom_id)
    RelativeLayout rvBottomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beef_store_house);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

    }

    @OnClick({R.id.back_id, R.id.get_goods_record, R.id.question_show_id, R.id.get_goods_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.get_goods_record:
                startActivity(GetGoodsRecordActivity.class);
                break;
            case R.id.question_show_id:
                break;
            case R.id.get_goods_btn:
                break;
            default:
                break;
        }
    }
}
