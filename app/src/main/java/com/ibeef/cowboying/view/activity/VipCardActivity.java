package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.MyContractBase;
import com.ibeef.cowboying.bean.MyContractListBean;
import com.ibeef.cowboying.bean.MyContractURLBean;
import com.ibeef.cowboying.bean.MyDiscountCouponListBean;
import com.ibeef.cowboying.bean.PayCodeBean;
import com.ibeef.cowboying.bean.VipCardBean;
import com.ibeef.cowboying.bean.VipCardListBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.MyContractPresenter;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * @author Administrator
 * 会员卡界面
 */
public class VipCardActivity extends BaseActivity implements MyContractBase.IView {


    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.iv_xinpian)
    ImageView ivXinpian;
    @Bind(R.id.tv_weizhi)
    TextView tvWeizhi;
    @Bind(R.id.tv_money)
    TextView tvMoney;
    @Bind(R.id.tv_number)
    TextView tvNumber;
    @Bind(R.id.tv_address_info)
    TextView tvAddressInfo;
    @Bind(R.id.iv_vip_card_bg_null)
    ImageView ivVipCardBgNull;
    @Bind(R.id.iv_vip_card_bg)
    ImageView ivVipCardBg;
    private String token;
    private MyContractPresenter myContractPresenter;
    private VipCardBean vipCardBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_card);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        token = Hawk.get(HawkKey.TOKEN);
        info.setText("会员卡");
        actionNewQuestionTv.setText("消费记录");
        actionNewQuestionTv.setVisibility(View.VISIBLE);
        myContractPresenter = new MyContractPresenter(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        token = Hawk.get(HawkKey.TOKEN);
        if (!TextUtils.isEmpty(token)) {
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization", token);
            reqData.put("version", getVersionCodes());
            myContractPresenter.showVipCard(reqData);
        }
    }


    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void getMyContrantList(MyContractListBean myContractListBean) {

    }

    @Override
    public void getMyContrantURL(MyContractURLBean myContractURLBean) {

    }

    @Override
    public void getMyDiscountCouponList(MyDiscountCouponListBean myDiscountCouponListBean) {

    }

    @Override
    public void showPayCode(PayCodeBean payCodeBean) {


    }

    @Override
    public void showVipCard(VipCardBean vipCardBean) {
        if ("000000".equals(vipCardBean.getCode())) {
            this.vipCardBean = vipCardBean;
            if (vipCardBean.getBizData().getId() != null||"".equals(vipCardBean.getBizData().getId())) {
                //有会员卡
                ivVipCardBg.setVisibility(View.VISIBLE);
                ivXinpian.setVisibility(View.VISIBLE);
                tvWeizhi.setVisibility(View.VISIBLE);
                tvMoney.setVisibility(View.VISIBLE);
                tvNumber.setVisibility(View.VISIBLE);
                tvMoney.setText("余额："+vipCardBean.getBizData().getAmount()+"元");
                tvNumber.setText("NO"+vipCardBean.getBizData().getCardNo());
            } else {
                //没有会员卡
                ivVipCardBgNull.setVisibility(View.VISIBLE);
                tvAddressInfo.setVisibility(View.VISIBLE);
                tvAddressInfo.setText("线下门店地址："+vipCardBean.getBizData().getStoreAddress());
                actionNewQuestionTv.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void showVipCardHistory(VipCardListBean vipCardListBean) {

    }

    @OnClick({R.id.back_id, R.id.action_new_question_tv, R.id.iv_xinpian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.action_new_question_tv:
                startActivity(ConsumptionHistoryListActivity.class);
                break;
            case R.id.iv_xinpian:
                startActivity(PayActivity.class);
                break;
        }
    }
}
