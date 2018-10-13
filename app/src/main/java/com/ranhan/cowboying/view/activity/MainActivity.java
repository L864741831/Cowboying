package com.ranhan.cowboying.view.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.ranhan.cowboying.R;
import com.ranhan.cowboying.adapter.MainFragmentAdapter;
import com.ranhan.cowboying.view.fragment.FourFragment;
import com.ranhan.cowboying.view.fragment.HomeFragment;
import com.ranhan.cowboying.view.fragment.SecondFragment;
import com.ranhan.cowboying.view.fragment.ThreeFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rxfamily.view.BaseActivity;
import rxfamily.view.BaseFragment;

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
        bnve.setTextSize(13);
        bnve.setIconSize(20,20);
        bnve.setIconsMarginTop(20);
        bnve.setItemIconTintList(null);
//        bnve.setTypeface(Typeface.MONOSPACE);
        fragments=new ArrayList<>();
        HomeFragment homeFragment=new HomeFragment();
        SecondFragment secondFragment=new SecondFragment();
        ThreeFragment threeFragment=new ThreeFragment();
        FourFragment fourFragment=new FourFragment();
        fragments.add(homeFragment);
        fragments.add(secondFragment);
        fragments.add(threeFragment);
        fragments.add(fourFragment);


        MainFragmentAdapter mAdpter = new MainFragmentAdapter(getSupportFragmentManager(),fragments);
        vp.setAdapter(mAdpter);
        bnve.setupWithViewPager(vp);
    }

    private void initEvent() {
        // set listener to do something then item selected
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
