package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;
import com.ibeef.cowboying.config.Constant;

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
    private String schemeType;

    public SellCowsChirdAdapter(List data, Context context,String schemeType, String pastureName) {
        super(R.layout.item_sell_cows_chird, data);
        this.context=context;
        this.pastureName=pastureName;
        this.schemeType=schemeType;

    }

    @Override
    protected void convert(final BaseViewHolder helper, MyCowsOrderListBean.BizDataBean.CattleListBean item) {
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .error(R.mipmap.jzsb)
                //跳过内存缓存
                ;
        Glide.with(mContext).load(Constant.imageDomain+item.getCattleImage()).apply(options).into((ImageView) helper.getView(R.id.show_img_id));
        CheckBox all_ck_id=helper.getView(R.id.all_ck_id);
        if(0==item.getDefautChoose()){
            all_ck_id.setBackground(ContextCompat.getDrawable(context, R.drawable.unhascheck));
        }else {
            all_ck_id.setBackground(ContextCompat.getDrawable(context, R.drawable.hascheck));
        }
        helper.setText(R.id.pasture_name_id,pastureName)
                .setText(R.id.get_money_id,item.getCattleCode());
        helper.addOnClickListener(R.id.all_ck_id);
        if("3".equals(schemeType)){
              helper.setText(R.id.cows_name_id,"虚拟牛只");
        }else {
            helper.setText(R.id.cows_name_id,"安格斯牛");
        }

    }

}
