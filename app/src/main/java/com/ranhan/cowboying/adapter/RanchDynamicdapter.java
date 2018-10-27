package com.ranhan.cowboying.adapter;


import android.content.Context;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ranhan.cowboying.R;
import com.ranhan.cowboying.bean.RanchDynamicListBean;

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

