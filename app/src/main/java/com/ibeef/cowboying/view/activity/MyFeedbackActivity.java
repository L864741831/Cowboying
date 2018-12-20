package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MyFeedbackAdapter;
import com.ibeef.cowboying.base.FeedbackBase;
import com.ibeef.cowboying.base.MdUploadImgBean;
import com.ibeef.cowboying.bean.MyFeedbackResultBean;
import com.ibeef.cowboying.bean.SubmitFeedbackResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.FeedbackPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
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

/**
 * 我的反馈界面
 */
public class MyFeedbackActivity extends BaseActivity implements SuperSwipeRefreshLayout.OnPullRefreshListener,BaseQuickAdapter.RequestLoadMoreListener ,FeedbackBase.IView {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.ry_id)
    RecyclerView ryId;
    @Bind(R.id.swipe_ly)
    SuperSwipeRefreshLayout mSwipeLayout;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    @Bind(R.id.rv_order)
    RelativeLayout rvOrder;
    private MyFeedbackAdapter myFeedbackAdapter;
    private List<MyFeedbackResultBean.BizDataBean> baseBeans;
    private FeedbackPresenter feedbackPresenter;
    private String token;

    private int currentPage=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_feedback);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        token = Hawk.get(HawkKey.TOKEN);
        info.setText("我的反馈");
        baseBeans = new ArrayList<>();
        ryId.setLayoutManager(new LinearLayoutManager(this));
        myFeedbackAdapter = new MyFeedbackAdapter(this, baseBeans, R.layout.my_feedback_item);
        myFeedbackAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(myFeedbackAdapter);

        mSwipeLayout.setHeaderViewBackgroundColor(getResources().getColor(R.color.colorAccent));
        mSwipeLayout.setHeaderView(createHeaderView());// add headerView
        mSwipeLayout.setTargetScrollWithLayout(true);
        mSwipeLayout.setOnPullRefreshListener(this);

        feedbackPresenter = new FeedbackPresenter(this);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization", token);
        reqData.put("version", getVersionCodes());
        feedbackPresenter.getMyFeedback(reqData, currentPage);

        ryId.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!ryId.canScrollVertically(-1)){
                    mSwipeLayout.setEnabled(true);
                }else {
                    mSwipeLayout.setEnabled(false);
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
        currentPage = 1;
        isFirst = true;
        baseBeans.clear();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization", token);
        reqData.put("version", getVersionCodes());
        if (!TextUtils.isEmpty(token)) {
            feedbackPresenter.getMyFeedback(reqData, currentPage);
        }
    }

    @Override
    public void onPullDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {

    }

    @Override
    public void onLoadMoreRequested() {
        isMoreLoad = true;
        currentPage += 1;
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization", token);
        reqData.put("version", getVersionCodes());
        feedbackPresenter.getMyFeedback(reqData, currentPage);
    }

    @Override
    public void showMsg(String msg) {
    }

    @Override
    public void getMyFeedback(MyFeedbackResultBean myFeedbackResultBean) {
        if ("000000".equals(myFeedbackResultBean.getCode())) {
            if (SDCardUtil.isNullOrEmpty(myFeedbackResultBean.getBizData())) {
                if (isFirst) {
                    rvOrder.setVisibility(View.VISIBLE);
                    ryId.setVisibility(View.GONE);
                } else {
                    rvOrder.setVisibility(View.GONE);
                    ryId.setVisibility(View.VISIBLE);
                }
                myFeedbackAdapter.loadMoreEnd();
            } else {
                isFirst = false;
                baseBeans.addAll(myFeedbackResultBean.getBizData());
                myFeedbackAdapter.setNewData(this.baseBeans);
                myFeedbackAdapter.loadMoreComplete();
            }
        } else {
            showToast(myFeedbackResultBean.getMessage());
        }

    }

    @Override
    public void getSubmitFeedback(SubmitFeedbackResultBean submitFeedbackResultBean) {

    }

    @Override
    public void showLoading() {
        if (isMoreLoad) {
            loadingLayout.setVisibility(View.GONE);
            ryId.setVisibility(View.VISIBLE);
            isMoreLoad = false;
        } else {
            loadingLayout.setVisibility(View.VISIBLE);
            ryId.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        ryId.setVisibility(View.VISIBLE);
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void getUploadImg(MdUploadImgBean mdUploadImgBean) {

    }

    @Override
    protected void onDestroy() {
        if (feedbackPresenter != null) {
            feedbackPresenter.detachView();
        }
        super.onDestroy();
    }
}
