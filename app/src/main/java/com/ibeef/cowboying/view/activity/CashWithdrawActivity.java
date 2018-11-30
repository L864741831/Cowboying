package com.ibeef.cowboying.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.AccountSecurityBase;
import com.ibeef.cowboying.base.CashMoneyBase;
import com.ibeef.cowboying.base.InitThirdLoginBase;
import com.ibeef.cowboying.bean.AuthResult;
import com.ibeef.cowboying.bean.BindThirdCountParamBean;
import com.ibeef.cowboying.bean.BindThirdCountResultBean;
import com.ibeef.cowboying.bean.CashMoneyParamBean;
import com.ibeef.cowboying.bean.CashMoneyRecordResultBean;
import com.ibeef.cowboying.bean.CashMoneyResultBean;
import com.ibeef.cowboying.bean.CashMoneyUserInfoResultBean;
import com.ibeef.cowboying.bean.SafeInfoResultBean;
import com.ibeef.cowboying.bean.ThirdLoginResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.AccountSecurityPresenter;
import com.ibeef.cowboying.presenter.CashMoneyPresenter;
import com.ibeef.cowboying.presenter.InitThirdLoginPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.ibeef.cowboying.utils.VerificationCodeInput;
import com.orhanobut.hawk.Hawk;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 钱包提现
 */
