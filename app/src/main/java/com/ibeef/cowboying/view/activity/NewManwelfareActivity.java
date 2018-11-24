package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.UserInfoBase;
import com.ibeef.cowboying.bean.HomeBannerResultBean;
import com.ibeef.cowboying.bean.ModifyHeadResultBean;
import com.ibeef.cowboying.bean.ModifyNickResultBean;
import com.ibeef.cowboying.bean.RealNameReaultBean;
import com.ibeef.cowboying.bean.UserInfoResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.UserInfoPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

public class NewManwelfareActivity extends BaseActivity implements UserInfoBase.IView{

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.show_img_id)
    ImageView showImgId;
    @Bind(R.id.experience_btn_id)
    TextView experienceBtnId;
    private UserInfoPresenter userInfoPresenter;
    private UserInfoResultBean userInfoResultBean;
    private String token;
    private HomeBannerResultBean.BizDataBean.NewUserActivity infos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_manwelfare);
        ButterKnife.bind(this);
        init();
    }
    private void init(){
        info.setText("新手福利");
        userInfoPresenter=new UserInfoPresenter(this);
        infos= (HomeBannerResultBean.BizDataBean.NewUserActivity) getIntent().getSerializableExtra("infos");
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .error(R.mipmap.jzsb)
                //跳过内存缓存
                ;
        Glide.with(this).load(Constant.imageDomain+infos.getDescribe()).apply(options).into(showImgId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        token= Hawk.get(HawkKey.TOKEN);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        userInfoPresenter.getUserInfo(reqData);
    }

    @OnClick({R.id.back_id, R.id.experience_btn_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.experience_btn_id:
                //  先判断是否绑定手机号，（未绑定手机号去绑定，），
                // 已绑定手机号再判断是否实名认证 ，已认证挑已认证的界面，未认证调到实名认证的界面
                //当前类型（1：可以认领；2：即将开始；3：领完）
                if(TextUtils.isEmpty(token)){
                    startActivity(LoginActivity.class);
                }else {
                    if(!SDCardUtil.isNullOrEmpty(userInfoResultBean)){
                        if(SDCardUtil.isNullOrEmpty(userInfoResultBean.getBizData().getMobile())){
                            Intent intent2=new Intent(NewManwelfareActivity.this,MobileLoginActivity.class);
                            intent2.putExtra("stadus","7");
                            startActivity(intent2);
                        }else {
                            if("0".equals(userInfoResultBean.getBizData().getIsValidate())){
                                //未实名认证
                                Intent intent1=new Intent(NewManwelfareActivity.this,ClaimUnCertificationActivity.class);
                                intent1.putExtra("mobile",userInfoResultBean.getBizData().getMobile());
                                intent1.putExtra("schemeId",infos.getSchemeId());
                                intent1.putExtra("quantity",1);
                                intent1.putExtra("userId",userInfoResultBean.getBizData().getUserId());
                                startActivity(intent1);
                            }else {
                                //已实名认证
                                Intent intent1=new Intent(NewManwelfareActivity.this,ClaimCertificationActivity.class);
                                intent1.putExtra("infos",userInfoResultBean);
                                intent1.putExtra("schemeId",infos.getSchemeId());
                                intent1.putExtra("quantity",1);
                                startActivity(intent1);
                            }
                        }
                    }

                    }
                break;
            default:
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getModifyHead(ModifyHeadResultBean modifyHeadResultBean) {

    }

    @Override
    public void getModifNick(ModifyNickResultBean modifyNickResultBean) {

    }

    @Override
    public void getRealName(RealNameReaultBean realNameReaultBean) {

    }

    @Override
    public void getUserInfo(UserInfoResultBean userInfoResultBean) {
        if("000000".equals(userInfoResultBean.getCode())){
            this.userInfoResultBean=userInfoResultBean;
        }else {
            showToast(userInfoResultBean.getMessage());
        }
    }

    @Override
    public void isTakePhoeto(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        if(userInfoPresenter!=null){
            userInfoPresenter.detachView();
        }
        super.onDestroy();
    }
}
