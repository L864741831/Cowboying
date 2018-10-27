package com.ibeef.cowboying.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import rxfamily.view.BaseFragment;


public class MainFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;

    public MainFragmentAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public BaseFragment getItem(int fragment) {
        return fragments.get(fragment);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    private View mCurrentView;

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (object instanceof View) {
            mCurrentView = (View) object;
        } else if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            mCurrentView = fragment.getView();
        }
    }

    public View getPrimaryItem() {
        return mCurrentView;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Tab"+position;
    }
}
