package com.ibeef.cowboying.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.view.activity.PayResultActivity;
import com.ibeef.cowboying.view.activity.StorePayResultActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * 微信支付结果页面
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        //支付结果回调 resp.errCode 0成功 -1 错误 -2 用户取消
        //baseresp.getType 1:第三方授权， 2:分享,5:支付
        Log.e(Constant.TAG, "onPayFinish, errCode = " + resp.errCode);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            // resp.errCode == -1 原因：支付错误,可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等
            // resp.errCode == -2 原因 用户取消,无需处理。发生场景：用户不支付了，点击取消，返回APP
            Log.e("--main--", resp.errCode + "??????????????????啥情况");
            if (resp.errCode == 0)
            // 支付成功
            {
                /**
                 * 0是养牛
                 *  1是商城
                 *  2是拼牛
                 */
                Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
                if(0== Constant.PAY_RESULT_TYPE){
                    Intent intent=new Intent(WXPayEntryActivity.this,PayResultActivity.class);
                    intent.putExtra("orderId",Constant.orderId);
                    startActivity(intent);
                }else  if(1== Constant.PAY_RESULT_TYPE){
                    Intent intent=new Intent(WXPayEntryActivity.this,StorePayResultActivity.class);
                    intent.putExtra("orderId",Constant.orderId);
                    startActivity(intent);
                }else  if(2== Constant.PAY_RESULT_TYPE){

                }

                finish();
            } else {
                Toast.makeText(this, "支付失败！", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }


}