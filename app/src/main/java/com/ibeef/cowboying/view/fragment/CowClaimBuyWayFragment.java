package com.ibeef.cowboying.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.ibeef.cowboying.R;

import rxfamily.view.BaseFragment;

public class CowClaimBuyWayFragment extends BaseFragment {

    private String des;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cow_cliam_bugway;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            des = args.getString("des");
        }
    }
    public static CowClaimBuyWayFragment newInstance(String  des) {

        CowClaimBuyWayFragment newFragment = new CowClaimBuyWayFragment();
        Bundle bundle = new Bundle();
        bundle.putString("des", des);
        newFragment.setArguments(bundle);
        return newFragment;
    }
}
