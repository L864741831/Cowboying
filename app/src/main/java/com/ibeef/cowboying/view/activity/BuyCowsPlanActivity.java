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
import com.ibeef.cowboying.adapter.BuyCowListAdapter;
import com.ibeef.cowboying.base.BuyCowSchemeBase;
import com.ibeef.cowboying.bean.ActiveSchemeResultBean;
import com.ibeef.cowboying.bean.HistorySchemeResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.BuyCowsSchemePresenter;
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
 * 养牛方案列表
 */
public class BuyCowsPlanActivity extends BaseActivity implements SuperSwipeRefreshLayout.OnPullRefreshListener,BaseQuickAdapter.RequestLoadMoreListener,BuyCowSchemeBase.IView {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.swipe_ly)
    SuperSwipeRefreshLayout swipeLy;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    @Bind(R.id.ry_id)
    RecyclerView ryId;
    @Bind(R.id.rv_order)
    RelativeLayout rvOrder;
    private BuyCowListAdapter buyCowListAdapter;
    private List<ActiveSchemeResultBean.BizDataBean> objectList;
    private BuyCowsSchemePresenter buyCowsSchemePresenter;

    private String token;

    private int currentPage=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_cows_plan);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        token = Hawk.get(HawkKey.TOKEN);
        info.setText("养牛方案列表");
        actionNewQuestionTv.setText("往期记录");
        actionNewQuestionTv.setVisibility(View.VISIBLE);

        swipeLy.setHeaderViewBackgroundColor(getResources().getColor(R.color.colorAccent));
        swipeLy.setHeaderView(createHeaderView());// add headerView
        swipeLy.setTargetScrollWithLayout(true);
        swipeLy.setOnPullRefreshListener(this);

        objectList=new ArrayList<>();
        ryId.setLayoutManager(new LinearLayoutManager(this));
        buyCowListAdapter=new BuyCowListAdapter(objectList,this,R.layout.item_buy_cows_plan);
        buyCowListAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(buyCowListAdapter);
        ryId.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!ryId.canScrollVertically(-1)){
                    swipeLy.setEnabled(true);
                }else {
                    swipeLy.setEnabled(false);
                }
            }
        });

        buyCowListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId()==R.id.now_claim_btn_id){
                    ActiveSchemeResultBean.BizDataBean item=buyCowListAdapter.getItem(position);
                    Intent intent=new Intent(BuyCowsPlanActivity.this,CowsClaimActivity.class);
                    intent.putExtra("schemId",item.getSchemeId());
                    startActivity(intent);
                }
            }
        });

        buyCowsSchemePresenter=new BuyCowsSchemePresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        currentPage=1;
        isFirst=true;
        isMoreLoad=false;
        objectList.clear();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        buyCowsSchemePresenter.getActiveSchemeInfo(reqData,currentPage);
    }

    @OnClick({R.id.back_id,R.id.action_new_question_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.action_new_question_tv:
                startActivity(CowClaimPastRecordActivity.class);
                break;
            default:
                break;
        }
    }


    @Override
    public void onLoadMoreRequested() {
        isMoreLoad = true;
        currentPage += 1;
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        buyCowsSchemePresenter.getActiveSchemeInfo(reqData,currentPage);
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getActiveSchemeInfo(ActiveSchemeResultBean activeSchemeResultBean) {
        if ("000000".equals(activeSchemeResultBean.getCode())) {
            if (SDCardUtil.isNullOrEmpty(activeSchemeResultBean.getBizData())) {
                if (isFirst) {
                    rvOrder.setVisibility(View.VISIBLE);
                    ryId.setVisibility(View.GONE);
                } else {
                    rvOrder.setVisibility(View.GONE);
                    ryId.setVisibility(View.VISIBLE);
                }
                buyCowListAdapter.loadMoreEnd();
            } else {
                isFirst = false;
                objectList.addAll(activeSchemeResultBean.getBizData());
                buyCowListAdapter.setNewData(this.objectList);
                buyCowListAdapter.loadMoreComplete();
            }
        } else {
            showToast(activeSchemeResultBean.getMessage());
        }
    }

    @Override
    public void getHistorySchemeInfo(HistorySchemeResultBean historySchemeResultBean) {

    }

    @Override
    public void showLoading() {
        if (isMoreLoad) {
            loadingLayout.setVisibility(View.GONE);
            ryId.setVisibility(View.VISIBLE);
            isMoreLoad = false;
        } else {
            loadingLayout.setVisibility(View.VISIBLE);
            ryId.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        ryId.setVisibility(View.VISIBLE);
        swipeLy.setRefreshing(false);
    }

    @Override
    protected void onDestroy() {
        if (buyCowsSchemePresenter != null) {
            buyCowsSchemePresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        isFirst = true;
        objectList.clear();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        buyCowsSchemePresenter.getActiveSchemeInfo(reqData,currentPage);

    }

    @Override
    public void onPullDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {

    }
}
