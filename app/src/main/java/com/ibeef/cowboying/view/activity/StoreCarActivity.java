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
import com.ibeef.cowboying.adapter.BuyCowListAdapter;
import com.ibeef.cowboying.base.BuyCowSchemeBase;
import com.ibeef.cowboying.bean.ActiveSchemeResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.BuyCowsSchemePresenter;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

//商城购物车
public class StoreCarActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.go_store_btn)
    ImageView go_store_btn;
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
    @Bind(R.id.rv_order)
    RelativeLayout rvOrder;
    private List<Object> objectList;

    private String token;

    private int currentPage=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    private boolean isclick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_car);
        ButterKnife.bind(this);
        init();
    }
    private void init(){
        token = Hawk.get(HawkKey.TOKEN);
        info.setText("购物车");
        actionNewQuestionTv.setText("编辑");
        actionNewQuestionTv.setVisibility(View.VISIBLE);
        swipeLy.setColorSchemeResources(R.color.colorAccent);
        swipeLy.setOnRefreshListener(this);
        swipeLy.setEnabled(true);
        objectList=new ArrayList<>();
        ryId.setLayoutManager(new LinearLayoutManager(this));
//        buyCowListAdapter=new BuyCowListAdapter(objectList,this,R.layout.item_buy_cows_plan);
//        buyCowListAdapter.setOnLoadMoreListener(this, ryId);
//        ryId.setAdapter(buyCowListAdapter);
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

    }

    @OnClick({R.id.back_id,R.id.action_new_question_tv,R.id.go_store_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.go_store_btn:
                finish();
                break;
            case R.id.action_new_question_tv:
                if(!isclick){
                    actionNewQuestionTv.setText("完成");
                    isclick=true;
                }else {
                    actionNewQuestionTv.setText("编辑");
                    isclick=false;
                }

                break;
            default:
                break;
        }
    }
    @Override
    public void onRefresh() {
        currentPage = 1;
        isFirst = true;
        objectList.clear();
        swipeLy.setRefreshing(false);
    }

    @Override
    public void onLoadMoreRequested() {
        isMoreLoad = true;
        currentPage += 1;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
