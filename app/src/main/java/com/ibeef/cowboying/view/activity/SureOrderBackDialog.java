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
import com.ibeef.cowboying.bean.CreatOderResultBean;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListDetailBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.MyCowsOrderPresenter;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;


public class SureOrderBackDialog extends AppCompatActivity implements View.OnClickListener, MyCowsOrderBase.IView{

    private TextView refuce_id,sure_id;
    private String orderCode;
    private String token;
    private MyCowsOrderPresenter myCowsOrderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sure_order_back_dialog);
        init();
    }

    private void init(){
        token = Hawk.get(HawkKey.TOKEN);
        orderCode = getIntent().getStringExtra("orderCode");
        refuce_id= findViewById(R.id.refuce_id);
        sure_id= findViewById(R.id.sure_id);
        refuce_id.setOnClickListener(this);
        sure_id.setOnClickListener(this);

        myCowsOrderPresenter = new MyCowsOrderPresenter(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sure_id:
                if (!TextUtils.isEmpty(token)) {
                    Map<String, String> reqData = new HashMap<>();
                    reqData.put("Authorization", token);
                    reqData.put("version", Constant.VersionCodes);
                    myCowsOrderPresenter.getMyCowsOrderCancel(reqData, orderCode);
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
    public void getMyCowsToPay(CreatOderResultBean creatOderResultBean) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(myCowsOrderPresenter!=null){
            myCowsOrderPresenter.detachView();
        }
    }
}