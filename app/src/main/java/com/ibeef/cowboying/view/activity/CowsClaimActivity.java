package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MainFragmentAdapter;
import com.ibeef.cowboying.base.PastureDetailBase;
import com.ibeef.cowboying.base.UserInfoBase;
import com.ibeef.cowboying.bean.JionPersonInfoResultBean;
import com.ibeef.cowboying.bean.ModifyHeadResultBean;
import com.ibeef.cowboying.bean.ModifyNickResultBean;
import com.ibeef.cowboying.bean.RealNameReaultBean;
import com.ibeef.cowboying.bean.SchemeDetailReultBean;
import com.ibeef.cowboying.bean.UserInfoResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.PastureDetailPresenter;
import com.ibeef.cowboying.presenter.UserInfoPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.ibeef.cowboying.view.customview.AmountView;
import com.ibeef.cowboying.view.customview.SuperSwipeRefreshLayout;
import com.ibeef.cowboying.view.fragment.CowClaimBuyWayFragment;
import com.ibeef.cowboying.view.fragment.CowClaimDesFragment;
import com.ibeef.cowboying.view.fragment.CowClaimSelectFragment;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;
import rxfamily.view.BaseFragment;

/**
 * 养牛方案》》》》认领界面
 */
public class CowsClaimActivity extends BaseActivity implements SuperSwipeRefreshLayout.OnPullRefreshListener,PastureDetailBase.IView, UserInfoBase.IView {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.banner_img_id)
    ImageView bannerImgId;
    @Bind(R.id.parsture_name_id)
    TextView parstureNameId;
    @Bind(R.id.see_all_pasture_rv)
    TextView seeAllPastureRv;
    @Bind(R.id.target_txt_id)
    TextView targetTxtId;
    @Bind(R.id.hasidentify_txt_id)
    TextView hasidentifyTxtId;
    @Bind(R.id.price_txt_id)
    TextView priceTxtId;
    @Bind(R.id.isShows_lv_id)
    LinearLayout isShowsLvId;
    @Bind(R.id.nts_top)
    NavigationTabStrip ntsTop;
    @Bind(R.id.content_vp)
    ViewPager contentVp;
    @Bind(R.id.img_id)
    ImageView img_id;
    private static final String[] CHANNELS = new String[]{"项目介绍", "养牛方案", "牛只查询"};
    @Bind(R.id.custom_img_id)
    ImageView customImgId;
    @Bind(R.id.amout_num_id)
    AmountView amoutNumId;
    @Bind(R.id.all_money_id)
    TextView allMoneyId;
    @Bind(R.id.now_claim_btn_id)
    ImageView nowClaimBtnId;
    @Bind(R.id.scroll_isShow_id)
    TextView scrollIsShowId;
    @Bind(R.id.rv_middle_id)
    RelativeLayout rvMiddleId;
//    @Bind(R.id.swipe_ly)
//    SuperSwipeRefreshLayout swipeLy;
    private List<String> dataBeen = Arrays.asList(CHANNELS);
    private ArrayList<BaseFragment> fragmentList;
    private MainFragmentAdapter mainFragmentAdapter;
    private CowClaimDesFragment cowClaimDesFragment;
    private CowClaimBuyWayFragment cowClaimBuyWayFragment;
    private CowClaimSelectFragment cowClaimSelectFragment;
    private int allAmout=1;
    private String token;
    private int schemId;
    private PastureDetailPresenter pastureDetailPresenter;
    private UserInfoPresenter userInfoPresenter;
    private UserInfoResultBean userInfoResultBean;
    private SchemeDetailReultBean schemeDetailReultBean;
    private boolean isAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cows_claim);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        isAd=getIntent().getBooleanExtra("isAd",false);
        info.setText("认领");
        fragmentList = new ArrayList<>();
        token= Hawk.get(HawkKey.TOKEN);

        scrollIsShowId.setSelected(true);
        schemId= getIntent().getIntExtra("schemId",0);
        pastureDetailPresenter=new PastureDetailPresenter(this);
        userInfoPresenter=new UserInfoPresenter(this);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        pastureDetailPresenter.getSchemeDetail(reqData,schemId);

