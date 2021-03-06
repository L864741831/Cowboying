package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.MyCowsOrderListDetailBean;
import com.ibeef.cowboying.config.Constant;

import java.util.List;

/**
 *
 * @author Administrator*/
public class MyCowsDetailListAdapter extends BaseQuickAdapter<MyCowsOrderListDetailBean.BizDataBean.CattleListBean,BaseViewHolder> {
    private Context context;
    private String status;
    private String pastureName;
    private  String schemeType;
    public MyCowsDetailListAdapter(List data,String status,String schemeType,String pastureName, Context context, int layout) {
        super(layout, data);
        this.context=context;
        this.status=status;
        this.pastureName=pastureName;
        this.schemeType=schemeType;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCowsOrderListDetailBean.BizDataBean.CattleListBean item) {

        Log.i("/adopt/order/detail", "pastureName: ::::"+pastureName);
        helper .setText(R.id.tv_ranch_id,pastureName)
                .setText(R.id.money_id,"安格斯牛")
               .setText(R.id.num_id,"初始重量："+item.getCattleWeight()+"kg");

        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .error(R.mipmap.jzsb)
                //跳过内存缓存
                ;
        ImageView imageView= helper.getView(R.id.store_img);
        ImageView imageView2= helper.getView(R.id.iv_my_cows_dingqi);
        Glide.with(context).load(Constant.imageDomain+item.getCattleImage()).apply(options).into(imageView);
 //   订单状态（1:未付款；2：已付款未分配；3：已分配；4：已分配锁定期中；5：出售中；6:交易完成；9；交易关闭）
//    不用给领养类型，活期是3 定期只有4，不会为3
        if ("1".equals(status)) {
            //待付款(基础界面只显示待付款的几个条目，其他都隐藏掉了)
            if("3".equals(schemeType)){
                helper.setText(R.id.store_name,item.getCattleCode())
                        .setText(R.id.money_id,item.getCattleName());
            }else {
                helper.setText(R.id.store_name,"待付款");
            }

        } else if ("2".equals(status)) {
            //已付款未分配（比待付款多了一个支付时间和支付时间）
            if("3".equals(schemeType)){
                helper.setText(R.id.store_name,item.getCattleCode())
                        .setText(R.id.money_id,item.getCattleName());
            }else {
                helper.setText(R.id.store_name,"待分配牛只");
            }
        } else if ("3".equals(status)) {
            //已分配（这里只有活期养牛这一种）
            if("3".equals(schemeType)){
                helper.setText(R.id.store_name,item.getCattleCode())
                        .setText(R.id.money_id,item.getCattleName());
            }else {
                helper.setText(R.id.store_name,"牛只编号:"+item.getCattleCode());
            }
        } else if ("4".equals(status)) {
            //已分配锁定期中（只有定期养牛才会有这个状态）
            if("3".equals(schemeType)){
                helper.setText(R.id.store_name,item.getCattleCode())
                        .setText(R.id.money_id,item.getCattleName());
            }else {
                helper.setText(R.id.store_name,"牛只编号:"+item.getCattleCode());
            }
        } else if ("5".equals(status)) {
            //出售中（不分活期和定期。。定期到期后会自动转为活期的）
            if("3".equals(schemeType)){
                helper.setText(R.id.store_name,item.getCattleCode())
                        .setText(R.id.money_id,item.getCattleName());
            }else {
                helper.setText(R.id.store_name,"牛只编号:"+item.getCattleCode());
            }
        } else if ("6".equals(status)) {
            //交易完成（比交易完成多一个出售成功时间）
            if("3".equals(schemeType)){
                helper.setText(R.id.store_name,item.getCattleCode())
                        .setText(R.id.money_id,item.getCattleName());
            }else {
                helper.setText(R.id.store_name,"牛只编号:"+item.getCattleCode());
            }
        }else if ("9".equals(status)) {
            //交易关闭
            if("3".equals(schemeType)){
                helper.setText(R.id.store_name,item.getCattleCode())
                        .setText(R.id.money_id,item.getCattleName());
            }else {
                helper.setText(R.id.store_name,"交易关闭");
            }

        }

        //方案类型（1：活期；2：定期；3：新人活动）
        if ("4".equals(status)) {
            imageView2.setVisibility(View.VISIBLE);
            imageView2.setImageResource(R.mipmap.img_my_cows_dingqi);
        } else  if ("3".equals(schemeType)){
            imageView2.setVisibility(View.VISIBLE);
            imageView2.setImageResource(R.mipmap.newmangoods);
            helper.getView(R.id.num_id).setVisibility(View.GONE);
        }else {
            imageView2.setVisibility(View.GONE);
        }
    }
}
