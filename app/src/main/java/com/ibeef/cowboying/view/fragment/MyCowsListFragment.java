package com.ibeef.cowboying.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MyCowsListAdapter;
import com.ibeef.cowboying.base.MyCowsOrderBase;
import com.ibeef.cowboying.base.MyCowsOrderDeleteBean;
import com.ibeef.cowboying.bean.CreatOderResultBean;
import com.ibeef.cowboying.bean.MyCowsOrderListBean;
import com.ibeef.cowboying.bean.MyCowsOrderListDetailBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.MyCowsOrderPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.ibeef.cowboying.view.activity.MyCowsDetailActivity;
import com.ibeef.cowboying.view.activity.MyCowsProgressDialog;
import com.ibeef.cowboying.view.activity.SellCowsFirstActivity;
import com.ibeef.cowboying.view.activity.SureOderActivity;
import com.ibeef.cowboying.view.activity.SureOrderBackDialog;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import rxfamily.view.BaseFragment;


/**
 * 我的牛只
 *
 * @author lalala
 */
public class MyCowsListFragment extends BaseFragment implements MyCowsOrderBase.IView,SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    RecyclerView mRView;
    ImageView tvTextNull;
    RelativeLayout rv_order;
    SwipeRefreshLayout mSwipeLayout;
    private String token;
    private String stadus;
    private RelativeLayout loadingLayout;
    private List<MyCowsOrderListBean.BizDataBean> listData;
    private MyCowsListAdapter myCowsListAdapter;
    private String cancelReson = "";
    private int page = 1;
    private boolean isMoreLoad = false;
    private String groupId;
    private MyCowsOrderPresenter myCowsOrderPresenter;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        mRView=view.findViewById(R.id.video_list_rv);
        tvTextNull=view.findViewById(R.id.tv_text_null);
        rv_order=view.findViewById(R.id.rv_order);
        mSwipeLayout=view.findViewById(R.id.swipe_layout);

        mSwipeLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setEnabled(true);
        listData=new ArrayList<>();
        listData.clear();
        loadingLayout = view.findViewById(R.id.loading_layout);
        mRView.setLayoutManager(new LinearLayoutManager(getHoldingActivity()));
