package rxfamily.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


import com.afollestad.materialdialogs.MaterialDialog;

import rxfamily.R;
import rxfamily.utils.StatusBarUtils;
public class BaseActivity extends RxAppCompatActivity{

    /**
     * 是否沉浸状态栏
     **/
    private boolean isSetStatusBar = false;
    /**
     * 是否允许全屏
     **/
    private boolean mAllowFullScreen = false;
    /**
     * 是否禁止旋转屏幕
     **/
    private boolean isAllowScreenRoate = false;

    private Toolbar toolbar;
    private TextView titleTv;
    private TextView titleSubTv;
    private Boolean hasMenu = false;
    private int menuLayout;
    private MaterialDialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.staduss);
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

//        if (isSetStatusBar) {
//            steepStatusBar();
//        }

        if (!isAllowScreenRoate) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    public void initDialog() {
        loadingDialog = new MaterialDialog.Builder(BaseActivity.this)
                .progress(true, 0)
                .canceledOnTouchOutside(false)
                .build();
    }
    public void showLoadings() {
        if (loadingDialog != null && !loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    public void dismissLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }
    /**
     * [沉浸状态栏]
     */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
//        StatSDKService.onResume(this,"f680476896");
    }

    /**
     * 获取版本号
     * @return
     */
    public String getVersionCodes() {
        return  "1.0";
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
//        StatSDKService.onPause(this,"f680476896");
//        StatService.onPause(this);
    }

    /**
     * 是否允许全屏
     * @param allowFullScreen
     */
    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }

    /**
     * [是否设置沉浸状态栏]
     *
     * @param isSetStatusBar
     */
    public void setSteepStatusBar(boolean isSetStatusBar) {
        this.isSetStatusBar = isSetStatusBar;
    }

    /**
     * [是否允许屏幕旋转]
     *
     * @param isAllowScreenRoate
     */
    public void setScreenRoate(boolean isAllowScreenRoate) {
        this.isAllowScreenRoate = isAllowScreenRoate;
    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(BaseActivity.this, clz));
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    public void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            //使能app bar的导航功能
            actionBar.setDisplayShowTitleEnabled(false);
            //必须设置
        }

        titleTv = findViewById(R.id.toolbar_title);
        toolbar.setTitle("");
        //title_sub_tv = (TextView) findViewById(R.id.toolbar_sub_title);
        //toolbar.setSubtitle("");

        //back_btn = (ImageButton) findViewById(R.id.toolbar_back_btn);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setNavigationIcon(int icon) {
        toolbar.setNavigationIcon(icon);
    }

    public void hasBack(Boolean isBack) {
        if (isBack) {
            toolbar.setNavigationIcon(R.drawable.back_icon);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setToolbarBack();
                }
            });

        }
    }

    protected void setToolbarBack() {
        finish();
    }

    /**
     * 设置背景色
     * @param context
     * @param color
     */
    protected void setToolbarBackground(Context context,int color){
        toolbar.setBackgroundColor(ContextCompat.getColor(context,color));
    }

    /**
     * 设置背景透明度 0-100
     * @param alpha
     */
    protected void setToolbarBackgroundAlpha(int alpha){
        toolbar.getBackground().setAlpha(alpha);
    }

    protected void setToolbarClickListener(View.OnClickListener listener) {

        toolbar.setNavigationOnClickListener(listener);
    }

    protected void setToolbarLogo(int icon) {
        //设置app logo R.drawable.icon
        toolbar.setLogo(icon);
    }

    /**
     * 设置主标题
     * @param title
     */
    protected void setToolbarTitle(String title) {
        titleTv.setText(title);
        setToolbarTitlePosition("center");
    }

    protected void setToolbarTitleTextColor(Context context,int color) {
        if (titleTv != null) {
            titleTv.setTextColor(ContextCompat.getColor(context,color));
        } else {
            Log.e("ToolbarTitleTextColor", "title_tv is null ,please check out");
        }
    }

    protected void setToolbarTitleTextSize(float size) {
        if (titleTv != null) {
            titleTv.setTextSize(size);
        }
    }

    protected void setToolbarTitlePosition(String position) {

        String left="left",right="right";
        if (left.equals(position)) {
            titleTv.setGravity(Gravity.LEFT);
        } else if (right.equals(position)) {
            titleTv.setGravity(Gravity.RIGHT);
        } else {
            titleTv.setGravity(Gravity.CENTER);
        }
    }

    protected void setToolbarSubTitle(String subTitle) {
        titleSubTv.setText(subTitle);
        titleSubTv.setVisibility(View.VISIBLE);
    }

    protected void setToolbarSubTitleTextColor(Context context,int color) {
        if (titleSubTv != null) {
            titleSubTv.setTextColor(ContextCompat.getColor(context,color));
        }
    }

    protected void setToolbarSubTitlePosition(String position) {
        String left="left",right="right";
        if (left.equals(position)) {
            titleSubTv.setGravity(Gravity.LEFT);
        } else if (right.equals(position)) {
            titleSubTv.setGravity(Gravity.RIGHT);
        } else {
            titleSubTv.setGravity(Gravity.CENTER);
        }
    }

    protected void setHasMenu(Boolean show) {
        this.hasMenu = show;
    }

    protected Boolean getHasMenu() {
        return this.hasMenu;
    }

    /**
     * 设置菜单layout
     */
    protected void setMenuLayout(int layoutId){
        this.menuLayout = layoutId;
    }

    protected int getMenuLayout(){
        return this.menuLayout;
    }

    /**
     * 右侧菜单(可自定义菜单layout)
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        if(getHasMenu()){
            getMenuInflater().inflate(getMenuLayout(), menu);
            return true;
        }

        return false;
    }

    public void showToast(String txt) {
        try {
            Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
