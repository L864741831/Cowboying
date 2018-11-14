package com.ibeef.cowboying.view.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.BuyCowListAdapter;
import com.ibeef.cowboying.adapter.JoinPeopleNumListAdapter;
import com.ibeef.cowboying.base.PastureBase;
import com.ibeef.cowboying.base.PastureDetailBase;
import com.ibeef.cowboying.bean.HistorySchemeResultBean;
import com.ibeef.cowboying.bean.JionPersonInfoResultBean;
import com.ibeef.cowboying.bean.PastureAllResultBean;
import com.ibeef.cowboying.bean.PastureDetelResultBean;
import com.ibeef.cowboying.bean.SchemeDetailReultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.PastureDetailPresenter;
import com.ibeef.cowboying.presenter.PasturePresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
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
 * 参与人数
 */
public class JionPeopleNumActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener,PastureDetailBase.IView {
    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.parsture_name_id)
    TextView parstureNameId;
    @Bind(R.id.show_time_id)
    TextView showTimeId;
    @Bind(R.id.swipe_ly)
    SwipeRefreshLayout swipeLy;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    @Bind(R.id.ry_id)
    RecyclerView ryId;
    @Bind(R.id.rv_order)
    RelativeLayout rvOrder;
    @Bind(R.id.lv_show_id)
    LinearLayout lvShowId;
    private JoinPeopleNumListAdapter joinPeopleNumListAdapter;
    private List<JionPersonInfoResultBean.BizDataBean> objectList;
    private String token;
    private int currentPage=1;
    private boolean isFirst=true;
    private boolean isMoreLoad=false;
    private PastureDetailPresenter pastureDetailPresenter;
    private  HistorySchemeResultBean.BizDataBean infos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jion_people_num);
        ButterKnife.bind(this);
        init();
    }
    private void init(){
        token = Hawk.get(HawkKey.TOKEN);
        infos= (HistorySchemeResultBean.BizDataBean) getIntent().getSerializableExtra("info");
        parstureNameId.setText("第"+infos.getCode()+"期");
        showTimeId.setText(infos.getStartTime());
        info.setText("参与人数");
        objectList=new ArrayList<>();
        swipeLy.setColorSchemeResources(R.color.colorAccent);
        swipeLy.setOnRefreshListener(this);
        swipeLy.setEnabled(true);
        ryId.setLayoutManager(new LinearLayoutManager(this));
        joinPeopleNumListAdapter=new JoinPeopleNumListAdapter(objectList,this,R.layout.item_join_people_num);
        joinPeopleNumListAdapter.setOnLoadMoreListener(this, ryId);
        ryId.setAdapter(joinPeopleNumListAdapter);
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

        pastureDetailPresenter=new PastureDetailPresenter(this);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        pastureDetailPresenter.getJionPersonInfo(reqData,infos.getSchemeId(),currentPage);

    }
    @OnClick({R.id.back_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        isFirst = true;
        objectList.clear();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        pastureDetailPresenter.getJionPersonInfo(reqData,infos.getSchemeId(),currentPage);
        swipeLy.setRefreshing(false);
    }

    @Override
    public void onLoadMoreRequested() {
        isMoreLoad = true;
        currentPage += 1;
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        pastureDetailPresenter.getJionPersonInfo(reqData,infos.getSchemeId(),currentPage);
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getJionPersonInfo(JionPersonInfoResultBean jionPersonInfoResultBean) {
        if ("000000".equals(jionPersonInfoResultBean.getCode())) {
            if (SDCardUtil.isNullOrEmpty(jionPersonInfoResultBean.getBizData())) {
                if (isFirst) {
                    rvOrder.setVisibility(View.VISIBLE);
                    ryId.setVisibility(View.GONE);
                    lvShowId.setVisibility(View.GONE);
                } else {
                    rvOrder.setVisibility(View.GONE);
                    ryId.setVisibility(View.VISIBLE);
                    lvShowId.setVisibility(View.VISIBLE);
                }
                joinPeopleNumListAdapter.loadMoreEnd();
            } else {
                isFirst = false;
                objectList.addAll(jionPersonInfoResultBean.getBizData());
                for (int i=0;i<objectList.size();i++){
                    objectList.get(i).setInviterId(i+1);
                }
                joinPeopleNumListAdapter.setNewData(this.objectList);
                joinPeopleNumListAdapter.loadMoreComplete();
            }
        } else {
            showToast(jionPersonInfoResultBean.getMessage());
        }
    }

    @Override
    public void getSchemeDetail(SchemeDetailReultBean schemeDetailReultBean) {

    }

    @Override
    public void showLoading() {
        if (isMoreLoad) {
            loadingLayout.setVisibility(View.GONE);
            ryId.setVisibility(View.VISIBLE);
            lvShowId.setVisibility(View.VISIBLE);
            isMoreLoad = false;
        } else {
            loadingLayout.setVisibility(View.VISIBLE);
            ryId.setVisibility(View.GONE);
            lvShowId.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        ryId.setVisibility(View.VISIBLE);
        lvShowId.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        if(pastureDetailPresenter!=null){
            pastureDetailPresenter.detachView();
        }
        super.onDestroy();
    }
}
