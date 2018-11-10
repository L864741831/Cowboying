package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class WalletDetailListAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {
    private Context context;
    public WalletDetailListAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {

        helper.setText(R.id.show_reson_id,"认领牛只")
                .setText(R.id.show_time_id,"2018-08-25 13:00")
                .setText(R.id.show_money_id,"+5000");
        TextView textView=helper.getView(R.id.show_money_id);

        if(true){
            //如果金额是add的
            textView.setTextColor(context.getResources().getColor(R.color.colorGolds));
        }else {
            //金额是减少的
            textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }
    }
}
