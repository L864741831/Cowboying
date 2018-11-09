package com.ibeef.cowboying.view.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MyCowsListAdapter;
import com.ibeef.cowboying.bean.MyCowsListBean;
import com.ibeef.cowboying.config.HawkKey;
import com.orhanobut.hawk.Hawk;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import rxfamily.view.BaseFragment;


/**
 * 我的牛只
 *
 * @author lalala
 */
public class MyCowsListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    @Bind(R.id.video_list_rv)
    RecyclerView mRView;
    @Bind(R.id.tv_text_null)
    ImageView tvTextNull;
    @Bind(R.id.rv_order)
    RelativeLayout rv_order;
    @Bind(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeLayout;
    private String token;
    private String stadus;
    private RelativeLayout loadingLayout;
    private List<MyCowsListBean.DataBean> listData;
    private MyCowsListAdapter myCowsListAdapter;
    private String cancelReson = "";
    private int page = 1;
    private boolean isMoreLoad = false;
    private String groupId;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setEnabled(true);
        listData=new ArrayList<>();
        listData.clear();
        loadingLayout = view.findViewById(R.id.loading_layout);
        mRView.setLayoutManager(new LinearLayoutManager(getHoldingActivity()));
        mRView.setHasFixedSize(true);
        mRView.setNestedScrollingEnabled(false);
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

    }

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
        page=1;
        token= Hawk.get(HawkKey.TOKEN);
        listData.clear();
        if(!TextUtils.isEmpty(token)){
//            orderListPresenter.setList(token,stadus,page);
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
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
