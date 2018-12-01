package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.FightCattleAdapter;
import com.ibeef.cowboying.adapter.SellCowsAdapter;
import com.ibeef.cowboying.config.HawkKey;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

public class FightCattleActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.fight_cattle_order)
    ImageView fightCattleOrder;
    @Bind(R.id.ry_id)
    RecyclerView ryId;
    @Bind(R.id.swipe_ly)
    SwipeRefreshLayout swipeLy;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    @Bind(R.id.rv_order)
    RelativeLayout rvOrder;
    private String token;
    private List<Object> baseBeans;
    private FightCattleAdapter fightCattleAdapter;

    private int currentPage=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight_cattle);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        token = Hawk.get(HawkKey.TOKEN);
        baseBeans = new ArrayList<>();
        for (int i=0;i<10;i++){
            baseBeans.add(new Object());
        }
        ryId.setLayoutManager(new LinearLayoutManager(this));
        fightCattleAdapter = new FightCattleAdapter( baseBeans,this, R.layout.item_fight_cattle);
        fightCattleAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(fightCattleAdapter);
        swipeLy.setColorSchemeResources(R.color.colorAccent);
        swipeLy.setOnRefreshListener(this);
        swipeLy.setEnabled(true);

    }

    @OnClick({R.id.back_id, R.id.fight_cattle_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.fight_cattle_order:
                //拼牛订单
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        isFirst = true;
//        baseBeans.clear();
        swipeLy.setRefreshing(false);
    }

    @Override
    public void onLoadMoreRequested() {
        isMoreLoad = true;
        currentPage += 1;
    }

//    @Override
//    public void showLoading() {
//        if (isMoreLoad) {
//            loadingLayout.setVisibility(View.GONE);
//            ryId.setVisibility(View.VISIBLE);
//            isMoreLoad = false;
//        } else {
//            loadingLayout.setVisibility(View.VISIBLE);
//            ryId.setVisibility(View.GONE);
//        }
//    }
//
//    @Override
//    public void hideLoading() {
//        loadingLayout.setVisibility(View.GONE);
//        ryId.setVisibility(View.VISIBLE);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
