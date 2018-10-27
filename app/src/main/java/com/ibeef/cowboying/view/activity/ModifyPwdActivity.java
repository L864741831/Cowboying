package com.ibeef.cowboying.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.ibeef.cowboying.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;
import rxfamily.utils.PermissionsUtils;
import rxfamily.view.BaseActivity;

/**
 * 修改密码界面
 */
public class ModifyPwdActivity extends BaseActivity {

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
    @Bind(R.id.et_old_pwd)
    EditText etOldPwd;
    @Bind(R.id.et_new_pwd)
    EditText etNewPwd;
    @Bind(R.id.btn_sure)
    TextView btnSure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pwd);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        info.setText("修改密码");
    }

    @OnClick({R.id.back_id, R.id.btn_sure,R.id.btn_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.btn_sure:
                finish();
                break;
            case R.id.btn_code:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    rx.Observable<Boolean> grantObservable = PermissionsUtils.getPhoneCode(ModifyPwdActivity.this);
                    grantObservable.subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean granted) {
                            if (granted) {
                                // TODO: 2018/10/23 网络请求
                            } else {
                                PermissionsUtils.showPermissionDeniedDialog(ModifyPwdActivity.this, false);
                            }
                        }
                    });
                }else {
                    // TODO: 2018/10/23 网络请求
                }
                break;
            default:
                break;
        }
    }
}
