package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.ConssumptionHistoryListAdapter;
import com.ibeef.cowboying.base.MyContractBase;
import com.ibeef.cowboying.bean.MyContractListBean;
import com.ibeef.cowboying.bean.MyContractURLBean;
import com.ibeef.cowboying.bean.MyDiscountCouponListBean;
import com.ibeef.cowboying.bean.PayCodeBean;
import com.ibeef.cowboying.bean.VipCardBean;
import com.ibeef.cowboying.bean.VipCardListBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.MyContractPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.ibeef.cowboying.view.customview.SuperSwipeRefreshLayout;
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
 * 钱包明细
 */
public class ConsumptionHistoryListActivity extends BaseActivity  implements SuperSwipeRefreshLayout.OnPullRefreshListener,BaseQuickAdapter.RequestLoadMoreListener,MyContractBase.IView {
    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.message_ry)
    RecyclerView messageRy;
    @Bind(R.id.swipe_ly)
    SuperSwipeRefreshLayout swipeLy;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    @Bind(R.id.rv_order)
    RelativeLayout rvOrder;
    private ConssumptionHistoryListAdapter conssumptionHistoryListAdapter;
    private List<VipCardListBean.BizDataBean> beanList;
    private String token;
    private MyContractPresenter myContractPresenter;
    private int page=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_detail);
        ButterKnife.bind(this);
        init();
    }
    private void init(){
        token= Hawk.get(HawkKey.TOKEN);
        info.setText("消费记录");
        messageRy.setLayoutManager(new LinearLayoutManager(this));
        beanList=new ArrayList<>();
        conssumptionHistoryListAdapter=new ConssumptionHistoryListAdapter(beanList,this,R.layout.item_assetdetail_notes);
        conssumptionHistoryListAdapter.setOnLoadMoreListener(this, messageRy);
        messageRy.setAdapter(conssumptionHistoryListAdapter);
        swipeLy.setHeaderViewBackgroundColor(getResources().getColor(R.color.colorAccent));
        swipeLy.setHeaderView(createHeaderView());// add headerView
        swipeLy.setTargetScrollWithLayout(true);
        swipeLy.setOnPullRefreshListener(this);
        messageRy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!messageRy.canScrollVertically(-1)){
                    swipeLy.setEnabled(true);
                }else {
                    swipeLy.setEnabled(false);
                }
            }
        });
        myContractPresenter=new MyContractPresenter(this);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        myContractPresenter.showVipCardHistory(reqData,page);
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onLoadMoreRequested() {
        isMoreLoad = true;
        page += 1;
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        myContractPresenter.showVipCardHistory(reqData,page);
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void showLoading() {
        if (isMoreLoad) {
            loadingLayout.setVisibility(View.GONE);
            messageRy.setVisibility(View.VISIBLE);
            isMoreLoad = false;
        } else {
            loadingLayout.setVisibility(View.VISIBLE);
            messageRy.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        messageRy.setVisibility(View.VISIBLE);
        swipeLy.setRefreshing(false);
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

    }

    @Override
    public void showVipCard(VipCardBean vipCardBean) {

    }

    @Override
    public void showVipCardHistory(VipCardListBean vipCardListBean) {
        if ("000000".equals(vipCardListBean.getCode())) {
            if (SDCardUtil.isNullOrEmpty(vipCardListBean.getBizData())) {
                if (isFirst) {
                    rvOrder.setVisibility(View.VISIBLE);
                    messageRy.setVisibility(View.GONE);
                } else {
                    rvOrder.setVisibility(View.GONE);
                    messageRy.setVisibility(View.VISIBLE);
                }
                conssumptionHistoryListAdapter.loadMoreEnd();
            } else {
                isFirst = false;
                beanList.addAll(vipCardListBean.getBizData());
                conssumptionHistoryListAdapter.setNewData(this.beanList);
                conssumptionHistoryListAdapter.loadMoreComplete();
            }
        } else {
            showToast(vipCardListBean.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        if(myContractPresenter!=null){
            myContractPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        page=1;
        isFirst = true;
        beanList.clear();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        myContractPresenter.showVipCardHistory(reqData,page);
    }

    @Override
    public void onPullDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {

    }
}
