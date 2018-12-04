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

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class StoreSureOrderAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {
    private Context context;
    public StoreSureOrderAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, Object item) {

        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .error(R.mipmap.cowbeefimg)
                //跳过内存缓存
                ;
        Glide.with(context).load(Constant.imageDomain).apply(options).into((ImageView) helper.getView(R.id.store_img));
        helper.setText(R.id.store_name,"牛胸肉")
                .setText(R.id.type_id,"20克X2快装")
                .setText(R.id.money_id,"￥1")
                .setText(R.id.num_id,"X1");
    }
}
