package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.StoreInfoListResultBean;
import com.ibeef.cowboying.utils.SDCardUtil;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class StoreBottomAdapter extends BaseQuickAdapter<StoreInfoListResultBean.BizDataBean.ProductVideoResVosBean,BaseViewHolder> {
    private Context context;
    public StoreBottomAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreInfoListResultBean.BizDataBean.ProductVideoResVosBean item) {
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .error(R.mipmap.cowbeefimg)
                //跳过内存缓存
                ;
        Glide.with(context).load(item.getVideoCoverUrl()).apply(options).into((ImageView) helper.getView(R.id.show_img_id));
        if(SDCardUtil.isNullOrEmpty(item.getName())){
            helper.setVisible(R.id.img_txt_id,false);
        }else {
            helper.setVisible(R.id.img_txt_id,true);
            helper.setText(R.id.img_txt_id,item.getName());
        }
    }
}
