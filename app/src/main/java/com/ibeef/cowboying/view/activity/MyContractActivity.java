package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.InviteNotesAdapter;
import com.ibeef.cowboying.adapter.MyContractAdapter;
import com.ibeef.cowboying.config.HawkKey;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.bean.BaseBean;
import rxfamily.view.BaseActivity;

/**
 * 我的合同
 */
public class MyContractActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.loading_layout)
    RelativeLayout rvLoading;
    @Bind(R.id.ry_id)
    RecyclerView ryId;
    @Bind(R.id.rv_bg)
    RelativeLayout rvBg;
    @Bind(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    private String token;
    private List<BaseBean> beanList;
    private MyContractAdapter myContractAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contract);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        token= Hawk.get(HawkKey.TOKEN);
        info.setText("我的合同");
        beanList=new ArrayList<>();
        for (int i=0;i<4;i++){
            BaseBean baseBean=new BaseBean();
            beanList.add(baseBean);
        }
        swipeLayout.setOnRefreshListener(this);
        ryId.setLayoutManager(new LinearLayoutManager(this));
        myContractAdapter=new MyContractAdapter(beanList,this,R.layout.item_my_contract);
        myContractAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(myContractAdapter);
        myContractAdapter.setNewData(this.beanList);
        myContractAdapter.loadMoreEnd();
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
    public void onLoadMoreRequested() {

    }
}
