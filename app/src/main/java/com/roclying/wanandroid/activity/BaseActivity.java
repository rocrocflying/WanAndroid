package com.roclying.wanandroid.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.roclying.wanandroid.R;

public abstract class BaseActivity extends AppCompatActivity {


    private String TAG = getClass().getSimpleName();

    private ImageView backIv;
    private TextView titleTv;
    private OnLeftBtnClickListener l;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);
        View baseView = LayoutInflater.from(this).inflate(R.layout.activity_base, null);
        FrameLayout contentView = baseView.findViewById(R.id.fl_content);
        getContentViews(baseView);
        LayoutInflater.from(this).inflate(getLayoutId(), contentView, true);
        setContentView(baseView);
        initView();
        initListener();
        initData();


    }


    abstract void initView();

    abstract void initData();

    abstract void initListener();

    abstract int getLayoutId();

    private void getContentViews(View contentView) {

        backIv = contentView.findViewById(R.id.iv_back);
        titleTv = contentView.findViewById(R.id.tv_title);

        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             l.onClick();
            }
        });
    }

    public void setTitile(String title) {

        titleTv.setText(title);
    }

    protected void showLongToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG);
    }

    protected void showShortToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT);

    }

    protected BaseActivity() {
        super();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    public void setOnLeftBtnClickListener(OnLeftBtnClickListener l) {
        this.l = l;
    }

    interface OnLeftBtnClickListener {
        void onClick();
    }
}
