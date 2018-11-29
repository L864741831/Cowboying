package com.ibeef.cowboying.adapter;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.MyDiscountCouponListBean;
import com.ibeef.cowboying.utils.DateUtils;

import java.util.List;
import rxfamily.bean.BaseBean;


public class DiscountCoupondapter extends BaseQuickAdapter<MyDiscountCouponListBean.BizDataBean, BaseViewHolder> {
    private Context context;
    private String stadus;
    public DiscountCoupondapter(List data, Context context,String stadus) {
        super(R.layout.item_my_discount_list, data);
        this.context=context;
        this.stadus=stadus;
    }

    @Override
    protected void convert(BaseViewHolder helper, final MyDiscountCouponListBean.BizDataBean item) {

        Double d = new Double(item.getNeedAmount());
        int i = d.intValue();
        Double d1 = new Double(item.getParValue());
        int i1 = d1.intValue();
          helper.setText(R.id.tv_type_id,item.getName())
                .setText(R.id.tv_condition_1_id,"· 满"+i+"元可用 ·")
                .setText(R.id.tv_time_id,DateUtils.formatDate(item.getUseStartTime(),DateUtils.TYPE_04)+"-"+DateUtils.formatDate(item.getUseEndTime(),DateUtils.TYPE_04))
                .setText(R.id.tv_price_id,i1+"");
          //getUseType 使用类型（0：全部；1：养牛；2：拼牛；3：商城；） getUseSchemeType适用方案类型（0：所有；1：活期；2：定期）
          if("0".equals(item.getUseType())){
              if("0".equals(item.getUseSchemeType())){
                  helper.setText(R.id.tv_condition_2_id,"本平台内全场通用");
              }else  if("1".equals(item.getUseSchemeType())){
                  helper.setText(R.id.tv_condition_2_id,"仅可参与活期养牛使用");
              }else  if("2".equals(item.getUseSchemeType())){
                  helper.setText(R.id.tv_condition_2_id,"仅可参与定期养牛使用");
              }
          }else  if("1".equals(item.getUseType())){
              if("0".equals(item.getUseSchemeType())){
                  helper.setText(R.id.tv_condition_2_id,"仅可参与养牛使用");
              }else if("1".equals(item.getUseSchemeType())){
                  helper.setText(R.id.tv_condition_2_id,"仅可参与活期养牛使用");
              }else  if("2".equals(item.getUseSchemeType())){
                  helper.setText(R.id.tv_condition_2_id,"仅可参与定期养牛使用");
              }
          }else  if("2".equals(item.getUseType())){
              helper.setText(R.id.tv_condition_2_id,"仅可参与拼牛使用");
          }else  if("3".equals(item.getUseType())){
              helper.setText(R.id.tv_condition_2_id,"仅可购买牛肉使用");
          }

          TextView tv_commit_id=helper.getView(R.id.tv_commit_id);
          helper.addOnClickListener(R.id.tv_commit_id);
          RelativeLayout rl_bg=helper.getView(R.id.rl_bg);
          RelativeLayout rv_show_id=helper.getView(R.id.rv_show_id);
          ImageView img_show_id=helper.getView(R.id.img_show_id);
          if("2".equals(stadus)){
              rl_bg.setBackgroundResource(R.mipmap.unusecouponbg);
              if("0".equals(item.getStatus())||"2".equals(item.getStatus())){
                  img_show_id.setImageResource(R.mipmap.hasuntime);
              }else   if("1".equals(item.getStatus())){
                  //状态（0：未使用；1：已使用；2：不可用）
                  img_show_id.setImageResource(R.mipmap.hasusebg);
              }
              rv_show_id.setVisibility(View.VISIBLE);
              tv_commit_id.setBackgroundResource(R.mipmap.unuserbtnbg);
          }else {
              rl_bg.setBackgroundResource(R.mipmap.my_discount_coupon_bg);
              rv_show_id.setVisibility(View.GONE);
              tv_commit_id.setBackgroundResource(R.mipmap.my_discount_coupon_commit);
          }

    }
}

