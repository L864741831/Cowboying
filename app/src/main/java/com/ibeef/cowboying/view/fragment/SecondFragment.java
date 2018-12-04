package com.ibeef.cowboying.view.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.FightCattleAdapter;
import com.ibeef.cowboying.adapter.StoreBottomAdapter;
import com.ibeef.cowboying.adapter.StoreTopAdapter;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.utils.CustomGridLayoutManager;
import com.ibeef.cowboying.utils.SmoonRecycleViewUtil;
import com.ibeef.cowboying.view.activity.StoreCarActivity;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.richeditor.RichEditor;
import rxfamily.view.BaseFragment;

public class SecondFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    private ImageView showCarImg;
    private RecyclerView ryId;
    private SwipeRefreshLayout swipeLy;
    private TextView txt1_id;
    private int currentPage=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    private String token;
    private List<Object> baseBeans;
    private StoreTopAdapter storeTopAdapter;
    private LinearLayoutManager layoutManager;

    private BroadcastReceiver receiver1;
    private int num;
    //目标项是否在最后一个可见项之后
    private boolean mShouldScroll;
    //记录目标项位置
    private int mToPosition;
    /**
     * 滑动到指定位置
     */
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        showCarImg=view.findViewById(R.id.show_car_img);
        showCarImg.setOnClickListener(this);
        ryId=view.findViewById(R.id.ry_id);

        swipeLy=view.findViewById(R.id.swipe_ly);

        txt1_id=view.findViewById(R.id.txt1_id);
        ryId.setHasFixedSize(true);
        ryId.setNestedScrollingEnabled(false);

        layoutManager=new LinearLayoutManager(getHoldingActivity(), LinearLayoutManager.HORIZONTAL, false);
        ryId.setLayoutManager(layoutManager);


        token = Hawk.get(HawkKey.TOKEN);
        baseBeans = new ArrayList<>();
        for (int i=0;i<10;i++){
            baseBeans.add(new Object());
        }

        storeTopAdapter = new StoreTopAdapter(baseBeans,getHoldingActivity(), R.layout.item_store_top);
        storeTopAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(storeTopAdapter);
        storeTopAdapter.loadMoreEnd();

        storeTopAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                if(view.getId()==R.id.first_go_img){
//                    if(position!=baseBeans.size()-1){
//                        smoothMoveToPosition(ryId,position+1);
//                    }else {
//                        smoothMoveToPosition(ryId,position);
//                    }
//                }else if(view.getId()==R.id.last_go_img){
//                    if(position!=0){
//                        smoothMoveToPosition(ryId,position-1);
//                    }else {
//                        smoothMoveToPosition(ryId,position);
//                    }
//                }

                if(view.getId()==R.id.see_more_id){
                    //视频查看更多
                }
            }
        });
        ryId.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mShouldScroll&& RecyclerView.SCROLL_STATE_IDLE == newState) {
                    mShouldScroll = false;
                    smoothMoveToPosition(ryId, mToPosition);
                }
            }
        });

        swipeLy.setColorSchemeResources(R.color.colorAccent);
        swipeLy.setOnRefreshListener(this);
        swipeLy.setEnabled(true);


        // 设置广播接收器,动态修改布局
        IntentFilter intentFilter1 = new IntentFilter("com.ibeef.cowboying.storenum");
        receiver1 = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                num=intent.getIntExtra("num",0);
                if(num>0){
                    //网络请求改变购物车
                    txt1_id.setVisibility(View.VISIBLE);
                    txt1_id.setText(num+"");
                }else {
                    txt1_id.setVisibility(View.GONE);
                }
            }
        };
        getHoldingActivity().registerReceiver(receiver1, intentFilter1);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_second;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    /**
     * 滑动到指定位置
     */
    private void smoothMoveToPosition(RecyclerView mRecyclerView, final int position) {
        // 第一个可见位置
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        // 最后一个可见位置
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));
        if (position < firstItem) {
            // 第一种可能:跳转位置在第一个可见位置之前
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            // 第二种可能:跳转位置在第一个可见位置之后
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                mRecyclerView.smoothScrollBy(0, top);
            }
        } else {
            // 第三种可能:跳转位置在最后可见项之后
            mRecyclerView.smoothScrollToPosition(position);
            mToPosition = position;
            mShouldScroll = true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show_car_img:
                //跳到购物车
                startActivity(StoreCarActivity.class);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (receiver1 != null) {
            getHoldingActivity().unregisterReceiver(receiver1);
        }
    }
}
