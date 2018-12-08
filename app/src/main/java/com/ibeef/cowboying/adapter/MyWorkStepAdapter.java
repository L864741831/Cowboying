package com.ibeef.cowboying.adapter;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.ShowDeleveryResultBean;


import java.util.List;

public class MyWorkStepAdapter extends BaseQuickAdapter<ShowDeleveryResultBean.BizDataBean.ExpressResVosBean, BaseViewHolder> {
    private Context context;
    private int type;
    public MyWorkStepAdapter(List data, Context context, int layOut) {
        super(layOut, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShowDeleveryResultBean.BizDataBean.ExpressResVosBean item) {
        helper.setText(R.id.description_tv,item.getContext());
        helper.setText(R.id.time_id,item.getTime());
       ImageView icon= helper.getView(R.id.stepicon_iv);
        TextView textView=helper.getView(R.id.description_tv);
        if(item.getContext().contains("已签收")||item.getContext().contains("签收")){
            icon.setImageResource(R.mipmap.stepchose);
            textView.setTextColor(ContextCompat.getColor(context,R.color.skyblue));
        }else {
            icon.setImageResource(R.mipmap.stepun);
            textView.setTextColor(ContextCompat.getColor(context,R.color.black));
        }

    }
}

