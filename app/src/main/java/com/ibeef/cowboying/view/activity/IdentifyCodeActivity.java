package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.utils.VerificationCodeInput;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;
import rxfamily.utils.PermissionsUtils;
import rxfamily.view.BaseActivity;

/**
 * 输入验证码界面
 */
public class IdentifyCodeActivity extends BaseActivity {

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

    }
    @OnClick({R.id.sure_id, R.id.again_getcode_id,R.id.back_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sure_id:
                if(TextUtils.isEmpty(contents)){
                    showToast("请完善验证码！");
                    return;
                }
                if("1".equals(stadus)){
                    //1来自忘记密码，跳到重置密码界面，
                    Intent intent=new Intent(IdentifyCodeActivity.this,ResetPwdActivity.class);
                    intent.putExtra("setPwd",false);
                    startActivity(intent);
                }else   if("0".equals(stadus)){
                   //0 正常手机号登录完成
                    Intent intent=new Intent(IdentifyCodeActivity.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else   if("2".equals(stadus)){
                    //注册流程
                    Intent intent=new Intent(IdentifyCodeActivity.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else   if("3".equals(stadus)){
                    //第三方登录绑定手机号
                    Intent intent=new Intent(IdentifyCodeActivity.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else if("4".equals(stadus)){
                    //设置<账号安全<设置登录密码
                    Intent intent=new Intent(IdentifyCodeActivity.this,ResetPwdActivity.class);
                    intent.putExtra("setPwd",true);
                    startActivity(intent);
                }else if("5".equals(stadus)){
                    //设置<账号安全<修改登录手机号
                    Intent intent=new Intent(IdentifyCodeActivity.this,MobileLoginActivity.class);
                    intent.putExtra("stadus","6");
                    startActivity(intent);
                }else if("6".equals(stadus)){
                    //设置<账号安全<修改登录手机号 <验证码，输入新的手机号 获取验证码的界面
                  startActivity(LoginActivity.class);
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
                                // TODO: 2018/10/25 网络获取验证码
                            } else {
                                PermissionsUtils.showPermissionDeniedDialog(IdentifyCodeActivity.this, false);
                            }
                        }
                    });
                }else {
                    // TODO: 2018/10/25 网络获取验证码
                }
                break;
            case R.id.back_id:
                finish();
                break;
            default:
                break;
        }
    }
}
