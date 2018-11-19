package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.RanchBottomVideoResultBean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/23 17:16
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class PastureDetelBottomImgAdapter extends BaseQuickAdapter<RanchBottomVideoResultBean.BizDataBean,BaseViewHolder> {

    private Context mContext;
    public PastureDetelBottomImgAdapter(Context context, List<RanchBottomVideoResultBean.BizDataBean> list, int layout){
        super(layout, list);
        this.mContext=context;
    }
    @Override
    protected void convert(BaseViewHolder helper, RanchBottomVideoResultBean.BizDataBean item) {
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .error(R.mipmap.jzsb)
                //跳过内存缓存
                ;
        ImageView imageView= helper.getView(R.id.img_id);
        Glide.with(mContext).load(item.getCoverUrl()).apply(options).into(imageView);
        helper.setText(R.id.txt_name_id,item.getName());
    }
}
