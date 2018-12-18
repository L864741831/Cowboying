package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.AfterSaleDetailAdapter;
import com.ibeef.cowboying.base.GetApplyReturnParameterBean;
import com.ibeef.cowboying.base.GetEditApplyReturnParameterBean;
import com.ibeef.cowboying.base.MyOrderListBase;
import com.ibeef.cowboying.bean.MyAfterSaleDetailBean;
import com.ibeef.cowboying.bean.MyAfterSaleListBean;
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
import rxfamily.view.BaseActivity;

/**
 * 申请退款界面
 *
 * @author Administrator
 */
public class AfterSaleApplyActivity extends BaseActivity implements MyOrderListBase.IView {

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
    @Bind(R.id.tv_return_reason)
    TextView tvReturnReason;
    private List<MyOrderListDetailBean.BizDataBean.ShopOrderProductResVosBean> beanList;
    private AfterSaleDetailAdapter afterSaleAdapter;
    private String orderCode;
    private String token;
    private MyOrderListPresenter myOrderListPresenter;
    private MyOrderListDetailBean myOrderListDetailBean;
    private String id;
    private String selectText;
    private String refundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_sale_apply);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("申请售后");
        token = Hawk.get(HawkKey.TOKEN);
        orderCode = getIntent().getStringExtra("orderCode");
        Log.i("htht", "orderCode:::::接收 "+orderCode);
        id = getIntent().getStringExtra("id");
        beanList = new ArrayList<>();
        beanList.clear();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setHasFixedSize(true);
        rvList.setNestedScrollingEnabled(false);
        myOrderListPresenter = new MyOrderListPresenter(this);
        if (!TextUtils.isEmpty(token)) {
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization", token);
            reqData.put("version", getVersionCodes());
            myOrderListPresenter.getMyOrderListDetail(reqData, orderCode);
        }

    }

    @OnClick({R.id.back_id, R.id.btn_return_reason, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.btn_return_reason:
                Intent intent = new Intent(this, AfterSaleApplyReasonDialog.class);
                startActivityForResult(intent, 666);
                break;
            case R.id.btn_commit:
                if (tvReturnReason.getText().toString().length()==0||"".equals(tvReturnReason.getText().toString())){
                    Toast.makeText(this,"原因不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (id != null) {
                    //修改申请退款
                    Map<String, String> reqData1 = new HashMap<>();
                    reqData1.put("Authorization", token);
                    reqData1.put("version", getVersionCodes());
                    GetEditApplyReturnParameterBean getEditApplyReturnParameterBean = new GetEditApplyReturnParameterBean();
                    getEditApplyReturnParameterBean.setId(id);
                    getEditApplyReturnParameterBean.setReason(selectText + tvReturnInfo.getText().toString());
                    myOrderListPresenter.getEditApplyReturn(reqData1, getEditApplyReturnParameterBean);
                } else {
                    //申请退款
                    Map<String, String> reqData = new HashMap<>();
                    reqData.put("Authorization", token);
                    reqData.put("version", getVersionCodes());
                    GetApplyReturnParameterBean getApplyReturnParameterBean = new GetApplyReturnParameterBean();
                    getApplyReturnParameterBean.setOrderId(orderCode);
                    getApplyReturnParameterBean.setReason(selectText + tvReturnInfo.getText().toString());
                    myOrderListPresenter.getApplyReturn(reqData, getApplyReturnParameterBean);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 666 && resultCode == 555) {
            selectText = data.getStringExtra("selectText");
            tvReturnReason.setText(selectText);
            Log.i("htht", "selectText=============" + selectText);
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
    public void getMyOrderListDetail(MyOrderListDetailBean myOrderListDetailBean) {
        if ("000000".equals(myOrderListDetailBean.getCode())) {
            this.myOrderListDetailBean = myOrderListDetailBean;
            String status = myOrderListDetailBean.getBizData().getShopOrderResVo().getStatus();
            beanList.clear();
            afterSaleAdapter = new AfterSaleDetailAdapter(beanList, myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveType(), this, R.layout.item_after_sale_detail);
            rvList.setAdapter(afterSaleAdapter);
            this.beanList.addAll(myOrderListDetailBean.getBizData().getShopOrderProductResVos());
            afterSaleAdapter.setNewData(beanList);
            tvReturnMoney.setText("￥" + myOrderListDetailBean.getBizData().getShopOrderResVo().getPayAmount());
            refundId = myOrderListDetailBean.getBizData().getRefundId();
            tvReturnGrayInfo.setText("最多￥"+myOrderListDetailBean.getBizData().getShopOrderResVo().getPayAmount()+"含发货运费￥"+myOrderListDetailBean.getBizData().getShopOrderResVo().getCarriageAmount());
        }
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

    }

    @Override
    public void getApplyReturn(MyOrderListCancelBean myOrderListCancelBean) {
        if ("000000".equals(myOrderListCancelBean.getCode())) {
//            Intent intent3 = new Intent(this, AfterSaleDetailActivity.class);
//            intent3.putExtra("orderId", refundId);
//            startActivity(intent3);
            //todo refundId为空   跳不到退款详情界面
            finish();
            Toast.makeText(this, "申请退款成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, myOrderListCancelBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getCancelApplyReturn(MyOrderListCancelBean myOrderListCancelBean) {

    }

    @Override
    public void getEditApplyReturn(MyOrderListCancelBean myOrderListCancelBean) {
        if ("000000".equals(myOrderListCancelBean.getCode())) {
            finish();
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, myOrderListCancelBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showDelevery(ShowDeleveryResultBean showDeleveryResultBean) {

    }
}
