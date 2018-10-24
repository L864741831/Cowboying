package com.ranhan.cowboying.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ranhan.cowboying.R;

import java.util.ArrayList;
import java.util.List;

import rxfamily.bean.BaseBean;

/**
 * @author ls
 * @date on 2018/10/23 17:16
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class MyFeedbackAdapter extends BaseQuickAdapter<BaseBean,BaseViewHolder> {

    private Context mContext;
    public MyFeedbackAdapter(Context context, List<BaseBean> list, int layout){
        super(layout, list);
        this.mContext=context;
    }
    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {
        helper.setText(R.id.time_show_id,"2015-23-23")
                .setText(R.id.question_txt_id,"亲，我买的牛肉想退换货怎么弄？");

        //已回复
        if("0".equals(item.getStatus())){
            helper.setText(R.id.response_stadus_id,"已回复")
            .setText(R.id.response_nane_id,"口袋牧场回复:")
            .setText(R.id.response_info_id,"您的牛肉已出仓库，请到附近的实体店兑换您的牛肉已出仓库，请到附近的实体店兑换您的牛肉已出仓库，请到附近的实体店兑换:");
            //显示回复内容
            helper.setVisible(R.id.response_rv,true);
            helper.setVisible(R.id.ry_id,false);
        }else {
            helper.setText(R.id.response_stadus_id,"未回复");
            //隐藏回复内容
            helper.setVisible(R.id.response_rv,false);
            //如果有图片，展示图片
            if(true){
                helper.setVisible(R.id.ry_id,true);
                RecyclerView recyclerView=helper.getView(R.id.ry_id);
                recyclerView.setLayoutManager(new GridLayoutManager(mContext,4));
                List<BaseBean> beanList=new ArrayList<>();
                BaseBean baseBean=new BaseBean();
                baseBean.setMessage("https://upload-images.jianshu.io/upload_images/2057501-a4d09d5892ca1518.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/868/format/webp");
                beanList.add(baseBean);
                MyFeedbackImgAdapter myFeedbackImgAdapter=new MyFeedbackImgAdapter(mContext,beanList,R.layout.my_feedbackimg_item);
                recyclerView.setAdapter(myFeedbackImgAdapter);

            }else {
                helper.setVisible(R.id.ry_id,false);
            }

        }


    }
}
