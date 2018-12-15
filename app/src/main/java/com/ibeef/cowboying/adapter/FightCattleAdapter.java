package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
public class FightCattleAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {
    private Context context;
    public FightCattleAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {

        helper.setText(R.id.container_txt_id,"· 澳洲02号货柜 ·")
                .setText(R.id.kill_house_txt_id,"澳洲屠宰场")
                .setText(R.id.parsture_name_id,"安格斯牛")
                .setText(R.id.percent_txt_id,"30%")//服务费
                .setText(R.id.minpercent_txt_id,"进度10%")
                .setText(R.id.fight_person_name_id,"0人已拼");
        RecyclerView ry_id=helper.getView(R.id.ry_id);
        ry_id.setLayoutManager(new LinearLayoutManager(context));
        ry_id.setHasFixedSize(true);
        ry_id.setNestedScrollingEnabled(false);

        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .error(R.mipmap.jzsb)
                //跳过内存缓存
                ;
        ImageView imageView= helper.getView(R.id.banner_img_id);
        Glide.with(context).load(Constant.imageDomain).apply(options).into(imageView);
    }
}
