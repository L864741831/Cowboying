package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.MyContractListBean;

import java.util.List;

import rxfamily.bean.BaseBean;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class MyContractAdapter extends BaseQuickAdapter<MyContractListBean.BizDataBean,BaseViewHolder> {
    private Context context;
    public MyContractAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyContractListBean.BizDataBean item) {

          helper.setText(R.id.issue_txt_id,item.getSchemeCode())
                .setText(R.id.cows_txt_id,item.getCattleName())
                .setText(R.id.time_txt_id,item.getCreateTime());
    }
}
