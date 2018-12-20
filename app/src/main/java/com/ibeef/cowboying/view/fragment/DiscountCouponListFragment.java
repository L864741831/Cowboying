package com.ibeef.cowboying.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.DiscountCoupondapter;
import com.ibeef.cowboying.base.MyContractBase;
import com.ibeef.cowboying.bean.MyContractListBean;
import com.ibeef.cowboying.bean.MyContractURLBean;
import com.ibeef.cowboying.bean.MyDiscountCouponListBean;
import com.ibeef.cowboying.bean.PayCodeBean;
import com.ibeef.cowboying.bean.VipCardBean;
import com.ibeef.cowboying.bean.VipCardListBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.MyContractPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.ibeef.cowboying.view.activity.BuyCowsPlanActivity;
import com.ibeef.cowboying.view.activity.FightCattleActivity;
import com.ibeef.cowboying.view.activity.MainActivity;
import com.ibeef.cowboying.view.customview.SuperSwipeRefreshLayout;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rxfamily.view.BaseFragment;

/**
 * @author htgg
 */
public class DiscountCouponListFragment extends BaseFragment implements SuperSwipeRefreshLayout.OnPullRefreshListener,BaseQuickAdapter.RequestLoadMoreListener,MyContractBase.IView{

    private String token;
    private String stadus;
    private SuperSwipeRefreshLayout mSwipeLayout;
    private RecyclerView mRView;
    private RelativeLayout loadingLayout;
    private List<MyDiscountCouponListBean.BizDataBean> listData;
    private DiscountCoupondapter discountCoupondapter;
    private RelativeLayout rv_order;
    private int page=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    private MyContractPresenter myContractPresenter;


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mSwipeLayout = view.findViewById(R.id.swipe_layout);
        mSwipeLayout.setHeaderViewBackgroundColor(getHoldingActivity().getResources().getColor(R.color.colorAccent));
        mSwipeLayout.setHeaderView(createHeaderView());// add headerView
        mSwipeLayout.setTargetScrollWithLayout(true);
        mSwipeLayout.setOnPullRefreshListener(this);
        listData=new ArrayList<>();
        loadingLayout = view.findViewById(R.id.loading_layout);
        rv_order = view.findViewById(R.id.rv_order);
        mRView = view.findViewById(R.id.video_list_rv);
        mRView.setLayoutManager(new LinearLayoutManager(getHoldingActivity()));
        mRView.setHasFixedSize(true);
        mRView.setNestedScrollingEnabled(false);

        myContractPresenter=new MyContractPresenter(this);

        discountCoupondapter = new DiscountCoupondapter(listData,getHoldingActivity(),stadus);
        discountCoupondapter.setOnLoadMoreListener(this, mRView);
        mRView.setAdapter(discountCoupondapter);
        mRView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!mRView.canScrollVertically(-1)){
                    mSwipeLayout.setEnabled(true);
                }else {
                    mSwipeLayout.setEnabled(false);
                }
            }
        });


        discountCoupondapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyDiscountCouponListBean.BizDataBean item=discountCoupondapter.getItem(position);
                if(view.getId()==R.id.tv_commit_id){
                    if("1".equals(stadus)){
                        //getUseType 使用类型（0：全部；1：养牛；2：拼牛；3：商城；）
                        if("0".equals(item.getUseType())){
                            Intent intent1=new Intent(getHoldingActivity(),MainActivity.class);
                            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent1);
                        }else   if("1".equals(item.getUseType())){
                            Intent intent1=new Intent(getHoldingActivity(),BuyCowsPlanActivity.class);
                            startActivity(intent1);
                        }else   if("2".equals(item.getUseType())){
                            Intent intent1=new Intent(getHoldingActivity(),FightCattleActivity.class);
                            startActivity(intent1);
                        }else  if("3".equals(item.getUseType())){
                            if(SDCardUtil.isNullOrEmpty(item.getUseProductName())){
                                Intent intent1=new Intent(getHoldingActivity(),MainActivity.class);
                                intent1.putExtra("index",1);
                                startActivity(intent1);
                            }else {
                                Constant.PRODUCR_ID=item.getUseProductId();
                                Intent intent1=new Intent(getHoldingActivity(),MainActivity.class);
                                intent1.putExtra("index",1);
                                startActivity(intent1);
                            }

                        }

                    }
                }
            }
        });

    }

    public static DiscountCouponListFragment newInstance(String stadus) {
        DiscountCouponListFragment newFragment = new DiscountCouponListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("stadus", stadus);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        page = 1;
        token = Hawk.get(HawkKey.TOKEN);
        listData.clear();
        if (!TextUtils.isEmpty(token)) {
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization",token);
            reqData.put("version",getVersionCodes());
            myContractPresenter.getMyDiscountCouponList(reqData,page,10,stadus);
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_discount_coupon_list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            stadus = args.getString("stadus");
        }
    }

    @Override
    public void onLoadMoreRequested() {
        page+=1;
        isMoreLoad=true;

        if(!TextUtils.isEmpty(token)){
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization",token);
            reqData.put("version",getVersionCodes());
            myContractPresenter.getMyDiscountCouponList(reqData,page,10,stadus);
        }
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void showLoading() {
        if(isMoreLoad){
            loadingLayout.setVisibility(View.GONE);
            mRView.setVisibility(View.VISIBLE);
            isMoreLoad=false;
        }else {
            loadingLayout.setVisibility(View.VISIBLE);
            mRView.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        mRView.setVisibility(View.VISIBLE);
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void getMyContrantList(MyContractListBean myContractListBean) {

    }

    @Override
    public void getMyContrantURL(MyContractURLBean myContractURLBean) {

    }

    @Override
    public void getMyDiscountCouponList(MyDiscountCouponListBean myDiscountCouponListBean) {
        if ("000000".equals(myDiscountCouponListBean.getCode())) {
            if (SDCardUtil.isNullOrEmpty(myDiscountCouponListBean.getBizData())) {
                if (isFirst) {
                    rv_order.setVisibility(View.VISIBLE);
                    mRView.setVisibility(View.GONE);
                } else {
                    rv_order.setVisibility(View.GONE);
                    mRView.setVisibility(View.VISIBLE);
                }
                discountCoupondapter.loadMoreEnd();
            } else {
                isFirst = false;
                listData.addAll(myDiscountCouponListBean.getBizData());
                discountCoupondapter.setNewData(this.listData);
                discountCoupondapter.loadMoreComplete();
            }
        } else {
            showToast(myDiscountCouponListBean.getMessage());
        }
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
    public void onDestroy() {
        if(myContractPresenter!=null){
            myContractPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        page = 1;
        isFirst = true;
        listData.clear();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization", token);
        reqData.put("version", getVersionCodes());
        myContractPresenter.getMyDiscountCouponList(reqData,page,10,stadus);

    }

    @Override
    public void onPullDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {

    }
}
