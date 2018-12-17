package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.HomeBannerResultBean;
import com.ibeef.cowboying.config.Constant;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

public class GivePoursActivity extends BaseActivity {

    @Bind(R.id.home_honegivepour)
    ImageView homeHonegivepour;
    @Bind(R.id.img_close_id)
    ImageView imgCloseId;
    private HomeBannerResultBean info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_pour);
        ButterKnife.bind(this);
        info= (HomeBannerResultBean) getIntent().getSerializableExtra("info");
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                //跳过内存缓存
                ;
        Glide.with(this).load(Constant.imageDomain+info.getBizData().getPopBanner().getImageUrl()).apply(options).into(homeHonegivepour);
    }

    @OnClick({R.id.home_honegivepour, R.id.img_close_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_honegivepour:
                switch (info.getBizData().getPopBanner().getPageUrl()){
                    case "adopt_scheme_list":
                        //买牛方案列表
                        startActivity(BuyCowsPlanActivity.class);
                        break;
                    case "adopt_scheme_detail":
                        //买牛方案详情
                        break;
                    case "adop_order_list":
                        //买牛订单列表
                        startActivity(MyCowsActivity.class);
                        break;
                    case "adop_order_detail":
                        //买牛订单详情
                        Intent intent = new Intent(GivePoursActivity.this, MyCowsDetailActivity.class);
                        intent.putExtra("orderId",info.getBizData().getPopBanner().getParams()+"");
                        startActivity(intent);
                        break;
                    case "pasture_list":
                        //合作牧场列表
                        startActivity(RanchConsociationActivity.class);
                        break;
                    case "shop_product_list":
                        //商城商品列表
                        Intent intent1=new Intent(GivePoursActivity.this,MainActivity.class);
                        intent1.putExtra("index",1);
                        startActivity(intent1);
                        break;
                    case "shop_order_list":
                        //商城订单列表
                        startActivity(MyOrderActivity.class);
                        break;
                    case "shop_order_detail":
                        //商城订单详情
                        Intent intent2 = new Intent(GivePoursActivity.this, MyOrderDetailActivity.class);
                        intent2.putExtra("orderId",info.getBizData().getPopBanner().getParams()+"");
                        startActivity(intent2);
                        break;
                    case "total_assets":
                        //总资产
                        startActivity(MyAllMoneyActivity.class);
                        break;
                    case "coupon_list":
                        //优惠券列表
                        startActivity(DiscountCouponActivity.class);
                        break;
                    case "contract_list":
                        //合同列表
                        startActivity(MyContractActivity.class);
                        break;
                    case "new_welfare":
                        //新人福利
                        Intent intent3=new Intent(GivePoursActivity.this,NewManwelfareActivity.class);
                        intent3.putExtra("infos",info.getBizData().getNewUserActivity());
                        startActivity(intent3);
                        break;
                    default:
                        break;
                }
                finish();
                break;
            case R.id.img_close_id:
                finish();
                break;
            default:
                break;
        }
    }
}
