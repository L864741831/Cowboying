package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.MyCowsOrderBase;
import com.ibeef.cowboying.base.MyCowsOrderDeleteBean;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListDetailBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.MyCowsOrderPresenter;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import rxfamily.view.BaseActivity;


public class MyCowsProgressDialog extends BaseActivity implements View.OnClickListener, MyCowsOrderBase.IView{

    private String orderCode;
    private String token;
    private LinearLayout linearLayout;
    private MyCowsOrderPresenter myCowsOrderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cows_progress_dialog);
        init();
    }

    private void init(){
        token = Hawk.get(HawkKey.TOKEN);
        orderCode = getIntent().getStringExtra("orderCode");
        linearLayout=findViewById(R.id.rvs_id);
        linearLayout.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rvs_id:
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
    public void geMyCowsOrderList(MyCowsOrderListBean myCowsOrderListBean) {

    }

    @Override
    public void geMyCowsOrderListDetail(MyCowsOrderListDetailBean myCowsOrderListDetailBean) {

    }

    @Override
    public void getMyCowsOrderDelete(MyCowsOrderDeleteBean myCowsOrderDeleteBean) {

    }

    @Override
    public void getMyCowsOrderCancel(MyCowsOrderDeleteBean msg) {
        if("000000".equals(msg.getCode())){
            finish();
            Toast.makeText(this,"取消订单成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, msg.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("/adopt/order/cancel", "msg.getMessage()"+msg.getMessage());
        }
    }
}