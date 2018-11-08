package com.ibeef.cowboying.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.PayResult;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.utils.VerificationCodeInput;
import com.orhanobut.hawk.Hawk;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import rxfamily.view.BaseActivity;

/**
 * 买牛确认订单界面
 */
public class SureOderActivity extends BaseActivity {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.pay_back_id)
    ImageView payBackId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.head_img)
    CircleImageView headImg;
    @Bind(R.id.nick_name_txt)
    TextView nickNameTxt;
    @Bind(R.id.order_code_txt)
    TextView orderCodeTxt;
    @Bind(R.id.pay_money_id)
    TextView payMoneyId;
    @Bind(R.id.coupon_money_id)
    TextView couponMoneyId;
    @Bind(R.id.know_name_id)
    TextView knowNameId;
    @Bind(R.id.know_mobile_id)
    TextView knowMobileId;
    @Bind(R.id.know_identifycode_id)
    TextView knowIdentifycodeId;
    @Bind(R.id.know_code_id)
    TextView knowCodeId;
    @Bind(R.id.know_pasture_id)
    TextView knowPastureId;
    @Bind(R.id.know_number_id)
    TextView knowNumberId;
    @Bind(R.id.know_one_price_id)
    TextView knowOnePriceId;
    @Bind(R.id.custom_txt_id)
    TextView customTxtId;
    @Bind(R.id.sure_pay_btn)
    TextView surePayBtn;
    @Bind(R.id.account_pay_show_rv)
    RelativeLayout accountPayShowRv;
    @Bind(R.id.weixin_check_rv)
    RelativeLayout weixinCheckRv;
    @Bind(R.id.zfb_check_rv)
    RelativeLayout zfbCheckRv;
    @Bind(R.id.verificationCodeInput_id)
    VerificationCodeInput verificationCodeInputId;
    @Bind(R.id.foret_pwd_id)
    TextView foretPwdId;
    @Bind(R.id.zfb_check)
    CheckBox zfbCheck;
    @Bind(R.id.weixin_check)
    CheckBox weixinCheck;
    @Bind(R.id.account_balance_ck)
    CheckBox accountBalanceCk;
    private int chooseType = 1;

    private static final int SDK_PAY_FLAG = 1;
    private IWXAPI api;
    private String token, contents;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    // 同步返回需要验证的信息
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(SureOderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(SureOderActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sure_oder);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("确认订单");
        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID, false);
        token = Hawk.get(HawkKey.TOKEN);

        verificationCodeInputId.setOnCompleteListener(new VerificationCodeInput.Listener() {
            @Override
            public void onComplete(String content) {
                contents = content;
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                Log.e(Constant.TAG, "完成输入：" + content);
            }
        });
    }

    @OnClick({R.id.back_id, R.id.custom_txt_id, R.id.sure_pay_btn, R.id.pay_back_id, R.id.foret_pwd_id, R.id.account_pay_show_rv, R.id.weixin_check_rv, R.id.zfb_check_rv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.account_pay_show_rv:
                chooseType = 3;
                weixinCheck.setBackground(ContextCompat.getDrawable(SureOderActivity.this, R.drawable.unhascheck));
                zfbCheck.setBackground(ContextCompat.getDrawable(SureOderActivity.this, R.drawable.unhascheck));
                accountBalanceCk.setBackground(ContextCompat.getDrawable(SureOderActivity.this, R.drawable.hascheck));
                break;
            case R.id.weixin_check_rv:
                chooseType = 2;
                weixinCheck.setBackground(ContextCompat.getDrawable(SureOderActivity.this, R.drawable.hascheck));
                zfbCheck.setBackground(ContextCompat.getDrawable(SureOderActivity.this, R.drawable.unhascheck));
                accountBalanceCk.setBackground(ContextCompat.getDrawable(SureOderActivity.this, R.drawable.unhascheck));
                break;
            case R.id.zfb_check_rv:
                chooseType = 1;
                weixinCheck.setBackground(ContextCompat.getDrawable(SureOderActivity.this, R.drawable.unhascheck));
                zfbCheck.setBackground(ContextCompat.getDrawable(SureOderActivity.this, R.drawable.hascheck));
                accountBalanceCk.setBackground(ContextCompat.getDrawable(SureOderActivity.this, R.drawable.unhascheck));
                break;
            case R.id.custom_txt_id:
                showToast("客服");
                break;
            case R.id.pay_back_id:
                accountPayShowRv.setVisibility(View.GONE);
                break;
            case R.id.foret_pwd_id:
                //设置支付密码

                break;
            case R.id.sure_pay_btn:
                if (chooseType == 1) {
                    aplilyPayResult();
                } else if (chooseType == 2) {
                    weixinPay();
                } else if (chooseType == 3) {
                    accountPayShowRv.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
    }


    /**
     * 支付宝支付
     */
    private void aplilyPayResult() {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(SureOderActivity.this);
                Map<String, String> result = alipay.payV2(Constant.ALIPAYINFO, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 微信支付
     */
    private void weixinPay() {
        PayReq request = new PayReq();
        request.appId = "wxd930ea5d5a258f4f";
        request.partnerId = "1900000109";
        request.prepayId = "1101000000140415649af9fc314aa427";
        request.packageValue = "Sign=WXPay";
        request.nonceStr = "1101000000140429eb40476f8896f4c9";
        request.timeStamp = "1398746574";
        request.sign = "7FFECB600D7157C5AA49810D2D8F28BC2811827B";
        api.sendReq(request);
    }


}
