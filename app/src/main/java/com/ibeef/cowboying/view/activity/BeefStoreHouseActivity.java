package com.ibeef.cowboying.view.activity;

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
import com.ibeef.cowboying.adapter.BeefHourseAdapter;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.view.customview.SuperSwipeRefreshLayout;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.bean.BaseBean;
import rxfamily.view.BaseActivity;

/**
 * 牛肉仓库
 */
public class BeefStoreHouseActivity extends BaseActivity implements SuperSwipeRefreshLayout.OnPullRefreshListener, BaseQuickAdapter.RequestLoadMoreListener{

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.get_goods_record)
    ImageView getGoodsRecord;
    @Bind(R.id.beef_money_id)
    TextView beefMoneyId;
    @Bind(R.id.question_show_id)
    TextView questionShowId;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    @Bind(R.id.ry_id)
    RecyclerView ryId;
    @Bind(R.id.limit_weight_delevery_id)
    TextView limitWeightDeleveryId;
    @Bind(R.id.order_weight_id)
    TextView orderWeightId;
    @Bind(R.id.get_goods_btn)
    TextView getGoodsBtn;
    @Bind(R.id.rv_bottom_id)
    RelativeLayout rvBottomId;
    @Bind(R.id.swipe_layout)
    SuperSwipeRefreshLayout swipeLayout;
    private List<BaseBean> beanList;
    private BeefHourseAdapter beefHourseAdapter;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beef_store_house);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        token= Hawk.get(HawkKey.TOKEN);
        beanList=new ArrayList<>();
        for (int i=0;i<14;i++){
            BaseBean baseBean=new BaseBean();
            beanList.add(baseBean);
        }
        ryId.setLayoutManager(new LinearLayoutManager(this));
        ryId.setHasFixedSize(true);
        ryId.setNestedScrollingEnabled(false);

        swipeLayout.setHeaderViewBackgroundColor(getResources().getColor(R.color.colorAccent));
        swipeLayout.setHeaderView(createHeaderView());// add headerView
        swipeLayout.setTargetScrollWithLayout(true);
        swipeLayout.setOnPullRefreshListener(this);

        beefHourseAdapter=new BeefHourseAdapter(beanList,this,R.layout.item_beef_hourse);
        beefHourseAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(beefHourseAdapter);
        beefHourseAdapter.setNewData(this.beanList);
        beefHourseAdapter.loadMoreEnd();

    }

    @OnClick({R.id.back_id, R.id.get_goods_record, R.id.question_show_id, R.id.get_goods_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.get_goods_record:
                startActivity(GetGoodsRecordActivity.class);
                break;
            case R.id.question_show_id:
                startActivity(BeefHourseDialog.class);
                break;
            case R.id.get_goods_btn:
                break;
            default:
                break;
        }
    }

    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public void onRefresh() {
        swipeLayout.setRefreshing(false);
    }

    @Override
    public void onPullDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {

    }
}
