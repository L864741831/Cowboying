package com.ibeef.cowboying.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.BuyCowListAdapter;
import com.ibeef.cowboying.adapter.StoreCarAdapter;
import com.ibeef.cowboying.base.BuyCowSchemeBase;
import com.ibeef.cowboying.bean.ActiveSchemeResultBean;
import com.ibeef.cowboying.bean.StoreCarResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.BuyCowsSchemePresenter;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 商城购物车
 */
public class StoreCarActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

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
    private List<StoreCarResultBean> objectList;

    private String token;

    private int currentPage=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    private boolean isclick;
    private StoreCarAdapter storeCarAdapter;
    private BroadcastReceiver receiver1;
    private int num,position,chooseNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_car);
        ButterKnife.bind(this);
        init();
    }
    private void init(){
        token = Hawk.get(HawkKey.TOKEN);
        info.setText("购物车");
        actionNewQuestionTv.setText("编辑");
        actionNewQuestionTv.setVisibility(View.VISIBLE);
        swipeLy.setColorSchemeResources(R.color.colorAccent);
        swipeLy.setOnRefreshListener(this);
        swipeLy.setEnabled(true);
        objectList=new ArrayList<>();
        for (int i=0;i<10;i++){
            StoreCarResultBean storeCarResultBean=new StoreCarResultBean();
            storeCarResultBean.setDefautChoose(0);
            objectList.add(storeCarResultBean);
        }

        ryId.setHasFixedSize(true);
        ryId.setNestedScrollingEnabled(false);
        ryId.setLayoutManager(new GridLayoutManager(this,2));
        storeCarAdapter=new StoreCarAdapter(objectList,this,R.layout.item_store_car);
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
                objectList.get(position).setNum(num);
            }
        };
       registerReceiver(receiver1, intentFilter1);

        storeCarAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.all_ck_id:
                        if(0==objectList.get(position).getDefautChoose()){
                            objectList.get(position).setDefautChoose(1);
                            chooseNum++;
                        }else{
                            chooseNum--;
                            objectList.get(position).setDefautChoose(0);
                        }
                       storeCarAdapter.notifyItemChanged(position);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @OnClick({R.id.back_id,R.id.action_new_question_tv,R.id.go_store_btn,R.id.now_claim_btn_id,R.id.all_ck_id,R.id.refuce_id,R.id.sure_id,R.id.lvs_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
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
                int size = objectList.size();
                for(int i=size-1;i>=0;i--){
                    if(1==objectList.get(i).getDefautChoose()) {
                        objectList.remove(i);
                        storeCarAdapter.notifyItemRemoved(i);
                        storeCarAdapter.notifyItemChanged(i);
                    }
                }
                allCkId.setChecked(false);
                break;
            case R.id.all_ck_id:
              //全选
                for (int i=0;i<objectList.size();i++){
                    if(allCkId.isChecked()){
                        objectList.get(i).setDefautChoose(1);
                    }else {
                        objectList.get(i).setDefautChoose(0);
                    }
                    storeCarAdapter.notifyItemChanged(i);
                }
                chooseNum=objectList.size();
                break;
            case R.id.now_claim_btn_id:
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
                        startActivity(StoreSureOderActivity.class);
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
                    for (int i=0;i<objectList.size();i++){
                        objectList.get(i).setDefautChoose(0);
                        storeCarAdapter.notifyItemChanged(i);
                    }
                    isclick=true;
                }else {
                    actionNewQuestionTv.setText("编辑");
                    nowClaimBtnId.setText("立即购买");
                    allCownumId.setVisibility(View.VISIBLE);
                    for (int i=0;i<objectList.size();i++){
                        objectList.get(i).setDefautChoose(0);
                        storeCarAdapter.notifyItemChanged(i);
                    }
                    isclick=false;
                }
                allCkId.setChecked(false);
                chooseNum=0;
                break;
            default:
                break;
        }
    }
    @Override
    public void onRefresh() {
        currentPage = 1;
        isFirst = true;
//        objectList.clear();
        swipeLy.setRefreshing(false);
    }

    @Override
    public void onLoadMoreRequested() {
        isMoreLoad = true;
        currentPage += 1;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver1 != null) {
           unregisterReceiver(receiver1);
        }
    }
}
