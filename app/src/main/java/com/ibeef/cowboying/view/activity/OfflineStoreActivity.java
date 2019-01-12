package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.flyco.dialog.widget.NormalDialog;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.OfflineStoreAddrAdapter;
import com.ibeef.cowboying.adapter.OfflineStoreImgAdapter;
import com.ibeef.cowboying.application.CowboyingApplication;
import com.ibeef.cowboying.base.OfflineStoreInfoBase;
import com.ibeef.cowboying.bean.OfflineStoreInfoResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.OfflineStoreInfoPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.ibeef.cowboying.view.customview.MapContainer;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;
import rxfamily.utils.PermissionsUtils;
import rxfamily.view.BaseActivity;

/**
 * 口袋牧场线下门店介绍
 */
public class OfflineStoreActivity extends BaseActivity implements AMap.OnMarkerClickListener ,OfflineStoreInfoBase.IView {

    @Bind(R.id.banner)
    ImageView banner;
    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.tell_us_img)
    ImageView tellUsImg;
    @Bind(R.id.store_addr_ry)
    RecyclerView storeAddrRy;
    @Bind(R.id.store_img_ry)
    RecyclerView storeImgRy;
    @Bind(R.id.lv_img)
    LinearLayout lvImg;
    @Bind(R.id.lv_info)
    LinearLayout lvInfo;
    @Bind(R.id.video_img)
    ImageView videoImg;
    @Bind(R.id.lv_video)
    LinearLayout lvVideo;
    @Bind(R.id.map)
    MapView mapView;
    @Bind(R.id.map_container)
    MapContainer map_container;
    @Bind(R.id.nest_scroll_id)
    NestedScrollView nest_scroll_id;
    @Bind(R.id.store_name_id)
    TextView store_name_id;
    @Bind(R.id.store_addr_id)
    TextView store_addr_id;
    @Bind(R.id.store_info_id)
    TextView store_info_id;
    @Bind(R.id.rv_show_id)
    RelativeLayout rv_show_id;
    private OfflineStoreAddrAdapter offlineStoreAddrAdapter;
    private OfflineStoreImgAdapter offlineStoreImgAdapter;
    private List<String> imgList;
    private List<String> addrList;
    private AMap aMap;
    private OfflineStoreInfoPresenter offlineStoreInfoPresenter;
    private String token;
    private OfflineStoreInfoResultBean offlineStoreInfoResultBean;
    private UiSettings mUiSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_store);
        ButterKnife.bind(this);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        init();
    }

    private void init() {
        storeAddrRy.setLayoutManager(new LinearLayoutManager(this));
        storeImgRy.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        imgList=new ArrayList<>();
        addrList=new ArrayList<>();
        offlineStoreAddrAdapter=new OfflineStoreAddrAdapter(addrList,this,R.layout.item_offlinestore_addr);
        offlineStoreImgAdapter=new OfflineStoreImgAdapter(imgList,this,R.layout.item_offlinestore_img);
        storeAddrRy.setAdapter(offlineStoreAddrAdapter);
        storeImgRy.setAdapter(offlineStoreImgAdapter);

        offlineStoreImgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(OfflineStoreActivity.this,ViewPagerActivity.class);
                intent.putExtra("info", offlineStoreInfoResultBean);
                intent.putExtra("index",position);
                startActivity(intent);
            }
        });

        offlineStoreInfoPresenter=new OfflineStoreInfoPresenter(this);
        token = Hawk.get(HawkKey.TOKEN);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        offlineStoreInfoPresenter.getOfflinestoreInfo(reqData);


        if (aMap == null) {
            aMap = mapView.getMap();
            mUiSettings = aMap.getUiSettings();
        }
        /**
         * 设置地图默认的缩放按钮是否显示
         */
        mUiSettings.setZoomControlsEnabled(false);
        /**
         * 设置地图是否可以手势滑动
         */