//        swipeLy.setHeaderViewBackgroundColor(getResources().getColor(R.color.colorAccent));
//        swipeLy.setHeaderView(createHeaderView());// add headerView
//        swipeLy.setTargetScrollWithLayout(true);
//        swipeLy.setOnPullRefreshListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        userInfoPresenter.getUserInfo(reqData);
    }

    private void initMagicIndicator(SchemeDetailReultBean schemeDetailReultBean) {
        cowClaimDesFragment = CowClaimDesFragment.newInstance(schemeDetailReultBean.getBizData().getProjectDescribe());
        cowClaimBuyWayFragment = CowClaimBuyWayFragment.newInstance(schemeDetailReultBean);
        cowClaimSelectFragment = CowClaimSelectFragment.newInstance(schemeDetailReultBean.getBizData().getCode(),schemeDetailReultBean.getBizData().getSchemeId());
        fragmentList.add(cowClaimDesFragment);
        fragmentList.add(cowClaimBuyWayFragment);
        fragmentList.add(cowClaimSelectFragment);
        mainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager(), fragmentList);
        contentVp.setAdapter(mainFragmentAdapter);

        ntsTop.setViewPager(contentVp, 0);
        ntsTop.setTitles(CHANNELS);
        ntsTop.setStripType(NavigationTabStrip.StripType.LINE);
        ntsTop.setStripGravity(NavigationTabStrip.StripGravity.BOTTOM);
        ntsTop.setTabIndex(0, true);
        amoutNumId.intEdit("1");
        amoutNumId.setGoods_storage(1000000);
    }

    @OnClick({R.id.back_id, R.id.see_all_pasture_rv,R.id.custom_img_id, R.id.now_claim_btn_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                if(isAd){
                    removeALLActivity();
                    startActivity(new Intent(CowsClaimActivity.this, MainActivity.class));
                }
                finish();
                break;
            case R.id.see_all_pasture_rv:
                if(SDCardUtil.isNullOrEmpty(schemeDetailReultBean)){
                    return;
                }
                Intent intent = new Intent(CowsClaimActivity.this, ChooseParsterActivity.class);
                intent.putExtra("id", schemeDetailReultBean.getBizData().getPastureId());
                intent.putExtra("name", schemeDetailReultBean.getBizData().getPastureName());
                startActivity(intent);
                break;
            case R.id.custom_img_id:
                showToast("客服");
                break;
            case R.id.now_claim_btn_id:
                if(SDCardUtil.isNullOrEmpty(schemeDetailReultBean)){
                    return;
                }
                //  先判断是否绑定手机号，（未绑定手机号去绑定，），
                // 已绑定手机号再判断是否实名认证 ，已认证挑已认证的界面，未认证调到实名认证的界面
                if("1".equals(schemeDetailReultBean.getBizData().getCurStatus())){
                    //当前类型（1：可以认领；2：即将开始；3：领完）
                    if(TextUtils.isEmpty(token)){
                        startActivity(LoginActivity.class);
                    }else {
                        if(!SDCardUtil.isNullOrEmpty(userInfoResultBean)){
                            if(SDCardUtil.isNullOrEmpty(userInfoResultBean.getBizData().getMobile())){
                                Intent intent2=new Intent(CowsClaimActivity.this,MobileLoginActivity.class);
                                intent2.putExtra("stadus","7");
                                startActivity(intent2);
                            }else {
                                if("0".equals(userInfoResultBean.getBizData().getIsValidate())){
                                    //未实名认证
                                    Intent intent1=new Intent(CowsClaimActivity.this,ClaimUnCertificationActivity.class);
                                    intent1.putExtra("mobile",userInfoResultBean.getBizData().getMobile());
                                    intent1.putExtra("schemeId", schemeDetailReultBean.getBizData().getSchemeId());
                                    intent1.putExtra("quantity",allAmout);
                                    intent1.putExtra("userId",userInfoResultBean.getBizData().getUserId());
                                    startActivity(intent1);
                                }else {
                                    //已实名认证
                                    Intent intent1=new Intent(CowsClaimActivity.this,ClaimCertificationActivity.class);
                                    intent1.putExtra("infos",userInfoResultBean);
                                    intent1.putExtra("schemeId", schemeDetailReultBean.getBizData().getSchemeId());
                                    intent1.putExtra("quantity",allAmout);
                                    startActivity(intent1);
                                }
                            }
                        }

                    }
                }else  if("2".equals(schemeDetailReultBean.getBizData().getCurStatus())) {
                    showToast("即将开售");
                }else  if("3".equals(schemeDetailReultBean.getBizData().getCurStatus())) {
                    showToast("已售罄");
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
    public void getJionPersonInfo(JionPersonInfoResultBean jionPersonInfoResultBean) {

    }

    @Override
    public void getSchemeDetail(final SchemeDetailReultBean schemeDetailReultBean) {

        if("000000".equals(schemeDetailReultBean.getCode())){
            this.schemeDetailReultBean=schemeDetailReultBean;
            allMoneyId.setText("总金额：￥"+schemeDetailReultBean.getBizData().getPrice());
            targetTxtId.setText(schemeDetailReultBean.getBizData().getTotalStock()+"头");
            Double d1 = new Double(schemeDetailReultBean.getBizData().getPrice());
            int i1 = d1.intValue();
            priceTxtId.setText(i1+"元/头");
            hasidentifyTxtId.setText((schemeDetailReultBean.getBizData().getTotalStock()-schemeDetailReultBean.getBizData().getStock())+"头");
            parstureNameId.setText(schemeDetailReultBean.getBizData().getPastureName());

            initMagicIndicator(schemeDetailReultBean);

            RequestOptions options = new RequestOptions()
                    .skipMemoryCache(true)
                    .error(R.mipmap.jzsb)
                    //跳过内存缓存
                    ;
            Glide.with(CowsClaimActivity.this).load(Constant.imageDomain+schemeDetailReultBean.getBizData().getImageUrl()).apply(options).into(bannerImgId);
            amoutNumId.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
                @Override
                public void onAmountChange(View view, int amount) {
                    allAmout=amount;
                   allMoneyId.setText("总金额：￥"+amount* schemeDetailReultBean.getBizData().getPrice());
                }
            });

            if("1".equals(schemeDetailReultBean.getBizData().getCurStatus())){
                //当前类型（1：可以认领；2：即将开始；3：领完）
                nowClaimBtnId.setImageResource(R.mipmap.nowclaim);
                rvMiddleId.setVisibility(View.GONE);
            }else  if("2".equals(schemeDetailReultBean.getBizData().getCurStatus())) {
                nowClaimBtnId.setImageResource(R.mipmap.claimwillopen);
                rvMiddleId.setVisibility(View.VISIBLE);
                scrollIsShowId.setText("开售时间："+ schemeDetailReultBean.getBizData().getStartTime());
            }else  if("3".equals(schemeDetailReultBean.getBizData().getCurStatus())) {
                nowClaimBtnId.setImageResource(R.mipmap.claimend);
                rvMiddleId.setVisibility(View.VISIBLE);
                scrollIsShowId.setText("您来晚了，当前养牛方案牛只已售罄，不过还有牧场主未付款，您还有机会哟~");
            }else  if(SDCardUtil.isNullOrEmpty(schemeDetailReultBean.getBizData().getCurStatus())) {
                nowClaimBtnId.setImageResource(R.mipmap.claimend);
                rvMiddleId.setVisibility(View.VISIBLE);
                scrollIsShowId.setText("您来晚了，当前养牛方案牛只已售罄，不过还有牧场主未付款，您还有机会哟~");
            }
        }else {
            showToast(schemeDetailReultBean.getMessage());
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
//        swipeLy.setRefreshing(false);
    }

    @Override
    protected void onDestroy() {
        if(pastureDetailPresenter!=null){
            pastureDetailPresenter.detachView();
        }
        if(userInfoPresenter!=null){
            userInfoPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(isAd){
            removeALLActivity();
            startActivity(new Intent(CowsClaimActivity.this, MainActivity.class));
        }
        finish();
    }

    @Override
    public void onRefresh() {
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        pastureDetailPresenter.getSchemeDetail(reqData,schemId);
    }

    @Override
    public void onPullDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {

    }
}
