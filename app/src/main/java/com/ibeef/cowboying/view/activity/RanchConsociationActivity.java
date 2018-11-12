package com.ibeef.cowboying.view.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MainFragmentAdapter;
import com.ibeef.cowboying.base.PastureBase;
import com.ibeef.cowboying.bean.PastureAllResultBean;
import com.ibeef.cowboying.bean.PastureDetelResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.PasturePresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;
import rxfamily.view.BaseFragment;

/**
 * @author Administrator
 * 合作牧场主界面
 */
public class RanchConsociationActivity extends BaseActivity implements PastureBase.IView{

    private List<PastureAllResultBean.BizDataBean> dataBeen;
    private PasturePresenter pasturePresenter;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    @Bind(R.id.magic_indicator1)
    MagicIndicator magicIndicator;
    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    private ArrayList<BaseFragment> fragmentList;
    private MainFragmentAdapter mainFragmentAdapter;
    private RanchConsociationFragment ranchConsociationFragment;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranch_consociation);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("合作牧场");
        dataBeen = new ArrayList<>();
        fragmentList=new ArrayList<>();
        pasturePresenter = new PasturePresenter(this);
        mainFragmentAdapter=new MainFragmentAdapter(getSupportFragmentManager(),fragmentList);
        token= Hawk.get(HawkKey.TOKEN);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        pasturePresenter.getPastureAllVideo(reqData);
    }
    private void initMagicIndicator() {
        for (int i=0;i<dataBeen.size();i++){
            ranchConsociationFragment=RanchConsociationFragment.newInstance(dataBeen.get(i).getPastureId());
            fragmentList.add(ranchConsociationFragment);
        }
        mViewPager.setAdapter(mainFragmentAdapter);
        magicIndicator.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return dataBeen.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(Color.BLACK);
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.colorGold));
                simplePagerTitleView.setText(dataBeen.get(index).getName());
                simplePagerTitleView.setTextSize(15);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setColors(getResources().getColor(R.color.seekbar_bg_first));
                linePagerIndicator.setLineHeight(UIUtil.dip2px(context, 2));
                return linePagerIndicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }
    @Override
    public void showMsg(String msg) {
        showToast(msg);
    }


    @Override
    public void getPastureAllVideo(PastureAllResultBean pastureAllResultBean) {
        if ("000000".equals(pastureAllResultBean.getCode())) {
            if(SDCardUtil.isNullOrEmpty(pastureAllResultBean.getBizData())){
                return;
            }
            dataBeen.clear();
            dataBeen.addAll(pastureAllResultBean.getBizData());
            initMagicIndicator();
        }
    }

    @Override
    public void getPastureDetelVideo(PastureDetelResultBean pastureDetelResultBean) {

    }

    @Override
    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
        magicIndicator.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        magicIndicator.setVisibility(View.VISIBLE);
    }


    @OnClick({R.id.back_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (pasturePresenter != null) {
            pasturePresenter.detachView();
        }
        super.onDestroy();
    }
}
