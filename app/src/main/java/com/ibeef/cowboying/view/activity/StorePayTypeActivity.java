package com.ibeef.cowboying.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.MyOrderListBase;
import com.ibeef.cowboying.base.OrderInitBase;
import com.ibeef.cowboying.base.PayPwdBase;
import com.ibeef.cowboying.bean.CreatOderResultBean;
import com.ibeef.cowboying.bean.MyAfterSaleDetailBean;
import com.ibeef.cowboying.bean.MyAfterSaleListBean;
import com.ibeef.cowboying.bean.MyOrderListBean;
import com.ibeef.cowboying.bean.MyOrderListCancelBean;
import com.ibeef.cowboying.bean.MyOrderListDetailBean;
import com.ibeef.cowboying.bean.PayInitParamBean;
import com.ibeef.cowboying.bean.PayInitResultBean;
import com.ibeef.cowboying.bean.PayPwdResultBean;
import com.ibeef.cowboying.bean.PayResult;
import com.ibeef.cowboying.bean.ShowDeleveryResultBean;
import com.ibeef.cowboying.bean.WeinXinBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.IsPayPwdPresenter;
import com.ibeef.cowboying.presenter.MyOrderListPresenter;
import com.ibeef.cowboying.presenter.OrderInitPresenter;
import com.ibeef.cowboying.utils.DateUtils;
import com.ibeef.cowboying.utils.VerificationCodeInput;
import com.ibeef.cowboying.view.customview.CountDownView;
import com.orhanobut.hawk.Hawk;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.unionpay.UPPayAssistEx;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import android.os.Handler.Callback;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 商城支付方式
 */
