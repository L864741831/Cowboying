package com.ibeef.cowboying.adapter;


import android.content.Context;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.MyCowsListBean;


import java.util.List;


public class MyCowsChirdListAdapter extends BaseQuickAdapter<MyCowsListBean.DataBean.ProductsBean, BaseViewHolder> {
    private Context context;
    private String allMoney;
    public MyCowsChirdListAdapter(List data, String allMoney, Context context) {
        super(R.layout.my_cows_chird_item, data);
        this.context=context;
        this.allMoney=allMoney;
    }

    @Override
    protected void convert(BaseViewHolder helper, final MyCowsListBean.DataBean.ProductsBean item) {
//        helper.setText(R.id.store_name,item.getName())
//                .setText(R.id.size_id,""+item.getSpec()).
//                        setText(R.id.money_id,"￥"+item.getPrice())
//                .setText(R.id.num_id,"x"+item.getQuantity())
//                .setText(R.id.totol_money,"共"+item.getQuantity()+"件商品 实付款￥"+allMoney);
//        RequestOptions options = new RequestOptions()
//                .skipMemoryCache(true)
//                //跳过内存缓存
//                ;
//        Glide.with(mContext).load(YbConstant.imageDomain+item.getImage()).apply(options).into((ImageView) helper.getView(R.id.store_img));

    }
}

