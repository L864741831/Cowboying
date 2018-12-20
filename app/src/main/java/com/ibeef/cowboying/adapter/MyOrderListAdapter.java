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
import com.ibeef.cowboying.bean.MyOrderListBean;
import com.ibeef.cowboying.utils.WrapContentLinearLayoutManager;
import com.ibeef.cowboying.view.activity.MyOrderDetailActivity;

import java.util.ArrayList;
import java.util.List;


public class MyOrderListAdapter extends BaseQuickAdapter<MyOrderListBean.BizDataBean, BaseViewHolder> {
    private Context context;
    private List<MyOrderListBean.BizDataBean.ShopOrderProductResVosBean> listData;
    public MyOrderListAdapter(List data, Context context) {
        super(R.layout.my_order_list_item, data);
        this.context=context;

    }

    @Override
    protected void convert(BaseViewHolder helper, final MyOrderListBean.BizDataBean item) {
        TextView textView1 = helper.getView(R.id.btn_delet_order);//删除订单
        TextView textView2 = helper.getView(R.id.btn_see_order_progress);//物流信息
        TextView textView3 = helper.getView(R.id.btn_confirm_receipt);//确认收货
        TextView textView4 = helper.getView(R.id.btn_cancle_order);//取消订单
        TextView textView5 = helper.getView(R.id.btn_to_pay);//去付款
        TextView textView6 = helper.getView(R.id.btn_apply_return);//申请退款
        TextView textView7 = helper.getView(R.id.btn_to_detail);//查看详情

        helper.addOnClickListener(R.id.btn_delet_order);
        helper.addOnClickListener(R.id.btn_see_order_progress);
        helper.addOnClickListener(R.id.btn_confirm_receipt);
        helper.addOnClickListener(R.id.btn_cancle_order);
        helper.addOnClickListener(R.id.btn_to_pay);
        helper.addOnClickListener(R.id.btn_apply_return);

        int quantity=0;
        for (int i=0;i<item.getShopOrderProductResVos().size();i++){
            quantity=item.getShopOrderProductResVos().get(i).getQuantity()+quantity;
        }

        helper.setText(R.id.order_id,"订单编号："+item.getShopOrderResVo().getCode())
              .setText(R.id.tv_total,"共"+quantity+"件，合计：￥"+item.getShopOrderResVo().getPayAmount());

       listData = new ArrayList<>();
        if (item.getShopOrderProductResVos().size()>3){
            listData.add(item.getShopOrderProductResVos().get(0));
            listData.add(item.getShopOrderProductResVos().get(1));
            listData.add(item.getShopOrderProductResVos().get(2));
        }else{
            listData.addAll(item.getShopOrderProductResVos());
        }
        RecyclerView ry_id=helper.getView(R.id.ry_id);
        ry_id.setLayoutManager(new WrapContentLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        ry_id.setHasFixedSize(true);
        ry_id.setNestedScrollingEnabled(false);
       final MyOrderChirdListAdapter myOrderChirdListAdapter=new MyOrderChirdListAdapter(listData,item.getShopOrderResVo().getReceiveType(),context);
        ry_id.setAdapter(myOrderChirdListAdapter);
        myOrderChirdListAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(context, MyOrderDetailActivity.class);
                    intent.putExtra("orderId",item.getShopOrderResVo().getOrderId()+"");
                    context.startActivity(intent);
                }
            });
//        订单状态（0：未支付；1：已支付；2：已发货；3：确认收货；4：退款中；5：已退款；6：已取消；）'
            if ("0".equals(item.getShopOrderResVo().getStatus())) {
                //待付款
                helper.setText(R.id.stadus_id,"待付款");
                textView4.setVisibility(View.VISIBLE);
                textView5.setVisibility(View.VISIBLE);
                textView1.setVisibility(View.GONE);
                textView2.setVisibility(View.GONE);
                textView3.setVisibility(View.GONE);
                textView6.setVisibility(View.GONE);
                textView7.setVisibility(View.GONE);
            } else if ("1".equals(item.getShopOrderResVo().getStatus())) {
                //已支付{包含待发货和待取货}
                textView6.setVisibility(View.VISIBLE);
                textView1.setVisibility(View.GONE);
                textView2.setVisibility(View.GONE);
                textView3.setVisibility(View.GONE);
                textView4.setVisibility(View.GONE);
                textView5.setVisibility(View.GONE);
                textView7.setVisibility(View.GONE);
                if ("1".equals(item.getShopOrderResVo().getReceiveType())){
                    //快递
                    helper.setText(R.id.stadus_id,"待发货");
                }else if ("2".equals(item.getShopOrderResVo().getReceiveType())){
                    //门店自取
                    helper.setText(R.id.stadus_id,"待取货");
                }
            } else if ("2".equals(item.getShopOrderResVo().getStatus())) {
                //待收货
                helper.setText(R.id.stadus_id,"待收货");
                textView2.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.VISIBLE);
                textView1.setVisibility(View.GONE);
                textView6.setVisibility(View.GONE);
                textView4.setVisibility(View.GONE);
                textView5.setVisibility(View.GONE);
                textView7.setVisibility(View.GONE);
            } else if ("3".equals(item.getShopOrderResVo().getStatus())) {
                //交易成功{包含快递和线下门店}
                helper.setText(R.id.stadus_id,"交易成功");
                textView1.setVisibility(View.VISIBLE);
                textView6.setVisibility(View.GONE);
                textView3.setVisibility(View.GONE);
                textView4.setVisibility(View.GONE);
                textView5.setVisibility(View.GONE);
                textView7.setVisibility(View.GONE);
                if ("1".equals(item.getShopOrderResVo().getReceiveType())){
                    //快递
                    textView2.setVisibility(View.VISIBLE);
                }else if ("2".equals(item.getShopOrderResVo().getReceiveType())){
                    //门店自取
                    textView2.setVisibility(View.GONE);
                }
            } else if ("6".equals(item.getShopOrderResVo().getStatus())) {
                //交易关闭
                helper.setText(R.id.stadus_id,"交易关闭");
                textView1.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.GONE);
                textView3.setVisibility(View.GONE);
                textView4.setVisibility(View.GONE);
                textView5.setVisibility(View.GONE);
                textView7.setVisibility(View.GONE);
                textView6.setVisibility(View.GONE);
            } else if ("4".equals(item.getShopOrderResVo().getStatus())) {
                //退款中
                helper.setText(R.id.stadus_id,"退款中");
                textView7.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.GONE);
                textView3.setVisibility(View.GONE);
                textView4.setVisibility(View.GONE);
                textView5.setVisibility(View.GONE);
                textView1.setVisibility(View.GONE);
                textView6.setVisibility(View.GONE);
            }else if ("5".equals(item.getShopOrderResVo().getStatus())) {
                //已退款
                helper.setText(R.id.stadus_id,"退款成功");
                textView7.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.GONE);
                textView3.setVisibility(View.GONE);
                textView4.setVisibility(View.GONE);
                textView5.setVisibility(View.GONE);
                textView1.setVisibility(View.VISIBLE);
                textView6.setVisibility(View.GONE);
            }

    }
}

