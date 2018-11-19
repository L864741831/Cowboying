package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.SetPayPwdBase;
import com.ibeef.cowboying.bean.ResetPayPwdParamBean;
import com.ibeef.cowboying.bean.ResetPayPwdResultBean;
import com.ibeef.cowboying.bean.SetPayPwdResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.SetPayPwdPresenter;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

public class ModifyPayPwdActivity extends BaseActivity implements SetPayPwdBase.IView {

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
    private String token;
    private SetPayPwdPresenter setPayPwdPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pay_pwd);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("修改支付密码");
        token= Hawk.get(HawkKey.TOKEN);
        addPayPwd.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_CLASS_NUMBER);
        surePayPwd.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_CLASS_NUMBER);
        oldPayPwd.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_CLASS_NUMBER);
        setPayPwdPresenter=new SetPayPwdPresenter(this);
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
                if(addPayPwd.getText().toString().trim().length()!=6){
                    showToast("支付密码必须为6为哟！");
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
                Map<String, String> reqData = new HashMap<>();
                reqData.put("Authorization",token);
                reqData.put("version",getVersionCodes());
                ResetPayPwdParamBean resetPayPwdParamBean=new ResetPayPwdParamBean();
                resetPayPwdParamBean.setNewPayPassWord(addPayPwd.getText().toString().trim());
                resetPayPwdParamBean.setOldPayPassWord(oldPayPwd.getText().toString().trim());
                setPayPwdPresenter.getResetPayPwd(reqData,resetPayPwdParamBean);
                break;
            default:
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getSetPayPwd(SetPayPwdResultBean setPayPwdResultBean) {

    }

    @Override
    public void getResetPayPwd(ResetPayPwdResultBean resetPayPwdResultBean) {
        if("000000".equals(resetPayPwdResultBean.getCode())){
            showToast("重置支付密码成功！");
            finish();
        }else {
            showToast(resetPayPwdResultBean.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        if(setPayPwdPresenter!=null){
            setPayPwdPresenter.detachView();
        }
        super.onDestroy();
    }
}
