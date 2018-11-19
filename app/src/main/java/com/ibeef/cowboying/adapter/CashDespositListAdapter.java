package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.CashMoneyRecordResultBean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class CashDespositListAdapter extends BaseQuickAdapter<CashMoneyRecordResultBean.BizDataBean,BaseViewHolder> {
    private Context context;
    public CashDespositListAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CashMoneyRecordResultBean.BizDataBean item) {
        helper.setText(R.id.cash_money_id,"￥"+item.getAmount())
        .setText(R.id.applay_time_id,item.getCreateTime());
        TextView title_stadus_id=helper.getView(R.id.title_stadus_id);

        if("2".equals(item.getStatus())){
            //取现成功
            title_stadus_id.setText("取现成功");
            helper.setVisible(R.id.transfer_money_rv,true);
            helper.setText(R.id.transfer_money_id,item.getSuccessTime())
            .setText(R.id.transfer_money_stadus,"取现成功时间：")
            .setText(R.id.cash_progress_id,"取现成功");
        }else if("1".equals(item.getStatus())){
            //申请取现
            title_stadus_id.setText("审核中");
            helper.setVisible(R.id.transfer_money_rv,false);
            helper.setText(R.id.cash_progress_id,"审核中");
        }else if("3".equals(item.getStatus())){
            //提现失败
            title_stadus_id.setText("提现失败");
            helper.setVisible(R.id.transfer_money_rv,true);
            helper.setText(R.id.transfer_money_id,item.getReason())
                    .setText(R.id.transfer_money_stadus,"失败原因：")
                    .setText(R.id.cash_progress_id,"提现失败");
        }else if("4".equals(item.getStatus())){
            //取现拒绝
            title_stadus_id.setText("取现拒绝");
            helper.setVisible(R.id.transfer_money_rv,true);
            helper.setText(R.id.transfer_money_id,item.getReason())
                    .setText(R.id.transfer_money_stadus,"拒绝原因：")
                    .setText(R.id.cash_progress_id,"取现拒绝");
        }

    }
}
