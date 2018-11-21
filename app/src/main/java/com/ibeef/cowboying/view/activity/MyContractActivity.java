package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.ibeef.cowboying.bean.HomeAllVideoResultBean;
import com.ibeef.cowboying.bean.MyContractListBean;
import com.ibeef.cowboying.bean.MyContractURLBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.HomeBannerPresenter;
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
import rxfamily.bean.BaseBean;
import rxfamily.view.BaseActivity;

/**
 * 我的合同
 */
public class MyContractActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener, MyContractBase.IView{

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
    SwipeRefreshLayout swipeLayout;
    private String token;
    private List<MyContractListBean.BizDataBean> beanList;
    private MyContractAdapter myContractAdapter;
    private MyContractPresenter myContractPresenter;
    private int currentPage=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contract);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        token= Hawk.get(HawkKey.TOKEN);
        info.setText("我的合同");
        beanList=new ArrayList<>();
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeResources(R.color.colorAccent);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setEnabled(true);
        ryId.setLayoutManager(new LinearLayoutManager(this));
        myContractAdapter=new MyContractAdapter(beanList,this,R.layout.item_my_contract);
        myContractAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(myContractAdapter);
        myContractAdapter.loadMoreEnd();
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
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
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
        swipeLayout.setRefreshing(false);
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
    protected void onDestroy() {
        if(myContractPresenter!=null){
            myContractPresenter.detachView();
        }
        super.onDestroy();
    }
}
