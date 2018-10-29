package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MyFeedbackAdapter;
import com.ibeef.cowboying.base.FeedbackBase;
import com.ibeef.cowboying.bean.MyFeedbackResultBean;
import com.ibeef.cowboying.bean.SubmitFeedbackResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.FeedbackPresenter;
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
    private MyFeedbackAdapter myFeedbackAdapter;
    private List<BaseBean> baseBeans;
    private FeedbackPresenter feedbackPresenter;
    private String token;
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
        BaseBean baseBean=new BaseBean();
        baseBean.setStatus("0");
        baseBeans.add(baseBean);
        BaseBean baseBean1=new BaseBean();
        baseBean1.setStatus("1");
        baseBeans.add(baseBean1);
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
        feedbackPresenter.getMyFeedback(reqData);
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onRefresh() {
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getMyFeedback(MyFeedbackResultBean myFeedbackResultBean) {

    }

    @Override
    public void getSubmitFeedback(SubmitFeedbackResultBean submitFeedbackResultBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        if(feedbackPresenter != null){
            feedbackPresenter.detachView();
        }
        super.onDestroy();
    }
}