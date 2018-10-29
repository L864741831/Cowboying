package com.ibeef.cowboying.view.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.EditLogionPwdBase;
import com.ibeef.cowboying.bean.EditLoginPwdParamBean;
import com.ibeef.cowboying.bean.EditLoginPwdResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.EditLoginPwdPresenter;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 重置密码界面
 */
public class ResetPwdActivity extends BaseActivity implements EditLogionPwdBase.IView {

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
    private EditLoginPwdPresenter editLoginPwdPresenter;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        token= Hawk.get(HawkKey.TOKEN);
        setPwd=getIntent().getBooleanExtra("setPwd",false);
        editLoginPwdPresenter=new EditLoginPwdPresenter(this);
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

                EditLoginPwdParamBean editLoginPwdParamBean=new EditLoginPwdParamBean();
                editLoginPwdParamBean.setPassWord(etNewPwd.getText().toString().trim());
                if(setPwd){
                    //设置<账号安全<设置登录密码 跳到获取验证码界面 设置密码
                    editLoginPwdParamBean.setType("5");
                }else {
                    //忘记密码
                    editLoginPwdParamBean.setType("3");
                }

                Map<String, String> reqData = new HashMap<>();
                reqData.put("version", getVersionCodes());
                reqData.put("token", token);// TODO: 2018/10/29
                editLoginPwdPresenter.getEditLoginPwd(reqData,editLoginPwdParamBean);
                break;
            default:
                break;
        }
    }

    @Override
    public void showMsg(String msg) {
        showMsg(msg);
    }

    @Override
    public void getEditLoginPwd(EditLoginPwdResultBean editLoginPwdResultBean) {
        if("000000".equals(editLoginPwdResultBean.getCode())){
            //修改密码成功，重新跳到密码登录的界面，重新登录
            startActivity(PwdLoginActivity.class);
        }else {
            showMsg(editLoginPwdResultBean.getMessage());
        }

    }

    @Override
    protected void onDestroy() {
        if(editLoginPwdPresenter!=null){
            editLoginPwdPresenter.detachView();
        }
        super.onDestroy();
    }


}
