package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MyMessegeListAdapter;
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
 * 我的消息界面
 */
public class MyMessegeActivity extends BaseActivity  implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.message_ry)
    RecyclerView messageRy;
    @Bind(R.id.swipe_ly)
    SwipeRefreshLayout swipeLy;

    private MyMessegeListAdapter myMessegeListAdapter;
    private List<BaseBean> beanList;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_messege);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        token= Hawk.get(HawkKey.TOKEN);
        info.setText("消息");
        messageRy.setLayoutManager(new LinearLayoutManager(this));
        beanList=new ArrayList<>();
        for (int i=0;i<10;i++){
            BaseBean baseBean=new BaseBean();
            beanList.add(baseBean);
        }
        myMessegeListAdapter=new MyMessegeListAdapter(beanList,this,R.layout.my_messege_item);
        myMessegeListAdapter.setOnLoadMoreListener(this, messageRy);
        messageRy.setAdapter(myMessegeListAdapter);
        swipeLy.setColorSchemeResources(R.color.colorAccent);
        swipeLy.setOnRefreshListener(this);
        swipeLy.setEnabled(true);
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
