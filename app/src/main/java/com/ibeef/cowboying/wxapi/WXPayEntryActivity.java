package com.ibeef.cowboying.wxapi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.config.Constant;
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
            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
                    break;
                case BaseResp.ErrCode.ERR_COMM:
                    Toast.makeText(this, "支付错误", Toast.LENGTH_SHORT).show();
                    //发送取消
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    Toast.makeText(this, "用户取消", Toast.LENGTH_SHORT).show();
                    //发送取消
                    break;
                default:
                    Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
                    break;
            }
    }


}