package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;


import com.ibeef.cowboying.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 第一次启动的引导页
 */
public class GuideActivity extends BaseActivity {

    @Bind(R.id.vp_guide)
    ViewPager vpGuide;
    @Bind(R.id.iv_start)
    ImageView ivStart;
    private List<ImageView> imgs;
    private int[] imgIds = new int[]{R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        init();
    }

    public void init(){
        imgs = new ArrayList<ImageView>();
        for (int i = 0; i < imgIds.length; i++) {
            ImageView img = new ImageView(this);
            img.setBackgroundResource(imgIds[i]);
            imgs.add(img);
        }
        vpGuide.setAdapter(new MyPagerAdapter(imgs));
        initListener();
    }

    private void initListener() {

        vpGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == imgs.size() - 1) {
                    ivStart.setVisibility(View.VISIBLE);
                } else {
                    ivStart.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }


    class MyPagerAdapter extends PagerAdapter {

        private List<ImageView> imgs;

        public MyPagerAdapter(List<ImageView> imgs) {
            this.imgs = imgs;
        }

        @Override
        public int getCount() {
            return imgs.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = imgs.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imgs.get(position));
        }
    }
    @Override
    public void onBackPressed() {
        finish();
    }

    @OnClick(R.id.iv_start)
    public void onViewClicked() {
        Intent intent = new Intent(GuideActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
