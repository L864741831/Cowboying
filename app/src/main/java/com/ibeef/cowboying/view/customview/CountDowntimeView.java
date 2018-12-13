package com.ibeef.cowboying.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Chronometer;

import java.text.SimpleDateFormat;

/**
 * 倒计时分秒
 * author: gyq
 * create at 2017/2/15 11:04
 */
public class CountDowntimeView extends Chronometer{
    private long mTime;
    private long mNextTime;
    private OnTimeCompleteListener mListener;
    private SimpleDateFormat mTimeFormat;

    public CountDowntimeView(Context context) {
        super(context);
    }

    public CountDowntimeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mTimeFormat = new SimpleDateFormat("hh:mm:ss");
        this.setOnChronometerTickListener(listener);
    }

    //重新启动计时
    public void reStart(long time_s)
    {
        if (time_s == -1)
        {
            mNextTime = mTime;
        }
        else
        {
            mTime = mNextTime = time_s;
        }
        this.start();
    }

    public void reStart()
    {
        reStart(-1);
    }

    //不建议方法名用onResume()或onPause()，容易和activity生命周期混淆
    //继续计时
    public void onResume()
    {
        this.start();
    }

    //暂停计时
    public void onPause()
    {
        this.stop();
    }

    /**
     * 设置时间格式
     *
     * @param pattern 计时格式
     */
    public void setTimeFormat(String pattern)
    {
        mTimeFormat = new SimpleDateFormat(pattern);
    }

    public void setOnTimeCompleteListener(OnTimeCompleteListener l)
    {
        mListener = l;
    }

    OnChronometerTickListener listener = new OnChronometerTickListener()
    {
        @Override
        public void onChronometerTick(Chronometer chronometer)
        {
            if (mNextTime <= 0)
            {
                if (mNextTime == 0)
                {
                    CountDowntimeView.this.stop();
                    if (null != mListener) {
                        mListener.onTimeComplete();
                    }
                }
                mNextTime = 0;
                updateTimeText();
                return;
            }

            mNextTime--;

            updateTimeText();
        }
    };

    //初始化时间(秒)
    public void initTime(long time_s)
    {
        mTime = mNextTime = time_s;
        updateTimeText();
    }

    //初始化时间（分秒）
    public void initTime(long time_d,long time_h,long time_m,long time_s) {
        initTime(time_d*86400+time_h*3600+time_m * 60 + time_s);
    }

    private void updateTimeText()
    {
        this.setText("还剩"+FormatMiss(mNextTime));
//        this.setText(FormatMiss(mNextTime));
    }

    // 将秒转化成小时分钟秒
    public String FormatMiss(long miss){

//			long day = miss / 86400000;                         //以天数为单位取整
//			long hh = miss % 86400000 / 3600000;               //以小时为单位取整
//			long mm = miss % 86400000 % 3600000 / 60000;       //以分钟为单位取整
//			long ss = miss % 86400000 % 3600000 % 60000 / 1000;   //以秒为单位取整
        String day=((miss/3600)/24)+"";
        String hh=(miss/3600)%24>9?(miss/3600)%24 +"":"0"+(miss/3600)%24 ;
        String  mm=(miss % 3600)/60>9?(miss % 3600)/60+"":"0"+(miss % 3600)/60;
        String ss=(miss % 3600) % 60>9?(miss % 3600) % 60+"":"0"+(miss % 3600) % 60;

        return day+"天"+hh+"时"+mm+"分";
//        return mm+"分"+ss+"秒";
    }

    public interface OnTimeCompleteListener
    {
        void onTimeComplete();
    }

}