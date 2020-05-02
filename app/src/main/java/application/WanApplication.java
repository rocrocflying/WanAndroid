package application;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import network.RetrofitManager;

public class WanApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitManager.getInstance().init();
        RetrofitManager.getInstance().initMeizhi();

        //根据app上次退出的状态来判断是否需要设置夜间模式,提前在SharedPreference中存了一个是否是夜间模式的boolean值
//        boolean isNightMode = NightModeConfig.getInstance().getNightMode(getApplicationContext());
//        if (isNightMode) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        }


    }
}
