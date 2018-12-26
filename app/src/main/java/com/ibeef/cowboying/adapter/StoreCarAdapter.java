package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.CarListResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.view.customview.AmountViewWhite;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class StoreCarAdapter extends BaseQuickAdapter<CarListResultBean.BizDataBean,BaseViewHolder> {
    private Context context;
    public StoreCarAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, CarListResultBean.BizDataBean item) {

        AmountViewWhite amountViewWhite=helper.getView(R.id.amout_num_white_id);
        amountViewWhite.setGoods_storage(item.getStock());
        amountViewWhite.intEdit(item.getQuantity()+"");

        CheckBox all_ck_id=helper.getView(R.id.all_ck_id);
        if(0==item.getDefautChoose()){
            all_ck_id.setBackground(ContextCompat.getDrawable(context, R.mipmap.unhascheck));
        }else {
            all_ck_id.setBackground(ContextCompat.getDrawable(context, R.mipmap.hascheck));
        }

        helper.addOnClickListener(R.id.all_ck_id);

        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .error(R.mipmap.cowbeefimg)
                //跳过内存缓存
                ;
        Glide.with(context).load(Constant.imageDomain+item.getProductMainImage()).apply(options).into((ImageView) helper.getView(R.id.goods_info_img));

        helper.setText(R.id.name_beef_id,item.getName())
                .setText(R.id.price_id,"￥"+item.getPrice());

        amountViewWhite.setOnAmountChangeListener(new AmountViewWhite.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                Intent intent1=new Intent();
                intent1.setAction("com.ibeef.cowboying.storecarnum");
                intent1.putExtra("position",helper.getAdapterPosition());
                intent1.putExtra("amount",amount);
                context.sendBroadcast(intent1);
            }
        });

    }

}
