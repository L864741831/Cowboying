package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.StoreResultOrderAdapter;
import com.ibeef.cowboying.base.MyOrderListBase;
import com.ibeef.cowboying.bean.MyAfterSaleDetailBean;
import com.ibeef.cowboying.bean.MyAfterSaleListBean;
import com.ibeef.cowboying.bean.MyOrderListBean;
import com.ibeef.cowboying.bean.MyOrderListCancelBean;
import com.ibeef.cowboying.bean.MyOrderListDetailBean;
import com.ibeef.cowboying.bean.ShowDeleveryResultBean;
import com.ibeef.cowboying.bean.StoreCarResultBean;
import com.ibeef.cowboying.config.Constant;
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

public class StorePayResultActivity extends BaseActivity implements MyOrderListBase.IView{

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
    @Bind(R.id.rv_storeaddr_id)
    RelativeLayout rvStoreaddrId;
    @Bind(R.id.show_store_id)
    TextView showStoreId;
    @Bind(R.id.show_store_addr_id)
    TextView showStoreAddrId;
    @Bind(R.id.address_rv)
    RelativeLayout addressRv;
    @Bind(R.id.freight_rv)
    RelativeLayout freightrv;
    @Bind(R.id.show_title_id)
    TextView showTitleId;
    private int orderId;
    private List<StoreCarResultBean> objectList;
    private String token;
    private StoreResultOrderAdapter storeResultOrderAdapter;
    private MyOrderListPresenter myOrderListPresenter;
    private Map<String, String> reqData;
    private String stadus;
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
        myOrderListPresenter=new MyOrderListPresenter(this);
        reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        myOrderListPresenter.getMyOrderListDetail(reqData,orderId+"");
    }

    @OnClick({R.id.back_id,R.id.rv_bottom_id})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.back_id:
                finish();
                break;
            case R.id.rv_bottom_id:
                //跳到订单列表
                Intent intent1=new Intent(StorePayResultActivity.this,MyOrderDetailActivity.class);
                intent1.putExtra("orderId",orderId+"");
                intent1.putExtra("from",true);
                startActivity(intent1);
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
    public void getMyOrderListDetail(MyOrderListDetailBean myOrderListDetailBean) {
        if("000000".equals(myOrderListDetailBean.getCode())){
            stadus=myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveType();
            storeResultOrderAdapter=new StoreResultOrderAdapter(objectList,this,R.layout.item_sureorder_info,stadus);
            ryId.setAdapter(storeResultOrderAdapter);
            //取货方式（1：快递；2：门店自提）
            if ("1".equals(stadus)) {
                rvStoreaddrId.setVisibility(View.GONE);
                addressRv.setVisibility(View.VISIBLE);
                freightrv.setVisibility(View.VISIBLE);
                showTitleId.setVisibility(View.VISIBLE);
                showAddrId.setText(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiverName());
                mobileTxtId.setText(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiverMobile());
                delAddrTxtId.setText(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiverProvince()+myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiverCity()+myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiverRegion()+myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiverAddress());
            } else  if ("2".equals(stadus)){
                rvStoreaddrId.setVisibility(View.VISIBLE);
                addressRv.setVisibility(View.GONE);
                freightrv.setVisibility(View.GONE);
                showTitleId.setVisibility(View.GONE);

                showStoreId.setText(myOrderListDetailBean.getBizData().getStoreInfoResVo().getName());
                showStoreAddrId.setText(myOrderListDetailBean.getBizData().getStoreInfoResVo().getAddress());
            }

            storeResultOrderAdapter.setNewData(myOrderListDetailBean.getBizData().getShopOrderProductResVos());

            storeResultOrderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    MyOrderListDetailBean.BizDataBean.ShopOrderProductResVosBean items=storeResultOrderAdapter.getItem(position);
                    Constant.PRODUCR_ID=items.getProductId();
                    Intent intent1=new Intent(StorePayResultActivity.this,MainActivity.class);
                    intent1.putExtra("index",1);
                    startActivity(intent1);
                }
            });
            //取货方式（1：快递；2：门店自提）
            if("1".equals(myOrderListDetailBean.getBizData().getShopOrderResVo().getReceiveType())){
                deleveryTypeId.setText("顺丰配送");
            }else {
                deleveryTypeId.setText("门店自取");
            }
            if(myOrderListDetailBean.getBizData().getShopOrderResVo().getDiscountAmount()>0){
                coupponMoneyId.setText("-￥"+myOrderListDetailBean.getBizData().getShopOrderResVo().getDiscountAmount());
            }else {
                coupponMoneyId.setText("未使用");
            }
            if(myOrderListDetailBean.getBizData().getShopOrderResVo().getCarriageAmount()>0){
                freightMoneyId.setText("￥"+myOrderListDetailBean.getBizData().getShopOrderResVo().getDiscountAmount());
            }else {
                freightMoneyId.setText("包邮");
            }
            oderAllMoneyId.setText("￥"+myOrderListDetailBean.getBizData().getShopOrderResVo().getOrderAmount());
            realPayMoneyId.setText("￥"+myOrderListDetailBean.getBizData().getShopOrderResVo().getPayAmount());
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
        super.onDestroy();
        if(myOrderListPresenter!=null){
            myOrderListPresenter.detachView();
        }
    }
}
