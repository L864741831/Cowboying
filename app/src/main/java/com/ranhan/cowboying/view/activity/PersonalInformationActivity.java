package com.ranhan.cowboying.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
    @Bind(R.id.real_info_rv)
    RelativeLayout realInfoRv;
    @Bind(R.id.modify_pwd_rv)
    RelativeLayout modifyPwdRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        info.setText("个人信息");
    }
    @OnClick({R.id.back_id, R.id.iv_icon, R.id.see_head, R.id.nickname_txt, R.id.nickname_rv, R.id.bind_phone_stadus, R.id.bind_phone_txt, R.id.bind_phone_rv, R.id.real_info_stadus, R.id.real_info_txt, R.id.real_info_rv, R.id.modify_pwd_rv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.iv_icon:
                break;
            case R.id.see_head:
                break;
            case R.id.nickname_txt:
                break;
            case R.id.nickname_rv:
                break;
            case R.id.bind_phone_stadus:
                break;
            case R.id.bind_phone_txt:
                break;
            case R.id.bind_phone_rv:
                break;
            case R.id.real_info_stadus:
                break;
            case R.id.real_info_txt:
                break;
            case R.id.real_info_rv:
                break;
            case R.id.modify_pwd_rv:
                break;
            default:
                break;
        }
    }
}
