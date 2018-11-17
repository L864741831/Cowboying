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
import com.ibeef.cowboying.adapter.CashDespositListAdapter;
import com.ibeef.cowboying.base.CashMoneyBase;
import com.ibeef.cowboying.bean.CashMoneyRecordResultBean;
import com.ibeef.cowboying.bean.CashMoneyResultBean;
import com.ibeef.cowboying.bean.CashMoneyUserInfoResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.CashMoneyPresenter;
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
 * 提现记录
 */
public class CashDepositActivity extends BaseActivity  implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener, CashMoneyBase.IView{
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
    @Bind(R.id.title_show_id)
    TextView titleShowId;
    private CashDespositListAdapter cashDespositListAdapter;
    private List<CashMoneyRecordResultBean.BizDataBean> beanList;
    private String token;
    private int page=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    private CashMoneyPresenter cashMoneyPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_deposit);
        ButterKnife.bind(this);
        init();
    }
    private void init(){
        token= Hawk.get(HawkKey.TOKEN);
        info.setText("提现记录");
        messageRy.setLayoutManager(new LinearLayoutManager(this));
        messageRy.setHasFixedSize(true);
        messageRy.setNestedScrollingEnabled(false);
        beanList=new ArrayList<>();
        cashDespositListAdapter=new CashDespositListAdapter(beanList,this,R.layout.item_cash_deposit);
        cashDespositListAdapter.setOnLoadMoreListener(this, messageRy);
        messageRy.setAdapter(cashDespositListAdapter);
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
        cashMoneyPresenter=new CashMoneyPresenter(this);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        cashMoneyPresenter.getCashMoneyRecord(reqData,page);
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
        cashMoneyPresenter.getCashMoneyRecord(reqData,page);
        swipeLy.setRefreshing(false);
    }

    @Override
    public void onLoadMoreRequested() {
        isMoreLoad = true;
        page += 1;
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        cashMoneyPresenter.getCashMoneyRecord(reqData,page);
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getCashMoney(CashMoneyResultBean cashMoneyResultBean) {

    }

    @Override
    public void getCashMoneyRecord(CashMoneyRecordResultBean cashMoneyRecordResultBean) {
        if ("000000".equals(cashMoneyRecordResultBean.getCode())) {
            if (SDCardUtil.isNullOrEmpty(cashMoneyRecordResultBean.getBizData())) {
                if (isFirst) {
                    rvOrder.setVisibility(View.VISIBLE);
                    messageRy.setVisibility(View.GONE);
                    titleShowId.setVisibility(View.GONE);
                } else {
                    rvOrder.setVisibility(View.GONE);
                    messageRy.setVisibility(View.VISIBLE);
                    titleShowId.setVisibility(View.VISIBLE);
                }
                cashDespositListAdapter.loadMoreEnd();
            } else {
                isFirst = false;
                beanList.addAll(cashMoneyRecordResultBean.getBizData());
                cashDespositListAdapter.setNewData(this.beanList);
                cashDespositListAdapter.loadMoreComplete();
            }
        } else {
            showToast(cashMoneyRecordResultBean.getMessage());
        }
    }

    @Override
    public void getCashMoneyUserInfo(CashMoneyUserInfoResultBean cashMoneyUserInfoResultBean) {

    }

    @Override
    public void showLoading() {
        if (isMoreLoad) {
            loadingLayout.setVisibility(View.GONE);
            messageRy.setVisibility(View.VISIBLE);
            titleShowId.setVisibility(View.VISIBLE);
            isMoreLoad = false;
        } else {
            loadingLayout.setVisibility(View.VISIBLE);
            messageRy.setVisibility(View.GONE);
            titleShowId.setVisibility(View.GONE);

        }
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        messageRy.setVisibility(View.VISIBLE);
        titleShowId.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        if(cashMoneyPresenter!=null){
            cashMoneyPresenter.detachView();
        }
        super.onDestroy();
    }
}
