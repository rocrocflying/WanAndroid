package com.roclying.wanandroid.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.roclying.wanandroid.R;

import static utils.Constant.WANANDROID_URL;

public class AboutActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout versionCheckLayout;
    private RelativeLayout websiteLayout;
    private RelativeLayout sourceUrlLayout;


    @Override
    void initView() {
        versionCheckLayout = findViewById(R.id.rl_version_check);
        websiteLayout = findViewById(R.id.rl_website);
        sourceUrlLayout = findViewById(R.id.rl_source_url);

    }

    @Override
    void initData() {

    }

    @Override
    void initListener() {

        versionCheckLayout.setOnClickListener(this);
        websiteLayout.setOnClickListener(this);
        sourceUrlLayout.setOnClickListener(this);
    }

    @Override
    int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rl_version_check: {
                break;
            }
            case R.id.rl_website: {
                Intent intent = new Intent(this, WebDetailActivity.class);
                intent.putExtra("link", WANANDROID_URL);
                startActivity(intent);
                break;
            }
            case R.id.rl_source_url: {
                break;
            }
        }

    }
}
