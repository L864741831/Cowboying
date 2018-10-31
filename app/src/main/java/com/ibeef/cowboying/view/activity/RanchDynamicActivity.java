package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.adapter.RanchDynamicdapter;
import com.ibeef.cowboying.base.HomeBannerBase;
import com.ibeef.cowboying.bean.HomeAllVideoResultBean;
import com.ibeef.cowboying.bean.HomeBannerResultBean;
import com.ibeef.cowboying.bean.HomeVideoResultBean;
import com.ibeef.cowboying.presenter.HomeBannerPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * @author Administrator
 * 牧场动态主界面
 */
public class RanchDynamicActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener,HomeBannerBase.IView {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.video_list_rv)
    RecyclerView videoListRv;
    @Bind(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    @Bind(R.id.rv_order)
    RelativeLayout rvOrder;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    private RanchDynamicdapter ranchDynamicdapter;
    private List<HomeAllVideoResultBean.BizDataBean> listData;

    private HomeBannerPresenter homeBannerPresenter;
    private int currentPage=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranch_dynamic);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("牧场动态");
        listData=new ArrayList<>();
        swipeLayout.setColorSchemeResources(R.color.colorAccent);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setEnabled(true);
        videoListRv.setLayoutManager(new LinearLayoutManager(this));
        ranchDynamicdapter = new RanchDynamicdapter(listData,this);
        ranchDynamicdapter.setOnLoadMoreListener(this, videoListRv);
        videoListRv.setAdapter(ranchDynamicdapter);
        homeBannerPresenter=new HomeBannerPresenter(this);
        homeBannerPresenter.getAllVideo(getVersionCodes(),currentPage);
        videoListRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!videoListRv.canScrollVertically(-1)){
                    swipeLayout.setEnabled(true);
                }else {
                    swipeLayout.setEnabled(false);
                }
            }
        });
        ranchDynamicdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeAllVideoResultBean.BizDataBean item=ranchDynamicdapter.getItem(position);
                Intent intent = new Intent(RanchDynamicActivity.this, PlayerVideoActivity.class);
                intent.putExtra("video_url",item.getPlayUrl());
                intent.putExtra("title",item.getName());
                intent.putExtra("coverUrl",item.getCoverUrl());
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
        listData.clear();
        homeBannerPresenter.getAllVideo(getVersionCodes(),currentPage);
        swipeLayout.setRefreshing(false);
    }

    @Override
    public void onLoadMoreRequested() {
        isMoreLoad=true;
        currentPage+=1;
        homeBannerPresenter.getAllVideo(getVersionCodes(),currentPage);
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getHomeBanner(HomeBannerResultBean homeBannerResultBean) {

    }

    @Override
    public void getHomeVideo(HomeVideoResultBean homeAdResultBean) {

    }

    @Override
    public void getAllVideo(HomeAllVideoResultBean homeAdResultBean) {
        if(SDCardUtil.isNullOrEmpty(homeAdResultBean.getBizData())){
            if(isFirst){
                rvOrder.setVisibility(View.VISIBLE);
                videoListRv.setVisibility(View.GONE);
            }else {
                rvOrder.setVisibility(View.GONE);
                videoListRv.setVisibility(View.VISIBLE);
            }
            ranchDynamicdapter.loadMoreEnd();
        }else {
            isFirst=false;
            listData.addAll(homeAdResultBean.getBizData());
            ranchDynamicdapter.setNewData(this.listData);
            ranchDynamicdapter.loadMoreComplete();
        }
    }

    @Override
    public void showLoading() {
        if(isMoreLoad){
            loadingLayout.setVisibility(View.GONE);
            videoListRv.setVisibility(View.VISIBLE);
            isMoreLoad=false;
        }else {
            loadingLayout.setVisibility(View.VISIBLE);
            videoListRv.setVisibility(View.GONE);
        }

    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        videoListRv.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        if(homeBannerPresenter!=null){
           homeBannerPresenter.detachView();
        }
        super.onDestroy();
    }

}
