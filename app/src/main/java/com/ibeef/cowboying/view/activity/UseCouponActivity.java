package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.UseCouponListAdapter;
import com.ibeef.cowboying.base.UseCouponListBase;
import com.ibeef.cowboying.bean.AddStoreCarParamBean;
import com.ibeef.cowboying.bean.CouponNumParamBean;
import com.ibeef.cowboying.bean.CouponNumResultBean;
import com.ibeef.cowboying.bean.UseCouponListResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.UseCouponListPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.ibeef.cowboying.view.customview.SuperSwipeRefreshLayout;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 选择优惠券
 */
public class UseCouponActivity extends BaseActivity implements SuperSwipeRefreshLayout.OnPullRefreshListener, UseCouponListBase.IView{

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.agree_xieyi_chck)
    CheckBox agreeXieyiChck;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    @Bind(R.id.rv_bg)
    RelativeLayout rvOrder;
    @Bind(R.id.top_show_rv)
    RelativeLayout topShowRv;
    @Bind(R.id.ry_id)
    RecyclerView ryId;
    @Bind(R.id.swipe_ly)
    SuperSwipeRefreshLayout swipeLy;
    private UseCouponListAdapter useCouponListAdapter;
    private List<UseCouponListResultBean.BizDataBean> objectList;
    private String token;
    private int selectId;
    private boolean check;
    private UseCouponListPresenter useCouponListPresenter;
    private int schemeId,quantity;

    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    private  UseCouponListResultBean.BizDataBean item;
    private   Map<String, String> reqData;
    private boolean isStore;
    private List<AddStoreCarParamBean> storeCarResultBeans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_coupon);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        isStore=getIntent().getBooleanExtra("isStore",false);
        token= Hawk.get(HawkKey.TOKEN);
        info.setText("我的优惠券");

        swipeLy.setHeaderViewBackgroundColor(getResources().getColor(R.color.colorAccent));
        swipeLy.setHeaderView(createHeaderView());// add headerView
        swipeLy.setTargetScrollWithLayout(true);
        swipeLy.setOnPullRefreshListener(this);

        objectList=new ArrayList<>();
        useCouponListPresenter=new UseCouponListPresenter(this);

        selectId=getIntent().getIntExtra("selectId",0);
        check=getIntent().getBooleanExtra("check",false);

        ryId.setLayoutManager(new LinearLayoutManager(this));
        useCouponListAdapter=new UseCouponListAdapter(objectList,this,R.layout.item_use_coupon);
        ryId.setAdapter(useCouponListAdapter);
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

        useCouponListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                 item=useCouponListAdapter.getItem(position);
                agreeXieyiChck.setChecked(false);
                Intent intent = new Intent();
                intent.putExtra("selectId",item.getCouponId());
                intent.putExtra("couponmoney", item.getParValue());
                if(agreeXieyiChck.isChecked()){
                    intent.putExtra("check",true);
                }else {
                    intent.putExtra("check",false);
                }
                //通过intent对象返回结果，必须要调用一个setResult方法，
                //setResult(resultCode, data);第一个参数表示结果返回码，一般只要大于1就可以
                setResult(2, intent);
                finish();

            }
        });


        reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        CouponNumParamBean couponNumParamBean=new CouponNumParamBean();
        if(isStore){
            storeCarResultBeans= (List<AddStoreCarParamBean>) getIntent().getSerializableExtra("infos");
            couponNumParamBean.setProductQuantityReqDtos(storeCarResultBeans);
            couponNumParamBean.setUseType("3");
        }else {
            schemeId=getIntent().getIntExtra("schemeId",0);
            quantity=getIntent().getIntExtra("quantity",0);
            couponNumParamBean.setSchemeId(schemeId+"");
            couponNumParamBean.setQuantity(quantity+"");
            couponNumParamBean.setUseType("1");
        }

        useCouponListPresenter.getUseCouponList(reqData,couponNumParamBean);

    }

    @OnClick({R.id.back_id,R.id.agree_xieyi_chck})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
               Intent intent = new Intent();
               if(!SDCardUtil.isNullOrEmpty(item)){
                   intent.putExtra("couponmoney", item.getParValue());
                   intent.putExtra("selectId",item.getCouponId());
               }else {
                   intent.putExtra("selectId", 0);
               }
                if(agreeXieyiChck.isChecked()){
                    intent.putExtra("check",true);
                }else {
                    intent.putExtra("check",false);
                }
               //通过intent对象返回结果，必须要调用一个setResult方法，
               //setResult(resultCode, data);第一个参数表示结果返回码，一般只要大于1就可以
               setResult(2, intent);
               finish();
                break;
            case R.id.agree_xieyi_chck:
                item=null;
                agreeXieyiChck.setChecked(true);
                Intent intent1 = new Intent();
                if(!SDCardUtil.isNullOrEmpty(item)){
                    intent1.putExtra("couponmoney", item.getParValue());
                    intent1.putExtra("selectId", item.getCouponId());
                }else {
                    intent1.putExtra("selectId", 0);
                }
                if(agreeXieyiChck.isChecked()){
                    intent1.putExtra("check",true);
                }else {
                    intent1.putExtra("check",false);
                }
                //通过intent对象返回结果，必须要调用一个setResult方法，
                //setResult(resultCode, data);第一个参数表示结果返回码，一般只要大于1就可以
                setResult(2, intent1);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        isFirst = true;
        objectList.clear();
        CouponNumParamBean couponNumParamBean=new CouponNumParamBean();
        if(isStore){
            couponNumParamBean.setUseType("3");
            couponNumParamBean.setProductQuantityReqDtos(storeCarResultBeans);
        }else {
            couponNumParamBean.setUseType("1");
            couponNumParamBean.setSchemeId(schemeId+"");
            couponNumParamBean.setQuantity(quantity+"");
        }
        useCouponListPresenter.getUseCouponList(reqData,couponNumParamBean);
    }

    @Override
    public void onPullDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();

        if(!SDCardUtil.isNullOrEmpty(item)){
            intent.putExtra("couponmoney", item.getParValue());
            intent.putExtra("selectId", item.getCouponId());
        }else {
            intent.putExtra("selectId", 0);
        }
        if(agreeXieyiChck.isChecked()){
            intent.putExtra("check",true);
        }else {
            intent.putExtra("check",false);
        }
        //通过intent对象返回结果，必须要调用一个setResult方法，
        //setResult(resultCode, data);第一个参数表示结果返回码，一般只要大于1就可以
        setResult(2, intent);
        finish();
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getCouponNum(CouponNumResultBean couponNumResultBean) {

    }

    @Override
    public void getUseCouponList(UseCouponListResultBean useCouponListResultBean) {
        if ("000000".equals(useCouponListResultBean.getCode())) {
            if (SDCardUtil.isNullOrEmpty(useCouponListResultBean.getBizData())) {
                if (isFirst) {
                    rvOrder.setVisibility(View.VISIBLE);
                    ryId.setVisibility(View.GONE);
                    topShowRv.setVisibility(View.GONE);
                } else {
                    rvOrder.setVisibility(View.GONE);
                    ryId.setVisibility(View.VISIBLE);
                    topShowRv.setVisibility(View.VISIBLE);
                }
                useCouponListAdapter.loadMoreEnd();
            } else {
                isFirst = false;
                objectList.addAll(useCouponListResultBean.getBizData());
                if(check){
                    //未使用优惠券
                    agreeXieyiChck.setChecked(true);
                }else {
                    //使用优惠券刷新数据
                    for (int i=0;i<objectList.size();i++){
                        if(selectId==objectList.get(i).getCouponId()){
                            objectList.get(i).setDefautChoose(1);
                            item=objectList.get(i);
                        }else {
                            objectList.get(i).setDefautChoose(0);
                        }
                    }
                }
                useCouponListAdapter.setNewData(this.objectList);
                useCouponListAdapter.loadMoreComplete();
            }
        } else {
            showToast(useCouponListResultBean.getMessage());
        }
        swipeLy.setRefreshing(false);
    }

    @Override
    public void showLoading() {
        if (isMoreLoad) {
            loadingLayout.setVisibility(View.GONE);
            ryId.setVisibility(View.VISIBLE);
            topShowRv.setVisibility(View.VISIBLE);
            isMoreLoad = false;
        } else {
            loadingLayout.setVisibility(View.VISIBLE);
            ryId.setVisibility(View.GONE);
            topShowRv.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        ryId.setVisibility(View.VISIBLE);
        topShowRv.setVisibility(View.VISIBLE);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(useCouponListPresenter!=null){
            useCouponListPresenter.detachView();
        }
    }

}
