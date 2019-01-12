package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.HomePastureMessegeAdapter;
import com.ibeef.cowboying.base.HomePastureBase;
import com.ibeef.cowboying.bean.HomeBuyCowPlanResultBean;
import com.ibeef.cowboying.bean.HomeParstureMessegeResultBean;
import com.ibeef.cowboying.bean.HomeParstureResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.HomePasturePresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.ibeef.cowboying.view.customview.GlideRoundTransformAll;
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

public class ParstureMessegeActivity extends BaseActivity implements SuperSwipeRefreshLayout.OnPullRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, HomePastureBase.IView {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.pasture_messege_img)
    ImageView pastureMessegeImg;
    @Bind(R.id.pasture_messege_title)
    TextView pastureMessegeTitle;
    @Bind(R.id.parsture_messege_ry)
    RecyclerView parstureMessegeRy;
    @Bind(R.id.swipe_ly)
    SuperSwipeRefreshLayout swipeLy;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    private HomePastureMessegeAdapter homePastureMessegeAdapter;
    private List<HomeParstureMessegeResultBean.BizDataBean> homepastureMessegeList;
    private String token;
    private HomePasturePresenter homePasturePresenter;
    private int currentPage = 1;
    private boolean isMoreLoad = false;
    private HomeParstureMessegeResultBean homeParstureMessegeResultBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parsture_messege);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("牧场资讯");
        homepastureMessegeList = new ArrayList<>();
        token = Hawk.get(HawkKey.TOKEN);
        homePasturePresenter = new HomePasturePresenter(this);
        swipeLy.setHeaderViewBackgroundColor(getResources().getColor(R.color.colorAccent));
        swipeLy.setHeaderView(createHeaderView());// add headerView
        swipeLy.setTargetScrollWithLayout(true);
        swipeLy.setOnPullRefreshListener(this);

        parstureMessegeRy.setHasFixedSize(true);
        parstureMessegeRy.setNestedScrollingEnabled(false);
        parstureMessegeRy.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        homePastureMessegeAdapter = new HomePastureMessegeAdapter(homepastureMessegeList, ParstureMessegeActivity.this, R.layout.item_home_pasture_messege);
        homePastureMessegeAdapter.setOnLoadMoreListener(this, parstureMessegeRy);
        parstureMessegeRy.setAdapter(homePastureMessegeAdapter);

        homePastureMessegeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent1 = new Intent(ParstureMessegeActivity.this, AdWebviewActivity.class);
                intent1.putExtra("url", homepastureMessegeList.get(position).getLinkUrl());
                intent1.putExtra("title", "牧场资讯");
                startActivity(intent1);
            }
        });

        parstureMessegeRy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!parstureMessegeRy.canScrollVertically(-1)) {
                    swipeLy.setEnabled(true);
                } else {
                    swipeLy.setEnabled(false);
                }
            }
        });

        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization", token);
        reqData.put("version", getVersionCodes());
        homePasturePresenter.getHomeParstureMoreMessege(reqData, currentPage);
    }

    @OnClick({R.id.back_id, R.id.pasture_messege_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.pasture_messege_img:
                if(homeParstureMessegeResultBean!=null){
                    if(homeParstureMessegeResultBean.getBizData().size()>0){
                        Intent intent1=new Intent(ParstureMessegeActivity.this,AdWebviewActivity.class);
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
    public void onLoadMoreRequested() {
        isMoreLoad = true;
        currentPage += 1;
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization", token);
        reqData.put("version", getVersionCodes());
        homePasturePresenter.getHomeParstureMoreMessege(reqData, currentPage);
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        homepastureMessegeList.clear();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization", token);
        reqData.put("version", getVersionCodes());
        homePasturePresenter.getHomeParstureMoreMessege(reqData, currentPage);
    }

    @Override
    public void onPullDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getHomeParsture(HomeParstureResultBean homeParstureResultBean) {

    }

    @Override
    public void getHomeParstureMessege(HomeParstureMessegeResultBean homeParstureMessegeResultBean) {

    }

    @Override
    public void getHomeParstureMoreMessege(HomeParstureMessegeResultBean homeParstureMessegeResultBean) {

        if ("000000".equals(homeParstureMessegeResultBean.getCode())) {
            if (SDCardUtil.isNullOrEmpty(homeParstureMessegeResultBean.getBizData())) {
                homePastureMessegeAdapter.loadMoreEnd();
                return;
            }
            if (homeParstureMessegeResultBean.getBizData().size() > 0) {
                this.homeParstureMessegeResultBean = homeParstureMessegeResultBean;
                RequestOptions options1 = new RequestOptions()
                        .centerCrop()
//                .placeholder(R.mipmap.jzsb)//预加载图片
                        .error(R.mipmap.jzsb)//加载失败显示图片
                        .priority(Priority.HIGH)//优先级
                        .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存策略
                        .transform(new GlideRoundTransformAll(10));//转化为圆角
                Glide.with(ParstureMessegeActivity.this).load(Constant.imageDomain + homeParstureMessegeResultBean.getBizData().get(0).getImageUrl()).apply(options1).into(pastureMessegeImg);
                pastureMessegeTitle.setText(homeParstureMessegeResultBean.getBizData().get(0).getTitle());
                homepastureMessegeList.addAll(homeParstureMessegeResultBean.getBizData());
                homepastureMessegeList.remove(0);
            }
            homePastureMessegeAdapter.setNewData(this.homepastureMessegeList);
            homePastureMessegeAdapter.loadMoreComplete();

        } else {
            showToast(homeParstureMessegeResultBean.getMessage());
        }

    }

    @Override
    public void getHomeBuyCowPlay(HomeBuyCowPlanResultBean homeBuyCowPlanResultBean) {

    }


    @Override
    public void showLoading() {
        if (isMoreLoad) {
            loadingLayout.setVisibility(View.GONE);
            parstureMessegeRy.setVisibility(View.VISIBLE);
            isMoreLoad = false;
        } else {
            loadingLayout.setVisibility(View.VISIBLE);
            parstureMessegeRy.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        parstureMessegeRy.setVisibility(View.VISIBLE);
        swipeLy.setRefreshing(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (homePasturePresenter != null) {
            homePasturePresenter.detachView();
        }
    }
}
