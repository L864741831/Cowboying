package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.AccountRegisterBase;
import com.ibeef.cowboying.base.BindMobileBase;
import com.ibeef.cowboying.base.LoginBase;
import com.ibeef.cowboying.base.SmscodeBase;
import com.ibeef.cowboying.base.UpdateMobileBase;
import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.BindMobileParamBean;
import com.ibeef.cowboying.bean.BindMobileResultBean;
import com.ibeef.cowboying.bean.LoginBean;
import com.ibeef.cowboying.bean.LoginParamBean;
import com.ibeef.cowboying.bean.SmsCodeParamBean;
import com.ibeef.cowboying.bean.SmsCodeResultBean;
import com.ibeef.cowboying.bean.UpdateMobileParamBean;
import com.ibeef.cowboying.bean.UpdateMobileResultBean;
import com.ibeef.cowboying.bean.ValidateSmsCodeParamBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.AccountRegisterPresenter;
import com.ibeef.cowboying.presenter.BindMobilePresenter;
import com.ibeef.cowboying.presenter.LoginPresenter;
import com.ibeef.cowboying.presenter.SmsCodePresenter;
import com.ibeef.cowboying.presenter.UpdateMobilePresenter;
import com.ibeef.cowboying.utils.Md5Util;
import com.ibeef.cowboying.utils.VerificationCodeInput;
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
 * 输入验证码界面
 */
