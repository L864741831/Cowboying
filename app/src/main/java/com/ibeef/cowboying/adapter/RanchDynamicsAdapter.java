package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.HomeVideoResultBean;
import com.ibeef.cowboying.view.customview.GlideRoundTransformAll;

import java.util.List;

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
        ImageView imageView= helper.getView(R.id.show_img_id);
        ImageView imgStatId= helper.getView(R.id.img_stat_id);
        RequestOptions options1 = new RequestOptions()
                .centerCrop()
//                .placeholder(R.mipmap.jzsb)//预加载图片
                .error(R.mipmap.jzsb)//加载失败显示图片
                .priority(Priority.HIGH)//优先级
                .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存策略
                .transform(new GlideRoundTransformAll(10));//转化为圆角
        Glide.with(context).load(item.getCoverUrl()).apply(options1).into(imageView);
        imgStatId.setVisibility(View.VISIBLE);

    }
}
