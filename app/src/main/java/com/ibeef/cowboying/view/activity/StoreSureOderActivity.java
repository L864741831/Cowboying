package com.ibeef.cowboying.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.StoreAddrAdapter;
import com.ibeef.cowboying.adapter.StoreSureOrderAdapter;
import com.ibeef.cowboying.base.StoreCarPayBase;
import com.ibeef.cowboying.base.UseCouponListBase;
import com.ibeef.cowboying.bean.AddShopCarResultBean;
import com.ibeef.cowboying.bean.AddStoreCarParamBean;
import com.ibeef.cowboying.bean.CarListResultBean;
import com.ibeef.cowboying.bean.CouponNumParamBean;
import com.ibeef.cowboying.bean.CouponNumResultBean;
import com.ibeef.cowboying.bean.DeleteCarResultBean;
import com.ibeef.cowboying.bean.NowBuyOrderResultBean;
import com.ibeef.cowboying.bean.NowPayOrderParamBean;
import com.ibeef.cowboying.bean.NowPayOrderResultBean;
import com.ibeef.cowboying.bean.ShowAddressResultBean;
import com.ibeef.cowboying.bean.StoreAddrResultBean;
import com.ibeef.cowboying.bean.UseCouponListResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.StoreCarPayPresenter;
import com.ibeef.cowboying.presenter.UseCouponListPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.orhanobut.hawk.Hawk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 商城确认订单
 */
