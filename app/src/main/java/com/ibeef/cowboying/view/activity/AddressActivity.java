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
import com.ibeef.cowboying.config.HawkKey;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

public class AddressActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

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
    @Bind(R.id.swipe_ly)
    SwipeRefreshLayout swipeLy;
    private int currentPage=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    private List<Object> objectList;

    private String token;
    private GoodsAddrAdapter goodsAddrAdapter;

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
        for (int i=0;i<10;i++){
            objectList.add(new Object());
        }
        goodsAddrAdapter=new GoodsAddrAdapter(objectList,this);
        ryId.setLayoutManager(new LinearLayoutManager(this));
        goodsAddrAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(goodsAddrAdapter);

        goodsAddrAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.add_address_rv:
                        startActivity(EditAddressActivity.class);
                        break;
                    case R.id.delete_addr:
                        //执行删除操作
                        break;
                    case R.id.addr_edit:
                        startActivity(EditAddressActivity.class);
                        break;
                    default:
                        break;
                }
            }
        });

        goodsAddrAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent1=new Intent();
                intent1.setAction("com.ibeef.cowboying.chooseaddr");
                intent1.putExtra("info","item");
                sendBroadcast(intent1);
                finish();
            }
        });
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        isFirst = true;
//        objectList.clear();
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
