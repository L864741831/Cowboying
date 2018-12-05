package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.AfterSaleDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.bean.BaseBean;
import rxfamily.view.BaseActivity;

/**
 * 售后详情
 *
 * @author Administrator
 */
public class AfterSaleApplyActivity extends BaseActivity {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.rv_list)
    RecyclerView rvList;
    @Bind(R.id.tv_return_type)
    TextView tvReturnType;
    @Bind(R.id.btn_return_reason)
    RelativeLayout btnReturnReason;
    @Bind(R.id.tv_other)
    TextView tvOther;
    @Bind(R.id.tv_weizhi)
    TextView tvWeizhi;
    @Bind(R.id.tv_return_money)
    TextView tvReturnMoney;
    @Bind(R.id.tv_return_gray_info)
    TextView tvReturnGrayInfo;
    @Bind(R.id.tv_weizhi_2)
    TextView tvWeizhi2;
    @Bind(R.id.tv_return_info)
    EditText tvReturnInfo;
    @Bind(R.id.btn_commit)
    TextView btnCommit;
    private List<BaseBean> beanList;
    private AfterSaleDetailAdapter afterSaleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_sale_apply);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("售后详情");
        beanList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            BaseBean baseBean = new BaseBean();
            beanList.add(baseBean);
        }
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setHasFixedSize(true);
        rvList.setNestedScrollingEnabled(false);
        afterSaleAdapter = new AfterSaleDetailAdapter(beanList, this, R.layout.item_after_sale_detail);
        rvList.setAdapter(afterSaleAdapter);

    }

    @OnClick({R.id.back_id, R.id.btn_return_reason, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.btn_return_reason:
                Intent intent = new Intent(this, AfterSaleApplyReasonDialog.class);
                startActivityForResult(intent,666);
                break;
            case R.id.btn_commit:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


}
