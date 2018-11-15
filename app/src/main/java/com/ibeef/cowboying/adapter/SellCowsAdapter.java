package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.AddMoneyResultBean;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class SellCowsAdapter extends BaseQuickAdapter<MyCowsOrderListBean.BizDataBean,BaseViewHolder> {
    private Context context;
    public SellCowsAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCowsOrderListBean.BizDataBean item) {

        helper.setText(R.id.order_code_txt,"订单编号："+item.getOrderCode())
                .setText(R.id.cow_num_id,"共"+item.getCattleCount()+"头，合计认领金额：")
                .setText(R.id.all_money_id,item.getOrderAmount()+"");
        RecyclerView ry_id=helper.getView(R.id.ry_id);
        ry_id.setLayoutManager(new LinearLayoutManager(context));
        ry_id.setHasFixedSize(true);
        ry_id.setNestedScrollingEnabled(false);
        SellCowsChirdAdapter sellCowsChirdAdapter=new SellCowsChirdAdapter(item.getCattleList(),context,item.getPastureName());
        ry_id.setAdapter(sellCowsChirdAdapter);
    }
}
