package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MyMessegeListAdapter;
import com.ibeef.cowboying.base.HomeBannerBase;
import com.ibeef.cowboying.base.MessegeBase;
import com.ibeef.cowboying.bean.DeleteMessegeResultBean;
import com.ibeef.cowboying.bean.HomeAllVideoResultBean;
import com.ibeef.cowboying.bean.HomeBannerResultBean;
import com.ibeef.cowboying.bean.HomeSellCowNumResultBean;
import com.ibeef.cowboying.bean.HomeVideoResultBean;
import com.ibeef.cowboying.bean.MessegeListReslutBean;
import com.ibeef.cowboying.bean.MessegeNumResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.HomeBannerPresenter;
import com.ibeef.cowboying.presenter.MessegePresenter;
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
 * 我的消息界面
 */
public class MyMessegeActivity extends BaseActivity  implements SuperSwipeRefreshLayout.OnPullRefreshListener,BaseQuickAdapter.RequestLoadMoreListener,MessegeBase.IView, HomeBannerBase.IView{

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.message_ry)
    RecyclerView messageRy;
    @Bind(R.id.swipe_ly)
    SuperSwipeRefreshLayout swipeLy;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    @Bind(R.id.rv_order)
    RelativeLayout rvOrder;
    private MyMessegeListAdapter myMessegeListAdapter;
    private List<MessegeListReslutBean.BizDataBean> beanList;
    private String token;
    private int type;
    private Map<String, String> reqData;
    private MessegePresenter messegePresenter;
    private int currentPage=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;

    private HomeBannerPresenter homeBannerPresenter;
    private HomeBannerResultBean homeBannerResultBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_messege);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        type=getIntent().getIntExtra("type",0);
        token= Hawk.get(HawkKey.TOKEN);
        reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());

        info.setText("消息详情");
        messageRy.setLayoutManager(new LinearLayoutManager(this));
        beanList=new ArrayList<>();
        myMessegeListAdapter=new MyMessegeListAdapter(beanList,this,R.layout.my_messege_item);
        myMessegeListAdapter.setOnLoadMoreListener(this, messageRy);
        messageRy.setAdapter(myMessegeListAdapter);

        swipeLy.setHeaderViewBackgroundColor(getResources().getColor(R.color.colorAccent));
        swipeLy.setHeaderView(createHeaderView());// add headerView
        swipeLy.setTargetScrollWithLayout(true);
        swipeLy.setOnPullRefreshListener(this);

        messageRy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!messageRy.canScrollVertically(-1)){
                    swipeLy.setEnabled(true);
                }else {
                    swipeLy.setEnabled(false);
                }
            }
        });

        messegePresenter=new MessegePresenter(this);
        messegePresenter.getMessegeList(reqData,type, currentPage);

        homeBannerPresenter=new HomeBannerPresenter(this);
        homeBannerPresenter.getHomeBanner(reqData);

        myMessegeListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MessegeListReslutBean.BizDataBean item=myMessegeListAdapter.getItem(position);
                switch (item.getPageUrl()){
                    case "adopt_scheme_list":
                        //养牛方案列表
                        startActivity(BuyCowsPlanActivity.class);
                        break;
                    case "adopt_scheme_detail":
                        //养牛方案详情
                        Intent intent4=new Intent(MyMessegeActivity.this,CowsClaimActivity.class);
                        intent4.putExtra("schemId",Integer.parseInt(item.getParams()));
                        startActivity(intent4);
                        break;
                    case "adop_order_list":
                        //养牛订单列表
                        startActivity(MyCowsActivity.class);
                        break;
                    case "adop_order_detail":
                        //养牛订单详情
                        Intent intent3 = new Intent(MyMessegeActivity.this, MyCowsDetailActivity.class);
                        intent3.putExtra("orderId",item.getParams()+"");
                        startActivity(intent3);
                        break;
                    case "pasture_list":
                        //合作牧场列表
                        startActivity(RanchConsociationActivity.class);
                        break;
                    case "shop_product_list":
                        //商城商品列表
                        Intent intent1=new Intent(MyMessegeActivity.this,MainActivity.class);
                        intent1.putExtra("index",1);
                        startActivity(intent1);
                        break;
                    case "shop_order_list":
                        //商城订单列表
                        startActivity(MyOrderActivity.class);
                        break;
                    case "shop_order_detail":
                        //商城订单详情
                        Intent intent7 = new Intent(MyMessegeActivity.this, MyOrderDetailActivity.class);
                        intent7.putExtra("orderId",item.getParams()+"");
                        startActivity(intent7);
                        break;
                    case "total_assets":
                        //总资产
                        startActivity(MyAllMoneyActivity.class);
                        break;
                    case "coupon_list":
                        //优惠券列表
                        startActivity(DiscountCouponActivity.class);
                        break;
                    case "contract_list":
                        //合同列表
                        startActivity(MyContractActivity.class);
                        break;
                    case "vip_card_detail":
                        //会员卡详情
                        startActivity(VipCardActivity.class);
                        break;
                    case "new_welfare":
                        //新人福利
                        if(!SDCardUtil.isNullOrEmpty(homeBannerResultBean)){
                            Intent intent11=new Intent(MyMessegeActivity.this,NewManwelfareActivity.class);
                            intent11.putExtra("infos",homeBannerResultBean.getBizData().getNewUserActivity());
                            startActivity(intent11);
                        }
                        break;
                    default:
                        break;
                }
            }
        });

        myMessegeListAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                MessegeListReslutBean.BizDataBean item=myMessegeListAdapter.getItem(position);
                showDeleteMesage(item.getId());
                return false;
            }
        });
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
    }



    @Override
    public void onLoadMoreRequested() {
        isMoreLoad = true;
        currentPage += 1;
        messegePresenter.getMessegeList(reqData,type, currentPage);
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        isFirst = true;
        beanList.clear();
        messegePresenter.getMessegeList(reqData,type, currentPage);

    }

    @Override
    public void onPullDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {

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
    public void getMessegeList(MessegeListReslutBean messegeListReslutBean) {
        if ("000000".equals(messegeListReslutBean.getCode())) {
            if (SDCardUtil.isNullOrEmpty(messegeListReslutBean.getBizData())) {
                if (isFirst) {
                    rvOrder.setVisibility(View.VISIBLE);
                    messageRy.setVisibility(View.GONE);
                } else {
                    rvOrder.setVisibility(View.GONE);
                    messageRy.setVisibility(View.VISIBLE);
                }
                myMessegeListAdapter.loadMoreEnd();
            } else {
                isFirst = false;
                beanList.addAll(messegeListReslutBean.getBizData());
                myMessegeListAdapter.setNewData(this.beanList);
                myMessegeListAdapter.loadMoreComplete();
            }
        } else {
            showToast(messegeListReslutBean.getMessage());
        }
    }

    @Override
    public void getMessegeNum(MessegeNumResultBean numResultBean) {

    }

    @Override
    public void getMessegeDelete(DeleteMessegeResultBean deleteMessegeResultBean) {
        if("000000".equals(deleteMessegeResultBean.getCode())){
            showToast("消息删除成功！");
            currentPage = 1;
            isMoreLoad=false;
            isFirst = true;
            beanList.clear();
            messegePresenter.getMessegeList(reqData,type, currentPage);
        }else {
            showToast(deleteMessegeResultBean.getMessage());
        }
    }

    @Override
    public void showLoading() {
        if (isMoreLoad) {
            loadingLayout.setVisibility(View.GONE);
            messageRy.setVisibility(View.VISIBLE);
            isMoreLoad = false;
        } else {
            loadingLayout.setVisibility(View.VISIBLE);
            messageRy.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        messageRy.setVisibility(View.VISIBLE);
        swipeLy.setRefreshing(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(messegePresenter!=null){
            messegePresenter.detachView();
        }
    }

    public  void showDeleteMesage(final int messygeId){
        final NormalDialog dialog = new NormalDialog(MyMessegeActivity.this);
        dialog.isTitleShow(false)
                .content("确定删除改消息吗？")
                .titleTextSize(18)
                .titleTextColor(Color.parseColor("#101010"))
                .titleLineColor(Color.parseColor("#B0957A"))
                .contentGravity(Gravity.CENTER)
                .contentTextColor(Color.parseColor("#808080"))
                .dividerColor(Color.parseColor("#B0957A"))
                .btnTextSize(15.5f, 15.5f)
                .btnTextColor(Color.parseColor("#000000"), Color.parseColor("#B0957A"))
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
                          messegePresenter.getMessegeDelete(reqData,messygeId);
                          dialog.dismiss();
                    }
                });
    }
}
