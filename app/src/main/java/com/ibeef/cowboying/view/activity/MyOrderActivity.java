package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MainFragmentAdapter;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.view.fragment.MyCowsListFragment;
import com.ibeef.cowboying.view.fragment.MyOrderListFragment;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;
import rxfamily.view.BaseFragment;

public class MyOrderActivity extends BaseActivity {


    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.nts_top)
    NavigationTabStrip ntsTop;
    @Bind(R.id.content_vp)
    ViewPager contentVp;
    private String token;
    private int index;
    private String[] title = {"全部", "待付款", "待发货", "待收货", "待取货","退款中"};
    private ArrayList<BaseFragment> fragmentList;
    private MyOrderListFragment fragment_0;
    private MyOrderListFragment fragment_1;
    private MyOrderListFragment fragment_2;
    private MyOrderListFragment fragment_3;
    private MyOrderListFragment fragment_4;
    private MyOrderListFragment fragment_5;
    private MainFragmentAdapter mAdpter;
    private boolean from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cows);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        from=getIntent().getBooleanExtra("from",false);
        token = Hawk.get(HawkKey.TOKEN);
        info.setText("我的牛只");

        fragmentList = new ArrayList<>();

//（0：未支付；1：已支付；2：已发货；3：确认收货；4：退款中；5：已退款；6：已取消；7:待取货（库中无此值，仅用于查询时表示线下门店取货方式））如果取所以的，则传空
        fragment_0 = MyOrderListFragment.newInstance("");
        fragment_1 = MyOrderListFragment.newInstance("0");
        fragment_2 = MyOrderListFragment.newInstance("1");
        fragment_3 = MyOrderListFragment.newInstance("2");
        fragment_4 = MyOrderListFragment.newInstance("7");
        fragment_5 = MyOrderListFragment.newInstance("4");

        fragmentList.add(fragment_0);
        fragmentList.add(fragment_1);
        fragmentList.add(fragment_2);
        fragmentList.add(fragment_3);
        fragmentList.add(fragment_4);
        fragmentList.add(fragment_5);
        mAdpter = new MainFragmentAdapter(getSupportFragmentManager(), fragmentList);
        contentVp.setAdapter(mAdpter);
        ntsTop.setViewPager(contentVp, index);
        ntsTop.setTitles(title);
        ntsTop.setStripType(NavigationTabStrip.StripType.LINE);
        ntsTop.setStripGravity(NavigationTabStrip.StripGravity.BOTTOM);
        ntsTop.setTabIndex(index, true);
    }


    @OnClick({R.id.back_id, R.id.action_new_question_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                if(from){
                    Intent intent1=new Intent(MyOrderActivity.this,MainActivity.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent1);
                }else {
                    finish();
                }
                break;
            default:
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (from){
            Intent intent1=new Intent(MyOrderActivity.this,MainActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent1);
        }
    }
}
