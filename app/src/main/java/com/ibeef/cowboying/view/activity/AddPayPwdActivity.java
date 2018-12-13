package com.ibeef.cowboying.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.SetPayPwdBase;
import com.ibeef.cowboying.base.SmscodeBase;
import com.ibeef.cowboying.bean.ResetPayPwdResultBean;
import com.ibeef.cowboying.bean.SetPayPwdParamBean;
import com.ibeef.cowboying.bean.SetPayPwdResultBean;
import com.ibeef.cowboying.bean.SmsCodeParamBean;
import com.ibeef.cowboying.bean.SmsCodeResultBean;
import com.ibeef.cowboying.bean.ValidateSmsCodeParamBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.SetPayPwdPresenter;
import com.ibeef.cowboying.presenter.SmsCodePresenter;
import com.ibeef.cowboying.utils.Md5Util;
import com.ibeef.cowboying.utils.TimeUtils;
import com.orhanobut.hawk.Hawk;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;
import rxfamily.utils.PermissionsUtils;
import rxfamily.view.BaseActivity;

/**
 * 设置支付密码
 */
public class AddPayPwdActivity extends BaseActivity implements SmscodeBase.IView ,SetPayPwdBase.IView {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.et_mobile)
    EditText etMobile;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.btn_code)
    CheckBox btnCode;
    @Bind(R.id.add_pay_pwd)
    EditText addPayPwd;
    @Bind(R.id.sure_pay_pwd)
    EditText surePayPwd;
    @Bind(R.id.btn_sure)
    TextView btnSure;

    private SmsCodePresenter smsCodePresenter;
    private SetPayPwdPresenter setPayPwdPresenter;
    private String token;
    private String tell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pay_pwd);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("设置支付密码");
        token= Hawk.get(HawkKey.TOKEN);
        tell=getIntent().getStringExtra("tell");
        String str="";
        if(TextUtils.isEmpty(tell)){
            etMobile.setFocusable(true);
            etMobile.setEnabled(true);
        }else {
            etMobile.setFocusable(false);
            etMobile.setEnabled(false);
            str = tell.substring(0, tell.length() - (tell.substring(3)).length()) + "****" + tell.substring(7);
        }

        etMobile.setText(str);
        addPayPwd.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_CLASS_NUMBER);
        surePayPwd.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_CLASS_NUMBER);
        smsCodePresenter=new SmsCodePresenter(this);
        setPayPwdPresenter=new SetPayPwdPresenter(this);
    }

    @OnClick({R.id.back_id, R.id.btn_code, R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.btn_code:
                if(TextUtils.isEmpty(tell)){
                    tell=etMobile.getText().toString();
                }
                if (TextUtils.isEmpty(tell)) {
                    showToast("手机号码不能为空！");
                    return;
                }
                if (!TimeUtils.isMatchered(TimeUtils.PHONE_PATTERN, tell)) {
                    showToast("请输入正确的手机号码！");
                    return;
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    rx.Observable<Boolean> grantObservable = PermissionsUtils.getPhoneCode(AddPayPwdActivity.this);
                    grantObservable.subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean granted) {
                            if (granted) {
                                getCodes();
                            } else {
                                PermissionsUtils.showPermissionDeniedDialog(AddPayPwdActivity.this, false);
                            }
                        }
                    });
                }else {
                    getCodes();
                }
                break;
            case R.id.btn_sure:
                if (TextUtils.isEmpty(etCode.getText().toString().trim())) {
                    showToast("验证码码不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(addPayPwd.getText().toString().trim())) {
                    showToast("支付密码不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(surePayPwd.getText().toString().trim())) {
                    showToast("确认密码不能为空！");
                    return;
                }
                if (surePayPwd.getText().toString().trim().length()!=6) {
                    showToast("支付密码为六位数！");
                    return;
                }
                if (!addPayPwd.getText().toString().trim().equals(surePayPwd.getText().toString().trim())) {
                    showToast("两次支付密码输入不正确！");
                    return;
                }

                validade();

                break;
            default:
                break;
        }
    }
        private void getCodes(){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String times=   sdf.format( new Date());

            SmsCodeParamBean smsCodeParamBean=new SmsCodeParamBean();
            smsCodeParamBean.setPlatform("2");
            smsCodeParamBean.setPhone(tell);
            smsCodeParamBean.setTime(times);
            smsCodeParamBean.setUniqueCode(Md5Util.getIMEI(AddPayPwdActivity.this));

            Map<String, String> reqData = new HashMap<>();
            smsCodeParamBean.setSmsType("setPayPassWord");
            reqData.put("smsType", "setPayPassWord");
            reqData.put("phone", tell);
            reqData.put("uniqueCode", Md5Util.getIMEI(AddPayPwdActivity.this));
            reqData.put("platform", "2");
            reqData.put("time", times);
            try {
                smsCodeParamBean.setSign(Md5Util.generateSignature(reqData,Constant.MD5KEY));
            } catch (Exception e) {
                e.printStackTrace();
            }
            smsCodePresenter.getSms(getVersionCodes(), smsCodeParamBean);
        }

    private void validade(){
        ValidateSmsCodeParamBean validateSmsCodeParamBean=new ValidateSmsCodeParamBean();
        validateSmsCodeParamBean.setPhone(tell);
        validateSmsCodeParamBean.setSmsCode(etCode.getText().toString().trim());
        validateSmsCodeParamBean.setSmsType("setPayPassWord");
        smsCodePresenter.getValidateSms(getVersionCodes(),validateSmsCodeParamBean);
    }
    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getSetPayPwd(SetPayPwdResultBean setPayPwdResultBean) {
        if("000000".equals(setPayPwdResultBean.getCode())){
            showToast("设置支付密码成功！");
            finish();
        }else {
            showToast(setPayPwdResultBean.getMessage());
        }
    }

    @Override
    public void getResetPayPwd(ResetPayPwdResultBean resetPayPwdResultBean) {

    }

    @Override
    public void getSms(SmsCodeResultBean smsCodeResultBean) {
        if("000000".equals(smsCodeResultBean.getCode())){
        }else {
            showToast(smsCodeResultBean.getMessage());
        }
    }

    @Override
    public void getValidateSms(SmsCodeResultBean smsCodeResultBean) {

        if("000000".equals(smsCodeResultBean.getCode())){
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization",token);
            reqData.put("version",getVersionCodes());
            SetPayPwdParamBean setPayPwdParamBean=new SetPayPwdParamBean();
            setPayPwdParamBean.setNewPayPassWord(addPayPwd.getText().toString().trim());
            setPayPwdPresenter.getSetPayPwd(reqData,setPayPwdParamBean);
        }else {
            showToast(smsCodeResultBean.getMessage());
        }
    }

    @Override
    public void countNumber(String msg) {
        btnCode.setText(msg);
    }

    @Override
    public void setClickable(boolean clickable) {
        if(clickable){
            btnCode.setClickable(true);
            btnCode.setBackground(ContextCompat.getDrawable(AddPayPwdActivity.this,R.drawable.shape_oval_btn));
        }else{
            btnCode.setClickable(false);
            btnCode.setBackground(ContextCompat.getDrawable(AddPayPwdActivity.this,R.drawable.shape_oval_gray_btn));
        }
    }

    @Override
    protected void onDestroy() {
        if(smsCodePresenter!=null){
            smsCodePresenter.getCountDownTimerService().stopCountDown();
            smsCodePresenter.detachView();
        }
        if(setPayPwdPresenter!=null){
            setPayPwdPresenter.detachView();
        }
        super.onDestroy();
    }
}
