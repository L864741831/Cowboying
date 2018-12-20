package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.zxing.BarcodeFormat;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.MyOrderListBean;
import com.ibeef.cowboying.bean.PayCodeBean;
import com.king.zxing.util.CodeUtils;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class PayTypeAdapter extends BaseQuickAdapter<PayCodeBean.BizDataBean.PayTypeListBean,BaseViewHolder> {
    private Context context;
    public PayTypeAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert( BaseViewHolder helper,PayCodeBean.BizDataBean.PayTypeListBean item) {
            helper.setText(R.id.tv_pay_type,item.getName()+"(￥"+item.getAmount()+")");
            helper.addOnClickListener(R.id.tv_pay_type);
        CheckBox checkBox = helper.getView(R.id.cb_pay_type);
        if (item.isChecked()){
            checkBox.setBackgroundResource(R.drawable.hascheck);
        }else {
            checkBox.setBackgroundResource(R.drawable.unhascheck);
        }


    }
}
