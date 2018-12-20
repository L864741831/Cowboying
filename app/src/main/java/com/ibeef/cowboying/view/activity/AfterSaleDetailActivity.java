package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.AfterSaleoneDetailAdapter;
import com.ibeef.cowboying.base.MyOrderListBase;
import com.ibeef.cowboying.bean.MyAfterSaleDetailBean;
import com.ibeef.cowboying.bean.MyAfterSaleListBean;
import com.ibeef.cowboying.bean.MyOrderListBean;
import com.ibeef.cowboying.bean.MyOrderListCancelBean;
import com.ibeef.cowboying.bean.MyOrderListDetailBean;
import com.ibeef.cowboying.bean.ShowDeleveryResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.MyOrderListPresenter;
import com.ibeef.cowboying.utils.DateUtils;
import com.ibeef.cowboying.view.customview.CountDowntimeView;
import com.orhanobut.hawk.Hawk;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 售后详情
 *
 * @author Administrator
 */
public class AfterSaleDetailActivity extends BaseActivity implements MyOrderListBase.IView {

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
    @Bind(R.id.tv_return_money_num)
    TextView tvReturnMoneyNum;
    @Bind(R.id.tv_return_money_way)
    TextView tvReturnMoneyWay;
    @Bind(R.id.tv_return__way)
    TextView tv_return__way;
    @Bind(R.id.tv_cancel_time)
    TextView tv_cancel_time;
    @Bind(R.id.rl_cancel)
    LinearLayout llCancel;
    @Bind(R.id.time_show_id)
    CountDowntimeView time_show_id;
    @Bind(R.id.ll_return_time)
    LinearLayout ll_return_time;
    @Bind(R.id.tv_return_time)
    TextView tv_return_time;
    private List<MyAfterSaleDetailBean.BizDataBean.OrderProductResVosBean> beanList;
    private String token;
    private AfterSaleoneDetailAdapter afterSaleAdapter;
    private MyOrderListPresenter myOrderListPresenter;
    private String orderId;
    private MyAfterSaleDetailBean myAfterSaleDetailBean;
    private String status;
    private int id;
    private int orderIdnew;
    private String times;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_sale_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        orderId = getIntent().getStringExtra("orderId");
        info.setText("售后详情");
        token = Hawk.get(HawkKey.TOKEN);
        beanList = new ArrayList<>();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setHasFixedSize(true);
        rvList.setNestedScrollingEnabled(false);
        afterSaleAdapter = new AfterSaleoneDetailAdapter(beanList, this, R.layout.item_after_sale_detail);
        rvList.setAdapter(afterSaleAdapter);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        times = sdf.format( new Date());
    }

    @Override
    protected void onResume() {
        super.onResume();
        myOrderListPresenter = new MyOrderListPresenter(this);
        if (!TextUtils.isEmpty(token)) {
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization", token);
            reqData.put("version", getVersionCodes());
            myOrderListPresenter.getAfterSaleDetail(reqData, orderId);
        }
    }

    @OnClick({R.id.back_id, R.id.bt_change_apply, R.id.bt_see_detail, R.id.btn_logistics_info, R.id.btn_apply_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.bt_change_apply:
                //修改申请
                Intent intent3 = new Intent(this,AfterSaleApplyActivity.class);
                intent3.putExtra("id",id+"");
                intent3.putExtra("orderCode",orderIdnew+"");
                Log.i("htht", "orderId:::::发送 "+orderIdnew);
                startActivity(intent3);
                break;
            case R.id.bt_see_detail:
                //撤销申请
                Map<String, String> reqData = new HashMap<>();
                reqData.put("Authorization", token);
                reqData.put("version", getVersionCodes());
                myOrderListPresenter.getCancelApplyReturn(reqData, id+"");
                break;
            case R.id.btn_logistics_info:
                //查看物流
                break;
            case R.id.btn_apply_return:
                //申请退款
                Intent intent8 = new Intent(this, AfterSaleApplyActivity.class);
                intent8.putExtra("id",id+"");
                intent8.putExtra("orderCode", orderIdnew+"");
                Log.i("htht", "orderIdnew:::::发送 "+orderIdnew);
                startActivity(intent8);
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
    public void getMyOrderList(MyOrderListBean myOrderListBean) {

    }

    @Override
    public void getMyOrderListDetail(MyOrderListDetailBean MyOrderListDetailBean) {

    }

    @Override
    public void getMyOrderListDelete(MyOrderListCancelBean myOrderListCancelBean) {

    }

    @Override
    public void getMyOrderListCancel(MyOrderListCancelBean myOrderListCancelBean) {

    }

    @Override
    public void getMyOrderListOk(MyOrderListCancelBean myOrderListCancelBean) {

    }

    @Override
    public void getAfterSaleList(MyAfterSaleListBean myAfterSaleListBean) {

    }

    @Override
    public void getAfterSaleDetail(MyAfterSaleDetailBean myAfterSaleDetailBean) {

        if ("000000".equals(myAfterSaleDetailBean.getCode())) {
            this.myAfterSaleDetailBean = myAfterSaleDetailBean;
            beanList.clear();
            rvList.setAdapter(afterSaleAdapter);
            this.beanList.addAll(myAfterSaleDetailBean.getBizData().getOrderProductResVos());
            afterSaleAdapter.setNewData(beanList);
            status = myAfterSaleDetailBean.getBizData().getShopRefundOrderResVo().getStatus();
            id = myAfterSaleDetailBean.getBizData().getShopRefundOrderResVo().getId();
            orderIdnew = myAfterSaleDetailBean.getBizData().getShopRefundOrderResVo().getOrderId();
        } else {
            Toast.makeText(this, myAfterSaleDetailBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
//退款单状态（1：申请退款；2：退款完成；3：退款拒绝；4：退款取消）',
        if ("1".equals(status)) {
            time_show_id.setVisibility(View.VISIBLE);
            tvApplyTopStatus.setText("请等待商家处理");
            tvApplyTopTime.setVisibility(View.GONE);
            llApplyIng.setVisibility(View.VISIBLE);
            rlReturnSuccess1.setVisibility(View.GONE);
            rlReturnRefuse.setVisibility(View.GONE);
            rlReturnSuccess2.setVisibility(View.GONE);
            llBottomBtn.setVisibility(View.GONE);
        } else if ("2".equals(status)) {
            tvApplyTopStatus.setText("退款成功");
            time_show_id.setVisibility(View.GONE);
            tvApplyTopTime.setText(DateUtils.formatDate(myAfterSaleDetailBean.getBizData().getShopRefundOrderResVo().getRefundTime(), DateUtils.TYPE_01));
            rlReturnSuccess1.setVisibility(View.VISIBLE);
            llApplyIng.setVisibility(View.GONE);
            rlReturnRefuse.setVisibility(View.GONE);
            rlReturnSuccess2.setVisibility(View.VISIBLE);
            ll_return_time.setVisibility(View.VISIBLE);
            tv_return_time.setText(DateUtils.formatDate(myAfterSaleDetailBean.getBizData().getShopRefundOrderResVo().getUpdateTime(), DateUtils.TYPE_01));
            tvReturnMoneyNum.setText("￥"+myAfterSaleDetailBean.getBizData().getShopRefundOrderResVo().getAmount());
            tvReturnMoneyWay.setText("￥"+myAfterSaleDetailBean.getBizData().getShopRefundOrderResVo().getAmount());
            llBottomBtn.setVisibility(View.GONE);
        } else if ("3".equals(status)) {
            tvApplyTopStatus.setText("审核拒绝");
            time_show_id.setVisibility(View.GONE);
            tvApplyTopTime.setText(DateUtils.formatDate(myAfterSaleDetailBean.getBizData().getShopRefundOrderResVo().getUpdateTime(), DateUtils.TYPE_01));
            llApplyIng.setVisibility(View.GONE);
            rlReturnSuccess1.setVisibility(View.GONE);
            rlReturnRefuse.setVisibility(View.VISIBLE);
            rlReturnRefuse.setText("拒绝原因："+myAfterSaleDetailBean.getBizData().getShopRefundOrderResVo().getRefuseReason());
            rlReturnSuccess2.setVisibility(View.GONE);
            llBottomBtn.setVisibility(View.VISIBLE);
        } else if ("4".equals(status)) {
            tvApplyTopStatus.setText("已撤销退款申请");
            time_show_id.setVisibility(View.GONE);
            tvApplyTopTime.setText(DateUtils.formatDate(myAfterSaleDetailBean.getBizData().getShopRefundOrderResVo().getUpdateTime(), DateUtils.TYPE_01));
            rlReturnSuccess1.setVisibility(View.VISIBLE);
            llApplyIng.setVisibility(View.GONE);
            rlReturnRefuse.setVisibility(View.GONE);
            tvReturnMoneyNum.setText("￥"+myAfterSaleDetailBean.getBizData().getShopRefundOrderResVo().getAmount());
            tvReturnMoneyWay.setText("￥"+myAfterSaleDetailBean.getBizData().getShopRefundOrderResVo().getAmount());
            llCancel.setVisibility(View.VISIBLE);
            rlReturnSuccess2.setVisibility(View.VISIBLE);
            tv_cancel_time.setText(DateUtils.formatDate(myAfterSaleDetailBean.getBizData().getShopRefundOrderResVo().getUpdateTime(), DateUtils.TYPE_01));
            llBottomBtn.setVisibility(View.GONE);
        }
//'支付方式（1：支付宝；2：微信；3：银联；4：钱包；5：会员卡；6：会员卡+钱包；7：白条）'
        switch(myAfterSaleDetailBean.getBizData().getPayType()){
            case "1":
                tv_return__way.setText("退回支付宝");
                break;
            case "2":
                tv_return__way.setText("退回微信");
                break;
            case "3":
                tv_return__way.setText("退回钱包");
                break;
            case "4":
                tv_return__way.setText("退回银行卡");
                break;
            case "5":
                tv_return__way.setText("退回会员卡");
                break;
            case "6":
                tv_return__way.setText("退回会员卡+钱包");
                break;
            case "7":
                tv_return__way.setText("退回白条");
                break;
                default:
                    break;
        }
        int quantity=0;
        for (int i=0;i<myAfterSaleDetailBean.getBizData().getOrderProductResVos().size();i++){
            quantity=myAfterSaleDetailBean.getBizData().getOrderProductResVos().get(i).getQuantity()+quantity;
        }
        tvPeriodNumber.setText(myAfterSaleDetailBean.getBizData().getShopRefundOrderResVo().getReason());
        tvReturnMoney.setText("￥"+myAfterSaleDetailBean.getBizData().getShopRefundOrderResVo().getAmount());
        tvApplyId.setText(""+quantity);
        txtApplyTime.setText(DateUtils.formatDate(myAfterSaleDetailBean.getBizData().getShopRefundOrderResVo().getCreateTime(), DateUtils.TYPE_01));
        tvReturnId.setText(""+myAfterSaleDetailBean.getBizData().getShopRefundOrderResVo().getCode());

        long time= DateUtils.getReducenew(times,DateUtils.formatDate(myAfterSaleDetailBean.getBizData().getShopRefundOrderResVo().getEndTime(),DateUtils.TYPE_01));
        Log.i("htht", "CreateTime::::::: "+DateUtils.formatDate(myAfterSaleDetailBean.getBizData().getShopRefundOrderResVo().getEndTime(),DateUtils.TYPE_01));
        Log.i("htht", "time::::::::: "+time);
        long day = time / 86400000;                         //以天数为单位取整
        long hour = time % 86400000 / 3600000;               //以小时为单位取整
        long min = time % 86400000 % 3600000 / 60000;       //以分钟为单位取整
        long seconds = time % 86400000 % 3600000 % 60000 / 1000;   //以秒为单位取整
        //以秒为单位取整
        if(time>0){
            time_show_id.initTime(day,hour,min,seconds);
            time_show_id.start();
        }else {
            time_show_id.setVisibility(View.GONE);
        }

    }

    @Override
    public void getApplyReturn(MyOrderListCancelBean myOrderListCancelBean) {

    }

    @Override
    public void getCancelApplyReturn(MyOrderListCancelBean myOrderListCancelBean) {
        if ("000000".equals(myOrderListCancelBean.getCode())) {
            finish();
            Toast.makeText(this, "撤销申请成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, myOrderListCancelBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getEditApplyReturn(MyOrderListCancelBean myOrderListCancelBean) {

    }

    @Override
    public void showDelevery(ShowDeleveryResultBean showDeleveryResultBean) {

    }
}
