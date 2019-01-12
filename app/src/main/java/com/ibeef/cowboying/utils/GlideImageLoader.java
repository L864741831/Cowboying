package com.ibeef.cowboying.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.view.customview.GlideRoundTransform;
import com.youth.banner.loader.ImageLoader;


public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        RequestOptions options1 = new RequestOptions()
                .centerCrop()
//                .placeholder(R.mipmap.jzsb)//预加载图片
                .error(R.mipmap.jzsb)//加载失败显示图片
                .priority(Priority.HIGH)//优先级
                .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存策略
                .transform(new GlideRoundTransform(10));//转化为圆角
        Glide.with(context).load(path).apply(options1).into(imageView);
    }
}
