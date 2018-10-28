package com.ibeef.cowboying.adapter;


import android.content.Context;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.RanchDynamicListBean;

import java.util.List;


public class RanchDynamicdapter extends BaseQuickAdapter<RanchDynamicListBean.DataBean, BaseViewHolder> {
    private Context context;
    public RanchDynamicdapter(List data, Context context) {
        super(R.layout.item_ranch_dynamic_list, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final RanchDynamicListBean.DataBean item) {

    }
}

