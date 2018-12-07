package com.ibeef.cowboying.adapter;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.StoreAddrResultBean;

import java.util.List;


public class StoreAddrAdapter extends BaseQuickAdapter<StoreAddrResultBean.BizDataBean, BaseViewHolder> {
    private Context context;
    public StoreAddrAdapter(List data, Context context) {
        super(R.layout.item_store_addr, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final StoreAddrResultBean.BizDataBean item) {

        CheckBox all_ck_id=helper.getView(R.id.all_ck_id);
        helper.addOnClickListener(R.id.all_ck_id);
        if(0==item.getDefautChoose()){
            all_ck_id.setBackground(ContextCompat.getDrawable(context, R.drawable.unhascheck));
        }else {
            all_ck_id.setBackground(ContextCompat.getDrawable(context, R.drawable.hascheck));
        }
        helper.setText(R.id.store_addr_id,item.getAddress());
    }
}

