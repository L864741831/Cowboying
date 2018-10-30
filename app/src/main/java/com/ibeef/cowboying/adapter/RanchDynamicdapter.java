package com.ibeef.cowboying.adapter;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.HomeAllVideoResultBean;
import com.ibeef.cowboying.bean.HomeVideoResultBean;

import java.util.List;


public class RanchDynamicdapter extends BaseQuickAdapter<HomeAllVideoResultBean.BizDataBean, BaseViewHolder> {
    private Context context;
    public RanchDynamicdapter(List data, Context context) {
        super(R.layout.item_ranch_dynamic_list, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final HomeAllVideoResultBean.BizDataBean item) {

        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                //跳过内存缓存
                ;
        ImageView imageView= helper.getView(R.id.img_id);
        Glide.with(mContext).load(item.getCoverUrl()).apply(options).into(imageView);
        helper.setText(R.id.name_id,item.getName())
                .setText(R.id.time_id,item.getCreateTime());
    }
}

