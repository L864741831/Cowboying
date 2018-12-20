package com.ibeef.cowboying.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.ibeef.cowboying.base.HomeBannerBase;
import com.ibeef.cowboying.bean.HomeAllVideoResultBean;
import com.ibeef.cowboying.bean.HomeBannerResultBean;
import com.ibeef.cowboying.bean.HomeSellCowNumResultBean;
import com.ibeef.cowboying.bean.HomeVideoResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.HomeBannerPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * 
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver implements HomeBannerBase.IView{
	private static final String TAG = "JIGUANG-Example";
	private static String pageUrl="";
	private static String pageId="";
	private HomeBannerPresenter homeBannerPresenter;
	private HomeBannerResultBean homeBannerResultBean;
	private Map<String, String> reqData;
	private String token;
	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
				Bundle bundle = intent.getExtras();
				printBundle(bundle);
				token= Hawk.get(HawkKey.TOKEN);
				reqData = new HashMap<>();
				reqData.put("Authorization",token);
				reqData.put("version","1.0");
				homeBannerPresenter=new HomeBannerPresenter(this);
				homeBannerPresenter.getHomeBanner(reqData);
				switch (pageUrl){
					case "adopt_scheme_list":
						//养牛方案列表
						Intent intent11=new Intent(context,BuyCowsPlanActivity.class);
						intent11.putExtra("isAd",true);
						context.startActivity(intent11);
						break;
					case "adopt_scheme_detail":
						//养牛方案详情
						Intent intent4=new Intent(context,CowsClaimActivity.class);
						intent4.putExtra("schemId",pageId);
						intent4.putExtra("isAd",true);
						context.startActivity(intent4);
						break;
					case "adop_order_list":
						//养牛订单列表
						Intent intent2=new Intent(context,MyCowsActivity.class);
						intent2.putExtra("isAd",true);
						context.startActivity(intent2);
						break;
					case "adop_order_detail":
						//养牛订单详情
						Intent intent3 = new Intent(context, MyCowsDetailActivity.class);
						intent3.putExtra("orderId",pageId);
						intent3.putExtra("isAd",true);
						context.startActivity(intent3);
						break;
					case "pasture_list":
						//合作牧场列表
						Intent intent5=new Intent(context,RanchConsociationActivity.class);
						intent5.putExtra("isAd",true);
						context.startActivity(intent5);
						break;
					case "shop_product_list":
						//商城商品列表
						Intent intent1=new Intent(context,MainActivity.class);
						intent1.putExtra("index",1);
						context.startActivity(intent1);
						break;
					case "shop_order_list":
						//商城订单列表
						Intent intent6=new Intent(context,MyOrderActivity.class);
						intent6.putExtra("isAd",true);
						context.startActivity(intent6);
						break;
					case "shop_order_detail":
						//商城订单详情
						Intent intent7 = new Intent(context, MyOrderDetailActivity.class);
						intent7.putExtra("orderId",pageId);
						intent7.putExtra("isAd",true);
						context.startActivity(intent7);
						break;
					case "total_assets":
						//总资产
						Intent intent8=new Intent(context,MyAllMoneyActivity.class);
						intent8.putExtra("isAd",true);
						context.startActivity(intent8);
						break;
					case "coupon_list":
						//优惠券列表
						Intent intent9=new Intent(context,DiscountCouponActivity.class);
						intent9.putExtra("isAd",true);
						context.startActivity(intent9);
						break;
					case "contract_list":
						//合同列表
						Intent intent10=new Intent(context,MyContractActivity.class);
						intent10.putExtra("isAd",true);
						context.startActivity(intent10);
						break;
					case "vip_card_detail":
						//会员卡详情
						context.startActivity(new Intent(context,VipCardActivity.class));
						break;
					case "new_welfare":
						//新人福利
						if(!SDCardUtil.isNullOrEmpty(homeBannerResultBean)){
							Intent intent12=new Intent(context,NewManwelfareActivity.class);
							intent12.putExtra("infos",homeBannerResultBean.getBizData().getNewUserActivity());
							context.startActivity(intent12);
						}
						break;
					default:
						break;
				}
			}
		} catch (Exception e){

		}

	}

	// 打印所有的 intent extra 数据
	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
			} else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
				if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
					Log.e(TAG, "This message has no Extra data");
					continue;
				}

				try {
					JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
					Iterator<String> it =  json.keys();

					while (it.hasNext()) {
						String myKey = it.next();
						sb.append("\nkey:" + key + ", value: [" +
								myKey + " - " +json.optString(myKey) + "]");
						if("pageUrl".equals(myKey)){
							pageUrl=json.optString(myKey);
							Log.e(Constant.TAG,pageUrl+"?????pageUrl");
						}
						if("pageParams".equals(myKey)){
							pageId=json.optString(myKey);
							Log.e(Constant.TAG,pageId+"?????pageId");
						}
					}
				} catch (JSONException e) {
					Log.e(TAG, "Get message extra JSON error!");
				}

			} else {
				sb.append("\nkey:" + key + ", value:" + bundle.get(key));
			}
		}
		Log.e(TAG, "[MyReceiver] onReceive - "  + ", extras: " + sb.toString());
		return sb.toString();
	}

	@Override
	public void showMsg(String msg) {

	}

	@Override
	public void getHomeBanner(HomeBannerResultBean homeBannerResultBean) {
		this.homeBannerResultBean=homeBannerResultBean;
	}

	@Override
	public void getHomeVideo(HomeVideoResultBean homeAdResultBean) {

	}

	@Override
	public void getHomeSellCowsNum(HomeSellCowNumResultBean homeSellCowNumResultBean) {

	}

	@Override
	public void getAllVideo(HomeAllVideoResultBean homeAllVideoResultBean) {

	}

	@Override
	public void showLoading() {

	}

	@Override
	public void hideLoading() {

	}

//	//send msg to MainActivity
//	private void processCustomMessage(Context context, Bundle bundle) {
//		if (MainActivity.isForeground) {
//			String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//			Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
//			msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
//			if (!ExampleUtil.isEmpty(extras)) {
//				try {
//					JSONObject extraJson = new JSONObject(extras);
//					if (extraJson.length() > 0) {
//						msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
//					}
//				} catch (JSONException e) {
//
//				}
//
//			}
//			LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
//		}
//	}
}
