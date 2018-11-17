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

/**
 * 常见问题界面
 */
public class NormalQuestionActivity extends BaseActivity {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.web_html_id)
    WebView webHtmlId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_question);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        info.setText("常见问题");
        webHtmlId.setWebViewClient(new WebViewClient());
        webHtmlId.setWebChromeClient(new WebChromeClient());
        WebSettings settings = webHtmlId.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        webHtmlId.getSettings().setUseWideViewPort(true);
        webHtmlId.getSettings().setLoadWithOverviewMode(true);
        webHtmlId.loadUrl("http://www.baidu.com");
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
    }
}
