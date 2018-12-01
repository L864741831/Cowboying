package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.AddMoneyResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.view.customview.AmountView;
import com.ibeef.cowboying.view.customview.AmountViewStoreBeef;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class StoreTopAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {
    private Context context;
    public StoreTopAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {

        helper.addOnClickListener(R.id.last_go_img);
        helper.addOnClickListener(R.id.first_go_img);
        final AmountViewStoreBeef amountViewStoreBeef=helper.getView(R.id.amout_num_id);
        amountViewStoreBeef.setGoods_storage(1000000);
        amountViewStoreBeef.intEdit(0+"");
        amountViewStoreBeef.setOnAmountChangeListener(new AmountViewStoreBeef.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                Intent intent1=new Intent();
                intent1.setAction("com.ibeef.cowboying.storenum");
                intent1.putExtra("num",amount);
                context.sendBroadcast(intent1);
            }
        });

        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .error(R.mipmap.cowbeefimg)
                //跳过内存缓存
                ;
        Glide.with(mContext).load(Constant.imageDomain).apply(options).into((ImageView) helper.getView(R.id.goods_info_img));
        helper.setText(R.id.nane_beef_id,"当前位置"+helper.getAdapterPosition())
                .setText(R.id.beef_price_id,"价格：00元")
                .setText(R.id.beef_stock_id,"库存：00/袋")
                .setText(R.id.beef_size_id,"规格：00g/袋");

    }
}
