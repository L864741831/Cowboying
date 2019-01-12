package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.HomeParstureResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.view.customview.GlideRoundTransformAll;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class HomePastureAdapter extends BaseQuickAdapter<HomeParstureResultBean.BizDataBean,BaseViewHolder> {
    private Context context;
    public HomePastureAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeParstureResultBean.BizDataBean item) {
       helper.setText(R.id.pasture_name_id,item.getName())
               .setText(R.id.pasture_des_id,item.getIntroduction());
         ImageView pasture_img_id=helper.getView(R.id.pasture_img_id);
        RequestOptions options1 = new RequestOptions()
                .centerCrop()
//                .placeholder(R.mipmap.jzsb)//预加载图片
                .error(R.mipmap.jzsb)//加载失败显示图片
                .priority(Priority.HIGH)//优先级
                .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存策略
                .transform(new GlideRoundTransformAll(10));//转化为圆角
        Glide.with(context).load(Constant.imageDomain+item.getPastureImage()).apply(options1).into(pasture_img_id);
    }
}
