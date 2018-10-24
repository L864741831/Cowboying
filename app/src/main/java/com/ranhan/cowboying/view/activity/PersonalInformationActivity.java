package com.ranhan.cowboying.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ranhan.cowboying.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import rxfamily.view.BaseActivity;

/**
 * 个人信息界面
 */
public class PersonalInformationActivity extends BaseActivity {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.iv_icon)
    CircleImageView ivIcon;
    @Bind(R.id.see_head)
    RelativeLayout seeHead;
    @Bind(R.id.nickname_txt)
    TextView nicknameTxt;
    @Bind(R.id.nickname_rv)
    RelativeLayout nicknameRv;
    @Bind(R.id.bind_phone_stadus)
    TextView bindPhoneStadus;
    @Bind(R.id.bind_phone_txt)
    TextView bindPhoneTxt;
    @Bind(R.id.bind_phone_rv)
    RelativeLayout bindPhoneRv;
    @Bind(R.id.real_info_stadus)
    TextView realInfoStadus;
    @Bind(R.id.real_info_txt)
    TextView realInfoTxt;
    @Bind(R.id.modify_btn)
    TextView modifyBtn;
    @Bind(R.id.title_txt_id)
    TextView titleTxtId;
    @Bind(R.id.code_txt_id)
    TextView codeTxtId;
    @Bind(R.id.name_txt_id)
    TextView nameTxtId;
    @Bind(R.id.real_info_rv)
    RelativeLayout realInfoRv;
    @Bind(R.id.modify_pwd_rv)
    RelativeLayout modifyPwdRv;
    @Bind(R.id.et_write_id)
    EditText etWriteId;
    @Bind(R.id.et_write_id1)
    EditText etWriteId1;
    @Bind(R.id.cancle_img)
    ImageView cancleImg;
    @Bind(R.id.cancle_img1)
    ImageView cancleImg1;
    @Bind(R.id.modify_nickname_rv)
    RelativeLayout modifyNicknameRv;
    @Bind(R.id.show_realinfo_rv)
    RelativeLayout showRealinfoRv;
    @Bind(R.id.search2_id)
    LinearLayout search2Id;
    private boolean isNickname=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("个人信息");
    }

    @OnClick({R.id.back_id, R.id.iv_icon, R.id.see_head, R.id.nickname_rv, R.id.bind_phone_rv, R.id.real_info_rv, R.id.modify_pwd_rv,R.id.modify_btn,R.id.cancle_img,R.id.cancle_img1,R.id.modify_nickname_rv,R.id.show_realinfo_rv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.iv_icon:
                break;
            case R.id.see_head:
                break;
            case R.id.nickname_rv:
                etWriteId.setHint("请输入昵称");
                etWriteId.setText("");
                modifyNicknameRv.setVisibility(View.VISIBLE);
                titleTxtId.setText("昵称");
                isNickname=true;
                search2Id.setVisibility(View.GONE);
                break;
            case R.id.bind_phone_rv:
                break;
            case R.id.real_info_rv:
                if(true){
                    //已实名认证
                    showRealinfoRv.setVisibility(View.VISIBLE);
                    nameTxtId.setText("姓名：**斌");
                    codeTxtId.setText("身份证号：4201123330*****");
                }else {
                    //未实名认证
                    isNickname=false;
                    etWriteId.setHint("请输入真实姓名");
                    etWriteId1.setHint("请输入真实身份证号");
                    etWriteId.setText("");
                    etWriteId1.setText("");
                    modifyNicknameRv.setVisibility(View.VISIBLE);
                    titleTxtId.setText("实名认证");
                    search2Id.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.modify_pwd_rv:
                startActivity(ModifyPwdActivity.class);
                break;
            case R.id.modify_btn:
                if(isNickname){
                    // TODO: 2018/10/23 修改昵称
                }else {
                    // TODO: 2018/10/23 实名认证
                }
                modifyNicknameRv.setVisibility(View.GONE);
                break; 
            case R.id.cancle_img:
                etWriteId.setText("");
                break;
            case R.id.cancle_img1:
                etWriteId1.setText("");
                break;
            case R.id.modify_nickname_rv:
                modifyNicknameRv.setVisibility(View.GONE);
                break;
            case R.id.show_realinfo_rv:
                showRealinfoRv.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
}
