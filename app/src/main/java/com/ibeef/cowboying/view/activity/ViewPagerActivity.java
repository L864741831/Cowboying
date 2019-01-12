package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.application.CowboyingApplication;
import com.ibeef.cowboying.bean.OfflineStoreInfoResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.view.customview.HackyViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ViewPagerActivity extends AppCompatActivity {

    @Bind(R.id.view_pager)
    HackyViewPager viewPager;
    @Bind(R.id.rvs_id)
    RelativeLayout rvsId;
    @Bind(R.id.back_id)
    ImageView back_id;
    private static int index=0;
    private OfflineStoreInfoResultBean info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);
        info= (OfflineStoreInfoResultBean) getIntent().getSerializableExtra("info");
        viewPager.setAdapter(new SamplePagerAdapter());

        index=getIntent().getIntExtra("index",0);
        viewPager.setCurrentItem(index);
        rvsId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        back_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return info.getBizData().getStoreImages().size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            photoView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            RequestOptions options = new RequestOptions()
                    .skipMemoryCache(true)
                    .error(R.mipmap.jzsb)
                    //跳过内存缓存
                    ;
            Glide.with(CowboyingApplication.getInstance()).load(Constant.imageDomain+info.getBizData().getStoreImages().get(position)).apply(options).into(photoView);

            // Now just add PhotoView to ViewPager and return it
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
