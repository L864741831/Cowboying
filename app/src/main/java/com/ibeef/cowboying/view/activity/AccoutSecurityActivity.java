package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.SmscodeBase;
import com.ibeef.cowboying.bean.SmsCodeParamBean;
import com.ibeef.cowboying.bean.SmsCodeResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.presenter.SmsCodePresenter;
import com.ibeef.cowboying.utils.Md5Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;
import rxfamily.utils.PermissionsUtils;
import rxfamily.view.BaseActivity;

/**
 * 账号安全界面
 */
public class AccoutSecurityActivity extends BaseActivity{

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.phone_txt_id)
    TextView phoneTxtId;
    @Bind(R.id.weixin_stadus_id)
    TextView weixinStadusId;
    @Bind(R.id.zfb_stadus_id)
    TextView zfbStadusId;
    @Bind(R.id.cancle_txt_id)
    TextView cancleTxtId;
    @Bind(R.id.sure_txt_id)
    TextView sureTxtId;
    @Bind(R.id.set_login_pwd_rv)
    RelativeLayout setLoginPwdRv;
    @Bind(R.id.modify_mobile_rv)
    RelativeLayout modifyMobileRv;
    @Bind(R.id.show_bind_rv)
    RelativeLayout showBindRv;

    private String stadus;
    private String mobile="18703643373";
    // TODO: 2018/10/28
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accout_security);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        info.setText("账号安全");
    }

    @OnClick({R.id.back_id, R.id.phone_txt_id, R.id.weixin_stadus_id, R.id.zfb_stadus_id, R.id.set_login_pwd_rv, R.id.modify_mobile_rv,R.id.cancle_txt_id,R.id.sure_txt_id,R.id.show_bind_rv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.phone_txt_id:
                break;
            case R.id.weixin_stadus_id:
                //解绑弹框
                if(true){
                    showBindRv.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.zfb_stadus_id:
                break;
            case R.id.set_login_pwd_rv:
                sureBtn("5");
                break;
            case R.id.modify_mobile_rv:
                sureBtn("4");
                break;
            case R.id.cancle_txt_id:
                // TODO: 2018/10/24 取消微信绑定
                showBindRv.setVisibility(View.GONE);
                break;
            case R.id.sure_txt_id:
                // TODO: 2018/10/24 不取消微信绑定
                showBindRv.setVisibility(View.GONE);
                break;
            case R.id.show_bind_rv:
                showBindRv.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    private void sureBtn(final String stadus){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            rx.Observable<Boolean> grantObservable = PermissionsUtils.getPhoneCode(AccoutSecurityActivity.this);
            grantObservable.subscribe(new Action1<Boolean>() {
                @Override
                public void call(Boolean granted) {
                    if (granted) {
                        Intent intent=new Intent(AccoutSecurityActivity.this,IdentifyCodeActivity.class);
                        intent.putExtra("stadus",stadus);
                        intent.putExtra("mobile",mobile);
                        startActivity(intent);
                    } else {
                        PermissionsUtils.showPermissionDeniedDialog(AccoutSecurityActivity.this, false);
                    }
                }
            });
        }else {
            Intent intent=new Intent(AccoutSecurityActivity.this,IdentifyCodeActivity.class);
            intent.putExtra("stadus",stadus);
            intent.putExtra("mobile",mobile);
            startActivity(intent);
        }
    }
}
