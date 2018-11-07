package com.ibeef.cowboying.view.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.BuyCowListAdapter;
import com.ibeef.cowboying.adapter.CowClaimPastRecordAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 买牛往期记录
 */
public class CowClaimPastRecordActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{
    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.swipe_ly)
    SwipeRefreshLayout swipeLy;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    @Bind(R.id.ry_id)
    RecyclerView ryId;
    private CowClaimPastRecordAdapter cowClaimPastRecordAdapter;
    private List<Object> objectList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cow_claim_past_record);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        info.setText("往期记录");
        objectList=new ArrayList<>();
        for (int i=0;i<10;i++){
            objectList.add(new Object());
        }
        swipeLy.setColorSchemeResources(R.color.colorAccent);
        swipeLy.setOnRefreshListener(this);
        swipeLy.setEnabled(true);
        ryId.setLayoutManager(new LinearLayoutManager(this));
        cowClaimPastRecordAdapter=new CowClaimPastRecordAdapter(objectList,this,R.layout.item_cows_cliam_pastrecord);
        cowClaimPastRecordAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(cowClaimPastRecordAdapter);
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

        cowClaimPastRecordAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId()==R.id.see_people_num_rv){
                    startActivity(JionPeopleNumActivity.class);
                }
            }
        });

    }
    @OnClick({R.id.back_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
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
