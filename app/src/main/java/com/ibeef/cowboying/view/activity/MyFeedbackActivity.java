package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MyFeedbackAdapter;
import com.ibeef.cowboying.base.FeedbackBase;
import com.ibeef.cowboying.bean.MyFeedbackResultBean;
import com.ibeef.cowboying.bean.SubmitFeedbackResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.FeedbackPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.bean.BaseBean;
import rxfamily.view.BaseActivity;

/**
 * 我的反馈界面
 */
public class MyFeedbackActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener ,FeedbackBase.IView {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.ry_id)
    RecyclerView ryId;
    @Bind(R.id.swipe_ly)
    SwipeRefreshLayout mSwipeLayout;
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

    private void init(){
        token= Hawk.get(HawkKey.TOKEN);
        info.setText("我的反馈");
        baseBeans=new ArrayList<>();
        ryId.setLayoutManager(new LinearLayoutManager(this));
        myFeedbackAdapter=new MyFeedbackAdapter(this,baseBeans,R.layout.my_feedback_item);
        myFeedbackAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(myFeedbackAdapter);
        mSwipeLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setEnabled(true);

        feedbackPresenter=new FeedbackPresenter(this);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("token",token);
        reqData.put("version",getVersionCodes());
        feedbackPresenter.getMyFeedback(reqData,currentPage);
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onRefresh() {
        currentPage=1;
        isFirst=true;
        baseBeans.clear();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("token",token);
        reqData.put("version",getVersionCodes());
        feedbackPresenter.getMyFeedback(reqData,currentPage);
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void onLoadMoreRequested() {
        isMoreLoad=true;
        currentPage+=1;
        Map<String, String> reqData = new HashMap<>();
        reqData.put("token",token);
        reqData.put("version",getVersionCodes());
        feedbackPresenter.getMyFeedback(reqData,currentPage);
    }

    @Override
    public void showMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void getMyFeedback(MyFeedbackResultBean myFeedbackResultBean) {
        if("000000".equals(myFeedbackResultBean.getCode())){
            if(SDCardUtil.isNullOrEmpty(myFeedbackResultBean.getBizData())){
                if(isFirst){
                    rvOrder.setVisibility(View.VISIBLE);
                    ryId.setVisibility(View.GONE);
                }else {
                    rvOrder.setVisibility(View.GONE);
                    ryId.setVisibility(View.VISIBLE);
                }
                myFeedbackAdapter.loadMoreEnd();
            }else {
                isFirst=false;
                baseBeans.addAll(myFeedbackResultBean.getBizData());
                myFeedbackAdapter.setNewData(this.baseBeans);
                myFeedbackAdapter.loadMoreComplete();
            }
        }else {
            showToast(myFeedbackResultBean.getMessage());
        }

    }

    @Override
    public void getSubmitFeedback(SubmitFeedbackResultBean submitFeedbackResultBean) {

    }

    @Override
    public void showLoading() {
        if(isMoreLoad){
            loadingLayout.setVisibility(View.GONE);
            ryId.setVisibility(View.VISIBLE);
            isMoreLoad=false;
        }else {
            loadingLayout.setVisibility(View.VISIBLE);
            ryId.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        ryId.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        if(feedbackPresenter != null){
            feedbackPresenter.detachView();
        }
        super.onDestroy();
    }
}
