package com.ibeef.cowboying.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.HomeEatCowsAdapter;
import com.ibeef.cowboying.adapter.HomePastureAdapter;
import com.ibeef.cowboying.adapter.HomePastureMessegeAdapter;
import com.ibeef.cowboying.adapter.HomeProductListAdapter;
import com.ibeef.cowboying.adapter.RanchDynamicsAdapter;
import com.ibeef.cowboying.base.HomeBannerBase;
import com.ibeef.cowboying.base.HomePastureBase;
import com.ibeef.cowboying.bean.HomeAllVideoResultBean;
import com.ibeef.cowboying.bean.HomeBannerResultBean;
import com.ibeef.cowboying.bean.HomeBuyCowPlanResultBean;
import com.ibeef.cowboying.bean.HomeParstureMessegeResultBean;
import com.ibeef.cowboying.bean.HomeParstureResultBean;
import com.ibeef.cowboying.bean.HomeSellCowNumResultBean;
import com.ibeef.cowboying.bean.HomeVideoResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.HomeBannerPresenter;
import com.ibeef.cowboying.presenter.HomePasturePresenter;
import com.ibeef.cowboying.utils.DateUtils;
import com.ibeef.cowboying.utils.GlideImageLoader;
import com.ibeef.cowboying.utils.GlideImageLoaderAll;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.ibeef.cowboying.view.activity.AdWebviewActivity;
import com.ibeef.cowboying.view.activity.BuyCowsPlanActivity;
import com.ibeef.cowboying.view.activity.ChooseParsterActivity;
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
import com.ibeef.cowboying.view.activity.OfflineStoreActivity;
import com.ibeef.cowboying.view.activity.ParstureMessegeActivity;
import com.ibeef.cowboying.view.activity.PlayerVideoActivity;
import com.ibeef.cowboying.view.activity.RanchConsociationActivity;
import com.ibeef.cowboying.view.activity.RanchDynamicActivity;
import com.ibeef.cowboying.view.activity.VipCardActivity;
import com.ibeef.cowboying.view.customview.GlideBlurformation;
import com.ibeef.cowboying.view.customview.GlideRoundTransformAll;
import com.ibeef.cowboying.view.customview.SuperSwipeRefreshLayout;
import com.ibeef.cowboying.view.customview.ViewPagerLayoutManager;
import com.jaeger.library.StatusBarUtil;
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

public class HomeFragment extends BaseFragment implements SuperSwipeRefreshLayout.OnPullRefreshListener,View.OnClickListener,HomeBannerBase.IView,HomePastureBase.IView {

    private RecyclerView homeRyId,home_sellcows_ry,home_parsture_ry,parsture_messege_ry;
    private SuperSwipeRefreshLayout swipeLy;
    private Banner banner;
    private HomeProductListAdapter homeProductListAdapter;
    private List<Object> objectList;
    private TextView sellCowNumId;
    private TextView sellCowNum2Id;

