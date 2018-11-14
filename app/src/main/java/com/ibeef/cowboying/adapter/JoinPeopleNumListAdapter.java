package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.bean.JionPersonInfoResultBean;
import com.ibeef.cowboying.config.Constant;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class JoinPeopleNumListAdapter extends BaseQuickAdapter<JionPersonInfoResultBean.BizDataBean,BaseViewHolder> {
    private Context context;
    public JoinPeopleNumListAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, JionPersonInfoResultBean.BizDataBean item) {
        helper.setText(R.id.nickname_txt_id,item.getNickName())
                .setText(R.id.code_txt_id,""+item.getInviterId());
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .error(R.mipmap.meheaddefalut)
                //跳过内存缓存
                ;
        CircleImageView imageView= helper.getView(R.id.head_img_id);
        Glide.with(context).load(Constant.imageDomain+item.getHeadImage()).apply(options).into(imageView);
    }
}