//        mRView.setHasFixedSize(true);
//        mRView.setNestedScrollingEnabled(false);
        myCowsListAdapter = new MyCowsListAdapter(listData,getHoldingActivity());
        myCowsListAdapter.setOnLoadMoreListener(this, mRView);
        mRView.setAdapter(myCowsListAdapter);
        mRView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!mRView.canScrollVertically(-1)){
                    mSwipeLayout.setEnabled(true);
                }else {
                    mSwipeLayout.setEnabled(false);
                }
            }
        });
        myCowsListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getHoldingActivity(), MyCowsDetailActivity.class);
                intent.putExtra("orderId",myCowsListAdapter.getItem(position).getOrderId()+"");
                startActivity(intent);
            }
        });

        myCowsOrderPresenter = new MyCowsOrderPresenter(this);
    }

    /**
     * （1:未付款；2：已付款未分配；3：已分配；4：已分配锁定期中；5：出售中；6:交易完成；9；交易关闭）
     *   空：全部
     */
    public static MyCowsListFragment newInstance(String stadus) {
        MyCowsListFragment newFragment = new MyCowsListFragment();
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
            myCowsOrderPresenter.geMyCowsOrderList(reqData, page,stadus);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_cows_list;
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
        myCowsOrderPresenter.geMyCowsOrderList(reqData, page,stadus);
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void onLoadMoreRequested() {
        isMoreLoad = true;
        page += 1;
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization", token);
        reqData.put("version", getVersionCodes());
        myCowsOrderPresenter.geMyCowsOrderList(reqData, page,stadus);
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
    }

    @Override
    public void geMyCowsOrderList(MyCowsOrderListBean myCowsOrderListBean) {
        if(myCowsOrderListBean.getPageNo()==1&&SDCardUtil.isNullOrEmpty(myCowsOrderListBean.getBizData())){
            rv_order.setVisibility(View.VISIBLE);
            mRView.setVisibility(View.GONE);
            return;
        }else {
            if(SDCardUtil.isNullOrEmpty(myCowsOrderListBean.getBizData())){
                myCowsListAdapter.loadMoreEnd();
                return;
            }
        }
        this.listData.addAll(myCowsOrderListBean.getBizData());

        rv_order.setVisibility(View.GONE);
        mRView.setVisibility(View.VISIBLE);

        if(SDCardUtil.isNullOrEmpty(myCowsOrderListBean.getBizData())){
            myCowsListAdapter.loadMoreEnd();
        }else {
            myCowsListAdapter.setNewData(this.listData);
            myCowsListAdapter.loadMoreComplete();
        }

        myCowsListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyCowsOrderListBean.BizDataBean dataBean= myCowsListAdapter.getItem(position);
                switch (view.getId()){
                    case R.id.delet_order:
                        //删除订单
                        showDeleteOrder(dataBean.getOrderId()+"");
                        break;
                    case R.id.see_order_progress:
                        //查看进度
                        Intent intent1 = new Intent(getHoldingActivity(),MyCowsProgressDialog.class);
                        intent1.putExtra("status",myCowsListAdapter.getItem(position).getStatus());
                        intent1.putExtra("LockMonths",myCowsListAdapter.getItem(position).getLockMonths()+"");
                        intent1.putExtra("UnlockTime",myCowsListAdapter.getItem(position).getUnlockTime());
                        startActivity(intent1);
                        break;
                    case  R.id.sell_want:
                        //我要卖牛
                        Calendar cal = Calendar.getInstance();
                        int i = cal.get(Calendar.DAY_OF_WEEK);
                        int hour = cal.get(Calendar.HOUR_OF_DAY);// 获取小时
                        int minute = cal.get(Calendar.MINUTE);// 获取分钟
                        int minuteOfDay = hour * 60 + minute;// 从0:00分开是到目前为止的分钟数
                        final int start = 10* 60;// 起始时间 10:00的分钟数
                        final int end = 22 * 60;// 结束时间 22:00的分钟数
                        if (i==2){
                            if (minuteOfDay >= start && minuteOfDay <= end) {
                                Intent intent = new Intent(getHoldingActivity(), SellCowsFirstActivity.class);
                                intent.putExtra("orderId",myCowsListAdapter.getItem(position).getOrderId()+"");
                                startActivity(intent);
                            } else {
                                showWantShellOrder();
                            }
                        }else{
                            showWantShellOrder();
                        }
                        break;
                    case  R.id.cancle_order:
                        //取消订单
                        Intent intent2 = new Intent(getHoldingActivity(),SureOrderBackDialog.class);
                        intent2.putExtra("orderCode",dataBean.getOrderId()+"");
                        startActivity(intent2);
                        break;
                    case  R.id.to_pay:
                        //去支付
                        if (!TextUtils.isEmpty(token)) {
                            Map<String, String> reqData = new HashMap<>();
                            reqData.put("Authorization", token);
                            reqData.put("version", getVersionCodes());
                            myCowsOrderPresenter.getMyCowsToPay(reqData, dataBean.getOrderId()+"");
                        }
                        break;
                    default:
                        break;
                }
            }
        });
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
                            myCowsOrderPresenter.getMyCowsOrderDelete(reqData, orderId);
                        }
                        dialog.dismiss();
                    }
                });
    }

    public  void showWantShellOrder(){
        final NormalDialog dialog = new NormalDialog(getHoldingActivity());
        dialog.isTitleShow(true)
                .title("卖牛提示")
                .content("亲爱的牛主人，还未到交易日哦，我们的交易日为每周一的10:00-22:00，到时候可别忘了来哦~")
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
                        dialog.dismiss();
                    }
                });
    }

    @Override
    public void geMyCowsOrderListDetail(MyCowsOrderListDetailBean myCowsOrderListDetailBean) {

    }

    @Override
    public void getMyCowsOrderDelete(MyCowsOrderDeleteBean msg) {
        if("000000".equals(msg.getCode())){
            page = 1;
            listData.clear();
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization", token);
            reqData.put("version", getVersionCodes());
            myCowsOrderPresenter.geMyCowsOrderList(reqData, page,stadus);
            Toast.makeText(getHoldingActivity(),"删除订单成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getHoldingActivity(), msg.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getMyCowsOrderCancel(MyCowsOrderDeleteBean msg) {
        if("000000".equals(msg.getCode())){
            page = 1;
            listData.clear();
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization", token);
            reqData.put("version", getVersionCodes());
            myCowsOrderPresenter.geMyCowsOrderList(reqData, page,stadus);
            Toast.makeText(getHoldingActivity(),"取消订单成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getHoldingActivity(), msg.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getMyCowsToPay(CreatOderResultBean creatOderResultBean) {
        if("000000".equals(creatOderResultBean.getCode())){
            Intent intent=new Intent(getHoldingActivity(),SureOderActivity.class);
            intent.putExtra("infos",creatOderResultBean);
            startActivity(intent);
        }else {
            showToast(creatOderResultBean.getMessage());
        }
    }

    @Override
    public void onDestroy() {
        if(myCowsOrderPresenter!=null){
            myCowsOrderPresenter.detachView();
        }
        super.onDestroy();
    }
}
