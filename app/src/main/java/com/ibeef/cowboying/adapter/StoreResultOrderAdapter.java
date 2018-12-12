package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.MyOrderListDetailBean;
import com.ibeef.cowboying.config.Constant;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class StoreResultOrderAdapter extends BaseQuickAdapter<MyOrderListDetailBean.BizDataBean.ShopOrderProductResVosBean,BaseViewHolder> {
    private Context context;
    private String status;
    public StoreResultOrderAdapter(List data, Context context, int layout,String status) {
        super(layout, data);
        this.context=context;
        this.status=status;
    }

    @Override
    protected void convert(final BaseViewHolder helper, MyOrderListDetailBean.BizDataBean.ShopOrderProductResVosBean item) {

        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .error(R.mipmap.cowbeefimg)
                //跳过内存缓存
                ;
        Glide.with(context).load(Constant.imageDomain+item.getImageUrl()).apply(options).into((ImageView) helper.getView(R.id.store_img));
        helper.setText(R.id.store_name,item.getName())
                .setText(R.id.type_id,item.getSpecification())
                .setText(R.id.money_id,"￥"+item.getBuyPrice())
                .setText(R.id.num_id,"X"+item.getQuantity());
        ImageView imageView= helper.getView(R.id.iv_my_cows_xianxia);//角标
        //取货方式（1：快递；2：门店自提）
        if ("1".equals(status)) {
            imageView.setVisibility(View.GONE);
        } else  if ("2".equals(status)){
            imageView.setVisibility(View.VISIBLE);
        }
    }
}
