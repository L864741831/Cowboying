package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MyCowsDetailListAdapter;
import com.ibeef.cowboying.base.MyCowsOrderBase;
import com.ibeef.cowboying.base.MyCowsOrderDeleteBean;
import com.ibeef.cowboying.bean.CreatOderResultBean;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListDetailBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.MyCowsOrderPresenter;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

public class MyCowsDetailActivity extends BaseActivity implements MyCowsOrderBase.IView {
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
    @Bind(R.id.store_img)
    ImageView storeImg;
    @Bind(R.id.store_name)
    TextView storeName;
    @Bind(R.id.tv_ranch_id)
    TextView tvRanchId;
    @Bind(R.id.money_id)
    TextView moneyId;
    @Bind(R.id.num_id)
    TextView numId;
    @Bind(R.id.rl_emeng)
    RelativeLayout rlEmeng;
    @Bind(R.id.tv_discount_coupon)
    TextView tvDiscountCoupon;
    @Bind(R.id.discount_type_id)
    TextView discountTypeId;
    @Bind(R.id.txt_expirence_day)
    TextView txtExpirenceDay;
    @Bind(R.id.rl_discount_coupon)
    RelativeLayout rlDiscountCoupon;
    @Bind(R.id.rl_expirence_day)
    RelativeLayout rlExpirenceDay;
    private String token;
    private MyCowsDetailListAdapter myCowsDetailListAdapter;
    private List<MyCowsOrderListDetailBean.BizDataBean.CattleListBean> beanList;
    private MyCowsOrderPresenter myCowsOrderPresenter;
    private String status;
    private String pastureName;
    private MyCowsOrderListDetailBean myCowsOrderListDetailBean;
    private String orderCode;
    private int LockMonths;
    private String unlockTime;
    private boolean isAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cows_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        isAd=getIntent().getBooleanExtra("isAd",false);
        token = Hawk.get(HawkKey.TOKEN);
        orderCode = getIntent().getStringExtra("orderId");
        info.setText("我的牛只");
        rvCowsList.setLayoutManager(new LinearLayoutManager(this));
        rvCowsList.setHasFixedSize(true);
        rvCowsList.setNestedScrollingEnabled(false);
        beanList = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        myCowsOrderPresenter = new MyCowsOrderPresenter(this);
        if (!TextUtils.isEmpty(token)) {
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization", token);
            reqData.put("version", getVersionCodes());
            myCowsOrderPresenter.geMyCowsOrderListDetail(reqData, orderCode);
        }
    }

    @OnClick({R.id.back_id, R.id.btn_delet_order, R.id.btn_see_order_progress, R.id.btn_sell_want, R.id.btn_cancle_order, R.id.btn_to_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                if(isAd){
                    startActivity(new Intent(MyCowsDetailActivity.this, MainActivity.class));
                }
                finish();
                break;
            case R.id.btn_delet_order:
                //删除订单
                showDeleteOrder();
                break;
            case R.id.btn_see_order_progress:
                //产看进度
                Intent intent1 = new Intent(this, MyCowsProgressDialog.class);
                intent1.putExtra("status", status);
                intent1.putExtra("LockMonths", LockMonths);
                intent1.putExtra("UnlockTime", unlockTime);
                startActivity(intent1);
                break;
            case R.id.btn_sell_want:
                //我想卖牛
                Calendar cal = Calendar.getInstance();
                int i = cal.get(Calendar.DAY_OF_WEEK);
                int hour = cal.get(Calendar.HOUR_OF_DAY);// 获取小时
                int minute = cal.get(Calendar.MINUTE);// 获取分钟
                int minuteOfDay = hour * 60 + minute;// 从0:00分开是到目前为止的分钟数
                final int start = 10 * 60;// 起始时间 10:00的分钟数
                final int end = 22 * 60;// 结束时间 22:00的分钟数
                if (i == 2) {
                    if (minuteOfDay >= start && minuteOfDay <= end) {
                        System.out.println("在外围内");
                        Intent intent = new Intent(this, SellCowsFirstActivity.class);
                        intent.putExtra("orderId", orderCode);
                        startActivity(intent);
                    } else {
                        showWantShellOrder();
                    }
                } else {
                    showWantShellOrder();
                }
                break;
            case R.id.btn_cancle_order:
                //取消订单
                Intent intent = new Intent(this, SureOrderBackDialog.class);
                intent.putExtra("orderCode", orderCode);
                startActivity(intent);
                break;
            case R.id.btn_to_pay:
                //去支付
                if (!TextUtils.isEmpty(token)) {
                    Map<String, String> reqData = new HashMap<>();
                    reqData.put("Authorization", token);
                    reqData.put("version", getVersionCodes());
                    myCowsOrderPresenter.getMyCowsToPay(reqData, orderCode);
                }
                break;
            default:
                break;
        }
    }

    public void showDeleteOrder() {
        final NormalDialog dialog = new NormalDialog(this);
        dialog.isTitleShow(false)
                .content("确定删除订单？")
                .titleTextSize(18)
                .contentGravity(Gravity.CENTER)
                .contentTextColor(Color.parseColor("#808080"))
                .dividerColor(Color.parseColor("#f3f3f3"))
                .btnTextSize(15.5f, 15.5f)
                .btnTextColor(Color.parseColor("#000000"), Color.parseColor("#2899ff"))
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        if (!TextUtils.isEmpty(token)) {
                            Map<String, String> reqData = new HashMap<>();
                            reqData.put("Authorization", token);
                            reqData.put("version", getVersionCodes());
                            myCowsOrderPresenter.getMyCowsOrderDelete(reqData, orderCode);
                        }
                        dialog.dismiss();
                    }
                });
    }

    public void showWantShellOrder() {
        final NormalDialog dialog = new NormalDialog(this);
        dialog.isTitleShow(true)
                .title("卖牛提示")
                .content("亲爱的牛主人，还未到交易日哦，我们的交易日为每周一的10:00-22:00，到时候可别忘了来哦~")
                .titleTextSize(18)
                .titleTextColor(Color.parseColor("#101010"))
                .titleLineColor(Color.parseColor("#B0957A"))
                .contentGravity(Gravity.CENTER)
                .contentTextColor(Color.parseColor("#808080"))
                .dividerColor(Color.parseColor("#B0957A"))
                .btnTextSize(15.5f, 15.5f)
                .btnTextColor(Color.parseColor("#000000"), Color.parseColor("#B0957A"))
                .show();
        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                });
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
        if ("000000".equals(myCowsOrderListDetailBean.getCode())) {
            this.myCowsOrderListDetailBean = myCowsOrderListDetailBean;
            status = myCowsOrderListDetailBean.getBizData().getStatus();
            pastureName = myCowsOrderListDetailBean.getBizData().getPastureName();
            LockMonths = myCowsOrderListDetailBean.getBizData().getLockMonths();
            unlockTime = myCowsOrderListDetailBean.getBizData().getUnlockTime();
            if (myCowsOrderListDetailBean.getBizData().getCattleList().size() > 0) {
                rlEmeng.setVisibility(View.GONE);
                rvCowsList.setVisibility(View.VISIBLE);
                beanList.clear();
                this.beanList.addAll(myCowsOrderListDetailBean.getBizData().getCattleList());
                myCowsDetailListAdapter = new MyCowsDetailListAdapter(beanList, status, myCowsOrderListDetailBean.getBizData().getSchemeType(),pastureName, this, R.layout.my_cows_chird_item);
                rvCowsList.setAdapter(myCowsDetailListAdapter);
                myCowsDetailListAdapter.setNewData(beanList);
            } else {
                RequestOptions options = new RequestOptions()
                        .skipMemoryCache(true)
                        .error(R.mipmap.jzsb)
                        //跳过内存缓存
                        ;
                Glide.with(this).load(Constant.imageDomain + myCowsOrderListDetailBean.getBizData().getPastureImage()).apply(options).into(storeImg);
                tvRanchId.setText(myCowsOrderListDetailBean.getBizData().getPastureName());
                moneyId.setText("安格斯牛");
                rlEmeng.setVisibility(View.VISIBLE);
                rvCowsList.setVisibility(View.GONE);
                numId.setText("×" + myCowsOrderListDetailBean.getBizData().getCattleCount());
            }

            if ("1".equals(status)) {
                //待付款(基础界面只显示待付款的几个条目，其他都隐藏掉了)
                storeName.setText("待付款");
                tvStatusId.setText("待付款");
                btnCancleOrder.setVisibility(View.VISIBLE);
                btnToPay.setVisibility(View.VISIBLE);
            } else if ("2".equals(status)) {
                btnSeeOrderProgress.setVisibility(View.VISIBLE);
                //已付款未分配（比待付款多了一个支付时间和支付时间）
                storeName.setText("待分配牛只");
                tvStatusId.setText("待分配牛只");
                rlPayTime.setVisibility(View.VISIBLE);
                rlPayWay.setVisibility(View.VISIBLE);
            } else if ("3".equals(status)) {
                tvStatusId.setText("已分配牛只");
                btnSeeOrderProgress.setVisibility(View.VISIBLE);
                btnSellWant.setVisibility(View.VISIBLE);
                //已分配（这里只有活期养牛这一种）
                rlPayTime.setVisibility(View.VISIBLE);
                rlPayWay.setVisibility(View.VISIBLE);
                rlAssignTime.setVisibility(View.VISIBLE);
            } else if ("4".equals(status)) {
                tvStatusId.setText("已分配牛只");
                btnSeeOrderProgress.setVisibility(View.VISIBLE);
                //已分配锁定期中（只有定期养牛才会有这个状态）
                rlPayTime.setVisibility(View.VISIBLE);
                rlPayWay.setVisibility(View.VISIBLE);
                rlAssignTime.setVisibility(View.VISIBLE);
                rlLockDay.setVisibility(View.VISIBLE);
                rlLockEndTime.setVisibility(View.VISIBLE);
                tvLockDay.setText(myCowsOrderListDetailBean.getBizData().getLockMonths() + "个月");
                tvLockEndTime.setText(myCowsOrderListDetailBean.getBizData().getUnlockTime());
            } else if ("5".equals(status)) {
                tvStatusId.setText("出售中");
                btnSeeOrderProgress.setVisibility(View.VISIBLE);
                //出售中（不分活期和定期。。定期到期后会自动转为活期的）
                rlPayTime.setVisibility(View.VISIBLE);
                rlPayWay.setVisibility(View.VISIBLE);
                rlAssignTime.setVisibility(View.VISIBLE);
                rlSellTime.setVisibility(View.VISIBLE);
                tvSellTime.setText(myCowsOrderListDetailBean.getBizData().getSellTime());
            } else if ("6".equals(status)) {
                tvStatusId.setText("交易完成");
                btnSeeOrderProgress.setVisibility(View.VISIBLE);
                btnDeletOrder.setVisibility(View.VISIBLE);
                //交易完成（比交易完成多一个出售成功时间）
                rlPayTime.setVisibility(View.VISIBLE);
                rlPayWay.setVisibility(View.VISIBLE);
                rlAssignTime.setVisibility(View.VISIBLE);
                rlSellTime.setVisibility(View.VISIBLE);
                tvSellTime.setText(myCowsOrderListDetailBean.getBizData().getSellTime());
                rlSellDoneTime.setVisibility(View.VISIBLE);
                tvSellDoneTime.setText(myCowsOrderListDetailBean.getBizData().getSellSuccessTime());
            } else if ("9".equals(status)) {
                tvStatusId.setText("交易关闭");
                btnDeletOrder.setVisibility(View.VISIBLE);
                storeName.setText("交易关闭");
                btnCancleOrder.setVisibility(View.GONE);
                btnToPay.setVisibility(View.GONE);
                //交易关闭
                rlTransactionEndTime.setVisibility(View.VISIBLE);
                tvTransactionEndTime.setText(myCowsOrderListDetailBean.getBizData().getCloseTime());
            }


//    订单状态（1:未付款；2：已付款未分配；3：已分配；4：已分配锁定期中；5：出售中；6:交易完成；9；交易关闭）
//    不用给领养类型，活期是3 定期只有4，不会为3
            tvOrderId.setText("订单编号：" + myCowsOrderListDetailBean.getBizData().getOrderCode());
            tvPeriodNumber.setText(myCowsOrderListDetailBean.getBizData().getSchemeCode());
            tvPrice.setText("￥" + String.valueOf(myCowsOrderListDetailBean.getBizData().getPrice()));
            tvCreateTime.setText(myCowsOrderListDetailBean.getBizData().getCreateTime());
            tvPayTime.setText(myCowsOrderListDetailBean.getBizData().getPayTime());
            tvAssignTime.setText(myCowsOrderListDetailBean.getBizData().getDistributeTime());
            if ("1".equals(myCowsOrderListDetailBean.getBizData().getSchemeType())) {
                tvMode.setText("活期养牛");
                discountTypeId.setText("优惠券：");
            } else  if ("2".equals(myCowsOrderListDetailBean.getBizData().getSchemeType())) {
                tvMode.setText("定期养牛");
                discountTypeId.setText("优惠券：");
            } else  if ("3".equals(myCowsOrderListDetailBean.getBizData().getSchemeType())){
                tvMode.setText("新手体验养牛");
                discountTypeId.setText("体验金额：");
                rlExpirenceDay.setVisibility(View.VISIBLE);
                txtExpirenceDay.setText(myCowsOrderListDetailBean.getBizData().getExperienceDays()+"天");
            }
            if ("1".equals(myCowsOrderListDetailBean.getBizData().getPayType())) {
                tvPayWay.setText("支付宝");
            } else if ("2".equals(myCowsOrderListDetailBean.getBizData().getPayType())) {
                tvPayWay.setText("微信");
            } else {
                tvPayWay.setText("钱包余额");
            }
            if (myCowsOrderListDetailBean.getBizData().getDiscountAmount()>0){
                rlDiscountCoupon.setVisibility(View.VISIBLE);
                if ("3".equals(myCowsOrderListDetailBean.getBizData().getSchemeType())){
                    tvDiscountCoupon.setText("￥"+myCowsOrderListDetailBean.getBizData().getDiscountAmount());
                }else {
                    tvDiscountCoupon.setText("-￥"+myCowsOrderListDetailBean.getBizData().getDiscountAmount());
                }

            }else {
                if ("3".equals(myCowsOrderListDetailBean.getBizData().getSchemeType())){
                    rlDiscountCoupon.setVisibility(View.VISIBLE);
                }else {
                    rlDiscountCoupon.setVisibility(View.GONE);
                }
            }
        } else {
            Toast.makeText(this, myCowsOrderListDetailBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getMyCowsOrderDelete(MyCowsOrderDeleteBean msg) {
        if ("000000".equals(msg.getCode())) {
            finish();
            Toast.makeText(this, "删除订单成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, msg.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getMyCowsOrderCancel(MyCowsOrderDeleteBean msg) {
        if ("000000".equals(msg.getCode())) {
            Toast.makeText(this, "取消订单成功", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, msg.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getMyCowsToPay(CreatOderResultBean creatOderResultBean) {
        if ("000000".equals(creatOderResultBean.getCode())) {
            Intent intent = new Intent(this, SureOderActivity.class);
            intent.putExtra("infos", creatOderResultBean);
            startActivity(intent);
            finish();
        } else {
            showToast(creatOderResultBean.getMessage());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(isAd){
            startActivity(new Intent(MyCowsDetailActivity.this, MainActivity.class));
        }
        finish();
    }
}
