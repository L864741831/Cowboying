package com.ibeef.cowboying.view.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.StoreBottomAdapter;
import com.ibeef.cowboying.adapter.StoreTopAdapter;
import com.ibeef.cowboying.base.StoreCarBase;
import com.ibeef.cowboying.bean.AddShopCarResultBean;
import com.ibeef.cowboying.bean.AddStoreCarParamBean;
import com.ibeef.cowboying.bean.AddStoreCarResultBean;
import com.ibeef.cowboying.bean.StoreCarNumResultBean;
import com.ibeef.cowboying.bean.StoreInfoListResultBean;
import com.ibeef.cowboying.bean.StoreOneResultBean;
import com.ibeef.cowboying.bean.StorePriductIdParamBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.StoreCarPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.ibeef.cowboying.view.activity.PlayerVideoActivity;
import com.ibeef.cowboying.view.activity.StoreCarActivity;
import com.ibeef.cowboying.view.customview.AmountViewStoreBeef;
import com.ibeef.cowboying.view.customview.OnViewPagerListener;
import com.ibeef.cowboying.view.customview.ViewPagerLayoutManager;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.richeditor.RichEditor;
import rxfamily.view.BaseFragment;

public class SecondFragment extends BaseFragment implements View.OnClickListener,BaseQuickAdapter.RequestLoadMoreListener,StoreCarBase.IView {

    private RecyclerView ryId;
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
    private BroadcastReceiver receiver,receiver1;
    private int num,position;
    private boolean isClick=false;
    private List<AddStoreCarParamBean> storeCarResultBeans;
    private StoreCarPresenter storeCarPresenter;
    private boolean isClickCar=false;
    private ImageView show_des_img;
    private ImageView cow_nine_img;
    private ImageView last_go_img;
    private ImageView first_go_img;
    private TextView nane_beef_id;
    private TextView beef_price_id;
    private TextView beef_stock_id;
    private TextView beef_size_id;
    private TextView see_more_id;
    private RichEditor richEditId;
    private RecyclerView ryBottomId;
    private LinearLayout lv_show_id;
    private AmountViewStoreBeef amountViewBeef;
    private boolean isLoadFirst=true;
    private boolean isNoData=false;
    private List<Integer> productIds;
    private int PRODUCR_ID;
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
        loadingLayout=view.findViewById(R.id.loading_layout);
        cow_nine_img=view.findViewById(R.id.cow_nine_img);
        show_des_img=view.findViewById(R.id.show_des_img);
        nane_beef_id=view.findViewById(R.id.nane_beef_id);
        beef_price_id=view.findViewById(R.id.beef_price_id);
        beef_stock_id=view.findViewById(R.id.beef_stock_id);
        beef_size_id=view.findViewById(R.id.beef_size_id);
        richEditId=view.findViewById(R.id.rich_edit_id);
        ryBottomId=view.findViewById(R.id.ry_bottom_id);
        see_more_id=view.findViewById(R.id.see_more_id);
        first_go_img=view.findViewById(R.id.first_go_img);
        last_go_img=view.findViewById(R.id.last_go_img);
        lv_show_id=view.findViewById(R.id.lv_show_id);
        txt1_id=view.findViewById(R.id.txt1_id);
        amountViewBeef=view.findViewById(R.id.amout_num_id);

        last_go_img.setOnClickListener(this);
        first_go_img.setOnClickListener(this);

        layoutManager = new ViewPagerLayoutManager(getHoldingActivity(), OrientationHelper.HORIZONTAL);
        ryId.setLayoutManager(layoutManager);
        token = Hawk.get(HawkKey.TOKEN);
        PRODUCR_ID = Hawk.get(HawkKey.PRODUCR_ID);

        productIds=new ArrayList<>();

        /**
         * 手势滑动图片
         */
        layoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {

            }

            @Override
            public void onPageRelease(boolean isNext, int position) {

            }

