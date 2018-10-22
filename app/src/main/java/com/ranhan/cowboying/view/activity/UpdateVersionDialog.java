package com.ranhan.cowboying.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.ranhan.cowboying.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_version_dialog);
        ButterKnife.bind(this);
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
