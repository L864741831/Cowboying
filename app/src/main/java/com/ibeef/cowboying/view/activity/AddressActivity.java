package com.ibeef.cowboying.view.activity;

import android.content.Intent;
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
import com.ibeef.cowboying.adapter.GoodsAddrAdapter;
import com.ibeef.cowboying.base.ModifyAddressBase;
import com.ibeef.cowboying.bean.DeleteCarResultBean;
import com.ibeef.cowboying.bean.ShowAddressResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.ModifyAddressPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

public class AddressActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener,ModifyAddressBase.IView {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    @Bind(R.id.ry_id)
    RecyclerView ryId;
    @Bind(R.id.rv_order)
    RelativeLayout rvOrder;
    @Bind(R.id.add_address_rv)
    RelativeLayout addaddressRv;
    @Bind(R.id.swipe_ly)
    SwipeRefreshLayout swipeLy;
    private int currentPage=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    private List<ShowAddressResultBean.BizDataBean> objectList;

    private String token;
    private GoodsAddrAdapter goodsAddrAdapter;
    private ModifyAddressPresenter modifyAddressPresenter;
    private   Map<String, String> reqData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        init();
    }
    private void init(){
        token = Hawk.get(HawkKey.TOKEN);
        info.setText("收货地址");
        swipeLy.setColorSchemeResources(R.color.colorAccent);
        swipeLy.setOnRefreshListener(this);
        swipeLy.setEnabled(true);
        objectList=new ArrayList<>();
        goodsAddrAdapter=new GoodsAddrAdapter(objectList,this);
//        ryId.setHasFixedSize(true);
//        ryId.setNestedScrollingEnabled(false);
        ryId.setLayoutManager(new LinearLayoutManager(this));
        goodsAddrAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(goodsAddrAdapter);
        reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        goodsAddrAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ShowAddressResultBean.BizDataBean item=goodsAddrAdapter.getItem(position);
                switch (view.getId()){
                    case R.id.delete_addr:
                        //执行删除操作
                        modifyAddressPresenter.deleteAddress(reqData,item.getId());
                        break;
                    case R.id.addr_edit:
                        Intent intent=new Intent(AddressActivity.this,EditAddressActivity.class);
                        intent.putExtra("addaddress",false);
                        intent.putExtra("infos",item);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });

        goodsAddrAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShowAddressResultBean.BizDataBean item=goodsAddrAdapter.getItem(position);
                Intent intent1=new Intent();
                intent1.setAction("com.ibeef.cowboying.chooseaddr");
                intent1.putExtra("info",item);
                sendBroadcast(intent1);
                finish();
            }
        });
        modifyAddressPresenter=new ModifyAddressPresenter(this);
        modifyAddressPresenter.showAddressList(reqData,currentPage);
    }

    @OnClick({R.id.back_id,R.id.add_address_rv})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.add_address_rv:
                Intent intent=new Intent(AddressActivity.this,EditAddressActivity.class);
                intent.putExtra("addaddress",true);
                startActivity(intent);
                break;
            case R.id.back_id:
                finish();
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
        modifyAddressPresenter.showAddressList(reqData,currentPage);
        swipeLy.setRefreshing(false);
    }

    @Override
    public void onLoadMoreRequested() {
        isMoreLoad = true;
        currentPage += 1;
        modifyAddressPresenter.showAddressList(reqData,currentPage);
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void addAddress(DeleteCarResultBean deleteCarResultBean) {

    }

    @Override
    public void showAddressList(ShowAddressResultBean showAddressResultBean) {
        if ("000000".equals(showAddressResultBean.getCode())) {
            if (SDCardUtil.isNullOrEmpty(showAddressResultBean.getBizData())) {
                if (isFirst) {
                    rvOrder.setVisibility(View.VISIBLE);
                    ryId.setVisibility(View.GONE);
                } else {
                    rvOrder.setVisibility(View.GONE);
                    ryId.setVisibility(View.VISIBLE);
                }
                goodsAddrAdapter.loadMoreEnd();
            } else {
                isFirst = false;
                objectList.addAll(showAddressResultBean.getBizData());
                goodsAddrAdapter.setNewData(this.objectList);
                goodsAddrAdapter.loadMoreComplete();
            }
        } else {
            showToast(showAddressResultBean.getMessage());
        }
    }

    @Override
    public void updateAddress(DeleteCarResultBean deleteCarResultBean) {

    }

    @Override
    public void deleteAddress(DeleteCarResultBean deleteCarResultBean) {

        if("000000".equals(deleteCarResultBean.getCode())){
            currentPage = 1;
            isFirst = true;
            objectList.clear();
            isMoreLoad=false;
            modifyAddressPresenter.showAddressList(reqData,currentPage);
            showToast("地址删除成功！");
        }else {
            showToast(deleteCarResultBean.getMessage());
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
    protected void onDestroy() {
        super.onDestroy();
        if(modifyAddressPresenter!=null){
            modifyAddressPresenter.detachView();
        }
    }
}
