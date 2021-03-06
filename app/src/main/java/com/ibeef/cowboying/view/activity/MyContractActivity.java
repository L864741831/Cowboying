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
import com.ibeef.cowboying.adapter.MyContractAdapter;
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
 * 我的合同
 */
public class MyContractActivity extends BaseActivity implements SuperSwipeRefreshLayout.OnPullRefreshListener,BaseQuickAdapter.RequestLoadMoreListener, MyContractBase.IView{

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    @Bind(R.id.ry_id)
    RecyclerView ryId;
    @Bind(R.id.rv_bg)
    RelativeLayout rvBg;
    @Bind(R.id.swipe_layout)
    SuperSwipeRefreshLayout swipeLayout;
    private String token;
    private List<MyContractListBean.BizDataBean> beanList;
    private MyContractAdapter myContractAdapter;
    private MyContractPresenter myContractPresenter;
    private int currentPage=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    private boolean isAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contract);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        isAd=getIntent().getBooleanExtra("isAd",false);
        token= Hawk.get(HawkKey.TOKEN);
        info.setText("我的合同");
        beanList=new ArrayList<>();
        ryId.setLayoutManager(new LinearLayoutManager(this));
        myContractAdapter=new MyContractAdapter(beanList,this,R.layout.item_my_contract);
        myContractAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(myContractAdapter);
        myContractPresenter=new MyContractPresenter(this);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        myContractPresenter.getMyContrantList(reqData,currentPage);
        ryId.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!ryId.canScrollVertically(-1)){
                    swipeLayout.setEnabled(true);
                }else {
                    swipeLayout.setEnabled(false);
                }
            }
        });
        myContractAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyContractListBean.BizDataBean item=myContractAdapter.getItem(position);
                Intent intent = new Intent(MyContractActivity.this, MyContractDetailActivity.class);
                intent.putExtra("fileName",item.getFileName());
                intent.putExtra("type","");
                startActivity(intent);
            }
        });

        swipeLayout.setHeaderViewBackgroundColor(getResources().getColor(R.color.colorAccent));
        swipeLayout.setHeaderView(createHeaderView());// add headerView
        swipeLayout.setTargetScrollWithLayout(true);
        swipeLayout.setOnPullRefreshListener(this);

    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        if(isAd){
            removeALLActivity();
            startActivity(new Intent(MyContractActivity.this, MainActivity.class));
        }
        finish();
    }

    @Override
    public void onLoadMoreRequested() {
        isMoreLoad=true;
        currentPage+=1;
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        myContractPresenter.getMyContrantList(reqData,currentPage);
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void showLoading() {
        if(isMoreLoad){
            loadingLayout.setVisibility(View.GONE);
            ryId.setVisibility(View.VISIBLE);
            isMoreLoad=false;
        }else {
            loadingLayout.setVisibility(View.VISIBLE);
            ryId.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        ryId.setVisibility(View.VISIBLE);
        swipeLayout.setRefreshing(false);
    }

    @Override
    public void getMyContrantList(MyContractListBean myContractListBean) {
        if(SDCardUtil.isNullOrEmpty(myContractListBean.getBizData())){
            if(isFirst){
                rvBg.setVisibility(View.VISIBLE);
                ryId.setVisibility(View.GONE);
            }else {
                rvBg.setVisibility(View.GONE);
                ryId.setVisibility(View.VISIBLE);
            }
            myContractAdapter.loadMoreEnd();
        }else {
            isFirst=false;
            beanList.addAll(myContractListBean.getBizData());
            myContractAdapter.setNewData(this.beanList);
            myContractAdapter.loadMoreComplete();
        }
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
        currentPage=1;
        isFirst=true;
        beanList.clear();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        myContractPresenter.getMyContrantList(reqData,currentPage);

    }

    @Override
    public void onPullDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(isAd){
            removeALLActivity();
            startActivity(new Intent(MyContractActivity.this, MainActivity.class));
        }
        finish();
    }
}
