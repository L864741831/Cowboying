package com.ibeef.cowboying.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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
import com.ibeef.cowboying.utils.VerificationCodeInput;
import com.ibeef.cowboying.view.customview.CountDownView;
import com.orhanobut.hawk.Hawk;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 商城支付方式
 */
public class StorePayTypeActivity extends BaseActivity implements OrderInitBase.IView, MyOrderListBase.IView,PayPwdBase.IView{

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
    @Bind(R.id.refuce_id)
    TextView refuceId;
    @Bind(R.id.cancle_order_id)
    TextView cancle_order_id;
    private int type=1;
    private boolean isComplet=true;
    private String token, contents;
    private IWXAPI api;
    private static final int SDK_PAY_FLAG = 1;
    private int orderId;
    private OrderInitPresenter orderInitPresenter;
    private MyOrderListPresenter myOrderListPresenter;
    private  Map<String, String> reqData;
    private IsPayPwdPresenter isPayPwdPresenter;

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
        orderId=getIntent().getIntExtra("orderId",0);

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

    @OnClick({R.id.cancle_id, R.id.sure_pay_id,R.id.back_id,R.id.zfb_check,R.id.weixin_check, R.id.foret_pwd_id, R.id.pay_back_id,R.id.wallet_check,R.id.cancle_order_id,R.id.refuce_id,R.id.lvs_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                lvsId.setVisibility(View.VISIBLE);
                break;
            case R.id.lvs_id:
                //重写取消订单dialog
                break;
            case R.id.cancle_order_id:
                myOrderListPresenter.getMyOrderListCancel(reqData,orderId+"");
            case R.id.refuce_id:
                //我再想想
                lvsId.setVisibility(View.GONE);
                break;
            case R.id.zfb_check:
                type=1;
                weixinCheck.setImageResource(R.drawable.unhascheck);
                zfbCheck.setImageResource(R.drawable.hascheck);
                walletCheck.setImageResource(R.drawable.unhascheck);
                break;
            case R.id.weixin_check:
                type=2;
                weixinCheck.setImageResource(R.drawable.hascheck);
                zfbCheck.setImageResource(R.drawable.unhascheck);
                walletCheck.setImageResource(R.drawable.unhascheck);
                break;
            case R.id.wallet_check:
                type=3;
                weixinCheck.setImageResource(R.drawable.unhascheck);
                zfbCheck.setImageResource(R.drawable.unhascheck);
                walletCheck.setImageResource(R.drawable.hascheck);
                break;
            case R.id.foret_pwd_id:
                //设置支付密码
                startActivity(AddPayPwdActivity.class);
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
                    isPayPwdPresenter.isPayPwd(reqData);
                }else {
                   //网络请求
                    PayInitParamBean payInitParamBean=new PayInitParamBean();
                    payInitParamBean.setOrderId(orderId);
                    payInitParamBean.setPayType(type+"");
                    orderInitPresenter.getStorePayInit(reqData,payInitParamBean);
                }
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
            if(payPwdResultBean.isBizData()){
                accountPayShowRv.setVisibility(View.VISIBLE);
                isComplet=true;
            }else {
                startActivity(AddPayPwdActivity.class);
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
            }
        }else {
            clearData();
            accountPayShowRv.setVisibility(View.GONE);
            showToast(payInitResultBean.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        if(orderInitPresenter!=null){
            orderInitPresenter.detachView();
        }
        if(isPayPwdPresenter!=null){
            isPayPwdPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        lvsId.setVisibility(View.VISIBLE);
    }
}
