package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.HomeBuyCowPlanResultBean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class HomeEatCowsAdapter extends BaseQuickAdapter<HomeBuyCowPlanResultBean.BizDataBean,BaseViewHolder> {
    private Context context;
    public HomeEatCowsAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBuyCowPlanResultBean.BizDataBean item) {
       helper.setText(R.id.show_rate_id,item.getExpectRate());
        RelativeLayout rv_type_show=helper.getView(R.id.rv_type_show);
       if("1".equals(item.getType())){
           rv_type_show.setBackgroundResource(R.mipmap.homehqcarview);
           helper.setText(R.id.type_time_id,"7天锁定期")
                   .setText(R.id.type_name_id,"活期养牛")
                   .setText(R.id.type_rate_id,"预期牛肉回购回报率(年化)")
                   .setText(R.id.des_type_id,"养满一年可兑换120斤牛肉");
       }else  if("3".equals(item.getType())){
           rv_type_show.setBackgroundResource(R.mipmap.homenewcarview);
           helper.setText(R.id.type_time_id,"7天锁定期")
                   .setText(R.id.type_name_id,"新手福利")
                   .setText(R.id.type_rate_id,"预期牛肉回购回报率(年化)")
                   .setText(R.id.des_type_id,"一元体验  十倍收益");
       }else {
           rv_type_show.setBackgroundResource(R.mipmap.homedqcarview);
           helper.setText(R.id.type_time_id,item.getLockMonths()+"个月锁定期")
                   .setText(R.id.type_name_id,"定期养牛")
                   .setText(R.id.type_rate_id,"预期牛肉回购回报率(年化)")
                   .setText(R.id.des_type_id,"养满一年可兑换120斤牛肉");
       }
    }
}
