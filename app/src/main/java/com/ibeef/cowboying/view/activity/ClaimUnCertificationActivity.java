package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.OrderInitBase;
import com.ibeef.cowboying.base.UseCouponListBase;
import com.ibeef.cowboying.base.UserInfoBase;
import com.ibeef.cowboying.bean.CouponNumResultBean;
import com.ibeef.cowboying.bean.CreatOderResultBean;
import com.ibeef.cowboying.bean.CreatOrderParamBean;
import com.ibeef.cowboying.bean.ModifyHeadResultBean;
import com.ibeef.cowboying.bean.ModifyNickResultBean;
import com.ibeef.cowboying.bean.PayInitResultBean;
import com.ibeef.cowboying.bean.RealNameParamBean;
import com.ibeef.cowboying.bean.RealNameReaultBean;
import com.ibeef.cowboying.bean.UseCouponListResultBean;
import com.ibeef.cowboying.bean.UserInfoResultBean;
import com.ibeef.cowboying.config.Constant;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.OrderInitPresenter;
import com.ibeef.cowboying.presenter.UseCouponListPresenter;
import com.ibeef.cowboying.presenter.UserInfoPresenter;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 立即认领未实名认证界面
 */
public class ClaimUnCertificationActivity extends BaseActivity implements UserInfoBase.IView,OrderInitBase.IView , UseCouponListBase.IView{

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.et_real_name_id)
    EditText etRealNameId;
    @Bind(R.id.et_real_identification_id)
    EditText etRealIdentificationId;
    @Bind(R.id.mobile_txt_id)
    TextView mobileTxtId;
    @Bind(R.id.et_right_code_id)
    EditText etRightCodeId;
    @Bind(R.id.agree_xieyi_chck)
    CheckBox agreeXieyiChck;
    @Bind(R.id.xieyiss_txt_id)
    TextView xieyissTxtId;
    @Bind(R.id.is_use_id)
    TextView isUseId;
    @Bind(R.id.is_coupon_rv)
    RelativeLayout isCouponRv;
    @Bind(R.id.next_step_txt)
    TextView nextStepTxt;
    @Bind(R.id.coupon_num_id)
    TextView couponNumId;
    private String token;
    private String mobile;
    private int schemeId,quantity,userId;
    private UserInfoPresenter userInfoPresenter;
    private OrderInitPresenter orderInitPresenter;
    private final static int REQUESTCODE = 1; // 返回的结果码
    private   String selectId="";
    private  boolean check;
    private UseCouponListPresenter useCouponListPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_un_certification);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("认领");
        token= Hawk.get(HawkKey.TOKEN);
        mobile=getIntent().getStringExtra("mobile");
        schemeId=getIntent().getIntExtra("schemeId",0);
        quantity=getIntent().getIntExtra("quantity",0);
        userId=getIntent().getIntExtra("userId",0);
        mobileTxtId.setText(mobile);

        userInfoPresenter=new UserInfoPresenter(this);
        orderInitPresenter=new OrderInitPresenter(this);
        useCouponListPresenter=new UseCouponListPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        useCouponListPresenter.getCouponNum(reqData,schemeId+"");
    }

    @OnClick({R.id.back_id, R.id.is_coupon_rv, R.id.next_step_txt,R.id.xieyiss_txt_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.xieyiss_txt_id:
                Intent intent=new Intent(ClaimUnCertificationActivity.this,MyContractDetailActivity.class);
                intent.putExtra("fileName","");
                intent.putExtra("type","1");
                startActivity(intent);
                break;
            case R.id.is_coupon_rv:
                Intent intent1=new Intent(ClaimUnCertificationActivity.this,UseCouponActivity.class);
                intent1.putExtra("selectId",selectId);
                intent1.putExtra("check",check);
                intent1.putExtra("schemeId",schemeId);
                startActivityForResult(intent1,REQUESTCODE);
                break;
            case R.id.next_step_txt:
                // 实名认证
                if(TextUtils.isEmpty(etRealNameId.getText().toString().trim())){
                    showToast("姓名不能为空~");
                    return;
                }
                if(TextUtils.isEmpty(etRealIdentificationId.getText().toString().trim())){
                    showToast("身份证号不能为空~");
                    return;
                }

                if(agreeXieyiChck.isChecked()){
                    Map<String, String> reqData = new HashMap<>();
                    reqData.put("Authorization",token);
                    reqData.put("version",getVersionCodes());
                    RealNameParamBean realNameParamBean=new RealNameParamBean();
                    realNameParamBean.setUserId(userId+"");
                    realNameParamBean.setUserMobile(mobile);
                    realNameParamBean.setRealName(etRealNameId.getText().toString().trim());
                    realNameParamBean.setRealCardNo(etRealIdentificationId.getText().toString().trim());
                    userInfoPresenter.getRealName(reqData,realNameParamBean);
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
        if(couponNumResultBean.getBizData()>0){
            couponNumId.setVisibility(View.VISIBLE);
            couponNumId.setText(couponNumResultBean.getBizData()+"张可用");
            isUseId.setText("未使用");
        }else {
            isUseId.setText("无可用");
            couponNumId.setVisibility(View.GONE);
        }
    }

    @Override
    public void getUseCouponList(UseCouponListResultBean useCouponListResultBean) {

    }

    // 为了获取结果
     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
          super.onActivityResult(requestCode, resultCode, data);
         // RESULT_OK，判断另外一个activity已经结束数据输入功能，Standard activity result:
         // operation succeeded. 默认值是-1
          if (resultCode == 2) {
              if (requestCode == REQUESTCODE) {
                  selectId = data.getStringExtra("selectId");
                  check=data.getBooleanExtra("check",false);
                  //优惠金额
                  double couponmoney=data.getDoubleExtra("couponmoney",0);
                  if(check){
                      //不使用优惠券
                      isUseId.setText("未使用");
                  }else {
                      if(couponmoney>0){
                          isUseId.setText(couponmoney+"");
                      }else {
                          isUseId.setText("无可用");
                      }
                  }
                  Log.e(Constant.TAG,"fddffff"+selectId+check+couponmoney);
              }
          }
        }
    @Override
    public void getCreatOder(CreatOderResultBean creatOderResultBean) {
        if("000000".equals(creatOderResultBean.getCode())){
            Intent intent=new Intent(ClaimUnCertificationActivity.this,SureOderActivity.class);
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
    public void getModifyHead(ModifyHeadResultBean modifyHeadResultBean) {

    }

    @Override
    public void getModifNick(ModifyNickResultBean modifyNickResultBean) {

    }

    @Override
    public void getRealName(RealNameReaultBean realNameReaultBean) {
        if("000000".equals(realNameReaultBean.getCode())){
            Map<String, String> reqData = new HashMap<>();
            reqData.put("Authorization",token);
            reqData.put("version",getVersionCodes());
            CreatOrderParamBean creatOrderParamBean=new CreatOrderParamBean();
            creatOrderParamBean.setQuantity(quantity);
            creatOrderParamBean.setRecommender(etRightCodeId.getText().toString().trim());
            creatOrderParamBean.setSchemeId(schemeId);
            orderInitPresenter.getCreatOder(reqData,creatOrderParamBean);
        }else {
            showToast(realNameReaultBean.getMessage());
        }
    }

    @Override
    public void getUserInfo(UserInfoResultBean userInfoResultBean) {

    }

    @Override
    public void isTakePhoeto(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        if(userInfoPresenter!=null){
            userInfoPresenter.detachView();
        }
        if(orderInitPresenter!=null){
            orderInitPresenter.detachView();
        }
        super.onDestroy();
    }
}
