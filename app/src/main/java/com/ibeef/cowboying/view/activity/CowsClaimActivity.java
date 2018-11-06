package com.ibeef.cowboying.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MainFragmentAdapter;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.PasturePresenter;
import com.ibeef.cowboying.view.fragment.CowClaimBuyWayFragment;
import com.ibeef.cowboying.view.fragment.CowClaimDesFragment;
import com.ibeef.cowboying.view.fragment.CowClaimPastRecordFragment;
import com.ibeef.cowboying.view.fragment.CowClaimSelectFragment;
import com.ibeef.cowboying.view.fragment.RanchConsociationFragment;
import com.orhanobut.hawk.Hawk;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;
import rxfamily.view.BaseFragment;

/**
 * 买牛方案》》》》认领界面
 */
public class CowsClaimActivity extends BaseActivity {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.banner_img_id)
    ImageView bannerImgId;
    @Bind(R.id.parsture_name_id)
    TextView parstureNameId;
    @Bind(R.id.see_all_pasture_rv)
    RelativeLayout seeAllPastureRv;
    @Bind(R.id.target_txt_id)
    TextView targetTxtId;
    @Bind(R.id.hasidentify_txt_id)
    TextView hasidentifyTxtId;
    @Bind(R.id.price_txt_id)
    TextView priceTxtId;
    @Bind(R.id.isShows_lv_id)
    LinearLayout isShowsLvId;
    @Bind(R.id.nts_top)
    NavigationTabStrip ntsTop;
    @Bind(R.id.content_vp)
    ViewPager contentVp;
    @Bind(R.id.img_id)
    ImageView img_id;
    private static final String[] CHANNELS = new String[]{"项目介绍", "买牛方案", "往期记录", "牛只查询"};
    private List<String> dataBeen = Arrays.asList(CHANNELS);
    private ArrayList<BaseFragment> fragmentList;
    private MainFragmentAdapter mainFragmentAdapter;
    private CowClaimDesFragment cowClaimDesFragment;
    private CowClaimBuyWayFragment cowClaimBuyWayFragment;
    private CowClaimPastRecordFragment cowClaimPastRecordFragment;
    private CowClaimSelectFragment cowClaimSelectFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cows_claim);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("认领");
        fragmentList=new ArrayList<>();
        initMagicIndicator();
    }
    private void initMagicIndicator() {
        cowClaimDesFragment=CowClaimDesFragment.newInstance(dataBeen.get(0));
        cowClaimBuyWayFragment=CowClaimBuyWayFragment.newInstance(dataBeen.get(1));
        cowClaimPastRecordFragment=CowClaimPastRecordFragment.newInstance(dataBeen.get(2));
        cowClaimSelectFragment=CowClaimSelectFragment.newInstance(dataBeen.get(3));
        fragmentList.add(cowClaimDesFragment);
        fragmentList.add(cowClaimBuyWayFragment);
        fragmentList.add(cowClaimPastRecordFragment);
        fragmentList.add(cowClaimSelectFragment);
        mainFragmentAdapter=new MainFragmentAdapter(getSupportFragmentManager(),fragmentList);
        contentVp.setAdapter(mainFragmentAdapter);

        ntsTop.setViewPager(contentVp, 0);
        ntsTop.setTitles(CHANNELS);
        ntsTop.setStripType(NavigationTabStrip.StripType.LINE);
        ntsTop.setStripGravity(NavigationTabStrip.StripGravity.BOTTOM);
        ntsTop.setTabIndex(0, true);
        img_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowsLvId.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                isShowsLvId.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
        return true;
    }

    @OnClick({R.id.back_id, R.id.see_all_pasture_rv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.see_all_pasture_rv:
                Intent intent=new Intent(CowsClaimActivity.this,ChooseParsterActivity.class);
                intent.putExtra("id",18);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
