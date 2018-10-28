package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.adapter.RanchDynamicdapter;
import com.ibeef.cowboying.bean.RanchDynamicListBean;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * @author Administrator
 * 牧场动态主界面
 */
public class RanchDynamicActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{


    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.avi_loading)
    AVLoadingIndicatorView aviLoading;
    @Bind(R.id.video_list_rv)
    RecyclerView videoListRv;
    @Bind(R.id.tv_text_null)
    ImageView tvTextNull;
    @Bind(R.id.rv_order)
    RelativeLayout rvOrder;
    @Bind(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;

    private RanchDynamicdapter ranchDynamicdapter;
    private List<RanchDynamicListBean.DataBean> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranch_dynamic);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("牧场动态");
        listData=new ArrayList<>();
        listData.clear();
        videoListRv.setLayoutManager(new LinearLayoutManager(this));
        videoListRv.setHasFixedSize(true);
        videoListRv.setNestedScrollingEnabled(false);
        ranchDynamicdapter = new RanchDynamicdapter(listData,this);
        ranchDynamicdapter.setOnLoadMoreListener(this, videoListRv);
        videoListRv.setAdapter(ranchDynamicdapter);
        videoListRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!videoListRv.canScrollVertically(-1)){
                    swipeLayout.setEnabled(true);
                }else {
                    swipeLayout.setEnabled(false);
                }
            }
        });
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
