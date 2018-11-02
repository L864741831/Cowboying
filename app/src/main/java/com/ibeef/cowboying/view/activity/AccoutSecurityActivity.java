package com.ibeef.cowboying.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.AccountSecurityBase;
import com.ibeef.cowboying.base.InitThirdLoginBase;
import com.ibeef.cowboying.base.SmscodeBase;
import com.ibeef.cowboying.bean.AuthResult;
import com.ibeef.cowboying.bean.BindThirdCountParamBean;
import com.ibeef.cowboying.bean.BindThirdCountResultBean;
import com.ibeef.cowboying.bean.SafeInfoResultBean;
import com.ibeef.cowboying.bean.SmsCodeParamBean;
import com.ibeef.cowboying.bean.SmsCodeResultBean;
import com.ibeef.cowboying.bean.ThirdCountLoginParamBean;
import com.ibeef.cowboying.bean.ThirdLoginResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.AccountSecurityPresenter;
import com.ibeef.cowboying.presenter.InitThirdLoginPresenter;
import com.ibeef.cowboying.presenter.SmsCodePresenter;
import com.ibeef.cowboying.utils.Md5Util;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.orhanobut.hawk.Hawk;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

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
public class AccoutSecurityActivity extends BaseActivity implements AccountSecurityBase.IView ,InitThirdLoginBase.IView {

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
    @Bind(R.id.modify_pwd_id)
    TextView modifyPwdId;
    @Bind(R.id.sure_txt_id)
    TextView sureTxtId;
    @Bind(R.id.set_login_pwd_rv)
    RelativeLayout setLoginPwdRv;
    @Bind(R.id.modify_mobile_rv)
    RelativeLayout modifyMobileRv;
    @Bind(R.id.show_bind_rv)
    RelativeLayout showBindRv;

