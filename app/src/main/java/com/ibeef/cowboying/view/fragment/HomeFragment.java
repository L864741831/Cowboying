package com.ibeef.cowboying.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.HomeProductListAdapter;
import com.ibeef.cowboying.adapter.RanchDynamicsAdapter;
import com.ibeef.cowboying.base.CheckVersionBase;
import com.ibeef.cowboying.base.HomeBannerBase;
import com.ibeef.cowboying.bean.CheckVersionBean;
import com.ibeef.cowboying.bean.CheckVersionParamBean;
import com.ibeef.cowboying.bean.HomeAdResultBean;
import com.ibeef.cowboying.bean.HomeAllVideoResultBean;
import com.ibeef.cowboying.bean.HomeBannerResultBean;
import com.ibeef.cowboying.bean.HomeVideoResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.CheckVersionPresenter;
import com.ibeef.cowboying.presenter.HomeBannerPresenter;
import com.ibeef.cowboying.utils.DateUtils;
import com.ibeef.cowboying.utils.GlideImageLoader;
import com.ibeef.cowboying.view.activity.AdActivity;
import com.ibeef.cowboying.view.activity.AdWebviewActivity;
import com.ibeef.cowboying.view.activity.GivePoursActivity;
import com.ibeef.cowboying.view.activity.HomeVideoPlayActivity;
import com.ibeef.cowboying.view.activity.PlayerVideoActivity;
import com.ibeef.cowboying.view.activity.RanchConsociationActivity;
import com.ibeef.cowboying.view.activity.RanchDynamicActivity;
import com.orhanobut.hawk.Hawk;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rxfamily.bean.BaseBean;
import rxfamily.view.BaseFragment;

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,View.OnClickListener,HomeBannerBase.IView  {

    @Bind(R.id.home_ry_id)
    RecyclerView homeRyId;
    SwipeRefreshLayout swipeLy;
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
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        swipeLy=view.findViewById(R.id.swipe_ly);
        homeRyId.setLayoutManager(new LinearLayoutManager(getHoldingActivity()));
        swipeLy.setColorSchemeResources(R.color.colorAccent);
        swipeLy.setOnRefreshListener(this);
        swipeLy.setEnabled(true);

        objectList=new ArrayList<>();
        homeProductListAdapter=new HomeProductListAdapter(objectList,getHoldingActivity(),R.layout.activity_ad_webview);
        View headView = View.inflate(getHoldingActivity(), R.layout.home_head_view, null);
        init(headView);
        //添加头视图
        homeProductListAdapter.addHeaderView(headView);
        homeRyId.setAdapter(homeProductListAdapter);

        homeBannerPresenter=new HomeBannerPresenter(this);
        homeBannerPresenter.getHomeBanner(getVersionCodes());
        homeBannerPresenter.getHomeVideo(getVersionCodes());
        mPrefDailog = getHoldingActivity().getSharedPreferences("firstopenDailogs", Activity.MODE_PRIVATE);
        history= mPrefDailog.getString(KEY_HISTORY_KEYWORD, "");

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
        specialbeefImgId.isAutoPlay(true);
        specialbeefImgId.setDelayTime(1000 * 6);
        //设置轮播时间
        specialbeefImgId.setClickable(true);

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
                    Log.i("htht", "video_url:::::::"+item.getPlayUrl());
                }
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        beanList.clear();
        homeBannerPresenter.getHomeBanner(getVersionCodes());
        homeBannerPresenter.getHomeVideo(getVersionCodes());
        swipeLy.setRefreshing(false);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buy_cow_fm:
                //买牛
                break;
            case R.id.together_cow_fm:
                //拼牛
                break;
            case R.id.newpeople_img_id:
                //新人礼券
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
        ArrayList<String> imgStr = new ArrayList<>();
        for (int i=0;i<homeBannerResultBean.getBizData().getTopBannerList().size();i++){
            imgStr.add(Constant.imageDomain+homeBannerResultBean.getBizData().getTopBannerList().get(i).getImageUrl());
        }
        banner.setImages(imgStr);
        //设置图片集合
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent=new Intent(getHoldingActivity(), AdWebviewActivity.class);
                intent.putExtra("url",homeBannerResultBean.getBizData().getTopBannerList().get(position).getLinkUrl());
                intent.putExtra("title","口袋牧场");
                startActivity(intent);
            }
        });


        ArrayList<String> imgStr1 = new ArrayList<>();
        for (int i=0;i<homeBannerResultBean.getBizData().getTopBannerList().size();i++){
            imgStr1.add(Constant.imageDomain+homeBannerResultBean.getBizData().getMiddleBannerList().get(i).getImageUrl());
        }
        specialbeefImgId.setImages(imgStr1);
        //设置图片集合
        specialbeefImgId.start();
        specialbeefImgId.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent=new Intent(getHoldingActivity(), AdWebviewActivity.class);
                intent.putExtra("url",homeBannerResultBean.getBizData().getTopBannerList().get(position).getLinkUrl());
                intent.putExtra("title","口袋牧场");
                startActivity(intent);
            }
        });


        if(TextUtils.isEmpty(history)){
            SharedPreferences.Editor editor = mPrefDailog.edit();
            editor.putString(KEY_HISTORY_KEYWORD, DateUtils.getTime(new Date()));
            editor.commit();
            Intent intent=new Intent(getHoldingActivity(),GivePoursActivity.class);
            intent.putExtra("info",homeBannerResultBean.getBizData().getPopBannerResDto());
            startActivity(intent);
        }else {
            if(!DateUtils.getTime(new Date()).equals(history)){
                SharedPreferences.Editor editor = mPrefDailog.edit();
                editor.putString(KEY_HISTORY_KEYWORD, DateUtils.getTime(new Date()));
                editor.commit();
                Intent intent=new Intent(getHoldingActivity(),GivePoursActivity.class);
                intent.putExtra("info",homeBannerResultBean.getBizData().getPopBannerResDto());
                startActivity(intent);
            }

        }
    }

    @Override
    public void getHomeVideo(HomeVideoResultBean homeAdResultBean) {
        beanList.addAll(homeAdResultBean.getBizData());
        ranchDynamicsAdapter.setNewData(this.beanList);
        ranchDynamicsAdapter.loadMoreEnd();
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
}
