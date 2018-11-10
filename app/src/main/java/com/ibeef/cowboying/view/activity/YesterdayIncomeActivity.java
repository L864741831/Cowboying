package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MainFragmentAdapter;
import com.ibeef.cowboying.view.fragment.AddIncomeFragment;
import com.ibeef.cowboying.view.fragment.CowClaimBuyWayFragment;
import com.ibeef.cowboying.view.fragment.CowClaimDesFragment;
import com.ibeef.cowboying.view.fragment.CowClaimSelectFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;
import rxfamily.view.BaseFragment;

/**
 * 昨日收益
 */
public class YesterdayIncomeActivity extends BaseActivity {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.income_money_stadus)
    TextView incomeMoneyStadus;
    @Bind(R.id.income_money_id)
    TextView incomeMoneyId;
    @Bind(R.id.nts_top)
    NavigationTabStrip ntsTop;
    @Bind(R.id.content_vp)
    ViewPager contentVp;
    private boolean isYesterday;
    private static final String[] CHANNELS = new String[]{"0.00", "0.00", "0.00"};
    private MainFragmentAdapter mainFragmentAdapter;
    private AddIncomeFragment addIncomeFragment;
    private AddIncomeFragment addIncomeFragment2;
    private AddIncomeFragment addIncomeFragment3;
    private ArrayList<BaseFragment> fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yesterday_income);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        isYesterday = getIntent().getBooleanExtra("isYesterday", false);
        if(isYesterday){
            info.setText("昨日收益");
            incomeMoneyStadus.setText("昨日收益（元）");
        }else {
            info.setText("累计收益");
            incomeMoneyStadus.setText("累计收益（元）");
        }
        fragmentList=new ArrayList<>();
        addIncomeFragment = AddIncomeFragment.newInstance("1");
        addIncomeFragment2 = AddIncomeFragment.newInstance("1");
        addIncomeFragment3 = AddIncomeFragment.newInstance("1");
        fragmentList.add(addIncomeFragment);
        fragmentList.add(addIncomeFragment2);
        fragmentList.add(addIncomeFragment3);
        mainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager(), fragmentList);
        contentVp.setAdapter(mainFragmentAdapter);

        ntsTop.setViewPager(contentVp, 0);
        ntsTop.setTitles(CHANNELS);
        ntsTop.setStripType(NavigationTabStrip.StripType.LINE);
        ntsTop.setStripGravity(NavigationTabStrip.StripGravity.BOTTOM);
        ntsTop.setTabIndex(0, true);

    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
    }
}
