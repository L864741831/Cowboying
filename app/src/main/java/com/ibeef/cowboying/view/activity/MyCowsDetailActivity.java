package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MyCowsDetailListAdapter;
import com.ibeef.cowboying.base.MyCowsOrderBase;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListDetailBean;
import com.ibeef.cowboying.config.HawkKey;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.bean.BaseBean;
import rxfamily.view.BaseActivity;

public class MyCowsDetailActivity extends BaseActivity implements MyCowsOrderBase.IView{
    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.tv_order_id)
    TextView tvOrderId;
    @Bind(R.id.tv_status_id)
    TextView tvStatusId;
    @Bind(R.id.rv_cows_list)
    RecyclerView rvCowsList;
    @Bind(R.id.tv_period_number)
    TextView tvPeriodNumber;
    @Bind(R.id.rl_period_number)
    RelativeLayout rlPeriodNumber;
    @Bind(R.id.tv_mode)
    TextView tvMode;
    @Bind(R.id.rl_mode)
    RelativeLayout rlMode;
    @Bind(R.id.tv_lock_day)
    TextView tvLockDay;
    @Bind(R.id.rl_lock_day)
    RelativeLayout rlLockDay;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.rl_price)
    RelativeLayout rlPrice;
    @Bind(R.id.tv_pay_way)
    TextView tvPayWay;
    @Bind(R.id.rl_pay_way)
    RelativeLayout rlPayWay;
    @Bind(R.id.tv_create_time)
    TextView tvCreateTime;
    @Bind(R.id.rl_create_time)
    RelativeLayout rlCreateTime;
    @Bind(R.id.tv_pay_time)
    TextView tvPayTime;
    @Bind(R.id.rl_pay_time)
    RelativeLayout rlPayTime;
    @Bind(R.id.tv_assign_time)
    TextView tvAssignTime;
    @Bind(R.id.rl_assign_time)
    RelativeLayout rlAssignTime;
    @Bind(R.id.tv_lock_end_time)
    TextView tvLockEndTime;
    @Bind(R.id.rl_lock_end_time)
    RelativeLayout rlLockEndTime;
    @Bind(R.id.tv_transaction_end_time)
    TextView tvTransactionEndTime;
    @Bind(R.id.rl_transaction_end_time)
    RelativeLayout rlTransactionEndTime;
    @Bind(R.id.btn_delet_order)
    TextView btnDeletOrder;
    @Bind(R.id.btn_see_order_progress)
    TextView btnSeeOrderProgress;
    @Bind(R.id.btn_sell_want)
    TextView btnSellWant;
    @Bind(R.id.btn_cancle_order)
    TextView btnCancleOrder;
    @Bind(R.id.btn_to_pay)
    TextView btnToPay;
    @Bind(R.id.tv_sell_time)
    TextView tvSellTime;
    @Bind(R.id.rl_sell_time)
    RelativeLayout rlSellTime;
    @Bind(R.id.tv_sell_done_time)
    TextView tvSellDoneTime;
    @Bind(R.id.rl_sell_done_time)
    RelativeLayout rlSellDoneTime;
    private String token;
    private MyCowsDetailListAdapter myCowsDetailListAdapter;
    private List<BaseBean> beanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cows_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        token = Hawk.get(HawkKey.TOKEN);
        info.setText("我的牛只");
        rvCowsList.setLayoutManager(new LinearLayoutManager(this));
        beanList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            BaseBean baseBean = new BaseBean();
            beanList.add(baseBean);
        }
        myCowsDetailListAdapter = new MyCowsDetailListAdapter(beanList, this, R.layout.my_cows_chird_item);
        rvCowsList.setAdapter(myCowsDetailListAdapter);
    }

    @OnClick({R.id.back_id, R.id.btn_delet_order, R.id.btn_see_order_progress, R.id.btn_sell_want, R.id.btn_cancle_order, R.id.btn_to_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.btn_delet_order:
                //删除订单
                break;
            case R.id.btn_see_order_progress:
                //产看进度
                break;
            case R.id.btn_sell_want:
                //我想卖牛
                break;
            case R.id.btn_cancle_order:
                //取消订单
                break;
            case R.id.btn_to_pay:
                //去支付
                break;
            default:
                break;
        }
    }


    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void geMyCowsOrderList(MyCowsOrderListBean myCowsOrderListBean) {

    }

    @Override
    public void geMyCowsOrderListDetail(MyCowsOrderListDetailBean myCowsOrderListDetailBean) {
//    订单状态（1:未付款；2：已付款未分配；3：已分配；4：已分配锁定期中；5：出售中；6:交易完成；9；交易关闭）
//    不用给领养类型，活期是3 定期只有4，不会为3
            //前几个为共同都有的
            tvPeriodNumber.setText("买牛期数");
            tvMode.setText("养牛的模式");
            tvPrice.setText("牛的单价");
            tvCreateTime.setText("单子创建的时间");
            if ("1".equals("1")) {
                //待付款(基础界面只显示待付款的几个条目，其他都隐藏掉了)
                btnCancleOrder.setVisibility(View.VISIBLE);
                btnToPay.setVisibility(View.VISIBLE);
            } else if ("2".equals("2")) {
                btnSeeOrderProgress.setVisibility(View.VISIBLE);
                //已付款未分配（比待付款多了一个支付时间和支付时间）
                rlPayTime.setVisibility(View.VISIBLE);
                rlPayWay.setVisibility(View.VISIBLE);
                tvPayTime.setText("支付的时间");
                tvPayWay.setText("支付方式");
            } else if ("3".equals("3")) {
                btnSeeOrderProgress.setVisibility(View.VISIBLE);
                btnSellWant.setVisibility(View.VISIBLE);
                //已分配（这里只有活期养牛这一种）
                rlPayTime.setVisibility(View.VISIBLE);
                rlPayWay.setVisibility(View.VISIBLE);
                rlAssignTime.setVisibility(View.VISIBLE);
                tvPayTime.setText("支付的时间");
                tvPayWay.setText("支付方式");
                tvAssignTime.setText("牛只分配时间");
            } else if ("4".equals("4")) {
                btnSeeOrderProgress.setVisibility(View.VISIBLE);
                //已分配锁定期中（只有定期养牛才会有这个状态）
                rlPayTime.setVisibility(View.VISIBLE);
                rlPayWay.setVisibility(View.VISIBLE);
                rlAssignTime.setVisibility(View.VISIBLE);
                tvPayTime.setText("支付的时间");
                tvPayWay.setText("支付方式");
                tvAssignTime.setText("牛只分配时间");
                rlLockDay.setVisibility(View.VISIBLE);
                rlLockEndTime.setVisibility(View.VISIBLE);
                tvLockDay.setText("锁定期天数");
                tvLockEndTime.setText("锁定期结束时间");
            } else if ("5".equals("5")) {
                btnSeeOrderProgress.setVisibility(View.VISIBLE);
                //出售中（不分活期和定期。。定期到期后会自动转为活期的）
                rlPayTime.setVisibility(View.VISIBLE);
                rlPayWay.setVisibility(View.VISIBLE);
                rlAssignTime.setVisibility(View.VISIBLE);
                tvPayTime.setText("支付的时间");
                tvPayWay.setText("支付方式");
                tvAssignTime.setText("牛只分配时间");
                rlSellTime.setVisibility(View.VISIBLE);
                tvSellTime.setText("牛只出售时间");
            } else if ("6".equals("6")) {
                btnSeeOrderProgress.setVisibility(View.VISIBLE);
                btnDeletOrder.setVisibility(View.VISIBLE);
                //交易完成（比交易完成多一个出售成功时间）
                rlPayTime.setVisibility(View.VISIBLE);
                rlPayWay.setVisibility(View.VISIBLE);
                rlAssignTime.setVisibility(View.VISIBLE);
                tvPayTime.setText("支付的时间");
                tvPayWay.setText("支付方式");
                tvAssignTime.setText("牛只分配时间");
                rlSellTime.setVisibility(View.VISIBLE);
                tvSellTime.setText("牛只出售时间");
                rlSellDoneTime.setVisibility(View.VISIBLE);
                tvSellDoneTime.setText("成功售出时间");
            }else if ("9".equals("9")) {
                btnDeletOrder.setVisibility(View.VISIBLE);
                //交易关闭
                rlTransactionEndTime.setVisibility(View.VISIBLE);
                tvTransactionEndTime.setText("交易关闭时间");
            }
    }
}
