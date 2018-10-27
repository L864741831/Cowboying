package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.ibeef.cowboying.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 版本更新dialog
 */
public class UpdateVersionDialog extends AppCompatActivity {

    @Bind(R.id.img_close_id)
    ImageView imgCloseId;
    @Bind(R.id.sure_id)
    ImageView sureId;
    @Bind(R.id.version_txt_id)
    TextView versionTxtId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_version_dialog);
        ButterKnife.bind(this);
        // TODO: 2018/10/27  如果是强更，不展示关闭按钮
        versionTxtId.setText("1.0.0 版本");
        if(true){
            imgCloseId.setVisibility(View.GONE);
        }else {
            imgCloseId.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.img_close_id, R.id.sure_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_close_id:
                finish();
                break;
            case R.id.sure_id:
                break;
            default:
                break;
        }
    }
}
