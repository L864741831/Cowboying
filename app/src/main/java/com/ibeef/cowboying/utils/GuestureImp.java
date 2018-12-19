package com.ibeef.cowboying.utils;
 
import android.content.Context;
import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
 
public class GuestureImp implements OnGestureListener{
 
	Context context;
	View view;
	String tag="me";
	
	
	public GuestureImp(Context ct,View vw) {
		// TODO Auto-generated constructor stub
		context=ct;
		view=vw;
	}
 
	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		Log.e(tag, "down-"+"x:"+arg0.getX()+"y:"+arg0.getY());
		
		return true;
	}
 
	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		
		return true;
	}
 
	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		Log.e(tag, "onLongPress-"+"x:"+arg0.getX()+"y:"+arg0.getY());
	}
 
	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		Log.e(tag, "onScroll-"+"x:"+arg0.getX()+"y:"+arg0.getY());
		return false;
	}
 
	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		Log.e(tag, "onShowPress-"+"x:"+arg0.getX()+"y:"+arg0.getY());
	}
 
	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		Log.e(tag, "onSingleTapUp-"+"x:"+arg0.getX()+"y:"+arg0.getY());
		return false;
	}
 
}
