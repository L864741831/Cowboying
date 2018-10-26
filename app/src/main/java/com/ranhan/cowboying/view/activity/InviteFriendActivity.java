package com.ranhan.cowboying.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ranhan.cowboying.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * @author Administrator
 * 全民推广主界面
 */
public class InviteFriendActivity extends BaseActivity {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.tv_invite_code)
    TextView tvInviteCode;
    @Bind(R.id.tv_invite_num)
    TextView tvInviteNum;
    @Bind(R.id.tv_invite_money)
    TextView tvInviteMoney;
    @Bind(R.id.btn_share_wechat)
    TextView btnShareWechat;
    @Bind(R.id.btn_share_wechat_quan)
    TextView btnShareWechatQuan;
    @Bind(R.id.btn_share_or_code)
    TextView btnShareOrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friend);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        actionNewQuestionTv.setVisibility(View.VISIBLE);
        actionNewQuestionTv.setText("邀请记录");
        info.setText("全民推广");
    }

    @OnClick({R.id.back_id, R.id.action_new_question_tv, R.id.btn_share_wechat, R.id.btn_share_wechat_quan, R.id.btn_share_or_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.action_new_question_tv:
                startActivity(InviteFriendHistoryActivity.class);
                break;
            case R.id.btn_share_wechat:
                break;
            case R.id.btn_share_wechat_quan:
                break;
            case R.id.btn_share_or_code:
                break;
            default:
                break;
        }
    }
}
