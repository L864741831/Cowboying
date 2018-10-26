package com.ranhan.cowboying.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.ranhan.cowboying.base.WeiXinAuthBase;
import com.ranhan.cowboying.bean.WeixinAuthFirstBean;
import com.ranhan.cowboying.bean.WeixinAuthSecondeBean;
import com.ranhan.cowboying.presenter.WeixinAuthPresenter;
import com.ranhan.cowboying.view.activity.LoginActivity;
import com.ranhan.cowboying.view.activity.MobileLoginActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler, WeiXinAuthBase.IView {
    private IWXAPI iwxapi;
    private WeixinAuthPresenter weixinAuthPresenter=new WeixinAuthPresenter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //接收到分享以及登录的intent传递handleIntent方法，处理结果
        iwxapi = WXAPIFactory.createWXAPI(this, "wx0678b96a189375f3", false);
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
                String code = newResp.code;
                Log.e("WXTest", "onResp code = " + code);
                weixinAuthPresenter.getWeixinAuthFirst("wx0678b96a189375f3","4850c2390a02f27f8ebc444ec42568b1",code,"authorization_code");
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "发送取消";
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "发送被拒绝";
                break;
            case BaseResp.ErrCode.ERR_UNSUPPORT:
                result = "不支持错误";
                break;
            default:
                result = "发送返回";
                break;
        }
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        finish();


    }

    @Override
    public void getWeixinAuthFirst(WeixinAuthFirstBean weixinAuthFirstBean) {
        //根据auth_token获取微信授权userinfo
        weixinAuthPresenter.getWeixinAuthSeconde(weixinAuthFirstBean.getAccess_token(),weixinAuthFirstBean.getOpenid());
    }

    @Override
    public void getWeixinAuthSeconde(WeixinAuthSecondeBean weixinAuthSecondeBean) {
        Intent intent1=new Intent(WXEntryActivity.this,MobileLoginActivity.class);
        intent1.putExtra("stadus","3");
        startActivity(intent1);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg , Toast.LENGTH_SHORT).show();
    }
}