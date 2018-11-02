package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.LoginBase;
import com.ibeef.cowboying.bean.LoginBean;
import com.ibeef.cowboying.bean.LoginParamBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.LoginPresenter;
import com.ibeef.cowboying.utils.TimeUtils;
import com.orhanobut.hawk.Hawk;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 手机密码登录
 */
public class PwdLoginActivity extends BaseActivity implements LoginBase.IView {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.et_mobile)
    EditText etMobile;
    @Bind(R.id.close_img_id)
    ImageView closeImgId;
    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(R.id.close1_img_id)
    ImageView close1ImgId;
    @Bind(R.id.sure_id)
    TextView sureId;
    @Bind(R.id.forget_pwd_id)
    TextView forgetPwdId;
    @Bind(R.id.identify_code_login_id)
    TextView identifyCodeLoginId;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd_login);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        etMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(etMobile.getText().toString().trim())) {
                    closeImgId.setVisibility(View.INVISIBLE);
                } else {
                    closeImgId.setVisibility(View.VISIBLE);
                }
            }
        });
        etPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(etPwd.getText().toString().trim())) {
                    close1ImgId.setVisibility(View.INVISIBLE);
                } else {
                    close1ImgId.setVisibility(View.VISIBLE);
                }
            }
        });

        loginPresenter = new LoginPresenter(this);
    }

    @OnClick({R.id.back_id, R.id.close_img_id, R.id.close1_img_id, R.id.sure_id, R.id.forget_pwd_id, R.id.identify_code_login_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.close_img_id:
                etMobile.setText("");
                break;
            case R.id.close1_img_id:
                etPwd.setText("");
                break;
            case R.id.sure_id:
                if (TextUtils.isEmpty(etMobile.getText().toString().trim())) {
                    showToast("手机号码不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(etPwd.getText().toString().trim())) {
                    showToast("密码不能为空！");
                    return;
                }
                if (!TimeUtils.isMatchered(TimeUtils.PHONE_PATTERN, etMobile.getText().toString().trim())) {
                    showToast("请输入正确的手机号码！");
                    return;
                }

                LoginParamBean loginParamBean = new LoginParamBean();
                loginParamBean.setUserName(etMobile.getText().toString().trim());
                loginParamBean.setType("1");
                //1：密码登录；2：短信验证码登录
                loginParamBean.setPassword(etPwd.getText().toString().trim());
                loginPresenter.getUserLogin(getVersionCodes(), loginParamBean);

                break;
            case R.id.forget_pwd_id:
                //通过手机号验证码 修改密码
                Intent intent = new Intent(PwdLoginActivity.this, MobileLoginActivity.class);
                intent.putExtra("stadus", "1");
                startActivity(intent);
                break;
            case R.id.identify_code_login_id:
                //验证码登录
                Intent intent2 = new Intent(PwdLoginActivity.this, MobileLoginActivity.class);
                intent2.putExtra("stadus", "0");
                startActivity(intent2);
                break;
            default:
                break;
        }
    }

    @Override
    public void showMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void getUserLogin(LoginBean loginBean) {
        if("000000".equals(loginBean.getCode())){
            Hawk.put(HawkKey.TOKEN, loginBean.getBizData());

            Intent intent1=new Intent(PwdLoginActivity.this,MainActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent1);
        }else {
            showToast(loginBean.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        if(loginPresenter!=null){
            loginPresenter.detachView();
        }
        super.onDestroy();
    }
}
