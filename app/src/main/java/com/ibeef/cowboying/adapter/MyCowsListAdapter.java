package com.ibeef.cowboying.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.MyCowsListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.ibeef.cowboying.view.activity.MyCowsDetailActivity;


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
        helper.addOnClickListener(R.id.delet_order);
        helper.addOnClickListener(R.id.see_order_progress);
        helper.addOnClickListener(R.id.sell_want);
        helper.addOnClickListener(R.id.cancle_order);
        helper.addOnClickListener(R.id.to_pay);
        helper.setText(R.id.order_id,"订单编号："+item.getOrderCode())
              .setText(R.id.tv_total,"共"+item.getCattleCount()+"头，合计：￥"+item.getOrderAmount());
        TextView delet_order =helper.getView(R.id.delet_order);
        TextView see_order_progress =helper.getView(R.id.see_order_progress);
        TextView sell_want =helper.getView(R.id.sell_want);
        TextView cancle_order =helper.getView(R.id.cancle_order);
        TextView to_pay =helper.getView(R.id.to_pay);
        RelativeLayout rlEmeng=helper.getView(R.id.rl_emeng);
        RecyclerView ry_id=helper.getView(R.id.ry_id);
        ry_id.setLayoutManager(new LinearLayoutManager(context));
        ry_id.setHasFixedSize(true);
        ry_id.setNestedScrollingEnabled(false);
        if(item.getCattleList().size()>0&&!SDCardUtil.isNullOrEmpty(item.getCattleList())){
            ry_id.setVisibility(View.VISIBLE);
            MyCowsChirdListAdapter sureOrderChirdListAdapter=new MyCowsChirdListAdapter(item.getCattleList(),item.getPastureName(),context);
            ry_id.setAdapter(sureOrderChirdListAdapter);
            sureOrderChirdListAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(context, MyCowsDetailActivity.class);
                    intent.putExtra("orderCode",item.getOrderCode());
                    context.startActivity(intent);
                }
            });
        }else {
            ry_id.setVisibility(View.GONE);
        }

        helper.setText(R.id.tv_ranch_id,item.getPastureName()).
                setText(R.id.num_id,"×"+item.getCattleCount());
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                //跳过内存缓存
                ;
        Glide.with(mContext).load(Constant.imageDomain+item.getPastureImage()).apply(options).into((ImageView) helper.getView(R.id.store_img));

//    订单状态（1:未付款；2：已付款未分配；3：已分配；4：已分配锁定期中；5：出售中；6:交易完成；9；交易关闭）
//    不用给领养类型，活期是3 定期只有4，不会为3
            if ("1".equals(item.getStatus())) {
                //待付款(基础界面只显示待付款的几个条目，其他都隐藏掉了)
                helper.setText(R.id.store_name,"待付款");
                helper.setText(R.id.stadus_id,"等待用户付款");
                to_pay.setVisibility(View.VISIBLE);
                cancle_order.setVisibility(View.VISIBLE);
                rlEmeng.setVisibility(View.VISIBLE);
                see_order_progress.setVisibility(View.GONE);
                sell_want.setVisibility(View.GONE);
                delet_order.setVisibility(View.GONE);
            } else if ("2".equals(item.getStatus())) {
                //已付款未分配（比待付款多了一个支付时间和支付时间）
                helper.setText(R.id.store_name,"待分配牛只");
                helper.setText(R.id.stadus_id,"已付款，待分配牛只");
                see_order_progress.setVisibility(View.VISIBLE);
                rlEmeng.setVisibility(View.VISIBLE);
                to_pay.setVisibility(View.GONE);
                cancle_order.setVisibility(View.GONE);
                sell_want.setVisibility(View.GONE);
                delet_order.setVisibility(View.GONE);
            } else if ("3".equals(item.getStatus())) {
                //已分配（这里只有活期养牛这一种）
                helper.setText(R.id.stadus_id,"已分配牛只");
                to_pay.setVisibility(View.GONE);
                cancle_order.setVisibility(View.GONE);
                rlEmeng.setVisibility(View.GONE);
                see_order_progress.setVisibility(View.VISIBLE);
                sell_want.setVisibility(View.VISIBLE);
                delet_order.setVisibility(View.GONE);
            } else if ("4".equals(item.getStatus())) {
                //已分配锁定期中（只有定期养牛才会有这个状态）
                helper.setText(R.id.stadus_id,"已分配牛只");
                to_pay.setVisibility(View.GONE);
                cancle_order.setVisibility(View.GONE);
                rlEmeng.setVisibility(View.GONE);
                sell_want.setVisibility(View.GONE);
                see_order_progress.setVisibility(View.VISIBLE);
                delet_order.setVisibility(View.GONE);
            } else if ("5".equals(item.getStatus())) {
                //出售中（不分活期和定期。。定期到期后会自动转为活期的）
                helper.setText(R.id.stadus_id,"牛只出售中");
                to_pay.setVisibility(View.GONE);
                cancle_order.setVisibility(View.GONE);
                rlEmeng.setVisibility(View.GONE);
                sell_want.setVisibility(View.GONE);
                see_order_progress.setVisibility(View.VISIBLE);
                delet_order.setVisibility(View.GONE);
            } else if ("6".equals(item.getStatus())) {
                //交易完成（比交易完成多一个出售成功时间）
                helper.setText(R.id.stadus_id,"交易完成");
                to_pay.setVisibility(View.GONE);
                cancle_order.setVisibility(View.GONE);
                rlEmeng.setVisibility(View.GONE);
                sell_want.setVisibility(View.GONE);
                delet_order.setVisibility(View.VISIBLE);
                see_order_progress.setVisibility(View.VISIBLE);
            }else if ("9".equals(item.getStatus())) {
                //交易关闭
                helper.setText(R.id.store_name,"交易关闭");
                helper.setText(R.id.stadus_id,"交易关闭");
                to_pay.setVisibility(View.GONE);
                cancle_order.setVisibility(View.GONE);
                rlEmeng.setVisibility(View.GONE);
                sell_want.setVisibility(View.GONE);
                see_order_progress.setVisibility(View.GONE);
                delet_order.setVisibility(View.VISIBLE);
            }

            helper.addOnClickListener(R.id.sell_want);
    }
}

