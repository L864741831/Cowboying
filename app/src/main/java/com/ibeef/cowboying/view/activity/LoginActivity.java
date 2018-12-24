package com.ibeef.cowboying.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.InitThirdLoginBase;
import com.ibeef.cowboying.base.ThirdLoginBase;
import com.ibeef.cowboying.bean.AuthResult;
import com.ibeef.cowboying.bean.ThirdCountLoginParamBean;
import com.ibeef.cowboying.bean.ThirdCountLoginResultBean;
import com.ibeef.cowboying.bean.ThirdLoginResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.InitThirdLoginPresenter;
import com.ibeef.cowboying.presenter.ThirdAccountLoginPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.orhanobut.hawk.Hawk;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity implements ThirdLoginBase.IView ,InitThirdLoginBase.IView {

    @Bind(R.id.aplily_login)
    ImageView aplilyLogin;
    @Bind(R.id.weixin_login)
    ImageView weixinLogin;
    @Bind(R.id.login_btn)
    ImageView loginBtn;
    @Bind(R.id.registe_btn)
    ImageView registeBtn;
    private static final int SDK_AUTH_FLAG = 1000;
    private IWXAPI api;
    private ThirdAccountLoginPresenter thirdAccountLoginPresenter;
    private InitThirdLoginPresenter initThirdLoginPresenter;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        api = WXAPIFactory.createWXAPI(this, Constant.WeixinAppId,true);
        api.registerApp(Constant.WeixinAppId);
        thirdAccountLoginPresenter=new ThirdAccountLoginPresenter(this);
        initThirdLoginPresenter=new InitThirdLoginPresenter(this);
        token= Hawk.get(HawkKey.TOKEN);
    }

    @OnClick({R.id.aplily_login, R.id.weixin_login,R.id.registe_btn,R.id.login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aplily_login:
                initThirdLoginPresenter.getInitThirdLogin(getVersionCodes(),"4");
                break;
            case R.id.weixin_login:
                Constant.isBindWeiXin=false;
                weixinLogin();
                break;
            case R.id.registe_btn:
                Intent intent1=new Intent(LoginActivity.this,MobileLoginActivity.class);
                intent1.putExtra("stadus","2");
                startActivity(intent1);
                break;
            case R.id.login_btn:
                Intent intent=new Intent(LoginActivity.this,MobileLoginActivity.class);
                intent.putExtra("stadus","0");
                startActivity(intent);
                break;
            default:
                break;
        }
    }


    /**
     * 支付宝授权登录
     */
    private void aplilyLogin(final String authInfo){
        //异步处理
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {

                AuthTask authTask = new AuthTask(LoginActivity.this);
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
    private void weixinLogin(){
        if (!api.isWXAppInstalled()) {
            Toast.makeText(this, "安装微信后再授权登录" , Toast.LENGTH_SHORT).show();
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
                        ThirdCountLoginParamBean thirdCountLoginParamBean=new ThirdCountLoginParamBean();
                        thirdCountLoginParamBean.setAuthCode(authResult.getAuthCode());
                        thirdCountLoginParamBean.setType("4");
                        thirdCountLoginParamBean.setOpenId(authResult.getUserId());
//                        thirdCountLoginParamBean.setLoginZone("");
                        thirdAccountLoginPresenter.getThirdCountLogin(getVersionCodes(),thirdCountLoginParamBean);

                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(LoginActivity.this,
                                "授权失败", Toast.LENGTH_SHORT).show();

                    }

                    Log.e(Constant.TAG,"支付宝结果"+authResult+"????????"+resultStatus);
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getInitThirdLogin(ThirdLoginResultBean thirdLoginResultBean) {
        if("000000".equals(thirdLoginResultBean.getCode())){
            aplilyLogin(thirdLoginResultBean.getBizData());
        }else {
            showToast(thirdLoginResultBean.getMessage());
        }
    }

    @Override
    public void getThirdCountLogin(ThirdCountLoginResultBean thirdCountLoginResultBean) {
        if("000000".equals(thirdCountLoginResultBean.getCode())){
            if(SDCardUtil.isNullOrEmpty(thirdCountLoginResultBean.getBizData().getMobile())){
                Intent intent2=new Intent(LoginActivity.this,MobileLoginActivity.class);
                intent2.putExtra("stadus","3");
                intent2.putExtra("visitorId",thirdCountLoginResultBean.getBizData().getVisitorId());
                startActivity(intent2);
            }else {
                Hawk.put(HawkKey.TOKEN, thirdCountLoginResultBean.getBizData().getToken());
                removeALLActivity();
                Intent intent1=new Intent(LoginActivity.this,MainActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                finish();
            }
        }else {
            showToast(thirdCountLoginResultBean.getMessage());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(thirdAccountLoginPresenter!=null){
            thirdAccountLoginPresenter.detachView();
        }
        if(initThirdLoginPresenter!=null){
            initThirdLoginPresenter.detachView();
        }
    }

    @Override
    public void onBackPressed() {
        if(TextUtils.isEmpty(token)){
            finish();
        }else {
            removeALLActivity();
            Intent intent1=new Intent(LoginActivity.this,MainActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent1);
            finish();
        }
    }


}
