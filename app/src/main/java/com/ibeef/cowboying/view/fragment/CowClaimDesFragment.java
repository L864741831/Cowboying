package com.ibeef.cowboying.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.config.HawkKey;
import com.orhanobut.hawk.Hawk;

import jp.wasabeef.richeditor.RichEditor;
import rxfamily.view.BaseFragment;

public class CowClaimDesFragment extends BaseFragment {

    private static String  des;
    private RichEditor rich_edit_id;
    private String token;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        token= Hawk.get(HawkKey.TOKEN);
        rich_edit_id=view.findViewById(R.id.rich_edit_id);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cow_cliamdes;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bundle args = getArguments();
//        if (args != null) {
//            des = args.getString("des");
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        rich_edit_id.setEditorFontSize(16);
        rich_edit_id.setEditorFontColor(Color.BLACK);
        rich_edit_id.setInputEnabled(false);
        rich_edit_id.setPadding(3, 5, 5, 5);
        rich_edit_id.loadCSS("file:///android_asset/img.css");
        rich_edit_id.setHtml(des);
    }

    public static CowClaimDesFragment newInstance(String  info) {

        CowClaimDesFragment newFragment = new CowClaimDesFragment();
        Bundle bundle = new Bundle();
//        bundle.putString("des", des);
        des=info;
        newFragment.setArguments(bundle);
        return newFragment;
    }
}