public class StorePayTypeActivity extends BaseActivity implements OrderInitBase.IView, MyOrderListBase.IView,PayPwdBase.IView, Callback, Runnable {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.zfb_check)
    ImageView zfbCheck;
    @Bind(R.id.weixin_check)
    ImageView weixinCheck;
    @Bind(R.id.wallet_check)
    ImageView walletCheck;
    @Bind(R.id.time_show_id)
    CountDownView timeShowId;
    @Bind(R.id.cancle_id)
    TextView cancleId;
    @Bind(R.id.sure_pay_id)
    TextView surePayId;
    @Bind(R.id.account_pay_show_rv)
    RelativeLayout accountPayShowRv;
    @Bind(R.id.pay_back_id)
    ImageView payBackId;
    @Bind(R.id.verificationCodeInput_id)
    VerificationCodeInput verificationCodeInputId;
    @Bind(R.id.foret_pwd_id)
    TextView foretPwdId;
    @Bind(R.id.lvs_id)
    LinearLayout lvsId;
    @Bind(R.id.lvs_back_id)
    LinearLayout lvsBackId;
    @Bind(R.id.refuce_id)
    TextView refuceId;
    @Bind(R.id.cancle_order_id)
    TextView cancle_order_id;
    @Bind(R.id.my_order_id)
    TextView myOrderId;
    @Bind(R.id.dialog_close_id)
    TextView dialogCloseId;
    @Bind(R.id.rv1_id)
    RelativeLayout rv1_id;
    @Bind(R.id.rv2_id)
    RelativeLayout rv2_id;
    @Bind(R.id.rv3_id)
    RelativeLayout rv3_id;
    @Bind(R.id.rv4_id)
    RelativeLayout rv4_id;
    @Bind(R.id.yl_check)
    ImageView yl_check;
    private int type=4;
    private boolean isComplet=true;
    private String token, contents;
    private IWXAPI api;
    private static final int SDK_PAY_FLAG = 1;
    private int orderId;
    private OrderInitPresenter orderInitPresenter;
    private MyOrderListPresenter myOrderListPresenter;
    private  Map<String, String> reqData;
    private IsPayPwdPresenter isPayPwdPresenter;
    private boolean isPwd;
    private Handler mHandlers = null;
    /*****************************************************************
     * mMode参数解释： "00" - 启动银联正式环境 "01" - 连接银联测试环境
     *****************************************************************/
    private final String mMode = "01";
    private static final String TN_URL_01 = "http://101.231.204.84:8091/sim/getacptn";
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    // 同步返回需要验证的信息
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(StorePayTypeActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(StorePayTypeActivity.this,StorePayResultActivity.class);
                        intent.putExtra("orderId",orderId);
                        startActivity(intent);
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(StorePayTypeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_pay_type);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initDialog();
        mHandlers = new Handler(this);
        orderId=getIntent().getIntExtra("orderId",0);
        long createTime=getIntent().getLongExtra("createTime",0);
        Log.i("htht", "createTime::::: "+createTime);
        if (createTime==0){
            info.setText("支付方式");
            long time= 1800000;
            //两时间差,精确到毫秒
            long hour = time  / 3600000;
            //以小时为单位取整
            long min = time  % 3600000 / 60000;
            //以分钟为单位取整
            long seconds = time  % 3600000 % 60000 / 1000;
            //以秒为单位取整
            Log.e(Constant.TAG,time+"????????????");
            timeShowId.initTime(hour,min,seconds);
            timeShowId.start();
        }else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String times = sdf.format(new Date());
            long time = DateUtils.getReduce(times, DateUtils.formatDate(createTime+1800000, DateUtils.TYPE_01));
            //两时间差,精确到毫秒
            long hour = time / 3600000;
            //以小时为单位取整
            long min = time % 3600000 / 60000;
            //以分钟为单位取整
            long seconds = time % 3600000 % 60000 / 1000;
            //以秒为单位取整
            if (time > 0) {
                timeShowId.initTime(hour, min, seconds);
                timeShowId.start();
            } else {
                timeShowId.setVisibility(View.GONE);
            }
        }

        verificationCodeInputId.setOnCompleteListener(new VerificationCodeInput.Listener() {
            @Override
            public void onComplete(String content) {
                contents = content;
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                if(isComplet){
                    //网络请求
                    isComplet=false;
                    Map<String, String> reqData = new HashMap<>();
                    reqData.put("Authorization",token);
                    reqData.put("version",getVersionCodes());
                    PayInitParamBean payInitParamBean=new PayInitParamBean();
                    payInitParamBean.setOrderId(orderId);
                    payInitParamBean.setPayType(type+"");
                    payInitParamBean.setSecret(contents);
                    orderInitPresenter.getStorePayInit(reqData,payInitParamBean);
                }

            }
        });

        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID, false);
        token = Hawk.get(HawkKey.TOKEN);
        orderInitPresenter=new OrderInitPresenter(this);
        myOrderListPresenter=new MyOrderListPresenter(this);
        Constant.PAY_RESULT_TYPE=1;
        //WXPayEntryActivity 的orderId赋值
        Constant.orderId=orderId;

        reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());

        isPayPwdPresenter=new IsPayPwdPresenter(this);
    }

    @OnClick({R.id.cancle_id, R.id.sure_pay_id,R.id.back_id,R.id.rv1_id,R.id.rv2_id, R.id.foret_pwd_id, R.id.pay_back_id,R.id.rv3_id,R.id.rv4_id,R.id.yl_check,R.id.cancle_order_id,R.id.refuce_id,R.id.lvs_id,R.id.lvs_back_id,R.id.my_order_id,R.id.dialog_close_id
    ,R.id.zfb_check,R.id.weixin_check,R.id.wallet_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                lvsBackId.setVisibility(View.VISIBLE);
                break;
            case R.id.lvs_id:
                //重写取消订单dialog
                break;
            case R.id.lvs_back_id:
                //重写取消订单dialog
                break;
            case R.id.cancle_order_id:
                myOrderListPresenter.getMyOrderListCancel(reqData,orderId+"");
            case R.id.refuce_id:
                //我再想想
                lvsId.setVisibility(View.GONE);
                break;
            case R.id.dialog_close_id:
                //暂时放弃
                lvsBackId.setVisibility(View.GONE);
                break;
            case R.id.my_order_id:
                //我的订单
                Intent intent1=new Intent(StorePayTypeActivity.this,MyOrderActivity.class);
                intent1.putExtra("from",true);
                startActivity(intent1);
                break;
            case R.id.rv4_id:
            case R.id.yl_check:
                type=4;
                weixinCheck.setImageResource(R.mipmap.unhascheck);
                yl_check.setImageResource(R.mipmap.hascheck);
                zfbCheck.setImageResource(R.mipmap.unhascheck);
                walletCheck.setImageResource(R.mipmap.unhascheck);
                break;
            case R.id.rv1_id:
            case R.id.zfb_check:
                type=1;
                weixinCheck.setImageResource(R.mipmap.unhascheck);
                zfbCheck.setImageResource(R.mipmap.hascheck);
                walletCheck.setImageResource(R.mipmap.unhascheck);
                yl_check.setImageResource(R.mipmap.unhascheck);
                break;
            case R.id.rv2_id:
            case R.id.weixin_check:
                type=2;
                weixinCheck.setImageResource(R.mipmap.hascheck);
                zfbCheck.setImageResource(R.mipmap.unhascheck);
                walletCheck.setImageResource(R.mipmap.unhascheck);
                yl_check.setImageResource(R.mipmap.unhascheck);
                break;
            case R.id.rv3_id:
            case R.id.wallet_check:
                type=3;
                weixinCheck.setImageResource(R.mipmap.unhascheck);
                zfbCheck.setImageResource(R.mipmap.unhascheck);
                walletCheck.setImageResource(R.mipmap.hascheck);
                yl_check.setImageResource(R.mipmap.unhascheck);
                break;
            case R.id.foret_pwd_id:
                isPwd=false;
                isPayPwdPresenter.isPayPwd(reqData);
                break;
            case R.id.pay_back_id:
                accountPayShowRv.setVisibility(View.GONE);
                break;
            case R.id.cancle_id:
                lvsId.setVisibility(View.VISIBLE);
                break;
            case R.id.sure_pay_id:
                if (type==3){
                    //判断是否设置密码
                    isPwd=true;
                    isPayPwdPresenter.isPayPwd(reqData);
                }else {
                   //网络请求
                    PayInitParamBean payInitParamBean=new PayInitParamBean();
                    payInitParamBean.setOrderId(orderId);
                    payInitParamBean.setPayType(type+"");
                    orderInitPresenter.getStorePayInit(reqData,payInitParamBean);
                }

//                /*************************************************
//                 * 步骤1：从网络开始,获取交易流水号即TN
//                 ************************************************/
//                new Thread(StorePayTypeActivity.this).start();

                break;
            default:
                break;
        }
    }
    private void clearData(){
        //遍历子类
        for (int i=0;i<verificationCodeInputId.getChildCount();i++){
            //设置可点击
            verificationCodeInputId.getChildAt(i).setEnabled(true);
            EditText childAt = (EditText) verificationCodeInputId.getChildAt(i);
            //清空内容
            childAt.setText("");
            if(i==0){
                //第一个获取焦点
                verificationCodeInputId.getChildAt(i).requestFocus();
                verificationCodeInputId.getChildAt(i).setFocusable(true);
                verificationCodeInputId.getChildAt(i).setFocusableInTouchMode(true);
            }
        }
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void isPayPwd(PayPwdResultBean payPwdResultBean) {
        if("000000".equals(payPwdResultBean.getCode())){
          if(isPwd){
              if(payPwdResultBean.getBizData().isHavePayPassword()){
                  accountPayShowRv.setVisibility(View.VISIBLE);
                  isComplet=true;
              }else {
                  Intent intent=new Intent(StorePayTypeActivity.this,AddPayPwdActivity.class);
                  intent.putExtra("tell",payPwdResultBean.getBizData().getMobile());
                  startActivity(intent);
              }
          }else {
              Intent intent=new Intent(StorePayTypeActivity.this,AddPayPwdActivity.class);
              intent.putExtra("tell",payPwdResultBean.getBizData().getMobile());
              startActivity(intent);
          }

        }else {
            showToast(payPwdResultBean.getMessage());
        }

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void getMyOrderList(MyOrderListBean myOrderListBean) {

    }

    @Override
    public void getMyOrderListDetail(MyOrderListDetailBean MyOrderListDetailBean) {

    }

    @Override
    public void getMyOrderListDelete(MyOrderListCancelBean myOrderListCancelBean) {

    }

    @Override
    public void getMyOrderListCancel(MyOrderListCancelBean myOrderListCancelBean) {
        if("000000".equals(myOrderListCancelBean.getCode())){
            lvsId.setVisibility(View.GONE);
            Intent intent1=new Intent(StorePayTypeActivity.this,MyOrderActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent1.putExtra("from",true);
            startActivity(intent1);
        }else {
            showToast(myOrderListCancelBean.getMessage());
        }

    }

    @Override
    public void showDelevery(ShowDeleveryResultBean showDeleveryResultBean) {

    }

    @Override
    public void getMyOrderListOk(MyOrderListCancelBean myOrderListCancelBean) {

    }

    @Override
    public void getAfterSaleList(MyAfterSaleListBean myAfterSaleListBean) {

    }

    @Override
    public void getAfterSaleDetail(MyAfterSaleDetailBean myAfterSaleDetailBean) {

    }

    @Override
    public void getApplyReturn(MyOrderListCancelBean myOrderListCancelBean) {

    }

    @Override
    public void getCancelApplyReturn(MyOrderListCancelBean myOrderListCancelBean) {

    }

    @Override
    public void getEditApplyReturn(MyOrderListCancelBean myOrderListCancelBean) {

    }

    @Override
    public void getCreatOder(CreatOderResultBean creatOderResultBean) {

    }

    @Override
    public void getPayInit(final PayInitResultBean payInitResultBean) {

    }

    @Override
    public void getStorePayInit(final PayInitResultBean payInitResultBean) {
        if("000000".equals(payInitResultBean.getCode())){
            if (type == 1) {
                //异步处理
                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        //新建任务
                        PayTask alipay = new PayTask(StorePayTypeActivity.this);
                        //获取支付结果
                        Map<String, String> result = alipay.payV2(payInitResultBean.getBizData(), true);
                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };
                // 必须异步调用

                Thread payThread = new Thread(payRunnable);
                payThread.start();
            } else if (type == 2) {
                Gson gs = new Gson();
                WeinXinBean weinXinBean=gs.fromJson(payInitResultBean.getBizData(), WeinXinBean.class);
                //把JSON字符串转为对象
                PayReq request = new PayReq();
                request.appId = weinXinBean.getAppid();
                request.partnerId = weinXinBean.getPartnerid();
                request.prepayId= weinXinBean.getPrepayid();
                request.packageValue = weinXinBean.getPackageX();
                request.nonceStr= weinXinBean.getNoncestr();
                request.timeStamp= weinXinBean.getTimestamp();
                request.sign= weinXinBean.getSign();
                Toast.makeText(StorePayTypeActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                api.sendReq(request);
                finish();
            } else if (type == 3) {
                Intent intent=new Intent(StorePayTypeActivity.this,StorePayResultActivity.class);
                intent.putExtra("orderId",orderId);
                startActivity(intent);
                finish();
                accountPayShowRv.setVisibility(View.GONE);
            }else  if (type == 4) {
                Message msg = mHandlers.obtainMessage();
                msg.obj = payInitResultBean.getBizData();
                mHandlers.sendMessage(msg);
            }
        }else {
            clearData();
            accountPayShowRv.setVisibility(View.GONE);
            showToast(payInitResultBean.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*************************************************
         * 步骤3：处理银联手机支付控件返回的支付结果
         ************************************************/
        if (data == null) {
            return;
        }

        String msg = "";
        /*
         * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
         */
        String str = data.getExtras().getString("pay_result");
        if (str.equalsIgnoreCase("success")) {

            // 如果想对结果数据验签，可使用下面这段代码，但建议不验签，直接去商户后台查询交易结果
            // result_data结构见c）result_data参数说明
            if (data.hasExtra("result_data")) {
                String result = data.getExtras().getString("result_data");
                try {
                    JSONObject resultJson = new JSONObject(result);
                    String sign = resultJson.getString("sign");
                    String dataOrg = resultJson.getString("data");
                    // 此处的verify建议送去商户后台做验签
                    // 如要放在手机端验，则代码必须支持更新证书
                    boolean ret = verify(dataOrg, sign, mMode);
                    if (ret) {
                        // 验签成功，显示支付结果
                        msg = "支付成功！";
                    } else {
                        // 验签失败
                        msg = "支付失败！";
                    }
                } catch (JSONException e) {
                }
            }
            // 结果result_data为成功时，去商户后台查询一下再展示成功
            msg = "支付成功！";
            Intent intent=new Intent(StorePayTypeActivity.this,StorePayResultActivity.class);
            intent.putExtra("orderId",orderId);
            startActivity(intent);
            finish();
        } else if (str.equalsIgnoreCase("fail")) {
            msg = "支付失败！";
        } else if (str.equalsIgnoreCase("cancel")) {
            msg = "用户取消了支付";
        }

       showToast(msg);
    }

    private boolean verify(String msg, String sign64, String mode) {
        // 此处的verify，商户需送去商户后台做验签
        return true;

    }
    @Override
    protected void onDestroy() {
        if(orderInitPresenter!=null){
            orderInitPresenter.detachView();
        }
        if(isPayPwdPresenter!=null){
            isPayPwdPresenter.detachView();
        }
        if(myOrderListPresenter!=null){
            myOrderListPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        lvsBackId.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean handleMessage(Message msg) {
        String tn = "";
        if (msg.obj == null || ((String) msg.obj).length() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("错误提示");
            builder.setMessage("网络连接失败,请重试!");
            builder.setNegativeButton("确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.create().show();
        } else {
            tn = (String) msg.obj;
            /*************************************************
             * 步骤2：通过银联工具类启动支付插件
             ************************************************/
            // “00” – 银联正式环境
            // “01” – 银联测试环境，该环境中不发生真实交易
            dismissLoading();
            String serverMode = "01";
            doStartUnionPayPlugin(StorePayTypeActivity.this, tn, serverMode);
        }
        return false;
    }

    /**
     *  启动支付界面
     */
    public void doStartUnionPayPlugin(Activity activity, String tn, String mode) {
        UPPayAssistEx.startPayByJAR(activity, PayActivity.class, null, null,
                tn, mode);
    }
    @Override
    public void run() {
        String tn = null;
        InputStream is;
        try {

            String url = TN_URL_01;

            URL myURL = new URL(url);
            URLConnection ucon = myURL.openConnection();
            ucon.setConnectTimeout(120000);
            is = ucon.getInputStream();
            int i = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((i = is.read()) != -1) {
                baos.write(i);
            }

            tn = baos.toString();
            is.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Message msg = mHandlers.obtainMessage();
        msg.obj = tn;
        mHandlers.sendMessage(msg);
    }
}
