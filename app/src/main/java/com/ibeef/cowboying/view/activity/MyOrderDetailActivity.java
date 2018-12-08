package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.AfterSaleDetailAdapter;
import com.ibeef.cowboying.base.MyOrderListBase;
import com.ibeef.cowboying.bean.MyOrderListBean;
import com.ibeef.cowboying.bean.MyOrderListCancelBean;
import com.ibeef.cowboying.bean.MyOrderListDetailBean;
import com.ibeef.cowboying.bean.ShowDeleveryResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.MyOrderListPresenter;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class MyOrderDetailActivity extends BaseActivity implements MyOrderListBase.IView{

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.tv_countdown)
    TextView tvCountdown;
    @Bind(R.id.ll_countdown)
    LinearLayout llCountdown;
    @Bind(R.id.tv_order_status)
    TextView tvOrderStatus;
    @Bind(R.id.iv_icon)
    ImageView ivIcon;
    @Bind(R.id.tv_code)
    TextView tvCode;
    @Bind(R.id.tv_shop_name)
    TextView tvShopName;
    @Bind(R.id.tv_shop_address)
    TextView tvShopAddress;
    @Bind(R.id.ll_pick_up)
    LinearLayout llPickUp;
    @Bind(R.id.tv_wuliu_ing)
    TextView tvWuliuIng;
    @Bind(R.id.tv_wuliu_ing_time)
    TextView tvWuliuIngTime;
    @Bind(R.id.ll_wuliu)
    LinearLayout llWuliu;
    @Bind(R.id.tv_address_name)
    TextView tvAddressName;
    @Bind(R.id.tv_address_mobile)
    TextView tvAddressMobile;
    @Bind(R.id.tv_address_detail)
    TextView tvAddressDetail;
    @Bind(R.id.rl_address_locast)
    RelativeLayout rlAddressLocast;
    @Bind(R.id.rv_list)
    RecyclerView rvList;
    @Bind(R.id.tv_peisong_type)
    TextView tvPeisongType;
    @Bind(R.id.tv_discount_coupon)
    TextView tvDiscountCoupon;
    @Bind(R.id.rl_discount_coupon)
    RelativeLayout rlDiscountCoupon;
    @Bind(R.id.tv_yunfei)
    TextView tvYunfei;
    @Bind(R.id.rl_yunfei)
    RelativeLayout rlYunfei;
    @Bind(R.id.tv_num_money)
    TextView tvNumMoney;
    @Bind(R.id.tv_shifu_money)
    TextView tvShifuMoney;
    @Bind(R.id.tv_order_id)
    TextView tvOrderId;
    @Bind(R.id.tv_order_create_time)
    TextView tvOrderCreateTime;
    @Bind(R.id.tv_order_pay_time)
    TextView tvOrderPayTime;
    @Bind(R.id.tv_order_fahuo_time)
    TextView tvOrderFahuoTime;
    @Bind(R.id.btn_order_delete)
    TextView btnOrderDelete;
    @Bind(R.id.btn_order_cancel)
    TextView btnOrderCancel;
    @Bind(R.id.btn_order_pay)
    TextView btnOrderPay;
    @Bind(R.id.btn_order_apply_back)
    TextView btnOrderApplyBack;
    @Bind(R.id.btn_order_see_wuliu)
    TextView btnOrderSeeWuliu;
    @Bind(R.id.btn_order_ok)
    TextView btnOrderOk;
    @Bind(R.id.btn_order_detail)
    TextView btnOrderDetail;
    @Bind(R.id.ll_bottom_btn)
    LinearLayout llBottomBtn;
    private List<BaseBean> beanList;
    private AfterSaleDetailAdapter afterSaleAdapter;
    private String orderId;
    private MyOrderListPresenter myOrderListPresenter;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        orderId = getIntent().getStringExtra("orderId");
        token = Hawk.get(HawkKey.TOKEN);
        info.setText("订单详情");
        beanList = new ArrayList<>();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setHasFixedSize(true);
        rvList.setNestedScrollingEnabled(false);
        afterSaleAdapter = new AfterSaleDetailAdapter(beanList, this, R.layout.item_after_sale_detail);
        rvList.setAdapter(afterSaleAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        myOrderListPresenter = new MyOrderListPresenter(this);
        if (!TextUtils.isEmpty(token)) {
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization", token);
            reqData.put("version", getVersionCodes());
            myOrderListPresenter.getMyOrderListDetail(reqData, orderId);
        }
    }


    @OnClick({R.id.back_id, R.id.btn_order_delete, R.id.btn_order_cancel, R.id.btn_order_pay, R.id.btn_order_apply_back, R.id.btn_order_see_wuliu, R.id.btn_order_ok, R.id.btn_order_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.btn_order_delete:
                //删除订单
                break;
            case R.id.btn_order_cancel:
                //取消订单
                break;
            case R.id.btn_order_pay:
                //去付款
                break;
            case R.id.btn_order_apply_back:
                //申请退款
                break;
            case R.id.btn_order_see_wuliu:
                //查看物流
                break;
            case R.id.btn_order_ok:
                //确认收货
                break;
            case R.id.btn_order_detail:
                //退款详情
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
    public void showDelevery(ShowDeleveryResultBean showDeleveryResultBean) {

    }
}
