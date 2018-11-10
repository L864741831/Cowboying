package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.widget.LinearLayout;
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
public class CashDespositListAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {
    private Context context;
    public CashDespositListAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        helper.setText(R.id.cash_money_id,"￥1000");
        TextView title_stadus_id=helper.getView(R.id.title_stadus_id);

        if(true){
            //已到账
            title_stadus_id.setText("已到账");
            helper.setVisible(R.id.transfer_money_rv,true);
            helper.setText(R.id.transfer_money_id,"2018-10-03 12:20:02")
            .setText(R.id.transfer_money_stadus,"到账时间：")
            .setText(R.id.cash_progress_id,"已到账");
        }else if(false){
            //审核中
            title_stadus_id.setText("审核中");
            helper.setVisible(R.id.transfer_money_rv,false);
            helper.setText(R.id.cash_progress_id,"审核中");
        }else if(false){
            //提现失败
            title_stadus_id.setText("提现失败");
            helper.setVisible(R.id.transfer_money_rv,true);
            helper.setText(R.id.transfer_money_id,"银行卡信息不对")
                    .setText(R.id.transfer_money_stadus,"失败原因：")
                    .setText(R.id.cash_progress_id,"提下失败");
        }

    }
}
