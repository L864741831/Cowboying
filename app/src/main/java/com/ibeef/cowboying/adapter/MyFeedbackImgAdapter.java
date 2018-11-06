package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.config.Constant;


import java.util.List;

import rxfamily.bean.BaseBean;

/**
 * @author ls
 * @date on 2018/10/23 17:16
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class MyFeedbackImgAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    private Context mContext;
    public MyFeedbackImgAdapter(Context context, List<String> list, int layout){
        super(layout, list);
        this.mContext=context;
    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {

        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                //跳过内存缓存
                ;
        ImageView imageView= helper.getView(R.id.upload_img);
        Glide.with(mContext).load(Constant.imageDomain+item).apply(options).into(imageView);
    }

}
