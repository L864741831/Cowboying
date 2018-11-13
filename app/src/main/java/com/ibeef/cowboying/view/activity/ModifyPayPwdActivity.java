package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibeef.cowboying.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

public class ModifyPayPwdActivity extends BaseActivity {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.old_pay_pwd)
    EditText oldPayPwd;
    @Bind(R.id.add_pay_pwd)
    EditText addPayPwd;
    @Bind(R.id.sure_pay_pwd)
    EditText surePayPwd;
    @Bind(R.id.btn_sure)
    TextView btnSure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pay_pwd);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("修改支付密码");
        addPayPwd.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_CLASS_NUMBER);
        surePayPwd.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_CLASS_NUMBER);
        oldPayPwd.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_CLASS_NUMBER);
    }

    @OnClick({R.id.back_id, R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.btn_sure:
                if (TextUtils.isEmpty(oldPayPwd.getText().toString().trim())) {
                    showToast("原密码不能为空！");
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
}
