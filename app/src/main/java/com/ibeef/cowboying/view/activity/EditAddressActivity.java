package com.ibeef.cowboying.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.ModifyAddressBase;
import com.ibeef.cowboying.bean.AddAddressParamBean;
import com.ibeef.cowboying.bean.CityBean;
import com.ibeef.cowboying.bean.DeleteCarResultBean;
import com.ibeef.cowboying.bean.DistrictBean;
import com.ibeef.cowboying.bean.ProvinceBean;
import com.ibeef.cowboying.bean.ShowAddressResultBean;
import com.ibeef.cowboying.citywhell.CityConfig;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.ModifyAddressPresenter;
import com.ibeef.cowboying.utils.Base64Util;
import com.ibeef.cowboying.utils.TimeUtils;
import com.ibeef.cowboying.view.customview.CityPickerView;
import com.ibeef.cowboying.view.customview.OnCityItemClickListener;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

public class EditAddressActivity extends BaseActivity implements ModifyAddressBase.IView{

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_tell)
    EditText etTell;
    @Bind(R.id.show_addr)
    TextView showAddr;
    @Bind(R.id.lv_addrs)
    RelativeLayout lvAddrs;
    @Bind(R.id.et_addr_describe)
    EditText etAddrDescribe;
    @Bind(R.id.ck_id)
    CheckBox ckId;
    @Bind(R.id.moren_id)
    TextView morenId;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    private String token;
    private ModifyAddressPresenter modifyAddressPresenter;
    CityPickerView mCityPickerView = new CityPickerView();
    private boolean isChckAddr=false;
    private String provinceId,cityId,districtId;
    private Map<String, String> reqData;
    private boolean addaddress;
    private   ShowAddressResultBean.BizDataBean infos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        addaddress=getIntent().getBooleanExtra("addaddress",false);
        token = Hawk.get(HawkKey.TOKEN);
        info.setText("添加收货地址");
        actionNewQuestionTv.setText("保存");
        actionNewQuestionTv.setVisibility(View.VISIBLE);
        //预先加载仿iOS滚轮实现的全部数据
        mCityPickerView.init(this);
        modifyAddressPresenter=new ModifyAddressPresenter(this);
        reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());

        if(!addaddress){
            isChckAddr=true;
            infos= (ShowAddressResultBean.BizDataBean) getIntent().getSerializableExtra("infos");
            etName.setText(infos.getName());
            etTell.setText(infos.getMobile());
            showAddr.setText(infos.getProvince()+infos.getCity()+infos.getRegion());
            etAddrDescribe.setText(infos.getDetailAddress());
            provinceId=infos.getProvinceId()+"";
            cityId=infos.getCityId()+"";
            districtId=infos.getRegionId()+"";
            if("1".equals(infos.getIsDefault())){
                ckId.setChecked(true);
            }else {
                ckId.setChecked(false);
            }
        }

    }

    @OnClick({R.id.back_id, R.id.lv_addrs,R.id.action_new_question_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.lv_addrs:
                InputMethodManager imm = (InputMethodManager) lvAddrs.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(),0);
                }
                wheel();
                break;
            case R.id.action_new_question_tv:
                //保存地址
                if(TextUtils.isEmpty(etName.getText().toString().trim())){
                    Toast.makeText(EditAddressActivity.this, "收货人不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!TimeUtils.isMatchered(TimeUtils.PHONE_PATTERN,etTell.getText().toString().trim())){
                    Toast.makeText(EditAddressActivity.this, "手机号错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(showAddr.getText().toString().trim())){
                    Toast.makeText(EditAddressActivity.this, "收货地址不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(etAddrDescribe.getText().toString().trim())){
                    Toast.makeText(EditAddressActivity.this, "详细地址不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Base64Util.containsEmoji(etName.getText().toString().trim())){
                    //真为含有表情
                    Toast.makeText(EditAddressActivity.this, "收货人不能含表情！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Base64Util.containsEmoji(etAddrDescribe.getText().toString().trim())){
                    //真为含有表情
                    Toast.makeText(EditAddressActivity.this, "详细地址不能含表情！", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(etAddrDescribe.getText().toString().trim().length()>40){
                    Toast.makeText(EditAddressActivity.this, "详细地址不能多于40个字！", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!isChckAddr){
                    showToast("请选择收货地址");
                }
                AddAddressParamBean addAddressParamBean=new AddAddressParamBean();
                addAddressParamBean.setCityId(cityId);
                addAddressParamBean.setProvinceId(provinceId);
                addAddressParamBean.setRegionId(districtId);
                addAddressParamBean.setDetailAddress(etAddrDescribe.getText().toString().trim());
                addAddressParamBean.setMobile(etTell.getText().toString().trim());
                addAddressParamBean.setName(etName.getText().toString().trim());
                if(ckId.isChecked()){
                    //'是否默认地址（0：不是；1：是）'
                    addAddressParamBean.setIsDefault(1);
                }else {
                    addAddressParamBean.setIsDefault(0);
                }
                if(!addaddress){
                    addAddressParamBean.setId(infos.getId());
                    modifyAddressPresenter.updateAddress(reqData,addAddressParamBean);

                }else {
                    addAddressParamBean.setId(null);
                    modifyAddressPresenter.addAddress(reqData,addAddressParamBean);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 弹出选择器
     */
    private void wheel() {

        CityConfig cityConfig = new CityConfig.Builder().title("选择城市")
                //标题
                .setShowGAT(true)
                .build();

        mCityPickerView.setConfig(cityConfig);

        mCityPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                showAddr.setText(province.getName()+city.getName()+district.getName());
                isChckAddr=true;
                provinceId=province.getId();
                cityId=city.getId();
                districtId=district.getId();
            }

            @Override
            public void onCancel() {
            }
        });
        mCityPickerView.showCityPicker();
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void addAddress(DeleteCarResultBean deleteCarResultBean) {

        if("000000".equals(deleteCarResultBean.getCode())){
            showToast("地址添加成功！");
            finish();
        }else {
            showToast(deleteCarResultBean.getMessage());
        }
    }

    @Override
    public void showAddressList(ShowAddressResultBean showAddressResultBean) {

    }

    @Override
    public void updateAddress(DeleteCarResultBean deleteCarResultBean) {
        if("000000".equals(deleteCarResultBean.getCode())){
            showToast("地址更新成功！");
            finish();
        }else {
            showToast(deleteCarResultBean.getMessage());
        }
    }

    @Override
    public void deleteAddress(DeleteCarResultBean deleteCarResultBean) {

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
        if(modifyAddressPresenter!=null){
            modifyAddressPresenter.detachView();
        }
    }
}
