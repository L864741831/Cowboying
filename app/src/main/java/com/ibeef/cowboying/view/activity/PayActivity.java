package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.MyContractBase;
import com.ibeef.cowboying.bean.MyContractListBean;
import com.ibeef.cowboying.bean.MyContractURLBean;
import com.ibeef.cowboying.bean.MyDiscountCouponListBean;
import com.ibeef.cowboying.bean.PayCodeBean;
import com.ibeef.cowboying.bean.VipCardBean;
import com.ibeef.cowboying.bean.VipCardListBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.MyContractPresenter;
import com.king.zxing.util.CodeUtils;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * @author Administrator
 * 付款吗界面
 */
public class PayActivity extends BaseActivity implements MyContractBase.IView{

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.fl_code)
    FrameLayout flCode;
    @Bind(R.id.iv_code_1)
    ImageView ivCode1;
    @Bind(R.id.tv_code)
    TextView tvCode;
    @Bind(R.id.iv_code_2)
    ImageView ivCode2;
    @Bind(R.id.v_line)
    View vLine;
    @Bind(R.id.tv_wallet)
    TextView tvWallet;
    private String token;
    private MyContractPresenter myContractPresenter;
    private PayCodeBean payCodeBean;
    private String payCode;
    private Handler handler;
    private Thread thread;
    private boolean stopThread = false;
    private String payType="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
       token = Hawk.get(HawkKey.TOKEN);
       info.setText("向商家付钱");
        myContractPresenter=new MyContractPresenter(this);

        thread = new Thread(new MyThread());
        thread.start();
        //do something
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1){
                    Map<String, String> reqData = new HashMap<>();
                    reqData.put("Authorization", token);
                    reqData.put("version", getVersionCodes());
                    myContractPresenter.showPayCode(reqData,payType);
                }
                super.handleMessage(msg);
            }
        };
    }

    class MyThread extends Thread {//这里也可用Runnable接口实现
         @Override
         public void run() {
              while (!stopThread){
                try {
                    Thread.sleep(60000);//每隔1s执行一次
                    Message msg = new Message();
                    msg.what = 1;
                     handler.sendMessage(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                         } } }
     }


    @Override
    protected void onResume() {
        super.onResume();
        token= Hawk.get(HawkKey.TOKEN);
        if (!TextUtils.isEmpty(token)) {
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization", token);
            reqData.put("version", getVersionCodes());
            myContractPresenter.showPayCode(reqData,payType);
        }
    }

    @OnClick({R.id.back_id, R.id.tv_wallet,R.id.tv_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.tv_wallet:
                Intent intent = new Intent(this, PayTypeDialog.class);
                intent.putExtra("PayType",payType);
                startActivityForResult(intent, 666);
                break;
            case R.id.tv_code:
                tvCode.setText(payCode);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 666 && resultCode == 555) {
            payType = data.getStringExtra("PayType");
//            Map<String, String> reqData = new HashMap<>();
//            reqData.put("Authorization", token);
//            reqData.put("version", getVersionCodes());
//            myContractPresenter.showPayCode(reqData,payType);
//            Log.i("htht", "payType=============" + payType);
        }
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void getMyContrantList(MyContractListBean myContractListBean) {

    }

    @Override
    public void getMyContrantURL(MyContractURLBean myContractURLBean) {

    }

    @Override
    public void getMyDiscountCouponList(MyDiscountCouponListBean myDiscountCouponListBean) {

    }

    @Override
    public void showPayCode(PayCodeBean payCodeBean) {
        if("000000".equals(payCodeBean.getCode())){
            this.payCodeBean=payCodeBean;
            payCode = payCodeBean.getBizData().getPayCode();
            if (payCode!=null){
                tvCode.setText(payCode.substring(0,4)+"******查看数字");
                Bitmap barCode = CodeUtils.createBarCode(payCode, BarcodeFormat.CODE_128, 800, 200);
                ivCode1.setImageBitmap(barCode);
               for (int i =0;i<payCodeBean.getBizData().getPayTypeList().size();i++){
                  if (payCodeBean.getBizData().getPayTypeList().get(i).isChecked()==true){
                      tvWallet.setText(payCodeBean.getBizData().getPayTypeList().get(i).getName());
                      if ("1".equals(payCodeBean.getBizData().getPayTypeList().get(i).getPayType())){
                          ivCode2.setImageBitmap(CodeUtils.createQRCode(payCode,600,BitmapFactory.decodeResource(getResources(), R.mipmap.pay_code_log_1, null)));
                      }else if ("2".equals(payCodeBean.getBizData().getPayTypeList().get(i).getPayType())){
                          ivCode2.setImageBitmap(CodeUtils.createQRCode(payCode,600,BitmapFactory.decodeResource(getResources(), R.mipmap.pay_code_log_2, null)));
                      }
                  }
               }
            }
        }
    }

    @Override
    public void showVipCard(VipCardBean vipCardBean) {

    }

    @Override
    public void showVipCardHistory(VipCardListBean vipCardListBean) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopThread = true;//这样在线程执行run方法就会退出那个循环了
        if(myContractPresenter!=null){
            myContractPresenter.detachView();
        }
    }
}
