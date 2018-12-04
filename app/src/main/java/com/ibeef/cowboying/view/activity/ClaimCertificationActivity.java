package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.ibeef.cowboying.base.UseCouponListBase;
import com.ibeef.cowboying.bean.CouponNumResultBean;
import com.ibeef.cowboying.bean.CreatOderResultBean;
import com.ibeef.cowboying.bean.CreatOrderParamBean;
import com.ibeef.cowboying.bean.PayInitResultBean;
import com.ibeef.cowboying.bean.UseCouponListResultBean;
import com.ibeef.cowboying.bean.UserInfoResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.OrderInitPresenter;
import com.ibeef.cowboying.presenter.UseCouponListPresenter;
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
public class ClaimCertificationActivity extends BaseActivity implements OrderInitBase.IView , UseCouponListBase.IView{

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
    @Bind(R.id.xieyiss_txt_id)
    TextView xieyissTxtId;
    @Bind(R.id.quan_txt_id)
    TextView quanTxtId;
    @Bind(R.id.coupon_num_id)
    TextView couponNumId;
    @Bind(R.id.mobie_txt_id)
    TextView mobieTxtId;
    @Bind(R.id.is_use_id)
    TextView isUseId;
    @Bind(R.id.is_coupon_rv)
    RelativeLayout isCouponRv;
    @Bind(R.id.next_step_txt)
    TextView nextStepTxt;
    private String token;
    private UserInfoResultBean infos;
    private Integer schemeId,quantity;
    private OrderInitPresenter orderInitPresenter;
    private final static int REQUESTCODE = 1; // 返回的结果码
    private   int selectId;
    private boolean check,isNewMan;
    private UseCouponListPresenter useCouponListPresenter;
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
        isNewMan=getIntent().getBooleanExtra("isNewMan",false);
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
        useCouponListPresenter=new UseCouponListPresenter(this);

        if(isNewMan){
            isCouponRv.setVisibility(View.GONE);
        }else {
            isCouponRv.setVisibility(View.VISIBLE);
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization",token);
            reqData.put("version",getVersionCodes());
            useCouponListPresenter.getCouponNum(reqData,schemeId+"",quantity);
        }
    }

    @OnClick({R.id.back_id, R.id.is_coupon_rv, R.id.next_step_txt,R.id.xieyiss_txt_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.xieyiss_txt_id:
                Intent intent=new Intent(ClaimCertificationActivity.this,MyContractDetailActivity.class);
                intent.putExtra("fileName","");
                intent.putExtra("type","1");
                startActivity(intent);
                break;
            case R.id.is_coupon_rv:
                Intent intent1=new Intent(ClaimCertificationActivity.this,UseCouponActivity.class);
                intent1.putExtra("selectId",selectId);
                intent1.putExtra("check",check);
                intent1.putExtra("schemeId",schemeId);
                intent1.putExtra("quantity",quantity);
                startActivityForResult(intent1,REQUESTCODE);
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
                    if(selectId!=0){
                        creatOrderParamBean.setCouponId(selectId);
                    }
                    Log.e(Constant.TAG,quantity+"?????"+schemeId);
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
    public void getCouponNum(CouponNumResultBean couponNumResultBean) {
        if("000000".equals(couponNumResultBean.getCode())){
            if(couponNumResultBean.getBizData()>0){
                couponNumId.setVisibility(View.VISIBLE);
                couponNumId.setText(couponNumResultBean.getBizData()+"张可用");
                isUseId.setText("未使用");
            }else {
                isUseId.setText("无可用");
                couponNumId.setVisibility(View.GONE);
            }
        }else {
            showToast(couponNumResultBean.getMessage());
        }

    }

    @Override
    public void getUseCouponList(UseCouponListResultBean useCouponListResultBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    // 为了获取结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RESULT_OK，判断另外一个activity已经结束数据输入功能，Standard activity result:
        // operation succeeded. 默认值是-1
        if (resultCode == 2) {
            if (requestCode == REQUESTCODE) {
                selectId = data.getIntExtra("selectId",0);
                 check=data.getBooleanExtra("check",false);
                //优惠金额
                double couponmoney=data.getDoubleExtra("couponmoney",0);
                if(check){
                    //不使用优惠券
                    isUseId.setText("未使用");
                    isUseId.setTextColor(getResources().getColor(R.color.txthui));
                }else {
                    if(couponmoney>0){
                        isUseId.setText("-"+couponmoney);
                        isUseId.setTextColor(getResources().getColor(R.color.red));
                    }else {
                        isUseId.setText("无可用");
                        isUseId.setTextColor(getResources().getColor(R.color.txthui));
                    }
                }
                Log.e(Constant.TAG,"fddffff"+selectId+check+couponmoney);
            }
        }
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
