package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
public class AfterSaleDetailActivity extends BaseActivity {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.tv_apply_top_status)
    TextView tvApplyTopStatus;
    @Bind(R.id.tv_apply_top_time)
    TextView tvApplyTopTime;
    @Bind(R.id.bt_change_apply)
    TextView btChangeApply;
    @Bind(R.id.bt_see_detail)
    TextView btSeeDetail;
    @Bind(R.id.ll_apply_ing)
    LinearLayout llApplyIng;
    @Bind(R.id.tv_weizhi_1)
    TextView tvWeizhi1;
    @Bind(R.id.rl_return_success_1)
    RelativeLayout rlReturnSuccess1;
    @Bind(R.id.rl_return_success_2)
    RelativeLayout rlReturnSuccess2;
    @Bind(R.id.rl_return_refuse)
    TextView rlReturnRefuse;
    @Bind(R.id.rv_list)
    RecyclerView rvList;
    @Bind(R.id.tv_period_number)
    TextView tvPeriodNumber;
    @Bind(R.id.tv_return_money)
    TextView tvReturnMoney;
    @Bind(R.id.tv_apply_id)
    TextView tvApplyId;
    @Bind(R.id.txt_apply_time)
    TextView txtApplyTime;
    @Bind(R.id.discount_type_id)
    TextView discountTypeId;
    @Bind(R.id.tv_return_id)
    TextView tvReturnId;
    @Bind(R.id.btn_logistics_info)
    TextView btnLogisticsInfo;
    @Bind(R.id.btn_apply_return)
    TextView btnApplyReturn;
    @Bind(R.id.ll_bottom_btn)
    LinearLayout llBottomBtn;
    private List<BaseBean> beanList;
    private AfterSaleDetailAdapter afterSaleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_sale_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("售后详情");
        beanList=new ArrayList<>();
        for (int i=0;i<3;i++){
            BaseBean baseBean=new BaseBean();
            beanList.add(baseBean);
        }
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setHasFixedSize(true);
        rvList.setNestedScrollingEnabled(false);
        afterSaleAdapter=new AfterSaleDetailAdapter(beanList,this,R.layout.item_after_sale_detail);
        rvList.setAdapter(afterSaleAdapter);

    }

    @OnClick({R.id.back_id, R.id.bt_change_apply, R.id.bt_see_detail, R.id.btn_logistics_info, R.id.btn_apply_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.bt_change_apply:
                //修改申请
                startActivity(AfterSaleApplyActivity.class);
                break;
            case R.id.bt_see_detail:
                //撤销申请
                break;
            case R.id.btn_logistics_info:
                //查看物流
                break;
            case R.id.btn_apply_return:
                //申请退款
                startActivity(AfterSaleApplyActivity.class);
                break;
            default:
                break;
        }
    }
}
