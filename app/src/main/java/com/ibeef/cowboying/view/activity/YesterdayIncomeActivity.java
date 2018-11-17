package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MainFragmentAdapter;
import com.ibeef.cowboying.base.AddMoneyBase;
import com.ibeef.cowboying.bean.AddMoneyResultBean;
import com.ibeef.cowboying.bean.YesterdayIncomeResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.AddMoneyPresenter;
import com.ibeef.cowboying.view.fragment.AddIncomeFragment;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;
import rxfamily.view.BaseFragment;

/**
 * 昨日收益
 */
public class YesterdayIncomeActivity extends BaseActivity implements AddMoneyBase.IView {

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
    private  String[] CHANNELS;
    private MainFragmentAdapter mainFragmentAdapter;
    private AddIncomeFragment addIncomeFragment;
    private AddIncomeFragment addIncomeFragment2;
    private AddIncomeFragment addIncomeFragment3;
    private ArrayList<BaseFragment> fragmentList;
    private String token,incomeType;
    private AddMoneyPresenter addMoneyPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yesterday_income);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        token= Hawk.get(HawkKey.TOKEN);
        isYesterday = getIntent().getBooleanExtra("isYesterday", false);
        if(isYesterday){
            info.setText("昨日收益");
            incomeMoneyStadus.setText("昨日收益（元）");
            incomeType="1";
        }else {
            info.setText("累计收益");
            incomeMoneyStadus.setText("累计收益（元）");
            incomeType="2";
        }
        fragmentList=new ArrayList<>();
        addIncomeFragment = AddIncomeFragment.newInstance("1",incomeType);
        addIncomeFragment2 = AddIncomeFragment.newInstance("2",incomeType);
        addIncomeFragment3 = AddIncomeFragment.newInstance("3",incomeType);
        fragmentList.add(addIncomeFragment);
        fragmentList.add(addIncomeFragment2);
        fragmentList.add(addIncomeFragment3);
        mainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager(), fragmentList);
        contentVp.setAdapter(mainFragmentAdapter);

        addMoneyPresenter=new AddMoneyPresenter(this);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        addMoneyPresenter.getYesterdayIncome(reqData,incomeType);
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getAddMoney(AddMoneyResultBean accountRegisterResultBean) {

    }

    @Override
    public void getYesterdayIncome(YesterdayIncomeResultBean yesterdayIncomeResultBean) {

        if("000000".equals(yesterdayIncomeResultBean.getCode())){
            incomeMoneyId.setText(yesterdayIncomeResultBean.getBizData().getCumulativeIncome()+"");
            CHANNELS= new String[]{""+yesterdayIncomeResultBean.getBizData().getAdoptCattleIncome(), ""+yesterdayIncomeResultBean.getBizData().getGroupCattleIncome(), ""+yesterdayIncomeResultBean.getBizData().getRecommendIncome()};
            ntsTop.setViewPager(contentVp, 0);
            ntsTop.setTitles(CHANNELS);
            ntsTop.setStripType(NavigationTabStrip.StripType.LINE);
            ntsTop.setStripGravity(NavigationTabStrip.StripGravity.BOTTOM);
            ntsTop.setTabIndex(0, true);
        }else {
            showToast(yesterdayIncomeResultBean.getMessage());
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        if(addMoneyPresenter!=null){
            addMoneyPresenter.detachView();
        }
        super.onDestroy();
    }
}
