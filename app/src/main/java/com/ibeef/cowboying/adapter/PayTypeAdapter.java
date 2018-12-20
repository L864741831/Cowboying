package com.ibeef.cowboying.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.PayCodeBean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class PayTypeAdapter extends BaseQuickAdapter<PayCodeBean.BizDataBean.PayTypeListBean,BaseViewHolder> {
    private Context context;
    public PayTypeAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert( BaseViewHolder helper,PayCodeBean.BizDataBean.PayTypeListBean item) {
            helper.setText(R.id.tv_pay_type,item.getName()+"(￥"+item.getAmount()+")");
    }
}
