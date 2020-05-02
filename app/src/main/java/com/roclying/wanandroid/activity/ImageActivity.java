package com.roclying.wanandroid.activity;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.roclying.wanandroid.R;

public class ImageActivity extends BaseActivity {

    private PhotoView photoView;
    private String url;

    @Override
    void initView() {
        photoView = findViewById(R.id.photo_view);
    }

    @Override
    void initData() {
        setTitile("预览");
        url = getIntent().getStringExtra("url");
        Glide.with(this).load(url).into(photoView);

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
        return R.layout.image_activity_layout;
    }
}
