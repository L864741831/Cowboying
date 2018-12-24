package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MainFragmentAdapter;
import com.ibeef.cowboying.base.CheckVersionBase;
import com.ibeef.cowboying.base.GetJgRegisterIdBase;
import com.ibeef.cowboying.bean.CheckVersionBean;
import com.ibeef.cowboying.bean.CheckVersionParamBean;
import com.ibeef.cowboying.bean.JgParamBean;
import com.ibeef.cowboying.bean.JgResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.CheckVersionPresenter;
import com.ibeef.cowboying.presenter.GetRegisterIdPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.ibeef.cowboying.view.customview.NoScrollViewPager;
import com.ibeef.cowboying.view.fragment.HomeFragment;
import com.ibeef.cowboying.view.fragment.SecondFragment;
import com.ibeef.cowboying.view.fragment.ThreeFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import rxfamily.utils.PackageUtils;
import rxfamily.view.BaseActivity;
import rxfamily.view.BaseFragment;

/**
 * 主页面
 */
public class MainActivity extends BaseActivity implements CheckVersionBase.IView,GetJgRegisterIdBase.IView{

    @Bind(R.id.bnve)
    BottomNavigationViewEx bnve;
    @Bind(R.id.vp)
    NoScrollViewPager vp;
    private ArrayList<BaseFragment> fragments;
    private CheckVersionPresenter checkVersionPresenter;
    private String token;
    private int index;
    private GetRegisterIdPresenter getRegisterIdPresenter;
    private Map<String, String> reqData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        initEvent();

    }

    private void init(){
        token= Hawk.get(HawkKey.TOKEN);
        bnve.enableAnimation(false);
        bnve.enableShiftingMode(false);
        bnve.enableItemShiftingMode(false);
        bnve.setTextSize(12);
        bnve.setIconSize(30,30);
        bnve.setIconsMarginTop(13);
        bnve.setItemIconTintList(null);
        fragments=new ArrayList<>();
        HomeFragment homeFragment=new HomeFragment();
        SecondFragment secondFragment=new SecondFragment();
        ThreeFragment threeFragment=new ThreeFragment();
        fragments.add(homeFragment);
        fragments.add(secondFragment);
        fragments.add(threeFragment);

        MainFragmentAdapter mAdpter = new MainFragmentAdapter(getSupportFragmentManager(),fragments);
        vp.setAdapter(mAdpter);
        bnve.setupWithViewPager(vp);

        index=getIntent().getIntExtra("index",0);

        bnve.setSelectedItemId(bnve.getMenu().getItem(index).getItemId());

//        bnve.setCurrentItem(index);

        checkVersionPresenter=new CheckVersionPresenter(this);
        CheckVersionParamBean checkVersionParamBean=new CheckVersionParamBean();
        checkVersionParamBean.setAppType("1");
        checkVersionParamBean.setVersion(PackageUtils.getAppVersionCode(this)+"");
        checkVersionPresenter.getCheckVersion(getVersionCodes(),checkVersionParamBean);

        getRegisterIdPresenter=new GetRegisterIdPresenter(this);
        reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        if(!TextUtils.isEmpty(token)){
            JgParamBean jgParamBean=new JgParamBean();
            jgParamBean.setPlatform("1");
            jgParamBean.setRegistrationId(JPushInterface.getRegistrationID(this));
            Log.e(Constant.TAG,JPushInterface.getRegistrationID(this)+"CowboyingApplication.registrationId");
            getRegisterIdPresenter.getJgRegisteId(reqData,jgParamBean);
        }

    }

    private void initEvent() {
        bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    token= Hawk.get(HawkKey.TOKEN);
                    if(TextUtils.isEmpty(token)){
                        startActivity(LoginActivity.class);
                        finish();
                    }
                    if(item.getItemId()==R.id.i_second){
                        Intent intent1=new Intent();
                        intent1.setAction("com.ibeef.cowboying.storeaddcar");
                        sendBroadcast(intent1);
                    }
                return true;
            }
        });

    }

    @Override
    public void showMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void getJgRegisteId(JgResultBean jgResultBean) {

    }

    @Override
    public void getCheckVersion(CheckVersionBean checkVersionBean) {
        if("1".equals(checkVersionBean.getBizData().getUpgrade())){

            if("1".equals(checkVersionBean.getBizData().getMust())){
                // 这里的提示框是我自定义的
                Intent intent=new Intent(MainActivity.this,UpdateVersionDialog.class);
                intent.putExtra("from","1");
                intent.putExtra("version",checkVersionBean.getBizData().getVersion());
                if(SDCardUtil.isNullOrEmpty(checkVersionBean.getBizData().getFileUrl())){
                    intent.putExtra("fileUrl","https://sj.qq.com/myapp/");
                }else {
                    intent.putExtra("fileUrl",checkVersionBean.getBizData().getFileUrl());
                }
                startActivity(intent);
            }else  if("0".equals(checkVersionBean.getBizData().getMust())){
                Intent intent=new Intent(MainActivity.this,UpdateVersionDialog.class);
                intent.putExtra("from","0");
                intent.putExtra("version",checkVersionBean.getBizData().getVersion());
                if(SDCardUtil.isNullOrEmpty(checkVersionBean.getBizData().getFileUrl())){
                    intent.putExtra("fileUrl","https://sj.qq.com/myapp/");
                }else {
                    intent.putExtra("fileUrl",checkVersionBean.getBizData().getFileUrl());
                }
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(checkVersionPresenter!=null){
            checkVersionPresenter.detachView();
        }
        if(getRegisterIdPresenter!=null){
            getRegisterIdPresenter.detachView();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断当点击的是返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();//退出方法
        }
        return true;
    }

    private long time = 0;

    //退出方法
    private void exit() {
        //如果在两秒大于2秒
        if (System.currentTimeMillis() - time > 2000) {
            //获得当前的时间
            time = System.currentTimeMillis();
            showToast("再点击一次退出应用程序");
        } else {
            //点击在两秒以内
            removeALLActivity();//执行移除所以Activity方法
        }
    }

}
