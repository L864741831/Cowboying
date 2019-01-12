package com.ibeef.cowboying.view.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.ezvizuikit.open.EZUIError;
import com.ezvizuikit.open.EZUIKit;
import com.ezvizuikit.open.EZUIPlayer;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.VideoAppkeyBase;
import com.ibeef.cowboying.bean.VideoAppkeyResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.VideoAppkeyPresenter;
import com.ibeef.cowboying.utils.InteneteUtil;
import com.ibeef.cowboying.view.customview.WindowSizeChangeNotifier;
import com.orhanobut.hawk.Hawk;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TvLiveActivity extends AppCompatActivity implements View.OnClickListener,VideoAppkeyBase.IView , WindowSizeChangeNotifier.OnWindowSizeChangedListener, EZUIPlayer.EZUIPlayerCallBack {


    private VideoAppkeyPresenter videoAppkeyPresenter;
    private String token;
    private String video_url;
    private String title;
    private String coverUrl;
    private EZUIPlayer mEZUIPlayer;
    private ImageView back_id;
    private MyOrientationDetector mOrientationDetector;
    /**
     * onresume时是否恢复播放
     */
    private boolean isResumePlay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_tv_live);
        init();
    }

    private void init() {
        video_url = getIntent().getStringExtra("video_url");
        title = getIntent().getStringExtra("title");
        coverUrl = getIntent().getStringExtra("coverUrl");

        back_id=findViewById(R.id.back_id);
        back_id.setOnClickListener(this);
        token = Hawk.get(HawkKey.TOKEN);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization", token);
        reqData.put("version", Constant.VersionCodes);
        videoAppkeyPresenter = new VideoAppkeyPresenter(this);
        videoAppkeyPresenter.getVideoAppKey(reqData);


        mOrientationDetector = new MyOrientationDetector(this);
        new WindowSizeChangeNotifier(this, this);

        //获取EZUIPlayer实例
        mEZUIPlayer = (EZUIPlayer) findViewById(R.id.player_ui);
        //设置加载需要显示的view
        mEZUIPlayer.setLoadingView(initProgressBar());

    }

    /**
     * 创建加载view
     *
     * @return
     */
    private View initProgressBar() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setBackgroundColor(Color.parseColor("#000000"));
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        relativeLayout.setLayoutParams(lp);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlp.addRule(RelativeLayout.CENTER_IN_PARENT);//addRule参数对应RelativeLayout XML布局的属性
        ProgressBar mProgressBar = new ProgressBar(this);
        mProgressBar.setIndeterminateDrawable(getResources().getDrawable(R.drawable.progress));
        relativeLayout.addView(mProgressBar, rlp);
        return relativeLayout;
    }

    /**
     * 准备播放资源参数
     */
    private void preparePlay(String mGlobalAreaDomain,String appkey,String accesstoken) {
        //设置debug模式，输出log信息
        EZUIKit.setDebug(true);
        if (TextUtils.isEmpty(mGlobalAreaDomain)) {
            //appkey初始化
            EZUIKit.initWithAppKey(this.getApplication(), appkey);

        } else {
            //appkey初始化 海外版本
            EZUIKit.initWithAppKeyGlobal(this.getApplication(), appkey, mGlobalAreaDomain);
        }
        //设置授权accesstoken
        EZUIKit.setAccessToken(accesstoken);
        //设置播放资源参数
        mEZUIPlayer.setCallBack(this);
        mEZUIPlayer.setUrl(video_url);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!InteneteUtil.isNetworkConnected(this)){
            showWaring("播放失败，请求连接设备超时，检测设备网路连接是否正常");
        }
        mOrientationDetector.enable();
        Log.d(Constant.TAG, "onResume");
        //界面stop时，如果在播放，那isResumePlay标志位置为true，resume时恢复播放
        if (isResumePlay) {
            isResumePlay = false;
            mEZUIPlayer.startPlay();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mOrientationDetector.disable();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Constant.TAG, "onStop + " + mEZUIPlayer.getStatus());
        //界面stop时，如果在播放，那isResumePlay标志位置为true，以便resume时恢复播放
        if (mEZUIPlayer.getStatus() != EZUIPlayer.STATUS_STOP) {
            isResumePlay = true;
        }
        //停止播放
        mEZUIPlayer.stopPlay();
    }

    @Override
    public void onPlaySuccess() {

    }

    @Override
    public void onPlayFail(EZUIError error) {
        Log.d(Constant.TAG, "onPlayFail");
        // TODO: 2017/2/21 播放失败处理
        if (error.getErrorString().equals(EZUIError.UE_ERROR_ACCESSTOKEN_ERROR_OR_EXPIRE)) {
            showWaring("accesstoken异常或失效，需要重新获取accesstoken，并传入到sd");
        } else if (error.getErrorString().equalsIgnoreCase(EZUIError.UE_ERROR_APPKEY_ERROR)) {
            showWaring("appkey和AccessToken不匹配,建议更换appkey或者AccessToken");
        }else if (error.getErrorString().equalsIgnoreCase(EZUIError.UE_ERROR_CAMERA_NOT_EXIST)) {
            showWaring("通道不存在，设备参数错误，建议重新获取播放地址");
        }else if (error.getErrorString().equalsIgnoreCase(EZUIError.UE_ERROR_DEVICE_NOT_EXIST)) {
            showWaring("设备不存在，设备参数错误，建议重新获取播放地址");
        }else if (error.getErrorString().equalsIgnoreCase(EZUIError.UE_ERROR_PARAM_ERROR)) {
            showWaring("参数错误，建议重新获取播放地址");
        }else if (error.getErrorString().equalsIgnoreCase(EZUIError.UE_ERROR_PROTOCAL_ERROR)) {
            showWaring("播放地址错误,建议重新获取播放地址");
        }else if (error.getErrorString().equalsIgnoreCase(EZUIError.UE_ERROR_CAS_MSG_PU_NO_RESOURCE)) {
            showWaring("设备连接数过大，升级设备固件版本,海康设备可咨询客服获取升级流程");
        }else if (error.getErrorString().equalsIgnoreCase(EZUIError.UE_ERROR_TRANSF_DEVICE_OFFLINE)) {
            showWaring("设备不在线，确认设备上线之后重试");
        }else if (error.getErrorString().equalsIgnoreCase(EZUIError.UE_ERROR_INNER_STREAM_TIMEOUT)) {
            showWaring("播放失败，请求连接设备超时，检测设备网路连接是否正常");
        }else if (error.getErrorString().equalsIgnoreCase(EZUIError.UE_ERROR_INNER_VERIFYCODE_ERROR)) {
            showWaring("视频验证码错误，建议重新获取url地址增加验证码");
        }else if (error.getErrorString().equalsIgnoreCase(EZUIError.UE_ERROR_PLAY_FAIL)) {
            showWaring("视频播放失败");
        }else if (error.getErrorString().equalsIgnoreCase(EZUIError.UE_ERROR_TRANSF_TERMINAL_BINDING)) {
            showWaring("当前账号开启了终端绑定，只允许指定设备登录操作");
        }else if (error.getErrorString().equalsIgnoreCase(EZUIError.UE_ERROR_INNER_DEVICE_NULLINFO)) {
            showWaring("设备信息异常为空，建议重新获取播放地址");
        }else if (error.getErrorString().equalsIgnoreCase(EZUIError.UE_ERROR_NOT_FOUND_RECORD_FILES)) {
            showWaring("未查找到录像文件");
        }else if (error.getErrorString().equalsIgnoreCase(EZUIError.UE_ERROR_STREAM_CLIENT_LIMIT)) {
            showWaring("取流并发路数限制");
        }else if (error.getErrorString().equalsIgnoreCase("UE110")) {
            showWaring("设备不支持的清晰度类型, 请根据设备预览能力级选择");
        }
    }


    public void showWaring(String info) {
        final NormalDialog dialog = new NormalDialog(this);
        dialog.isTitleShow(true)
                .style(NormalDialog.STYLE_TWO)
                .content(info)
                .titleTextSize(18)
                .show();
        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                });
    }
    @Override
    public void onVideoSizeChange(int width, int height) {
        // TODO: 2017/2/16 播放视频分辨率回调
        Log.d(Constant.TAG, "onVideoSizeChange  width = " + width + "   height = " + height);
    }

    @Override
    public void onPrepared() {
        Log.d(Constant.TAG, "onPrepared");
        //播放
        mEZUIPlayer.startPlay();
    }

    @Override
    public void onPlayTime(Calendar calendar) {
        Log.d(Constant.TAG, "onPlayTime");
        if (calendar != null) {
            // TODO: 2017/2/16 当前播放时间
            Log.d(Constant.TAG, "onPlayTime calendar = " + calendar.getTime().toString());
        }
    }

    @Override
    public void onPlayFinish() {
        // TODO: 2017/2/16 播放结束
        Log.d(Constant.TAG, "onPlayFinish");
    }


    /**
     * 屏幕旋转时调用此方法
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(Constant.TAG, "onConfigurationChanged");
        setSurfaceSize();
    }

    private void setSurfaceSize() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        boolean isWideScrren = mOrientationDetector.isWideScrren();
        //竖屏
        if (!isWideScrren) {
            //竖屏调整播放区域大小，宽全屏，高根据视频分辨率自适应
            mEZUIPlayer.setSurfaceSize(dm.widthPixels, 0);
        } else {
            //横屏屏调整播放区域大小，宽、高均全屏，播放区域根据视频分辨率自适应
            mEZUIPlayer.setSurfaceSize(dm.widthPixels, dm.heightPixels);
        }
    }

    @Override
    public void onWindowSizeChanged(int w, int h, int oldW, int oldH) {
        if (mEZUIPlayer != null) {
            setSurfaceSize();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.back_id){
            finish();
        }
    }

    public class MyOrientationDetector extends OrientationEventListener {

        private WindowManager mWindowManager;
        private int mLastOrientation = 0;

        public MyOrientationDetector(Context context) {
            super(context);
            mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }

        public boolean isWideScrren() {
            Display display = mWindowManager.getDefaultDisplay();
            Point pt = new Point();
            display.getSize(pt);
            return pt.x > pt.y;
        }

        @Override
        public void onOrientationChanged(int orientation) {
            int value = getCurentOrientationEx(orientation);
            if (value != mLastOrientation) {
                mLastOrientation = value;
                int current = getRequestedOrientation();
                if (current == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        || current == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                }
            }
        }

        private int getCurentOrientationEx(int orientation) {
            int value = 0;
            if (orientation >= 315 || orientation < 45) {
                // 0度
                value = 0;
                return value;
            }
            if (orientation >= 45 && orientation < 135) {
                // 90度
                value = 90;
                return value;
            }
            if (orientation >= 135 && orientation < 225) {
                // 180度
                value = 180;
                return value;
            }
            if (orientation >= 225 && orientation < 315) {
                // 270度
                value = 270;
                return value;
            }
            return value;
        }
    }


    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getVideoAppKey(VideoAppkeyResultBean videoAppkeyResultBean) {
        if ("000000".equals(videoAppkeyResultBean.getCode())) {
            preparePlay("",videoAppkeyResultBean.getBizData().getAppKey(),videoAppkeyResultBean.getBizData().getAccessToken());
            setSurfaceSize();
        } else {
            showWaring(videoAppkeyResultBean.getMessage());
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Constant.TAG, "onDestroy");

        //释放资源
        mEZUIPlayer.releasePlayer();
        if(videoAppkeyPresenter!=null){
            videoAppkeyPresenter.detachView();
        }
    }
}

