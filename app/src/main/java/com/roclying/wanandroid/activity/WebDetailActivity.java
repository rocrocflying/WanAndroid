package com.roclying.wanandroid.activity;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.roclying.wanandroid.R;

public class WebDetailActivity extends BaseActivity {

    private WebView webView;
    private String title;
    private String link;
    private ProgressBar pg;


    @Override
    void initView() {
        webView = findViewById(R.id.web_view);
        pg = findViewById(R.id.pg);
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


        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    pg.setVisibility(View.GONE);
                } else {
                    pg.setVisibility(View.VISIBLE);
                    pg.setProgress(progress);
                }
            }
        });

    }

    @Override
    int getLayoutId() {
        return R.layout.activity_webdetail;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
