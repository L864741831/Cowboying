package com.ibeef.cowboying.adapter;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.MyCowsListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;
import com.ibeef.cowboying.config.Constant;


import java.util.List;


public class MyCowsChirdListAdapter extends BaseQuickAdapter<MyCowsOrderListBean.BizDataBean.CattleListBean, BaseViewHolder> {
    private Context context;
    private String ranch_id;
    public MyCowsChirdListAdapter(List data,String ranch_id, Context context) {
        super(R.layout.my_cows_chird_item, data);
        this.context=context;
        this.ranch_id=ranch_id;
    }

    @Override
    protected void convert(BaseViewHolder helper, final MyCowsOrderListBean.BizDataBean.CattleListBean item) {
        helper.setText(R.id.store_name,"牛只编号:"+item.getCattleCode())
                .setText(R.id.tv_ranch_id,""+ranch_id).
                setText(R.id.num_id,"初始重量："+item.getCattleWeight()+"kg");

        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                //跳过内存缓存
                ;
        Glide.with(mContext).load(Constant.imageDomain+item.getCattleImage()).apply(options).into((ImageView) helper.getView(R.id.store_img));

    }
}

