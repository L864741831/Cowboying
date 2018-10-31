package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.AccountSecurityBase;
import com.ibeef.cowboying.base.SmscodeBase;
import com.ibeef.cowboying.bean.BindThirdCountParamBean;
import com.ibeef.cowboying.bean.BindThirdCountResultBean;
import com.ibeef.cowboying.bean.SafeInfoResultBean;
import com.ibeef.cowboying.bean.SmsCodeParamBean;
import com.ibeef.cowboying.bean.SmsCodeResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.AccountSecurityPresenter;
import com.ibeef.cowboying.presenter.SmsCodePresenter;
import com.ibeef.cowboying.utils.Md5Util;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.orhanobut.hawk.Hawk;

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
public class AccoutSecurityActivity extends BaseActivity implements AccountSecurityBase.IView {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.name_txt_id)
    TextView nameTxtId;
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
    private String token,userId;
    // TODO: 2018/10/28
    private boolean isSetPwd=false,isMobie=false;
    private AccountSecurityPresenter accountSecurityPresenter;
    private SafeInfoResultBean safeInfoResultBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accout_security);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        info.setText("账号安全");
        token= Hawk.get(HawkKey.TOKEN);
        userId= Hawk.get(HawkKey.userId);
        accountSecurityPresenter=new AccountSecurityPresenter(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        accountSecurityPresenter.getSafeInfo(reqData);
    }

    @OnClick({R.id.back_id, R.id.phone_txt_id, R.id.weixin_stadus_id, R.id.zfb_stadus_id, R.id.set_login_pwd_rv, R.id.modify_mobile_rv,R.id.cancle_txt_id,R.id.sure_txt_id,R.id.show_bind_rv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.phone_txt_id:
                if(!isMobie){
                    showToast("暂无手机号，不可以换绑~");
                    return;
                }
                sureBtn("8");
                break;
            case R.id.weixin_stadus_id:
                //解绑弹框
                if(SDCardUtil.isNullOrEmpty(safeInfoResultBean.getBizData())){
                    if(!SDCardUtil.isNullOrEmpty(safeInfoResultBean.getBizData().getWxName())){
                        nameTxtId.setText("确认解除微信账号的绑定吗？");
                        showBindRv.setVisibility(View.VISIBLE);
                    }else {
                        bindThird("3");
                    }
                }
                break;
            case R.id.zfb_stadus_id:
                //解绑弹框
                if(SDCardUtil.isNullOrEmpty(safeInfoResultBean.getBizData())){
                    if(!SDCardUtil.isNullOrEmpty(safeInfoResultBean.getBizData().getZfbName())) {
                        nameTxtId.setText("确认解除支付宝账号的绑定吗？");
                        showBindRv.setVisibility(View.VISIBLE);
                    } else {
                        bindThird("4");
                    }
                }
                break;
            case R.id.set_login_pwd_rv:
                if(!isSetPwd){
                    sureBtn("5");
                }
                break;
            case R.id.modify_mobile_rv:
                sureBtn("4");
                break;
            case R.id.cancle_txt_id:
                showBindRv.setVisibility(View.GONE);
                break;
            case R.id.sure_txt_id:
                // 解除绑定
                showBindRv.setVisibility(View.GONE);
                Map<String, String> reqData = new HashMap<>();
                reqData.put("Authorization",token);
                reqData.put("version",getVersionCodes());
                accountSecurityPresenter.getUnBindThidCount(reqData,"");
                break;
            case R.id.show_bind_rv:
                showBindRv.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    private void bindThird(String type){
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        BindThirdCountParamBean bindThirdCountParamBean=new BindThirdCountParamBean();
        bindThirdCountParamBean.setUserId(userId);
        bindThirdCountParamBean.setAccessToken("");
        bindThirdCountParamBean.setOpenId("");
        bindThirdCountParamBean.setType(type);
        accountSecurityPresenter.getBindThidCount(reqData,bindThirdCountParamBean);
    }
    private void sureBtn(final String stadus){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            rx.Observable<Boolean> grantObservable = PermissionsUtils.getPhoneCode(AccoutSecurityActivity.this);
            grantObservable.subscribe(new Action1<Boolean>() {
                @Override
                public void call(Boolean granted) {
                    if (granted) {
                        Intent intent=new Intent(AccoutSecurityActivity.this,IdentifyCodeActivity.class);
                        if("8".equals(stadus)){
                            intent.putExtra("oldmobile",safeInfoResultBean.getBizData().getMobile());
                        }else {
                            intent.putExtra("mobile",safeInfoResultBean.getBizData().getMobile());
                        }
                        intent.putExtra("stadus",stadus);

                        startActivity(intent);
                    } else {
                        PermissionsUtils.showPermissionDeniedDialog(AccoutSecurityActivity.this, false);
                    }
                }
            });
        }else {
            Intent intent=new Intent(AccoutSecurityActivity.this,IdentifyCodeActivity.class);
            intent.putExtra("stadus",stadus);
            if("8".equals(stadus)){
                intent.putExtra("oldmobile",safeInfoResultBean.getBizData().getMobile());
            }else {
                intent.putExtra("mobile",safeInfoResultBean.getBizData().getMobile());
            }
            startActivity(intent);
        }
    }

    @Override
    public void showMsg(String msg) {
        if(!TextUtils.isEmpty(msg)){
            if(msg.contains("401")){
                Hawk.put(HawkKey.TOKEN, "");
                Toast.makeText(this,"Authorization失效，请重新登录",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
        }
    }

    @Override
    public void getSafeInfo(SafeInfoResultBean safeInfoResultBean) {
        if("000000".equals(safeInfoResultBean.getCode())){
            this.safeInfoResultBean=safeInfoResultBean;
            if("0".equals(safeInfoResultBean.getBizData().getIsPassWord())){
                isSetPwd=false;
            }else {
                isSetPwd=true;
            }
            if(SDCardUtil.isNullOrEmpty(safeInfoResultBean.getBizData().getMobile())){
                phoneTxtId.setText("暂无手机号");
                isMobie=false;
            }else {
                phoneTxtId.setText(safeInfoResultBean.getBizData().getMobile());
                isMobie=true;
            }


            if(SDCardUtil.isNullOrEmpty(safeInfoResultBean.getBizData().getWxName())){
                weixinStadusId.setText("");
                Drawable drawable = getResources().getDrawable(R.mipmap.binds);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                weixinStadusId.setCompoundDrawables(drawable, null, null, null);
                weixinStadusId.setCompoundDrawablePadding(10);
            }else {
                weixinStadusId.setText("已绑定");
                Drawable drawable = getResources().getDrawable(R.mipmap.unbinds);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                weixinStadusId.setCompoundDrawables(drawable, null, null, null);
                weixinStadusId.setCompoundDrawablePadding(10);
            }
            if(SDCardUtil.isNullOrEmpty(safeInfoResultBean.getBizData().getZfbName())){
                zfbStadusId.setText("");
                Drawable drawable = getResources().getDrawable(R.mipmap.binds);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                weixinStadusId.setCompoundDrawables(drawable, null, null, null);
                weixinStadusId.setCompoundDrawablePadding(10);
            }else {
                zfbStadusId.setText("已绑定");
                Drawable drawable = getResources().getDrawable(R.mipmap.unbinds);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                weixinStadusId.setCompoundDrawables(drawable, null, null, null);
                weixinStadusId.setCompoundDrawablePadding(10);
            }

        }else {
            showToast(safeInfoResultBean.getMessage());
        }

    }

    @Override
    public void getBindThidCount(BindThirdCountResultBean bindThirdCountResultBean) {
        if("000000".equals(bindThirdCountResultBean.getCode())){
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization",token);
            reqData.put("version",getVersionCodes());
            accountSecurityPresenter.getSafeInfo(reqData);
            showToast("绑定第三方账号成功~");
        }else {
            showToast(bindThirdCountResultBean.getMessage());
        }
    }

    @Override
    public void getUnBindThidCount(BindThirdCountResultBean bindThirdCountResultBean) {
        if("000000".equals(bindThirdCountResultBean.getCode())){
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization",token);
            reqData.put("version",getVersionCodes());
            accountSecurityPresenter.getSafeInfo(reqData);
            showToast("解绑成功~");
        }else {
            showToast(bindThirdCountResultBean.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        if(accountSecurityPresenter!=null){
            accountSecurityPresenter.detachView();
        }
        super.onDestroy();
    }
}
