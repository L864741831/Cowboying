package com.ibeef.cowboying.view.activity;

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
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;
import rxfamily.view.BaseFragment;

public class MyCowsActivity extends BaseActivity {


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
    private String[] title = {"全部", "待付款", "待分配", "可出售", "出售中"};
    private ArrayList<BaseFragment> fragmentList;
    private MyCowsListFragment fragment_0;
    private MyCowsListFragment fragment_1;
    private MyCowsListFragment fragment_2;
    private MyCowsListFragment fragment_3;
    private MyCowsListFragment fragment_4;
    private MainFragmentAdapter mAdpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cows);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        token = Hawk.get(HawkKey.TOKEN);
        info.setText("我的牛只");
        actionNewQuestionTv.setVisibility(View.VISIBLE);
        actionNewQuestionTv.setText("我要卖牛");

        fragmentList = new ArrayList<>();
        fragment_0 = MyCowsListFragment.newInstance("");
        fragment_1 = MyCowsListFragment.newInstance("0");
        fragment_2 = MyCowsListFragment.newInstance("1");
        fragment_3 = MyCowsListFragment.newInstance("2");
        fragment_4 = MyCowsListFragment.newInstance("3");

        fragmentList.add(fragment_0);
        fragmentList.add(fragment_1);
        fragmentList.add(fragment_2);
        fragmentList.add(fragment_3);
        fragmentList.add(fragment_4);
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
                finish();
                break;
            case R.id.action_new_question_tv:
                break;
        }
    }
}
