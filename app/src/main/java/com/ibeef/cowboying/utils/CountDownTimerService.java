package com.ibeef.cowboying.utils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * CountDownTimer Service
 * Created by Eminem Lu on 18/8/15.
 * Email arjinmc@hotmail.com
 */
public class CountDownTimerService extends Service {

    private static final long TIMER_UNIT =1000;
    private static long mDistination_total;
    private Timer timer;
    private MyTimerTask timerTask;

    private static long timer_couting = 0;


    private int timerStatus = CountDownTimerUtil.PREPARE;

    public static CountDownTimerService countDownTimerService;

    private static CountDownTimerListener mCountDownTimerListener;

    public static CountDownTimerService getInstance(CountDownTimerListener countDownTimerListener
            ,long distination_total){
        if(countDownTimerService==null){
            countDownTimerService = new CountDownTimerService();
        }
        setCountDownTimerListener(countDownTimerListener);
        mDistination_total = distination_total;
        if(timer_couting==0) {
            timer_couting = mDistination_total;
        }
        return  countDownTimerService;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * get countdowan time
     * @return
     */
    public long getCountingTime(){
        return timer_couting;
    }

    /**
     * get current timer status
     * @return
     */
    public int getTimerStatus(){
        return  timerStatus;
    }

    /**
     * start
     */
    public void startCountDown(){
        startTimer();
        timerStatus = CountDownTimerUtil.START;
    }

    /**
     * paust
     */
    public void pauseCountDown(){
        timer.cancel();
        timerStatus = CountDownTimerUtil.PASUSE;
        timer_couting=getCountingTime()+2000;
//        Log.e("--main--",timer_couting+"timer_couting");
    }

    /**
     * stop
     */
    public void stopCountDown(){
        if(timer!=null){
            timer.cancel();
            initTimerStatus();
            mCountDownTimerListener.onChange();
        }
    }

    public static void  setCountDownTimerListener(CountDownTimerListener countDownTimerListener){
        mCountDownTimerListener = countDownTimerListener;
    }

    /**
     * count down task
     */
    private class MyTimerTask extends TimerTask {


        @Override
        public void run() {
            timer_couting -=TIMER_UNIT;
            mCountDownTimerListener.onChange();
            if(timer_couting==0){
                cancel();
                initTimerStatus1();
            }
        }
    }

    /**
     * init timer status
     */
    private void initTimerStatus(){
        timer_couting = mDistination_total;
        timerStatus = CountDownTimerUtil.PREPARE;
    }
 /**
     * init timer status
     */
    private void initTimerStatus1(){
        timer_couting = 0;
        timerStatus = CountDownTimerUtil.PREPARE;
    }

    /**
     * start count down
     */
    private void startTimer(){
        timer = new Timer();
        timerTask = new MyTimerTask();
        timer.scheduleAtFixedRate(timerTask, 0, TIMER_UNIT);
    }

}
