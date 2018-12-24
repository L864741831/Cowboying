package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.PickUpCodeAdapter;
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
 * 取货码
 *
 * @author Administrator
 */
public class PickUpCodeActivity extends BaseActivity implements SuperSwipeRefreshLayout.OnPullRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, MyOrderListBase.IView {

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
    @Bind(R.id.ll_bg)
    LinearLayout llBg;
    private String token;
    private List<MyOrderListBean.BizDataBean> listData;
    private PickUpCodeAdapter pickUpCodeAdapter;
    private MyOrderListPresenter myOrderListPresenter;
    private int page = 1;
    private boolean isFirst = true;
    private boolean isMoreLoad = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up_code);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        token = Hawk.get(HawkKey.TOKEN);
        info.setText("取货码");
        listData = new ArrayList<>();
        listData.clear();
        swipeLayout.setHeaderViewBackgroundColor(getResources().getColor(R.color.colorAccent));
        swipeLayout.setHeaderView(createHeaderView());// add headerView
        swipeLayout.setTargetScrollWithLayout(true);
        swipeLayout.setOnPullRefreshListener(this);
        ryId.setLayoutManager(new LinearLayoutManager(this));
        pickUpCodeAdapter = new PickUpCodeAdapter(listData, this, R.layout.item_my_pick_up_code);
        pickUpCodeAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(pickUpCodeAdapter);
        pickUpCodeAdapter.loadMoreEnd();
        ryId.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!ryId.canScrollVertically(-1)) {
                    swipeLayout.setEnabled(true);
                } else {
                    swipeLayout.setEnabled(false);
                }
            }
        });
        myOrderListPresenter = new MyOrderListPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        page = 1;
        token = Hawk.get(HawkKey.TOKEN);
        listData.clear();
        if (!TextUtils.isEmpty(token)) {
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization", token);
            reqData.put("version", getVersionCodes());
            myOrderListPresenter.getMyOrderList(reqData, page, "7");
        }
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onRefresh() {
        page = 1;
        listData.clear();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization", token);
        reqData.put("version", getVersionCodes());
        myOrderListPresenter.getMyOrderList(reqData, page, "7");
    }

    @Override
    public void onPullDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {

    }

    @Override
    public void onLoadMoreRequested() {
        isMoreLoad = true;
        page += 1;
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization", token);
        reqData.put("version", getVersionCodes());
        myOrderListPresenter.getMyOrderList(reqData, page, "7");
    }

    @Override
    public void showMsg(String msg) {

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
        swipeLayout.setRefreshing(false);
    }

    @Override
    public void getMyOrderList(MyOrderListBean myOrderListBean) {
        if (myOrderListBean.getPageNo() == 1 && SDCardUtil.isNullOrEmpty(myOrderListBean.getBizData())) {
            rvBg.setVisibility(View.VISIBLE);
            ryId.setVisibility(View.GONE);
            llBg.setBackgroundColor(getResources().getColor(R.color.white));
            return;
        } else {
            if (SDCardUtil.isNullOrEmpty(myOrderListBean.getBizData())) {
                pickUpCodeAdapter.loadMoreEnd();
                return;
            }
        }
        this.listData.addAll(myOrderListBean.getBizData());

        rvBg.setVisibility(View.GONE);
        ryId.setVisibility(View.VISIBLE);

        if (SDCardUtil.isNullOrEmpty(myOrderListBean.getBizData())) {
            pickUpCodeAdapter.loadMoreEnd();
        } else {
            pickUpCodeAdapter.setNewData(this.listData);
            pickUpCodeAdapter.loadMoreComplete();
        }
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

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(myOrderListPresenter!=null){
            myOrderListPresenter.detachView();
        }
    }
}