            @Override
            public void onPageSelected(int positions, boolean isBottom) {
                if(positions>position){
                    isShowData();
                }else {
                    if(position==0){
                        return;
                    }
                    position=position-1;
                    MoveToPosition(layoutManager,ryId,position);
                }

            }
        });
        baseBeans = new ArrayList<>();
        storeCarResultBeans = new ArrayList<>();

        storeTopAdapter = new StoreTopAdapter(baseBeans,getHoldingActivity(), R.layout.item_store_top);
        storeTopAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(storeTopAdapter);
        storeTopAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int positions) {
                position=positions;
            }
        });

        if(receiver==null){
            // 点击底部icon时 动态执行假如购物车操作的广播接收器
            IntentFilter intentFilter = new IntentFilter("com.ibeef.cowboying.storeaddcar");
            receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    addCar(false);
                }
            };
            getHoldingActivity().registerReceiver(receiver, intentFilter);
        }

        if(receiver1==null){
            // 点击底部icon时 动态执行假如购物车操作的广播接收器
            IntentFilter intentFilter = new IntentFilter("com.ibeef.cowboying.refreshstore");
            receiver1 = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    //购物车删除商拼，添加商品时刷新数据
                    isClick=false;
                    isClickCar=false;
                    num=0;
                    refreshData();
                }
            };
            getHoldingActivity().registerReceiver(receiver1, intentFilter);
        }


        storeCarPresenter=new StoreCarPresenter(this);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        StorePriductIdParamBean storePriductIdParamBean=new StorePriductIdParamBean();
        if(PRODUCR_ID==0){
            if(productIds.size()>0){
                storePriductIdParamBean.setProductIds(productIds);
                storePriductIdParamBean.setCurrentPage(currentPage);
                storeCarPresenter.getStoreInfoList(reqData,storePriductIdParamBean);
            }else {
                storePriductIdParamBean.setCurrentPage(currentPage);
                storePriductIdParamBean.setProductIds(null);
                storeCarPresenter.getStoreInfoList(reqData,storePriductIdParamBean);
            }
        }else {
            //数据清空重新加载，详情的数据放在第一个
            isClick=false;
            isClickCar=false;
            baseBeans.clear();
            productIds.clear();
            currentPage = 1;
            isFirst = true;
            position=0;
            isNoData=false;
            isMoreLoad=false;
            isLoadFirst=true;
            storeCarPresenter.getStoreOneInfo(reqData,PRODUCR_ID);
        }
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
    public  void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {
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
        if(baseBeans.size()>0){
            initData();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.storecars_rv:
                addCar(true);
                break;
            case R.id.last_go_img:
                if(position==0){
                    return;
                }
                position=position-1;
                MoveToPosition(layoutManager,ryId,position);
                break;
            case R.id.first_go_img:
                isShowData();
                break;
            default:
                break;
        }
    }


    /**
     * 往右划显示数据
     */
    private void isShowData(){
        if (position == baseBeans.size() - 1) {
            if(isNoData){
                if(position!=0){
                    position=0;
                    MoveToPosition(layoutManager,ryId,position);
                }else {
                    position = position + 1;
                    MoveToPosition(layoutManager,ryId,position);
                }
            }else {
                currentPage = 1;
                Map<String, String> reqData = new HashMap<>();
                reqData.put("Authorization", token);
                reqData.put("version", getVersionCodes());

                StorePriductIdParamBean storePriductIdParamBean=new StorePriductIdParamBean();
                if(productIds.size()>0){
                    storePriductIdParamBean.setProductIds(productIds);
                    storePriductIdParamBean.setCurrentPage(currentPage);
                    storeCarPresenter.getStoreInfoList(reqData,storePriductIdParamBean);
                }else {
                    storePriductIdParamBean.setCurrentPage(currentPage);
                    storePriductIdParamBean.setProductIds(null);
                    storeCarPresenter.getStoreInfoList(reqData,storePriductIdParamBean);
                }

            }

        } else {
            position = position + 1;
            MoveToPosition(layoutManager, ryId, position);
        }

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
            }else {
                refreshData();
            }
        }else if (isClickCar){
            //点击购物车
            startActivity(StoreCarActivity.class);
        }else {
            refreshData();
        }


    }

    @Override
    public void onLoadMoreRequested() {
        isMoreLoad = true;
        currentPage = 1;
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        StorePriductIdParamBean storePriductIdParamBean=new StorePriductIdParamBean();
        if(productIds.size()>0){
            storePriductIdParamBean.setProductIds(productIds);
            storePriductIdParamBean.setCurrentPage(currentPage);
            storeCarPresenter.getStoreInfoList(reqData,storePriductIdParamBean);
        }else {
            storePriductIdParamBean.setCurrentPage(currentPage);
            storePriductIdParamBean.setProductIds(null);
            storeCarPresenter.getStoreInfoList(reqData,storePriductIdParamBean);
        }


    }


    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getStoreInfoList(final StoreInfoListResultBean storeInfoListResultBean) {
        if ("000000".equals(storeInfoListResultBean.getCode())) {
            if (SDCardUtil.isNullOrEmpty(storeInfoListResultBean.getBizData())) {
                if (isFirst) {
                    lv_show_id.setVisibility(View.GONE);
                } else {
                    lv_show_id.setVisibility(View.VISIBLE);
                    isNoData=true;
                    position=0;
                    MoveToPosition(layoutManager,ryId,position);
                }
                storeTopAdapter.loadMoreEnd();
            } else {

                isFirst = false;
                baseBeans.addAll(storeInfoListResultBean.getBizData());
                for(int i=0;i<storeInfoListResultBean.getBizData().size();i++){
                    productIds.add(storeInfoListResultBean.getBizData().get(i).getShopProductResVo().getProductId());
                }
                storeTopAdapter.setNewData(this.baseBeans);
                if(isLoadFirst){
                    isLoadFirst=false;
                    initData();
                }
                storeTopAdapter.loadMoreComplete();
            }
        } else {
            showToast(storeInfoListResultBean.getMessage());
        }

    }

    private void initData(){
        RequestOptions options1 = new RequestOptions()
                .skipMemoryCache(true)
                .error(R.mipmap.cowone)
                //跳过内存缓存
                ;
        RequestOptions options2 = new RequestOptions()
                .skipMemoryCache(true)
                .error(R.mipmap.des_info_img)
                //跳过内存缓存
                ;

            if(baseBeans.get(position).getProductImages().size()!=0){
                if(baseBeans.get(position).getProductImages().size()==2){
                    Glide.with(getHoldingActivity()).load(Constant.imageDomain+baseBeans.get(position).getProductImages().get(1).getImageUrl()).apply(options2).into(show_des_img);
                }
            }

        amountViewBeef.setGoods_storage(baseBeans.get(position).getShopProductResVo().getStock());
        amountViewBeef.intEdit(baseBeans.get(position).getCartProductNum()+"");
        amountViewBeef.setOnAmountChangeListener(new AmountViewStoreBeef.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                isClick=true;
                if(amount>baseBeans.get(position).getShopProductResVo().getStock()){
//                    showToast("已达到最大库存！");
                }else {
                    if(baseBeans.get(position).getCartProductNum()>=amount){
                        //为0不能再减
                        if(baseBeans.get(position).getCartProductNum()==0){
                            return;
                        }
                        //达到库存数
                        if(baseBeans.get(position).getCartProductNum()==baseBeans.get(position).getShopProductResVo().getStock()){
                            if(amount==baseBeans.get(position).getShopProductResVo().getStock()){
                                return;
                            }
                        }
                        int nums=baseBeans.get(position).getCartProductNum();
                        num=num-nums;
                        num+=nums-1;
                        baseBeans.get(position).setCartProductNum(nums-1);
                        baseBeans.get(position).getShopProductResVo().setChoose(true);

                    }else {
                        //达到库存数
                        if(baseBeans.get(position).getCartProductNum()==baseBeans.get(position).getShopProductResVo().getStock()){
                            return;
                        }
                        int nums=baseBeans.get(position).getCartProductNum();
                        num=num-nums;
                        num+=nums+1;
                        baseBeans.get(position).setCartProductNum(nums+1);
                        baseBeans.get(position).getShopProductResVo().setChoose(true);
                    }
                    if(num>0){
                        //网络请求改变购物车
                        txt1_id.setVisibility(View.VISIBLE);
                        txt1_id.setText(num+"");
                    }else {
                        txt1_id.setVisibility(View.GONE);
                    }
                }
            }
        });
        Glide.with(getHoldingActivity()).load(Constant.imageDomain+baseBeans.get(position).getCategoryResVo().getImageUrl()).apply(options1).into(cow_nine_img);
        nane_beef_id.setText(baseBeans.get(position).getShopProductResVo().getName());
        beef_price_id.setText("价格："+baseBeans.get(position).getShopProductResVo().getPrice()+"元");
        if(baseBeans.get(position).getShopProductResVo().getStock()<=20){
            beef_stock_id.setVisibility(View.VISIBLE);
            beef_stock_id.setText("仅剩"+baseBeans.get(position).getShopProductResVo().getStock()+"件");
            beef_stock_id.setTextColor(getHoldingActivity().getResources().getColor(R.color.qreds));
        }else {
            beef_stock_id.setVisibility(View.GONE);
        }
        beef_size_id.setText("规格："+baseBeans.get(position).getShopProductResVo().getSpecification());
        richEditId.setEditorFontSize(16);
        richEditId.setEditorFontColor(Color.BLACK);
        richEditId.setInputEnabled(false);
        richEditId.setPadding(3, 5, 5, 5);
        richEditId.loadCSS("file:///android_asset/img.css");
        richEditId.setHtml(baseBeans.get(position).getShopProductResVo().getDescribes());


        ryBottomId.setHasFixedSize(true);
        ryBottomId.setNestedScrollingEnabled(false);
        ryBottomId.setLayoutManager(new GridLayoutManager(getHoldingActivity(),2));

        final List<StoreInfoListResultBean.BizDataBean.ProductVideoResVosBean> baseBean = new ArrayList<>();
        if(baseBeans.get(position).getProductVideoResVos().size()>2){
            for (int i=0;i<2;i++){
                baseBean.add(baseBeans.get(position).getProductVideoResVos().get(i));
            }
            see_more_id.setVisibility(View.VISIBLE);
        }else {
            baseBean.addAll(baseBeans.get(position).getProductVideoResVos());
            see_more_id.setVisibility(View.INVISIBLE);
        }

        final StoreBottomAdapter storeBottomAdapter=new StoreBottomAdapter(baseBean,getHoldingActivity(),R.layout.item_store_bottm);
        ryBottomId.setAdapter(storeBottomAdapter);

        storeBottomAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                StoreInfoListResultBean.BizDataBean.ProductVideoResVosBean items=storeBottomAdapter.getItem(position);
                Intent intent = new Intent(getHoldingActivity(), PlayerVideoActivity.class);
                intent.putExtra("video_url",items.getVideoUrl());
                intent.putExtra("title",items.getName());
                intent.putExtra("coverUrl",items.getVideoCode());
                startActivity(intent);
            }
        });

        see_more_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //视频查看更多

                if(baseBeans.get(position).getProductVideoResVos().size()>2){
                    for (int j=0;j<baseBeans.get(position).getProductVideoResVos().size()-2;j++){
                        baseBean.add(j+2,baseBeans.get(position).getProductVideoResVos().get(j+2));
                        storeBottomAdapter.notifyDataSetChanged();
                        see_more_id.setVisibility(View.INVISIBLE);
                    }
                }else {
                    Toast.makeText(getHoldingActivity(),"没有更多视频~",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void getStoreCarNum(StoreCarNumResultBean storeCarNumResultBean) {
        if("000000".equals(storeCarNumResultBean.getCode())){
            if(storeCarNumResultBean.getBizData()>0){
                //点的的购物车
                if(isClickCar){
                    startActivity(StoreCarActivity.class);
                }
                if(isClick){
                    //添加购物车成功，执行的刷新
                    refreshData();
                }
                //添加成功，重置数据
                isClick=false;
                isClickCar=false;
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
        token = Hawk.get(HawkKey.TOKEN);
        currentPage = 1;
        isFirst = true;
        position=0;
        MoveToPosition(layoutManager,ryId,position);
        isNoData=false;
        isMoreLoad=false;
        isLoadFirst=true;
        baseBeans.clear();
        productIds.clear();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        StorePriductIdParamBean storePriductIdParamBean=new StorePriductIdParamBean();
        if(productIds.size()>0){
            storePriductIdParamBean.setProductIds(productIds);
            storePriductIdParamBean.setCurrentPage(currentPage);
            storeCarPresenter.getStoreInfoList(reqData,storePriductIdParamBean);
        }else {
            storePriductIdParamBean.setCurrentPage(currentPage);
            storePriductIdParamBean.setProductIds(null);
            storeCarPresenter.getStoreInfoList(reqData,storePriductIdParamBean);
        }

        storeCarPresenter.getStoreCarNum(reqData);
    }
    @Override
    public void addStoreCar(AddStoreCarResultBean addStoreCarResultBean) {
        if("000000".equals(addStoreCarResultBean.getCode())){

            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization",token);
            reqData.put("version",getVersionCodes());
            storeCarPresenter.getStoreCarNum(reqData);

        }else {
            showToast(addStoreCarResultBean.getMessage());
        }
    }

    @Override
    public void getStoreOneInfo(StoreOneResultBean storeOneResultBean) {
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        StorePriductIdParamBean storePriductIdParamBean=new StorePriductIdParamBean();
        if("000000".equals(storeOneResultBean.getCode())){
            productIds.add(storeOneResultBean.getBizData().getShopProductResVo().getProductId());
            baseBeans.add(storeOneResultBean.getBizData());

            storePriductIdParamBean.setProductIds(productIds);
            storePriductIdParamBean.setCurrentPage(currentPage);
            storeCarPresenter.getStoreInfoList(reqData,storePriductIdParamBean);
        }else {
            storePriductIdParamBean.setCurrentPage(currentPage);
            storeCarPresenter.getStoreInfoList(reqData,storePriductIdParamBean);
            showToast(storeOneResultBean.getMessage());
        }
    }

    @Override
    public void showLoading() {
        if (isMoreLoad) {
            loadingLayout.setVisibility(View.GONE);
            lv_show_id.setVisibility(View.VISIBLE);
            isMoreLoad = false;
        } else {
            loadingLayout.setVisibility(View.VISIBLE);
            lv_show_id.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        lv_show_id.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            getHoldingActivity().unregisterReceiver(receiver);
        }
        if (receiver1 != null) {
            getHoldingActivity().unregisterReceiver(receiver1);
        }

        if(storeCarPresenter!=null){
            storeCarPresenter.detachView();
        }
    }
}
