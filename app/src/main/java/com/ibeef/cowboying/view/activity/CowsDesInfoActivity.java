package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.CattleDetailBase;
import com.ibeef.cowboying.bean.AdoptInfosResultBean;
import com.ibeef.cowboying.bean.CattleDetailResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.CattleDetailPresenter;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 牛只详情
 */
public class CowsDesInfoActivity extends BaseActivity implements CattleDetailBase.IView {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.cows_identify_id)
    TextView cowsIdentifyId;
    @Bind(R.id.cows_mouth_id)
    TextView cowsMouthId;
    @Bind(R.id.cows_weight_id)
    TextView cowsWeightId;
    @Bind(R.id.cows_img_id)
    ImageView cowsImgId;
    private CattleDetailPresenter cattleDetailPresenter;
    private String token;
    private int cattleId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cows_des_info);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("牛只详情");
        cattleId=getIntent().getIntExtra("cattleId",0);
        token= Hawk.get(HawkKey.TOKEN);
        cattleDetailPresenter=new CattleDetailPresenter(this);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        cattleDetailPresenter.getCattleDetail(reqData,cattleId);
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getCattleDetail(CattleDetailResultBean cattleDetailResultBean) {

        if("000000".equals(cattleDetailResultBean.getCode())){
            RequestOptions options = new RequestOptions()
                    .skipMemoryCache(true)
                    .error(R.mipmap.jzsb)
                    //跳过内存缓存
                    ;
            Glide.with(CowsDesInfoActivity.this).load(Constant.imageDomain+cattleDetailResultBean.getBizData().getImageUrl()).apply(options).into(cowsImgId);
            cowsIdentifyId.setText(cattleDetailResultBean.getBizData().getCode());
            cowsMouthId.setText(cattleDetailResultBean.getBizData().getAge()+"月");
            cowsWeightId.setText(cattleDetailResultBean.getBizData().getWeight()+"KG");
        }else {
            showToast(cattleDetailResultBean.getMessage());
        }
    }

    @Override
    public void getAdoptInfos(AdoptInfosResultBean adoptInfosResultBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        if (cattleDetailPresenter!=null){
            cattleDetailPresenter.detachView();
        }
        super.onDestroy();
    }
}
