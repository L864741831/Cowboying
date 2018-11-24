package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MainFragmentAdapter;
import com.ibeef.cowboying.utils.NoScrollViewPager;
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
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.nts_top)
    NavigationTabStrip ntsTop;
    @Bind(R.id.content_vp)
    NoScrollViewPager contentVp;
    private NoScrollViewPager mViewPager;
    private NavigationTabStrip mTopNavigationTabStrip;

    private DiscountCouponListFragment fragment_0;
    private DiscountCouponListFragment fragment_1;


    private ArrayList<BaseFragment> fragmentList;
    private MainFragmentAdapter mAdpter;
    private int index;
    private String[] title = {"可使用", "不可用"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_coupon);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        info.setText("我的优惠券");
        index = getIntent().getIntExtra("index", 0);
        mTopNavigationTabStrip = findViewById(R.id.nts_top);
        mViewPager = findViewById(R.id.content_vp);

        fragmentList = new ArrayList<>();

        fragment_0 = DiscountCouponListFragment.newInstance("0");
        fragment_1 = DiscountCouponListFragment.newInstance("1");

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
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
    }
}
