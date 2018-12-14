package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.WalletRecordResultBean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class WalletDetailListAdapter extends BaseQuickAdapter<WalletRecordResultBean.BizDataBean,BaseViewHolder> {
    private Context context;
    public WalletDetailListAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, WalletRecordResultBean.BizDataBean item) {

        helper.setText(R.id.show_time_id,item.getChangeTime());
        TextView textView=helper.getView(R.id.show_money_id);

        if("11".equals(item.getChangeType())){
            helper.setText(R.id.show_reson_id,"卖牛本金返还");
        }else  if("12".equals(item.getChangeType())){
            helper.setText(R.id.show_reson_id,"卖牛收益奖励");
        }else  if("13".equals(item.getChangeType())){
            helper.setText(R.id.show_reson_id,"买牛花费");
        }else  if("14".equals(item.getChangeType())){
            helper.setText(R.id.show_reson_id,"用户提现减少");
        }else  if("15".equals(item.getChangeType())){
            helper.setText(R.id.show_reson_id,"用户提现失败或拒绝返还");
        }else  if("21".equals(item.getChangeType())){
            helper.setText(R.id.show_reson_id,"卖牛牛肉兑换");
        }else  if("22".equals(item.getChangeType())){
            helper.setText(R.id.show_reson_id,"牛肉提取");
        }else  if("31".equals(item.getChangeType())){
            helper.setText(R.id.show_reson_id,"买牛资金流入");
        }else  if("32".equals(item.getChangeType())){
            helper.setText(R.id.show_reson_id,"买牛资金收益");
        }else  if("33".equals(item.getChangeType())){
            helper.setText(R.id.show_reson_id,"兑换牛肉减少");
        }else  if("34".equals(item.getChangeType())){
            helper.setText(R.id.show_reson_id,"用户提现减少");
        }else  if("16".equals(item.getChangeType())){
            helper.setText(R.id.show_reson_id,"订单支付");
        }else  if("17".equals(item.getChangeType())){
            helper.setText(R.id.show_reson_id,"订单支付");
        }
        if(item.getChangeAmount().floatValue()>=0){
            //如果金额是add的
            textView.setTextColor(context.getResources().getColor(R.color.colorGolds));
            helper.setText(R.id.show_money_id,"+"+item.getChangeAmount());
        }else {
            //金额是减少的
            textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
            helper.setText(R.id.show_money_id,""+item.getChangeAmount());
        }
    }
}
