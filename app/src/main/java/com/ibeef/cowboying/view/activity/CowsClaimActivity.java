package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MainFragmentAdapter;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.view.customview.AmountView;
import com.ibeef.cowboying.view.fragment.CowClaimBuyWayFragment;
import com.ibeef.cowboying.view.fragment.CowClaimDesFragment;
import com.ibeef.cowboying.view.fragment.CowClaimSelectFragment;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;
import rxfamily.view.BaseFragment;

/**
 * 买牛方案》》》》认领界面
 */
public class CowsClaimActivity extends BaseActivity {

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
    RelativeLayout seeAllPastureRv;
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
    private static final String[] CHANNELS = new String[]{"项目介绍", "买牛方案", "牛只查询"};
    @Bind(R.id.custom_img_id)
    ImageView customImgId;
    @Bind(R.id.amout_num_id)
    AmountView amoutNumId;
    @Bind(R.id.all_money_id)
    TextView allMoneyId;
    @Bind(R.id.now_claim_btn_id)
    TextView nowClaimBtnId;
    private List<String> dataBeen = Arrays.asList(CHANNELS);
    private ArrayList<BaseFragment> fragmentList;
    private MainFragmentAdapter mainFragmentAdapter;
    private CowClaimDesFragment cowClaimDesFragment;
    private CowClaimBuyWayFragment cowClaimBuyWayFragment;
    private CowClaimSelectFragment cowClaimSelectFragment;
    private int allAmout=1;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cows_claim);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("认领");
        fragmentList = new ArrayList<>();
        token= Hawk.get(HawkKey.TOKEN);
        initMagicIndicator();
    }

    private void initMagicIndicator() {
        cowClaimDesFragment = CowClaimDesFragment.newInstance(dataBeen.get(0));
        cowClaimBuyWayFragment = CowClaimBuyWayFragment.newInstance(dataBeen.get(1));
        cowClaimSelectFragment = CowClaimSelectFragment.newInstance(dataBeen.get(2));
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
        amoutNumId.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                allAmout=amount;

            }
        });

    }

    @OnClick({R.id.back_id, R.id.see_all_pasture_rv,R.id.custom_img_id, R.id.now_claim_btn_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.see_all_pasture_rv:
                Intent intent = new Intent(CowsClaimActivity.this, ChooseParsterActivity.class);
                intent.putExtra("id", 18);
                startActivity(intent);
                break;
            case R.id.custom_img_id:
                showToast("客服");
                break;
            case R.id.now_claim_btn_id:
                // TODO: 2018/11/7 先判断是否绑定手机号，（未绑定手机号去绑定，），
                // 已绑定手机号再判断是否实名认证 ，已认证挑已认证的界面，未认证调到实名认证的界面
//                Intent intent2=new Intent(CowsClaimActivity.this,MobileLoginActivity.class);
//                intent2.putExtra("stadus","7");
//                startActivity(intent2);
                if(TextUtils.isEmpty(token)){
                    startActivity(LoginActivity.class);
                }else {
                    if(true){
                        //已实名认证
                        startActivity(ClaimCertificationActivity.class);
                    }else {
                        //未实名认证
                        startActivity(ClaimUnCertificationActivity.class);
                    }
                }
                break;
            default:
                break;
        }
    }
}
