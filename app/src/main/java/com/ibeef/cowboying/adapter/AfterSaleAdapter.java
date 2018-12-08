package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.MyAfterSaleListBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.utils.DateUtils;
import com.ibeef.cowboying.utils.SDCardUtil;

import java.util.List;

import rxfamily.bean.BaseBean;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class AfterSaleAdapter extends BaseQuickAdapter<MyAfterSaleListBean.BizDataBean,BaseViewHolder> {
    private Context context;
    public AfterSaleAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyAfterSaleListBean.BizDataBean item) {
        helper.setText(R.id.tv_service_id,"服务单号:  "+item.getShopRefundOrderResVo().getCode());
//        '退款单状态（1：申请退款；2：退款完成；3：退款拒绝；4：退款取消）',
        if ("1".equals(item.getShopRefundOrderResVo().getStatus())) {
            helper.setText(R.id.tv_after_sale_status,"待商家审核");
        } else if ("2".equals(item.getShopRefundOrderResVo().getStatus())) {
            helper.setText(R.id.tv_after_sale_status,"退款成功");
        } else if ("3".equals(item.getShopRefundOrderResVo().getStatus())) {
            helper.setText(R.id.tv_after_sale_status,"审核拒绝 ");
        } else if ("4".equals(item.getShopRefundOrderResVo().getStatus())) {
            helper.setText(R.id.tv_after_sale_status,"已撤销退款申请 ");
        }

        RecyclerView ry_id=helper.getView(R.id.ry_id);
        ry_id.setLayoutManager(new LinearLayoutManager(context));
        ry_id.setHasFixedSize(true);
        ry_id.setNestedScrollingEnabled(false);
        AfterSaleChirdListAdapter afterSaleChirdListAdapter=new AfterSaleChirdListAdapter(item.getOrderProductResVos(),"1",context);
        ry_id.setAdapter(afterSaleChirdListAdapter);
        afterSaleChirdListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                    Intent intent = new Intent(context, MyCowsDetailActivity.class);
//                    intent.putExtra("orderId",item.getOrderId()+"");
//                    context.startActivity(intent);
            }
        });
    }
}
