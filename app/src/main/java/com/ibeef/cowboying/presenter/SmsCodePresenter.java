package com.ibeef.cowboying.presenter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.ibeef.cowboying.base.AccountRegisterBase;
import com.ibeef.cowboying.base.SmscodeBase;
import com.ibeef.cowboying.bean.AccountRegisterParamBean;
import com.ibeef.cowboying.bean.AccountRegisterResultBean;
import com.ibeef.cowboying.bean.SmsCodeParamBean;
import com.ibeef.cowboying.bean.SmsCodeResultBean;
import com.ibeef.cowboying.bean.ValidateSmsCodeParamBean;
import com.ibeef.cowboying.model.AccountRegisetModel;
import com.ibeef.cowboying.model.SmsCodeModel;
import com.ibeef.cowboying.utils.CountDownTimerListener;
import com.ibeef.cowboying.utils.CountDownTimerService;
import com.ibeef.cowboying.utils.RxCountDown;
import com.ibeef.cowboying.utils.SDCardUtil;

import rx.Subscriber;
import rx.functions.Action0;
import rxfamily.mvp.BasePresenter;
import rxfamily.net.ResponseCallback;

/**
 * @author ls
 * @date on 2018/10/7 14:07
 * @describe 短信验证码
 * @package com.ranhan.cowboying.presenter
 **/
public class SmsCodePresenter extends BasePresenter implements SmscodeBase.IPresenter  {
    private SmscodeBase.IView mView;
    private SmscodeBase.IModel mModel;
    private CountDownTimerService countDownTimerService;
    public SmsCodePresenter(SmscodeBase.IView iView) {
        attachView(iView);
        mView = iView;
        mModel = new SmsCodeModel();
        countDownTimerService = CountDownTimerService.getInstance(new MyCountDownLisener()
                ,60*1000);
    }

    /**
     * 计时器handler更新UI
     */
    private class MyCountDownLisener implements CountDownTimerListener {

        @Override
        public void onChange() {
            mHandler.sendEmptyMessage(2);
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 2:
                    if(!SDCardUtil.isNullOrEmpty(countDownTimerService)){
                        if(Integer.parseInt(countDownTimerService.getCountingTime()/1000+"")==0){
                            mView.setClickable(true);
                            mView.countNumber("重新发送");
                            countDownTimerService = CountDownTimerService.getInstance(new MyCountDownLisener()
                                    ,60*1000);
                        }else {
                            mView.setClickable(false);
                            mView.countNumber(countDownTimerService.getCountingTime()/1000 + "秒后重发");
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public CountDownTimerService getCountDownTimerService(){
        return countDownTimerService;
    }
    @Override
    public void getSms(String version, SmsCodeParamBean smsCodeParamBean) {
        addSubscription(mModel.getSms(version,smsCodeParamBean,new ResponseCallback<SmsCodeResultBean>() {
            @Override
            public void onSuccess(SmsCodeResultBean result) {
                if("000000".equals(result.getCode())){
                    countDownTimerService.startCountDown();
                }
                mView.getSms(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void getValidateSms(String version, ValidateSmsCodeParamBean validateSmsCodeParamBean) {
        addSubscription(mModel.getValidateSms(version,validateSmsCodeParamBean,new ResponseCallback<SmsCodeResultBean>() {
            @Override
            public void onSuccess(SmsCodeResultBean result) {
                mView.getValidateSms(result);

            }

            @Override
            public void onFaild(String msg) {
                Log.e("onFaild", msg + "");
                mView.showMsg(msg);
            }
        }));
    }
}
