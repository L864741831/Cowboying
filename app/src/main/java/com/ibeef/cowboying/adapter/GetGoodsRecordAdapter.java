package com.ibeef.cowboying.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;

import java.util.List;

import rxfamily.bean.BaseBean;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class GetGoodsRecordAdapter extends BaseQuickAdapter<BaseBean,BaseViewHolder> {
    private Context context;
    public GetGoodsRecordAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {
        helper.setText(R.id.time_txt_id,"2019-10-10")
                .setText(R.id.cows_stadus_id,"安格斯牛")
                .setText(R.id.weight_txt_id,"0.6kg");
        if(true){
            //提货重量
            helper.setText(R.id.choose_beef_stadus_id,"提货重量")
            .setText(R.id.beef_stadus_id,"牛肉提货");
        }else {
            //充值重量
            helper.setText(R.id.choose_beef_stadus_id,"充值重量")
            .setText(R.id.beef_stadus_id,"牛肉充值");
        }

    }
}
