package com.ibeef.cowboying.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

/**
 * 我的合同详情
 *
 * @author Administrator
 */
public class MyContractDetailActivity extends AppCompatActivity {

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.action_new_question_tv)
    TextView actionNewQuestionTv;
    @Bind(R.id.web_html_id)
    WebView webHtmlId;
    @Bind(R.id.pdfview)
    com.joanzapata.pdfview.PDFView pdfview;
    private String fileName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contract_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        fileName = getIntent().getStringExtra("fileName");
        info.setText("常见问题");
        webHtmlId.setWebViewClient(new WebViewClient());
        webHtmlId.setWebChromeClient(new WebChromeClient());
        WebSettings settings = webHtmlId.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        webHtmlId.getSettings().setUseWideViewPort(true);
        webHtmlId.getSettings().setLoadWithOverviewMode(true);
        webHtmlId.loadUrl("http://pasture-contract.oss-cn-beijing.aliyuncs.com/contract/114540407984256.pdf?Expires=1542772214&OSSAccessKeyId=LTAIMwEKWL7OahCb&Signature=QT8W9f2StKmmOPZqt0u2ZipDF%2F4%3D");

    }


    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
    }
}
