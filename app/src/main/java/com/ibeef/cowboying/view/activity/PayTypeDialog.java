package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.PayTypeAdapter;
import com.ibeef.cowboying.adapter.PickUpCodeAdapter;
import com.ibeef.cowboying.base.MyContractBase;
import com.ibeef.cowboying.bean.CarListResultBean;
import com.ibeef.cowboying.bean.MyContractListBean;
import com.ibeef.cowboying.bean.MyContractURLBean;
import com.ibeef.cowboying.bean.MyDiscountCouponListBean;
import com.ibeef.cowboying.bean.PayCodeBean;
import com.ibeef.cowboying.bean.VipCardBean;
import com.ibeef.cowboying.bean.VipCardListBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.MyContractPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PayTypeDialog extends AppCompatActivity implements MyContractBase.IView{


    @Bind(R.id.ry_id)
    RecyclerView ryId;
    @Bind(R.id.rvs_id)
    RelativeLayout rvsId;
    private Intent intent;
    private RadioButton radioButton;
    private PayTypeAdapter payTypeAdapter;
    private String token;
    private MyContractPresenter myContractPresenter;
    private List<PayCodeBean.BizDataBean.PayTypeListBean> listData;
    private String payType="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_type_dialog);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        token = Hawk.get(HawkKey.TOKEN);
        payType = getIntent().getStringExtra("PayType");
        listData= new ArrayList<>();
        ryId.setLayoutManager(new LinearLayoutManager(this));
        payTypeAdapter = new PayTypeAdapter(listData, this, R.layout.item_pay_type);
        ryId.setAdapter(payTypeAdapter);
        myContractPresenter=new MyContractPresenter(this);
        if (!TextUtils.isEmpty(token)) {
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization", token);
            reqData.put("version", Constant.VersionCodes);
            myContractPresenter.showPayCode(reqData,payType);
        }

        payTypeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                intent = new Intent();
                intent.putExtra("PayType", payTypeAdapter.getItem(position).getPayType());
                setResult(555, intent);
                finish();
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
    public void getMyContrantList(MyContractListBean myContractListBean) {

    }

    @Override
    public void getMyContrantURL(MyContractURLBean myContractURLBean) {

    }

    @Override
    public void getMyDiscountCouponList(MyDiscountCouponListBean myDiscountCouponListBean) {

    }

    @Override
    public void showPayCode(PayCodeBean payCodeBean) {

        if ("000000".equals(payCodeBean.getCode())) {
            if (!SDCardUtil.isNullOrEmpty(payCodeBean.getBizData().getPayTypeList())) {
                listData.addAll(payCodeBean.getBizData().getPayTypeList());
                Log.i("htht", "listData.size():::: "+listData.size());
                payTypeAdapter.setNewData(this.listData);
            }
        } else {
            Toast.makeText(this,payCodeBean.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void showVipCard(VipCardBean vipCardBean) {

    }

    @Override
    public void showVipCardHistory(VipCardListBean vipCardListBean) {

    }
}