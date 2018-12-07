package com.ibeef.cowboying.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.StoreCarAdapter;
import com.ibeef.cowboying.base.StoreCarBase;
import com.ibeef.cowboying.base.StoreCarPayBase;
import com.ibeef.cowboying.bean.AddShopCarResultBean;
import com.ibeef.cowboying.bean.AddStoreCarParamBean;
import com.ibeef.cowboying.bean.AddStoreCarResultBean;
import com.ibeef.cowboying.bean.CarListResultBean;
import com.ibeef.cowboying.bean.DeleteCarResultBean;
import com.ibeef.cowboying.bean.NowBuyOrderResultBean;
import com.ibeef.cowboying.bean.NowPayOrderResultBean;
import com.ibeef.cowboying.bean.StoreAddrResultBean;
import com.ibeef.cowboying.bean.StoreCarNumResultBean;
import com.ibeef.cowboying.bean.StoreInfoListResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.StoreCarPayPresenter;
import com.ibeef.cowboying.presenter.StoreCarPresenter;
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
 * 商城购物车
 */
public class StoreCarActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener,StoreCarPayBase.IView,StoreCarBase.IView{

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.go_store_btn)
    ImageView go_store_btn;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.swipe_ly)
    SwipeRefreshLayout swipeLy;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    @Bind(R.id.ry_id)
    RecyclerView ryId;
    @Bind(R.id.rv_order)
    RelativeLayout rvOrder;
    @Bind(R.id.all_ck_id)
    CheckBox allCkId;
    @Bind(R.id.all_cownum_id)
    TextView allCownumId;
    @Bind(R.id.now_claim_btn_id)
    TextView nowClaimBtnId;
    @Bind(R.id.refuce_id)
    TextView refuceId;
    @Bind(R.id.sure_id)
    TextView sureId;
    @Bind(R.id.lvs_id)
    LinearLayout lvsId;
    @Bind(R.id.infos_id)
    TextView infosId;
    @Bind(R.id.rv_bottom_id)
    RelativeLayout rvBottomId;

    private String token;

    private int currentPage=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    private boolean isclick;
    private StoreCarAdapter storeCarAdapter;
    private BroadcastReceiver receiver1;
    private int num,position,chooseNum;
    private  List<CarListResultBean.BizDataBean> lists;
    private StoreCarPayPresenter storeCarPayPresenter;
    private List<AddStoreCarParamBean> storeCarResultBeans;
    private double allMoney;
    private CarListResultBean.BizDataBean item;
    private StoreCarPresenter storeCarPresenter;
    private   Map<String, String> reqData;
    private boolean isBuy=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_car);
        ButterKnife.bind(this);
        init();
    }
    private void init(){
        token = Hawk.get(HawkKey.TOKEN);
        reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());

        lists=new ArrayList<>();
        storeCarResultBeans=new ArrayList<>();
        info.setText("购物车");
        actionNewQuestionTv.setText("编辑");
        actionNewQuestionTv.setVisibility(View.VISIBLE);
        swipeLy.setColorSchemeResources(R.color.colorAccent);
        swipeLy.setOnRefreshListener(this);
        swipeLy.setEnabled(true);
        ryId.setHasFixedSize(true);
        ryId.setNestedScrollingEnabled(false);
        ryId.setLayoutManager(new GridLayoutManager(this,2));
        storeCarAdapter=new StoreCarAdapter(lists,this,R.layout.item_store_car);
        storeCarAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(storeCarAdapter);
        ryId.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!ryId.canScrollVertically(-1)){
                    swipeLy.setEnabled(true);
                }else {
                    swipeLy.setEnabled(false);
                }
            }
        });

        // 设置广播接收器,动态修改布局
        IntentFilter intentFilter1 = new IntentFilter("com.ibeef.cowboying.storecarnum");
        receiver1 = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                num=intent.getIntExtra("num",1);
                position=intent.getIntExtra("position",0);
                lists.get(position).setQuantity(num);
                lists.get(position).setChoose(true);
            }
        };
       registerReceiver(receiver1, intentFilter1);

        storeCarAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                 item=storeCarAdapter.getItem(position);
                switch (view.getId()){
                    case R.id.all_ck_id:
                        if(0==lists.get(position).getDefautChoose()){
                            lists.get(position).setDefautChoose(1);
                            chooseNum++;
                            allMoney+=item.getPrice()*item.getQuantity();
                            allCownumId.setText("合计：￥"+allMoney);
                        }else{
                            chooseNum--;
                            allMoney-=item.getPrice()*item.getQuantity();
                            allCownumId.setText("合计：￥"+allMoney);
                            lists.get(position).setDefautChoose(0);
                        }
                       storeCarAdapter.notifyItemChanged(position);
                        break;
                    default:
                        break;
                }
            }
        });

        storeCarPresenter=new StoreCarPresenter(this);
        storeCarPayPresenter=new StoreCarPayPresenter(this);

        storeCarPayPresenter.getCarList(reqData,currentPage);

    }

    @OnClick({R.id.back_id,R.id.action_new_question_tv,R.id.go_store_btn,R.id.now_claim_btn_id,R.id.all_ck_id,R.id.refuce_id,R.id.sure_id,R.id.lvs_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                //跳到购物车
                isBuy=false;
                addCar();
                finish();
                break;
            case R.id.go_store_btn:
                finish();
                break;
            case R.id.lvs_id:

                break;
            case R.id.refuce_id:
                lvsId.setVisibility(View.GONE);
                allCkId.setChecked(false);
                break;
            case R.id.sure_id:
                //编辑，批量删除
                lvsId.setVisibility(View.GONE);
                chooseNum=0;
                allMoney=0;
                allCownumId.setText("合计：￥"+allMoney);
                int size = lists.size();
                storeCarResultBeans.clear();
                for(int i=size-1;i>=0;i--){
                    if(1==lists.get(i).getDefautChoose()) {
                        AddStoreCarParamBean addStoreCarParamBean=new AddStoreCarParamBean();
                        addStoreCarParamBean.setProductId(lists.get(i).getProductId());
                        addStoreCarParamBean.setQuantity(lists.get(i).getQuantity());
                        storeCarResultBeans.add(addStoreCarParamBean);

                    }
                }
                AddShopCarResultBean addShopCarResultBean=new AddShopCarResultBean();
                addShopCarResultBean.setShopCartReqVos(storeCarResultBeans);
                storeCarPayPresenter.deleteStoreCar(reqData,addShopCarResultBean);
                allCkId.setChecked(false);
                break;
            case R.id.all_ck_id:
              //全选
                allMoney=0;
                for (int i=0;i<lists.size();i++){
                    if(allCkId.isChecked()){
                        lists.get(i).setDefautChoose(1);
                        allMoney+=lists.get(i).getPrice()*lists.get(i).getQuantity();
                    }else {
                        lists.get(i).setDefautChoose(0);
                        allMoney=0;
                    }
                    storeCarAdapter.notifyItemChanged(i);
                }
                allCownumId.setText("合计：￥"+allMoney);
                chooseNum=lists.size();
                break;
            case R.id.now_claim_btn_id:
                isBuy=true;
                addCar();
                if(isclick){
                    if(chooseNum>0){
                        lvsId.setVisibility(View.VISIBLE);
                        infosId.setText("您确定要删除这"+chooseNum+"件商品吗？");
                    }else {
                        showToast("请选中要删除的商品？");
                    }

                }else {
                    //立即购买
                    if(chooseNum>0){
                        storeCarResultBeans.clear();
                        for (int i=0;i<lists.size();i++){
                            if(lists.get(i).getDefautChoose()==1){
                                AddStoreCarParamBean addStoreCarParamBean=new AddStoreCarParamBean();
                                addStoreCarParamBean.setProductId(lists.get(i).getProductId());
                                addStoreCarParamBean.setQuantity(lists.get(i).getQuantity());
                                storeCarResultBeans.add(addStoreCarParamBean);
                            }
                        }
                        AddShopCarResultBean addShopCarResultBean1=new AddShopCarResultBean();
                        addShopCarResultBean1.setShopCartReqVos(storeCarResultBeans);
                        storeCarPayPresenter.nowBuyOrder(reqData,addShopCarResultBean1);
                    }else {
                        showToast("请选中要购买的商品？");
                    }
                }
                break;
            case R.id.action_new_question_tv:
                if(!isclick){
                    actionNewQuestionTv.setText("完成");
                    nowClaimBtnId.setText("删除");
                    allCownumId.setVisibility(View.GONE);
                    for (int i=0;i<lists.size();i++){
                        lists.get(i).setDefautChoose(0);
                        storeCarAdapter.notifyItemChanged(i);
                    }
                    isclick=true;
                }else {
                    actionNewQuestionTv.setText("编辑");
                    nowClaimBtnId.setText("立即购买");
                    allCownumId.setVisibility(View.VISIBLE);
                    for (int i=0;i<lists.size();i++){
                        lists.get(i).setDefautChoose(0);
                        storeCarAdapter.notifyItemChanged(i);
                    }
                    isclick=false;
                }
                allCkId.setChecked(false);
                chooseNum=0;
                allMoney=0;
                allCownumId.setText("合计：￥"+allMoney);
                break;
            default:
                break;
        }
    }

    /**
     * 购物车内商品改变通知后台
     */
    private void addCar(){
        for(int i=0;i<lists.size();i++){
            if(lists.get(i).isChoose()){
                AddStoreCarParamBean addStoreCarParamBean=new AddStoreCarParamBean();
                addStoreCarParamBean.setProductId(lists.get(i).getProductId());
                addStoreCarParamBean.setQuantity(lists.get(i).getQuantity());
                storeCarResultBeans.add(addStoreCarParamBean);
            }
        }

        if(storeCarResultBeans.size()>0){
            AddShopCarResultBean addShopCarResultBean=new AddShopCarResultBean();
            addShopCarResultBean.setShopCartReqVos(storeCarResultBeans);
            storeCarPresenter.addStoreCar(reqData,addShopCarResultBean);
        }else {
            if(!isBuy){
                finish();
            }
        }

    }
    @Override
    public void onRefresh() {
        currentPage = 1;
        isFirst = true;
        lists.clear();
        storeCarPayPresenter.getCarList(reqData,currentPage);
        swipeLy.setRefreshing(false);
    }

    @Override
    public void onLoadMoreRequested() {
        isMoreLoad = true;
        currentPage += 1;
        storeCarPayPresenter.getCarList(reqData,currentPage);
    }


    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getStoreInfoList(StoreInfoListResultBean storeInfoListResultBean) {

    }

    @Override
    public void getStoreCarNum(StoreCarNumResultBean storeCarNumResultBean) {

    }

    @Override
    public void addStoreCar(AddStoreCarResultBean addStoreCarResultBean) {
        if("000000".equals(addStoreCarResultBean.getCode())){
        }else {
            showToast(addStoreCarResultBean.getMessage());
        }
        if(!isBuy){
            finish();
        }

    }

    @Override
    public void nowBuyOrder(NowBuyOrderResultBean nowBuyOrderResultBean) {
        if("000000".equals(nowBuyOrderResultBean.getCode())){
            Intent intent=new Intent(StoreCarActivity.this,StoreSureOderActivity.class);
            intent.putExtra("infos",nowBuyOrderResultBean);
            intent.putExtra("goodlists",(Serializable) storeCarResultBeans);
            startActivity(intent);
        }else {
            showToast(nowBuyOrderResultBean.getMessage());
        }

    }

    @Override
    public void nowPayOrder(NowPayOrderResultBean nowPayOrderResultBean) {

    }

    @Override
    public void getCarList(CarListResultBean carListResultBean) {
        if ("000000".equals(carListResultBean.getCode())) {
            if (SDCardUtil.isNullOrEmpty(carListResultBean.getBizData())) {
                if (isFirst) {
                    rvOrder.setVisibility(View.VISIBLE);
                    ryId.setVisibility(View.GONE);
                    rvBottomId.setVisibility(View.GONE);
                } else {
                    rvOrder.setVisibility(View.GONE);
                    ryId.setVisibility(View.VISIBLE);
                    rvBottomId.setVisibility(View.VISIBLE);
                }
                storeCarAdapter.loadMoreEnd();
            } else {
                isFirst = false;
                lists.addAll(carListResultBean.getBizData());
                storeCarAdapter.setNewData(this.lists);
                storeCarAdapter.loadMoreComplete();
            }
        } else {
            showToast(carListResultBean.getMessage());
        }
    }

    @Override
    public void deleteStoreCar(DeleteCarResultBean deleteCarResultBean) {
        if("000000".equals(deleteCarResultBean.getCode())){
            currentPage = 1;
            isFirst = true;
            lists.clear();
            storeCarPayPresenter.getCarList(reqData,currentPage);
        }else {
            showToast(deleteCarResultBean.getMessage());
        }

    }

    @Override
    public void storeAddrList(StoreAddrResultBean storeAddrResultBean) {

    }

    @Override
    public void showLoading() {
        if (isMoreLoad) {
            loadingLayout.setVisibility(View.GONE);
            ryId.setVisibility(View.VISIBLE);
            isMoreLoad = false;
        } else {
            loadingLayout.setVisibility(View.VISIBLE);
            ryId.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        ryId.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver1 != null) {
            unregisterReceiver(receiver1);
        }
        if(storeCarPayPresenter!=null){
            storeCarPayPresenter.detachView();
        }
        if(storeCarPresenter!=null){
            storeCarPresenter.detachView();
        }
    }

    @Override
    public void onBackPressed() {
        isBuy=false;
        addCar();
    }
}
