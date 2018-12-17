package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.VipCardListBean;
import com.ibeef.cowboying.bean.WalletRecordResultBean;
import com.ibeef.cowboying.utils.DateUtils;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class ConssumptionHistoryListAdapter extends BaseQuickAdapter<VipCardListBean.BizDataBean,BaseViewHolder> {
    private Context context;
    public ConssumptionHistoryListAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, VipCardListBean.BizDataBean item) {

        helper.setText(R.id.show_time_id,DateUtils.formatDate(item.getCreateTime(), DateUtils.TYPE_01));
        TextView textView=helper.getView(R.id.show_money_id);
        if("1".equals(item.getChangeType())){
            helper.setText(R.id.show_reson_id,"充值");
        }else  if("2".equals(item.getChangeType())){
            helper.setText(R.id.show_reson_id,"充值取消");
        }else  if("3".equals(item.getChangeType())){
            helper.setText(R.id.show_reson_id,"消费");
        }else  if("4".equals(item.getChangeType())){
            helper.setText(R.id.show_reson_id,"消费取消");
        }
        if(item.getAmount()>=0){
            //如果金额是add的
//            textView.setTextColor(context.getResources().getColor(R.color.colorGolds));
            helper.setText(R.id.show_money_id,"+"+item.getAmount());
        }else {
            //金额是减少的
//            textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
            helper.setText(R.id.show_money_id,""+item.getAmount());
        }
    }
}
