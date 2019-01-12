package com.ibeef.cowboying.adapter;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.HomeAllVideoResultBean;
import com.ibeef.cowboying.view.customview.GlideRoundTransformAll;

import java.util.List;


public class RanchDynamicdapter extends BaseQuickAdapter<HomeAllVideoResultBean.BizDataBean, BaseViewHolder> {
    private Context context;
    public RanchDynamicdapter(List data, Context context) {
        super(R.layout.item_ranch_dynamic_list, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final HomeAllVideoResultBean.BizDataBean item) {

        helper.setText(R.id.name_id,item.getName())
                .setText(R.id.time_id,item.getCreateTime());
        ImageView imageView= helper.getView(R.id.img_id);
        RequestOptions options1 = new RequestOptions()
                .centerCrop()
//                .placeholder(R.mipmap.jzsb)//预加载图片
                .error(R.mipmap.jzsb)//加载失败显示图片
                .priority(Priority.HIGH)//优先级
                .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存策略
                .transform(new GlideRoundTransformAll(10));//转化为圆角
        Glide.with(context).load(item.getCoverUrl()).apply(options1).into(imageView);
    }
}

