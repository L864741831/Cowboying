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
 * @date on 2018/10/23 17:16
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class OptionReturnImgAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    private Context mContext;
    public OptionReturnImgAdapter(Context context, List<String> list,int layout){
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
        if(helper.getAdapterPosition()==0){
            imageView.setImageResource(R.mipmap.uploadphotoimg);
            helper.setVisible(R.id.close_id,false);
        }else {
            Glide.with(mContext).load(Constant.imageDomain+item).apply(options).into(imageView);
            helper.setVisible(R.id.close_id,true);
        }
        helper.addOnClickListener(R.id.close_id);
        helper.addOnClickListener(R.id.upload_img);
    }
}
