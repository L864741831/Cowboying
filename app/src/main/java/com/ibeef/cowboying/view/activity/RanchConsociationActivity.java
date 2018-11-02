package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.RanchConsociationTitleAdapter;
import com.ibeef.cowboying.base.PastureBase;
import com.ibeef.cowboying.bean.PastureAllResultBean;
import com.ibeef.cowboying.bean.PastureDetelResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.presenter.PasturePresenter;
import com.ibeef.cowboying.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * @author Administrator
 * 合作牧场主界面
 */
public class RanchConsociationActivity extends BaseActivity implements PastureBase.IView {


    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.lable_list_rv)
    RecyclerView lableListRv;
    @Bind(R.id.iv_ranch_canosciation)
    ImageView iv_ranch_canosciation;
    @Bind(R.id.banner)
    Banner banner;
    private int positions=0;
    private RanchConsociationTitleAdapter ranchConsociationTitleAdapter;
    private List<PastureAllResultBean.BizDataBean> dataBeen;
    private PasturePresenter pasturePresenter;

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
        ranchConsociationTitleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                positions=position;
                clickShow();
                pasturePresenter.getPastureDetelVideo(getVersionCodes(), dataBeen.get(position).getPastureId());
            }
        });
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showMsg(String msg) {
        showToast(msg);
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
            if (pastureAllResultBean.getBizData().size()>0) {
                pasturePresenter.getPastureDetelVideo(getVersionCodes(), pastureAllResultBean.getBizData().get(0).getPastureId());
            }
        }
    }

    @Override
    public void getPastureDetelVideo(PastureDetelResultBean pastureDetelResultBean) {
        ArrayList<String> imgStr = new ArrayList<>();
        for (int i=0;i<pastureDetelResultBean.getBizData().getImageList().size();i++){
            imgStr.add(Constant.imageDomain+pastureDetelResultBean.getBizData().getImageList().get(i));
        }
        banner.setImages(imgStr);
        //设置图片集合
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
            }
        });
        Glide.with(this).load(Constant.imageDomain + pastureDetelResultBean.getBizData().getMainImageUrl()).into(iv_ranch_canosciation);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    protected void onDestroy() {
        if (pasturePresenter != null) {
            pasturePresenter.detachView();
        }
        super.onDestroy();
    }
}
