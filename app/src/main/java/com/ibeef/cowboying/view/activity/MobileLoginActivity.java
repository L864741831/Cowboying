package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Build;
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

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.utils.TimeUtils;
import com.orhanobut.hawk.Hawk;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;
import rxfamily.utils.PermissionsUtils;
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
    @Bind(R.id.action_login_tv)
    TextView action_login_tv;
    @Bind(R.id.show_bind_rv)
    RelativeLayout showBindRv;
    private String stadus;
    private String token,oldmobile;
    private int visitorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_login);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        token = Hawk.get(HawkKey.TOKEN);
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

        actionRightTv.setText("跳过");
        stadus = getIntent().getStringExtra("stadus");
        if ("1".equals(stadus)) {
            //1 来自密码登录 的忘记密码，0 为正常的手机号登录流程
            stadusTitleId.setText("验证手机号");
            sureId.setText("发送验证码");
            pwdLoginId.setVisibility(View.GONE);
        } else if ("2".equals(stadus)) {
            //注册流程
            action_login_tv.setText("登录");
            action_login_tv.setVisibility(View.VISIBLE);
            pwdLoginId.setVisibility(View.GONE);
            registerRuleId.setVisibility(View.VISIBLE);
            stadusTitleId.setText("手机号注册");
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                registerRuleId.setText(Html.fromHtml("注册代表你已同意<font color='#4393F5'>注册协议</font>和<font color='#4393F5'>隐私政策</font>", Html.FROM_HTML_MODE_LEGACY));
            } else {
                registerRuleId.setText(Html.fromHtml("注册代表你已同意<font color='#4393F5'>注册协议</font>和<font color='#4393F5'>隐私政策</font>"));
            }

        } else if ("3".equals(stadus)) {
            //第三方登录绑定
            actionRightTv.setVisibility(View.GONE);
            pwdLoginId.setVisibility(View.GONE);
            stadusTitleId.setText("绑定手机号");
            visitorId=getIntent().getIntExtra("visitorId",0);
        } else if ("7".equals(stadus)) {
            //个人信息 绑定手机号
            actionRightTv.setVisibility(View.GONE);
            pwdLoginId.setVisibility(View.GONE);
            stadusTitleId.setText("绑定手机号");
        } else if ("8".equals(stadus) || "6".equals(stadus)) {
            //换绑手机号    //设置<账号安全<修改登录手机号 <验证码，输入新的手机号
            pwdLoginId.setVisibility(View.GONE);
            stadusTitleId.setText("输入新的手机号");
            oldmobile = getIntent().getStringExtra("oldmobile");
        }else if("0".equals(stadus)){
            action_login_tv.setText("注册");
            stadusTitleId.setText("手机号登录");
            action_login_tv.setVisibility(View.VISIBLE);
        }
        cancleTxtId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  第三方登录取消绑定手机号  跳转到主界面
                removeALLActivity();
                Intent intent1 = new Intent(MobileLoginActivity.this, MainActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                finish();
            }
        });
    }

    @OnClick({R.id.close_img_id, R.id.sure_id, R.id.back_id, R.id.pwd_login_id, R.id.action_right_tv, R.id.sure_txt_id, R.id.register_rule_id,R.id.action_login_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close_img_id:
                etMobile.setText("");
                break;
            case R.id.action_right_tv:
                showBindRv.setVisibility(View.VISIBLE);
                break;
            case R.id.sure_txt_id:
                //  第三方登录绑定手机号  继续绑定
                showBindRv.setVisibility(View.GONE);
                break;
            case R.id.sure_id:
                if (TextUtils.isEmpty(etMobile.getText().toString().trim())) {
                    showToast("手机号码不能为空！");
                    return;
                }
                if (!TimeUtils.isMatchered(TimeUtils.PHONE_PATTERN, etMobile.getText().toString().trim())) {
                    showToast("请输入正确的手机号码！");
                    return;
                }

                if ("8".equals(stadus) || "6".equals(stadus)) {
                    //设置 账号安全 手机号换绑 修改登录手机号
                    Intent intent = new Intent(MobileLoginActivity.this, IdentifyCodeActivity.class);
                    if ("6".equals(stadus)) {
                        intent.putExtra("stadus", "10");
                    } else {
                        intent.putExtra("stadus", "9");
                    }
                    intent.putExtra("oldmobile", oldmobile);
                    intent.putExtra("mobile", etMobile.getText().toString().trim());
                    startActivity(intent);
                    finish();
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        rx.Observable<Boolean> grantObservable = PermissionsUtils.getPhoneCode(MobileLoginActivity.this);
                        grantObservable.subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean granted) {
                                if (granted) {
                                    Intent intent = new Intent(MobileLoginActivity.this, IdentifyCodeActivity.class);
                                    intent.putExtra("stadus", stadus);
                                    intent.putExtra("mobile", etMobile.getText().toString().trim());
                                    if("3".equals(stadus)){
                                        intent.putExtra("visitorId",visitorId);
                                    }
                                    startActivity(intent);
                                    finish();
                                } else {
                                    PermissionsUtils.showPermissionDeniedDialog(MobileLoginActivity.this, false);
                                }
                            }
                        });
                    } else {
                        Intent intent = new Intent(MobileLoginActivity.this, IdentifyCodeActivity.class);
                        intent.putExtra("stadus", stadus);
                        intent.putExtra("mobile", etMobile.getText().toString().trim());
                        if("3".equals(stadus)){
                            intent.putExtra("visitorId",visitorId);
                        }
                        startActivity(intent);
                        finish();
                    }
                }
                break;
            case R.id.back_id:
                finish();
                break;
            case R.id.register_rule_id:
                Intent intent=new Intent(MobileLoginActivity.this,AdWebviewActivity.class);
                intent.putExtra("url","http://h5.ibeef.vip/protocol/index.html");
                intent.putExtra("title","注册协议");
                startActivity(intent);
                break;
            case R.id.pwd_login_id:
                if ("0".equals(stadus)) {
                    //正常登录流程
                    startActivity(PwdLoginActivity.class);
                }
                break;
            case R.id.action_login_tv:
                Intent intent1=new Intent(MobileLoginActivity.this,MobileLoginActivity.class);
                if("0".equals(stadus)){
                    //来自登录头，去注册
                    intent1.putExtra("stadus","2");
                }else {
                    //来自注册，去登陆
                    intent1.putExtra("stadus","0");
                }
                startActivity(intent1);
                finish();
                break;
            default:
                break;
        }
    }

}
