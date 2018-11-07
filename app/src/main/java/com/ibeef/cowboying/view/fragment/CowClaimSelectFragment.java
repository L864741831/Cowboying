package com.ibeef.cowboying.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.CowClaimPastRecordAdapter;
import com.ibeef.cowboying.adapter.CowClaimSelectAdapter;
import com.ibeef.cowboying.view.activity.CowsDesInfoActivity;
import com.ibeef.cowboying.view.activity.JionPeopleNumActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rxfamily.view.BaseFragment;

public class CowClaimSelectFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener ,View.OnClickListener {

    private String des;
    RelativeLayout loadingLayout;
    RecyclerView ryId;
    EditText edtId;
    TextView sureId;
    private CowClaimSelectAdapter cowClaimSelectAdapter;
    private List<Object> objectList;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        loadingLayout=view.findViewById(R.id.loading_layout);
        ryId=view.findViewById(R.id.ry_id);
        edtId=view.findViewById(R.id.edt_id);
        sureId=view.findViewById(R.id.sure_id);
        sureId.setOnClickListener(this);
        
        ryId.setHasFixedSize(true);
        ryId.setNestedScrollingEnabled(false);
        
        objectList=new ArrayList<>();
        for (int i=0;i<15;i++){
            objectList.add(new Object());
        }
        ryId.setLayoutManager(new LinearLayoutManager(getHoldingActivity()));
        cowClaimSelectAdapter=new CowClaimSelectAdapter(objectList,getHoldingActivity(),R.layout.item_cows_cliam_select);
        cowClaimSelectAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(cowClaimSelectAdapter);
        cowClaimSelectAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId()==R.id.cows_identify_rv){
                    startActivity(CowsDesInfoActivity.class);
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cow_cliam_select;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            des = args.getString("des");
        }
    }
    public static CowClaimSelectFragment newInstance(String  des) {

        CowClaimSelectFragment newFragment = new CowClaimSelectFragment();
        Bundle bundle = new Bundle();
        bundle.putString("des", des);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sure_id:
                // TODO: 2018/11/7  网络请求 刷新牛只列表 
                break;
            default:
                break;
        }
    }
}
