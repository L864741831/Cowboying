package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.google.zxing.BarcodeFormat;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.AfterSaleDetailAdapter;
import com.ibeef.cowboying.base.MyOrderListBase;
import com.ibeef.cowboying.bean.MyAfterSaleDetailBean;
import com.ibeef.cowboying.bean.MyAfterSaleListBean;
import com.ibeef.cowboying.bean.MyOrderListBean;
import com.ibeef.cowboying.bean.MyOrderListCancelBean;
import com.ibeef.cowboying.bean.MyOrderListDetailBean;
import com.ibeef.cowboying.bean.ShowDeleveryResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.MyOrderListPresenter;
import com.ibeef.cowboying.utils.DateUtils;
import com.ibeef.cowboying.view.customview.CountDownView;
import com.king.zxing.util.CodeUtils;
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
public class MyOrderDetailActivity extends BaseActivity implements MyOrderListBase.IView {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
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
    @Bind(R.id.tv_order_shouhuo_time)
    TextView tvOrderShouhuoTime;
    @Bind(R.id.tv_order_tuikuan_time)
    TextView tvOrderTuikuanTime;
    @Bind(R.id.tv_order_guanbi_time)
    TextView tvOrderGuanbiTime;
    @Bind(R.id.v_line)
    View vLine;
    @Bind(R.id.time_show_id)
    CountDownView time_show_id;
    @Bind(R.id.tv_tihuo)
    TextView tvTihuo;
    private List<MyOrderListDetailBean.BizDataBean.ShopOrderProductResVosBean> beanList;
    private AfterSaleDetailAdapter afterSaleAdapter;
    private String orderId;
    private MyOrderListPresenter myOrderListPresenter;
    private String token;
    private MyOrderListDetailBean myOrderListDetailBean;
    private String refundId;
    private boolean from;
    private long createTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        from=getIntent().getBooleanExtra("from",false);

        orderId = getIntent().getStringExtra("orderId");
        token = Hawk.get(HawkKey.TOKEN);
        info.setText("订单详情");
        beanList = new ArrayList<>();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setHasFixedSize(true);
        rvList.setNestedScrollingEnabled(false);
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


