package com.ibeef.cowboying.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.ibeef.cowboying.R;

import jp.wasabeef.richeditor.RichEditor;
import rxfamily.view.BaseFragment;

public class CowClaimDesFragment extends BaseFragment {

    private String des;
    private RichEditor rich_edit_id;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        rich_edit_id=view.findViewById(R.id.rich_edit_id);
        rich_edit_id.setEditorFontSize(0);
        rich_edit_id.setEditorFontColor(Color.BLACK);
        rich_edit_id.setInputEnabled(false);
        rich_edit_id.setPadding(0, 0, 0, 0);
        rich_edit_id.loadCSS("file:///android_asset/img.css");
        rich_edit_id.setHtml(des);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cow_cliamdes;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            des = args.getString("des");
        }
    }
    public static CowClaimDesFragment newInstance(String  des) {

        CowClaimDesFragment newFragment = new CowClaimDesFragment();
        Bundle bundle = new Bundle();
        bundle.putString("des", des);
        newFragment.setArguments(bundle);
        return newFragment;
    }
}
