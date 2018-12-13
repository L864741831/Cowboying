package com.ibeef.cowboying.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.OrderInitBase;
import com.ibeef.cowboying.base.PayPwdBase;
import com.ibeef.cowboying.bean.CreatOderResultBean;
import com.ibeef.cowboying.bean.PayInitParamBean;
import com.ibeef.cowboying.bean.PayInitResultBean;
import com.ibeef.cowboying.bean.PayPwdResultBean;
import com.ibeef.cowboying.bean.PayResult;
import com.ibeef.cowboying.bean.WeinXinBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.IsPayPwdPresenter;
import com.ibeef.cowboying.presenter.OrderInitPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.ibeef.cowboying.utils.VerificationCodeInput;
import com.orhanobut.hawk.Hawk;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import rxfamily.view.BaseActivity;

/**
 * 买牛确认订单界面
 */
public class SureOderActivity extends BaseActivity implements OrderInitBase.IView,PayPwdBase.IView {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.pay_back_id)
    ImageView payBackId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.head_img)
    CircleImageView headImg;
    @Bind(R.id.nick_name_txt)
    TextView nickNameTxt;
    @Bind(R.id.order_code_txt)
    TextView orderCodeTxt;
    @Bind(R.id.pay_money_id)
    TextView payMoneyId;
    @Bind(R.id.coupon_money_id)
    TextView couponMoneyId;
    @Bind(R.id.know_name_id)
    TextView knowNameId;
    @Bind(R.id.know_mobile_id)
    TextView knowMobileId;
    @Bind(R.id.know_identifycode_id)
    TextView knowIdentifycodeId;
    @Bind(R.id.know_code_id)
    TextView knowCodeId;
    @Bind(R.id.know_pasture_id)
    TextView knowPastureId;
    @Bind(R.id.know_number_id)
    TextView knowNumberId;
    @Bind(R.id.know_one_price_id)
    TextView knowOnePriceId;
    @Bind(R.id.custom_txt_id)
    TextView customTxtId;
    @Bind(R.id.sure_pay_btn)
    TextView surePayBtn;
    @Bind(R.id.account_pay_show_rv)
    RelativeLayout accountPayShowRv;
