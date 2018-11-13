package com.ibeef.cowboying.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.MyCowsListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;


import java.util.List;

import rxfamily.bean.BaseBean;


public class MyCowsListAdapter extends BaseQuickAdapter<MyCowsOrderListBean.BizDataBean, BaseViewHolder> {
    private Context context;
    public MyCowsListAdapter(List data, Context context) {
        super(R.layout.my_cows_list_item, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final MyCowsOrderListBean.BizDataBean item) {

//    订单状态（1:未付款；2：已付款未分配；3：已分配；4：已分配锁定期中；5：出售中；6:交易完成；9；交易关闭）
//    不用给领养类型，活期是3 定期只有4，不会为3
            if ("1".equals("1")) {
                //待付款(基础界面只显示待付款的几个条目，其他都隐藏掉了)
            } else if ("2".equals("2")) {
                //已付款未分配（比待付款多了一个支付时间和支付时间）
            } else if ("3".equals("3")) {
                //已分配（这里只有活期养牛这一种）
            } else if ("4".equals("4")) {
                //已分配锁定期中（只有定期养牛才会有这个状态）
            } else if ("5".equals("5")) {
                //出售中（不分活期和定期。。定期到期后会自动转为活期的）
            } else if ("6".equals("6")) {
                //交易完成（比交易完成多一个出售成功时间）
            }else if ("9".equals("9")) {
                //交易关闭
            }



    }
}

