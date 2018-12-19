package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.adapter.MainFragmentAdapter;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.view.fragment.MyCowsListFragment;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;
import rxfamily.view.BaseFragment;

public class MyCowsActivity extends BaseActivity {


    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.nts_top)
    NavigationTabStrip ntsTop;
    @Bind(R.id.content_vp)
    ViewPager contentVp;
    private String token;
    private int index;
    private String[] title = {"全部", "待付款", "待分配", "可出售", "出售中"};
    private ArrayList<BaseFragment> fragmentList;
    private MyCowsListFragment fragment_0;
    private MyCowsListFragment fragment_1;
    private MyCowsListFragment fragment_2;
    private MyCowsListFragment fragment_3;
    private MyCowsListFragment fragment_4;
    private MainFragmentAdapter mAdpter;
    private boolean from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cows);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        from=getIntent().getBooleanExtra("from",false);
        token = Hawk.get(HawkKey.TOKEN);
        info.setText("我的牛只");
//        actionNewQuestionTv.setVisibility(View.VISIBLE);
//        actionNewQuestionTv.setText("我要卖牛");

        fragmentList = new ArrayList<>();
        /**
         * （1:未付款；2：已付款未分配；3：已分配；4：已分配锁定期中；5：出售中；6:交易完成；9；交易关闭）
         *   空：全部
         */

        fragment_0 = MyCowsListFragment.newInstance("");
        fragment_1 = MyCowsListFragment.newInstance("1");
        fragment_2 = MyCowsListFragment.newInstance("2");
        fragment_3 = MyCowsListFragment.newInstance("3");
        fragment_4 = MyCowsListFragment.newInstance("5");

        fragmentList.add(fragment_0);
        fragmentList.add(fragment_1);
        fragmentList.add(fragment_2);
        fragmentList.add(fragment_3);
        fragmentList.add(fragment_4);
        mAdpter = new MainFragmentAdapter(getSupportFragmentManager(), fragmentList);
        contentVp.setAdapter(mAdpter);
        ntsTop.setViewPager(contentVp, index);
        ntsTop.setTitles(title);
        ntsTop.setStripType(NavigationTabStrip.StripType.LINE);
        ntsTop.setStripGravity(NavigationTabStrip.StripGravity.BOTTOM);
        ntsTop.setTabIndex(index, true);
    }


    @OnClick({R.id.back_id, R.id.action_new_question_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                if(from){
                    removeALLActivity();//执行移除所以Activity方法
                    Intent intent1=new Intent(MyCowsActivity.this,MainActivity.class);
                    startActivity(intent1);
                }else {
                    finish();
                }
                break;
            case R.id.action_new_question_tv:
                Intent intent=new Intent(MyCowsActivity.this,SellCowsActivity.class);
                intent.putExtra("orderId","");
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (from){
            removeALLActivity();//执行移除所以Activity方法
            Intent intent1=new Intent(MyCowsActivity.this,MainActivity.class);
            startActivity(intent1);
        }
    }
}
