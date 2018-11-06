package com.ibeef.cowboying.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.ibeef.cowboying.R;

import rxfamily.view.BaseFragment;

public class CowClaimPastRecordFragment extends BaseFragment {

    private String des;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_second;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            des = args.getString("des");
        }
    }
    public static CowClaimPastRecordFragment newInstance(String  des) {

        CowClaimPastRecordFragment newFragment = new CowClaimPastRecordFragment();
        Bundle bundle = new Bundle();
        bundle.putString("des", des);
        newFragment.setArguments(bundle);
        return newFragment;
    }
}
