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
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class RanchDynamicsAdapter extends BaseQuickAdapter<BaseBean,BaseViewHolder> {
    private Context context;
    private List<BaseBean> beanList;
    public RanchDynamicsAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
        this.beanList=data;
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                //跳过内存缓存
                ;
        ImageView imageView= helper.getView(R.id.show_img_id);
//        Glide.with(mContext).load(item.getMessage()).apply(options).into(imageView);
        if(helper.getAdapterPosition()==beanList.size()-1){
            imageView.setImageResource(R.mipmap.more);
        }
    }
}
