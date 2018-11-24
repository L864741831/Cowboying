package com.ibeef.cowboying.view.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.DiscountCoupondapter;
import com.ibeef.cowboying.base.MyContractBase;
import com.ibeef.cowboying.bean.MyContractListBean;
import com.ibeef.cowboying.bean.MyContractURLBean;
import com.ibeef.cowboying.bean.MyDiscountCouponListBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.MyContractPresenter;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rxfamily.bean.BaseBean;
import rxfamily.view.BaseFragment;

/**
 * @author htgg
 */
public class DiscountCouponListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener,MyContractBase.IView{

    private String token;
    private String stadus;
    private SwipeRefreshLayout mSwipeLayout;
    private RecyclerView mRView;
    private RelativeLayout loadingLayout;
    private List<BaseBean> listData;
    private DiscountCoupondapter discountCoupondapter;
    private RelativeLayout rv_order;
    private int page=1;
    private boolean isMoreLoad=false;
    private MyContractPresenter myContractPresenter;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mSwipeLayout = view.findViewById(R.id.swipe_layout);
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setEnabled(true);
        listData=new ArrayList<>();
        for (int i=0;i<10;i++){
            BaseBean baseBean=new BaseBean();
            listData.add(baseBean);
        }
        loadingLayout = view.findViewById(R.id.loading_layout);
        rv_order = view.findViewById(R.id.rv_order);
        mRView = view.findViewById(R.id.video_list_rv);
        mRView.setLayoutManager(new LinearLayoutManager(getHoldingActivity()));
        mRView.setHasFixedSize(true);
        mRView.setNestedScrollingEnabled(false);

        myContractPresenter=new MyContractPresenter(this);

        discountCoupondapter = new DiscountCoupondapter(listData,getHoldingActivity());
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
    public void onRefresh() {
        mSwipeLayout.setRefreshing(false);
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
    }

    @Override
    public void getMyContrantList(MyContractListBean myContractListBean) {

    }

    @Override
    public void getMyContrantURL(MyContractURLBean myContractURLBean) {

    }

    @Override
    public void getMyDiscountCouponList(MyDiscountCouponListBean myDiscountCouponListBean) {

    }
}
