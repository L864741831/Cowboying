package com.ibeef.cowboying.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.HomeProductListAdapter;
import com.ibeef.cowboying.adapter.RanchDynamicsAdapter;
import com.ibeef.cowboying.base.HomeBannerBase;
import com.ibeef.cowboying.bean.HomeAllVideoResultBean;
import com.ibeef.cowboying.bean.HomeBannerResultBean;
import com.ibeef.cowboying.bean.HomeSellCowNumResultBean;
import com.ibeef.cowboying.bean.HomeVideoResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.HomeBannerPresenter;
import com.ibeef.cowboying.utils.DateUtils;
import com.ibeef.cowboying.utils.GlideImageLoader;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.ibeef.cowboying.view.activity.AdWebviewActivity;
import com.ibeef.cowboying.view.activity.BuyCowsPlanActivity;
import com.ibeef.cowboying.view.activity.CowsClaimActivity;
import com.ibeef.cowboying.view.activity.DiscountCouponActivity;
import com.ibeef.cowboying.view.activity.FightCattleActivity;
import com.ibeef.cowboying.view.activity.GivePoursActivity;
import com.ibeef.cowboying.view.activity.MainActivity;
import com.ibeef.cowboying.view.activity.MyAllMoneyActivity;
import com.ibeef.cowboying.view.activity.MyContractActivity;
import com.ibeef.cowboying.view.activity.MyCowsActivity;
import com.ibeef.cowboying.view.activity.MyCowsDetailActivity;
import com.ibeef.cowboying.view.activity.MyOrderActivity;
import com.ibeef.cowboying.view.activity.MyOrderDetailActivity;
import com.ibeef.cowboying.view.activity.NewManwelfareActivity;
import com.ibeef.cowboying.view.activity.PlayerVideoActivity;
import com.ibeef.cowboying.view.activity.RanchConsociationActivity;
import com.ibeef.cowboying.view.activity.RanchDynamicActivity;
import com.ibeef.cowboying.view.activity.StoreSureOderActivity;
import com.ibeef.cowboying.view.customview.SuperSwipeRefreshLayout;
import com.orhanobut.hawk.Hawk;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rxfamily.view.BaseFragment;

public class HomeFragment extends BaseFragment implements SuperSwipeRefreshLayout.OnPullRefreshListener,View.OnClickListener,HomeBannerBase.IView  {

    RecyclerView homeRyId;
    SuperSwipeRefreshLayout swipeLy;
    private Banner banner;
    private HomeProductListAdapter homeProductListAdapter;
    private List<Object> objectList;
    private TextView sellCowNumId;
    private TextView sellCowNum2Id;

    private Banner specialbeefImgId;
    private ImageView newpeopleImgId;
    private ImageView ranchconsociationImgId;
    private FrameLayout buyCowFm;
    private FrameLayout togetherCowFm;
    private RecyclerView ranchDynamicsRy;
    private RanchDynamicsAdapter ranchDynamicsAdapter;
    private List<HomeVideoResultBean.BizDataBean> beanList;
    private HomeBannerPresenter homeBannerPresenter;
    //判断弹框
    private SharedPreferences mPrefDailog;
    public static final String KEY_HISTORY_KEYWORD = "key_mPrefDailogtimes";
    public String history;
    private boolean isFirstAdDialog=true;
    private RelativeLayout rv_show_id;
    private String token;
    private HomeBannerResultBean homeBannerResultBean;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        swipeLy=view.findViewById(R.id.swipe_ly);
        homeRyId=view.findViewById(R.id.home_ry_id);
        rv_show_id=view.findViewById(R.id.rv_show_id);

        homeRyId.setLayoutManager(new LinearLayoutManager(getHoldingActivity()));
        swipeLy.setHeaderViewBackgroundColor(getHoldingActivity().getResources().getColor(R.color.colorAccent));
        swipeLy.setHeaderView(createHeaderView());// add headerView
        swipeLy.setTargetScrollWithLayout(true);
        swipeLy.setOnPullRefreshListener(this);

