package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.ActiveSchemeResultBean;
import com.ibeef.cowboying.config.Constant;

import java.text.NumberFormat;
import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class BuyCowListAdapter extends BaseQuickAdapter<ActiveSchemeResultBean.BizDataBean,BaseViewHolder> {
    private Context context;
    public BuyCowListAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ActiveSchemeResultBean.BizDataBean item) {
        Double d1 = new Double(item.getPrice());
        int i1 = d1.intValue();
        helper.setText(R.id.parsture_addr_id,item.getPastureName())
                .setText(R.id.periods_txt_id,item.getCode()+"期")
                .setText(R.id.vip_level_txt_id,"· VIP"+item.getVipLevel()+" ·")
                .setText(R.id.minlevle_txt_id,i1+"起投")
                .setText(R.id.percent_txt_id,item.getExpectRate());

        int num1=item.getTotalStock()-item.getStock();
        int num2=item.getTotalStock();
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format( (float)num1 / num2 * 100);
        helper.setText(R.id.minpercent_txt_id,"进度"+result+"%");
        SeekBar seekbarId=helper.getView(R.id.seekbar_id);
        seekbarId.setProgress(Math.round(Float.parseFloat(result)));

        helper.addOnClickListener(R.id.now_claim_btn_id);
        seekbarId.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .error(R.mipmap.jzsb)
                //跳过内存缓存
                ;
        ImageView imageView= helper.getView(R.id.show_img_id);
        Glide.with(context).load(Constant.imageDomain+item.getImageUrl()).apply(options).into(imageView);
        TextView textView=helper.getView(R.id.now_claim_btn_id);

        if("1".equals(item.getCurStatus())){
            //当前类型（1：可以认领；2：即将开始；3：领完）
            textView.setBackground(context.getResources().getDrawable(R.drawable.shape_oval_white2_btn));
            textView.setTextColor(context.getResources().getColor(R.color.black));
            textView.setText("立即认领");
        }else  if("2".equals(item.getCurStatus())) {
            textView.setBackground(context.getResources().getDrawable(R.drawable.shape_oval_gold_btn));
            textView.setTextColor(context.getResources().getColor(R.color.black));
            textView.setText("即将开始");
        }else  if("3".equals(item.getCurStatus())) {
            textView.setBackground(context.getResources().getDrawable(R.drawable.shape_oval_colorhui_btn));
            textView.setTextColor(context.getResources().getColor(R.color.white));
            textView.setText("已售罄");
        }

        RelativeLayout rv_progress_id=helper.getView(R.id.rv_progress_id);
        if("1".equals(item.getType())){
            helper.setText(R.id.percent_way_txt_id,"锁定期七天");
            rv_progress_id.setVisibility(View.VISIBLE);
        }else   if("2".equals(item.getType())){
            helper.setText(R.id.percent_way_txt_id,"锁定期"+item.getLockMonths()+"个月");
            rv_progress_id.setVisibility(View.VISIBLE);
        }else if("3".equals(item.getType())){
            helper.setText(R.id.parsture_addr_id,"虚拟牧场")
                    .setText(R.id.periods_txt_id,"新手福利")
                    .setText(R.id.percent_way_txt_id,"锁定期七天")
                    .setText(R.id.minlevle_txt_id,"预估年化收益")
                    .setText(R.id.vip_level_txt_id,"· VIP1·")
                    .setText(R.id.minpercent_txt_id,"每日限量发放"+item.getDayStock()+"份");
            textView.setBackground(context.getResources().getDrawable(R.drawable.shape_oval_white2_btn));
            textView.setTextColor(context.getResources().getColor(R.color.black));
            textView.setText("一元体验");
            rv_progress_id.setVisibility(View.INVISIBLE);
            Glide.with(context).load(Constant.imageDomain+item.getBannerImageUrl()).apply(options).into(imageView);
        }
    }
}
