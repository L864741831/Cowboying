package com.ibeef.cowboying.view.activity;

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
import com.ibeef.cowboying.adapter.AssetDetailListAdapter;
import com.ibeef.cowboying.base.IncomeInfoBase;
import com.ibeef.cowboying.bean.IncomeInfoResultBean;
import com.ibeef.cowboying.bean.WalletRecordResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.IncomeInfoPresenter;
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
 * 资产明细
 */
public class AssetDetailActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener,IncomeInfoBase.IView {
    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.message_ry)
    RecyclerView messageRy;
    @Bind(R.id.swipe_ly)
    SwipeRefreshLayout swipeLy;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    @Bind(R.id.rv_order)
    RelativeLayout rvOrder;
    private AssetDetailListAdapter assetDetailListAdapter;
    private List<WalletRecordResultBean.BizDataBean> beanList;
    private String token;
    private IncomeInfoPresenter incomeInfoPresenter;
    private int page=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_detail);
        ButterKnife.bind(this);
        init();
    }
    private void init(){
        token= Hawk.get(HawkKey.TOKEN);
        info.setText("资产明细");
        messageRy.setLayoutManager(new LinearLayoutManager(this));
        beanList=new ArrayList<>();
        assetDetailListAdapter=new AssetDetailListAdapter(beanList,this,R.layout.item_assetdetail_notes);
        assetDetailListAdapter.setOnLoadMoreListener(this, messageRy);
        messageRy.setAdapter(assetDetailListAdapter);
        swipeLy.setColorSchemeResources(R.color.colorAccent);
        swipeLy.setOnRefreshListener(this);
        swipeLy.setEnabled(true);

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
        incomeInfoPresenter=new IncomeInfoPresenter(this);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        incomeInfoPresenter.getWalletRecord(reqData,page,"3");
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onRefresh() {
        page=1;
        isFirst = true;
        beanList.clear();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        incomeInfoPresenter.getWalletRecord(reqData,page,"3");
        swipeLy.setRefreshing(false);
    }

    @Override
    public void onLoadMoreRequested() {
        isMoreLoad = true;
        page += 1;
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        incomeInfoPresenter.getWalletRecord(reqData,page,"3");
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getMoneyInfo(IncomeInfoResultBean incomeInfeResultBean) {

    }

    @Override
    public void getWalletRecord(WalletRecordResultBean walletRecordResultBean) {
        if ("000000".equals(walletRecordResultBean.getCode())) {
            if (SDCardUtil.isNullOrEmpty(walletRecordResultBean.getBizData())) {
                if (isFirst) {
                    rvOrder.setVisibility(View.VISIBLE);
                    messageRy.setVisibility(View.GONE);
                } else {
                    rvOrder.setVisibility(View.GONE);
                    messageRy.setVisibility(View.VISIBLE);
                }
                assetDetailListAdapter.loadMoreEnd();
            } else {
                isFirst = false;
                beanList.addAll(walletRecordResultBean.getBizData());
                assetDetailListAdapter.setNewData(this.beanList);
                assetDetailListAdapter.loadMoreComplete();
            }
        } else {
            showToast(walletRecordResultBean.getMessage());
        }
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
    }

    @Override
    protected void onDestroy() {
        if(incomeInfoPresenter!=null){
            incomeInfoPresenter.detachView();
        }
        super.onDestroy();
    }
}
