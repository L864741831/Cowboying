package com.ibeef.cowboying.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.AddMoneyAdapter;
import com.ibeef.cowboying.adapter.CowClaimSelectAdapter;

import java.util.ArrayList;
import java.util.List;

import rxfamily.view.BaseFragment;

public class AddIncomeFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener {

    private String des;
    RelativeLayout loadingLayout;
    RecyclerView ryId;
    private List<Object> objectList;
    private AddMoneyAdapter addMoneyAdapter;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        loadingLayout=view.findViewById(R.id.loading_layout);
        ryId=view.findViewById(R.id.ry_id);
        ryId.setHasFixedSize(true);
        ryId.setNestedScrollingEnabled(false);

        objectList=new ArrayList<>();
        for (int i=0;i<15;i++){
            objectList.add(new Object());
        }
        ryId.setLayoutManager(new LinearLayoutManager(getHoldingActivity()));
        addMoneyAdapter=new AddMoneyAdapter(objectList,getHoldingActivity(),R.layout.item_add_money);
        addMoneyAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(addMoneyAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_income;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            des = args.getString("des");
        }
    }
    public static AddIncomeFragment newInstance(String  des) {

        AddIncomeFragment newFragment = new AddIncomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("des", des);
        newFragment.setArguments(bundle);
        return newFragment;
    }


    @Override
    public void onLoadMoreRequested() {

    }
}
