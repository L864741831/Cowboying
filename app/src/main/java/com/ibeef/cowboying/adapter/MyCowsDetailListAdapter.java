package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;

import java.util.List;

import rxfamily.bean.BaseBean;

/**
 *
 * @author Administrator*/
public class MyCowsDetailListAdapter extends BaseQuickAdapter<BaseBean,BaseViewHolder> {
    private Context context;
    public MyCowsDetailListAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {

        helper.setText(R.id.store_name,"耳标号：待分配")
                .setText(R.id.tv_ranch_id,"Ben  Nwvis牧场")
                .setText(R.id.money_id,"安格拉斯牛")
                .setText(R.id.num_id,"×2");

        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                //跳过内存缓存
                ;
        ImageView imageView= helper.getView(R.id.store_img);
//        Glide.with(mContext).load(item.getMessage()).apply(options).into(imageView);
    }
}
