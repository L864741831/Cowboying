package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibeef.cowboying.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 绑定支付宝账号
 */
public class AddAccountZfbActivity extends BaseActivity {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.real_zfbaccout_id)
    EditText realZfbaccoutId;
    @Bind(R.id.real_name_id)
    EditText realNameId;
    @Bind(R.id.sure_btn)
    TextView sureBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_zfb);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("绑定支付宝");
    }

    @OnClick({R.id.back_id, R.id.sure_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.sure_btn:
                finish();
                break;
            default:
                break;
        }
    }
}
