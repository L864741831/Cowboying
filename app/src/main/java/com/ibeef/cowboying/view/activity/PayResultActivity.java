package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.MyCowsOrderBase;
import com.ibeef.cowboying.base.MyCowsOrderDeleteBean;
import com.ibeef.cowboying.bean.CreatOderResultBean;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListDetailBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.MyCowsOrderPresenter;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

public class PayResultActivity extends BaseActivity implements MyCowsOrderBase.IView{

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.pay_money_id)
    TextView payMoneyId;
    @Bind(R.id.pasture_name_id)
    TextView pastureNameId;
    @Bind(R.id.pay_time_id)
    TextView payTimeId;
    @Bind(R.id.pay_type_id)
    TextView payTypeId;
    @Bind(R.id.impordent_info_id)
    TextView impordentInfoId;
    @Bind(R.id.see_order_btn)
    TextView seeOrderBtn;

    private Integer orderId;
    private MyCowsOrderPresenter myCowsOrderPresenter;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_result);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        info.setText("支付成功");
        orderId=getIntent().getIntExtra("orderId",0);
        myCowsOrderPresenter=new MyCowsOrderPresenter(this);
        token = Hawk.get(HawkKey.TOKEN);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization", token);
        reqData.put("version", getVersionCodes());
        myCowsOrderPresenter.geMyCowsOrderListDetail(reqData, orderId+"");
    }

    @OnClick({R.id.back_id, R.id.see_order_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.see_order_btn:
                Intent intent=new Intent(PayResultActivity.this,MyCowsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("from",true);
                startActivity(intent);
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
        if("000000".equals(myCowsOrderListDetailBean.getCode())){
            payMoneyId.setText("￥"+myCowsOrderListDetailBean.getBizData().getPrice());
            pastureNameId.setText("认领牧场："+myCowsOrderListDetailBean.getBizData().getPastureName());
            payTimeId.setText("交易时间："+myCowsOrderListDetailBean.getBizData().getPayTime());
            //支付方式（1:支付宝：2：微信支付；3：钱包余额）
            String payType="";
            if("1".equals(myCowsOrderListDetailBean.getBizData().getPayType())){
                payType="支付宝";
            }else   if("2".equals(myCowsOrderListDetailBean.getBizData().getPayType())){
                payType="微信";
            }else   if("3".equals(myCowsOrderListDetailBean.getBizData().getPayType())) {
                payType="账户余额";
            }
            payTypeId.setText("付款方式："+payType);
            impordentInfoId.setText("特别提醒：支付成功后请耐心等待系统审核，您的收益将从次日起开始计算，请注意查看。");
        }else {
            showToast(myCowsOrderListDetailBean.getMessage());
        }

    }

    @Override
    public void getMyCowsOrderDelete(MyCowsOrderDeleteBean myCowsOrderDeleteBean) {

    }

    @Override
    public void getMyCowsOrderCancel(MyCowsOrderDeleteBean myCowsOrderDeleteBean) {

    }

    @Override
    public void getMyCowsToPay(CreatOderResultBean creatOderResultBean) {

    }

    @Override
    protected void onDestroy() {
        if(myCowsOrderPresenter!=null){
            myCowsOrderPresenter.detachView();
        }
        super.onDestroy();
    }
}
