package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.MyCowsOrderListDetailBean;

import java.util.List;

import rxfamily.bean.BaseBean;

/**
 *
 * @author Administrator*/
public class MyCowsDetailListAdapter extends BaseQuickAdapter<MyCowsOrderListDetailBean.BizDataBean.CattleListBean,BaseViewHolder> {
    private Context context;
    private String status;
    private String pastureName;
    public MyCowsDetailListAdapter(List data,String status,String pastureName, Context context, int layout) {
        super(layout, data);
        this.context=context;
        this.status=status;
        this.pastureName=pastureName;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCowsOrderListDetailBean.BizDataBean.CattleListBean item) {

        helper .setText(R.id.tv_ranch_id,pastureName)
                .setText(R.id.money_id,"安格拉斯牛")
                .setText(R.id.num_id,"×2");

        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                //跳过内存缓存
                ;
        ImageView imageView= helper.getView(R.id.store_img);
//        Glide.with(mContext).load(item.getMessage()).apply(options).into(imageView);
        //    订单状态（1:未付款；2：已付款未分配；3：已分配；4：已分配锁定期中；5：出售中；6:交易完成；9；交易关闭）
//    不用给领养类型，活期是3 定期只有4，不会为3
        if ("1".equals(status)) {
            //待付款(基础界面只显示待付款的几个条目，其他都隐藏掉了)
            helper.setText(R.id.store_name,"待付款");
        } else if ("2".equals(status)) {
            //已付款未分配（比待付款多了一个支付时间和支付时间）
            helper.setText(R.id.store_name,"待分配牛只");
        } else if ("3".equals(status)) {
            //已分配（这里只有活期养牛这一种）
            helper.setText(R.id.store_name,"牛只编号:"+item.getCattleCode());
        } else if ("4".equals(status)) {
            //已分配锁定期中（只有定期养牛才会有这个状态）
            helper.setText(R.id.store_name,"牛只编号:"+item.getCattleCode());
        } else if ("5".equals(status)) {
            //出售中（不分活期和定期。。定期到期后会自动转为活期的）
            helper.setText(R.id.store_name,"牛只编号:"+item.getCattleCode());
        } else if ("6".equals(status)) {
            //交易完成（比交易完成多一个出售成功时间）
            helper.setText(R.id.store_name,"交易完成");
        }else if ("9".equals(status)) {
            //交易关闭
            helper.setText(R.id.store_name,"交易关闭");
        }
    }
}
