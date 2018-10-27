package com.ibeef.cowboying.view.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.HomeProductListAdapter;
import com.ibeef.cowboying.adapter.RanchDynamicsAdapter;
import com.ibeef.cowboying.utils.GlideImageLoader;
import com.ibeef.cowboying.view.activity.RanchConsociationActivity;
import com.ibeef.cowboying.view.activity.RanchDynamicActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rxfamily.bean.BaseBean;
import rxfamily.view.BaseFragment;

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,OnBannerListener,View.OnClickListener,BaseQuickAdapter.RequestLoadMoreListener{

    @Bind(R.id.home_ry_id)
    RecyclerView homeRyId;
    @Bind(R.id.swipe_ly)
    SwipeRefreshLayout swipeLy;
    private Banner banner;
    private HomeProductListAdapter homeProductListAdapter;
    private List<Object> objectList;
    private TextView sellCowNumId;
    private TextView sellCowNum2Id;
    private ImageView specialbeefImgId;
    private ImageView newpeopleImgId;
    private ImageView ranchconsociationImgId;
    private FrameLayout buyCowFm;
    private FrameLayout togetherCowFm;
    private RecyclerView ranchDynamicsRy;
    private RanchDynamicsAdapter ranchDynamicsAdapter;
    private List<BaseBean> beanList;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
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

        banner.setOnBannerListener(this);
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
        specialbeefImgId.setOnClickListener(this);
        ranchconsociationImgId.setOnClickListener(this);
        buyCowFm.setOnClickListener(this);
        togetherCowFm.setOnClickListener(this);
        newpeopleImgId.setOnClickListener(this);

        beanList=new ArrayList<>();
        for (int i=0;i<3;i++){
            BaseBean baseBean=new BaseBean();
            beanList.add(baseBean);
        }
        ranchDynamicsAdapter=new RanchDynamicsAdapter(beanList,getHoldingActivity(),R.layout.ranch_dynamics_item);

//        ranchDynamicsAdapter.setOnLoadMoreListener(this, ranchDynamicsRy);
        ranchDynamicsRy.setAdapter(ranchDynamicsAdapter);
        ranchDynamicsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(position==beanList.size()-1){
                    startActivity(RanchDynamicActivity.class);
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
        swipeLy.setRefreshing(false);
    }

    @Override
    public void OnBannerClick(int position) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.specialbeef_img_id:
                //特价牛排
                break;
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
    public void onLoadMoreRequested() {

    }
}