    private Banner specialbeefImgId;
    private ImageView img_top_id,pasture_messege_img;
    private FrameLayout togetherCowFm;
    private RecyclerView ranchDynamicsRy;
    private LinearLayout lv_show_num_id;
    private TextView see_more_sellcows_id,see_more_pasture,see_pasture_stadus_id,see_pasture_messege_id,pasture_messege_title;
    private LinearLayout lv_parsture_messege,lv_parsture_stadus,lv_eat_cows,lv_our_pasture;
    private FrameLayout fm_parsture_messege;
    private RanchDynamicsAdapter ranchDynamicsAdapter;
    private List<HomeVideoResultBean.BizDataBean> beanList;
    private List<HomeBuyCowPlanResultBean.BizDataBean> eatcowsList;
    private List<HomeParstureResultBean.BizDataBean> homepastureList;
    private List<HomeParstureMessegeResultBean.BizDataBean> homepastureMessegeList;
    private HomeEatCowsAdapter homeEatCowsAdapter;
    private HomePastureAdapter homePastureAdapter;
    private HomePastureMessegeAdapter homePastureMessegeAdapter;
    private HomeBannerPresenter homeBannerPresenter;
    //判断弹框
    private SharedPreferences mPrefDailog;
    public static final String KEY_HISTORY_KEYWORD = "key_mPrefDailogtimes";
    public String history;
    private boolean isFirstAdDialog=true;
    private RelativeLayout rv_show_id;
    private String token;
    private HomeBannerResultBean homeBannerResultBean;
    private HomePasturePresenter homePasturePresenter;
    private ViewPagerLayoutManager mLayoutManager,mLayoutManager1;
    private HomeParstureMessegeResultBean homeParstureMessegeResultBean;
    private boolean isPause=false;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        swipeLy=view.findViewById(R.id.swipe_ly);
        homeRyId=view.findViewById(R.id.home_ry_id);
//        homeRyId.setHasFixedSize(true);
//        homeRyId.setNestedScrollingEnabled(false);
        rv_show_id=view.findViewById(R.id.rv_show_id);
        img_top_id=view.findViewById(R.id.img_top_id);
        homeRyId.setLayoutManager(new LinearLayoutManager(getHoldingActivity()));
        swipeLy.setHeaderViewBackgroundColor(getHoldingActivity().getResources().getColor(R.color.transparent));
        swipeLy.setHeaderView(createHeaderViewTrasport());// add headerView
        swipeLy.setTargetScrollWithLayout(true);
        swipeLy.setOnPullRefreshListener(this);

        objectList=new ArrayList<>();
        homeProductListAdapter=new HomeProductListAdapter(objectList,getHoldingActivity(),R.layout.activity_ad_webview);
        token= Hawk.get(HawkKey.TOKEN);
        homeBannerPresenter=new HomeBannerPresenter(this);

        View headView = View.inflate(getHoldingActivity(), R.layout.home_head_view, null);
        init(headView);
        //添加头视图
        homeProductListAdapter.addHeaderView(headView);
        homeRyId.setAdapter(homeProductListAdapter);

        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        homeBannerPresenter.getHomeBanner(reqData);
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
        isPause=false;
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
        lv_show_num_id=view.findViewById(R.id.lv_show_num_id);
        see_more_pasture=view.findViewById(R.id.see_more_pasture);
        see_pasture_stadus_id=view.findViewById(R.id.see_pasture_stadus_id);
        see_pasture_messege_id=view.findViewById(R.id.see_pasture_messege_id);
        pasture_messege_img=view.findViewById(R.id.pasture_messege_img);

        lv_parsture_messege=view.findViewById(R.id.lv_parsture_messege);
        lv_parsture_stadus=view.findViewById(R.id.lv_parsture_stadus);
        lv_eat_cows=view.findViewById(R.id.lv_eat_cows);
        lv_our_pasture=view.findViewById(R.id.lv_our_pasture);
        fm_parsture_messege=view.findViewById(R.id.fm_parsture_messege);

