package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.HomeVideoResultBean;
import com.ibeef.cowboying.config.Constant;

import java.util.List;

import rxfamily.bean.BaseBean;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class RanchDynamicsAdapter extends BaseQuickAdapter<HomeVideoResultBean.BizDataBean,BaseViewHolder> {
    private Context context;
    private List<HomeVideoResultBean.BizDataBean> beanList;
    public RanchDynamicsAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
        this.beanList=data;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeVideoResultBean.BizDataBean item) {
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                //跳过内存缓存
                ;
        ImageView imageView= helper.getView(R.id.show_img_id);

        if(helper.getAdapterPosition()==beanList.size()-1){
            imageView.setImageResource(R.mipmap.more);
        }else {
            Glide.with(mContext).load(item.getCoverUrl()).apply(options).into(imageView);
        }
    }
}
