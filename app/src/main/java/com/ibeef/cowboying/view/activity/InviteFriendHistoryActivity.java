package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.InviteNotesAdapter;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.view.customview.SuperSwipeRefreshLayout;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.bean.BaseBean;
import rxfamily.view.BaseActivity;

/**
 * @author Administrator
 * 全民推广》邀请记录界面
 */
public class InviteFriendHistoryActivity extends BaseActivity implements SuperSwipeRefreshLayout.OnPullRefreshListener {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    @Bind(R.id.ry_id)
    RecyclerView ryId;
    @Bind(R.id.tv_text_null)
    ImageView tvTextNull;
    @Bind(R.id.rv_bg)
    RelativeLayout rvBg;
    @Bind(R.id.swipe_layout)
    SuperSwipeRefreshLayout swipeLayout;
    private InviteNotesAdapter inviteNotesAdapter;
    private List<BaseBean> beanList;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friend_history);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        token= Hawk.get(HawkKey.TOKEN);

        info.setText("邀请记录");
        beanList=new ArrayList<>();
        for (int i=0;i<4;i++){
            BaseBean baseBean=new BaseBean();
            beanList.add(baseBean);
        }
        swipeLayout.setHeaderViewBackgroundColor(getResources().getColor(R.color.colorAccent));
        swipeLayout.setHeaderView(createHeaderView());// add headerView
        swipeLayout.setTargetScrollWithLayout(true);
        swipeLayout.setOnPullRefreshListener(this);

        ryId.setLayoutManager(new LinearLayoutManager(this));
        inviteNotesAdapter=new InviteNotesAdapter(beanList,this,R.layout.item_invite_notes);
        ryId.setAdapter(inviteNotesAdapter);
        inviteNotesAdapter.setNewData(this.beanList);
        inviteNotesAdapter.loadMoreEnd();
        ryId.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!ryId.canScrollVertically(-1)){
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
        swipeLayout.setRefreshing(false);
    }

    @Override
    public void onPullDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {

    }
}
