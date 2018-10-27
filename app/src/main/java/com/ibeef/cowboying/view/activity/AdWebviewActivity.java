package com.ibeef.cowboying.view.activity;

import android.content.Intent;
import android.os.Bundle;
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
 * 启动广告页点击跳转webview
 */
public class AdWebviewActivity extends BaseActivity {

    @Bind(R.id.web_id)
    WebView webId;
    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    private String url,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_webview);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        url=getIntent().getStringExtra("url");
        title=getIntent().getStringExtra("title");
        //防止调用系统的浏览器
        webId.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webId.getSettings().setDefaultTextEncodingName("utf-8");
        //支持js
        webId.getSettings().setJavaScriptEnabled(true);
        WebSettings webSettings = webId.getSettings();
        webSettings.setSupportZoom(true);
        //启用地理定位
        webSettings.setGeolocationEnabled(true);
        //开启DomStorage缓存
        webSettings.setDomStorageEnabled(true);
        webId.loadUrl(url);
        info.setText(title);
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
        startActivity(new Intent(AdWebviewActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AdWebviewActivity.this, MainActivity.class));
        finish();
    }
}
