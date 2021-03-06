package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MainFragmentAdapter;
import com.ibeef.cowboying.view.fragment.DiscountCouponListFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;
import rxfamily.view.BaseFragment;

/**
 * 我的优惠券界面
 */
public class DiscountCouponActivity extends BaseActivity {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    private ViewPager mViewPager;
    private NavigationTabStrip mTopNavigationTabStrip;

    private DiscountCouponListFragment fragment_0;
    private DiscountCouponListFragment fragment_1;


    private ArrayList<BaseFragment> fragmentList;
    private MainFragmentAdapter mAdpter;
    private int index;
    private String[] title = {"已领取", "不可用"};
    private boolean isAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_coupon);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        isAd=getIntent().getBooleanExtra("isAd",false);
        info.setText("我的优惠券");
        index = getIntent().getIntExtra("index", 0);
        mTopNavigationTabStrip = findViewById(R.id.nts_top);
        mViewPager = findViewById(R.id.content_vp);

        fragmentList = new ArrayList<>();

        fragment_0 = DiscountCouponListFragment.newInstance("1");
        fragment_1 = DiscountCouponListFragment.newInstance("2");

        fragmentList.add(fragment_0);
        fragmentList.add(fragment_1);

        mAdpter = new MainFragmentAdapter(getSupportFragmentManager(), fragmentList);

        mViewPager.setAdapter(mAdpter);

        mTopNavigationTabStrip.setViewPager(mViewPager, index);
        mTopNavigationTabStrip.setTitles(title);
        mTopNavigationTabStrip.setStripType(NavigationTabStrip.StripType.LINE);
        mTopNavigationTabStrip.setStripGravity(NavigationTabStrip.StripGravity.BOTTOM);
        mTopNavigationTabStrip.setTabIndex(index, true);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(isAd){
            removeALLActivity();
            startActivity(new Intent(DiscountCouponActivity.this, MainActivity.class));
        }
        finish();
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        if(isAd){
            removeALLActivity();
            startActivity(new Intent(DiscountCouponActivity.this, MainActivity.class));
        }
        finish();
    }
}
