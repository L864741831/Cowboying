package com.ibeef.cowboying.view.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.AssetDetailListAdapter;
import com.ibeef.cowboying.adapter.CashDespositListAdapter;
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
 * 提现记录
 */
public class CashDepositActivity extends BaseActivity  implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{
    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.message_ry)
    RecyclerView messageRy;
    @Bind(R.id.swipe_ly)
    SwipeRefreshLayout swipeLy;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    private CashDespositListAdapter cashDespositListAdapter;
    private List<BaseBean> beanList;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_deposit);
        ButterKnife.bind(this);
        init();
    }
    private void init(){
        token= Hawk.get(HawkKey.TOKEN);
        info.setText("提现记录");
        messageRy.setLayoutManager(new LinearLayoutManager(this));
        messageRy.setHasFixedSize(true);
        messageRy.setNestedScrollingEnabled(false);
        beanList=new ArrayList<>();
        for (int i=0;i<10;i++){
            BaseBean baseBean=new BaseBean();
            beanList.add(baseBean);
        }
        cashDespositListAdapter=new CashDespositListAdapter(beanList,this,R.layout.item_cash_deposit);
        cashDespositListAdapter.setOnLoadMoreListener(this, messageRy);
        messageRy.setAdapter(cashDespositListAdapter);
        swipeLy.setColorSchemeResources(R.color.colorAccent);
        swipeLy.setOnRefreshListener(this);
        swipeLy.setEnabled(true);

        messageRy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!messageRy.canScrollVertically(-1)){
                    swipeLy.setEnabled(true);
                }else {
                    swipeLy.setEnabled(false);
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
        swipeLy.setRefreshing(false);
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
