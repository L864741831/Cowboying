package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.AccountRegisterBase;
import com.ibeef.cowboying.base.LoginBase;
import com.ibeef.cowboying.base.SmscodeBase;
import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.LoginBean;
import com.ibeef.cowboying.bean.LoginParamBean;
import com.ibeef.cowboying.bean.SmsCodeParamBean;
import com.ibeef.cowboying.bean.SmsCodeResultBean;
import com.ibeef.cowboying.bean.ValidateSmsCodeParamBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.presenter.AccountRegisterPresenter;
import com.ibeef.cowboying.presenter.LoginPresenter;
import com.ibeef.cowboying.presenter.SmsCodePresenter;
import com.ibeef.cowboying.utils.Md5Util;
import com.ibeef.cowboying.utils.VerificationCodeInput;

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
 * 输入验证码界面
 */
public class IdentifyCodeActivity extends BaseActivity implements AccountRegisterBase.IView ,SmscodeBase.IView ,LoginBase.IView {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.stadus_title_txt_id)
    TextView stadusTitleTxtId;
    @Bind(R.id.mobile_txt_id)
    TextView mobileTxtId;
    @Bind(R.id.verificationCodeInput_id)
    VerificationCodeInput verificationCodeInputId;
    @Bind(R.id.sure_id)
    TextView sureId;
    @Bind(R.id.again_getcode_id)
    TextView againGetcodeId;

    private String stadus;
    private String contents,mobile;
    private SmsCodePresenter smsCodePresenter;
    private AccountRegisterPresenter accountRegisterPresenter;
    private LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_code);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        //1 忘记密码流程
        stadus=getIntent().getStringExtra("stadus");
        mobile=getIntent().getStringExtra("mobile");
        mobileTxtId.setText("+86  "+mobile);
        verificationCodeInputId.setOnCompleteListener(new VerificationCodeInput.Listener() {
            @Override
            public void onComplete(String content) {
                contents=content;
                Log.e(Constant.TAG, "完成输入：" + content);
            }
        });

        smsCodePresenter=new SmsCodePresenter(this);
        accountRegisterPresenter=new AccountRegisterPresenter(this);
        loginPresenter=new LoginPresenter(this);
        getCodes();
    }
    @OnClick({R.id.sure_id, R.id.again_getcode_id,R.id.back_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sure_id:
                if(TextUtils.isEmpty(contents)){
                    showToast("请完善验证码！");
                    return;
                }
                if("0".equals(stadus)){
                    //调登录接口
                    LoginParamBean loginParamBean=new LoginParamBean();
                    loginParamBean.setUserName(mobile);
                    loginParamBean.setType("2");
                    //1：密码登录；2：短信验证码登录
                    loginParamBean.setSmsCode(contents);
                    loginPresenter.getUserLogin(getVersionCodes(),loginParamBean);
                }else  if("2".equals(stadus)){
                  //注册接口
                    AccountRegisterParamBean accountRegisterParamBean=new AccountRegisterParamBean();
                    accountRegisterParamBean.setMobile(mobile);
                    accountRegisterParamBean.setCode(contents);
                    accountRegisterPresenter.getAccoutRegister(getVersionCodes(),accountRegisterParamBean);

                }else {
                    //校验验证码接口
                    validade();
                }
                break;
            case R.id.again_getcode_id:
                //验证码
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    rx.Observable<Boolean> grantObservable = PermissionsUtils.getPhoneCode(IdentifyCodeActivity.this);
                    grantObservable.subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean granted) {
                            if (granted) {
                                getCodes();
                            } else {
                                PermissionsUtils.showPermissionDeniedDialog(IdentifyCodeActivity.this, false);
                            }
                        }
                    });
                }else {
                    getCodes();
                }
                break;
            case R.id.back_id:
                finish();
                break;
            default:
                break;
        }
    }


    private void validade(){
        ValidateSmsCodeParamBean validateSmsCodeParamBean=new ValidateSmsCodeParamBean();
        validateSmsCodeParamBean.setPhone(mobile);
        validateSmsCodeParamBean.setSmsCode(contents);
        switch (stadus){
            case "0":
                validateSmsCodeParamBean.setSmsType("login");
                break;
            case "1":
                validateSmsCodeParamBean.setSmsType("findPwd");
                break;
            case "2":
                validateSmsCodeParamBean.setSmsType("register");
                break;
            case "3":
                validateSmsCodeParamBean.setSmsType("editPhone");
                break;
            case "4":
                validateSmsCodeParamBean.setSmsType("editPhone");
                break;
            case "5":
                validateSmsCodeParamBean.setSmsType("addPwd");
                break;
            case "6":
                validateSmsCodeParamBean.setSmsType("editPhone");
                break;
            default:
                break;
        }
        smsCodePresenter.getValidateSms(getVersionCodes(),validateSmsCodeParamBean);
    }
    private void getCodes(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String times=   sdf.format( new Date());

        SmsCodeParamBean smsCodeParamBean=new SmsCodeParamBean();
        smsCodeParamBean.setPlatform("2");
        smsCodeParamBean.setPhone(mobile);
        smsCodeParamBean.setTime(times);
        smsCodeParamBean.setUniqueCode(Md5Util.getIMEI(IdentifyCodeActivity.this));

        Map<String, String> reqData = new HashMap<>();
        switch (stadus){
            case "0":
                smsCodeParamBean.setSmsType("login");
                reqData.put("smsType", "login");
                break;
            case "1":
                smsCodeParamBean.setSmsType("findPwd");
                reqData.put("smsType", "findPwd");
                break;
            case "2":
                smsCodeParamBean.setSmsType("register");
                reqData.put("smsType", "register");
                break;
            case "3":
                smsCodeParamBean.setSmsType("editPhone");
                reqData.put("smsType", "editPhone");
                break;
            case "4":
                smsCodeParamBean.setSmsType("editPhone");
                reqData.put("smsType", "editPhone");
                break;
            case "5":
                smsCodeParamBean.setSmsType("addPwd");
                reqData.put("smsType", "addPwd");
                break;
            case "6":
                smsCodeParamBean.setSmsType("editPhone");
                reqData.put("smsType", "editPhone");
                break;
            default:
                break;
        }
        reqData.put("phone", mobile);
        reqData.put("uniqueCode", Md5Util.getIMEI(IdentifyCodeActivity.this));
        reqData.put("platform", "2");
        reqData.put("time", times);
        try {
            smsCodeParamBean.setSign(Md5Util.generateSignature(reqData,Constant.MD5KEY));
        } catch (Exception e) {
            e.printStackTrace();
        }
        smsCodePresenter.getSms(getVersionCodes(), smsCodeParamBean);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(IdentifyCodeActivity.this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void getUserLogin(LoginBean loginBean) {
        if("000000".equals(loginBean.getCode())){
            Intent intent=new Intent(IdentifyCodeActivity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }else {
            showMsg(loginBean.getMessage());
        }

    }

    @Override
    public void getSms(SmsCodeResultBean smsCodeResultBean) {
        if("000000".equals(smsCodeResultBean.getCode())){
        }else {
            showMsg(smsCodeResultBean.getMessage());
        }
    }

    @Override
    public void getValidateSms(SmsCodeResultBean smsCodeResultBean) {
        if("000000".equals(smsCodeResultBean.getCode())){
            if("1".equals(stadus)||"5".equals(stadus)){
                //1来自忘记密码，跳到重置密码界面，  //5 设置<账号安全<设置登录密码
                Intent intent=new Intent(IdentifyCodeActivity.this,ResetPwdActivity.class);
                if("1".equals(stadus)){
                    intent.putExtra("setPwd",false);
                }else if ("5".equals(stadus)){
                    intent.putExtra("setPwd",true);
                }
                startActivity(intent);
            }else   if("3".equals(stadus)){
                //0 正常手机号登录完成  //3 第三方登录绑定手机号
                Intent intent=new Intent(IdentifyCodeActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }else   if("4".equals(stadus)){
                //2注册流程 4 //设置<账号安全<修改登录手机号
                Intent intent=new Intent(IdentifyCodeActivity.this,MobileLoginActivity.class);
                intent.putExtra("stadus","6");
                startActivity(intent);
            }else if("6".equals(stadus)){
                //设置<账号安全<修改登录手机号 <验证码，输入新的手机号 获取验证码的界面
                startActivity(LoginActivity.class);
            }
        }else {
            showMsg(smsCodeResultBean.getMessage());
        }

    }

    @Override
    public void getAccoutRegister(AccountRegisterResultBean accountRegisterResultBean) {
        if("000000".equals(accountRegisterResultBean.getCode())){
            Intent intent=new Intent(IdentifyCodeActivity.this,MobileLoginActivity.class);
            intent.putExtra("stadus","2");
            startActivity(intent);
        }else {
            showMsg(accountRegisterResultBean.getMessage());
        }

    }



    @Override
    protected void onDestroy() {
        if(smsCodePresenter != null){
            smsCodePresenter.detachView();
        }
        if(loginPresenter != null){
            loginPresenter.detachView();
        }
        if(accountRegisterPresenter != null){
            accountRegisterPresenter.detachView();
        }
        super.onDestroy();
    }

}
