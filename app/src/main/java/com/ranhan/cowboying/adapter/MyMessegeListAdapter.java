package com.ranhan.cowboying.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ranhan.cowboying.R;

import java.util.List;

import rxfamily.bean.BaseBean;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class MyMessegeListAdapter extends BaseQuickAdapter<BaseBean,BaseViewHolder> {
    private Context context;
    public MyMessegeListAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {

        helper.setText(R.id.time_show_id,"12:12")
                .setText(R.id.title_txt_id,"订单已提交，请尽快支付哟！")
                .setText(R.id.info_txt_id,"主人，您已提交认购安格斯牛一头，需支付5000，请在45分钟内完成支付哟！");

        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                //跳过内存缓存
                ;
        ImageView imageView= helper.getView(R.id.show_img_id);
        Glide.with(mContext).load(item.getMessage()).apply(options).into(imageView);
    }
}
