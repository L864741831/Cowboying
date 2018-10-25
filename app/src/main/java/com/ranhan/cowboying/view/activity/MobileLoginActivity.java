package com.ranhan.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ranhan.cowboying.R;
import com.ranhan.cowboying.utils.TimeUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 手机号登录
 */
public class MobileLoginActivity extends BaseActivity {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.et_mobile)
    EditText etMobile;
    @Bind(R.id.close_img_id)
    ImageView closeImgId;
    @Bind(R.id.sure_id)
    TextView sureId;
    @Bind(R.id.stadus_title_id)
    TextView stadusTitleId;
    @Bind(R.id.pwd_login_id)
    TextView pwdLoginId;
    @Bind(R.id.action_right_tv)
    TextView actionRightTv;
    @Bind(R.id.cancle_txt_id)
    TextView cancleTxtId;
    @Bind(R.id.sure_txt_id)
    TextView sureTxtId;
    @Bind(R.id.register_rule_id)
    TextView registerRuleId;
    @Bind(R.id.show_bind_rv)
    RelativeLayout showBindRv;
    private String stadus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_login);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        etMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(TextUtils.isEmpty(etMobile.getText().toString().trim())){
                    closeImgId.setVisibility(View.INVISIBLE);
                }else {
                    closeImgId.setVisibility(View.VISIBLE);
                }
            }
        });

        actionRightTv.setText("跳过");
        stadus=getIntent().getStringExtra("stadus");
        if("1".equals(stadus)){
            //1 来自密码登录 的忘记密码，0 为正常的手机号登录流程
            stadusTitleId.setText("验证手机号");
            sureId.setText("发送验证码");
            pwdLoginId.setVisibility(View.GONE);
        }else  if("2".equals(stadus)){
            //注册流程
            pwdLoginId.setVisibility(View.GONE);
            registerRuleId.setVisibility(View.VISIBLE);
            stadusTitleId.setText("输入手机号码");
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                registerRuleId.setText(Html.fromHtml("注册代表你已同意<font color='#4393F5'>注册协议</font>和<font color='#4393F5'>隐私政策</font>",Html.FROM_HTML_MODE_LEGACY));
            } else {
                registerRuleId.setText(Html.fromHtml("注册代表你已同意<font color='#4393F5'>注册协议</font>和<font color='#4393F5'>隐私政策</font>"));
            }

        }else if("3".equals(stadus)){
            //第三方登录绑定
            actionRightTv.setVisibility(View.VISIBLE);
            pwdLoginId.setVisibility(View.GONE);
            stadusTitleId.setText("绑定手机号");
        }else if("6".equals(stadus)){
            //设置<账号安全<修改登录手机号 <验证码，输入新的手机号
            pwdLoginId.setVisibility(View.GONE);
            stadusTitleId.setText("输入新的手机号");
        }
    }

    @OnClick({R.id.close_img_id, R.id.sure_id,R.id.back_id,R.id.pwd_login_id,R.id.action_right_tv,R.id.cancle_txt_id,R.id.sure_txt_id,R.id.show_bind_rv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close_img_id:
                etMobile.setText("");
                break;
            case R.id.action_right_tv:
                showBindRv.setVisibility(View.VISIBLE);
                break;
            case R.id.show_bind_rv:
                showBindRv.setVisibility(View.GONE);
                break;
            case R.id.cancle_txt_id:
                // TODO: 2018/10/25 第三方登录绑定手机号 网络请求成功 跳转到主界面
                Intent intent1=new Intent(MobileLoginActivity.this,MainActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                break;
            case R.id.sure_txt_id:
                showBindRv.setVisibility(View.GONE);
                break;
            case R.id.sure_id:
                if(TextUtils.isEmpty(etMobile.getText().toString().trim())){
                    showToast("手机号码不能为空！");
                    return;
                }
                if(!TimeUtils.isMatchered(TimeUtils.PHONE_PATTERN,etMobile.getText().toString().trim())){
                    showToast("请输入正确的手机号码！");
                    return;
                }
                Intent  intent=new Intent(MobileLoginActivity.this,IdentifyCodeActivity.class);
                intent.putExtra("stadus",stadus);
                intent.putExtra("mobile",etMobile.getText().toString().trim());
                startActivity(intent);
                break;
            case R.id.back_id:
                finish();
                break;
            case R.id.pwd_login_id:
                if("0".equals(stadus)){
                    //正常登录流程
                    startActivity(PwdLoginActivity.class);
                }else if("2".equals(stadus)){
                        //注册流程
                }
                break;
            default:
                break;
        }
    }
}
