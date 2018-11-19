package com.ibeef.cowboying.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.CowClaimSelectAdapter;
import com.ibeef.cowboying.base.CattleDetailBase;
import com.ibeef.cowboying.bean.AdoptInfosResultBean;
import com.ibeef.cowboying.bean.CattleDetailResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.CattleDetailPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.ibeef.cowboying.view.activity.CowsDesInfoActivity;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rxfamily.view.BaseFragment;

public class CowClaimSelectFragment extends BaseFragment implements View.OnClickListener,CattleDetailBase.IView {

    private Integer schemeId;
    private String code;
    RelativeLayout loadingLayout;
    RecyclerView ryId;
    EditText edtId;
    TextView sureId;
    private CowClaimSelectAdapter cowClaimSelectAdapter;
    private List<AdoptInfosResultBean.BizDataBean> objectList;
    private CattleDetailPresenter cattleDetailPresenter;
    private String token;
    private Integer currentPage;
    RelativeLayout rvOrder;
    private boolean isFirst=true;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        token= Hawk.get(HawkKey.TOKEN);
        loadingLayout=view.findViewById(R.id.loading_layout);
        ryId=view.findViewById(R.id.ry_id);
        edtId=view.findViewById(R.id.edt_id);
        sureId=view.findViewById(R.id.sure_id);
        rvOrder=view.findViewById(R.id.rv_order);
        sureId.setOnClickListener(this);
        
        ryId.setHasFixedSize(true);
        ryId.setNestedScrollingEnabled(false);
        
        objectList=new ArrayList<>();
        ryId.setLayoutManager(new LinearLayoutManager(getHoldingActivity()));
        cowClaimSelectAdapter=new CowClaimSelectAdapter(objectList,getHoldingActivity(),R.layout.item_cows_cliam_select);
        ryId.setAdapter(cowClaimSelectAdapter);
        cowClaimSelectAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId()==R.id.cows_identify_rv){
                    AdoptInfosResultBean.BizDataBean item=cowClaimSelectAdapter.getItem(position);
                    Intent intent=new Intent(getHoldingActivity(),CowsDesInfoActivity.class);
                    intent.putExtra("cattleId",item.getCattleId());
                    startActivity(intent);
                }
            }
        });

        cattleDetailPresenter=new CattleDetailPresenter(this);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        cattleDetailPresenter.getAdoptInfos(reqData,null,schemeId,null);
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
            code = args.getString("code");
            schemeId = args.getInt("schemeId");
        }
    }
    public static CowClaimSelectFragment newInstance(String  code,Integer schemeId) {

        CowClaimSelectFragment newFragment = new CowClaimSelectFragment();
        Bundle bundle = new Bundle();
        bundle.putString("code", code);
        bundle.putInt("schemeId", schemeId);
        newFragment.setArguments(bundle);
        return newFragment;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sure_id:
                isFirst=true;
                objectList.clear();
                Map<String, String> reqData = new HashMap<>();
                reqData.put("Authorization",token);
                reqData.put("version",getVersionCodes());
                cattleDetailPresenter.getAdoptInfos(reqData,edtId.getText().toString().trim(),schemeId,null);
                break;
            default:
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getCattleDetail(CattleDetailResultBean cattleDetailResultBean) {

    }

    @Override
    public void getAdoptInfos(AdoptInfosResultBean adoptInfosResultBean) {
        if ("000000".equals(adoptInfosResultBean.getCode())) {
            if (SDCardUtil.isNullOrEmpty(adoptInfosResultBean.getBizData())) {
                if (isFirst) {
                    rvOrder.setVisibility(View.VISIBLE);
                    ryId.setVisibility(View.GONE);
                } else {
                    rvOrder.setVisibility(View.GONE);
                    ryId.setVisibility(View.VISIBLE);
                }
                cowClaimSelectAdapter.loadMoreEnd();
            } else {
                isFirst = false;
                objectList.addAll(adoptInfosResultBean.getBizData());
                cowClaimSelectAdapter.setNewData(this.objectList);
                cowClaimSelectAdapter.loadMoreComplete();
            }
        } else {
            showToast(adoptInfosResultBean.getMessage());
        }

    }

    @Override
    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
        ryId.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        ryId.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        if(cattleDetailPresenter!=null){
            cattleDetailPresenter.detachView();
        }
        super.onDestroy();
    }
}
