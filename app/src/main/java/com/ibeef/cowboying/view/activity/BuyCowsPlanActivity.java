package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.BuyCowListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.bean.BaseBean;
import rxfamily.view.BaseActivity;

/**
 * 买牛方案列表
 */
public class BuyCowsPlanActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.swipe_ly)
    SwipeRefreshLayout swipeLy;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    @Bind(R.id.ry_id)
    RecyclerView ryId;
    private BuyCowListAdapter buyCowListAdapter;
    private List<Object> objectList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_cows_plan);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("买牛方案列表");
        actionNewQuestionTv.setText("往期记录");
        actionNewQuestionTv.setVisibility(View.VISIBLE);
        swipeLy.setColorSchemeResources(R.color.colorAccent);
        swipeLy.setOnRefreshListener(this);
        swipeLy.setEnabled(true);
        objectList=new ArrayList<>();
        for (int i=0;i<10;i++){
            objectList.add(new Object());
        }
        ryId.setLayoutManager(new LinearLayoutManager(this));
        buyCowListAdapter=new BuyCowListAdapter(objectList,this,R.layout.item_buy_cows_plan);
        buyCowListAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(buyCowListAdapter);
        ryId.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!ryId.canScrollVertically(-1)){
                    swipeLy.setEnabled(true);
                }else {
                    swipeLy.setEnabled(false);
                }
            }
        });

        buyCowListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId()==R.id.now_claim_btn_id){
                    startActivity(CowsClaimActivity.class);
                }
            }
        });

    }

    @OnClick({R.id.back_id,R.id.action_new_question_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.action_new_question_tv:
                startActivity(CowClaimPastRecordActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        swipeLy.setRefreshing(false);
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