    @OnClick({R.id.back_id, R.id.btn_order_delete, R.id.btn_order_cancel, R.id.btn_order_pay, R.id.btn_order_apply_back,
            R.id.btn_order_see_wuliu, R.id.btn_order_ok, R.id.btn_order_detail,R.id.tv_code,R.id.ll_wuliu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                if(from){
                    Intent intent1=new Intent(MyOrderDetailActivity.this,MyOrderActivity.class);
                    intent1.putExtra("from",true);
                    startActivity(intent1);
                }else {
                    finish();
                }
                break;
            case R.id.btn_order_delete:
                //删除订单
                showDeleteOrder();
                break;
            case R.id.btn_order_cancel:
                //取消订单
                Intent intent2 = new Intent(this, MyorderListCancelDialog.class);
                intent2.putExtra("orderCode", orderId);
                startActivity(intent2);
                break;
            case R.id.btn_order_pay:
                //去付款
                Intent intent5 = new Intent(this, StorePayTypeActivity.class);
                int i = Integer.valueOf(orderId).intValue();
                intent5.putExtra("orderId", i);
                intent5.putExtra("createTime", createTime);
                Log.i("htht", "去付款createTime::::: "+createTime);
                startActivity(intent5);
                break;
            case R.id.btn_order_apply_back:
                //申请退款
                Intent intent3 = new Intent(this, AfterSaleApplyActivity.class);
                intent3.putExtra("orderCode", orderId);
                startActivity(intent3);
                break;
            case R.id.btn_order_see_wuliu:
                //查看物流
                Intent intent = new Intent(this, ShowOrderDeleveryActivity.class);
                intent.putExtra("orderId", orderId);
                startActivity(intent);
                break;
            case R.id.btn_order_ok:
                //确认收货
                Map<String, String> reqData = new HashMap<>();
                reqData.put("Authorization", token);
                reqData.put("version", getVersionCodes());
                myOrderListPresenter.getMyOrderListOk(reqData, orderId);
                break;
            case R.id.btn_order_detail:
                //退款详情
                Intent intent6 = new Intent(this, AfterSaleDetailActivity.class);
                intent6.putExtra("orderId", refundId);
                startActivity(intent6);
                break;
            case R.id.tv_code:
                tvCode.setText(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveCode());
                break;
            case R.id.ll_wuliu:
                Intent intent8 = new Intent(this, ShowOrderDeleveryActivity.class);
                intent8.putExtra("orderId", orderId);
                startActivity(intent8);
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
                        if (!TextUtils.isEmpty(token)) {
                            Map<String, String> reqData = new HashMap<>();
                            reqData.put("Authorization", token);
                            reqData.put("version", getVersionCodes());
                            myOrderListPresenter.getMyOrderListDelete(reqData, orderId);
                        }
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
    public void getMyOrderList(MyOrderListBean myOrderListBean) {

    }

    @Override
    public void getMyOrderListDetail(final MyOrderListDetailBean myOrderListDetailBean) {
        if ("000000".equals(myOrderListDetailBean.getCode())) {
            this.myOrderListDetailBean = myOrderListDetailBean;
            String status = myOrderListDetailBean.getBizData().getShopOrderResVo().getStatus();
            beanList.clear();
            afterSaleAdapter = new AfterSaleDetailAdapter(beanList, myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveType(), this, R.layout.item_after_sale_detail);
            rvList.setAdapter(afterSaleAdapter);
            this.beanList.addAll(myOrderListDetailBean.getBizData().getShopOrderProductResVos());
            afterSaleAdapter.setNewData(beanList);
            refundId = myOrderListDetailBean.getBizData().getRefundId();
            createTime = myOrderListDetailBean.getBizData().getShopOrderResVo().getCreateTime();
            afterSaleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Constant.PRODUCR_ID=myOrderListDetailBean.getBizData().getShopOrderProductResVos().get(position).getProductId();
                    Intent intent1=new Intent(MyOrderDetailActivity.this,MainActivity.class);
                    intent1.putExtra("index",1);
                    startActivity(intent1);
                }
            });
//  订单状态（0：未支付；1：已支付；2：已发货；3：确认收货；4：退款中；5：已退款；6：已取消；）'
            if ("0".equals(status)) {
                //待付款
                llCountdown.setVisibility(View.VISIBLE);
                btnOrderCancel.setVisibility(View.VISIBLE);
                btnOrderPay.setVisibility(View.VISIBLE);
                tvOrderPayTime.setVisibility(View.GONE);
                tvOrderFahuoTime.setVisibility(View.GONE);
                tvOrderShouhuoTime.setVisibility(View.GONE);
                vLine.setVisibility(View.GONE);
                if ("1".equals(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveType())) {
                    //快递
                    rlAddressLocast.setVisibility(View.VISIBLE);
                } else if ("2".equals(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveType())) {
                    //门店自取
                    llPickUp.setVisibility(View.VISIBLE);
                    tvTihuo.setVisibility(View.GONE);
                    ivIcon.setVisibility(View.GONE);
                    tvCode.setVisibility(View.GONE);
                    rlAddressLocast.setVisibility(View.GONE);
                    rlYunfei.setVisibility(View.GONE);
                }
            } else if ("1".equals(status)) {
                //已支付{包含待发货和待取货}
                btnOrderApplyBack.setVisibility(View.VISIBLE);
                btnOrderDetail.setVisibility(View.GONE);
                tvOrderFahuoTime.setVisibility(View.GONE);
                tvOrderShouhuoTime.setVisibility(View.GONE);
                if ("1".equals(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveType())) {
                    //快递
                    rlAddressLocast.setVisibility(View.VISIBLE);
                    tvOrderStatus.setVisibility(View.VISIBLE);
                    tvOrderStatus.setText("订单状态：待发货");
                } else if ("2".equals(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveType())) {
                    //门店自取
                    llPickUp.setVisibility(View.VISIBLE);
                    tvOrderStatus.setVisibility(View.VISIBLE);
                    tvOrderStatus.setText("订单状态：待取货");
                    rlAddressLocast.setVisibility(View.GONE);
                    rlYunfei.setVisibility(View.GONE);
                }
            } else if ("2".equals(status)) {
                //待收货
                llWuliu.setVisibility(View.VISIBLE);
                tvOrderStatus.setVisibility(View.VISIBLE);
                btnOrderSeeWuliu.setVisibility(View.VISIBLE);
                btnOrderOk.setVisibility(View.VISIBLE);
                tvOrderShouhuoTime.setVisibility(View.GONE);
                tvOrderStatus.setText("订单状态：待收货");
                if ("1".equals(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveType())) {
                    //快递
                    rlAddressLocast.setVisibility(View.VISIBLE);
                } else if ("2".equals(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveType())) {
                    //门店自取
                    llPickUp.setVisibility(View.VISIBLE);
                    tvTihuo.setVisibility(View.GONE);
                    ivIcon.setVisibility(View.GONE);
                    tvCode.setVisibility(View.GONE);
                    rlAddressLocast.setVisibility(View.GONE);
                    rlYunfei.setVisibility(View.GONE);
                }
            } else if ("3".equals(status)) {
                //交易成功（包含线下门店和快递）
                llWuliu.setVisibility(View.VISIBLE);
                tvOrderStatus.setVisibility(View.VISIBLE);
                btnOrderDelete.setVisibility(View.VISIBLE);
                tvOrderStatus.setText("订单状态：交易成功");
                if ("1".equals(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveType())) {
                    //快递
                    rlAddressLocast.setVisibility(View.VISIBLE);
                    btnOrderSeeWuliu.setVisibility(View.VISIBLE);
                } else if ("2".equals(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveType())) {
                    //门店自取
                    llPickUp.setVisibility(View.VISIBLE);
                    tvTihuo.setVisibility(View.GONE);
                    ivIcon.setVisibility(View.GONE);
                    tvCode.setVisibility(View.GONE);
                    rlAddressLocast.setVisibility(View.GONE);
                    rlYunfei.setVisibility(View.GONE);
                }
            } else if ("4".equals(status)) {
                //退款中
                tvOrderStatus.setVisibility(View.VISIBLE);
                btnOrderDetail.setVisibility(View.VISIBLE);
                tvOrderFahuoTime.setVisibility(View.GONE);
                tvOrderShouhuoTime.setVisibility(View.GONE);
                tvOrderStatus.setText("订单状态：退款中");
                btnOrderApplyBack.setVisibility(View.GONE);
                if ("1".equals(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveType())) {
                    //快递
                    rlAddressLocast.setVisibility(View.VISIBLE);
                } else if ("2".equals(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveType())) {
                    //门店自取
                    llPickUp.setVisibility(View.VISIBLE);
                    tvTihuo.setVisibility(View.GONE);
                    ivIcon.setVisibility(View.GONE);
                    tvCode.setVisibility(View.GONE);
                    rlAddressLocast.setVisibility(View.GONE);
                    rlYunfei.setVisibility(View.GONE);
                }
            } else if ("5".equals(status)) {
                //已退款
                tvOrderStatus.setVisibility(View.VISIBLE);
                btnOrderDetail.setVisibility(View.VISIBLE);
                tvOrderFahuoTime.setVisibility(View.GONE);
                tvOrderShouhuoTime.setVisibility(View.GONE);
                tvOrderTuikuanTime.setVisibility(View.VISIBLE);
                tvOrderStatus.setText("订单状态：退款成功");
                if ("1".equals(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveType())) {
                    //快递
                    rlAddressLocast.setVisibility(View.VISIBLE);
                } else if ("2".equals(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveType())) {
                    //门店自取
                    llPickUp.setVisibility(View.VISIBLE);
                    tvTihuo.setVisibility(View.GONE);
                    ivIcon.setVisibility(View.GONE);
                    tvCode.setVisibility(View.GONE);
                    rlAddressLocast.setVisibility(View.GONE);
                    rlYunfei.setVisibility(View.GONE);
                }
            } else if ("6".equals(status)) {
                //交易关闭
                llCountdown.setVisibility(View.GONE);
                btnOrderCancel.setVisibility(View.GONE);
                btnOrderPay.setVisibility(View.GONE);
                vLine.setVisibility(View.VISIBLE);
                tvOrderStatus.setVisibility(View.VISIBLE);
                btnOrderDelete.setVisibility(View.VISIBLE);
                tvOrderFahuoTime.setVisibility(View.GONE);
                tvOrderShouhuoTime.setVisibility(View.GONE);
                tvOrderGuanbiTime.setVisibility(View.VISIBLE);
                tvOrderStatus.setText("订单状态：交易关闭");
                tvOrderPayTime.setVisibility(View.GONE);
                if ("1".equals(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveType())) {
                    //快递
                    rlAddressLocast.setVisibility(View.VISIBLE);
                } else if ("2".equals(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveType())) {
                    //门店自取
                    llPickUp.setVisibility(View.VISIBLE);
                    tvTihuo.setVisibility(View.GONE);
                    ivIcon.setVisibility(View.GONE);
                    tvCode.setVisibility(View.GONE);
                    rlAddressLocast.setVisibility(View.GONE);
                    rlYunfei.setVisibility(View.GONE);
                }
            }
            if (myOrderListDetailBean.getBizData().getStoreInfoResVo() != null) {
                Bitmap barCode = CodeUtils.createBarCode(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveCode(), BarcodeFormat.CODE_128, 800, 200);
                ivIcon.setImageBitmap(barCode);
                if (myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveCode() != null) {
                    tvCode.setText(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveCode().substring(0, 4) + "******查看数字");
                }
                tvShopName.setText(myOrderListDetailBean.getBizData().getStoreInfoResVo().getName());
                tvShopAddress.setText(myOrderListDetailBean.getBizData().getStoreInfoResVo().getAddress());
            }
            tvAddressName.setText(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiverName());
            tvAddressMobile.setText(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiverMobile());
            tvAddressDetail.setText(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiverAddress());
            tvYunfei.setText("￥" + myOrderListDetailBean.getBizData().getShopOrderResVo().getCarriageAmount());
            tvNumMoney.setText("￥" + myOrderListDetailBean.getBizData().getShopOrderResVo().getOrderAmount());
            tvShifuMoney.setText("￥" + myOrderListDetailBean.getBizData().getShopOrderResVo().getPayAmount());
            tvOrderId.setText("订单编号:   " + myOrderListDetailBean.getBizData().getShopOrderResVo().getCode());
            tvOrderCreateTime.setText("创建时间:   " + DateUtils.formatDate(myOrderListDetailBean.getBizData().getShopOrderResVo().getCreateTime(), DateUtils.TYPE_01));
            tvOrderPayTime.setText("付款时间:   " + DateUtils.formatDate(myOrderListDetailBean.getBizData().getShopOrderResVo().getPayTime(), DateUtils.TYPE_01));
            tvOrderFahuoTime.setText("发货时间:   " + DateUtils.formatDate(myOrderListDetailBean.getBizData().getShopOrderResVo().getDeliveryTime(), DateUtils.TYPE_01));
            tvOrderTuikuanTime.setText("退款时间:   " + DateUtils.formatDate(myOrderListDetailBean.getBizData().getShopOrderResVo().getRefundTime(), DateUtils.TYPE_01));
            tvOrderGuanbiTime.setText("关闭时间:   " + DateUtils.formatDate(myOrderListDetailBean.getBizData().getShopOrderResVo().getUpdateTime(), DateUtils.TYPE_01));
            tvOrderShouhuoTime.setText("收货时间:   " + DateUtils.formatDate(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveTime(), DateUtils.TYPE_01));
            if (myOrderListDetailBean.getBizData().getLatestState() != null) {
                tvWuliuIng.setText(myOrderListDetailBean.getBizData().getLatestState().getContext());
                tvWuliuIngTime.setText(myOrderListDetailBean.getBizData().getLatestState().getTime());
            }else{
                llWuliu.setVisibility(View.GONE);
            }
            //如果没有优惠券就不显示优惠券一栏
            if (myOrderListDetailBean.getBizData().getShopOrderResVo().getDiscountAmount() == 0.0) {
                rlDiscountCoupon.setVisibility(View.GONE);
            } else {
                tvDiscountCoupon.setText("-￥" + myOrderListDetailBean.getBizData().getShopOrderResVo().getDiscountAmount());
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String times = sdf.format(new Date());
            long time = DateUtils.getReduce(times, DateUtils.formatDate(myOrderListDetailBean.getBizData().getShopOrderResVo().getCreateTime()+1800000, DateUtils.TYPE_01));
            Log.i("htht", "CreateTime::::::: " + DateUtils.formatDate(myOrderListDetailBean.getBizData().getShopOrderResVo().getCreateTime()+1800000, DateUtils.TYPE_01));
            Log.i("htht", "time::::::::: " + time);
            //两时间差,精确到毫秒
            long hour = time / 3600000;
            //以小时为单位取整
            long min = time % 3600000 / 60000;
            //以分钟为单位取整
            long seconds = time % 3600000 % 60000 / 1000;
            //以秒为单位取整
            if (time > 0) {
                time_show_id.initTime(hour, min, seconds);
                time_show_id.start();
            } else {
                time_show_id.setVisibility(View.GONE);
            }
            if ("1".equals(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveType())) {
                tvPeisongType.setText("顺丰冷运");
            } else if ("2".equals(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveType())) {
                tvPeisongType.setText("到店提货");
            }

        } else {
            Toast.makeText(this, myOrderListDetailBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getMyOrderListDelete(MyOrderListCancelBean myOrderListCancelBean) {
        if ("000000".equals(myOrderListCancelBean.getCode())) {
            finish();
            Toast.makeText(this, "删除订单成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, myOrderListCancelBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getMyOrderListCancel(MyOrderListCancelBean myOrderListCancelBean) {
        if ("000000".equals(myOrderListCancelBean.getCode())) {
            finish();
            Toast.makeText(this, "取消订单成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, myOrderListCancelBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getMyOrderListOk(MyOrderListCancelBean myOrderListCancelBean) {
        if ("000000".equals(myOrderListCancelBean.getCode())) {
            finish();
            Toast.makeText(this, "确认收货成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, myOrderListCancelBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getAfterSaleList(MyAfterSaleListBean myAfterSaleListBean) {

    }

    @Override
    public void getAfterSaleDetail(MyAfterSaleDetailBean myAfterSaleDetailBean) {

    }

    @Override
    public void getApplyReturn(MyOrderListCancelBean myOrderListCancelBean) {

    }

    @Override
    public void getCancelApplyReturn(MyOrderListCancelBean myOrderListCancelBean) {

    }

    @Override
    public void getEditApplyReturn(MyOrderListCancelBean myOrderListCancelBean) {

    }

    @Override
    public void showDelevery(ShowDeleveryResultBean showDeleveryResultBean) {

    }

    @Override
    protected void onDestroy() {
        if (myOrderListPresenter != null) {
            myOrderListPresenter.detachView();
        }
        time_show_id.onPause();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (from){
            Intent intent1=new Intent(MyOrderDetailActivity.this,MyOrderActivity.class);
            intent1.putExtra("from",true);
            startActivity(intent1);
        }
    }
}
