package com.ibeef.cowboying.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.AddMoneyResultBean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class AddMoneyAdapter extends BaseQuickAdapter<AddMoneyResultBean.BizDataBean,BaseViewHolder> {
    private Context context;
    public AddMoneyAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, AddMoneyResultBean.BizDataBean item) {
        helper.setText(R.id.time_txt_id,item.getDate())
        .setText(R.id.order_txt_id,item.getOrderCode());
        if(item.getAmount().floatValue()>0){
            helper.setText(R.id.add_money_id,"+"+item.getAmount());
        }else {
            helper.setText(R.id.add_money_id,""+item.getAmount());
        }
    }
}