        pasture_messege_img.setOnClickListener(this);
        pasture_messege_title=view.findViewById(R.id.pasture_messege_title);
        see_pasture_stadus_id.setOnClickListener(this);//牧场动态查看更多
        see_pasture_messege_id.setOnClickListener(this);//牧场资讯查看更多
        see_more_pasture.setOnClickListener(this);//牧场查看更多
        see_more_sellcows_id=view.findViewById(R.id.see_more_sellcows_id);//产看更多养牛方案列表
        see_more_sellcows_id.setOnClickListener(this);
        banner = view.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置banner样式
        banner.setImageLoader(new GlideImageLoader());
        //设置图片加载器
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置banner动画效果
        banner.isAutoPlay(true);
        //设置自动轮播，默认为true
        banner.setDelayTime(1000 * 5);
        //设置轮播时间
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setClickable(true);
//        banner.setBannerAnimation(Transformer.DepthPage);
        banner.setBannerAnimation(Transformer.Default);
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if(!isPause){
                    SimpleTarget<Drawable> simpleTarget = new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            img_top_id.setBackground(resource);
                        }
                    };
                    if(getHoldingActivity()!=null&&!getHoldingActivity().isFinishing()){
                        if(position!=0){
                            if(position-1==homeBannerResultBean.getBizData().getTopBannerList().size()){
                                Glide.with(getHoldingActivity()).load(Constant.imageDomain+homeBannerResultBean.getBizData().getTopBannerList().get(0).getImageUrl()).apply(RequestOptions.bitmapTransform(new GlideBlurformation(getHoldingActivity()))).into(simpleTarget);
                            }else {
                                Glide.with(getHoldingActivity()).load(Constant.imageDomain+homeBannerResultBean.getBizData().getTopBannerList().get(position-1).getImageUrl()).apply(RequestOptions.bitmapTransform(new GlideBlurformation(getHoldingActivity()))).into(simpleTarget);
                            }
                        }
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if(SDCardUtil.isNullOrEmpty(homeBannerResultBean.getBizData().getTopBannerList())){
                    return;
                }
                if(TextUtils.isEmpty(homeBannerResultBean.getBizData().getTopBannerList().get(position).getPageUrl())){
                    return;
                }
                if(homeBannerResultBean.getBizData().getTopBannerList().get(position).getPageUrl().contains("http")){
                    Intent intent1=new Intent(getHoldingActivity(),AdWebviewActivity.class);
                    intent1.putExtra("url",homeBannerResultBean.getBizData().getTopBannerList().get(position).getPageUrl());
                    intent1.putExtra("title","");
                    startActivity(intent1);
                }else {
                    switch (homeBannerResultBean.getBizData().getTopBannerList().get(position).getPageUrl()) {
                        case "adopt_scheme_list":
                            //养牛方案列表
                            startActivity(BuyCowsPlanActivity.class);
                            break;
                        case "adopt_scheme_detail":
                            //养牛方案详情
                            if(!TextUtils.isEmpty(homeBannerResultBean.getBizData().getTopBannerList().get(position).getParams())){
                                Intent intent4 = new Intent(getHoldingActivity(), CowsClaimActivity.class);
                                intent4.putExtra("schemId", Integer.parseInt(homeBannerResultBean.getBizData().getTopBannerList().get(position).getParams()));
                                startActivity(intent4);
                            }
                            break;
                        case "adopt_order_list":
                            //养牛订单列表
                            startActivity(MyCowsActivity.class);
                            break;
                        case "adopt_order_detail":
                            //养牛订单详情
                            if(!TextUtils.isEmpty(homeBannerResultBean.getBizData().getTopBannerList().get(position).getParams())){
                                Intent intent3 = new Intent(getHoldingActivity(), MyCowsDetailActivity.class);
                                intent3.putExtra("orderId", homeBannerResultBean.getBizData().getTopBannerList().get(position).getParams() + "");
                                startActivity(intent3);
                            }
                            break;
                        case "pasture_list":
                            //合作牧场列表
                            startActivity(RanchConsociationActivity.class);
                            break;
                        case "shop_product_list":
                            //商城商品列表
                            Intent intent1 = new Intent(getHoldingActivity(), MainActivity.class);
                            intent1.putExtra("index", 1);
                            startActivity(intent1);
                            break;
                        case "shop_order_list":
                            //商城订单列表
                            startActivity(MyOrderActivity.class);
                            break;
                        case "shop_order_detail":
                            //商城订单详情
                            if(!TextUtils.isEmpty(homeBannerResultBean.getBizData().getTopBannerList().get(position).getParams())){
                                Intent intent7 = new Intent(getHoldingActivity(), MyOrderDetailActivity.class);
                                intent7.putExtra("orderId", homeBannerResultBean.getBizData().getTopBannerList().get(position).getParams() + "");
                                startActivity(intent7);
                            }
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
                        case "vip_card_detail":
                            //会员卡详情
                            startActivity(VipCardActivity.class);
                            break;
                        case "new_welfare":
                            //新人福利
                            if(SDCardUtil.isNullOrEmpty(homeBannerResultBean.getBizData().getNewUserActivity())){
                                return;
                            }
                            Intent intent11 = new Intent(getHoldingActivity(), NewManwelfareActivity.class);
                            intent11.putExtra("infos", homeBannerResultBean.getBizData().getNewUserActivity());
                            startActivity(intent11);
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        sellCowNumId=view.findViewById(R.id.sell_cow_num_id);
        sellCowNum2Id=view.findViewById(R.id.sell_cow_num2_id);
        specialbeefImgId=view.findViewById(R.id.specialbeef_img_id);
        togetherCowFm=view.findViewById(R.id.together_cow_fm);

        mLayoutManager = new ViewPagerLayoutManager(getHoldingActivity(), OrientationHelper.HORIZONTAL);
        mLayoutManager1 = new ViewPagerLayoutManager(getHoldingActivity(), OrientationHelper.HORIZONTAL);

        ranchDynamicsRy=view.findViewById(R.id.ranch_dynamics_ry);
        ranchDynamicsRy.setLayoutManager(mLayoutManager);

        //养牛赚钱
        home_sellcows_ry=view.findViewById(R.id.home_sellcows_ry);
        home_sellcows_ry.setLayoutManager(mLayoutManager1);
        eatcowsList=new ArrayList<>();
        homepastureList=new ArrayList<>();
        homepastureMessegeList=new ArrayList<>();

        homeEatCowsAdapter=new HomeEatCowsAdapter(eatcowsList,getHoldingActivity(),R.layout.item_home_eatcows);
        home_sellcows_ry.setAdapter(homeEatCowsAdapter);

        homeEatCowsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if("3".equals(eatcowsList.get(position).getType())){
                    //新人礼券
                    if(!SDCardUtil.isNullOrEmpty(homeBannerResultBean)){
                        if(!SDCardUtil.isNullOrEmpty(homeBannerResultBean.getBizData())){
                            if(!SDCardUtil.isNullOrEmpty(homeBannerResultBean.getBizData().getNewUserActivity())){
                                Intent intent=new Intent(getHoldingActivity(),NewManwelfareActivity.class);
                                intent.putExtra("infos",homeBannerResultBean.getBizData().getNewUserActivity());
                                startActivity(intent);
                            }
                        }
                    }

                }else {
                    Intent intent=new Intent(getHoldingActivity(),CowsClaimActivity.class);
                    intent.putExtra("schemId",eatcowsList.get(position).getSchemeId());
                    startActivity(intent);
                }
            }
        });

        //我们的牧场
        home_parsture_ry=view.findViewById(R.id.home_parsture_ry);
        home_parsture_ry.setLayoutManager(new LinearLayoutManager(getHoldingActivity(), LinearLayoutManager.VERTICAL, false));
        homePastureAdapter=new HomePastureAdapter(homepastureList,getHoldingActivity(),R.layout.item_home_pasture);
        home_parsture_ry.setAdapter(homePastureAdapter);
        homePastureAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getHoldingActivity(), ChooseParsterActivity.class);
                intent.putExtra("id", homepastureList.get(position).getPastureId());
                intent.putExtra("name", homepastureList.get(position).getName());
                startActivity(intent);
            }
        });

        //牧场资讯
        parsture_messege_ry=view.findViewById(R.id.parsture_messege_ry);
        parsture_messege_ry.setLayoutManager(new LinearLayoutManager(getHoldingActivity(), LinearLayoutManager.VERTICAL, false));
        homePastureMessegeAdapter=new HomePastureMessegeAdapter(homepastureMessegeList,getHoldingActivity(),R.layout.item_home_pasture_messege);
        parsture_messege_ry.setAdapter(homePastureMessegeAdapter);
        homePastureMessegeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent1=new Intent(getHoldingActivity(),AdWebviewActivity.class);
                intent1.putExtra("url",homepastureMessegeList.get(position).getLinkUrl());
                intent1.putExtra("title","牧场资讯");
                startActivity(intent1);
            }
        });



        togetherCowFm.setOnClickListener(this);
        specialbeefImgId.setBannerStyle(BannerConfig.NOT_INDICATOR);
        //设置banner样式
        specialbeefImgId.setImageLoader(new GlideImageLoaderAll());
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
                if(SDCardUtil.isNullOrEmpty(homeBannerResultBean.getBizData().getMiddleBannerList())){
                    return;
                }
                if(TextUtils.isEmpty(homeBannerResultBean.getBizData().getMiddleBannerList().get(position).getPageUrl())){
                    return;
                }
                if(homeBannerResultBean.getBizData().getMiddleBannerList().contains("http")){
                    Intent intent1=new Intent(getHoldingActivity(),AdWebviewActivity.class);
                    intent1.putExtra("url",homeBannerResultBean.getBizData().getTopBannerList().get(position).getPageUrl());
                    intent1.putExtra("title","");
                    startActivity(intent1);
                }else {
                    switch (homeBannerResultBean.getBizData().getMiddleBannerList().get(position).getPageUrl()) {
                        case "adopt_scheme_list":
                            //养牛方案列表
                            startActivity(BuyCowsPlanActivity.class);
                            break;
                        case "adopt_scheme_detail":
                            //养牛方案详情
                            if (!TextUtils.isEmpty(homeBannerResultBean.getBizData().getMiddleBannerList().get(position).getParams())) {
                                Intent intent4 = new Intent(getHoldingActivity(), CowsClaimActivity.class);
                                intent4.putExtra("schemId", Integer.parseInt(homeBannerResultBean.getBizData().getMiddleBannerList().get(position).getParams()));
                                startActivity(intent4);
                            }
                            break;
                        case "adopt_order_list":
                            //养牛订单列表
                            startActivity(MyCowsActivity.class);
                            break;
                        case "adopt_order_detail":
                            //养牛订单详情
                            if (!TextUtils.isEmpty(homeBannerResultBean.getBizData().getMiddleBannerList().get(position).getParams())) {
                                Intent intent3 = new Intent(getHoldingActivity(), MyCowsDetailActivity.class);
                                intent3.putExtra("orderId", homeBannerResultBean.getBizData().getMiddleBannerList().get(position).getParams() + "");
                                startActivity(intent3);
                            }
                            break;
                        case "pasture_list":
                            //合作牧场列表
                            startActivity(RanchConsociationActivity.class);
                            break;
                        case "shop_product_list":
                            //商城商品列表
                            Intent intent1 = new Intent(getHoldingActivity(), MainActivity.class);
                            intent1.putExtra("index", 1);
                            startActivity(intent1);
                            break;
                        case "shop_order_list":
                            //商城订单列表
                            startActivity(MyOrderActivity.class);
                            break;
                        case "shop_order_detail":
                            //商城订单详情
                            if (!TextUtils.isEmpty(homeBannerResultBean.getBizData().getMiddleBannerList().get(position).getParams())) {
                                Intent intent7 = new Intent(getHoldingActivity(), MyOrderDetailActivity.class);
                                intent7.putExtra("orderId", homeBannerResultBean.getBizData().getMiddleBannerList().get(position).getParams() + "");
                                startActivity(intent7);
                            }
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
                        case "vip_card_detail":
                            //会员卡详情
                            startActivity(VipCardActivity.class);
                            break;
                        case "new_welfare":
                            //新人福利
                            if(SDCardUtil.isNullOrEmpty(homeBannerResultBean.getBizData().getNewUserActivity())){
                                return;
                            }
                            Intent intent11 = new Intent(getHoldingActivity(), NewManwelfareActivity.class);
                            intent11.putExtra("infos", homeBannerResultBean.getBizData().getNewUserActivity());
                            startActivity(intent11);
                            break;
                        default:
                            break;
                    }
                }
            }
        });

        beanList=new ArrayList<>();

        ranchDynamicsAdapter=new RanchDynamicsAdapter(beanList,getHoldingActivity(),R.layout.ranch_dynamics_item);
        ranchDynamicsRy.setAdapter(ranchDynamicsAdapter);

        ranchDynamicsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeVideoResultBean.BizDataBean item=ranchDynamicsAdapter.getItem(position);
                Intent intent = new Intent(getHoldingActivity(), PlayerVideoActivity.class);
                intent.putExtra("video_url",item.getPlayUrl());
                intent.putExtra("title",item.getName());
                intent.putExtra("coverUrl",item.getCoverUrl());
                startActivity(intent);
            }
        });

        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        homeBannerPresenter.getHomeSellCowsNum(reqData);
        homeBannerPresenter.getHomeVideo(reqData);
        homePasturePresenter=new HomePasturePresenter(this);
        homePasturePresenter.getHomeParsture(reqData);
        homePasturePresenter.getHomeParstureMessege(reqData);
        homePasturePresenter.getHomeBuyCowPlay(reqData);
        initStatus();

    }
    /**
     * 初始化状态栏位置
     */
    private void initStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4以下不支持状态栏变色
            //注意了，这里使用了第三方库 StatusBarUtil，目的是改变状态栏的alpha
            StatusBarUtil.setTransparentForImageView(getHoldingActivity(), null);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        isPause=true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.together_cow_fm:
                //拼牛
                startActivity(FightCattleActivity.class);
                break;
            case R.id.see_more_sellcows_id:
                //养牛方案列表
                startActivity(BuyCowsPlanActivity.class);
                break;
            case R.id.see_more_pasture:
                //合作牧场
                startActivity(RanchConsociationActivity.class);
                break;
            case R.id.see_pasture_stadus_id:
                //牧场动态查看更多
                startActivity(RanchDynamicActivity.class);
                break;
            case R.id.see_pasture_messege_id:
                //牧场资讯查看更多
                startActivity(ParstureMessegeActivity.class);
                break;
            case R.id.pasture_messege_img:
                //牧场新闻
                if(homeParstureMessegeResultBean!=null){
                    if(homeParstureMessegeResultBean.getBizData().size()>0){
                        Intent intent1=new Intent(getHoldingActivity(),AdWebviewActivity.class);
                        intent1.putExtra("url",homeParstureMessegeResultBean.getBizData().get(0).getLinkUrl());
                        intent1.putExtra("title","牧场资讯");
                        startActivity(intent1);
                    }
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void showMsg(String msg) {
    }

    @Override
    public void getHomeParsture(HomeParstureResultBean homeParstureResultBean) {
        if("000000".equals(homeParstureResultBean.getCode())){
            if(!SDCardUtil.isNullOrEmpty(homeParstureResultBean.getBizData())){
                lv_our_pasture.setVisibility(View.VISIBLE);
                see_more_pasture.setVisibility(View.VISIBLE);
                homepastureList.addAll(homeParstureResultBean.getBizData());
                homePastureAdapter.setNewData(this.homepastureList);
                homePastureAdapter.loadMoreEnd();
            }else {
                lv_our_pasture.setVisibility(View.GONE);
                see_more_pasture.setVisibility(View.GONE);
            }
        }else {
            showToast(homeParstureResultBean.getMessage());
        }

    }

    @Override
    public void getHomeParstureMessege(HomeParstureMessegeResultBean homeParstureMessegeResultBean) {
        if("000000".equals(homeParstureMessegeResultBean.getCode())){
            this.homeParstureMessegeResultBean=homeParstureMessegeResultBean;
            if(!SDCardUtil.isNullOrEmpty(homeParstureMessegeResultBean.getBizData())){
                if(homeParstureMessegeResultBean.getBizData().size()>0){
                    lv_parsture_messege.setVisibility(View.VISIBLE);
                    fm_parsture_messege.setVisibility(View.VISIBLE);
                    RequestOptions options1 = new RequestOptions()
                            .centerCrop()
                            .error(R.mipmap.jzsb)//加载失败显示图片
                            .priority(Priority.HIGH)//优先级
                            .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存策略
                            .transform(new GlideRoundTransformAll(10));//转化为圆角
                    if(getHoldingActivity()!=null&&!getHoldingActivity().isFinishing()){
                        Glide.with(getHoldingActivity()).load(Constant.imageDomain+homeParstureMessegeResultBean.getBizData().get(0).getImageUrl()).apply(options1).into(pasture_messege_img);
                    }
                    pasture_messege_title.setText(homeParstureMessegeResultBean.getBizData().get(0).getTitle());
                    homepastureMessegeList.addAll(homeParstureMessegeResultBean.getBizData());
                    homepastureMessegeList.remove(0);
                }
                homePastureMessegeAdapter.setNewData(this.homepastureMessegeList);
                homePastureMessegeAdapter.loadMoreEnd();
            }else {
                lv_parsture_messege.setVisibility(View.GONE);
                fm_parsture_messege.setVisibility(View.GONE);
            }
        }else {
            showToast(homeParstureMessegeResultBean.getMessage());
        }


    }

    @Override
    public void getHomeParstureMoreMessege(HomeParstureMessegeResultBean homeParstureMessegeResultBean) {

    }

    @Override
    public void getHomeBuyCowPlay(HomeBuyCowPlanResultBean homeBuyCowPlanResultBean) {
        if("000000".equals(homeBuyCowPlanResultBean.getCode())){
            if(!SDCardUtil.isNullOrEmpty(homeBuyCowPlanResultBean.getBizData())){
                lv_eat_cows.setVisibility(View.VISIBLE);
                eatcowsList.addAll(homeBuyCowPlanResultBean.getBizData());
                homeEatCowsAdapter.setNewData(this.eatcowsList);
                homeEatCowsAdapter.loadMoreEnd();
            }else {
                lv_eat_cows.setVisibility(View.GONE);
            }

        }else {
            showToast(homeBuyCowPlanResultBean.getMessage());
        }
    }


    @Override
    public void getHomeBanner(final HomeBannerResultBean homeBannerResultBean) {
        this.homeBannerResultBean=homeBannerResultBean;
        swipeLy.setRefreshing(false);
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
        SimpleTarget<Drawable> simpleTarget = new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                img_top_id.setBackground(resource);
            }
        };
        if(getHoldingActivity()!=null&&!getHoldingActivity().isFinishing()){
            Glide.with(getHoldingActivity()).load(Constant.imageDomain+homeBannerResultBean.getBizData().getTopBannerList().get(0).getImageUrl()).apply(RequestOptions.bitmapTransform(new GlideBlurformation(getHoldingActivity()))).into(simpleTarget);
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
                if("0".equals(homeBannerResultBean.getBizData().getPopBanner().getPopFlag())){
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
                }else {
                    Intent intent=new Intent(getHoldingActivity(),GivePoursActivity.class);
                    intent.putExtra("info",homeBannerResultBean);
                    startActivity(intent);
                }

            }
        }

    }

    @Override
    public void getHomeVideo(HomeVideoResultBean homeAdResultBean) {
        if("000000".equals(homeAdResultBean.getCode())){
            if(!SDCardUtil.isNullOrEmpty(homeAdResultBean.getBizData())){
                if(homeAdResultBean.getBizData().size()>0){
                    lv_parsture_stadus.setVisibility(View.VISIBLE);
                    beanList.addAll(homeAdResultBean.getBizData());
                    ranchDynamicsAdapter.setNewData(this.beanList);
                    ranchDynamicsAdapter.loadMoreEnd();
                }
            }else {
                lv_parsture_stadus.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void getHomeSellCowsNum(HomeSellCowNumResultBean homeSellCowNumResultBean) {
        if("000000".equals(homeSellCowNumResultBean.getCode())){
            if(homeSellCowNumResultBean.getBizData().isShowStatistic()){
                lv_show_num_id.setVisibility(View.VISIBLE);
                sellCowNumId.setText(homeSellCowNumResultBean.getBizData().getTotalSalesQuantity()+"");
                sellCowNum2Id.setText(homeSellCowNumResultBean.getBizData().getTotalUserQuantity()+"");
            }else {
                lv_show_num_id.setVisibility(View.GONE);
            }
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
        super.onDestroy();
        if(homeBannerPresenter!=null){
            homeBannerPresenter.detachView();
        }
        if(homePasturePresenter!=null){
            homePasturePresenter.detachView();
        }
    }

    @Override
    public void onRefresh() {
        beanList.clear();
        homepastureList.clear();
        homepastureMessegeList.clear();
        eatcowsList.clear();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        homeBannerPresenter.getHomeBanner(reqData);
        homeBannerPresenter.getHomeVideo(reqData);
        homeBannerPresenter.getHomeSellCowsNum(reqData);
        homePasturePresenter.getHomeParsture(reqData);
        homePasturePresenter.getHomeParstureMessege(reqData);
        homePasturePresenter.getHomeBuyCowPlay(reqData);

        homePastureMessegeAdapter.notifyDataSetChanged();
        homePastureAdapter.notifyDataSetChanged();
        ranchDynamicsAdapter.notifyDataSetChanged();
        homeEatCowsAdapter.notifyDataSetChanged();
        homeProductListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPullDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {

    }
}
