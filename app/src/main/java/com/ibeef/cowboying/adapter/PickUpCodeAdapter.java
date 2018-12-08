package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.zxing.BarcodeFormat;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.MyOrderListBean;
import com.ibeef.cowboying.bean.MyOrderListDetailBean;
import com.king.zxing.util.CodeUtils;

import java.util.List;

import rxfamily.bean.BaseBean;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class PickUpCodeAdapter extends BaseQuickAdapter<MyOrderListBean.BizDataBean,BaseViewHolder> {
    private Context context;
    public PickUpCodeAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MyOrderListBean.BizDataBean item) {

           helper.setText(R.id.tv_goods_name,"订单编号"+item.getShopOrderResVo().getCode())
                   .setText(R.id.tv_goods_norm,item.getShopOrderResVo().getCode())
                   .setText(R.id.tv_goods_num,""+item.getShopOrderProductResVos().size())     //todo   没有
                   .setText(R.id.tv_goods_price,"￥"+item.getShopOrderResVo().getPayAmount())
                   .setText(R.id.tv_code,item.getShopOrderResVo().getReceiveCode().substring(0,4)+"******查看数字");
        helper.getView(R.id.tv_code).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.setText(R.id.tv_code,item.getShopOrderResVo().getReceiveCode());
            }
        });
        ImageView imageView = helper.getView(R.id.iv_icon);
        if (item.getShopOrderResVo().getReceiveCode()!=null){
            Bitmap barCode = CodeUtils.createBarCode(item.getShopOrderResVo().getReceiveCode(), BarcodeFormat.CODE_128, 800, 200);
            imageView.setImageBitmap(barCode);
        }


        if ("0".equals(item.getShopOrderResVo().getStatus())) {
            //待付款
        } else if ("1".equals(item.getShopOrderResVo().getStatus())) {
            //已支付{包含待发货和待取货}
            if ("1".equals(item.getShopOrderResVo().getReceiveType())){
                //快递
            }else if ("2".equals(item.getShopOrderResVo().getReceiveType())){
                //门店自取
                helper.setText(R.id.tv_goods_status,"待取货");
            }
        } else if ("2".equals(item.getShopOrderResVo().getStatus())) {
            //待收货
        } else if ("3".equals(item.getShopOrderResVo().getStatus())) {
            //交易成功{包含快递和线下门店}
            helper.setText(R.id.tv_goods_status,"已取货");
        } else if ("6".equals(item.getShopOrderResVo().getStatus())) {
            //交易关闭
        } else if ("4".equals(item.getShopOrderResVo().getStatus())) {
            //退款中
        }else if ("5".equals(item.getShopOrderResVo().getStatus())) {
            //已退款
        }
    }
}