    private String stadus;
    private String token;
    private static final int SDK_AUTH_FLAG = 1000;
    private boolean isSetPwd=false,isMobie=false;
    private AccountSecurityPresenter accountSecurityPresenter;
    private SafeInfoResultBean safeInfoResultBean;
    private InitThirdLoginPresenter initThirdLoginPresenter;
    private IWXAPI api;
    private String type;
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
        api = WXAPIFactory.createWXAPI(this,  Constant.WeixinAppId,true);
        api.registerApp(Constant.WeixinAppId);
        accountSecurityPresenter=new AccountSecurityPresenter(this);
        initThirdLoginPresenter=new InitThirdLoginPresenter(this);
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
                    Intent intent=new Intent(AccoutSecurityActivity.this,MobileLoginActivity.class);
                    intent.putExtra("stadus","7");
                    startActivity(intent);
                }else {
                    sureBtn("8");
                }
                break;
            case R.id.weixin_stadus_id:
                //解绑弹框
                type="3";
                if(!SDCardUtil.isNullOrEmpty(safeInfoResultBean.getBizData())){
                    if(!SDCardUtil.isNullOrEmpty(safeInfoResultBean.getBizData().getWxId())){
                        nameTxtId.setText("确认解除微信账号的绑定吗？");
                        showBindRv.setVisibility(View.VISIBLE);
                    }else {
                        Constant.isBindWeiXin=true;
                        weixinLogin();
                    }
                }
                break;
            case R.id.zfb_stadus_id:
                //解绑弹框
                type="4";
                if(!SDCardUtil.isNullOrEmpty(safeInfoResultBean.getBizData())){
                    if(!SDCardUtil.isNullOrEmpty(safeInfoResultBean.getBizData().getZfbId())) {
                        nameTxtId.setText("确认解除支付宝账号的绑定吗？");
                        showBindRv.setVisibility(View.VISIBLE);
                    } else {
                        initThirdLoginPresenter.getInitThirdLogin(getVersionCodes(),"4");
                    }
                }
                break;
            case R.id.set_login_pwd_rv:
                if(!isSetPwd){
                    sureBtn("5");
                }else {
                    Intent intent=new Intent(AccoutSecurityActivity.this,MobileLoginActivity.class);
                    intent.putExtra("stadus","11");
                    startActivity(intent);
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
                if("4".equals(type)){
                    accountSecurityPresenter.getUnBindThidCount(reqData,safeInfoResultBean.getBizData().getZfbId()+"");
                }else if("3".equals(type)){
                    accountSecurityPresenter.getUnBindThidCount(reqData,safeInfoResultBean.getBizData().getWxId()+"");
                }
                break;
            case R.id.show_bind_rv:
                showBindRv.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    private void sureBtn(final String stadus) {
        if (SDCardUtil.isNullOrEmpty(safeInfoResultBean)) {
            return;
        }
        if (SDCardUtil.isNullOrEmpty(safeInfoResultBean.getBizData())) {
            return;
        }
        if (SDCardUtil.isNullOrEmpty(safeInfoResultBean.getBizData().getMobile())) {
            showToast("暂无手机号，请先设置手机号");
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            rx.Observable<Boolean> grantObservable = PermissionsUtils.getPhoneCode(AccoutSecurityActivity.this);
            grantObservable.subscribe(new Action1<Boolean>() {
                @Override
                public void call(Boolean granted) {
                    if (granted) {
                        Intent intent = new Intent(AccoutSecurityActivity.this, IdentifyCodeActivity.class);
                        if ("8".equals(stadus) || "4".equals(stadus)) {
                            intent.putExtra("oldmobile", safeInfoResultBean.getBizData().getMobile());
                        } else {
                            intent.putExtra("mobile", safeInfoResultBean.getBizData().getMobile());
                        }
                        intent.putExtra("stadus", stadus);

                        startActivity(intent);
                    } else {
                        PermissionsUtils.showPermissionDeniedDialog(AccoutSecurityActivity.this, false);
                    }
                }
            });
        } else {
            Intent intent = new Intent(AccoutSecurityActivity.this, IdentifyCodeActivity.class);
            intent.putExtra("stadus", stadus);
            if ("8".equals(stadus)) {
                intent.putExtra("oldmobile", safeInfoResultBean.getBizData().getMobile());
            } else {
                intent.putExtra("mobile", safeInfoResultBean.getBizData().getMobile());
            }
            startActivity(intent);
        }
    }

    @Override
    public void showMsg(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            if (msg.contains("401")) {
                Hawk.put(HawkKey.TOKEN, "");
                Toast.makeText(this, "Authorization失效，请重新登录", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
        }
    }

    @Override
    public void getInitThirdLogin(ThirdLoginResultBean thirdLoginResultBean) {
        if ("000000".equals(thirdLoginResultBean.getCode())) {
            aplilyLogin(thirdLoginResultBean.getBizData());
        } else {
            showToast(thirdLoginResultBean.getMessage());
        }
    }

    /**
     * 支付宝授权登录
     */
    private void aplilyLogin(final String authInfo) {
        //异步处理
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {

                AuthTask authTask = new AuthTask(AccoutSecurityActivity.this);
                // 调用授权接口，获取授权结果ta
                Map<String, String> result = authTask.authV2(authInfo, true);
                Message msg = new Message();
                msg.what = SDK_AUTH_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }


    /**
     * 微信授权登录
     */
    private void weixinLogin() {
        if (!api.isWXAppInstalled()) {
            Toast.makeText(this, "安装微信后再授权登录", Toast.LENGTH_SHORT).show();
            return;
        }
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        api.sendReq(req);
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SDK_AUTH_FLAG:
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        // auth_code传入后台，后台获取 auth_token,其他信息 授权成功绑定手机号
                        Map<String, String> reqData = new HashMap<>();
                        reqData.put("version", getVersionCodes());
                        reqData.put("Authorization", token);
                        BindThirdCountParamBean bindThirdCountParamBean = new BindThirdCountParamBean();
                        bindThirdCountParamBean.setAuthCode(authResult.getAuthCode());
                        bindThirdCountParamBean.setOpenId(authResult.getUserId());
                        bindThirdCountParamBean.setType("4");
                        accountSecurityPresenter.getBindThidCount(reqData, bindThirdCountParamBean);

                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(AccoutSecurityActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    Log.e(Constant.TAG, "支付宝结果" + authResult + "????????" + resultStatus);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void getSafeInfo(SafeInfoResultBean safeInfoResultBean) {
        if ("000000".equals(safeInfoResultBean.getCode())) {
            this.safeInfoResultBean = safeInfoResultBean;
            if ("0".equals(safeInfoResultBean.getBizData().getIsPassWord())) {
                isSetPwd = false;
                modifyPwdId.setText("修改登录密码");
            } else {
                isSetPwd = true;
                modifyPwdId.setText("设置登录密码");
            }
            if (SDCardUtil.isNullOrEmpty(safeInfoResultBean.getBizData().getMobile())) {
                phoneTxtId.setText("");
                isMobie = false;
                Drawable drawableRight = getResources().getDrawable(
                        R.mipmap.binds);
                phoneTxtId.setCompoundDrawablesWithIntrinsicBounds(null,
                        null, drawableRight, null);
                phoneTxtId.setCompoundDrawablePadding(4);
            } else {
                phoneTxtId.setText(safeInfoResultBean.getBizData().getMobile());
                isMobie = true;
                Drawable drawableRight = getResources().getDrawable(
                        R.mipmap.replacebinds);
                phoneTxtId.setCompoundDrawablesWithIntrinsicBounds(null,
                        null, drawableRight, null);
                phoneTxtId.setCompoundDrawablePadding(4);
            }

            if (SDCardUtil.isNullOrEmpty(safeInfoResultBean.getBizData().getWxId())) {
                weixinStadusId.setText("");
                Drawable drawableRight = getResources().getDrawable(
                        R.mipmap.binds);
                weixinStadusId.setCompoundDrawablesWithIntrinsicBounds(null,
                        null, drawableRight, null);
                weixinStadusId.setCompoundDrawablePadding(4);
            } else {
                weixinStadusId.setText("已绑定");
                Drawable drawableRight = getResources().getDrawable(
                        R.mipmap.unbinds);
                weixinStadusId.setCompoundDrawablesWithIntrinsicBounds(null,
                        null, drawableRight, null);
                weixinStadusId.setCompoundDrawablePadding(4);
            }
            if (SDCardUtil.isNullOrEmpty(safeInfoResultBean.getBizData().getZfbId())) {
                zfbStadusId.setText("");
                Drawable drawableRight = getResources().getDrawable(
                        R.mipmap.binds);
                zfbStadusId.setCompoundDrawablesWithIntrinsicBounds(null,
                        null, drawableRight, null);
                zfbStadusId.setCompoundDrawablePadding(4);
            } else {
                zfbStadusId.setText("已绑定");
                Drawable drawableRight = getResources().getDrawable(
                        R.mipmap.unbinds);
                zfbStadusId.setCompoundDrawablesWithIntrinsicBounds(null,
                        null, drawableRight, null);
                zfbStadusId.setCompoundDrawablePadding(4);
            }

        } else {
            showToast(safeInfoResultBean.getMessage());
        }

    }

    @Override
    public void getBindThidCount(BindThirdCountResultBean bindThirdCountResultBean) {
        if ("000000".equals(bindThirdCountResultBean.getCode())) {
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization", token);
            reqData.put("version", getVersionCodes());
            accountSecurityPresenter.getSafeInfo(reqData);
            showToast("绑定第三方账号成功~");
        } else {
            showToast(bindThirdCountResultBean.getMessage());
        }
    }

    @Override
    public void getUnBindThidCount(BindThirdCountResultBean bindThirdCountResultBean) {
        if ("000000".equals(bindThirdCountResultBean.getCode())) {
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization", token);
            reqData.put("version", getVersionCodes());
            accountSecurityPresenter.getSafeInfo(reqData);
            showToast("解绑成功~");
        } else {
            showToast(bindThirdCountResultBean.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        if (accountSecurityPresenter != null) {
            accountSecurityPresenter.detachView();
        }
        super.onDestroy();
    }
}