//        mUiSettings.setScrollGesturesEnabled(false);
        /**
         * 设置地图是否可以手势缩放大小
         */
//        mUiSettings.setZoomGesturesEnabled(false);
        /**
         * 设置地图是否可以倾斜
         */
//        mUiSettings.setTiltGesturesEnabled(false);
        /**
         * 设置地图是否可以旋转
         */
//        mUiSettings.setRotateGesturesEnabled(false);

        aMap.setOnMarkerClickListener(this);
        changeCamera(
                CameraUpdateFactory.newCameraPosition(new CameraPosition(
                        Constant.ZHENGZHOUGD, 18, 30, 0)));
        aMap.clear();
        aMap.addMarker(new MarkerOptions().position(Constant.ZHENGZHOUGD)
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        map_container.setScrollView(nest_scroll_id);

        aMap.clear();

        //自定义位置显示的marker
        LayoutInflater mInflater = LayoutInflater.from(this);
        View view = mInflater.inflate(R.layout.custom_map_marker, null);
        Bitmap bitmap =convertViewToBitmap(view);
        MarkerOptions markOptiopns = new MarkerOptions();
        markOptiopns.position(Constant.ZHENGZHOUGD);
        markOptiopns.icon(BitmapDescriptorFactory.fromBitmap(bitmap));
        markOptiopns.draggable(false);
        aMap.addMarker(markOptiopns);

    }


    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    /**
     * 根据动画按钮状态，调用函数animateCamera或moveCamera来改变可视区域
     */
    private void changeCamera(CameraUpdate update) {
        aMap.moveCamera(update);
    }
    @OnClick({R.id.back_id, R.id.tell_us_img,R.id.video_img,R.id.banner})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.banner:
                if(!SDCardUtil.isNullOrEmpty(offlineStoreInfoResultBean)){
                    if(!SDCardUtil.isNullOrEmpty(offlineStoreInfoResultBean.getBizData().getPageUrl())){
                       if(offlineStoreInfoResultBean.getBizData().getPageUrl().contains("http")){
                           Intent intent1=new Intent(OfflineStoreActivity.this,AdWebviewActivity.class);
                           intent1.putExtra("url",offlineStoreInfoResultBean.getBizData().getPageUrl());
                           intent1.putExtra("title","");
                           startActivity(intent1);
                       }else {
                           switch (offlineStoreInfoResultBean.getBizData().getPageUrl()){
                               case "adopt_scheme_list":
                                   //养牛方案列表
                                   Intent intent=new Intent(OfflineStoreActivity.this,BuyCowsPlanActivity.class);
                                   intent.putExtra("isAd",false);
                                   startActivity(intent);
                                   break;
                               case "adopt_scheme_detail":
                                   //养牛方案详情
                                   Intent intent4=new Intent(OfflineStoreActivity.this,CowsClaimActivity.class);
                                   intent4.putExtra("schemId",Integer.parseInt(offlineStoreInfoResultBean.getBizData().getParams()));
                                   intent4.putExtra("isAd",false);
                                   startActivity(intent4);
                                   break;
                               case "adopt_order_list":
                                   //养牛订单列表
                                   Intent intent2=new Intent(OfflineStoreActivity.this,MyCowsActivity.class);
                                   intent2.putExtra("isAd",false);
                                   startActivity(intent2);
                                   break;
                               case "adopt_order_detail":
                                   //养牛订单详情
                                   Intent intent3 = new Intent(OfflineStoreActivity.this, MyCowsDetailActivity.class);
                                   intent3.putExtra("orderId",offlineStoreInfoResultBean.getBizData().getParams()+"");
                                   intent3.putExtra("isAd",false);
                                   startActivity(intent3);
                                   break;
                               case "pasture_list":
                                   //合作牧场列表
                                   Intent intent5=new Intent(OfflineStoreActivity.this,RanchConsociationActivity.class);
                                   intent5.putExtra("isAd",false);
                                   startActivity(intent5);
                                   break;
                               case "shop_product_list":
                                   //商城商品列表
                                   Intent intent1=new Intent(OfflineStoreActivity.this,MainActivity.class);
                                   intent1.putExtra("index",1);
                                   startActivity(intent1);
                                   break;
                               case "shop_order_list":
                                   //商城订单列表
                                   Intent intent6=new Intent(OfflineStoreActivity.this,MyOrderActivity.class);
                                   intent6.putExtra("isAd",false);
                                   startActivity(intent6);
                                   break;
                               case "shop_order_detail":
                                   //商城订单详情
                                   Intent intent7 = new Intent(OfflineStoreActivity.this, MyOrderDetailActivity.class);
                                   intent7.putExtra("orderId",offlineStoreInfoResultBean.getBizData().getParams()+"");
                                   intent7.putExtra("isAd",false);
                                   startActivity(intent7);
                                   break;
                               case "total_assets":
                                   //总资产
                                   Intent intent8=new Intent(OfflineStoreActivity.this,MyAllMoneyActivity.class);
                                   intent8.putExtra("isAd",false);
                                   startActivity(intent8);
                                   break;
                               case "coupon_list":
                                   //优惠券列表
                                   Intent intent9=new Intent(OfflineStoreActivity.this,DiscountCouponActivity.class);
                                   intent9.putExtra("isAd",false);
                                   startActivity(intent9);
                                   break;
                               case "contract_list":
                                   //合同列表
                                   Intent intent10=new Intent(OfflineStoreActivity.this,MyContractActivity.class);
                                   intent10.putExtra("isAd",false);
                                   startActivity(intent10);
                                   break;
                               case "vip_card_detail":
                                   //会员卡详情
                                   startActivity(VipCardActivity.class);
                                   break;
                               case "new_welfare":

                                   break;
                               default:
                                   break;
                           }
                       }
                    }
                }

                break;
            case R.id.tell_us_img:
                if(!SDCardUtil.isNullOrEmpty(offlineStoreInfoResultBean)){
                    if(!SDCardUtil.isNullOrEmpty(offlineStoreInfoResultBean.getBizData().getMobile())){
                        ActionSheetDialog();
                    }
                }
                break;
            case R.id.video_img:
                if(!SDCardUtil.isNullOrEmpty(offlineStoreInfoResultBean)){
                    if(!SDCardUtil.isNullOrEmpty(offlineStoreInfoResultBean.getBizData().getVideoPlayUrl())){
                        Intent intent = new Intent(OfflineStoreActivity.this, PlayerVideoActivity.class);
                        intent.putExtra("video_url", offlineStoreInfoResultBean.getBizData().getVideoPlayUrl());
                        intent.putExtra("title", "");
                        intent.putExtra("coverUrl", offlineStoreInfoResultBean.getBizData().getVideoCoverUrl());
                        startActivity(intent);
                    }
                }
                break;
            default:
                break;
        }
    }

    private void ActionSheetDialog() {

        final String[] stringItems = offlineStoreInfoResultBean.getBizData().getMobile().toArray(new String[offlineStoreInfoResultBean.getBizData().getMobile().size()]);

        final ActionSheetDialog dialog = new ActionSheetDialog(this, stringItems, null);
        dialog.isTitleShow(false)
                .titleTextSize_SP(14.5f)
                .itemTextColor(ContextCompat.getColor(OfflineStoreActivity.this,R.color.black)).cancelText(ContextCompat.getColor(OfflineStoreActivity.this,R.color.red))
                .show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, final int position, long id) {
               rx.Observable<Boolean> grantObservable1 = PermissionsUtils.getCallPhoneGrant2(OfflineStoreActivity.this);
               grantObservable1.subscribe(new Action1<Boolean>() {
                   @Override
                   public void call(Boolean granted) {
                       if (granted) {
                           callPhone(offlineStoreInfoResultBean.getBizData().getMobile().get(position));
                       } else {
                           PermissionsUtils.showPermissionDeniedDialog(OfflineStoreActivity.this, false);
                       }
                   }
               });
                dialog.dismiss();
            }
        });
    }


    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    private static final String[] MAP_PACKAGES = {Constant.BAIDUMAP_PACKAGENAME, Constant.AUTONAVI_PACKAGENAME, Constant.QQMAP_PACKAGENAME};
    List<String> packages = checkInstalledPackage(MAP_PACKAGES);
    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param packageNames 可变参数 String[]
     * @return 目标软件中已安装的列表
     */
    public  List<String> checkInstalledPackage(String... packageNames) {

        //获取packageManager
        final PackageManager packageManager = CowboyingApplication.getInstance().getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储
        List<String> newPackageNames = new ArrayList<>();
        int count = packageNames.length;

        if (packageInfos != null && packageInfos.size() > 0) {

            outermost:for (String packageName : packageNames) {
                for (int i = 0; i < packageInfos.size(); i++) {
                    String packageInfo = packageInfos.get(i).packageName;
                    if (packageInfo.contains(packageName)) {
                        newPackageNames.add(packageName);
                        if (newPackageNames.size() == count) {
                            break outermost;//这里使用了循环标记，跳出外层循环
                        }
                    }
                }
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return newPackageNames;
    }

    private void ActionAddrDialog() {

        final List<String> listShow=new ArrayList<>();
        for (String packageName : packages) {
            switch (packageName) {
                case Constant.BAIDUMAP_PACKAGENAME:
                    listShow.add("百度地图");
                    break;
                case Constant.AUTONAVI_PACKAGENAME:
                    listShow.add("高德地图");
                    break;
                case Constant.QQMAP_PACKAGENAME:
                    listShow.add("腾讯地图");
                    break;
                default:
                    break;
            }
        }

        if (listShow.size() ==0) {
            //没有地图打开集成的高德地图
            startActivity(OfflineStoreMapActivity.class);
        } else if (listShow.size() ==1) {
            //只有一个地图软件，不弹出选择框，直接跳转
            for (String packageName : listShow) {
                switch (packageName) {
                    case "百度地图":
                        goToBaiduMap();
                        break;
                    case "高德地图":
                        invokeAuToNaveMap();
                        break;
                    case "腾讯地图":
                        invokeQQMap();
                        break;
                    default:
                        break;
                }
            }
        }else {
            //用户选择跳转
            final ActionSheetDialog dialog = new ActionSheetDialog(this, listShow.toArray(new String[listShow.size()]), null);
            dialog.isTitleShow(false)
                    .titleTextSize_SP(14.5f)
                    .itemTextColor(ContextCompat.getColor(OfflineStoreActivity.this,R.color.black)).cancelText(ContextCompat.getColor(OfflineStoreActivity.this,R.color.red))
                    .show();

            dialog.setOnOperItemClickL(new OnOperItemClickL() {
                @Override
                public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (listShow.get(position)) {
                            case "百度地图":
                                goToBaiduMap();
                                break;
                            case "高德地图":
                                invokeAuToNaveMap();
                                break;
                            case "腾讯地图":
                                invokeQQMap();
                                break;
                            default:
                                break;
                        }

                    dialog.dismiss();
                }
            });
        }
    }


    /**
     * 跳转百度地图
     */
    private void goToBaiduMap() {

        Intent intent = new Intent();
        intent.setData(Uri.parse("baidumap://map/direction?destination=latlng:"
                + Constant.KDW + ","
                + Constant.KDJ + "|name:口袋牧场"  + // 终点
                "&mode=driving" + // 导航路线方式
                "&src=" + getPackageName()));
        startActivity(intent); // 启动调用
    }


    /**
     * 调用高德地图
     */
    private  void invokeAuToNaveMap() {

        try {
            Uri uri = Uri.parse("androidamap://route?sourceApplication={你的应用名称}" +
                    "&dlat="+Constant.GDKDW//终点的纬度
                    + "&dlon="+Constant.GDKDJ//终点的经度
                    + "&dname=口袋牧场"////终点的显示名称
                    + "&dev=0&m=0&t=0");
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            intent.addCategory("android.intent.category.DEFAULT");
            startActivity(intent);
        } catch (Exception e) {
            showToast("暂无该app");
        }

    }

    /**
     * 调用腾讯地图
     *
     */
    private  void invokeQQMap() {
        try {
            Uri uri = Uri.parse("qqmap://map/routeplan?type=drive" +
                    "&to=口袋牧场"//终点的显示名称 必要参数
                    + "&tocoord=" +Constant.TXKDW+ ","+Constant.TXKDJ //终点的经纬度
                    + "&referer={口袋牧场}");
            Intent intent = new Intent();
            intent.setData(uri);
            startActivity(intent);
        } catch (Exception e) {
            showToast("暂无该app");
        }
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        ActionAddrDialog();
        return false;

    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getOfflinestoreInfo(OfflineStoreInfoResultBean offlineStoreInfoResultBean) {
        if(SDCardUtil.isNullOrEmpty(offlineStoreInfoResultBean.getBizData())){
            nest_scroll_id.setVisibility(View.GONE);
            rv_show_id.setVisibility(View.VISIBLE);
        }else {
            nest_scroll_id.setVisibility(View.VISIBLE);
            rv_show_id.setVisibility(View.GONE);
        }
        if("000000".equals(offlineStoreInfoResultBean.getCode())){
            this.offlineStoreInfoResultBean=offlineStoreInfoResultBean;
            store_name_id.setText(offlineStoreInfoResultBean.getBizData().getName());
            store_addr_id.setText(offlineStoreInfoResultBean.getBizData().getAddress());

            addrList.addAll(offlineStoreInfoResultBean.getBizData().getOfficeTime());
            offlineStoreAddrAdapter.setNewData(addrList);
            RequestOptions options = new RequestOptions()
                    .skipMemoryCache(true)
                    .error(R.mipmap.jzsb)
                    //跳过内存缓存
                    ;
            Glide.with(OfflineStoreActivity.this).load(Constant.imageDomain+offlineStoreInfoResultBean.getBizData().getImageUrl()).apply(options).into(banner);
            if(SDCardUtil.isNullOrEmpty(offlineStoreInfoResultBean.getBizData().getStoreImages())){
                lvImg.setVisibility(View.GONE);
            }else {
                imgList.addAll(offlineStoreInfoResultBean.getBizData().getStoreImages());
                offlineStoreImgAdapter.setNewData(imgList);
            }
            if(SDCardUtil.isNullOrEmpty(offlineStoreInfoResultBean.getBizData().getDescribes())){
                lvInfo.setVisibility(View.GONE);
            }else {
                store_info_id.setText(offlineStoreInfoResultBean.getBizData().getDescribes());
            }
            if(SDCardUtil.isNullOrEmpty(offlineStoreInfoResultBean.getBizData().getVideoPlayUrl())){
                lvVideo.setVisibility(View.GONE);
            }else {
                Glide.with(OfflineStoreActivity.this).load(offlineStoreInfoResultBean.getBizData().getVideoCoverUrl()).apply(options).into(videoImg);
            }
        }else {
            showWaring(offlineStoreInfoResultBean.getMessage());
        }
    }

    public void showWaring(String info) {
        final NormalDialog dialog = new NormalDialog(this);
        dialog.isTitleShow(true)
                .style(NormalDialog.STYLE_TWO)
                .content(info)
                .titleTextSize(18)
                .show();
        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                });
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if(offlineStoreInfoPresenter!=null){
           offlineStoreInfoPresenter.detachView();
        }
    }

}