public class StoreSureOderActivity extends BaseActivity implements StoreCarPayBase.IView , UseCouponListBase.IView{

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.ry_id)
    RecyclerView ryId;
    @Bind(R.id.delevery_type_id)
    TextView deleveryTypeId;
    @Bind(R.id.delevery_rv)
    RelativeLayout deleveryRv;
    @Bind(R.id.couppon_money_id)
    TextView coupponMoneyId;
    @Bind(R.id.coupon_num_id)
    TextView couponNumId;
    @Bind(R.id.cuppon_rv)
    RelativeLayout cupponRv;
    @Bind(R.id.freight_money_id)
    TextView freightMoneyId;
    @Bind(R.id.freight_rv)
    RelativeLayout freightRv;
    @Bind(R.id.oder_all_money_id)
    TextView oderAllMoneyId;
    @Bind(R.id.all_num_money_id)
    TextView allNumMoneyId;
    @Bind(R.id.now_pay_id)
    TextView nowPayId;
    @Bind(R.id.rv_bottom_id)
    RelativeLayout rvBottomId;
    @Bind(R.id.address_rv)
    RelativeLayout addressRv;
    @Bind(R.id.show_addr_id)
    TextView showAddrId;
    @Bind(R.id.mobile_txt_id)
    TextView mobileTxtId;
    @Bind(R.id.del_addr_txt_id)
    TextView delAddrTxtId;
    @Bind(R.id.right_img_show)
    ImageView rightImgShow;
    @Bind(R.id.lvs_id)
    LinearLayout lvsId;
    @Bind(R.id.refuce_id)
    TextView refuceId;
    @Bind(R.id.sure_id)
    TextView sureId;
    @Bind(R.id.ry_store_id)
    RecyclerView ryStoreId;
    @Bind(R.id.img_choose1_id)
    ImageView imgChoose1Id;
    @Bind(R.id.img_choose2_id)
    ImageView imgChoose2Id;
    @Bind(R.id.lv_choose_id)
    LinearLayout lvChooseId;
    private StoreSureOrderAdapter storeSureOrderAdapter;
    private String token;
    private BroadcastReceiver receiver;
    private StoreAddrAdapter storeAddrAdapter;
    private int currentPage=1;
    private boolean isMoreLoad=false;
    private int type;
    private   int selectId;
    private  boolean check;
    private final static int REQUESTCODE = 1; // 返回的结果码

    private NowBuyOrderResultBean nowBuyOrderResultBean;
    private StoreCarPayPresenter storeCarPayPresenter;
    private List<AddStoreCarParamBean> storeCarResultBeans;
    private   ShowAddressResultBean.BizDataBean item=null;
    private UseCouponListPresenter useCouponListPresenter;
    private  Map<String, String> reqData;
    private List<StoreAddrResultBean.BizDataBean> bizDataBeans;
    private StoreAddrResultBean storeAddrResultBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_sure_oder);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        nowBuyOrderResultBean= (NowBuyOrderResultBean) getIntent().getSerializableExtra("infos");
        storeCarResultBeans= (List<AddStoreCarParamBean>) getIntent().getSerializableExtra("goodlists");

        if(nowBuyOrderResultBean.getBizData().getCarriageAmount()>0){
            freightMoneyId.setText("￥"+nowBuyOrderResultBean.getBizData().getCarriageAmount());
            freightMoneyId.setVisibility(View.VISIBLE);
        }else {
            freightMoneyId.setVisibility(View.GONE);
        }
        bizDataBeans=new ArrayList<>();
        token = Hawk.get(HawkKey.TOKEN);
        reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());

        info.setText("确认订单");
        ryId.setHasFixedSize(true);
        ryId.setNestedScrollingEnabled(false);
        ryId.setLayoutManager(new LinearLayoutManager(this));
        ryStoreId.setHasFixedSize(true);
        ryStoreId.setNestedScrollingEnabled(false);
        ryStoreId.setLayoutManager(new LinearLayoutManager(this));

        storeSureOrderAdapter=new StoreSureOrderAdapter(nowBuyOrderResultBean.getBizData().getProducts(),this,R.layout.item_sureorder_info);
        ryId.setAdapter(storeSureOrderAdapter);

        IntentFilter intentFilter = new IntentFilter("com.ibeef.cowboying.chooseaddr");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //获取选中的地址
                item= (ShowAddressResultBean.BizDataBean) intent.getSerializableExtra("info");
            }
        };
        registerReceiver(receiver, intentFilter);

        storeAddrAdapter=new StoreAddrAdapter(bizDataBeans,this);
        ryStoreId.setAdapter(storeAddrAdapter);
        storeAddrAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId()==R.id.all_ck_id){
                    for (int i=0;i<nowBuyOrderResultBean.getBizData().getProducts().size();i++){
                        if(i==position){
                            nowBuyOrderResultBean.getBizData().getProducts().get(i).setDefautChoose(1);
                        }else{
                            nowBuyOrderResultBean.getBizData().getProducts().get(i).setDefautChoose(0);
                        }
                        storeAddrAdapter.notifyItemChanged(i);
                    }

                }
            }
        });
        storeCarPayPresenter=new StoreCarPayPresenter(this);

        useCouponListPresenter=new UseCouponListPresenter(this);

        CouponNumParamBean couponNumParamBean=new CouponNumParamBean();
        couponNumParamBean.setSchemeId(null);
        couponNumParamBean.setUseType("3");
        couponNumParamBean.setQuantity(null);
        couponNumParamBean.setProductQuantityReqDtos(storeCarResultBeans);
        useCouponListPresenter.getCouponNum(reqData,couponNumParamBean);

        oderAllMoneyId.setText("￥"+nowBuyOrderResultBean.getBizData().getOrderAmount());
        allNumMoneyId.setText("共"+nowBuyOrderResultBean.getBizData().getTotalQuantity()+"件,实付款:￥"+nowBuyOrderResultBean.getBizData().getOrderAmount()+"");
        storeCarPayPresenter.storeAddrList(reqData);

    }

    @Override
    protected void onResume() {
        super.onResume();
        AddShopCarResultBean addShopCarResultBean1=new AddShopCarResultBean();
        addShopCarResultBean1.setShopCartReqVos(storeCarResultBeans);
        storeCarPayPresenter.nowBuyOrder(reqData,addShopCarResultBean1);
    }

    @OnClick({R.id.back_id, R.id.delevery_rv, R.id.cuppon_rv, R.id.now_pay_id,R.id.address_rv,R.id.refuce_id,R.id.sure_id,R.id.lv_choose_id,R.id.img_choose1_id,R.id.img_choose2_id,R.id.lvs_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.lv_choose_id:
                //最外层蒙版 阻止点击事件的传递
                break;
            case R.id.lvs_id:
                //最外层蒙版 阻止点击事件的传递
                break;
            case R.id.img_choose1_id:
                type=1;
                lvChooseId.setVisibility(View.GONE);
                deleveryTypeId.setText("顺丰配送");
                break;
            case R.id.img_choose2_id:
                type=2;
                lvChooseId.setVisibility(View.GONE);
                lvsId.setVisibility(View.VISIBLE);
                deleveryTypeId.setText("到店自取");
                break;
            case R.id.delevery_rv:
                lvChooseId.setVisibility(View.VISIBLE);
                break;
            case R.id.refuce_id:
                lvsId.setVisibility(View.GONE);
                type=0;
                deleveryTypeId.setText("请选择配送方式");
                break;
            case R.id.sure_id:
                //todo 接口自取门店地址
                lvsId.setVisibility(View.GONE);

                break;
            case R.id.cuppon_rv:
                Intent intent1=new Intent(StoreSureOderActivity.this,UseCouponActivity.class);
                intent1.putExtra("selectId",selectId);
                intent1.putExtra("check",check);
                intent1.putExtra("isStore",true);
                intent1.putExtra("infos",(Serializable) storeCarResultBeans);
                intent1.putExtra("schemeId",0);
                intent1.putExtra("quantity",0);
                startActivityForResult(intent1,REQUESTCODE);
                break;
            case R.id.now_pay_id:
                if(SDCardUtil.isNullOrEmpty(nowBuyOrderResultBean.getBizData().getAddress())){
                    if(SDCardUtil.isNullOrEmpty(item)){
                        showToast("请添加收货地址！");
                        return;
                    }
                }
                if(type==0){
                    showToast("请选择配送方式！");
                    return;
                }
                NowPayOrderParamBean noPayOrderParamBean=new NowPayOrderParamBean();
                if(!SDCardUtil.isNullOrEmpty(item)){
                    noPayOrderParamBean.setAddressDetail(item.getProvince()+item.getCity()+item.getRegion()+item.getDetailAddress());
                    noPayOrderParamBean.setAddressId(item.getId());
                }else {
                    noPayOrderParamBean.setAddressDetail(nowBuyOrderResultBean.getBizData().getAddress().getProvince()+nowBuyOrderResultBean.getBizData().getAddress().getCity()+nowBuyOrderResultBean.getBizData().getAddress().getRegion()+nowBuyOrderResultBean.getBizData().getAddress().getDetailAddress());
                    noPayOrderParamBean.setAddressId(nowBuyOrderResultBean.getBizData().getAddress().getId());
                }
                if(selectId!=0){
                    //使用了优惠券 selectId优惠券id
                    noPayOrderParamBean.setCouponId(selectId);
                }else {
                    noPayOrderParamBean.setCouponId(null);
                }
                if(type==2){
                    if(!SDCardUtil.isNullOrEmpty(storeAddrResultBean)){
                        if(storeAddrResultBean.getBizData().size()>0){
                            noPayOrderParamBean.setStoreId(storeAddrResultBean.getBizData().get(0).getStoreId());
                        }
                    }
                }
                noPayOrderParamBean.setReceiveType(type+"");
                noPayOrderParamBean.setProducts(storeCarResultBeans);
                storeCarPayPresenter.nowPayOrder(reqData,noPayOrderParamBean);
                break;
            case R.id.address_rv:
                startActivity(AddressActivity.class);
                break;
            default:
                break;

        }
    }


    // 为了获取结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RESULT_OK，判断另外一个activity已经结束数据输入功能，Standard activity result:
        // operation succeeded. 默认值是-1
        if (resultCode == 2) {
            if (requestCode == REQUESTCODE) {
                selectId = data.getIntExtra("selectId",0);
                check=data.getBooleanExtra("check",false);
                //优惠金额
                double couponmoney=data.getDoubleExtra("couponmoney",0);
                if(check){
                    //不使用优惠券
                    coupponMoneyId.setText("未使用");
                    coupponMoneyId.setTextColor(getResources().getColor(R.color.txthui));
                }else {
                    if(couponmoney>0){
                        coupponMoneyId.setText("-"+couponmoney);
                        coupponMoneyId.setTextColor(getResources().getColor(R.color.red));
                        allNumMoneyId.setText("共"+nowBuyOrderResultBean.getBizData().getTotalQuantity()+"件,实付款:￥"+(nowBuyOrderResultBean.getBizData().getOrderAmount()-couponmoney)+"");
                    }else {
                        coupponMoneyId.setText("无可用");
                        coupponMoneyId.setTextColor(getResources().getColor(R.color.txthui));
                    }
                }
            }
        }
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getCouponNum(CouponNumResultBean couponNumResultBean) {
        if("000000".equals(couponNumResultBean.getCode())){
            if(couponNumResultBean.getBizData()>0){
                couponNumId.setVisibility(View.VISIBLE);
                couponNumId.setText(couponNumResultBean.getBizData()+"张可用");
                coupponMoneyId.setText("未使用");
            }else {
                coupponMoneyId.setText("无可用");
                couponNumId.setVisibility(View.GONE);
            }
        }else {
            showToast(couponNumResultBean.getMessage());
        }
    }

    @Override
    public void getUseCouponList(UseCouponListResultBean useCouponListResultBean) {

    }

    @Override
    public void nowBuyOrder(NowBuyOrderResultBean nowBuyOrderResultBean) {
        if("000000".equals(nowBuyOrderResultBean.getCode())){
            this.nowBuyOrderResultBean=nowBuyOrderResultBean;
            if(SDCardUtil.isNullOrEmpty(nowBuyOrderResultBean.getBizData().getAddress())){
                //没有地址
                mobileTxtId.setVisibility(View.INVISIBLE);
                delAddrTxtId.setVisibility(View.INVISIBLE);
                rightImgShow.setVisibility(View.VISIBLE);
                showAddrId.setText("请选择收货地址");
            }else {
                mobileTxtId.setVisibility(View.VISIBLE);
                delAddrTxtId.setVisibility(View.VISIBLE);
                rightImgShow.setVisibility(View.GONE);
                showAddrId.setText(nowBuyOrderResultBean.getBizData().getAddress().getName());
                mobileTxtId.setText(nowBuyOrderResultBean.getBizData().getAddress().getMobile());
                delAddrTxtId.setText(nowBuyOrderResultBean.getBizData().getAddress().getProvince()+nowBuyOrderResultBean.getBizData().getAddress().getCity()+nowBuyOrderResultBean.getBizData().getAddress().getRegion()+nowBuyOrderResultBean.getBizData().getAddress().getDetailAddress());
            }
            if(!SDCardUtil.isNullOrEmpty(item)){
                delAddrTxtId.setText(item.getProvince()+item.getCity()+item.getRegion()+item.getDetailAddress());
                showAddrId.setText(item.getName());
                mobileTxtId.setText(item.getMobile());
                mobileTxtId.setVisibility(View.VISIBLE);
                delAddrTxtId.setVisibility(View.VISIBLE);
                rightImgShow.setVisibility(View.GONE);
            }

        }else {
            showToast(nowBuyOrderResultBean.getMessage());
        }

    }

    @Override
    public void nowPayOrder(NowPayOrderResultBean nowPayOrderResultBean) {
        if("000000".equals(nowPayOrderResultBean.getCode())){
            Intent intent=new Intent(StoreSureOderActivity.this,StorePayTypeActivity.class);
            intent.putExtra("orderId",nowPayOrderResultBean.getBizData());
            startActivity(intent);
        }else {
            showToast(nowPayOrderResultBean.getMessage());
        }

    }

    @Override
    public void getCarList(CarListResultBean carListResultBean) {

    }

    @Override
    public void deleteStoreCar(DeleteCarResultBean deleteCarResultBean) {

    }

    @Override
    public void storeAddrList(StoreAddrResultBean storeAddrResultBean) {
        if ("000000".equals(storeAddrResultBean.getCode())) {
            if (!SDCardUtil.isNullOrEmpty(storeAddrResultBean.getBizData())) {
                this.storeAddrResultBean=storeAddrResultBean;
                bizDataBeans.addAll(storeAddrResultBean.getBizData());
                storeAddrAdapter.setNewData(bizDataBeans);
            }
        } else {
            showToast(storeAddrResultBean.getMessage());
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
        if (storeCarPayPresenter != null) {
            storeCarPayPresenter.detachView();
        }
        if (useCouponListPresenter != null) {
            useCouponListPresenter.detachView();
        }
    }
}
