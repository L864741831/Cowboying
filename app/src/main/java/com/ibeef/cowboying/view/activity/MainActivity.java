package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Toast;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.CheckVersionBase;
import com.ibeef.cowboying.bean.CheckVersionBean;
import com.ibeef.cowboying.bean.CheckVersionParamBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.CheckVersionPresenter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.ibeef.cowboying.adapter.MainFragmentAdapter;
import com.ibeef.cowboying.view.fragment.HomeFragment;
import com.ibeef.cowboying.view.fragment.SecondFragment;
import com.ibeef.cowboying.view.fragment.ThreeFragment;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rxfamily.view.BaseActivity;
import rxfamily.view.BaseFragment;

/**
 * 主页面
 */
public class MainActivity extends BaseActivity implements CheckVersionBase.IView{

    @Bind(R.id.bnve)
    BottomNavigationViewEx bnve;
    @Bind(R.id.vp)
    ViewPager vp;
    private ArrayList<BaseFragment> fragments;
    private CheckVersionPresenter checkVersionPresenter;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        initEvent();

    }

    private void init(){
        token= Hawk.get(HawkKey.TOKEN);
        bnve.enableAnimation(false);
        bnve.enableShiftingMode(false);
        bnve.enableItemShiftingMode(false);
        bnve.setTextSize(12);
        bnve.setIconSize(30,30);
        bnve.setIconsMarginTop(13);
        bnve.setItemIconTintList(null);
        fragments=new ArrayList<>();
        HomeFragment homeFragment=new HomeFragment();
        SecondFragment secondFragment=new SecondFragment();
        ThreeFragment threeFragment=new ThreeFragment();
        fragments.add(homeFragment);
        fragments.add(secondFragment);
        fragments.add(threeFragment);

        MainFragmentAdapter mAdpter = new MainFragmentAdapter(getSupportFragmentManager(),fragments);
        vp.setAdapter(mAdpter);
        bnve.setupWithViewPager(vp);

        checkVersionPresenter=new CheckVersionPresenter(this);
        CheckVersionParamBean checkVersionParamBean=new CheckVersionParamBean();
        checkVersionParamBean.setAppType("1");
        checkVersionParamBean.setVersion(getVersionCodes());
        checkVersionPresenter.getCheckVersion(getVersionCodes(),checkVersionParamBean);

    }

    private void initEvent() {
        bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.i_three) {
                    token= Hawk.get(HawkKey.TOKEN);
                    if(TextUtils.isEmpty(token)){
                        startActivity(LoginActivity.class);
                        finish();
                    }
                }
                return true;
            }
        });

    }

    @Override
    public void showMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void getCheckVersion(CheckVersionBean checkVersionBean) {
        if("1".equals(checkVersionBean.getBizData().getUpgrade())){
            if("1".equals(checkVersionBean.getBizData().getMust())){
                // 这里的提示框是我自定义的
                Intent intent=new Intent(MainActivity.this,UpdateVersionDialog.class);
                intent.putExtra("from","1");
                intent.putExtra("version",checkVersionBean.getBizData().getVersion());
                startActivity(intent);
            }else  if("0".equals(checkVersionBean.getBizData().getMust())){
                Intent intent=new Intent(MainActivity.this,UpdateVersionDialog.class);
                intent.putExtra("from","0");
                intent.putExtra("version",checkVersionBean.getBizData().getVersion());
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onDestroy() {
        if(checkVersionPresenter!=null){
            checkVersionPresenter.detachView();
        }
        super.onDestroy();
    }
}
