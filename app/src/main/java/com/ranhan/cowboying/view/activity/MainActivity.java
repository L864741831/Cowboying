package com.ranhan.cowboying.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;


import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.ranhan.cowboying.R;
import com.ranhan.cowboying.adapter.MainFragmentAdapter;
import com.ranhan.cowboying.view.fragment.HomeFragment;
import com.ranhan.cowboying.view.fragment.SecondFragment;
import com.ranhan.cowboying.view.fragment.ThreeFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rxfamily.view.BaseActivity;
import rxfamily.view.BaseFragment;

/**
 * 主页面
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.bnve)
    BottomNavigationViewEx bnve;
    @Bind(R.id.vp)
    ViewPager vp;
    private ArrayList<BaseFragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        initEvent();

    }

    private void init(){
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

        // TODO: 2018/10/15 网络请求 版本升级
//        startActivity(UpdateVersionDialog.class);
    }

    private void initEvent() {
        bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                if (item.getItemId() == R.id.i_second) {
//                    Toast.makeText(MainActivity.this, "add", Toast.LENGTH_SHORT).show();
//                    return false;
//                }
                return true;
            }
        });

    }
}
