package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.MessegeBase;
import com.ibeef.cowboying.bean.DeleteMessegeResultBean;
import com.ibeef.cowboying.bean.MessegeListReslutBean;
import com.ibeef.cowboying.bean.MessegeNumResultBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.MessegePresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 消息
 */
public class MessegeChooseActivity extends BaseActivity implements MessegeBase.IView {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.txt1_id)
    TextView txt1Id;
    @Bind(R.id.system_messege_rv)
    RelativeLayout systemMessegeRv;
    @Bind(R.id.txt2_id)
    TextView txt2Id;
    @Bind(R.id.unuse_messege_rv)
    RelativeLayout unuseMessegeRv;
    @Bind(R.id.txt3_id)
    TextView txt3Id;
    @Bind(R.id.eatcows_messege_rv)
    RelativeLayout eatcowsMessegeRv;
    @Bind(R.id.txt4_id)
    TextView txt4Id;
    @Bind(R.id.storeorder_messege_rv)
    RelativeLayout storeorderMessegeRv;
    @Bind(R.id.txt5_id)
    TextView txt5Id;
    @Bind(R.id.mucoupon_messege_rv)
    RelativeLayout mucouponMessegeRv;
    private Intent intent;
    private MessegePresenter messegePresenter;
    private String token;
    private Map<String, String> reqData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messege_choose);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        intent=new Intent(MessegeChooseActivity.this,MyMessegeActivity.class);
        messegePresenter=new MessegePresenter(this);
        token= Hawk.get(HawkKey.TOKEN);
        reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        info.setText("消息");
    }

    @Override
    protected void onResume() {
        super.onResume();
        messegePresenter.getMessegeNum(reqData);
    }

    @OnClick({R.id.back_id, R.id.system_messege_rv, R.id.unuse_messege_rv, R.id.eatcows_messege_rv, R.id.storeorder_messege_rv, R.id.mucoupon_messege_rv})
    public void onViewClicked(View view) {
        //（1：系统消息；2：吃肉省钱消息；3：养牛赚钱消息；4：商城消息；5：优惠券消息）
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.system_messege_rv:
                intent.putExtra("type",1);
                startActivity(intent);
                break;
            case R.id.unuse_messege_rv:
                intent.putExtra("type",2);
                startActivity(intent);
                break;
            case R.id.eatcows_messege_rv:
                intent.putExtra("type",3);
                startActivity(intent);
                break;
            case R.id.storeorder_messege_rv:
                intent.putExtra("type",4);
                startActivity(intent);
                break;
            case R.id.mucoupon_messege_rv:
                intent.putExtra("type",5);
                startActivity(intent);
                break;
            default:
                break;

        }
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getMessegeList(MessegeListReslutBean messegeListReslutBean) {

    }

    @Override
    public void getMessegeNum(MessegeNumResultBean numResultBean) {
        if("000000".equals(numResultBean.getCode())){
            if(SDCardUtil.isNullOrEmpty(numResultBean.getBizData())){
                txt1Id.setVisibility(View.GONE);
                txt2Id.setVisibility(View.GONE);
                txt3Id.setVisibility(View.GONE);
                txt4Id.setVisibility(View.GONE);
                txt5Id.setVisibility(View.GONE);
            }else {
                if(numResultBean.getBizData().getSysMsgCount()>0){
                    txt1Id.setVisibility(View.VISIBLE);
                    txt1Id.setText(numResultBean.getBizData().getSysMsgCount()+"");
                }else {
                    txt1Id.setVisibility(View.GONE);
                }
                if(numResultBean.getBizData().getEatMeatCount()>0){
                    txt2Id.setVisibility(View.VISIBLE);
                    txt2Id.setText(numResultBean.getBizData().getEatMeatCount()+"");
                }else {
                    txt2Id.setVisibility(View.GONE);
                }
                if(numResultBean.getBizData().getAdoptCount()>0){
                    txt3Id.setVisibility(View.VISIBLE);
                    txt3Id.setText(numResultBean.getBizData().getAdoptCount()+"");
                }else {
                    txt3Id.setVisibility(View.GONE);
                }
                if(numResultBean.getBizData().getShopCount()>0){
                    txt4Id.setVisibility(View.VISIBLE);
                    txt4Id.setText(numResultBean.getBizData().getShopCount()+"");
                }else {
                    txt4Id.setVisibility(View.GONE);
                }
                if(numResultBean.getBizData().getCouponCount()>0){
                    txt5Id.setVisibility(View.VISIBLE);
                    txt5Id.setText(numResultBean.getBizData().getCouponCount()+"");
                }else {
                    txt5Id.setVisibility(View.GONE);
                }
            }

        }else {
            showToast(numResultBean.getMessage());
        }

    }

    @Override
    public void getMessegeDelete(DeleteMessegeResultBean deleteMessegeResultBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(messegePresenter!=null){
            messegePresenter.detachView();
        }
    }
}
