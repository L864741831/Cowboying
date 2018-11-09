package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class UseCouponListAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {
    private Context context;
    public UseCouponListAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        LinearLayout lvBgId=helper.getView(R.id.lv_bg_id);
        LinearLayout unvalidityShowbgLv=helper.getView(R.id.unvalidity_showbg_lv);

        if(false){
            //优惠券是否可用 可用
            unvalidityShowbgLv.setVisibility(View.GONE);
            if(true){
                //如果选中
                lvBgId.setBackgroundResource(R.mipmap.choosecoupon);
            }else {
                lvBgId.setBackgroundResource(R.mipmap.unchoosecoupon);
            }
        }else {
            //优惠券是否可用 不可用
            unvalidityShowbgLv.setVisibility(View.VISIBLE);
            lvBgId.setBackgroundResource(R.mipmap.unusecoupon);
        }

    }
}
