package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.SellCowsBase;
import com.ibeef.cowboying.bean.CreatSellCowsParamBean;
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

public class SellCowsSecondActivity extends BaseActivity implements SellCowsBase.IView {
    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.question_show_id)
    TextView questionShowId;
    @Bind(R.id.wallet_ck_id)
    CheckBox walletCkId;
    @Bind(R.id.othemoney_ck_id)
    CheckBox othemoneyCkId;
    @Bind(R.id.beefhourse_ck_id)
    CheckBox beefhourseCkId;
    @Bind(R.id.sure_btn)
    TextView sureBtn;
    @Bind(R.id.fm_show_id)
    FrameLayout fmShowId;
    @Bind(R.id.sure_dialog_show)
    FrameLayout sureDialogShow;
    @Bind(R.id.img_close_id)
    ImageView imgCloseId;
    @Bind(R.id.get_sell_cows_money)
    TextView getSellCowsMoney;
    @Bind(R.id.other_money_id)
    TextView otheMoneyId;
    @Bind(R.id.real_money_id)
    TextView realMoneyId;
    @Bind(R.id.return_monry_id)
    TextView returnMonryId;
    @Bind(R.id.give_money_id)
    TextView giveMoneyId;
    @Bind(R.id.show_money_id)
    TextView showMoneyId;
    @Bind(R.id.sure_id)
    TextView sureId;
    private SellCowsPresenter sellCowsPresenter;
    private String token, orderId;
    private int chooseType=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_cows_second);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("我要卖牛");
        orderId = getIntent().getStringExtra("orderId");
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
            otheMoneyId.setText(sellCowsResultBean.getBizData().getBrokerage()+"");
            float realMoney=(sellCowsResultBean.getBizData().getPrice().floatValue()*sellCowsResultBean.getBizData().getPastureNum())+sellCowsResultBean.getBizData().getEarn().floatValue()-sellCowsResultBean.getBizData().getDebt().floatValue()-sellCowsResultBean.getBizData().getBrokerage().floatValue();
            realMoneyId.setText(realMoney+"");
            showMoneyId.setText(realMoney+"");
            giveMoneyId.setText(sellCowsResultBean.getBizData().getEarn()+"");
        }else {
            showToast(sellCowsResultBean.getMessage());
        }
    }

    @Override
    public void getCreatSellCows(CreatSellCowsResultBean creatSellCowsResultBean) {
        if("000000".equals(creatSellCowsResultBean.getCode())){
            sureDialogShow.setVisibility(View.VISIBLE);
        }else {
            showToast(creatSellCowsResultBean.getMessage());
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @OnClick({R.id.back_id, R.id.sure_btn,R.id.wallet_ck_id,R.id.othemoney_ck_id,R.id.beefhourse_ck_id,R.id.question_show_id,R.id.img_close_id,R.id.fm_show_id,R.id.sure_id,R.id.sure_dialog_show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.sure_id:
                finish();
                break;
            case R.id.sure_dialog_show:
               //抵消 事件分发机制
                break;
            case R.id.question_show_id:
                fmShowId.setVisibility(View.VISIBLE);
                break;
            case R.id.img_close_id:
                fmShowId.setVisibility(View.GONE);
                break;
            case R.id.fm_show_id:
                fmShowId.setVisibility(View.GONE);
                break;
            case R.id.sure_btn:
                if(chooseType==3){
                    showToast("暂不支持复购加息1%");
                    return;
                }
                Map<String, String> reqData = new HashMap<>();
                reqData.put("Authorization", token);
                reqData.put("version", getVersionCodes());
                CreatSellCowsParamBean creatSellCowsParamBean=new CreatSellCowsParamBean();
                creatSellCowsParamBean.setOrderId(orderId);
                creatSellCowsParamBean.setSellType(chooseType+"");
                sellCowsPresenter.getCreatSellCows(reqData,creatSellCowsParamBean);
                break;
            case R.id.wallet_ck_id:
                chooseType = 1;
                beefhourseCkId.setBackground(ContextCompat.getDrawable(SellCowsSecondActivity.this, R.drawable.unhascheck));
                othemoneyCkId.setBackground(ContextCompat.getDrawable(SellCowsSecondActivity.this, R.drawable.unhascheck));
                walletCkId.setBackground(ContextCompat.getDrawable(SellCowsSecondActivity.this, R.drawable.hascheck));
                break;
            case R.id.othemoney_ck_id:
                chooseType = 3;
                othemoneyCkId.setBackground(ContextCompat.getDrawable(SellCowsSecondActivity.this, R.drawable.hascheck));
                walletCkId.setBackground(ContextCompat.getDrawable(SellCowsSecondActivity.this, R.drawable.unhascheck));
                beefhourseCkId.setBackground(ContextCompat.getDrawable(SellCowsSecondActivity.this, R.drawable.unhascheck));
                break;
            case R.id.beefhourse_ck_id:
                chooseType = 2;
                othemoneyCkId.setBackground(ContextCompat.getDrawable(SellCowsSecondActivity.this, R.drawable.unhascheck));
                beefhourseCkId.setBackground(ContextCompat.getDrawable(SellCowsSecondActivity.this, R.drawable.hascheck));
                walletCkId.setBackground(ContextCompat.getDrawable(SellCowsSecondActivity.this, R.drawable.unhascheck));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if(sellCowsPresenter!=null){
            sellCowsPresenter.detachView();
        }
        super.onDestroy();
    }
}
