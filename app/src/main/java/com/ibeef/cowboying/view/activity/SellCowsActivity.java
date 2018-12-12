package com.ibeef.cowboying.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.SellCowsAdapter;
import com.ibeef.cowboying.base.MyCowsOrderBase;
import com.ibeef.cowboying.base.MyCowsOrderDeleteBean;
import com.ibeef.cowboying.bean.CreatOderResultBean;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListDetailBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.MyCowsOrderPresenter;
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

public class SellCowsActivity extends BaseActivity  implements SuperSwipeRefreshLayout.OnPullRefreshListener,BaseQuickAdapter.RequestLoadMoreListener,MyCowsOrderBase.IView{
    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.ry_id)
    RecyclerView ryId;
    @Bind(R.id.swipe_ly)
    SuperSwipeRefreshLayout mSwipeLayout;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    @Bind(R.id.rv_order)
    RelativeLayout rvOrder;
    @Bind(R.id.rv_bottom_id)
    RelativeLayout rvBottomId;
    @Bind(R.id.all_cownum_id)
    TextView allCownumId;
    @Bind(R.id.to_sell_cows)
    TextView toSellCows;
    private SellCowsAdapter sellCowsAdapter;
    private List<MyCowsOrderListBean.BizDataBean> baseBeans;
    private String token;

    private int currentPage=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    private MyCowsOrderPresenter myCowsOrderPresenter;
    private BroadcastReceiver receiver;
    private MyCowsOrderListBean myCowsOrderListBean;
    private String  orderId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_cows);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        token = Hawk.get(HawkKey.TOKEN);
        info.setText("我要卖牛");
        baseBeans = new ArrayList<>();
        ryId.setLayoutManager(new LinearLayoutManager(this));
        sellCowsAdapter = new SellCowsAdapter( baseBeans,this, R.layout.item_sell_cows);
        sellCowsAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(sellCowsAdapter);

        mSwipeLayout.setHeaderViewBackgroundColor(getResources().getColor(R.color.colorAccent));
        mSwipeLayout.setHeaderView(createHeaderView());// add headerView
        mSwipeLayout.setTargetScrollWithLayout(true);
        mSwipeLayout.setOnPullRefreshListener(this);

        myCowsOrderPresenter = new MyCowsOrderPresenter(this);
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

        IntentFilter intentFilter = new IntentFilter("com.ibeef.cowboying");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
               int num=intent.getIntExtra("num",0);
                allCownumId.setText("合计牛只数："+num);
               int position=intent.getIntExtra("position",0);
               int outposition=intent.getIntExtra("outposition",0);
               orderId=intent.getStringExtra("orderId");
               for (int i=0;i<myCowsOrderListBean.getBizData().size();i++){
                   for (int j=0;j<myCowsOrderListBean.getBizData().get(i).getCattleList().size();j++){
                       if(outposition==i&&position==j){
                           myCowsOrderListBean.getBizData().get(i).getCattleList().get(j).setDefautChoose(1);
                       }else {
                           myCowsOrderListBean.getBizData().get(i).getCattleList().get(j).setDefautChoose(0);
                       }

                   }
               }
                sellCowsAdapter.notifyDataSetChanged();
            }
        };
        registerReceiver(receiver, intentFilter);

    }


    @Override
    protected void onResume() {
        super.onResume();
        currentPage=1;
        isFirst=true;
        isMoreLoad=false;
        baseBeans.clear();
        allCownumId.setText("合计牛只数：0");
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization", token);
        reqData.put("version", getVersionCodes());
        myCowsOrderPresenter.geMyCowsOrderList(reqData, currentPage,"3");


    }

    @OnClick({R.id.back_id, R.id.now_claim_btn_id,R.id.to_sell_cows})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.to_sell_cows:
                finish();
                break;
            case R.id.now_claim_btn_id:
                if(TextUtils.isEmpty(orderId)){
                    showToast("请先选择一头您要卖的牛！");
                }else {
                    Intent intent=new Intent(SellCowsActivity.this,SellCowsFirstActivity.class);
                    intent.putExtra("orderId",orderId);
                    startActivity(intent);
                    orderId="";
                }
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
        reqData.put("Authorization", token);
        reqData.put("version", getVersionCodes());
        myCowsOrderPresenter.geMyCowsOrderList(reqData, currentPage,"3");

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
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void geMyCowsOrderList(MyCowsOrderListBean myCowsOrderListBean) {
        if ("000000".equals(myCowsOrderListBean.getCode())) {
            if (SDCardUtil.isNullOrEmpty(myCowsOrderListBean.getBizData())) {
                if (isFirst) {
                    rvOrder.setVisibility(View.VISIBLE);
                    ryId.setVisibility(View.GONE);
                    rvBottomId.setVisibility(View.GONE);
                } else {
                    rvOrder.setVisibility(View.GONE);
                    ryId.setVisibility(View.VISIBLE);
                    rvBottomId.setVisibility(View.VISIBLE);
                }
                sellCowsAdapter.loadMoreEnd();
            } else {
                isFirst = false;
                baseBeans.addAll(myCowsOrderListBean.getBizData());
                this.myCowsOrderListBean=myCowsOrderListBean;
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
    public void getMyCowsOrderDelete(MyCowsOrderDeleteBean myCowsOrderDeleteBean) {

    }

    @Override
    public void getMyCowsOrderCancel(MyCowsOrderDeleteBean myCowsOrderDeleteBean) {

    }

    @Override
    public void getMyCowsToPay(CreatOderResultBean creatOderResultBean) {

    }

    @Override
    protected void onDestroy() {
        if(myCowsOrderPresenter!=null){
            myCowsOrderPresenter.detachView();
        }
        super.onDestroy();
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
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
    }

    @Override
    public void onPullDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {

    }
}
