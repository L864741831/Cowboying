package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibeef.cowboying.R;

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
        webId.setWebViewClient(new WebViewClient());
        webId.setWebChromeClient(new WebChromeClient());
        WebSettings settings = webId.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        webId.getSettings().setUseWideViewPort(true);
        webId.getSettings().setLoadWithOverviewMode(true);
        webId.loadUrl("http://www.baidu.com");
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
    }
}
