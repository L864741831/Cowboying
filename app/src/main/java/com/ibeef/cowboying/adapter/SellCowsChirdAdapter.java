package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class SellCowsChirdAdapter extends BaseQuickAdapter<MyCowsOrderListBean.BizDataBean.CattleListBean,BaseViewHolder> {
    private Context context;
    private String pastureName;
    public SellCowsChirdAdapter(List data, Context context, String pastureName) {
        super(R.layout.item_sell_cows_chird, data);
        this.context=context;
        this.pastureName=pastureName;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCowsOrderListBean.BizDataBean.CattleListBean item) {

    }
}
