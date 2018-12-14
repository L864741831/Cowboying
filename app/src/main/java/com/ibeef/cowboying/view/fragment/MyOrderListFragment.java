package com.ibeef.cowboying.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MyOrderListAdapter;
import com.ibeef.cowboying.base.MyOrderListBase;
import com.ibeef.cowboying.bean.MyAfterSaleDetailBean;
import com.ibeef.cowboying.bean.MyAfterSaleListBean;
import com.ibeef.cowboying.bean.MyOrderListBean;
import com.ibeef.cowboying.bean.MyOrderListCancelBean;
import com.ibeef.cowboying.bean.MyOrderListDetailBean;
import com.ibeef.cowboying.bean.ShowDeleveryResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.MyOrderListPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.ibeef.cowboying.utils.WrapContentLinearLayoutManager;
import com.ibeef.cowboying.view.activity.AfterSaleApplyActivity;
import com.ibeef.cowboying.view.activity.MainActivity;
import com.ibeef.cowboying.view.activity.MyOrderDetailActivity;
import com.ibeef.cowboying.view.activity.MyorderListCancelDialog;
import com.ibeef.cowboying.view.activity.ShowOrderDeleveryActivity;
import com.ibeef.cowboying.view.activity.StorePayTypeActivity;
import com.ibeef.cowboying.view.customview.SuperSwipeRefreshLayout;
import com.orhanobut.hawk.Hawk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import rxfamily.view.BaseFragment;


/**
 * 我的商城订单列表
 *
 * @author lalala
 */
