package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.StoreInfoListResultBean;
import com.ibeef.cowboying.config.Constant;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class StoreTopAdapter extends BaseQuickAdapter<StoreInfoListResultBean.BizDataBean,BaseViewHolder> {
    private Context context;
    public StoreTopAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final StoreInfoListResultBean.BizDataBean item) {

        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .error(R.mipmap.cowbeefimg)
                //跳过内存缓存
                ;

        ImageView goods_info_img=helper.getView(R.id.goods_info_img);
        if (item.getProductImages().size() >= 1) {
            Glide.with(context).load(Constant.imageDomain + item.getProductImages().get(0).getImageUrl()).apply(options).into((ImageView) helper.getView(R.id.goods_info_img));
        }else {
            goods_info_img.setImageResource(R.mipmap.cowbeefimg);
        }
    }
}