        objectList=new ArrayList<>();
        homeProductListAdapter=new HomeProductListAdapter(objectList,getHoldingActivity(),R.layout.activity_ad_webview);
        View headView = View.inflate(getHoldingActivity(), R.layout.home_head_view, null);
        init(headView);
        //添加头视图
        homeProductListAdapter.addHeaderView(headView);
        homeRyId.setAdapter(homeProductListAdapter);

        token= Hawk.get(HawkKey.TOKEN);
        homeBannerPresenter=new HomeBannerPresenter(this);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        homeBannerPresenter.getHomeBanner(reqData);
        homeBannerPresenter.getHomeVideo(reqData);
        homeBannerPresenter.getHomeSellCowsNum(reqData);
        mPrefDailog = getHoldingActivity().getSharedPreferences("firstopenDailogs", Activity.MODE_PRIVATE);
        history= mPrefDailog.getString(KEY_HISTORY_KEYWORD, "");

        homeRyId.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!homeRyId.canScrollVertically(-1)){
                    swipeLy.setEnabled(true);
                    rv_show_id.setVisibility(View.GONE);
                }else {
                    swipeLy.setEnabled(false);
                    rv_show_id.setVisibility(View.VISIBLE);
                }

            }
        });


    }


    @Override
    public void onResume() {
        super.onResume();
        token= Hawk.get(HawkKey.TOKEN);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    /**
     * 头视图
     * @param view
     */
    public void init(View view) {
        /**
         * 轮播图
         */
        banner = view.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置banner样式
        banner.setImageLoader(new GlideImageLoader());
        //设置图片加载器
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置banner动画效果
        banner.isAutoPlay(true);
        //设置自动轮播，默认为true
        banner.setDelayTime(1000 * 3);
        //设置轮播时间
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setClickable(true);
        banner.setBannerAnimation(Transformer.Accordion);

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if(SDCardUtil.isNullOrEmpty(homeBannerResultBean.getBizData().getTopBannerList())){
                    return;
                }
                switch (homeBannerResultBean.getBizData().getTopBannerList().get(position).getPageUrl()){
                    case "adopt_scheme_list":
                        //买牛方案列表
                        startActivity(BuyCowsPlanActivity.class);
                        break;
                    case "adopt_scheme_detail":
                        //买牛方案详情
                        Intent intent4=new Intent(getHoldingActivity(),CowsClaimActivity.class);
                        intent4.putExtra("schemId",Integer.parseInt(homeBannerResultBean.getBizData().getPopBanner().getParams()));
                        startActivity(intent4);
                        break;
                    case "adop_order_list":
                        //买牛订单列表
                        startActivity(MyCowsActivity.class);
                        break;
                    case "adop_order_detail":
                        //买牛订单详情
                        Intent intent = new Intent(getHoldingActivity(), MyCowsDetailActivity.class);
                        intent.putExtra("orderId",homeBannerResultBean.getBizData().getPopBanner().getParams()+"");
                        startActivity(intent);
                        break;
                    case "pasture_list":
                        //合作牧场列表
                        startActivity(RanchConsociationActivity.class);
                        break;
                    case "shop_product_list":
                        //商城商品列表
                        Intent intent1=new Intent(getHoldingActivity(),MainActivity.class);
                        intent1.putExtra("index",1);
                        startActivity(intent1);
                        break;
                    case "shop_order_list":
                        //商城订单列表
                        startActivity(MyOrderActivity.class);
                        break;
                    case "shop_order_detail":
                        //商城订单详情
                        Intent intent2 = new Intent(getHoldingActivity(), MyOrderDetailActivity.class);
                        intent2.putExtra("orderId",homeBannerResultBean.getBizData().getPopBanner().getParams()+"");
                        startActivity(intent2);
                        break;
                    case "total_assets":
                        //总资产
                        startActivity(MyAllMoneyActivity.class);
                        break;
                    case "coupon_list":
                        //优惠券列表
                        startActivity(DiscountCouponActivity.class);
                        break;
                    case "contract_list":
                        //合同列表
                        startActivity(MyContractActivity.class);
                        break;
                    case "new_welfare":
                        //新人福利
                        Intent intent3=new Intent(getHoldingActivity(),NewManwelfareActivity.class);
                        intent3.putExtra("infos",homeBannerResultBean.getBizData().getNewUserActivity());
                        startActivity(intent3);
                        break;
                    default:
                        break;
                }
            }
        });
        sellCowNumId=view.findViewById(R.id.sell_cow_num_id);
        sellCowNum2Id=view.findViewById(R.id.sell_cow_num2_id);
        specialbeefImgId=view.findViewById(R.id.specialbeef_img_id);
        ranchconsociationImgId=view.findViewById(R.id.ranchconsociation_img_id);
        buyCowFm=view.findViewById(R.id.buy_cow_fm);
        togetherCowFm=view.findViewById(R.id.together_cow_fm);
        newpeopleImgId=view.findViewById(R.id.newpeople_img_id);
        ranchDynamicsRy=view.findViewById(R.id.ranch_dynamics_ry);
        ranchDynamicsRy.setLayoutManager(new LinearLayoutManager(getHoldingActivity(), LinearLayoutManager.HORIZONTAL, false));
        ranchconsociationImgId.setOnClickListener(this);
        buyCowFm.setOnClickListener(this);
        togetherCowFm.setOnClickListener(this);
        newpeopleImgId.setOnClickListener(this);

        specialbeefImgId.setBannerStyle(BannerConfig.NOT_INDICATOR);
        //设置banner样式
        specialbeefImgId.setImageLoader(new GlideImageLoader());
        //设置图片加载器
        specialbeefImgId.setBannerAnimation(Transformer.DepthPage);
        //设置banner动画效果
        specialbeefImgId.isAutoPlay(false);
