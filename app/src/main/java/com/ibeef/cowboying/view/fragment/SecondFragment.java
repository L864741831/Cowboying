package com.ibeef.cowboying.view.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.StoreTopAdapter;
import com.ibeef.cowboying.bean.StoreCarResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.view.activity.StoreCarActivity;
import com.ibeef.cowboying.view.customview.AmountViewStoreBeef;
import com.orhanobut.hawk.Hawk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rxfamily.view.BaseFragment;

public class SecondFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    private RecyclerView ryId;
    private SwipeRefreshLayout swipeLy;
    private TextView txt1_id;
    private int currentPage=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    private String token;
    private List<StoreCarResultBean> baseBeans;
    private StoreTopAdapter storeTopAdapter;
    private LinearLayoutManager layoutManager;
    private RelativeLayout storecars_rv;

    private BroadcastReceiver receiver1,receiver;
    private int num,idChoose,position;
    //目标项是否在最后一个可见项之后
    private boolean mShouldScroll;
    //记录目标项位置
    private int mToPosition;
    private boolean isClick=true;
    private List<StoreCarResultBean> storeCarResultBeans;
    /**
     * 滑动到指定位置
     */
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        storecars_rv=view.findViewById(R.id.storecars_rv);
        storecars_rv.setOnClickListener(this);
        ryId=view.findViewById(R.id.ry_id);

        swipeLy=view.findViewById(R.id.swipe_ly);

        txt1_id=view.findViewById(R.id.txt1_id);
        ryId.setHasFixedSize(true);
        ryId.setNestedScrollingEnabled(false);

        layoutManager=new LinearLayoutManager(getHoldingActivity(), LinearLayoutManager.HORIZONTAL, false);
        ryId.setLayoutManager(layoutManager);


        token = Hawk.get(HawkKey.TOKEN);
        baseBeans = new ArrayList<>();
        storeCarResultBeans = new ArrayList<>();
        for (int i=0;i<10;i++){
            StoreCarResultBean storeCarResultBean=new StoreCarResultBean();
            storeCarResultBean.setDefautChoose(0);
            baseBeans.add(storeCarResultBean);
        }


        storeTopAdapter = new StoreTopAdapter(baseBeans,getHoldingActivity(), R.layout.item_store_top);
        storeTopAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(storeTopAdapter);
        storeTopAdapter.loadMoreEnd();

        storeTopAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId()==R.id.first_go_img){
                    if(position!=baseBeans.size()-1){
                        MoveToPosition(layoutManager,ryId,position+1);
                    }else {
                        MoveToPosition(layoutManager,ryId,position);
                    }
                }else if(view.getId()==R.id.last_go_img){
                    if(position!=0){
                        MoveToPosition(layoutManager,ryId,position-1);
                    }else {
                        MoveToPosition(layoutManager,ryId,position);
                    }
                }

            }
        });

        swipeLy.setColorSchemeResources(R.color.colorAccent);
        swipeLy.setOnRefreshListener(this);
        swipeLy.setEnabled(true);

        if (receiver1 == null) {
            // 设置广播接收器,动态修改布局
            IntentFilter intentFilter1 = new IntentFilter("com.ibeef.cowboying.storenum");
            receiver1 = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    num=intent.getIntExtra("num",0);
                    position=intent.getIntExtra("position",0);
                    baseBeans.get(position).setNum(num);
                    baseBeans.get(position).setChoose(true);
                    isClick=true;
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

        if(receiver==null){
            // 设置广播接收器,动态修改布局
            IntentFilter intentFilter = new IntentFilter("com.ibeef.cowboying.storeaddcar");
            receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if(isClick){
                        storeCarResultBeans.clear();
                        isClick=false;
                        //跳到购物车
                        for(int i=0;i<baseBeans.size();i++){
                            if(baseBeans.get(i).isChoose()){
                                storeCarResultBeans.add(baseBeans.get(i));
                            }
                        }
                        // TODO: 2018/12/4 net 请求加入购物车 storeCarResultBeans>0

                    }
                }
            };
            getHoldingActivity().registerReceiver(receiver, intentFilter);
        }
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
     * RecyclerView 移动到当前位置，
     *
     * @param manager   设置RecyclerView对应的manager
     * @param mRecyclerView  当前的RecyclerView
     * @param n  要跳转的位置
     */
    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {


        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.storecars_rv:
                storeCarResultBeans.clear();
                //跳到购物车
                for(int i=0;i<baseBeans.size();i++){
                    if(baseBeans.get(i).isChoose()){
                        storeCarResultBeans.add(baseBeans.get(i));
                    }
                }
                // TODO: 2018/12/4 net 请求加入购物车storeCarResultBeans>0
                Intent intent=new Intent(getHoldingActivity(),StoreCarActivity.class);
                intent.putExtra("lists",(Serializable) storeCarResultBeans);
                startActivity(intent);
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
        if (receiver != null) {
            getHoldingActivity().unregisterReceiver(receiver);
        }
    }
}