public class CashWithdrawActivity extends BaseActivity implements CashMoneyBase.IView ,AccountSecurityBase.IView ,InitThirdLoginBase.IView{

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.money_txt_id)
    EditText moneyTxtId;
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
    @Bind(R.id.sure_close_btn)
    TextView sureCloseBtn;
    @Bind(R.id.account_pay_show_rv)
    RelativeLayout accountPayShowRv;
    @Bind(R.id.get_succese_dialog)
    RelativeLayout getSucceseDialog;
    @Bind(R.id.verificationCodeInput_id)
    VerificationCodeInput verificationCodeInputId;
    @Bind(R.id.foret_pwd_id)
    TextView foretPwdId;
    private String contents;
    private CashMoneyPresenter cashMoneyPresenter;
    private String token,withdrawmoney;
    private CashMoneyUserInfoResultBean cashMoneyUserInfoResultBean;
    private InitThirdLoginPresenter initThirdLoginPresenter;
    private IWXAPI api;
    private static final int SDK_AUTH_FLAG = 1000;
    private AccountSecurityPresenter accountSecurityPresenter;
    private boolean isGetMoney=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_withdraw);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        token= Hawk.get(HawkKey.TOKEN);
        withdrawmoney=getIntent().getStringExtra("withdrawmoney");
        withdrawMoneyId.setText(withdrawmoney);
        info.setText("钱包提现");
        verificationCodeInputId.setOnCompleteListener(new VerificationCodeInput.Listener() {
            @Override
            public void onComplete(String content) {
                contents = content;
                accountPayShowRv.setVisibility(View.GONE);
                sureOutMoneyBtn.setVisibility(View.VISIBLE);
                Log.e(Constant.TAG, "完成输入：" + content);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                if(isGetMoney){
                    Map<String, String> reqData = new HashMap<>();
                    reqData.put("Authorization",token);
                    reqData.put("version",getVersionCodes());
                    CashMoneyParamBean cashMoneyParamBean=new CashMoneyParamBean();
                    cashMoneyParamBean.setAmount(moneyTxtId.getText().toString().trim());
                    cashMoneyParamBean.setPassword(contents);
                    cashMoneyParamBean.setType("1");
                    cashMoneyPresenter.getCashMoney(reqData,cashMoneyParamBean);
                }

            }
        });
        cashMoneyPresenter=new CashMoneyPresenter(this);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        cashMoneyPresenter.getCashMoneyUserInfo(reqData);

        api = WXAPIFactory.createWXAPI(this,  Constant.WeixinAppId,true);
        api.registerApp(Constant.WeixinAppId);
        initThirdLoginPresenter=new InitThirdLoginPresenter(this);
        accountSecurityPresenter=new AccountSecurityPresenter(this);
    }


    @OnClick({R.id.back_id, R.id.add_accountzfb_rv, R.id.sure_out_money_btn,R.id.foret_pwd_id,R.id.pay_back_id,R.id.sure_close_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.sure_close_btn:
                finish();
                break;
            case R.id.add_accountzfb_rv:
//                startActivity(AddAccountZfbActivity.class);
                //绑定支付宝
                initThirdLoginPresenter.getInitThirdLogin(getVersionCodes(),"4");
                break;
            case R.id.sure_out_money_btn:
                if(!SDCardUtil.isNullOrEmpty(cashMoneyUserInfoResultBean)){
                    if("0".equals(cashMoneyUserInfoResultBean.getBizData().getBindAlipay())){
                        showToast("请先绑定您的提现支付宝账号！");
                        return;
                    }
                }

                if(TextUtils.isEmpty(moneyTxtId.getText().toString().trim())){
                    showToast("请输入提现金额");
                    return;
                }
                if(moneyTxtId.getText().toString().trim().startsWith("0")){
                    showToast("输入金额不正确，请重新输入！");
                    return;
                }

                if(("0").equals(moneyTxtId.getText().toString().trim())){
                    showToast("输入金额不正确，请重新输入！");
                    return;
                }
                if(Float.parseFloat(moneyTxtId.getText().toString().trim())<10){
                    showToast("10元起可以提现哟~");
                    return;
                }

                if(chooseAccoutCk.isChecked()){
                    accountPayShowRv.setVisibility(View.VISIBLE);
                    sureOutMoneyBtn.setVisibility(View.GONE);

                }else {
                    showToast("请选择提现支付宝");
                }
                break;
            case R.id.pay_back_id:
                accountPayShowRv.setVisibility(View.GONE);
                sureOutMoneyBtn.setVisibility(View.VISIBLE);
                break;
            case R.id.foret_pwd_id:
                //设置支付密码
                startActivity(AddPayPwdActivity.class);
                break;
            default:
                break;
        }
    }


    private void clearData(){
        //遍历子类
        for (int i=0;i<verificationCodeInputId.getChildCount();i++){
            //设置可点击
            verificationCodeInputId.getChildAt(i).setEnabled(true);
            EditText childAt = (EditText) verificationCodeInputId.getChildAt(i);
            //清空内容
            childAt.setText("");
            if(i==0){
                //第一个获取焦点
                verificationCodeInputId.getChildAt(i).requestFocus();
                verificationCodeInputId.getChildAt(i).setFocusable(true);
                verificationCodeInputId.getChildAt(i).setFocusableInTouchMode(true);
            }
        }
    }
    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getSafeInfo(SafeInfoResultBean safeInfoResultBean) {

    }

    @Override
    public void getBindThidCount(BindThirdCountResultBean bindThirdCountResultBean) {
        if ("000000".equals(bindThirdCountResultBean.getCode())) {
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization",token);
            reqData.put("version",getVersionCodes());
            cashMoneyPresenter.getCashMoneyUserInfo(reqData);
            showToast("绑定支付宝账号成功~");
        } else {
            showToast(bindThirdCountResultBean.getMessage());
        }
    }

    @Override
    public void getUnBindThidCount(BindThirdCountResultBean bindThirdCountResultBean) {

    }

    @Override
    public void getInitThirdLogin(ThirdLoginResultBean thirdLoginResultBean) {
        if ("000000".equals(thirdLoginResultBean.getCode())) {
            aplilyLogin(thirdLoginResultBean.getBizData());
        } else {
            showToast(thirdLoginResultBean.getMessage());
        }
    }
    /**
     * 支付宝授权登录
     */
    private void aplilyLogin(final String authInfo) {
        //异步处理
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {

                AuthTask authTask = new AuthTask(CashWithdrawActivity.this);
                // 调用授权接口，获取授权结果ta
                Map<String, String> result = authTask.authV2(authInfo, true);
                Message msg = new Message();
                msg.what = SDK_AUTH_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SDK_AUTH_FLAG:
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        // auth_code传入后台，后台获取 auth_token,其他信息 授权成功绑定手机号
                        Map<String, String> reqData = new HashMap<>();
                        reqData.put("version", getVersionCodes());
                        reqData.put("Authorization", token);
                        BindThirdCountParamBean bindThirdCountParamBean = new BindThirdCountParamBean();
                        bindThirdCountParamBean.setAuthCode(authResult.getAuthCode());
                        bindThirdCountParamBean.setOpenId(authResult.getUserId());
                        bindThirdCountParamBean.setType("4");
                        accountSecurityPresenter.getBindThidCount(reqData, bindThirdCountParamBean);

                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(CashWithdrawActivity.this,
                                "授权失败" , Toast.LENGTH_SHORT).show();

                    }
                    Log.e(Constant.TAG, "支付宝结果" + authResult + "????????" + resultStatus);
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    public void getCashMoney(CashMoneyResultBean cashMoneyResultBean) {

        if("000000".equals(cashMoneyResultBean.getCode())){
            //取现成功
            getSucceseDialog.setVisibility(View.VISIBLE);
            isGetMoney=false;
        }else {
            clearData();
            showToast(cashMoneyResultBean.getMessage());
        }
    }

    @Override
    public void getCashMoneyRecord(CashMoneyRecordResultBean cashMoneyRecordResultBean) {

    }

    @Override
    public void getCashMoneyUserInfo(CashMoneyUserInfoResultBean cashMoneyUserInfoResultBean) {
        if("000000".equals(cashMoneyUserInfoResultBean.getCode())){
            this.cashMoneyUserInfoResultBean=cashMoneyUserInfoResultBean;
            if("0".equals(cashMoneyUserInfoResultBean.getBizData().getBindAlipay())){
                hasBindzfbRv.setVisibility(View.GONE);
                addAccountzfbRv.setVisibility(View.VISIBLE);
            }else  if("1".equals(cashMoneyUserInfoResultBean.getBizData().getBindAlipay())){
                hasBindzfbRv.setVisibility(View.VISIBLE);
                addAccountzfbRv.setVisibility(View.GONE);
                if(SDCardUtil.isNullOrEmpty(cashMoneyUserInfoResultBean.getBizData().getNickName())){
                    mobileTxtId.setText("暂无支付宝昵称");
                }else {
                    mobileTxtId.setText(cashMoneyUserInfoResultBean.getBizData().getNickName());
                }
            }
        }else {
            showToast(cashMoneyUserInfoResultBean.getMessage());
        }

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        if(cashMoneyPresenter!=null){
            cashMoneyPresenter.detachView();
        }
        super.onDestroy();
    }
}
