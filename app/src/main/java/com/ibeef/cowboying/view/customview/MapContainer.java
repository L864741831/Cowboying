package com.ibeef.cowboying.view.customview;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class MapContainer extends RelativeLayout {
    private NestedScrollView scrollView;
    public MapContainer(Context context) {
        super(context);
    }
 
    public MapContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
 
    public void setScrollView(NestedScrollView scrollView) {
        this.scrollView = scrollView;
    }
 
    //当点击到地图的时候，让ScrollView不拦截事件，把事件传递到子View
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            scrollView.requestDisallowInterceptTouchEvent(false);
        } else {
            scrollView.requestDisallowInterceptTouchEvent(true);
        }
        return false;
    }
 
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
