package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.ibeef.cowboying.view.activity.AfterSaleActivity;
import com.ibeef.cowboying.view.activity.AfterSaleDetailActivity;
import com.ibeef.cowboying.view.customview.CountDowntimeView;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    protected void convert(BaseViewHolder helper, final MyAfterSaleListBean.BizDataBean item) {
        helper.setText(R.id.tv_service_id,"服务单号:  "+item.getShopRefundOrderResVo().getCode());
        CountDowntimeView countDowntimeView = helper.getView(R.id.time_show_id);
//        '退款单状态（1：申请退款；2：退款完成；3：退款拒绝；4：退款取消）',
        if ("1".equals(item.getShopRefundOrderResVo().getStatus())) {
            helper.setText(R.id.tv_after_sale_status,"待商家审核，");
            countDowntimeView.setVisibility(View.VISIBLE);
        } else if ("2".equals(item.getShopRefundOrderResVo().getStatus())) {
            helper.setText(R.id.tv_after_sale_status,"退款成功");
            countDowntimeView.setVisibility(View.GONE);
        } else if ("3".equals(item.getShopRefundOrderResVo().getStatus())) {
            helper.setText(R.id.tv_after_sale_status,"审核拒绝 ");
            countDowntimeView.setVisibility(View.GONE);
        } else if ("4".equals(item.getShopRefundOrderResVo().getStatus())) {
            helper.setText(R.id.tv_after_sale_status,"已撤销退款申请 ");
            countDowntimeView.setVisibility(View.GONE);
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String times = sdf.format( new Date());
        long time= DateUtils.getReducenew(times,DateUtils.formatDate(item.getShopRefundOrderResVo().getEndTime(),DateUtils.TYPE_01));
        long day = time / 86400000;                         //以天数为单位取整
        long hour = time % 86400000 / 3600000;               //以小时为单位取整
        long min = time % 86400000 % 3600000 / 60000;       //以分钟为单位取整
        long seconds = time % 86400000 % 3600000 % 60000 / 1000;   //以秒为单位取整
        //以秒为单位取整
        if(time>0){
            countDowntimeView.initTime(day,hour,min,seconds);
            countDowntimeView.start();
        }else {
            countDowntimeView.setVisibility(View.GONE);
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
                Intent intent = new Intent(context, AfterSaleDetailActivity.class);
                intent.putExtra("orderId",item.getShopRefundOrderResVo().getId()+"");
                context.startActivity(intent);
            }
        });
    }
}
