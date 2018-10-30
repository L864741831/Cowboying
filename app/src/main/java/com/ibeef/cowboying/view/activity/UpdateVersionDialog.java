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

    private String from,version;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_version_dialog);
        ButterKnife.bind(this);
        from=getIntent().getStringExtra("from");
        version=getIntent().getStringExtra("version");
        // 如果是强更，不展示关闭按钮
        if("1".equals(from)){
            imgCloseId.setVisibility(View.GONE);
        }else {
            imgCloseId.setVisibility(View.VISIBLE);
        }
        versionTxtId.setText(version+" 版本");
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
