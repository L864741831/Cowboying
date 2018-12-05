package com.ibeef.cowboying.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import rxfamily.bean.BaseBean;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class AfterSaleAdapter extends BaseQuickAdapter<BaseBean,BaseViewHolder> {
    private Context context;
    public AfterSaleAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {
//        if(SDCardUtil.isNullOrEmpty(item.getContractType())){
//            helper.setText(R.id.title_txt_id,"养牛合同");
//        }else {
//            if("1".equals(item.getContractType())){
//                helper.setText(R.id.title_txt_id,"养牛合同");
//            } else if("2".equals(item.getContractType())){
//                helper.setText(R.id.title_txt_id,"拼牛合同");
//            }
//        }
//
//          helper.setText(R.id.issue_txt_id,item.getSchemeCode())
//                .setText(R.id.cows_txt_id,item.getCattleName())
//                .setText(R.id.time_txt_id,item.getCreateTime());
    }
}
