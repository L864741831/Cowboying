package com.ibeef.cowboying.adapter;


import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;

import java.util.List;


public class GoodsAddrAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {
    private Context context;
    public GoodsAddrAdapter(List data, Context context) {
        super(R.layout.goods_addr_list_item, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final Object item) {
        helper.addOnClickListener(R.id.add_address_rv);
        helper.addOnClickListener(R.id.addr_edit);
        helper.addOnClickListener(R.id.delete_addr);
        helper.setText(R.id.name_id,"李闪闪")
                .setText(R.id.tell_id,"10900000000")
                .setText(R.id.addr_id,"美国就近上将管路大山");

    }
}

