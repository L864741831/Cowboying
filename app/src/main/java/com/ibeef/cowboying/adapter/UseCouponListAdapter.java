package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.UseCouponListResultBean;
import com.ibeef.cowboying.utils.DateUtils;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class UseCouponListAdapter extends BaseQuickAdapter<UseCouponListResultBean.BizDataBean,BaseViewHolder> {
    private Context context;
    public UseCouponListAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, UseCouponListResultBean.BizDataBean item) {
        Double d = new Double(item.getNeedAmount());
        int i = d.intValue();
        Double d1 = new Double(item.getParValue());
        int i1 = d1.intValue();
        LinearLayout lvBgId=helper.getView(R.id.lv_bg_id);
        LinearLayout unvalidityShowbgLv=helper.getView(R.id.unvalidity_showbg_lv);
        helper.setText(R.id.money_txt_id,i1+"");
        helper.setText(R.id.enough_money_id,"· 满"+i+"元可用 ·");
        helper.setText(R.id.validity_time_id,"·有效期至"+DateUtils.formatDate(item.getUseEndTime(),DateUtils.TYPE_04));

        //useType使用类型（0：全部；1：养牛；2：拼牛；3：商城；）
        //状态（0：未使用；1：已使用；2：不可用）
        if("0".equals(item.getStatus())){
            //优惠券是否可用 可用
            unvalidityShowbgLv.setVisibility(View.GONE);
            if(1==item.getDefautChoose()){
                //如果选中
                lvBgId.setBackgroundResource(R.mipmap.choosecoupon);
            }else  if(0==item.getDefautChoose()){
                lvBgId.setBackgroundResource(R.mipmap.unchoosecoupon);
            }
        } else if("1".equals(item.getStatus())||"2".equals(item.getStatus())){
            //优惠券是否可用 不可用
//            unvalidityShowbgLv.setVisibility(View.VISIBLE);
            unvalidityShowbgLv.setVisibility(View.GONE);
            lvBgId.setBackgroundResource(R.mipmap.unusecoupon);
        }

        if("0".equals(item.getUseType())){
            helper.setText(R.id.coupon_type_id,"通用券");
        } else if("1".equals(item.getUseType())){
            helper.setText(R.id.coupon_type_id,"养牛券");
        }else if("2".equals(item.getUseType())){
            helper.setText(R.id.coupon_type_id,"拼牛券");
        }else if("3".equals(item.getUseType())){
            helper.setText(R.id.coupon_type_id,"商城券");
        }

    }
}
