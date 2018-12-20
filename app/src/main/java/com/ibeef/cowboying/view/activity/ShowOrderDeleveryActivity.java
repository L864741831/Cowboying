package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MyWorkStepAdapter;
import com.ibeef.cowboying.base.MyOrderListBase;
import com.ibeef.cowboying.bean.MyAfterSaleDetailBean;
import com.ibeef.cowboying.bean.MyAfterSaleListBean;
import com.ibeef.cowboying.bean.MyOrderListBean;
import com.ibeef.cowboying.bean.MyOrderListCancelBean;
import com.ibeef.cowboying.bean.MyOrderListDetailBean;
import com.ibeef.cowboying.bean.ShowDeleveryResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.MyOrderListPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
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
 * 物流信息
 */
public class ShowOrderDeleveryActivity extends BaseActivity  implements SwipeRefreshLayout.OnRefreshListener, MyOrderListBase.IView{

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.delevery_company_id)
    TextView deleveryCompanyId;
    @Bind(R.id.delevery_code_id)
    TextView deleveryCodeId;
    @Bind(R.id.message_ry)
    RecyclerView messageRy;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    @Bind(R.id.rv_order)
    RelativeLayout rvOrder;
    @Bind(R.id.lvs_id)
    LinearLayout lvsId;
    @Bind(R.id.swipe_ly)
    SwipeRefreshLayout swipeLy;
    private String token;
    private List<ShowDeleveryResultBean.BizDataBean.ExpressResVosBean> beanList;
    private MyOrderListPresenter myOrderListPresenter;
    private String orderId;
    private MyWorkStepAdapter myWorkStepAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_delevery);
        ButterKnife.bind(this);
        init();
    }
    private void init(){
        orderId=getIntent().getStringExtra("orderId");

        token= Hawk.get(HawkKey.TOKEN);
        info.setText("物流信息");
        messageRy.setLayoutManager(new LinearLayoutManager(this));
        messageRy.setHasFixedSize(true);
        messageRy.setNestedScrollingEnabled(false);

        swipeLy.setColorSchemeResources(R.color.colorAccent);
        swipeLy.setOnRefreshListener(this);
        swipeLy.setEnabled(true);

        beanList=new ArrayList<>();

        myOrderListPresenter=new MyOrderListPresenter(this);
        myWorkStepAdapter=new MyWorkStepAdapter(beanList,this,R.layout.item_step_list);
        messageRy.setAdapter(myWorkStepAdapter);

        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        myOrderListPresenter.showDelevery(reqData,orderId);
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
        messageRy.setVisibility(View.GONE);
        lvsId.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        messageRy.setVisibility(View.VISIBLE);
        lvsId.setVisibility(View.VISIBLE);
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
        if("000000".equals(showDeleveryResultBean.getCode())){
            if(!SDCardUtil.isNullOrEmpty(showDeleveryResultBean.getBizData().getExpressResVos())){
                beanList.addAll(showDeleveryResultBean.getBizData().getExpressResVos());
                myWorkStepAdapter.setNewData(beanList);
            }else{
                rvOrder.setVisibility(View.VISIBLE);
            }

            if(!SDCardUtil.isNullOrEmpty(showDeleveryResultBean.getBizData().getExpressName())){
                deleveryCompanyId.setText("物流公司："+showDeleveryResultBean.getBizData().getExpressName());
            }

            if(!SDCardUtil.isNullOrEmpty(showDeleveryResultBean.getBizData().getExpressCode())){
                deleveryCodeId.setText("运单编号："+showDeleveryResultBean.getBizData().getExpressNumber());
            }
        }else {
            showToast(showDeleveryResultBean.getMessage());
        }

    }

    @Override
    public void onRefresh() {
        beanList.clear();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        myOrderListPresenter.showDelevery(reqData,orderId);
        swipeLy.setRefreshing(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(myOrderListPresenter!=null){
            myOrderListPresenter.detachView();
        }
    }
}
