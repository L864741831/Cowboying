package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.view.customview.AmountView;
import com.ibeef.cowboying.view.customview.AmountViewBeef;

import java.util.List;

import rxfamily.bean.BaseBean;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class BeefHourseAdapter extends BaseQuickAdapter<BaseBean,BaseViewHolder> {
    private Context context;
    public BeefHourseAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {
       helper.setText(R.id.txt_area_id,"1.：8专区")
               .setText(R.id.beef_name_id,"澳洲和牛眼肉M7500克")
               .setText(R.id.beef_des_id,"星尊之选");

        AmountViewBeef amountViewBeef=helper.getView(R.id.amout_num_id);
        amountViewBeef.intEdit("1");
        amountViewBeef.setGoods_storage(1000000);
        amountViewBeef.setOnAmountChangeListener(new AmountViewBeef.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
               //amount数量

            }
        });
    }
}
