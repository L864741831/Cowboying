package com.ibeef.cowboying.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.HistorySchemeResultBean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class CowClaimPastRecordAdapter extends BaseQuickAdapter<HistorySchemeResultBean.BizDataBean,BaseViewHolder> {
    private Context context;
    public CowClaimPastRecordAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HistorySchemeResultBean.BizDataBean item) {

        helper.setText(R.id.cows_name_id,"第"+item.getCode()+"期 "+item.getPastureName())
                .setText(R.id.money_txt_id,item.getPrice()+"元")
                .setText(R.id.percent_txt_id,item.getExpectRate())
                .setText(R.id.people_num_id,(item.getTotalStock()-item.getStock())+"人");
        helper.addOnClickListener(R.id.see_people_num_rv);


    }
}
