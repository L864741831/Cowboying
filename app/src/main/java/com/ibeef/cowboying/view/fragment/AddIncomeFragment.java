package com.ibeef.cowboying.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.AddMoneyAdapter;
import com.ibeef.cowboying.base.AddMoneyBase;
import com.ibeef.cowboying.bean.AddMoneyResultBean;
import com.ibeef.cowboying.bean.YesterdayIncomeResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.AddMoneyPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rxfamily.view.BaseFragment;

public class AddIncomeFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener , AddMoneyBase.IView {

    private String interestType,incomeType;
    RelativeLayout loadingLayout;
    RecyclerView ryId;
    private List<AddMoneyResultBean.BizDataBean> objectList;
    private AddMoneyAdapter addMoneyAdapter;
    private String token;
    private AddMoneyPresenter addMoneyPresenter;
    private TextView type_txt_id;

    private int currentPage=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    RelativeLayout rvOrder;
    RelativeLayout rvShowId;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        loadingLayout=view.findViewById(R.id.loading_layout);
        rvOrder=view.findViewById(R.id.rv_order);
        rvShowId=view.findViewById(R.id.rv_show_id);
        type_txt_id=view.findViewById(R.id.type_txt_id);
        ryId=view.findViewById(R.id.ry_id);
        ryId.setHasFixedSize(true);
        ryId.setNestedScrollingEnabled(false);

        objectList=new ArrayList<>();
        ryId.setLayoutManager(new LinearLayoutManager(getHoldingActivity()));
        addMoneyAdapter=new AddMoneyAdapter(objectList,getHoldingActivity(),R.layout.item_add_money);
        addMoneyAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(addMoneyAdapter);
        token= Hawk.get(HawkKey.TOKEN);

        addMoneyPresenter=new AddMoneyPresenter(this);

        //昨日收益
        if("1".equals(incomeType)){
            type_txt_id.setText("订单收益(元)");
        }else  if("2".equals(incomeType)){
            //累计收益
            type_txt_id.setText("当日收益(元)");
        }
        rvShowId.setVisibility(View.VISIBLE);
        ryId.setVisibility(View.VISIBLE);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        addMoneyPresenter.getAddMoney(reqData,currentPage,interestType,incomeType);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_income;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            interestType = args.getString("interestType");
            incomeType = args.getString("incomeType");
        }
    }
    public static AddIncomeFragment newInstance(String  interestType,String incomeType) {

        AddIncomeFragment newFragment = new AddIncomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("interestType", interestType);
        bundle.putString("incomeType", incomeType);
        newFragment.setArguments(bundle);
        return newFragment;
    }


    @Override
    public void onLoadMoreRequested() {
            isMoreLoad = true;
            currentPage += 1;
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization", token);
            reqData.put("version", getVersionCodes());
            addMoneyPresenter.getAddMoney(reqData,currentPage,interestType,incomeType);
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getAddMoney(AddMoneyResultBean accountRegisterResultBean) {
        if ("000000".equals(accountRegisterResultBean.getCode())) {
            if (SDCardUtil.isNullOrEmpty(accountRegisterResultBean.getBizData())) {
                if (isFirst) {
                    rvOrder.setVisibility(View.VISIBLE);
                    ryId.setVisibility(View.GONE);
                    rvShowId.setVisibility(View.GONE);
                } else {
                    rvOrder.setVisibility(View.GONE);
                    ryId.setVisibility(View.VISIBLE);
                    rvShowId.setVisibility(View.VISIBLE);
                }
                addMoneyAdapter.loadMoreEnd();
            } else {
                isFirst = false;
                objectList.addAll(accountRegisterResultBean.getBizData());
                addMoneyAdapter.setNewData(this.objectList);
                addMoneyAdapter.loadMoreComplete();
            }
        } else {
            showToast(accountRegisterResultBean.getMessage());
        }

    }

    @Override
    public void getYesterdayIncome(YesterdayIncomeResultBean yesterdayIncomeResultBean) {

    }

    @Override
    public void showLoading() {
        if (isMoreLoad) {
            loadingLayout.setVisibility(View.GONE);
            ryId.setVisibility(View.VISIBLE);
            rvShowId.setVisibility(View.VISIBLE);
            isMoreLoad = false;
        } else {
            loadingLayout.setVisibility(View.VISIBLE);
            ryId.setVisibility(View.GONE);
            rvShowId.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        ryId.setVisibility(View.VISIBLE);
        rvShowId.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(addMoneyPresenter!=null){
            addMoneyPresenter.detachView();
        }
    }
}
