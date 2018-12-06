package com.ibeef.cowboying.adapter;


import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.ShowAddressResultBean;

import java.util.List;


public class GoodsAddrAdapter extends BaseQuickAdapter<ShowAddressResultBean.BizDataBean, BaseViewHolder> {
    private Context context;
    public GoodsAddrAdapter(List data, Context context) {
        super(R.layout.goods_addr_list_item, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShowAddressResultBean.BizDataBean item) {
        helper.addOnClickListener(R.id.addr_edit);
        helper.addOnClickListener(R.id.delete_addr);
        helper.setText(R.id.name_id,item.getName())
                .setText(R.id.tell_id,item.getMobile())
                .setText(R.id.addr_id,item.getProvince()+item.getCity()+item.getRegion()+item.getDetailAddress());

    }
}

