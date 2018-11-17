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

public class GivePoursActivity extends AppCompatActivity {

    @Bind(R.id.home_honegivepour)
    ImageView homeHonegivepour;
    @Bind(R.id.img_close_id)
    ImageView imgCloseId;
    private HomeBannerResultBean.BizDataBean.PopBannerResDtoBean info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_pour);
        ButterKnife.bind(this);
        info= (HomeBannerResultBean.BizDataBean.PopBannerResDtoBean) getIntent().getSerializableExtra("info");
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                //跳过内存缓存
                ;
        Glide.with(this).load(Constant.imageDomain+info.getImageUrl()).apply(options).into(homeHonegivepour);
    }

    @OnClick({R.id.home_honegivepour, R.id.img_close_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_honegivepour:
                Intent intent=new Intent(GivePoursActivity.this, AdWebviewActivity.class);
                intent.putExtra("url",info.getLinkUrl());
                intent.putExtra("title","口袋牧场");
                startActivity(intent);
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