public class IdentifyCodeActivity extends BaseActivity implements AccountRegisterBase.IView ,SmscodeBase.IView ,LoginBase.IView ,UpdateMobileBase.IView , BindMobileBase.IView {

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
    private String contents,mobile,oldmobile;
    private SmsCodePresenter smsCodePresenter;
    private AccountRegisterPresenter accountRegisterPresenter;
    private LoginPresenter loginPresenter;
    private UpdateMobilePresenter updateMobilePresenter;
    private String token;
    private BindMobilePresenter bindMobilePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_code);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        token= Hawk.get(HawkKey.TOKEN);
        bindMobilePresenter=new BindMobilePresenter(this);
        //1 忘记密码流程
        stadus=getIntent().getStringExtra("stadus");
        if("8".equals(stadus)||"4".equals(stadus)){
            oldmobile=getIntent().getStringExtra("oldmobile");
            mobileTxtId.setText("+86  "+oldmobile);
        }else if("9".equals(stadus)||"10".equals(stadus)){
            oldmobile=getIntent().getStringExtra("oldmobile");
            mobile=getIntent().getStringExtra("mobile");
            mobileTxtId.setText("+86  "+mobile);
        }else {
            mobile=getIntent().getStringExtra("mobile");
            mobileTxtId.setText("+86  "+mobile);
        }

        verificationCodeInputId.setOnCompleteListener(new VerificationCodeInput.Listener() {
            @Override
            public void onComplete(String content) {
                contents=content;
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                Log.e(Constant.TAG, "完成输入：" + content);
            }
        });

        smsCodePresenter=new SmsCodePresenter(this);
        accountRegisterPresenter=new AccountRegisterPresenter(this);
        loginPresenter=new LoginPresenter(this);
        updateMobilePresenter=new UpdateMobilePresenter(this);
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
        if("8".equals(stadus)||"4".equals(stadus)){
            validateSmsCodeParamBean.setPhone(oldmobile);
        }else {
            validateSmsCodeParamBean.setPhone(mobile);
        }

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
                validateSmsCodeParamBean.setSmsType("bindPhone");
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
            case "7":
                validateSmsCodeParamBean.setSmsType("bindPhone");
                break;
            case "8":
                validateSmsCodeParamBean.setSmsType("editPhone");
                break;
            case "9":
                validateSmsCodeParamBean.setSmsType("editPhone");
                break;
            case "10":
                validateSmsCodeParamBean.setSmsType("editPhone");
                break;
            case "11":
                validateSmsCodeParamBean.setSmsType("editPwd");
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
        if("8".equals(stadus)||"4".equals(stadus)){
            smsCodeParamBean.setPhone(oldmobile);
        }else {
            smsCodeParamBean.setPhone(mobile);
        }
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
                smsCodeParamBean.setSmsType("bindPhone");
                reqData.put("smsType", "bindPhone");
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
            case "7":
                smsCodeParamBean.setSmsType("bindPhone");
                reqData.put("smsType", "bindPhone");
                break;
            case "8":
                smsCodeParamBean.setSmsType("editPhone");
                reqData.put("smsType", "editPhone");
                break;
            case "9":
                smsCodeParamBean.setSmsType("editPhone");
                reqData.put("smsType", "editPhone");
                break;
            case "10":
                smsCodeParamBean.setSmsType("editPhone");
                reqData.put("smsType", "editPhone");
                break;
            case "11":
                smsCodeParamBean.setSmsType("editPwd");
                reqData.put("smsType", "editPwd");
                break;
            default:
                break;
        }
        if("8".equals(stadus)||"4".equals(stadus)){
            reqData.put("phone", oldmobile);
        }else {
            reqData.put("phone", mobile);
        }
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
        if(!TextUtils.isEmpty(msg)){
            if(msg.contains("401")){
                Hawk.put(HawkKey.TOKEN, "");
                Toast.makeText(this,"Authorization失效，请重新登录",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
        }
    }

    @Override
    public void getUpdateMobile(UpdateMobileResultBean updateMobileResultBean) {
        if("000000".equals(updateMobileResultBean.getCode())){
            if("9".equals(stadus)){
                showToast("换绑手机号成功~");
            }else  if("10".equals(stadus)){
                Hawk.put(HawkKey.TOKEN, "");
                startActivity(LoginActivity.class);
                showToast("登陆手机号设置成功，请重新登陆~");
            }
        }else {
            showToast(updateMobileResultBean.getMessage());
        }
    }

    @Override
    public void getUserLogin(LoginBean loginBean) {
        if("000000".equals(loginBean.getCode())){
            Hawk.put(HawkKey.TOKEN, loginBean.getBizData());

            Intent intent=new Intent(IdentifyCodeActivity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }else {
            showToast(loginBean.getMessage());
        }

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
            if("1".equals(stadus)||"5".equals(stadus)||"11".equals(stadus)){
                //1来自忘记密码，跳到重置密码界面，  //5 11设置<账号安全<设置登录密码  修改登录密码
                Intent intent=new Intent(IdentifyCodeActivity.this,ResetPwdActivity.class);
                if("1".equals(stadus)){
                    intent.putExtra("setPwd",false);
                }else if ("5".equals(stadus)||"11".equals(stadus)){
                    intent.putExtra("setPwd",true);
                }
                intent.putExtra("mobile",mobile);
                intent.putExtra("stadus",stadus);
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
                intent.putExtra("oldmobile",oldmobile);
                startActivity(intent);
            }else if("8".equals(stadus)){
                 // 8 设置<账号安全<手机号换绑
                Intent intent=new Intent(IdentifyCodeActivity.this,MobileLoginActivity.class);
                intent.putExtra("stadus","8");
                intent.putExtra("oldmobile",oldmobile);
                startActivity(intent);
            }else if("7".equals(stadus)){
                //个人信息 绑定手机号
                Map<String, String> reqData = new HashMap<>();
                reqData.put("Authorization",token);
                reqData.put("version",getVersionCodes());
                BindMobileParamBean bindMobileParamBean=new BindMobileParamBean();
                bindMobileParamBean.setMobile(mobile);
                bindMobilePresenter.getBindMobile(reqData,bindMobileParamBean);
            }else if("9".equals(stadus)||"10".equals(stadus)){
                //设置 账号安全 手机号换绑
                Map<String, String> reqData = new HashMap<>();
                reqData.put("Authorization",token);
                reqData.put("version",getVersionCodes());
                UpdateMobileParamBean  updateMobileParamBean=new UpdateMobileParamBean();
                updateMobileParamBean.setMobile(mobile);
                updateMobileParamBean.setOldMobile(oldmobile);
                updateMobilePresenter.getUpdateMobile(reqData,updateMobileParamBean);
            }
        }else {
            showToast(smsCodeResultBean.getMessage());
        }

    }

    @Override
    public void getAccoutRegister(AccountRegisterResultBean accountRegisterResultBean) {
        if("000000".equals(accountRegisterResultBean.getCode())){
            Intent intent=new Intent(IdentifyCodeActivity.this,MobileLoginActivity.class);
            intent.putExtra("stadus","2");
            startActivity(intent);
        }else {
            showToast(accountRegisterResultBean.getMessage());
        }

    }


    @Override
    public void getBindMobile(BindMobileResultBean bindMobileResultBean) {
        if("000000".equals(bindMobileResultBean.getCode())){
            showToast("绑定手机号成功~");

        }else {
            showToast(bindMobileResultBean.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        if(smsCodePresenter != null){
            smsCodePresenter.detachView();
        }
        if(bindMobilePresenter!=null){
            bindMobilePresenter.detachView();
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
