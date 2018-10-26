package com.ranhan.cowboying.view.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ranhan.cowboying.R;
import com.ranhan.cowboying.utils.TimeUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 重置密码界面
 */
public class ResetPwdActivity extends BaseActivity {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.et_new_pwd)
    EditText etNewPwd;
    @Bind(R.id.close_img_id)
    ImageView closeImgId;
    @Bind(R.id.et_again_pwd)
    EditText etAgainPwd;
    @Bind(R.id.close1_img_id)
    ImageView close1ImgId;
    @Bind(R.id.sure_id)
    TextView sureId;
    @Bind(R.id.stadus_title_id)
    TextView stadusTitleId;
    private boolean setPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        setPwd=getIntent().getBooleanExtra("setPwd",false);
        if(setPwd){
            //设置<账号安全<设置登录密码 跳到获取验证码界面 设置密码
            stadusTitleId.setText("设置密码");
        }else {
            //忘记密码
            stadusTitleId.setText("重置密码");
        }
        etNewPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(TextUtils.isEmpty(etNewPwd.getText().toString().trim())){
                    closeImgId.setVisibility(View.INVISIBLE);
                }else {
                    closeImgId.setVisibility(View.VISIBLE);
                }
            }
        });
        etAgainPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(TextUtils.isEmpty(etAgainPwd.getText().toString().trim())){
                    close1ImgId.setVisibility(View.INVISIBLE);
                }else {
                    close1ImgId.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @OnClick({R.id.back_id, R.id.close_img_id, R.id.close1_img_id, R.id.sure_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.close_img_id:
                etNewPwd.setText("");
                break;
            case R.id.close1_img_id:
                etAgainPwd.setText("");
                break;
            case R.id.sure_id:
                if(TextUtils.isEmpty(etNewPwd.getText().toString().trim())){
                    showToast("手机号码不能为空！");
                    return;
                }
                if(TextUtils.isEmpty(etAgainPwd.getText().toString().trim())){
                    showToast("密码不能为空！");
                    return;
                }
                if(!etNewPwd.getText().toString().trim().equals(etAgainPwd.getText().toString().trim())){
                    showToast("两次密码输入不相同~，请重新输入！");
                    return;
                }
                //修改密码成功，重新跳到密码登录的界面，重新登录
                startActivity(PwdLoginActivity.class);
                break;
            default:
                break;
        }
    }
}
