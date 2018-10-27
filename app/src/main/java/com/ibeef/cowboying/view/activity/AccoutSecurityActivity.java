package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibeef.cowboying.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 账号安全界面
 */
public class AccoutSecurityActivity extends BaseActivity {

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
                Intent intent=new Intent(AccoutSecurityActivity.this,IdentifyCodeActivity.class);
                intent.putExtra("stadus","4");
                intent.putExtra("mobile","4");
                startActivity(intent);
                break;
            case R.id.modify_mobile_rv:
                Intent intent1=new Intent(AccoutSecurityActivity.this,IdentifyCodeActivity.class);
                intent1.putExtra("stadus","5");
                intent1.putExtra("mobile","4");
                startActivity(intent1);
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
}
