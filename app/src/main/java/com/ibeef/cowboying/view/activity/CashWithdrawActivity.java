package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.CashMoneyBase;
import com.ibeef.cowboying.bean.CashMoneyParamBean;
import com.ibeef.cowboying.bean.CashMoneyRecordResultBean;
import com.ibeef.cowboying.bean.CashMoneyResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.CashMoneyPresenter;
import com.ibeef.cowboying.utils.VerificationCodeInput;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 钱包提现
 */
public class CashWithdrawActivity extends BaseActivity implements CashMoneyBase.IView {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.money_txt_id)
    TextView moneyTxtId;
    @Bind(R.id.withdraw_money_id)
    TextView withdrawMoneyId;
    @Bind(R.id.mobile_txt_id)
    TextView mobileTxtId;
    @Bind(R.id.choose_accout_ck)
    CheckBox chooseAccoutCk;
    @Bind(R.id.has_bindzfb_rv)
    RelativeLayout hasBindzfbRv;
    @Bind(R.id.add_accountzfb_rv)
    RelativeLayout addAccountzfbRv;
    @Bind(R.id.sure_out_money_btn)
    TextView sureOutMoneyBtn;
    @Bind(R.id.account_pay_show_rv)
    RelativeLayout accountPayShowRv;
    @Bind(R.id.verificationCodeInput_id)
    VerificationCodeInput verificationCodeInputId;
    @Bind(R.id.foret_pwd_id)
    TextView foretPwdId;
    private String contents;
    private CashMoneyPresenter cashMoneyPresenter;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_withdraw);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        token= Hawk.get(HawkKey.TOKEN);
        info.setText("钱包提现");
        verificationCodeInputId.setOnCompleteListener(new VerificationCodeInput.Listener() {
            @Override
            public void onComplete(String content) {
                contents = content;
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                Log.e(Constant.TAG, "完成输入：" + content);
            }
        });
        cashMoneyPresenter=new CashMoneyPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @OnClick({R.id.back_id, R.id.add_accountzfb_rv, R.id.sure_out_money_btn,R.id.foret_pwd_id,R.id.pay_back_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.add_accountzfb_rv:
                startActivity(AddAccountZfbActivity.class);
                break;
            case R.id.sure_out_money_btn:
                if(chooseAccoutCk.isChecked()){
                    accountPayShowRv.setVisibility(View.VISIBLE);
                }else {
                    showToast("请选择提现支付宝");
                }
                Map<String, String> reqData = new HashMap<>();
                reqData.put("Authorization",token);
                reqData.put("version",getVersionCodes());
                CashMoneyParamBean cashMoneyParamBean=new CashMoneyParamBean();
//                cashMoneyParamBean.setAccount();
                cashMoneyPresenter.getCashMoney(reqData,cashMoneyParamBean);
                break;
            case R.id.pay_back_id:
                accountPayShowRv.setVisibility(View.GONE);
                break;
            case R.id.foret_pwd_id:
                //设置支付密码
                startActivity(AddPayPwdActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getCashMoney(CashMoneyResultBean cashMoneyResultBean) {

    }

    @Override
    public void getCashMoneyRecord(CashMoneyRecordResultBean cashMoneyRecordResultBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
