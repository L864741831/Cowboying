package com.ibeef.cowboying.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ibeef.cowboying.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BeefHourseDialog extends AppCompatActivity {
    @Bind(R.id.i_see_id)
    TextView iSeeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beef_hourse_dialog);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.i_see_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.i_see_id:
               finish();
                break;
            default:
                break;
        }
    }
}
