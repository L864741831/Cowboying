package com.ibeef.cowboying.view.activity;

import android.content.Intent;
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
import com.ibeef.cowboying.base.AccountSecurityBase;
import com.ibeef.cowboying.bean.BindThirdCountResultBean;
import com.ibeef.cowboying.bean.SafeInfoResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.AccountSecurityPresenter;
import com.ibeef.cowboying.utils.CleanDataUtils;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.kyleduo.switchbutton.SwitchButton;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import rxfamily.view.BaseActivity;

/**
 * 设置界面
 */
public class SetUpActivity extends BaseActivity implements AccountSecurityBase.IView{

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
    @Bind(R.id.phone_stadus)
    ImageView phoneStadus;
    @Bind(R.id.station_txt)
    TextView stationTxt;
    @Bind(R.id.accout_securty_id)
    RelativeLayout accoutSecurtyId;
    @Bind(R.id.modify_pwd_rv)
    RelativeLayout modifyPwdRv;
    @Bind(R.id.goods_addr_rv)
    RelativeLayout goodsAddrRv;
    @Bind(R.id.option_return_rv)
    RelativeLayout optionReturnRv;
    @Bind(R.id.version_code_txt)
    TextView versionCodeTxt;
    @Bind(R.id.release_cache_txt)
    TextView releaseCacheTxt;
    @Bind(R.id.release_cache_rv)
    RelativeLayout releaseCacheRv;
    @Bind(R.id.unlogin_rv)
    TextView unloginRv;
    private String token,isMessege;
    private AccountSecurityPresenter accountSecurityPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);
        ButterKnife.bind(this);
        init();
        token= Hawk.get(HawkKey.TOKEN);
        isMessege=Hawk.get(HawkKey.ISMESSEGE);
        accountSecurityPresenter=new AccountSecurityPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        accountSecurityPresenter.getSafeInfo(reqData);
        if("1".equals(isMessege)){
            switchButton.setChecked(false);
        }else {
            switchButton.setChecked(true);
        }
    }

    private void init() {
        info.setText("设置");
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // 点击恢复按钮后，极光推送服务会恢复正常工作
                    JPushInterface.resumePush(getApplicationContext());
                    Hawk.put(HawkKey.ISMESSEGE,"0");
                }else {
                    // 点击停止按钮后，极光推送服务会被停止掉
                    JPushInterface.stopPush(getApplicationContext());
                    Hawk.put(HawkKey.ISMESSEGE,"1");
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

    @OnClick({R.id.accout_securty_id, R.id.modify_pwd_rv, R.id.option_return_rv, R.id.release_cache_rv,R.id.back_id,R.id.unlogin_rv,R.id.goods_addr_rv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.goods_addr_rv:
                startActivity(AddressActivity.class);
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
                Hawk.put(HawkKey.TOKEN, "");
                startActivity(new Intent(this, LoginActivity.class));
                finish();
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

    @Override
    public void showMsg(String msg) {
    }

    @Override
    public void getSafeInfo(SafeInfoResultBean safeInfoResultBean) {
        if("000000".equals(safeInfoResultBean.getCode())){
            if(SDCardUtil.isNullOrEmpty(safeInfoResultBean.getBizData().getMobile())){
                phoneStadus.setImageResource(R.mipmap.setphoneh);
            }else {
                phoneStadus.setImageResource(R.mipmap.setphone);
            }

            if(SDCardUtil.isNullOrEmpty(safeInfoResultBean.getBizData().getWxId())){
                weixinStadus.setImageResource(R.mipmap.setweixinh);
            }else {
                weixinStadus.setImageResource(R.mipmap.setweixin);
            }
            if(SDCardUtil.isNullOrEmpty(safeInfoResultBean.getBizData().getZfbId())){
               zfbStadus.setImageResource(R.mipmap.setzfbh);
            }else {
                zfbStadus.setImageResource(R.mipmap.setzfb);
            }

        }else {
            showToast(safeInfoResultBean.getMessage());
        }
    }

    @Override
    public void getBindThidCount(BindThirdCountResultBean bindThirdCountResultBean) {

    }

    @Override
    public void getUnBindThidCount(BindThirdCountResultBean bindThirdCountResultBean) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(accountSecurityPresenter!=null){
            accountSecurityPresenter.detachView();
        }
    }
}
