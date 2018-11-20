package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.OrderInitBase;
import com.ibeef.cowboying.bean.CreatOderResultBean;
import com.ibeef.cowboying.bean.CreatOrderParamBean;
import com.ibeef.cowboying.bean.PayInitResultBean;
import com.ibeef.cowboying.bean.UserInfoResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.OrderInitPresenter;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import rxfamily.view.BaseActivity;

/**
 * 立即认领已实名认证的界面
 */
public class ClaimCertificationActivity extends BaseActivity implements OrderInitBase.IView {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.certify_head_img)
    CircleImageView certifyHeadImg;
    @Bind(R.id.nick_name_txt)
    TextView nickNameTxt;
    @Bind(R.id.user_certifycode_txt)
    TextView userCertifycodeTxt;
    @Bind(R.id.et_right_code_id)
    EditText etRightCodeId;
    @Bind(R.id.agree_xieyi_chck)
    CheckBox agreeXieyiChck;
    @Bind(R.id.xieyi_txt_id)
    TextView xieyiTxtId;
    @Bind(R.id.quan_txt_id)
    TextView quanTxtId;
    @Bind(R.id.coupon_num_id)
    TextView couponNumId;
    @Bind(R.id.mobie_txt_id)
    TextView mobieTxtId;
    @Bind(R.id.is_coupon_rv)
    RelativeLayout isCouponRv;
    @Bind(R.id.next_step_txt)
    TextView nextStepTxt;
    private String token;
    private UserInfoResultBean infos;
    private Integer schemeId,quantity;
    private OrderInitPresenter orderInitPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_certification);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("认领");
        token= Hawk.get(HawkKey.TOKEN);
        infos= (UserInfoResultBean) getIntent().getSerializableExtra("infos");
        schemeId=getIntent().getIntExtra("schemeId",0);
        quantity=getIntent().getIntExtra("quantity",0);
        orderInitPresenter=new OrderInitPresenter(this);
        RequestOptions options = new RequestOptions()
                .error(R.mipmap.meheaddefalut)
                //加载错误之后的错误图
                .skipMemoryCache(true)
                //跳过内存缓存
                ;
        Glide.with(this).load(Constant.imageDomain+infos.getBizData().getHeadImage()).apply(options).into(certifyHeadImg);
        nickNameTxt.setText(infos.getBizData().getRealName());
        userCertifycodeTxt.setText(infos.getBizData().getRealCardNo());
        mobieTxtId.setText("手机号 "+infos.getBizData().getMobile());
    }

    @OnClick({R.id.back_id, R.id.is_coupon_rv, R.id.next_step_txt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.is_coupon_rv:
//                if(TextUtils.isEmpty(token)){
//                    startActivity(LoginActivity.class);
//                }else {
//                    startActivity(UseCouponActivity.class);
//                }
                showToast("无可用优惠券");
                break;
            case R.id.next_step_txt:
                if(agreeXieyiChck.isChecked()){
                    Map<String, String> reqData = new HashMap<>();
                    reqData.put("Authorization",token);
                    reqData.put("version",getVersionCodes());
                    CreatOrderParamBean creatOrderParamBean=new CreatOrderParamBean();
                    creatOrderParamBean.setQuantity(quantity);
                    creatOrderParamBean.setRecommender(etRightCodeId.getText().toString().trim());
                    creatOrderParamBean.setSchemeId(schemeId);
                    orderInitPresenter.getCreatOder(reqData,creatOrderParamBean);

                }else {
                    showToast("请同意认领协议！");
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void getCreatOder(CreatOderResultBean creatOderResultBean) {
        if("000000".equals(creatOderResultBean.getCode())){
            Intent intent=new Intent(ClaimCertificationActivity.this,SureOderActivity.class);
            intent.putExtra("infos",creatOderResultBean);
            startActivity(intent);
            finish();
        }else {
            showToast(creatOderResultBean.getMessage());
        }
    }

    @Override
    public void getPayInit(PayInitResultBean payInitResultBean) {

    }

    @Override
    protected void onDestroy() {
        if(orderInitPresenter!=null){
            orderInitPresenter.detachView();
        }
        super.onDestroy();
    }
}
