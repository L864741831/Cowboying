package com.ibeef.cowboying.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibeef.cowboying.R;
import com.ibeef.cowboying.base.MyContractBase;
import com.ibeef.cowboying.bean.MyContractListBean;
import com.ibeef.cowboying.bean.MyContractURLBean;
import com.ibeef.cowboying.bean.MyDiscountCouponListBean;
import com.ibeef.cowboying.config.HawkKey;
import com.ibeef.cowboying.presenter.MyContractPresenter;
import com.ibeef.cowboying.utils.SDCardUtil;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxfamily.view.BaseActivity;

/**
 * 我的合同详情
 *
 * @author Administrator
 */
public class MyContractDetailActivity extends BaseActivity implements  MyContractBase.IView{

    @Bind(R.id.back_id)
    ImageView backId;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.web_html_id)
    WebView mWebView;
    @Bind(R.id.loading_layout)
    RelativeLayout loadingLayout;
    private String fileName,type,token;
    private String  pdfHtml = "file:///android_asset/index.html";
    public static final int LOAD_JAVASCRIPT = 2;
    public static final int RESULT_JAVASCRIPT = 1;
    private MyContractPresenter myContractPresenter;
    private MyContractURLBean myContractURLBean;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contract_detail);
        ButterKnife.bind(this);
        init();
    }
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==2){
                String javaScript = "javascript: showPdf('"+ myContractURLBean.getBizData() +"')";
                mWebView.loadUrl(javaScript);
            }else  if(msg.what==1){
                loadingLayout.setVisibility(View.GONE);
                mWebView.setVisibility(View.VISIBLE);
            }

        }
    };
    private void init() {
        token= Hawk.get(HawkKey.TOKEN);
        fileName = getIntent().getStringExtra("fileName");
        type = getIntent().getStringExtra("type");
        if(SDCardUtil.isNullOrEmpty(type)){
            info.setText("养牛合同");
        }else if("1".equals(type)){
            info.setText("养牛合同");
        } else if("2".equals(type)){
            info.setText("拼牛合同");
        }

        WebSettings webSettings = mWebView.getSettings();

        webSettings.setLoadsImagesAutomatically(true);
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

        mWebView.setWebViewClient(new WebViewClient());

        myContractPresenter=new MyContractPresenter(this);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("Authorization",token);
        reqData.put("version",getVersionCodes());
        myContractPresenter.getMyContrantURL(reqData,type,fileName);
    }


    @OnClick(R.id.back_id)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
        mWebView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void getMyContrantList(MyContractListBean myContractListBean) {

    }

    @Override
    public void getMyContrantURL(MyContractURLBean myContractURLBean) {
        if("000000".equals(myContractURLBean.getCode())){
            this.myContractURLBean=myContractURLBean;
            mWebView.addJavascriptInterface(new JsObject(this, myContractURLBean.getBizData()), "client");
            mWebView.loadUrl(pdfHtml);
            handler.sendEmptyMessage(LOAD_JAVASCRIPT);
        }

    }

    @Override
    public void getMyDiscountCouponList(MyDiscountCouponListBean myDiscountCouponListBean) {

    }


    class JsObject {
        Activity mActivity;
        String url ;
        public JsObject(Activity activity,String url) {
            mActivity = activity;
            this.url= url;
        }
        @JavascriptInterface
        public String test() {
            return this.url;
        }

        @JavascriptInterface
        public String dismissProgress() {
            handler.sendEmptyMessage(RESULT_JAVASCRIPT);
            return this.url;
        }
        @JavascriptInterface
        public String alert(String url) {
           showToast("执行了"+url);
            return this.url;
        }
    }
}
