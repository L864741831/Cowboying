package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.SellCowsBase;
import com.ibeef.cowboying.bean.CreatSellCowsResultBean;
import com.ibeef.cowboying.bean.SellCowsResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.SellCowsPresenter;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

public class SellCowsFirstActivity extends BaseActivity implements SellCowsBase.IView {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.sell_cow_first_id)
    TextView sellCowFirstId;
    @Bind(R.id.get_sell_cows_money)
    TextView getSellCowsMoney;
    @Bind(R.id.return_monry_id)
    TextView returnMonryId;
    @Bind(R.id.other_money_id)
    TextView otherMoneyId;
    @Bind(R.id.succece_money_id)
    TextView succeceMoneyId;
    @Bind(R.id.next_step_btn)
    TextView nextStepBtn;
    @Bind(R.id.real_money_id)
    TextView realMoneyId;
    private SellCowsPresenter sellCowsPresenter;
    private String token,orderId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_cows_first);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        info.setText("我要卖牛");
        orderId=getIntent().getStringExtra("orderId");
        token = Hawk.get(HawkKey.TOKEN);
        sellCowsPresenter = new SellCowsPresenter(this);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization", token);
        reqData.put("version", getVersionCodes());
        sellCowsPresenter.getSellCows(reqData, orderId);
    }
    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getSellCows(SellCowsResultBean sellCowsResultBean) {
        if("000000".equals(sellCowsResultBean.getCode())){
            getSellCowsMoney.setText((sellCowsResultBean.getBizData().getPrice().floatValue()*sellCowsResultBean.getBizData().getPastureNum())+"");
            returnMonryId.setText(sellCowsResultBean.getBizData().getDebt()+"");
            otherMoneyId.setText(sellCowsResultBean.getBizData().getBrokerage()+"");
            sellCowFirstId.setText("您本次共选中订单1笔，合计卖牛"+sellCowsResultBean.getBizData().getPastureNum()+"只所选订单养殖回报率平均为"+sellCowsResultBean.getBizData().getRate()+"%每年");
            float realMoney=(sellCowsResultBean.getBizData().getPrice().floatValue()*sellCowsResultBean.getBizData().getPastureNum())-sellCowsResultBean.getBizData().getDebt().floatValue()-sellCowsResultBean.getBizData().getBrokerage().floatValue();
            realMoneyId.setText(realMoney+"");
            succeceMoneyId.setText("本次取回返利："+sellCowsResultBean.getBizData().getEarn()+"元");
        }else {
            showToast(sellCowsResultBean.getMessage());
        }

    }

    @Override
    public void getCreatSellCows(CreatSellCowsResultBean creatSellCowsResultBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @OnClick({R.id.back_id, R.id.next_step_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.next_step_btn:
                Intent intent=new Intent(SellCowsFirstActivity.this,SellCowsSecondActivity.class);
                intent.putExtra("orderId",orderId);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (sellCowsPresenter!=null){
            sellCowsPresenter.detachView();
        }
        super.onDestroy();
    }
}
