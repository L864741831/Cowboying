package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.UserInfoBase;
import com.ibeef.cowboying.bean.ModifyHeadResultBean;
import com.ibeef.cowboying.bean.ModifyNickResultBean;
import com.ibeef.cowboying.bean.RealNameParamBean;
import com.ibeef.cowboying.bean.RealNameReaultBean;
import com.ibeef.cowboying.bean.UserInfoResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.UserInfoPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.ibeef.cowboying.utils.ZzUtils;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 实名认证
 */
public class CertificationActivity extends BaseActivity implements UserInfoBase.IView {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.txt1_id)
    TextView txt1Id;
    @Bind(R.id.txt2_id)
    TextView txt2Id;
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.pwd)
    EditText pwd;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.cb_st)
    CheckBox cbSt;
    @Bind(R.id.sure1_id)
    TextView sure1Id;
    @Bind(R.id.xieyi_id)
    TextView xieyiId;
    @Bind(R.id.rv_show_id)
    RelativeLayout rvShowId;
    @Bind(R.id.btn_submit)
    TextView btnSubmit;
    @Bind(R.id.txt3_id)
    TextView txt3Id;
    private String token;
    private boolean isCreat = true;
    private String userId;
    private String mobile;
    private UserInfoPresenter userInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("实名认证");
        token = Hawk.get(HawkKey.TOKEN);
        userId = getIntent().getStringExtra("userId");
        mobile = getIntent().getStringExtra("mobile");
        String realName = getIntent().getStringExtra("realName");
        String realCardNo = getIntent().getStringExtra("realCardNo");
        String flag = getIntent().getStringExtra("flag");
        userInfoPresenter=new UserInfoPresenter(this);
        if ("1".equals(flag)){
            txt2Id.setVisibility(View.GONE);
            txt1Id.setText("恭喜您，实名认证成功");
            etName.setText(realName);
            etCode.setText(realCardNo);
            rvShowId.setVisibility(View.GONE);
            btnSubmit.setVisibility(View.GONE);
            etName.setEnabled(false);
            etCode.setEnabled(false);

        }
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getModifyHead(ModifyHeadResultBean modifyHeadResultBean) {

    }

    @Override
    public void getModifNick(ModifyNickResultBean modifyNickResultBean) {

    }

    @Override
    public void getRealName(RealNameReaultBean realNameReaultBean) {
        if("000000".equals(realNameReaultBean.getCode())){
            showToast("实名认证成功~");
            finish();
        }else {
            showToast(realNameReaultBean.getMessage());
        }
    }

    @Override
    public void getUserInfo(UserInfoResultBean userInfoResultBean) {

    }

    @Override
    public void isTakePhoeto(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @OnClick({R.id.back_id, R.id.xieyi_id, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.xieyi_id:
                Intent intent=new Intent(CertificationActivity.this,AdWebviewActivity.class);
                intent.putExtra("url","http://h5.ibeef.vip/protocol/index.html");
                intent.putExtra("title","用户使用协议");
                startActivity(intent);
                break;
            case R.id.btn_submit:
                if (cbSt.isChecked()) {
                    if (TextUtils.isEmpty(etName.getText().toString().trim())) {
                        Toast.makeText(CertificationActivity.this, "您的姓名不能为空！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(etCode.getText().toString().trim())) {
                        Toast.makeText(CertificationActivity.this, "身份证号不能为空！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!ZzUtils.isLegalName(etName.getText().toString().trim())) {
                        Toast.makeText(CertificationActivity.this, "请输入正确姓名！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!ZzUtils.isIDCard(etCode.getText().toString().trim())) {
                        Toast.makeText(CertificationActivity.this, "请输入身份证号！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Map<String, String> reqData = new HashMap<>();
                    reqData.put("Authorization",token);
                    reqData.put("version",getVersionCodes());
                    RealNameParamBean realNameParamBean=new RealNameParamBean();
                    realNameParamBean.setUserId(userId);
                    realNameParamBean.setUserMobile(mobile);
                    realNameParamBean.setRealName(etName.getText().toString().trim());
                    realNameParamBean.setRealCardNo(etCode.getText().toString().trim());
                    userInfoPresenter.getRealName(reqData,realNameParamBean);
                } else {
                    Toast.makeText(CertificationActivity.this, "请先同意用户使用协议书", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(userInfoPresenter!=null){
            userInfoPresenter.detachView();
        }
    }
}
