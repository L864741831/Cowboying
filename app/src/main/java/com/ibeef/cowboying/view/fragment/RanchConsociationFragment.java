package com.ibeef.cowboying.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.PastureDetelBottomImgAdapter;
import com.ibeef.cowboying.base.PastureBase;
import com.ibeef.cowboying.base.RanchBottomVideoBase;
import com.ibeef.cowboying.bean.PastureAllResultBean;
import com.ibeef.cowboying.bean.PastureDetelResultBean;
import com.ibeef.cowboying.bean.RanchBottomVideoResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.PasturePresenter;
import com.ibeef.cowboying.presenter.RanchBottomVideoPresenter;
import com.ibeef.cowboying.utils.GlideImageLoader;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.ibeef.cowboying.view.activity.PlayerVideoActivity;
import com.ibeef.cowboying.view.activity.TvLiveActivity;
import com.ibeef.cowboying.view.customview.SuperSwipeRefreshLayout;
import com.orhanobut.hawk.Hawk;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.richeditor.RichEditor;
import rxfamily.view.BaseFragment;

/**
 * 合作牧场主界面
 */
public class RanchConsociationFragment extends BaseFragment implements SuperSwipeRefreshLayout.OnPullRefreshListener,PastureBase.IView ,RanchBottomVideoBase.IView,View.OnClickListener {
    ImageView ivRanchConsociationLive;
    RecyclerView ryId;
    ImageView iv_ranch_canosciation;
    Banner banner;
    RelativeLayout loadingLayout;
    NestedScrollView nestScrollId;
    RichEditor richEditextInfoTv;
    SuperSwipeRefreshLayout mSwipeLayout;
    private PasturePresenter pasturePresenter;
    private  List<RanchBottomVideoResultBean.BizDataBean> beanList;
    private PastureDetelBottomImgAdapter pastureDetelBottomImgAdapter;
    private PastureDetelResultBean pastureDetelResultBean;
    private RanchBottomVideoPresenter ranchBottomVideoPresenter;
    private int ids;
    private String token;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        banner=view.findViewById(R.id.banner);
        ivRanchConsociationLive=view.findViewById(R.id.iv_ranch_consociation_live);
        ryId=view.findViewById(R.id.ry_id);
        iv_ranch_canosciation=view.findViewById(R.id.iv_ranch_canosciation);
        loadingLayout=view.findViewById(R.id.loading_layout);
        nestScrollId=view.findViewById(R.id.nest_scroll_id);
        richEditextInfoTv=view.findViewById(R.id.rich_editext_info_tv);
        mSwipeLayout=view.findViewById(R.id.swipe_ly);
        ivRanchConsociationLive.setOnClickListener(this);
        iv_ranch_canosciation.setOnClickListener(this);
        init();

    }

    private void init() {
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
        pasturePresenter = new PasturePresenter(this);

        ryId.setLayoutManager(new LinearLayoutManager(getHoldingActivity(), LinearLayoutManager.HORIZONTAL, false));
        ryId.setHasFixedSize(true);
        ryId.setNestedScrollingEnabled(false);
        beanList=new ArrayList<>();
        pastureDetelBottomImgAdapter=new PastureDetelBottomImgAdapter(getHoldingActivity(),beanList,R.layout.pasture_detel_img_item);
        ryId.setAdapter(pastureDetelBottomImgAdapter);
        richEditextInfoTv.setEditorFontSize(16);
        richEditextInfoTv.setEditorFontColor(Color.BLACK);
        richEditextInfoTv.setInputEnabled(false);
        richEditextInfoTv.setPadding(0, 0, 0, 0);
        richEditextInfoTv.loadCSS("file:///android_asset/img.css");

        mSwipeLayout.setHeaderViewBackgroundColor(getHoldingActivity().getResources().getColor(R.color.colorAccent));
        mSwipeLayout.setHeaderView(createHeaderView());// add headerView
        mSwipeLayout.setTargetScrollWithLayout(true);
        mSwipeLayout.setOnPullRefreshListener(this);

        ranchBottomVideoPresenter=new RanchBottomVideoPresenter(this);
        pastureDetelBottomImgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RanchBottomVideoResultBean.BizDataBean item=pastureDetelBottomImgAdapter.getItem(position);
                Intent intent = new Intent(getHoldingActivity(), PlayerVideoActivity.class);
                intent.putExtra("video_url",item.getPlayUrl());
                intent.putExtra("title",item.getName());
                intent.putExtra("coverUrl",item.getCoverUrl());
                startActivity(intent);
            }
        });
        token= Hawk.get(HawkKey.TOKEN);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        pasturePresenter.getPastureDetelVideo(reqData,ids);
    }

    public static RanchConsociationFragment newInstance(int id) {

        RanchConsociationFragment newFragment = new RanchConsociationFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        newFragment.setArguments(bundle);
        return newFragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ranchconsociation;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            ids = args.getInt("id");
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getRanchBottomVideo(RanchBottomVideoResultBean ranchBottomVideoResultBean) {
        if("000000".equals(ranchBottomVideoResultBean.getCode())){
            if(!SDCardUtil.isNullOrEmpty(ranchBottomVideoResultBean.getBizData())){
                beanList.clear();
                beanList.addAll(ranchBottomVideoResultBean.getBizData());
                pastureDetelBottomImgAdapter.setNewData(this.beanList);
                pastureDetelBottomImgAdapter.loadMoreEnd();
            }
        }else {
            showToast(ranchBottomVideoResultBean.getMessage());
        }
    }

    @Override
    public void getPastureAllVideo(PastureAllResultBean pastureAllResultBean) {

    }

    @Override
    public void getPastureDetelVideo(PastureDetelResultBean pastureDetelResultBean) {
        if("000000".equals(pastureDetelResultBean.getCode())){
            this.pastureDetelResultBean=pastureDetelResultBean;
            ArrayList<String> imgStr = new ArrayList<>();
            if(!SDCardUtil.isNullOrEmpty(pastureDetelResultBean.getBizData().getImageList())){
                for (int i=0;i<pastureDetelResultBean.getBizData().getImageList().size();i++){
                    imgStr.add(Constant.imageDomain+pastureDetelResultBean.getBizData().getImageList().get(i));
                }
                banner.setImages(imgStr);

                //设置图片集合
                banner.start();
                banner.setVisibility(View.VISIBLE);
            }

            Glide.with(this).load(pastureDetelResultBean.getBizData().getVideoCoverUrl()).into(iv_ranch_canosciation);
            RequestOptions options = new RequestOptions()
                    .skipMemoryCache(true)
                    .error(R.mipmap.ranch_consociation_banner_2)
                    //跳过内存缓存
                    ;
            Glide.with(this).load(Constant.imageDomain + pastureDetelResultBean.getBizData().getLiveCoverUrl()).apply(options).into(ivRanchConsociationLive);
            richEditextInfoTv.setHtml(pastureDetelResultBean.getBizData().getDescribes());
            //牧场随机视频
//            ranchBottomVideoPresenter.getRanchBottomVideo(getVersionCodes());
        }

    }

    @Override
    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
        nestScrollId.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        nestScrollId.setVisibility(View.VISIBLE);
        mSwipeLayout.setRefreshing(false);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(pasturePresenter!=null){
            pasturePresenter.detachView();
        }
        if(ranchBottomVideoPresenter!=null){
            ranchBottomVideoPresenter.detachView();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_ranch_canosciation:
                //视频播放
                if (!SDCardUtil.isNullOrEmpty(pastureDetelResultBean)) {
                    if (!SDCardUtil.isNullOrEmpty(pastureDetelResultBean.getBizData())) {
                        Intent intent = new Intent(getHoldingActivity(), PlayerVideoActivity.class);
                        intent.putExtra("video_url", pastureDetelResultBean.getBizData().getVideoPlayUrl());
                        intent.putExtra("title", pastureDetelResultBean.getBizData().getName());
                        intent.putExtra("coverUrl", pastureDetelResultBean.getBizData().getVideoCoverUrl());
                        startActivity(intent);
                    }
                }
                break;
            case R.id.iv_ranch_consociation_live:
                //牧场直播
                //直播view需要自定义
                if (!SDCardUtil.isNullOrEmpty(pastureDetelResultBean)) {
                    if (!SDCardUtil.isNullOrEmpty(pastureDetelResultBean.getBizData())) {
                        Intent intent = new Intent(getHoldingActivity(), TvLiveActivity.class);
                        intent.putExtra("video_url", pastureDetelResultBean.getBizData().getLivePlayUrl());
                        intent.putExtra("title", pastureDetelResultBean.getBizData().getName());
                        intent.putExtra("coverUrl", pastureDetelResultBean.getBizData().getLiveCoverUrl());
                        startActivity(intent);
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        banner.setVisibility(View.GONE);
        beanList.clear();
        //底部图片集合
        pastureDetelBottomImgAdapter.notifyDataSetChanged();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        pasturePresenter.getPastureDetelVideo(reqData,ids);
    }

    @Override
    public void onPullDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {

    }
}
