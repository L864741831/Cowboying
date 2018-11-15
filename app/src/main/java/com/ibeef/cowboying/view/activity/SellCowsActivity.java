package com.ibeef.cowboying.view.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MyFeedbackAdapter;
import com.ibeef.cowboying.adapter.SellCowsAdapter;
import com.ibeef.cowboying.base.FeedbackBase;
import com.ibeef.cowboying.base.MyCowsOrderBase;
import com.ibeef.cowboying.base.SellCowsBase;
import com.ibeef.cowboying.bean.CreatSellCowsResultBean;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListDetailBean;
import com.ibeef.cowboying.bean.MyFeedbackResultBean;
import com.ibeef.cowboying.bean.SellCowsResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.FeedbackPresenter;
import com.ibeef.cowboying.presenter.MyCowsOrderPresenter;
import com.ibeef.cowboying.presenter.SellCowsPresenter;
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

public class SellCowsActivity extends BaseActivity  implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener,MyCowsOrderBase.IView ,SellCowsBase.IView {
    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.ry_id)
    RecyclerView ryId;
    @Bind(R.id.swipe_ly)
    SwipeRefreshLayout mSwipeLayout;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    @Bind(R.id.rv_order)
    RelativeLayout rvOrder;
    private SellCowsAdapter sellCowsAdapter;
    private List<MyCowsOrderListBean.BizDataBean> baseBeans;
    private SellCowsPresenter sellCowsPresenter;
    private String token;

    private int currentPage=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    private MyCowsOrderPresenter myCowsOrderPresenter;
    private String orderId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_cows);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        orderId=getIntent().getStringExtra("orderId");
        token = Hawk.get(HawkKey.TOKEN);
        info.setText("我要卖牛");
        baseBeans = new ArrayList<>();
        ryId.setLayoutManager(new LinearLayoutManager(this));
        sellCowsAdapter = new SellCowsAdapter( baseBeans,this, R.layout.my_feedback_item);
        sellCowsAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(sellCowsAdapter);
        mSwipeLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setEnabled(true);

        sellCowsPresenter = new SellCowsPresenter(this);
        myCowsOrderPresenter = new MyCowsOrderPresenter(this);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization", token);
        reqData.put("version", getVersionCodes());
        myCowsOrderPresenter.geMyCowsOrderList(reqData, currentPage,"3");

        ryId.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!ryId.canScrollVertically(-1)){
                    mSwipeLayout.setEnabled(true);
                }else {
                    mSwipeLayout.setEnabled(false);
                }
            }
        });
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization", token);
        reqData.put("version", getVersionCodes());
        sellCowsPresenter.getSellCows(reqData, orderId);
        finish();
    }
    @Override
    public void onRefresh() {
        currentPage = 1;
        isFirst = true;
        baseBeans.clear();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization", token);
        reqData.put("version", getVersionCodes());
        myCowsOrderPresenter.geMyCowsOrderList(reqData, currentPage,"3");

        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void onLoadMoreRequested() {
        isMoreLoad = true;
        currentPage += 1;
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization", token);
        reqData.put("version", getVersionCodes());
        myCowsOrderPresenter.geMyCowsOrderList(reqData, currentPage,"3");

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getSellCows(SellCowsResultBean sellCowsResultBean) {

    }

    @Override
    public void getCreatSellCows(CreatSellCowsResultBean creatSellCowsResultBean) {

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
    public void geMyCowsOrderList(MyCowsOrderListBean myCowsOrderListBean) {
        if ("000000".equals(myCowsOrderListBean.getCode())) {
            if (SDCardUtil.isNullOrEmpty(myCowsOrderListBean.getBizData())) {
                if (isFirst) {
                    rvOrder.setVisibility(View.VISIBLE);
                    ryId.setVisibility(View.GONE);
                } else {
                    rvOrder.setVisibility(View.GONE);
                    ryId.setVisibility(View.VISIBLE);
                }
                sellCowsAdapter.loadMoreEnd();
            } else {
                isFirst = false;
                baseBeans.addAll(myCowsOrderListBean.getBizData());
                sellCowsAdapter.setNewData(this.baseBeans);
                sellCowsAdapter.loadMoreComplete();
            }
        } else {
            showToast(myCowsOrderListBean.getMessage());
        }
    }

    @Override
    public void geMyCowsOrderListDetail(MyCowsOrderListDetailBean myCowsOrderListDetailBean) {

    }

    @Override
    protected void onDestroy() {
        if(sellCowsPresenter!=null){
            sellCowsPresenter.detachView();
        }
        if(myCowsOrderPresenter!=null){
            myCowsOrderPresenter.detachView();
        }
        super.onDestroy();
    }
}
