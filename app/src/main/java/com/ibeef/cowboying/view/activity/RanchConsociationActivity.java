package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MyFeedbackImgAdapter;
import com.ibeef.cowboying.adapter.PastureDetelBottomImgAdapter;
import com.ibeef.cowboying.adapter.RanchConsociationTitleAdapter;
import com.ibeef.cowboying.base.PastureBase;
import com.ibeef.cowboying.base.RanchBottomVideoBase;
import com.ibeef.cowboying.bean.PastureAllResultBean;
import com.ibeef.cowboying.bean.PastureDetelResultBean;
import com.ibeef.cowboying.bean.RanchBottomVideoResultBean;
import com.ibeef.cowboying.bean.SubmitFeedbackParamBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.presenter.PasturePresenter;
import com.ibeef.cowboying.presenter.RanchBottomVideoPresenter;
import com.ibeef.cowboying.utils.GlideImageLoader;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.richeditor.RichEditor;
import rxfamily.view.BaseActivity;

/**
 * @author Administrator
 * 合作牧场主界面
 */
public class RanchConsociationActivity extends BaseActivity implements PastureBase.IView,SwipeRefreshLayout.OnRefreshListener ,RanchBottomVideoBase.IView {
    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.iv_ranch_consociation_live)
    ImageView ivRanchConsociationLive;
    @Bind(R.id.lable_list_rv)
    RecyclerView lableListRv;
    @Bind(R.id.ry_id)
    RecyclerView ryId;
    @Bind(R.id.iv_ranch_canosciation)
    ImageView iv_ranch_canosciation;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    @Bind(R.id.nest_scroll_id)
    NestedScrollView nestScrollId;
    @Bind(R.id.rich_editext_info_tv)
    RichEditor richEditextInfoTv;
    @Bind(R.id.swipe_ly)
    SwipeRefreshLayout mSwipeLayout;
    private int positions=0;
    private RanchConsociationTitleAdapter ranchConsociationTitleAdapter;
    private List<PastureAllResultBean.BizDataBean> dataBeen;
    private PasturePresenter pasturePresenter;
    private  List<RanchBottomVideoResultBean.BizDataBean> beanList;
    private  PastureDetelBottomImgAdapter pastureDetelBottomImgAdapter;
    private PastureDetelResultBean pastureDetelResultBean;
    private boolean isFirst=true;
    private RanchBottomVideoPresenter ranchBottomVideoPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranch_consociation);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("合作牧场");
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
        dataBeen = new ArrayList<>();
        pasturePresenter = new PasturePresenter(this);
        lableListRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ranchConsociationTitleAdapter = new RanchConsociationTitleAdapter(dataBeen, this, R.layout.item_day_new_title);
        lableListRv.setAdapter(ranchConsociationTitleAdapter);
        pasturePresenter.getPastureAllVideo(getVersionCodes());
        ranchConsociationTitleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                positions=position;
                clickShow();
                banner.setVisibility(View.GONE);
                beanList.clear();
                pastureDetelBottomImgAdapter.notifyDataSetChanged();
                PastureAllResultBean.BizDataBean item=ranchConsociationTitleAdapter.getItem(position);
                pasturePresenter.getPastureDetelVideo(getVersionCodes(), item.getPastureId());
            }
        });
        ryId.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ryId.setHasFixedSize(true);
        ryId.setNestedScrollingEnabled(false);
        beanList=new ArrayList<>();
        pastureDetelBottomImgAdapter=new PastureDetelBottomImgAdapter(this,beanList,R.layout.pasture_detel_img_item);
        ryId.setAdapter(pastureDetelBottomImgAdapter);
        richEditextInfoTv.setEditorFontSize(16);
        richEditextInfoTv.setEditorFontColor(Color.BLACK);
        richEditextInfoTv.setInputEnabled(false);
        richEditextInfoTv.setPadding(3, 5, 5, 5);
        richEditextInfoTv.loadCSS("file:///android_asset/img.css");
        mSwipeLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setEnabled(true);
        ranchBottomVideoPresenter=new RanchBottomVideoPresenter(this);
        pastureDetelBottomImgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RanchBottomVideoResultBean.BizDataBean item=pastureDetelBottomImgAdapter.getItem(position);
                Intent intent = new Intent(RanchConsociationActivity.this, PlayerVideoActivity.class);
                intent.putExtra("video_url",item.getPlayUrl());
                intent.putExtra("title",item.getName());
                intent.putExtra("coverUrl",item.getCoverUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    public void showMsg(String msg) {
        showToast(msg);
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

    private void clickShow(){
        for (int j=0;j<dataBeen.size();j++){
            if(j==positions){
                dataBeen.get(j).setDefaultFlag(0);
            }else {
                dataBeen.get(j).setDefaultFlag(1);
            }
        }
        ranchConsociationTitleAdapter.notifyDataSetChanged();
    }

    @Override
    public void getPastureAllVideo(PastureAllResultBean pastureAllResultBean) {
        if ("000000".equals(pastureAllResultBean.getCode())) {
            dataBeen.addAll(pastureAllResultBean.getBizData());
            ranchConsociationTitleAdapter.setNewData(dataBeen);
            ranchConsociationTitleAdapter.loadMoreEnd();
            if(isFirst){
                pasturePresenter.getPastureDetelVideo(getVersionCodes(), dataBeen.get(0).getPastureId());
                isFirst=false;
                positions=0;
                clickShow();

            }
        }
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
            ranchBottomVideoPresenter.getRanchBottomVideo(getVersionCodes());
        }

    }

    @OnClick({R.id.back_id, R.id.iv_ranch_canosciation, R.id.iv_ranch_consociation_live})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.iv_ranch_canosciation:
                //视频播放
                if(!SDCardUtil.isNullOrEmpty(pastureDetelResultBean)){
                    if(!SDCardUtil.isNullOrEmpty(pastureDetelResultBean.getBizData())){
                        Intent intent = new Intent(RanchConsociationActivity.this, PlayerVideoActivity.class);
                        intent.putExtra("video_url",pastureDetelResultBean.getBizData().getVideoPlayUrl());
                        intent.putExtra("title",pastureDetelResultBean.getBizData().getName());
                        intent.putExtra("coverUrl",pastureDetelResultBean.getBizData().getVideoCoverUrl());
                        startActivity(intent);
                    }
                }
                break;
            case R.id.iv_ranch_consociation_live:
                //牧场直播
                //直播view需要自定义
                if(!SDCardUtil.isNullOrEmpty(pastureDetelResultBean)){
                    if(!SDCardUtil.isNullOrEmpty(pastureDetelResultBean.getBizData())){
                        Intent intent = new Intent(RanchConsociationActivity.this, PlayerVideoActivity.class);
                        intent.putExtra("video_url",pastureDetelResultBean.getBizData().getLivePlayUrl());
                        intent.putExtra("title",pastureDetelResultBean.getBizData().getName());
                        intent.putExtra("coverUrl",pastureDetelResultBean.getBizData().getVideoCoverUrl());
                        startActivity(intent);
                    }
                }
                break;
            default:
                break;
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
    }

    @Override
    protected void onDestroy() {
        if (pasturePresenter != null) {
            pasturePresenter.detachView();
        }
        if (ranchBottomVideoPresenter != null) {
            ranchBottomVideoPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        banner.setVisibility(View.GONE);
        beanList.clear();
        //底部图片集合
        pastureDetelBottomImgAdapter.notifyDataSetChanged();
        pasturePresenter.getPastureDetelVideo(getVersionCodes(),dataBeen.get(positions).getPastureId());
        mSwipeLayout.setRefreshing(false);
    }
}
