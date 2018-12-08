package com.ibeef.cowboying.view.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.StoreTopAdapter;
import com.ibeef.cowboying.base.StoreCarBase;
import com.ibeef.cowboying.bean.AddShopCarResultBean;
import com.ibeef.cowboying.bean.AddStoreCarParamBean;
import com.ibeef.cowboying.bean.AddStoreCarResultBean;
import com.ibeef.cowboying.bean.StoreCarNumResultBean;
import com.ibeef.cowboying.bean.StoreInfoListResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.StoreCarPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.ibeef.cowboying.view.activity.StoreCarActivity;
import com.ibeef.cowboying.view.customview.ViewPagerLayoutManager;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rxfamily.view.BaseFragment;

public class SecondFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener,StoreCarBase.IView {

    private RecyclerView ryId;
    private SwipeRefreshLayout swipeLy;
    private TextView txt1_id;
    private int currentPage=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    private String token;
    private List<StoreInfoListResultBean.BizDataBean> baseBeans;
    private StoreTopAdapter storeTopAdapter;
    private ViewPagerLayoutManager layoutManager;
    private RelativeLayout storecars_rv;
    private RelativeLayout loadingLayout;
    private BroadcastReceiver receiver1,receiver;
    private int num,position;
    private boolean isClick=false;
    private List<AddStoreCarParamBean> storeCarResultBeans;
    private StoreCarPresenter storeCarPresenter;
    private boolean isClickCar=false;
    private boolean isRefresh=false;

    /**
     * 滑动到指定位置
     */
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        storecars_rv=view.findViewById(R.id.storecars_rv);
        storecars_rv.setOnClickListener(this);
        ryId=view.findViewById(R.id.ry_id);
        ryId.setHasFixedSize(true);
        ryId.setNestedScrollingEnabled(false);
        swipeLy=view.findViewById(R.id.swipe_ly);
        loadingLayout=view.findViewById(R.id.loading_layout);

        txt1_id=view.findViewById(R.id.txt1_id);

        layoutManager = new ViewPagerLayoutManager(getHoldingActivity(), OrientationHelper.HORIZONTAL);
        ryId.setLayoutManager(layoutManager);
        token = Hawk.get(HawkKey.TOKEN);

        baseBeans = new ArrayList<>();
        storeCarResultBeans = new ArrayList<>();

        storeTopAdapter = new StoreTopAdapter(baseBeans,getHoldingActivity(), R.layout.item_store_top);
        storeTopAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(storeTopAdapter);
        storeTopAdapter.loadMoreEnd();

        storeTopAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int positions) {
                Intent intent1=new Intent();
                intent1.setAction("com.ibeef.cowboying.storenum");
                if(view.getId()==R.id.first_go_img){
                    if(positions!=baseBeans.size()-1){
                        MoveToPosition(layoutManager,ryId,positions+1);
                    }else {
                        MoveToPosition(layoutManager,ryId,positions);
                    }
                }else if(view.getId()==R.id.last_go_img){
                    if(positions!=0){
                        MoveToPosition(layoutManager,ryId,positions-1);
                    }else {
                        MoveToPosition(layoutManager,ryId,positions);
                    }
                }else if(view.getId()==R.id.btnDecrease){
                    position=positions;
                    //为0不能再减
                    if(baseBeans.get(positions).getCartProductNum()==0){
                        return;
                    }
                    int nums=baseBeans.get(positions).getCartProductNum();
                    num=num-nums;
                    num+=nums-1;
                    baseBeans.get(positions).setCartProductNum(nums-1);
                    baseBeans.get(positions).getShopProductResVo().setChoose(true);

                    storeTopAdapter.notifyItemChanged(positions);
                    getHoldingActivity().sendBroadcast(intent1);
                }else if(view.getId()==R.id.btnIncrease){
                    //达到库存数
                    if(baseBeans.get(positions).getCartProductNum()==baseBeans.get(positions).getShopProductResVo().getStock()){
                        return;
                    }
                    int nums=baseBeans.get(positions).getCartProductNum();
                     num=num-nums;
                     num+=nums+1;
                    baseBeans.get(positions).setCartProductNum(nums+1);
                    baseBeans.get(positions).getShopProductResVo().setChoose(true);

                    storeTopAdapter.notifyItemChanged(positions);
                    getHoldingActivity().sendBroadcast(intent1);
                }

            }
        });

        swipeLy.setColorSchemeResources(R.color.colorAccent);
        swipeLy.setOnRefreshListener(this);
        swipeLy.setEnabled(true);

        if (receiver1 == null) {
            // 点击商品加减时动态更新购物车数量的广播接收器
            IntentFilter intentFilter1 = new IntentFilter("com.ibeef.cowboying.storenum");
            receiver1 = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
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
            // 点击底部icon时 动态执行假如购物车操作的广播接收器
            IntentFilter intentFilter = new IntentFilter("com.ibeef.cowboying.storeaddcar");
            receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    isRefresh=false;
                    addCar(false);
                }
            };
            getHoldingActivity().registerReceiver(receiver, intentFilter);
        }
        storeCarPresenter=new StoreCarPresenter(this);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        storeCarPresenter.getStoreInfoList(reqData,currentPage);
        storeCarPresenter.getStoreCarNum(reqData);
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
                isRefresh=false;
                addCar(true);
                break;
            default:
                break;
        }
    }



    @Override
    public void onRefresh() {
        isRefresh=true;
        addCar(false);
        swipeLy.setRefreshing(false);

    }

    /**
     * isAddCar 是否点击的是购物车
     * @param isAddCar
     */
    private void addCar(boolean isAddCar){
        isClickCar=isAddCar;
        //isClick 是否执行了商品数量加减的操作
        if(isClick){
            //清空数据，避免数据重复加
            storeCarResultBeans.clear();
            for(int i=0;i<baseBeans.size();i++){
                if(baseBeans.get(i).getShopProductResVo().isChoose()){
                    AddStoreCarParamBean addStoreCarParamBean=new AddStoreCarParamBean();
                    addStoreCarParamBean.setProductId(baseBeans.get(i).getShopProductResVo().getProductId());
                    addStoreCarParamBean.setQuantity(baseBeans.get(i).getCartProductNum());
                    storeCarResultBeans.add(addStoreCarParamBean);
                    Log.e(Constant.TAG,addStoreCarParamBean.getQuantity()+"购物车？？"+addStoreCarParamBean.getProductId());
                }
            }
            //如果有数据，则执行加入购物车的操作
            if(storeCarResultBeans.size()>0){
                for(int i=0;i<baseBeans.size();i++){
                    baseBeans.get(i).getShopProductResVo().setChoose(false);
                }
                Map<String, String> reqData = new HashMap<>();
                reqData.put("Authorization",token);
                reqData.put("version",getVersionCodes());
                AddShopCarResultBean addShopCarResultBean=new AddShopCarResultBean();
                addShopCarResultBean.setShopCartReqVos(storeCarResultBeans);
                storeCarPresenter.addStoreCar(reqData,addShopCarResultBean);
            }else if (isClickCar){
                //点击购物车
                startActivity(StoreCarActivity.class);
            }else if(isRefresh){
                //如果是刷新，刷新列表，购物车数据
                refreshData();
            }
        }else if (isClickCar){
            startActivity(StoreCarActivity.class);
        }else if(isRefresh){
            //如果是刷新，刷新列表，购物车数据
            refreshData();
        }
    }

    @Override
    public void onLoadMoreRequested() {
        isMoreLoad = true;
        currentPage += 1;
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        storeCarPresenter.getStoreInfoList(reqData,currentPage);
    }


    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getStoreInfoList(StoreInfoListResultBean storeInfoListResultBean) {
        if ("000000".equals(storeInfoListResultBean.getCode())) {
            if (SDCardUtil.isNullOrEmpty(storeInfoListResultBean.getBizData())) {
                if (isFirst) {
                    ryId.setVisibility(View.GONE);
                } else {
                    ryId.setVisibility(View.VISIBLE);
                }
                storeTopAdapter.loadMoreEnd();
            } else {
                isFirst = false;
                baseBeans.addAll(storeInfoListResultBean.getBizData());
                storeTopAdapter.setNewData(this.baseBeans);
                storeTopAdapter.loadMoreComplete();
            }
        } else {
            showToast(storeInfoListResultBean.getMessage());
        }

    }

    @Override
    public void getStoreCarNum(StoreCarNumResultBean storeCarNumResultBean) {
        if("000000".equals(storeCarNumResultBean.getCode())){
            if(storeCarNumResultBean.getBizData()>0){
                //网络请求改变购物车
                txt1_id.setVisibility(View.VISIBLE);
                //重置购物车的数量，避免累加
                num=0;
                num=storeCarNumResultBean.getBizData();
                txt1_id.setText(num+"");
            }else {
                txt1_id.setVisibility(View.GONE);
            }

        }

    }

    /**
     * 刷新的时候，加入购物车成功，再刷新数据
     */
    private void refreshData(){
        //先加入购物车在刷新
        currentPage = 1;
        isFirst = true;
        baseBeans.clear();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        storeCarPresenter.getStoreInfoList(reqData,currentPage);
        storeCarPresenter.getStoreCarNum(reqData);
    }
    @Override
    public void addStoreCar(AddStoreCarResultBean addStoreCarResultBean) {
        if("000000".equals(addStoreCarResultBean.getCode())){
            //点的的购物车
            if(isClickCar){
                startActivity(StoreCarActivity.class);
            }
            //执行的刷新
            if(isRefresh){
                refreshData();
            }
            //添加成功，重置数据
            isClick=false;
            isClickCar=false;
        }else {
            showToast(addStoreCarResultBean.getMessage());
        }
    }

    @Override
    public void showLoading() {
        if (isMoreLoad) {
            loadingLayout.setVisibility(View.GONE);
            ryId.setVisibility(View.VISIBLE);
            isMoreLoad = false;
        } else {
            loadingLayout.setVisibility(View.VISIBLE);
            ryId.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        ryId.setVisibility(View.VISIBLE);
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
        if(storeCarPresenter!=null){
            storeCarPresenter.detachView();
        }
    }
}
