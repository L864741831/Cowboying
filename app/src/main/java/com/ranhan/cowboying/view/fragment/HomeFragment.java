package com.ranhan.cowboying.view.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ranhan.cowboying.R;
import com.ranhan.cowboying.adapter.HomeProductListAdapter;
import com.ranhan.cowboying.adapter.MyFeedbackAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rxfamily.view.BaseFragment;

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    @Bind(R.id.home_ry_id)
    RecyclerView homeRyId;
    @Bind(R.id.swipe_ly)
    SwipeRefreshLayout swipeLy;

    private HomeProductListAdapter homeProductListAdapter;
    private List<Object> objectList;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        homeRyId.setLayoutManager(new LinearLayoutManager(getHoldingActivity()));
        swipeLy.setColorSchemeResources(R.color.colorAccent);
        swipeLy.setOnRefreshListener(this);
        swipeLy.setEnabled(true);

        objectList=new ArrayList<>();
        homeProductListAdapter=new HomeProductListAdapter(objectList,getHoldingActivity(),R.layout.home_head_view);
        View headView = View.inflate(getHoldingActivity(), R.layout.home_head_view, null);
        init(headView);
        //添加头视图
        homeProductListAdapter.addHeaderView(headView);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    /**
     * 头视图
     * @param view
     */
    public void init(View view) {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {

    }
}
