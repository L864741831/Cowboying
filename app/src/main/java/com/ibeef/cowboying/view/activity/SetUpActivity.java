package com.ibeef.cowboying.view.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.config.HawkKey;
import com.kyleduo.switchbutton.SwitchButton;
import com.ibeef.cowboying.utils.CleanDataUtils;
import com.orhanobut.hawk.Hawk;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import rxfamily.view.BaseActivity;

/**
 * 设置界面
 */
public class SetUpActivity extends BaseActivity {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.switchButton)
    SwitchButton switchButton;
    @Bind(R.id.weixin_stadus)
    ImageView weixinStadus;
    @Bind(R.id.zfb_stadus)
    ImageView zfbStadus;
    @Bind(R.id.station_txt)
    TextView stationTxt;
    @Bind(R.id.accout_securty_id)
    RelativeLayout accoutSecurtyId;
    @Bind(R.id.modify_pwd_rv)
    RelativeLayout modifyPwdRv;
    @Bind(R.id.option_return_rv)
    RelativeLayout optionReturnRv;
    @Bind(R.id.nomal_question_rv)
    RelativeLayout nomalQuestionRv;
    @Bind(R.id.version_code_txt)
    TextView versionCodeTxt;
    @Bind(R.id.release_cache_txt)
    TextView releaseCacheTxt;
    @Bind(R.id.release_cache_rv)
    RelativeLayout releaseCacheRv;
    @Bind(R.id.unlogin_rv)
    RelativeLayout unloginRv;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        token= Hawk.get(HawkKey.TOKEN);
        info.setText("设置");
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // 点击恢复按钮后，极光推送服务会恢复正常工作
                    JPushInterface.resumePush(getApplicationContext());
                    Toast.makeText(SetUpActivity.this,"true",Toast.LENGTH_SHORT).show();
                }else {
                    // 点击停止按钮后，极光推送服务会被停止掉
                    JPushInterface.stopPush(getApplicationContext());
                    Toast.makeText(SetUpActivity.this,"false",Toast.LENGTH_SHORT).show();
                }

            }
        });
        getVersionCode();

        try {
            String totalCacheSize = CleanDataUtils.getTotalCacheSize(this);
            releaseCacheTxt.setText(totalCacheSize);
        } catch (Exception e) {
        }

        initDialog();
    }



    @OnClick({R.id.accout_securty_id, R.id.modify_pwd_rv, R.id.option_return_rv, R.id.nomal_question_rv, R.id.release_cache_rv,R.id.back_id,R.id.unlogin_rv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.accout_securty_id:
                startActivity(AccoutSecurityActivity.class);
                break;
            case R.id.modify_pwd_rv:
                startActivity(ModifyPwdActivity.class);
                break;
            case R.id.option_return_rv:
                startActivity(OptionReturnActivity.class);
                break;
            case R.id.nomal_question_rv:
                startActivity(NormalQuestionActivity.class);
                break;
            case R.id.release_cache_rv:
                try {
                    showLoadings();
                    if(CleanDataUtils.clearAllCache(SetUpActivity.this)){
                       Toast.makeText(SetUpActivity.this,"缓存清除成功~",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SetUpActivity.this,"缓存清除失败~",Toast.LENGTH_SHORT).show();
                    }
                    String size = CleanDataUtils.getTotalCacheSize(SetUpActivity.this);
                    releaseCacheTxt.setText(size);
                    dismissLoading();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.unlogin_rv:
                finish();
                startActivity(LoginActivity.class);
                break;
            default:
                break;
        }
    }
    /**
     * 获取版本号
     * @return
     */
    public void getVersionCode() {
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            String versionName = packageInfo.versionName;
            versionCodeTxt.setText("版本 "+versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}