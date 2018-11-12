package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.WalletRecordResultBean;

import java.util.List;

import rxfamily.bean.BaseBean;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class AssetDetailListAdapter extends BaseQuickAdapter<WalletRecordResultBean.BizDataBean,BaseViewHolder> {
    private Context context;
    public AssetDetailListAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, WalletRecordResultBean.BizDataBean item) {

        helper.setText(R.id.show_reson_id,"认领牛只")
                .setText(R.id.show_time_id,"2018-08-25 13:00")
                .setText(R.id.show_money_id,"+5000");
        TextView textView=helper.getView(R.id.show_money_id);

        if(true){
            //如果金额是add的
            textView.setTextColor(context.getResources().getColor(R.color.colorGolds));
        }else {
            //金额是减少的
            textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }
    }
}
