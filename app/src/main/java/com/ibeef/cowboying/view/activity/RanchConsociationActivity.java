package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.RanchConsociationTitleAdapter;
import com.ibeef.cowboying.bean.RanchConsociationTitleBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * @author Administrator
 * 合作牧场主界面
 */
public class RanchConsociationActivity extends BaseActivity {


    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.lable_list_rv)
    RecyclerView lableListRv;
    private RanchConsociationTitleAdapter ranchConsociationTitleAdapter;
    private List<RanchConsociationTitleBean.DataBean> dataBeen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranch_consociation);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("合作牧场");
        lableListRv.setHasFixedSize(true);
        lableListRv.setNestedScrollingEnabled(false);
        lableListRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ranchConsociationTitleAdapter = new RanchConsociationTitleAdapter(dataBeen, this, R.layout.item_day_new_title);
        lableListRv.setAdapter(ranchConsociationTitleAdapter);

    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
    }
}
