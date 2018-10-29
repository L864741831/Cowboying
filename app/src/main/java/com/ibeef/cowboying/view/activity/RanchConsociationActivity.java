package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.RanchConsociationTitleAdapter;
import com.ibeef.cowboying.base.PastureBase;
import com.ibeef.cowboying.bean.PastureAllResultBean;
import com.ibeef.cowboying.bean.PastureDetelResultBean;
import com.ibeef.cowboying.presenter.PasturePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * @author Administrator
 * 合作牧场主界面
 */
public class RanchConsociationActivity extends BaseActivity implements PastureBase.IView {


    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.lable_list_rv)
    RecyclerView lableListRv;
    private RanchConsociationTitleAdapter ranchConsociationTitleAdapter;
    private List<PastureAllResultBean.BizDataBean> dataBeen;
    private PasturePresenter pasturePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranch_consociation);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("合作牧场");
        dataBeen=new ArrayList<>();
        pasturePresenter=new PasturePresenter(this);

        lableListRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ranchConsociationTitleAdapter = new RanchConsociationTitleAdapter(dataBeen, this, R.layout.item_day_new_title);
        lableListRv.setAdapter(ranchConsociationTitleAdapter);

        pasturePresenter.getPastureAllVideo(getVersionCodes());


    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getPastureAllVideo(PastureAllResultBean pastureAllResultBean) {
        dataBeen.addAll(pastureAllResultBean.getBizData());
        ranchConsociationTitleAdapter.setNewData(dataBeen);
        ranchConsociationTitleAdapter.loadMoreEnd();
        pasturePresenter.getPastureDetelVideo(getVersionCodes(),pastureAllResultBean.getBizData().get(0).getPastureId());
    }

    @Override
    public void getPastureDetelVideo(PastureDetelResultBean pastureDetelResultBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        if(pasturePresenter!=null){
            pasturePresenter.detachView();
        }
        super.onDestroy();
    }
}
