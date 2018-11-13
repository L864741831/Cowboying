package com.ibeef.cowboying.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.AccountSecurityBase;
import com.ibeef.cowboying.base.ThirdLoginBase;
import com.ibeef.cowboying.base.WeiXinAuthBase;
import com.ibeef.cowboying.bean.BindThirdCountParamBean;
import com.ibeef.cowboying.bean.BindThirdCountResultBean;
import com.ibeef.cowboying.bean.SafeInfoResultBean;
import com.ibeef.cowboying.bean.ThirdCountLoginParamBean;
import com.ibeef.cowboying.bean.ThirdCountLoginResultBean;
import com.ibeef.cowboying.bean.WeixinAuthFirstBean;
import com.ibeef.cowboying.bean.WeixinAuthSecondeBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.AccountSecurityPresenter;
import com.ibeef.cowboying.presenter.ThirdAccountLoginPresenter;
import com.ibeef.cowboying.presenter.WeixinAuthPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.ibeef.cowboying.view.activity.LoginActivity;
import com.ibeef.cowboying.view.activity.MainActivity;
import com.ibeef.cowboying.view.activity.MobileLoginActivity;
import com.orhanobut.hawk.Hawk;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

import rxfamily.view.BaseActivity;


public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler, WeiXinAuthBase.IView ,AccountSecurityBase.IView,ThirdLoginBase.IView  {
    private IWXAPI iwxapi;
//    private WeixinAuthPresenter weixinAuthPresenter=new WeixinAuthPresenter(this);
    private AccountSecurityPresenter  accountSecurityPresenter;
    private String token;
    private ThirdAccountLoginPresenter  thirdAccountLoginPresenter;
    private String  code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //接收到分享以及登录的intent传递handleIntent方法，处理结果
        iwxapi = WXAPIFactory.createWXAPI(this, Constant.WeixinAppId, false);
        accountSecurityPresenter=new AccountSecurityPresenter(this);
        thirdAccountLoginPresenter=new ThirdAccountLoginPresenter(this);
        token= Hawk.get(HawkKey.TOKEN);
        iwxapi.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
    }
    /**
     * 请求回调结果处理
     * @param baseResp
     */
    @Override
    public void onResp(BaseResp baseResp) {
        String  result = "";
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = "授权成功";
                SendAuth.Resp newResp = (SendAuth.Resp) baseResp;
                //获取微信传回的code
                 code = newResp.code;
                Log.e("WXTest", "onResp code = " + code);
//                weixinAuthPresenter.getWeixinAuthFirst(Constant.WeixinAppId,Constant.appappSecret,code,"authorization_code");
                if(Constant.isBindWeiXin){
                    //账号安全 绑定微信
                    Map<String, String> reqData = new HashMap<>();
                    reqData.put("version",getVersionCodes());
                    reqData.put("Authorization",token);
                    BindThirdCountParamBean bindThirdCountParamBean=new BindThirdCountParamBean();
                    bindThirdCountParamBean.setAuthCode(code);
                    bindThirdCountParamBean.setType("3");
                    accountSecurityPresenter.getBindThidCount(reqData,bindThirdCountParamBean);
                }else {//登录里微信登录
                    ThirdCountLoginParamBean thirdCountLoginParamBean=new ThirdCountLoginParamBean();
                    thirdCountLoginParamBean.setAuthCode(code);
                    thirdCountLoginParamBean.setType("3");
                    thirdAccountLoginPresenter.getThirdCountLogin(getVersionCodes(),thirdCountLoginParamBean);
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "授权失败";
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "授权失败";
                break;
            case BaseResp.ErrCode.ERR_UNSUPPORT:
                result = "授权失败";
                break;
            default:
                result = "授权失败";
                break;
        }
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getWeixinAuthFirst(WeixinAuthFirstBean weixinAuthFirstBean) {
        //根据auth_token获取微信授权userinfo
//        weixinAuthPresenter.getWeixinAuthSeconde(weixinAuthFirstBean.getAccess_token(),weixinAuthFirstBean.getOpenid());

    }

    @Override
    public void getWeixinAuthSeconde(WeixinAuthSecondeBean weixinAuthSecondeBean) {
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getThirdCountLogin(ThirdCountLoginResultBean thirdCountLoginResultBean) {
        if("000000".equals(thirdCountLoginResultBean.getCode())){
            Hawk.put(HawkKey.TOKEN, thirdCountLoginResultBean.getBizData().getToken());

            if(SDCardUtil.isNullOrEmpty(thirdCountLoginResultBean.getBizData().getMobile())){
                Intent intent2=new Intent(WXEntryActivity.this,MobileLoginActivity.class);
                intent2.putExtra("stadus","3");
                startActivity(intent2);
                finish();
            }else {
                Intent intent1=new Intent(WXEntryActivity.this,MainActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                finish();
            }

        }else {
            Toast.makeText(this, thirdCountLoginResultBean.getMessage() , Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void getSafeInfo(SafeInfoResultBean safeInfoResultBean) {

    }

    @Override
    public void getBindThidCount(BindThirdCountResultBean bindThirdCountResultBean) {
        if("000000".equals(bindThirdCountResultBean.getCode())){
            Toast.makeText(this,"绑定第三方账号成功~",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,bindThirdCountResultBean.getMessage(),Toast.LENGTH_LONG).show();
        }

        finish();
    }

    @Override
    public void getUnBindThidCount(BindThirdCountResultBean bindThirdCountResultBean) {

    }

    @Override
    protected void onDestroy() {
        if(accountSecurityPresenter!=null){
            accountSecurityPresenter.detachView();
        }
        if(thirdAccountLoginPresenter!=null){
            thirdAccountLoginPresenter.detachView();
        }
        super.onDestroy();
    }
}