public class MyOrderListFragment extends BaseFragment implements View.OnClickListener,MyOrderListBase.IView,SuperSwipeRefreshLayout.OnPullRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    RecyclerView mRView;
    ImageView tvTextNull;
    RelativeLayout rv_order;
    SuperSwipeRefreshLayout swipeLayout;
    TextView btn_into_store;
    private String token;
    private String stadus;
    private RelativeLayout loadingLayout;
    private List<MyOrderListBean.BizDataBean> listData;
    private MyOrderListAdapter myOrderListAdapter;
    private String cancelReson = "";
    private int page = 1;
    private boolean isMoreLoad = false;
    private String groupId;
    private MyOrderListPresenter myOrderListPresenter;


    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        mRView=view.findViewById(R.id.video_list_rv);
        tvTextNull=view.findViewById(R.id.tv_text_null);
        rv_order=view.findViewById(R.id.rv_order);
        swipeLayout=view.findViewById(R.id.swipe_layout);
        btn_into_store = view.findViewById(R.id.btn_into_store);
        btn_into_store.setOnClickListener(this);

        swipeLayout.setHeaderViewBackgroundColor(getResources().getColor(R.color.colorAccent));
        swipeLayout.setHeaderView(createHeaderView());// add headerView
        swipeLayout.setTargetScrollWithLayout(true);
        swipeLayout.setOnPullRefreshListener(this);
        listData=new ArrayList<>();
        loadingLayout = view.findViewById(R.id.loading_layout);
        mRView.setLayoutManager(new WrapContentLinearLayoutManager(getHoldingActivity(), LinearLayoutManager.VERTICAL, false));
        myOrderListAdapter = new MyOrderListAdapter(listData,getHoldingActivity());
        myOrderListAdapter.setOnLoadMoreListener(this, mRView);
        mRView.setAdapter(myOrderListAdapter);
        mRView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!mRView.canScrollVertically(-1)){
                    swipeLayout.setEnabled(true);
                }else {
                    swipeLayout.setEnabled(false);
                }
            }
        });
        myOrderListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getHoldingActivity(), MyOrderDetailActivity.class);
                intent.putExtra("orderId",myOrderListAdapter.getItem(position).getShopOrderResVo().getOrderId()+"");
                startActivity(intent);
            }
        });

        myOrderListPresenter = new MyOrderListPresenter(this);
    }

    public static MyOrderListFragment newInstance(String stadus) {
        MyOrderListFragment newFragment = new MyOrderListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("stadus", stadus);
        newFragment.setArguments(bundle);
        return newFragment;
    }


    @Override
    public void onResume() {
        super.onResume();
        page = 1;
        token = Hawk.get(HawkKey.TOKEN);
        listData.clear();
        if (!TextUtils.isEmpty(token)) {
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization", token);
            reqData.put("version", getVersionCodes());
            myOrderListPresenter.getMyOrderList(reqData, page,stadus);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_order_list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            stadus = args.getString("stadus");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        page = 1;
        listData.clear();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization", token);
        reqData.put("version", getVersionCodes());
        myOrderListPresenter.getMyOrderList(reqData, page,stadus);

    }

    @Override
    public void onPullDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {

    }

    @Override
    public void onLoadMoreRequested() {
        isMoreLoad = true;
        page += 1;
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization", token);
        reqData.put("version", getVersionCodes());
        myOrderListPresenter.getMyOrderList(reqData, page,stadus);
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void showLoading() {
        if(isMoreLoad){
            loadingLayout.setVisibility(View.GONE);
            mRView.setVisibility(View.VISIBLE);
            isMoreLoad=false;
        }else {
            loadingLayout.setVisibility(View.VISIBLE);
            mRView.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        mRView.setVisibility(View.VISIBLE);
        swipeLayout.setRefreshing(false);
    }

    @Override
    public void getMyOrderList(MyOrderListBean myOrderListBean) {
        if(myOrderListBean.getPageNo()==1&&SDCardUtil.isNullOrEmpty(myOrderListBean.getBizData())){
            rv_order.setVisibility(View.VISIBLE);
            mRView.setVisibility(View.GONE);
            return;
        }else {
            if(SDCardUtil.isNullOrEmpty(myOrderListBean.getBizData())){
                myOrderListAdapter.loadMoreEnd();
                return;
            }
        }
        this.listData.addAll(myOrderListBean.getBizData());

        rv_order.setVisibility(View.GONE);
        mRView.setVisibility(View.VISIBLE);

        if(SDCardUtil.isNullOrEmpty(myOrderListBean.getBizData())){
            myOrderListAdapter.loadMoreEnd();
        }else {
            myOrderListAdapter.setNewData(this.listData);
            myOrderListAdapter.loadMoreComplete();
        }

        myOrderListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyOrderListBean.BizDataBean dataBean= myOrderListAdapter.getItem(position);
                switch (view.getId()){
                    case R.id.btn_delet_order:
                        //删除订单
                        showDeleteOrder(dataBean.getShopOrderResVo().getOrderId()+"");
                        break;
                    case R.id.btn_see_order_progress:
                        //查看物流信息
                        Intent intent=new Intent(getHoldingActivity(),ShowOrderDeleveryActivity.class);
                        intent.putExtra("orderId",dataBean.getShopOrderResVo().getOrderId()+"");
                        startActivity(intent);
                        break;
                    case  R.id.btn_confirm_receipt:
                        //确认收货
                        Map<String, String> reqData = new HashMap<>();
                        reqData.put("Authorization", token);
                        reqData.put("version", getVersionCodes());
                        myOrderListPresenter.getMyOrderListOk(reqData, dataBean.getShopOrderResVo().getOrderId()+"");
                        break;
                    case  R.id.btn_cancle_order:
                        //取消订单
                        Intent intent2 = new Intent(getHoldingActivity(),MyorderListCancelDialog.class);
                        intent2.putExtra("orderCode",""+dataBean.getShopOrderResVo().getOrderId()+"");
                        startActivity(intent2);
                        break;
                    case  R.id.btn_apply_return:
                        //申请退款
                        Intent intent3 = new Intent(getHoldingActivity(),AfterSaleApplyActivity.class);
                        intent3.putExtra("orderCode",dataBean.getShopOrderResVo().getOrderId()+"");
                        startActivity(intent3);
                        break;
                    case  R.id.btn_to_pay:
                        //去支付
                        Intent intent5 = new Intent(getHoldingActivity(),StorePayTypeActivity.class);
                        intent5.putExtra("orderId",dataBean.getShopOrderResVo().getOrderId());
                        intent5.putExtra("createTime", dataBean.getShopOrderResVo().getCreateTime());
                        Log.i("htht", "去付款createTime::::: "+dataBean.getShopOrderResVo().getCreateTime());
                        startActivity(intent5);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void getMyOrderListDetail(MyOrderListDetailBean MyOrderListDetailBean) {

    }

    @Override
    public void getMyOrderListDelete(MyOrderListCancelBean msg) {
        if("000000".equals(msg.getCode())){
            page = 1;
            int previousSize = listData.size();
            listData.clear();
            myOrderListAdapter.notifyItemRangeRemoved(0, previousSize);
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization", token);
            reqData.put("version", getVersionCodes());
            myOrderListPresenter.getMyOrderList(reqData, page,stadus);
            Toast.makeText(getHoldingActivity(),"删除订单成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getHoldingActivity(), msg.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getMyOrderListCancel(MyOrderListCancelBean myOrderListCancelBean) {

    }

    @Override
    public void showDelevery(ShowDeleveryResultBean showDeleveryResultBean) {

    }

    @Override
    public void getMyOrderListOk(MyOrderListCancelBean myOrderListCancelBean) {
        if("000000".equals(myOrderListCancelBean.getCode())){
            page = 1;
            listData.clear();
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization", token);
            reqData.put("version", getVersionCodes());
            myOrderListPresenter.getMyOrderList(reqData, page,stadus);
            Toast.makeText(getHoldingActivity(),"确认收货成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getHoldingActivity(), myOrderListCancelBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getAfterSaleList(MyAfterSaleListBean myAfterSaleListBean) {

    }

    @Override
    public void getAfterSaleDetail(MyAfterSaleDetailBean myAfterSaleDetailBean) {

    }

    @Override
    public void getApplyReturn(MyOrderListCancelBean myOrderListCancelBean) {

    }

    @Override
    public void getCancelApplyReturn(MyOrderListCancelBean myOrderListCancelBean) {

    }

    @Override
    public void getEditApplyReturn(MyOrderListCancelBean myOrderListCancelBean) {

    }

    public  void showDeleteOrder(final String orderId){
        final NormalDialog dialog = new NormalDialog(getHoldingActivity());
        dialog.isTitleShow(false)
                .content("确定删除订单？")
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
                        if (!TextUtils.isEmpty(token)) {
                            Map<String, String> reqData = new HashMap<>();
                            reqData.put("Authorization", token);
                            reqData.put("version", getVersionCodes());
                            myOrderListPresenter.getMyOrderListDelete(reqData, orderId);
                        }
                        dialog.dismiss();
                    }
                });
    }

    @Override
    public void onDestroy() {
        if(myOrderListPresenter!=null){
            myOrderListPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        Intent intent1=new Intent(getHoldingActivity(),MainActivity.class);
        intent1.putExtra("index",1);
        startActivity(intent1);
    }
}
