package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.MyCowsOrderBase;
import com.ibeef.cowboying.base.MyCowsOrderDeleteBean;
import com.ibeef.cowboying.base.MyOrderListBase;
import com.ibeef.cowboying.bean.CreatOderResultBean;
import com.ibeef.cowboying.bean.MyAfterSaleDetailBean;
import com.ibeef.cowboying.bean.MyAfterSaleListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListDetailBean;
import com.ibeef.cowboying.bean.MyOrderListBean;
import com.ibeef.cowboying.bean.MyOrderListCancelBean;
import com.ibeef.cowboying.bean.MyOrderListDetailBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.MyCowsOrderPresenter;
import com.ibeef.cowboying.presenter.MyOrderListPresenter;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;


public class MyorderListCancelDialog extends AppCompatActivity implements View.OnClickListener, MyOrderListBase.IView{

    private TextView refuce_id,sure_id;
    private String orderCode;
    private String token;
    private MyOrderListPresenter myOrderListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorderlist_dialog);
        init();
    }

    private void init(){
        token = Hawk.get(HawkKey.TOKEN);
        orderCode = getIntent().getStringExtra("orderCode");
        refuce_id= findViewById(R.id.refuce_id);
        sure_id= findViewById(R.id.sure_id);
        refuce_id.setOnClickListener(this);
        sure_id.setOnClickListener(this);

        myOrderListPresenter = new MyOrderListPresenter(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sure_id:
                if (!TextUtils.isEmpty(token)) {
                    Map<String, String> reqData = new HashMap<>();
                    reqData.put("Authorization", token);
                    reqData.put("version", Constant.VersionCodes);
                    myOrderListPresenter.getMyOrderListCancel(reqData, orderCode);
                }
                break;
            case R.id.refuce_id:
                finish();
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
    public void getMyOrderListCancel(MyOrderListCancelBean msg) {
        Log.i("/adopt/order/cancel", "取消订单成功:::::1: "+msg.getCode());
        if("000000".equals(msg.getCode())){
            Log.i("/adopt/order/cancel", "取消订单成功:::2::: "+msg.getCode());
            Toast.makeText(this,"取消订单成功", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, msg.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("/adopt/order/cancel", "msg.getMessage()"+msg.getMessage());
        }
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

    }

    @Override
    public void getCancelApplyReturn(MyOrderListCancelBean myOrderListCancelBean) {

    }

    @Override
    public void getEditApplyReturn(MyOrderListCancelBean myOrderListCancelBean) {

    }

}