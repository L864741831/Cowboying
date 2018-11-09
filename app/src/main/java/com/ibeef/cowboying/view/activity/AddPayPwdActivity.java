package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.SmscodeBase;
import com.ibeef.cowboying.bean.SmsCodeResultBean;
import com.ibeef.cowboying.presenter.SmsCodePresenter;
import com.ibeef.cowboying.utils.TimeUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 设置支付密码
 */
public class AddPayPwdActivity extends BaseActivity implements SmscodeBase.IView {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pay_pwd);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("设置支付密码");
        addPayPwd.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_CLASS_NUMBER);
        surePayPwd.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_CLASS_NUMBER);
        smsCodePresenter=new SmsCodePresenter(this);
    }

    @OnClick({R.id.back_id, R.id.btn_code, R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.btn_code:
                break;
            case R.id.btn_sure:
                if (TextUtils.isEmpty(etMobile.getText().toString().trim())) {
                    showToast("手机号码不能为空！");
                    return;
                }
                if (!TimeUtils.isMatchered(TimeUtils.PHONE_PATTERN, etMobile.getText().toString().trim())) {
                    showToast("请输入正确的手机号码！");
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
                if (!addPayPwd.getText().toString().trim().equals(surePayPwd.getText().toString().trim())) {
                    showToast("两次支付密码输入不正确！");
                    return;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getSms(SmsCodeResultBean smsCodeResultBean) {

    }

    @Override
    public void getValidateSms(SmsCodeResultBean smsCodeResultBean) {

    }

    @Override
    protected void onDestroy() {
        if(smsCodePresenter!=null){
            smsCodePresenter.detachView();
        }
        super.onDestroy();
    }
}
