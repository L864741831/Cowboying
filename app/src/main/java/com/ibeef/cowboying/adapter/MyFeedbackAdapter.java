package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.MyFeedbackResultBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ls
 * @date on 2018/10/23 17:16
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class MyFeedbackAdapter extends BaseQuickAdapter<MyFeedbackResultBean.BizDataBean,BaseViewHolder> {

    private Context mContext;
    public MyFeedbackAdapter(Context context, List<MyFeedbackResultBean.BizDataBean> list, int layout){
        super(layout, list);
        this.mContext=context;
    }
    @Override
    protected void convert(BaseViewHolder helper, MyFeedbackResultBean.BizDataBean item) {
        helper.setText(R.id.question_txt_id,item.getContent());

        //已回复
        if("2".equals(item.getStatus())){
            helper.setText(R.id.response_stadus_id,"已回复")
            .setText(R.id.response_nane_id,"口袋牧场回复:")
            .setText(R.id.response_info_id,item.getReplyContent())
            .setText(R.id.time_show_id,item.getReplyTime());
            //显示回复内容
            helper.setVisible(R.id.response_rv,true);
            helper.setVisible(R.id.ry_id,false);
        }else if("1".equals(item.getStatus())){
            helper.setText(R.id.response_stadus_id,"未回复")
            .setText(R.id.time_show_id,item.getCreateTime());
            //隐藏回复内容
            helper.setVisible(R.id.response_rv,false);
        }

        //如果有图片，展示图片
        if(item.getImageList().size()>0){
            helper.setVisible(R.id.ry_id,true);
            RecyclerView recyclerView=helper.getView(R.id.ry_id);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext,4));
            List<String> beanList=new ArrayList<>();
            beanList.addAll(item.getImageList());
            MyFeedbackImgAdapter myFeedbackImgAdapter=new MyFeedbackImgAdapter(mContext,beanList,R.layout.my_feedbackimg_item);
            recyclerView.setAdapter(myFeedbackImgAdapter);

        }else {
            helper.setVisible(R.id.ry_id,false);
        }


    }
}
