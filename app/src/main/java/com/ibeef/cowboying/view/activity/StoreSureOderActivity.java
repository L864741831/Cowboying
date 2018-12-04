package com.ibeef.cowboying.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.ibeef.cowboying.bean.StoreCarResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 商城确认订单
 */
public class StoreSureOderActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener{

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
    private List<StoreCarResultBean> objectList;
    private String token;
    private BroadcastReceiver receiver;
    private StoreAddrAdapter storeAddrAdapter;
    private int currentPage=1;
    private boolean isMoreLoad=false;
    private int type;
    private   int selectId;
    private  boolean check;
    private final static int REQUESTCODE = 1; // 返回的结果码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_sure_oder);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        token = Hawk.get(HawkKey.TOKEN);
        info.setText("确认订单");
        ryId.setHasFixedSize(true);
        ryId.setNestedScrollingEnabled(false);
        ryId.setLayoutManager(new LinearLayoutManager(this));
        ryStoreId.setHasFixedSize(true);
        ryStoreId.setNestedScrollingEnabled(false);
        ryStoreId.setLayoutManager(new LinearLayoutManager(this));
        objectList=new ArrayList<>();
        for (int i=0;i<2;i++) {
            StoreCarResultBean storeCarResultBean=new StoreCarResultBean();
            storeCarResultBean.setDefautChoose(0);
            objectList.add(storeCarResultBean);
        }
        storeSureOrderAdapter=new StoreSureOrderAdapter(objectList,this,R.layout.item_sureorder_info);
        ryId.setAdapter(storeSureOrderAdapter);

        IntentFilter intentFilter = new IntentFilter("com.ibeef.cowboying.chooseaddr");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //获取选中的地址

            }
        };
        registerReceiver(receiver, intentFilter);

        storeAddrAdapter=new StoreAddrAdapter(objectList,this);
        storeAddrAdapter.setOnLoadMoreListener(this, ryStoreId);
        ryStoreId.setAdapter(storeAddrAdapter);
        storeAddrAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId()==R.id.all_ck_id){
                    for (int i=0;i<objectList.size();i++){
                        if(i==position){
                            objectList.get(i).setDefautChoose(1);
                        }else{
                            objectList.get(i).setDefautChoose(0);
                        }
                        storeAddrAdapter.notifyItemChanged(i);
                    }


                }
            }
        });
    }

    @OnClick({R.id.back_id, R.id.delevery_rv, R.id.cuppon_rv, R.id.now_pay_id,R.id.address_rv,R.id.refuce_id,R.id.sure_id,R.id.lv_choose_id,R.id.img_choose1_id,R.id.img_choose2_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.lv_choose_id:
                //最外层蒙版

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
                intent1.putExtra("schemeId",1);
                intent1.putExtra("quantity",1);
                startActivityForResult(intent1,REQUESTCODE);
                break;
            case R.id.now_pay_id:
                if(type==0){
                    showToast("请选择配送方式！");
                    return;
                }
                if(selectId!=0){
                    //使用了优惠券 selectId优惠券id

                }

                startActivity(StorePayTypeActivity.class);
                break;
            case R.id.address_rv:
                startActivity(AddressActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            unregisterReceiver(receiver);
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
                    }else {
                        coupponMoneyId.setText("无可用");
                        coupponMoneyId.setTextColor(getResources().getColor(R.color.txthui));
                    }
                }
            }
        }
    }
    @Override
    public void onLoadMoreRequested() {
        isMoreLoad = true;
        currentPage += 1;
    }
}
