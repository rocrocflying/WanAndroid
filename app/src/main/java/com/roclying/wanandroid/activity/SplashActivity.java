package com.roclying.wanandroid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.roclying.wanandroid.R;

public class SplashActivity extends Activity {

    private RelativeLayout splashLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashLayout = findViewById(R.id.rl_splash);
        AlphaAnimation animation = new AlphaAnimation(0.4f, 1.0f);
        animation.setDuration(2000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                startActivity(new Intent(SplashActivity.this, MainActivity.class));

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        splashLayout.setAnimation(animation);
    }
}