//        specialbeefImgId.setDelayTime(1000 * 6);
        //设置轮播时间
        specialbeefImgId.setClickable(true);

        specialbeefImgId.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent1=new Intent(getHoldingActivity(),MainActivity.class);
                intent1.putExtra("index",1);
                startActivity(intent1);
            }
        });

        beanList=new ArrayList<>();

        ranchDynamicsAdapter=new RanchDynamicsAdapter(beanList,getHoldingActivity(),R.layout.ranch_dynamics_item);
        ranchDynamicsRy.setAdapter(ranchDynamicsAdapter);

        ranchDynamicsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeVideoResultBean.BizDataBean item=ranchDynamicsAdapter.getItem(position);
                if(position==beanList.size()-1){
                    startActivity(RanchDynamicActivity.class);
                }else {
                    Intent intent = new Intent(getHoldingActivity(), PlayerVideoActivity.class);
                    intent.putExtra("video_url",item.getPlayUrl());
                    intent.putExtra("title",item.getName());
                    intent.putExtra("coverUrl",item.getCoverUrl());
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buy_cow_fm:
                //买牛方案列表
                startActivity(BuyCowsPlanActivity.class);
                break;
            case R.id.together_cow_fm:
                //拼牛
                startActivity(FightCattleActivity.class);
                break;
            case R.id.newpeople_img_id:
                //新人礼券
                Intent intent=new Intent(getHoldingActivity(),NewManwelfareActivity.class);
                intent.putExtra("infos",homeBannerResultBean.getBizData().getNewUserActivity());
                startActivity(intent);
                break;
            case R.id.ranchconsociation_img_id:
                //合作牧场
                startActivity(RanchConsociationActivity.class);
                break;
            default:
                break;
        }
    }


    @Override
    public void showMsg(String msg) {
    }


    @Override
    public void getHomeBanner(final HomeBannerResultBean homeBannerResultBean) {
        this.homeBannerResultBean=homeBannerResultBean;
        swipeLy.setRefreshing(false);
        //新人专享
        if(!SDCardUtil.isNullOrEmpty(homeBannerResultBean.getBizData().getNewUserActivity())) {
            RequestOptions options = new RequestOptions()
                    .skipMemoryCache(true)
                    .error(R.mipmap.newpeople)
                    //跳过内存缓存
                    ;
            Glide.with(getHoldingActivity()).load(Constant.imageDomain+homeBannerResultBean.getBizData().getNewUserActivity().getHomeBanner()).apply(options).into(newpeopleImgId);

            if(!SDCardUtil.isNullOrEmpty(homeBannerResultBean.getBizData().getNewUserActivity().getSchemeId())){
                newpeopleImgId.setVisibility(View.VISIBLE);
            }else {
                newpeopleImgId.setVisibility(View.GONE);
            }
        }else {
            newpeopleImgId.setVisibility(View.GONE);
        }

        //顶部banner
        if(!SDCardUtil.isNullOrEmpty(homeBannerResultBean.getBizData().getTopBannerList())){
            ArrayList<String> imgStr = new ArrayList<>();
            for (int i=0;i<homeBannerResultBean.getBizData().getTopBannerList().size();i++){
                imgStr.add(Constant.imageDomain+homeBannerResultBean.getBizData().getTopBannerList().get(i).getImageUrl());
            }
            banner.setImages(imgStr);
            //设置图片集合
            banner.start();

        }

        //中间轮播banner
        if(!SDCardUtil.isNullOrEmpty(homeBannerResultBean.getBizData().getMiddleBannerList())){
            ArrayList<String> imgStr1 = new ArrayList<>();
            for (int i=0;i<homeBannerResultBean.getBizData().getMiddleBannerList().size();i++){
                imgStr1.add(Constant.imageDomain+homeBannerResultBean.getBizData().getMiddleBannerList().get(i).getImageUrl());
            }
            specialbeefImgId.setImages(imgStr1);
            //设置图片集合
            specialbeefImgId.start();
        }

        //一天一次的广告弹框
        if(!SDCardUtil.isNullOrEmpty(homeBannerResultBean.getBizData().getPopBanner())){
            if(isFirstAdDialog){
                isFirstAdDialog=false;
                if(TextUtils.isEmpty(history)){
                    SharedPreferences.Editor editor = mPrefDailog.edit();
                    editor.putString(KEY_HISTORY_KEYWORD, DateUtils.getTime(new Date()));
                    editor.commit();
                    Intent intent=new Intent(getHoldingActivity(),GivePoursActivity.class);
                    intent.putExtra("info",homeBannerResultBean);
                    startActivity(intent);
                }else {
                    if(!DateUtils.getTime(new Date()).equals(history)){
                        SharedPreferences.Editor editor = mPrefDailog.edit();
                        editor.putString(KEY_HISTORY_KEYWORD, DateUtils.getTime(new Date()));
                        editor.commit();
                        Intent intent=new Intent(getHoldingActivity(),GivePoursActivity.class);
                        intent.putExtra("info",homeBannerResultBean);
                        startActivity(intent);
                    }
                }
            }
        }

    }

    @Override
    public void getHomeVideo(HomeVideoResultBean homeAdResultBean) {
        beanList.addAll(homeAdResultBean.getBizData());

        HomeVideoResultBean  homeVideoResultBean=new HomeVideoResultBean();
        HomeVideoResultBean.BizDataBean bizDataBean=homeVideoResultBean.new BizDataBean();
        bizDataBean.setCoverUrl("1");

        beanList.add(bizDataBean);
        ranchDynamicsAdapter.setNewData(this.beanList);
        ranchDynamicsAdapter.loadMoreEnd();
    }

    @Override
    public void getHomeSellCowsNum(HomeSellCowNumResultBean homeSellCowNumResultBean) {
        if("000000".equals(homeSellCowNumResultBean.getCode())){
            sellCowNumId.setText(homeSellCowNumResultBean.getBizData().getTotalSalesQuantity()+"");
            sellCowNum2Id.setText(homeSellCowNumResultBean.getBizData().getTotalUserQuantity()+"");
        }else {
            showToast(homeSellCowNumResultBean.getMessage());
        }

    }

    @Override
    public void getAllVideo(HomeAllVideoResultBean homeAdResultBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onDestroy() {
        if(homeBannerPresenter!=null){
            homeBannerPresenter.detachView();
        }

        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        beanList.clear();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        homeBannerPresenter.getHomeBanner(reqData);
        homeBannerPresenter.getHomeVideo(reqData);
        homeBannerPresenter.getHomeSellCowsNum(reqData);
    }

    @Override
    public void onPullDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {

    }
}
