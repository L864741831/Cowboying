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
public class BuyCowListAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {
    private Context context;
    public BuyCowListAdapter(List data, Context context, int layout) {
        super(layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {

        SeekBar seekbarId=helper.getView(R.id.seekbar_id);
        helper.addOnClickListener(R.id.now_claim_btn_id);
        seekbarId.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }
}
