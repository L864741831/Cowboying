package com.ibeef.cowboying.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.PayInitParamBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.utils.DateUtils;
import com.ibeef.cowboying.utils.VerificationCodeInput;
import com.ibeef.cowboying.view.customview.CountDownView;
import com.orhanobut.hawk.Hawk;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 商城支付方式
 */
public class StorePayTypeActivity extends BaseActivity {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.zfb_check)
    ImageView zfbCheck;
    @Bind(R.id.weixin_check)
    ImageView weixinCheck;
    @Bind(R.id.wallet_check)
    ImageView walletCheck;
    @Bind(R.id.time_show_id)
    CountDownView timeShowId;
    @Bind(R.id.cancle_id)
    TextView cancleId;
    @Bind(R.id.sure_pay_id)
    TextView surePayId;
    @Bind(R.id.account_pay_show_rv)
    RelativeLayout accountPayShowRv;
    @Bind(R.id.pay_back_id)
    ImageView payBackId;
    @Bind(R.id.verificationCodeInput_id)
    VerificationCodeInput verificationCodeInputId;
    @Bind(R.id.foret_pwd_id)
    TextView foretPwdId;
    private int type=1;
    private boolean isComplet=true;
    private String token, contents;
    private IWXAPI api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_pay_type);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("支付方式");
        long time= 1800000;
        //两时间差,精确到毫秒
        long hour = time  / 3600000;
        //以小时为单位取整
        long min = time  % 3600000 / 60000;
        //以分钟为单位取整
        long seconds = time  % 3600000 % 60000 / 1000;
        //以秒为单位取整
        Log.e(Constant.TAG,time+"????????????");
        timeShowId.initTime(hour,min,seconds);
        timeShowId.start();
        verificationCodeInputId.setOnCompleteListener(new VerificationCodeInput.Listener() {
            @Override
            public void onComplete(String content) {
                contents = content;
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                if(isComplet){
                    isComplet=false;
                    //网络请求

                }

            }
        });

        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID, false);
        token = Hawk.get(HawkKey.TOKEN);
    }

    @OnClick({R.id.cancle_id, R.id.sure_pay_id,R.id.back_id,R.id.zfb_check,R.id.weixin_check, R.id.foret_pwd_id, R.id.pay_back_id,R.id.wallet_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.zfb_check:
                type=1;
                weixinCheck.setImageResource(R.drawable.unhascheck);
                zfbCheck.setImageResource(R.drawable.hascheck);
                walletCheck.setImageResource(R.drawable.unhascheck);
                break;
            case R.id.weixin_check:
                type=2;
                weixinCheck.setImageResource(R.drawable.hascheck);
                zfbCheck.setImageResource(R.drawable.unhascheck);
                walletCheck.setImageResource(R.drawable.unhascheck);
                break;
            case R.id.wallet_check:
                type=3;
                weixinCheck.setImageResource(R.drawable.unhascheck);
                zfbCheck.setImageResource(R.drawable.unhascheck);
                walletCheck.setImageResource(R.drawable.hascheck);
                break;
            case R.id.foret_pwd_id:
                //设置支付密码
                startActivity(AddPayPwdActivity.class);
                break;
            case R.id.pay_back_id:
                accountPayShowRv.setVisibility(View.GONE);
                break;
            case R.id.cancle_id:
                break;
            case R.id.sure_pay_id:
                if (type==3){
                    accountPayShowRv.setVisibility(View.VISIBLE);
                    isComplet=true;
                }else {
                   //网络请求
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
