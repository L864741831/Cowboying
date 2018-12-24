package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.MessegeListReslutBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.utils.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class MyMessegeListAdapter extends BaseQuickAdapter<MessegeListReslutBean.BizDataBean,BaseViewHolder> {
    private Context context;
    public MyMessegeListAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MessegeListReslutBean.BizDataBean item) {

        String times="";
        if(DateUtils.getTime(new Date()).equals(DateUtils.formatDate(item.getCreateTime(),DateUtils.TYPE_02))){
            times=DateUtils.formatDate(item.getCreateTime(),DateUtils.TYPE_03);
        }else {
            times=DateUtils.formatDate(item.getCreateTime(),DateUtils.TYPE_01);
        }
        helper.setText(R.id.time_show_id,times)
                .setText(R.id.title_txt_id,item.getTitle())
                .setText(R.id.info_txt_id,item.getContent());

        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .error(R.mipmap.jzsb)
                //跳过内存缓存
                ;
        ImageView imageView= helper.getView(R.id.show_img_id);
        Glide.with(mContext).load(Constant.imageDomain+item.getImageUrl()).apply(options).into(imageView);
    }
}
