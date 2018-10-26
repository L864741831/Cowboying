package com.ranhan.cowboying.adapter;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ranhan.cowboying.R;
import com.ranhan.cowboying.bean.RanchConsociationTitleBean;

import java.util.List;


/**
 * @author Administrator
 *
 */
public class RanchConsociationTitleAdapter extends BaseQuickAdapter<RanchConsociationTitleBean.DataBean, BaseViewHolder> {
    private Context context;
    public RanchConsociationTitleAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final RanchConsociationTitleBean.DataBean item) {
        helper.setText(R.id.tv_title,item.getName());
        helper.addOnClickListener(R.id.tv_title);
        TextView tv=helper.getView(R.id.tv_title);
//        if (item.getDefaultFlag()==0) {
            tv.setTextColor(ContextCompat.getColor(context,R.color.colorAccent));
//        }else {
//            tv.setTextColor(ContextCompat.getColor(context,R.color.black));
//        }
    }
}

