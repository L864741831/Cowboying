package com.ibeef.cowboying.adapter;


import android.content.Context;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import java.util.List;
import rxfamily.bean.BaseBean;


public class DiscountCoupondapter extends BaseQuickAdapter<BaseBean, BaseViewHolder> {
    private Context context;
    public DiscountCoupondapter(List data, Context context) {
        super(R.layout.item_my_discount_list, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final BaseBean item) {


          helper.setText(R.id.tv_type_id,"券的种类")
                .setText(R.id.tv_condition_1_id,"使用条件")
                .setText(R.id.tv_condition_2_id,"使用范围")
                .setText(R.id.tv_time_id,"使用时间")
                .setText(R.id.tv_type_id,"券的种类")
                .setText(R.id.tv_price_id,"钱");


    }
}

