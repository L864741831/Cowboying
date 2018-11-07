package com.ibeef.cowboying.adapter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ibeef.cowboying.R;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/7 14:10
 * @describe
 * @package com.ranhan.cowboying.adapter
 **/
public class CowClaimPastRecordAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {
    private Context context;
    public CowClaimPastRecordAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {

        helper.addOnClickListener(R.id.see_people_num_rv);

    }
}
