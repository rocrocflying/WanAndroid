package com.roclying.wanandroid.activity;

import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.roclying.wanandroid.R;

public class WebDetailActivity extends BaseActivity {

    private WebView webView;
    private String title;
    private String link;


    @Override
    void initView() {
        webView = findViewById(R.id.web_view);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
    }

    @Override
    void initData() {
        title = getIntent().getStringExtra("title");
        link = getIntent().getStringExtra("link");
        if (!TextUtils.isEmpty(link)) {
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    webView.loadUrl(link);
                    return true;
                }
            });
        }
        webView.loadUrl(link);
        setTitile(title);

    }


    @Override
    void initListener() {
        setOnLeftBtnClickListener(new OnLeftBtnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });

    }

    @Override
    int getLayoutId() {
        return R.layout.activity_webdetail;
    }
}
