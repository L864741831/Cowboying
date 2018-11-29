package com.ibeef.cowboying.adapter;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;
import com.ibeef.cowboying.config.Constant;

import java.util.List;


public class MyCowsChirdListAdapter extends BaseQuickAdapter<MyCowsOrderListBean.BizDataBean.CattleListBean, BaseViewHolder> {
    private Context context;
    private String ranch_id;
    private String status;
    public MyCowsChirdListAdapter(List data,String ranch_id,String status, Context context) {
        super(R.layout.my_cows_chird_item, data);
        this.context=context;
        this.ranch_id=ranch_id;
        this.status=status;
    }

    @Override
    protected void convert(BaseViewHolder helper, final MyCowsOrderListBean.BizDataBean.CattleListBean item) {
        helper.setText(R.id.store_name,"牛只编号:"+item.getCattleCode())
                .setText(R.id.tv_ranch_id,""+ranch_id).
                setText(R.id.num_id,"初始重量："+item.getCattleWeight()+"kg");
        ImageView imageView= helper.getView(R.id.iv_my_cows_dingqi);
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .error(R.mipmap.jzsb)
                //跳过内存缓存
                ;
        Glide.with(mContext).load(Constant.imageDomain+item.getCattleImage()).apply(options).into((ImageView) helper.getView(R.id.store_img));
        //方案类型（1：活期；2：定期；3：新人活动）
        if ("2".equals(status)) {
            helper.setText(R.id.store_name,"牛只编号:"+item.getCattleCode())
            .setText(R.id.money_id,"安格斯牛");
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(R.mipmap.img_my_cows_dingqi);
            helper.setVisible(R.id.num_id,true);
        } else  if ("3".equals(status)){
            helper.setText(R.id.store_name,item.getCattleCode())
                    .setText(R.id.money_id,"虚拟牛只");
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(R.mipmap.newmangoods);
            helper.setVisible(R.id.num_id,false);
        }else {
            helper.setText(R.id.store_name,"牛只编号:"+item.getCattleCode())
            .setText(R.id.money_id,"安格斯牛");
            imageView.setVisibility(View.GONE);
            helper.setVisible(R.id.num_id,true);
        }
    }
}

