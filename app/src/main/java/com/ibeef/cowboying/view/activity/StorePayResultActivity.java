package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.StoreSureOrderAdapter;
import com.ibeef.cowboying.bean.StoreCarResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

public class StorePayResultActivity extends BaseActivity {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.show_addr_id)
    TextView showAddrId;
    @Bind(R.id.mobile_txt_id)
    TextView mobileTxtId;
    @Bind(R.id.del_addr_txt_id)
    TextView delAddrTxtId;
    @Bind(R.id.ry_id)
    RecyclerView ryId;
    @Bind(R.id.delevery_type_id)
    TextView deleveryTypeId;
    @Bind(R.id.couppon_money_id)
    TextView coupponMoneyId;
    @Bind(R.id.freight_money_id)
    TextView freightMoneyId;
    @Bind(R.id.oder_all_money_id)
    TextView oderAllMoneyId;
    @Bind(R.id.real_pay_money_id)
    TextView realPayMoneyId;
    @Bind(R.id.rv_bottom_id)
    RelativeLayout rvBottomId;
    private int orderId;
    private List<StoreCarResultBean> objectList;
    private String token;
    private StoreSureOrderAdapter storeSureOrderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_pay_result);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("支付成功");
        token = Hawk.get(HawkKey.TOKEN);
        orderId = getIntent().getIntExtra("orderId", 0);
        ryId.setHasFixedSize(true);
        ryId.setNestedScrollingEnabled(false);
        ryId.setLayoutManager(new LinearLayoutManager(this));
        objectList=new ArrayList<>();
        for (int i=0;i<2;i++) {
            StoreCarResultBean storeCarResultBean=new StoreCarResultBean();
            storeCarResultBean.setDefautChoose(0);
            objectList.add(storeCarResultBean);
        }
        storeSureOrderAdapter=new StoreSureOrderAdapter(objectList,this,R.layout.item_sureorder_info);
        ryId.setAdapter(storeSureOrderAdapter);
    }

    @OnClick({R.id.back_id,R.id.rv_bottom_id})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.back_id:
                finish();
                break;
            case R.id.rv_bottom_id:
                //跳到订单列表
                break;
            default:
                break;
        }

    }
}
