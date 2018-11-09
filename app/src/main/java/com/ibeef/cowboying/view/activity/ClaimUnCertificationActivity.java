package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.config.HawkKey;
import com.orhanobut.hawk.Hawk;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 立即认领未实名认证界面
 */
public class ClaimUnCertificationActivity extends BaseActivity {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.et_real_name_id)
    EditText etRealNameId;
    @Bind(R.id.et_real_identification_id)
    EditText etRealIdentificationId;
    @Bind(R.id.mobile_txt_id)
    TextView mobileTxtId;
    @Bind(R.id.et_right_code_id)
    EditText etRightCodeId;
    @Bind(R.id.agree_xieyi_chck)
    CheckBox agreeXieyiChck;
    @Bind(R.id.xieyi_txt_id)
    TextView xieyiTxtId;
    @Bind(R.id.is_use_id)
    TextView isUseId;
    @Bind(R.id.is_coupon_rv)
    RelativeLayout isCouponRv;
    @Bind(R.id.next_step_txt)
    TextView nextStepTxt;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_un_certification);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("认领");
        token= Hawk.get(HawkKey.TOKEN);
    }

    @OnClick({R.id.back_id, R.id.is_coupon_rv, R.id.next_step_txt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.is_coupon_rv:
                if(TextUtils.isEmpty(token)){
                    startActivity(LoginActivity.class);
                }else {
                    startActivity(UseCouponActivity.class);
                }

                break;
            case R.id.next_step_txt:
                if(TextUtils.isEmpty(token)){
                    startActivity(LoginActivity.class);
                }else {
                    startActivity(SureOderActivity.class);
                    finish();
                }
                break;
            default:
                break;
        }
    }
}
