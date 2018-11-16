package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.AddMoneyResultBean;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    protected void convert(final BaseViewHolder helper, final MyCowsOrderListBean.BizDataBean item) {

        helper.setText(R.id.order_code_txt,"订单编号："+item.getOrderCode())
                .setText(R.id.cow_num_id,"共"+item.getCattleCount()+"头，合计认领金额：")
                .setText(R.id.all_money_id,item.getOrderAmount()+"");
        RecyclerView ry_id=helper.getView(R.id.ry_id);
        ry_id.setLayoutManager(new LinearLayoutManager(context));
        ry_id.setHasFixedSize(true);
        ry_id.setNestedScrollingEnabled(false);

        final SellCowsChirdAdapter sellCowsChirdAdapter=new SellCowsChirdAdapter(item.getCattleList(),context,item.getPastureName());
        sellCowsChirdAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.all_ck_id:
                        Intent intent1=new Intent();
                        intent1.setAction("com.ibeef.cowboying");
                        intent1.putExtra("num",item.getCattleCount());
                        intent1.putExtra("position",position);
                        intent1.putExtra("outposition",helper.getAdapterPosition());
                        intent1.putExtra("orderId",item.getOrderId());
                        context.sendBroadcast(intent1);
                        break;
                    default:
                        break;
                }
            }
        });
        ry_id.setAdapter(sellCowsChirdAdapter);
    }

}