//    @Bind(R.id.verificationCodeInput_id)
    VerificationCodeInput verificationCodeInputId;
    @Bind(R.id.foret_pwd_id)
    TextView foretPwdId;
    @Bind(R.id.zfb_check)
    CheckBox zfbCheck;
    @Bind(R.id.weixin_check)
    CheckBox weixinCheck;
    @Bind(R.id.account_balance_ck)
    CheckBox accountBalanceCk;
    private int chooseType = 1;

    private static final int SDK_PAY_FLAG = 1;
    private IWXAPI api;
    private String token, contents;

    private CreatOderResultBean infos;
    private OrderInitPresenter orderInitPresenter;
    private boolean isComplet=true;
    private IsPayPwdPresenter isPayPwdPresenter;
    private boolean isPwd;

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
                        Toast.makeText(SureOderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(SureOderActivity.this,PayResultActivity.class);
                        intent.putExtra("orderId",infos.getBizData().getOrderId());
                        startActivity(intent);
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(SureOderActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_sure_oder);
        ButterKnife.bind(this);
        verificationCodeInputId=findViewById(R.id.verificationCodeInput_id);
        init();
        Constant.PAY_RESULT_TYPE=0;
    }

    private void init() {
        info.setText("确认订单");
        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID, false);
        token = Hawk.get(HawkKey.TOKEN);

        infos= (CreatOderResultBean) getIntent().getSerializableExtra("infos");
        RequestOptions options = new RequestOptions()
                .error(R.mipmap.meheaddefalut)
                //加载错误之后的错误图
                .skipMemoryCache(true)
                //跳过内存缓存
                ;
        //WXPayEntryActivity 的orderId赋值
        Constant.orderId=infos.getBizData().getOrderId();

        Glide.with(this).load(Constant.imageDomain+infos.getBizData().getHeadImage()).apply(options).into(headImg);
        nickNameTxt.setText("昵称："+infos.getBizData().getNickName());
        knowNameId.setText("认领人："+infos.getBizData().getRealName());
        orderCodeTxt.setText("订单编号："+infos.getBizData().getCode());
        payMoneyId.setText("￥"+infos.getBizData().getPayAmount());
        knowMobileId.setText(infos.getBizData().getMobile());
        knowIdentifycodeId.setText("身份证号："+infos.getBizData().getCardNo());
        couponMoneyId.setText("已优惠￥"+infos.getBizData().getDiscountAmount());
        knowCodeId.setText("第"+infos.getBizData().getSchemeCode()+"期");
        if(!SDCardUtil.isNullOrEmpty(infos.getBizData().getPastureName())){
            knowPastureId.setText(infos.getBizData().getPastureName());
        }
        knowNumberId.setText(infos.getBizData().getQuantity()+"");
        knowOnePriceId.setText(infos.getBizData().getPrice()+"");

        verificationCodeInputId.setOnCompleteListener(new VerificationCodeInput.Listener() {
            @Override
            public void onComplete(String content) {
                contents = content;
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                if(isComplet){
                    isComplet=false;
                    Map<String, String> reqData = new HashMap<>();
                    reqData.put("Authorization",token);
                    reqData.put("version",getVersionCodes());
                    PayInitParamBean payInitParamBean=new PayInitParamBean();
                    payInitParamBean.setOrderId(infos.getBizData().getOrderId());
                    payInitParamBean.setPayType(chooseType+"");
                    payInitParamBean.setSecret(contents);
                    orderInitPresenter.getPayInit(reqData,payInitParamBean);
                }
                Log.e(Constant.TAG, "完成输入：" + content);
            }
        });

        orderInitPresenter=new OrderInitPresenter(this);
        isPayPwdPresenter=new IsPayPwdPresenter(this);
    }

    @OnClick({R.id.back_id, R.id.custom_txt_id, R.id.sure_pay_btn, R.id.pay_back_id, R.id.foret_pwd_id, R.id.account_balance_ck, R.id.weixin_check, R.id.zfb_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.account_balance_ck:
                chooseType = 3;
                weixinCheck.setBackground(ContextCompat.getDrawable(SureOderActivity.this, R.drawable.unhascheck));
                zfbCheck.setBackground(ContextCompat.getDrawable(SureOderActivity.this, R.drawable.unhascheck));
                accountBalanceCk.setBackground(ContextCompat.getDrawable(SureOderActivity.this, R.drawable.hascheck));
                break;
            case R.id.weixin_check:
                chooseType = 2;
                weixinCheck.setBackground(ContextCompat.getDrawable(SureOderActivity.this, R.drawable.hascheck));
                zfbCheck.setBackground(ContextCompat.getDrawable(SureOderActivity.this, R.drawable.unhascheck));
                accountBalanceCk.setBackground(ContextCompat.getDrawable(SureOderActivity.this, R.drawable.unhascheck));
                break;
            case R.id.zfb_check:
                chooseType = 1;
                weixinCheck.setBackground(ContextCompat.getDrawable(SureOderActivity.this, R.drawable.unhascheck));
                zfbCheck.setBackground(ContextCompat.getDrawable(SureOderActivity.this, R.drawable.hascheck));
                accountBalanceCk.setBackground(ContextCompat.getDrawable(SureOderActivity.this, R.drawable.unhascheck));
                break;
            case R.id.custom_txt_id:
                showToast("客服");
                break;
            case R.id.pay_back_id:
                accountPayShowRv.setVisibility(View.GONE);
                surePayBtn.setVisibility(View.VISIBLE);
                break;
            case R.id.foret_pwd_id:
                isPwd=false;
                break;
            case R.id.sure_pay_btn:
                Map<String, String> reqData = new HashMap<>();
                reqData.put("Authorization",token);
                reqData.put("version",getVersionCodes());
                if (chooseType==3){
                    //判断是否设置密码
                    isPwd=true;
                    isPayPwdPresenter.isPayPwd(reqData);
                }else {
                    PayInitParamBean payInitParamBean=new PayInitParamBean();
                    payInitParamBean.setOrderId(infos.getBizData().getOrderId());
                    payInitParamBean.setPayType(chooseType+"");
                    orderInitPresenter.getPayInit(reqData,payInitParamBean);
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
            if(isPwd){
                if(payPwdResultBean.getBizData().isHavePayPassword()){
                    accountPayShowRv.setVisibility(View.VISIBLE);
                    surePayBtn.setVisibility(View.GONE);
                    isComplet=true;
                }else {
                    Intent intent=new Intent(SureOderActivity.this,AddPayPwdActivity.class);
                    intent.putExtra("tell",payPwdResultBean.getBizData().getMobile());
                    startActivity(intent);
                }
            }else {
                Intent intent=new Intent(SureOderActivity.this,AddPayPwdActivity.class);
                intent.putExtra("tell",payPwdResultBean.getBizData().getMobile());
                startActivity(intent);
            }


        }else {
            showToast(payPwdResultBean.getMessage());
        }
    }

    @Override
    public void getCreatOder(CreatOderResultBean creatOderResultBean) {

    }

    @Override
    public void getPayInit(final PayInitResultBean payInitResultBean) {

        if("000000".equals(payInitResultBean.getCode())){
            if (chooseType == 1) {
                //异步处理
                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        //新建任务
                        PayTask alipay = new PayTask(SureOderActivity.this);
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
            } else if (chooseType == 2) {
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
                Toast.makeText(SureOderActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                api.sendReq(request);
                finish();
            } else if (chooseType == 3) {

                Intent intent=new Intent(SureOderActivity.this,PayResultActivity.class);
                intent.putExtra("orderId",infos.getBizData().getOrderId());
                startActivity(intent);
                finish();
                accountPayShowRv.setVisibility(View.GONE);
                surePayBtn.setVisibility(View.VISIBLE);
            }
        }else {
            clearData();
            accountPayShowRv.setVisibility(View.GONE);
            surePayBtn.setVisibility(View.VISIBLE);
            showToast(payInitResultBean.getMessage());
        }
    }

    @Override
    public void getStorePayInit(PayInitResultBean payInitResultBean) {

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
}
