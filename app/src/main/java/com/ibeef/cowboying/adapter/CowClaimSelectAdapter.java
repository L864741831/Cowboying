package com.ibeef.cowboying.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.AdoptInfosResultBean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class CowClaimSelectAdapter extends BaseQuickAdapter<AdoptInfosResultBean.BizDataBean,BaseViewHolder> {
    private Context context;
    public CowClaimSelectAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, AdoptInfosResultBean.BizDataBean item) {

        helper.setText(R.id.cows_identify_id,item.getCode());
        helper.addOnClickListener(R.id.cows_identify_rv);

    }
}
