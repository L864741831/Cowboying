package com.ibeef.cowboying.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.HomeBannerBase;
import com.ibeef.cowboying.bean.HomeAdResultBean;
import com.ibeef.cowboying.bean.HomeAllVideoResultBean;
import com.ibeef.cowboying.bean.HomeBannerResultBean;
import com.ibeef.cowboying.bean.HomeSellCowNumResultBean;
import com.ibeef.cowboying.bean.HomeVideoResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.HomeBannerPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 广告页
 */
public class AdActivity extends BaseActivity implements HomeBannerBase.IView{

    @Bind(R.id.bg_img)
    ImageView bgImg;
    @Bind(R.id.go_main_activity)
    RelativeLayout goMainActivity;
    @Bind(R.id.go_txt_show)
    TextView goTxtShow;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    int timeCount = 0,countTime=6;
    boolean continueCount = true;
    private HomeAdResultBean info;
    private HomeBannerPresenter homeBannerPresenter;
    private HomeBannerResultBean homeBannerResultBean;
    private Map<String, String> reqData;
    private String token;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @SuppressWarnings("unused")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (continueCount) {
                countNum();
                goTxtShow.setText("跳过("+countTime+"s)");
                handler.sendMessageDelayed(handler.obtainMessage(-1),1000);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        ButterKnife.bind(this);

        token= Hawk.get(HawkKey.TOKEN);
        reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        homeBannerPresenter=new HomeBannerPresenter(this);
        homeBannerPresenter.getHomeBanner(reqData);

        handler.sendMessageDelayed(handler.obtainMessage(-1),1000);
        info= (HomeAdResultBean) getIntent().getSerializableExtra("info");


        RequestOptions options = new RequestOptions()
                .error(R.mipmap.startup)
                //加载错误之后的错误图
                .skipMemoryCache(true)
                //跳过内存缓存
                ;

        Glide.with(this).load(Constant.imageDomain+info.getBizData().getImageUrl()).apply(options).into(bgImg);
    }

    @OnClick({R.id.bg_img, R.id.go_main_activity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bg_img:
                // url广告webview地址，广告title
                if(!TextUtils.isEmpty(info.getBizData().getPageUrl())){
                    continueCount = false;
                    if(TextUtils.isEmpty(info.getBizData().getPageUrl())){
                        return;
                    }
                    if(info.getBizData().getPageUrl().contains("http")){
                        Intent intent1=new Intent(AdActivity.this,AdWebviewActivity.class);
                        intent1.putExtra("url",info.getBizData().getPageUrl());
                        intent1.putExtra("title","");
                        startActivity(intent1);
                    }else {
                        switch (info.getBizData().getPageUrl()) {
                            case "adopt_scheme_list":
                                //养牛方案列表
                                Intent intent = new Intent(AdActivity.this, BuyCowsPlanActivity.class);
                                intent.putExtra("isAd", true);
                                startActivity(intent);
                                break;
                            case "adopt_scheme_detail":
                                //养牛方案详情
                                if(!TextUtils.isEmpty(info.getBizData().getParams())){
                                    Intent intent4 = new Intent(AdActivity.this, CowsClaimActivity.class);
                                    intent4.putExtra("schemId", Integer.parseInt(info.getBizData().getParams()));
                                    intent4.putExtra("isAd", true);
                                    startActivity(intent4);
                                }
                                break;
                            case "adopt_order_list":
                                //养牛订单列表
                                Intent intent2 = new Intent(AdActivity.this, MyCowsActivity.class);
                                intent2.putExtra("isAd", true);
                                startActivity(intent2);
                                break;
                            case "adopt_order_detail":
                                //养牛订单详情
                                if(!TextUtils.isEmpty(info.getBizData().getParams())){
                                    Intent intent3 = new Intent(AdActivity.this, MyCowsDetailActivity.class);
                                    intent3.putExtra("orderId", info.getBizData().getParams() + "");
                                    intent3.putExtra("isAd", true);
                                    startActivity(intent3);
                                }
                                break;
                            case "pasture_list":
                                //合作牧场列表
                                Intent intent5 = new Intent(AdActivity.this, RanchConsociationActivity.class);
                                intent5.putExtra("isAd", true);
                                startActivity(intent5);
                                break;
                            case "shop_product_list":
                                //商城商品列表
                                Intent intent1 = new Intent(AdActivity.this, MainActivity.class);
                                intent1.putExtra("index", 1);
                                startActivity(intent1);
                                break;
                            case "shop_order_list":
                                //商城订单列表
                                Intent intent6 = new Intent(AdActivity.this, MyOrderActivity.class);
                                intent6.putExtra("isAd", true);
                                startActivity(intent6);
                                break;
                            case "shop_order_detail":
                                //商城订单详情
                                if(!TextUtils.isEmpty(info.getBizData().getParams())){
                                    Intent intent7 = new Intent(AdActivity.this, MyOrderDetailActivity.class);
                                    intent7.putExtra("orderId", info.getBizData().getParams() + "");
                                    intent7.putExtra("isAd", true);
                                    startActivity(intent7);
                                }
                                break;
                            case "total_assets":
                                //总资产
                                Intent intent8 = new Intent(AdActivity.this, MyAllMoneyActivity.class);
                                intent8.putExtra("isAd", true);
                                startActivity(intent8);
                                break;
                            case "coupon_list":
                                //优惠券列表
                                Intent intent9 = new Intent(AdActivity.this, DiscountCouponActivity.class);
                                intent9.putExtra("isAd", true);
                                startActivity(intent9);
                                break;
                            case "contract_list":
                                //合同列表
                                Intent intent10 = new Intent(AdActivity.this, MyContractActivity.class);
                                intent10.putExtra("isAd", true);
                                startActivity(intent10);
                                break;
                            case "vip_card_detail":
                                //会员卡详情
                                startActivity(VipCardActivity.class);
                                break;
                            case "new_welfare":
                                //新人福利
                                if (!SDCardUtil.isNullOrEmpty(homeBannerResultBean)) {
                                    Intent intent11 = new Intent(AdActivity.this, NewManwelfareActivity.class);
                                    intent11.putExtra("infos", homeBannerResultBean.getBizData().getNewUserActivity());
                                    startActivity(intent11);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    finish();
                }else {
                    showToast("暂无广告详情~");
                }
                break;
            case R.id.go_main_activity:
                continueCount = false;
                startActivity(new Intent(AdActivity.this, MainActivity.class));
                finish();
                break;
            default:
                break;
        }
    }

    private int countNum() {//数秒
        timeCount++;
        countTime--;
        if(timeCount==5){
            continueCount = false;
            //超过三秒进入下个页面
            startActivity(new Intent(AdActivity.this, MainActivity.class));
            finish();
        }
        return timeCount;
    }

    @Override
    public void onBackPressed() {
        continueCount = false;
        startActivity(new Intent(AdActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getHomeBanner(HomeBannerResultBean homeBannerResultBean) {
        this.homeBannerResultBean=homeBannerResultBean;
    }

    @Override
    public void getHomeVideo(HomeVideoResultBean homeAdResultBean) {

    }

    @Override
    public void getHomeSellCowsNum(HomeSellCowNumResultBean homeSellCowNumResultBean) {

    }

    @Override
    public void getAllVideo(HomeAllVideoResultBean homeAllVideoResultBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(homeBannerPresenter!=null){
            homeBannerPresenter.detachView();
        }
    }
}
