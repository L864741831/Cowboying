package com.ranhan.cowboying.view.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ranhan.cowboying.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

public class ContactUsActivity extends BaseActivity {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.web_id)
    WebView webId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        info.setText("联系我们");
        webId.loadUrl("https://www.baidu.com");
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
    }
}
