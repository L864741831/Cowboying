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
public class AfterSaleDetailAdapter extends BaseQuickAdapter<MyOrderListDetailBean.BizDataBean.ShopOrderProductResVosBean,BaseViewHolder> {
    private Context context;
    private String status;
    public AfterSaleDetailAdapter(List data,String status, Context context, int layout) {
        super(layout, data);
        this.context=context;
        this.status=status;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyOrderListDetailBean.BizDataBean.ShopOrderProductResVosBean item) {
        helper.setText(R.id.tv_goods_name,item.getName())
                .setText(R.id.tv_goods_norm,""+item.getSpecification())
                .setText(R.id.tv_goods_weight,"￥"+item.getBuyPrice())
                .setText(R.id.tv_goods_num,"×"+item.getQuantity());
        ImageView imageView= helper.getView(R.id.iv_my_cows_xianxia);//角标
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .error(R.mipmap.jzsb)
                //跳过内存缓存
                ;
        Glide.with(mContext).load(Constant.imageDomain+item.getImageUrl()).apply(options).into((ImageView) helper.getView(R.id.iv_icon));
        //取货方式（1：快递；2：门店自提）
        if ("1".equals(status)) {
            imageView.setVisibility(View.GONE);
        } else  if ("2".equals(status)){
            imageView.setVisibility(View.VISIBLE);
        }
    }
}
