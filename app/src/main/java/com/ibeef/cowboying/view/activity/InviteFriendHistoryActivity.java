package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * @author Administrator
 * 全民推广》邀请记录界面
 */
public class InviteFriendHistoryActivity extends BaseActivity {


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friend_history);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("邀请记录");
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
    }
}