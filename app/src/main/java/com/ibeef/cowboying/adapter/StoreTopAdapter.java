package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.StoreCarResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.view.activity.PlayerVideoActivity;
import com.ibeef.cowboying.view.customview.AmountViewStoreBeef;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.richeditor.RichEditor;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class StoreTopAdapter extends BaseQuickAdapter<StoreCarResultBean,BaseViewHolder> {
    private Context context;
    public StoreTopAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(final BaseViewHolder helper,final StoreCarResultBean item) {

        helper.addOnClickListener(R.id.last_go_img);
        helper.addOnClickListener(R.id.first_go_img);

        final AmountViewStoreBeef amountViewStoreBeef=helper.getView(R.id.amout_num_id);
        amountViewStoreBeef.setGoods_storage(1000000);
        amountViewStoreBeef.intEdit(item.getNum()+"");

        amountViewStoreBeef.setOnAmountChangeListener(new AmountViewStoreBeef.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                Intent intent1=new Intent();
                intent1.setAction("com.ibeef.cowboying.storenum");
                intent1.putExtra("num",amount);
                intent1.putExtra("position",helper.getAdapterPosition());
                context.sendBroadcast(intent1);
            }
        });
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .error(R.mipmap.cowbeefimg)
                //跳过内存缓存
                ;
        RequestOptions options1 = new RequestOptions()
                .skipMemoryCache(true)
                .error(R.mipmap.cowone)
                //跳过内存缓存
                ;
        Glide.with(context).load(Constant.imageDomain).apply(options).into((ImageView) helper.getView(R.id.goods_info_img));
        Glide.with(context).load(Constant.imageDomain).apply(options1).into((ImageView) helper.getView(R.id.cow_nine_img));
        helper.setText(R.id.nane_beef_id,"当前位置"+helper.getAdapterPosition())
                .setText(R.id.beef_price_id,"价格：00元")
                .setText(R.id.beef_stock_id,"库存：00/袋")
                .setText(R.id.beef_size_id,"规格：00g/袋");
        RichEditor   richEditId=helper.getView(R.id.rich_edit_id);
        richEditId.setEditorFontSize(16);
        richEditId.setEditorFontColor(Color.BLACK);
        richEditId.setInputEnabled(false);
        richEditId.setPadding(3, 5, 5, 5);
        richEditId.loadCSS("file:///android_asset/img.css");
        richEditId.setHtml("");

        RecyclerView  ryBottomId=helper.getView(R.id.ry_bottom_id);
        ryBottomId.setHasFixedSize(true);
        ryBottomId.setNestedScrollingEnabled(false);
        ryBottomId.setLayoutManager(new GridLayoutManager(context,2));
       final List<Object> baseBeans = new ArrayList<>();
        for (int i=0;i<2;i++){
            baseBeans.add(new Object());
        }
        final StoreBottomAdapter storeBottomAdapter=new StoreBottomAdapter(baseBeans,context,R.layout.item_store_bottm);
        ryBottomId.setAdapter(storeBottomAdapter);

        storeBottomAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(context, PlayerVideoActivity.class);
//                intent.putExtra("video_url",item.getPlayUrl());
//                intent.putExtra("title",item.getName());
//                intent.putExtra("coverUrl",item.getCoverUrl());
//                context.startActivity(intent);
            }
        });
        final TextView seeMore=helper.getView(R.id.see_more_id);
        seeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //视频查看更多
                for (int j=0;j<3;j++){
                    baseBeans.add(new Object());
                    storeBottomAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
