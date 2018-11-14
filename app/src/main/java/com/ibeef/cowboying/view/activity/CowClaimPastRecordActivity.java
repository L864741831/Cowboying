package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.BuyCowListAdapter;
import com.ibeef.cowboying.adapter.CowClaimPastRecordAdapter;
import com.ibeef.cowboying.base.BuyCowSchemeBase;
import com.ibeef.cowboying.bean.ActiveSchemeResultBean;
import com.ibeef.cowboying.bean.HistorySchemeResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.BuyCowsSchemePresenter;
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
 * 买牛往期记录
 */
public class CowClaimPastRecordActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener,BuyCowSchemeBase.IView {
    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.swipe_ly)
    SwipeRefreshLayout swipeLy;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    @Bind(R.id.ry_id)
    RecyclerView ryId;
    @Bind(R.id.rv_order)
    RelativeLayout rvOrder;
    private CowClaimPastRecordAdapter cowClaimPastRecordAdapter;
    private List<HistorySchemeResultBean.BizDataBean> objectList;
    private BuyCowsSchemePresenter buyCowsSchemePresenter;
    private String token;
    private int currentPage=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cow_claim_past_record);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        token = Hawk.get(HawkKey.TOKEN);
        info.setText("往期记录");
        objectList=new ArrayList<>();
        swipeLy.setColorSchemeResources(R.color.colorAccent);
        swipeLy.setOnRefreshListener(this);
        swipeLy.setEnabled(true);
        ryId.setLayoutManager(new LinearLayoutManager(this));
        cowClaimPastRecordAdapter=new CowClaimPastRecordAdapter(objectList,this,R.layout.item_cows_cliam_pastrecord);
        cowClaimPastRecordAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(cowClaimPastRecordAdapter);
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

        cowClaimPastRecordAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId()==R.id.see_people_num_rv){
                    HistorySchemeResultBean.BizDataBean item=cowClaimPastRecordAdapter.getItem(position);
                    Intent intent=new Intent(CowClaimPastRecordActivity.this,JionPeopleNumActivity.class);
                    intent.putExtra("info",item);
                    startActivity(intent);
                }
            }
        });
        buyCowsSchemePresenter=new BuyCowsSchemePresenter(this);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        buyCowsSchemePresenter.getHistorySchemeInfo(reqData,currentPage);

    }
    @OnClick({R.id.back_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            default:
                break;
        }
    }
    @Override
    public void onRefresh() {
        currentPage = 1;
        isFirst = true;
        objectList.clear();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        buyCowsSchemePresenter.getHistorySchemeInfo(reqData,currentPage);
        swipeLy.setRefreshing(false);
    }

    @Override
    public void onLoadMoreRequested() {
        isMoreLoad = true;
        currentPage += 1;
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        buyCowsSchemePresenter.getHistorySchemeInfo(reqData,currentPage);
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getActiveSchemeInfo(ActiveSchemeResultBean activeSchemeResultBean) {

    }

    @Override
    public void getHistorySchemeInfo(HistorySchemeResultBean historySchemeResultBean) {
        if ("000000".equals(historySchemeResultBean.getCode())) {
            if (SDCardUtil.isNullOrEmpty(historySchemeResultBean.getBizData())) {
                if (isFirst) {
                    rvOrder.setVisibility(View.VISIBLE);
                    ryId.setVisibility(View.GONE);
                } else {
                    rvOrder.setVisibility(View.GONE);
                    ryId.setVisibility(View.VISIBLE);
                }
                cowClaimPastRecordAdapter.loadMoreEnd();
            } else {
                isFirst = false;
                objectList.addAll(historySchemeResultBean.getBizData());
                cowClaimPastRecordAdapter.setNewData(this.objectList);
                cowClaimPastRecordAdapter.loadMoreComplete();
            }
        } else {
            showToast(historySchemeResultBean.getMessage());
        }
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
    }

    @Override
    protected void onDestroy() {
        if (buyCowsSchemePresenter != null) {
            buyCowsSchemePresenter.detachView();
        }
        super.onDestroy();
    }
}
