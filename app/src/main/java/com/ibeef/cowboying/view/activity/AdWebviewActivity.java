package com.ibeef.cowboying.view.activity;

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

        WebSettings webSettings = webId.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setBlockNetworkImage(false);//解决图片不显示
        //启用地理定位
        webSettings.setGeolocationEnabled(true);
        //开启DomStorage缓存
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置允许访问文件数据
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        //缩放开关，设置此属性，仅支持双击缩放，不支持触摸缩放
        webSettings.setSupportZoom(true);
        //设置是否可缩放，会出现缩放工具（若为true则上面的设值也默认为true）
        webSettings.setBuiltInZoomControls(true);
        //隐藏缩放工具
        webSettings.setDisplayZoomControls(false);

        webId.loadUrl(url);

        info.setText(title);
    }

    @OnClick(R.id.back_id)
    public void onViewClicked() {
//        startActivity(new Intent(AdWebviewActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
//        startActivity(new Intent(AdWebviewActivity.this, MainActivity.class));
        finish();
    }
}
