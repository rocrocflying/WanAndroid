package application;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

import java.util.ConcurrentModificationException;

import network.RetrofitManager;
import utils.WParam;

import static utils.WParam.NIGHT_MODE;

public class WanApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitManager.getInstance().init();
        RetrofitManager.getInstance().initMeizhi();
        context = this;
        WParam.init();

        //根据app上次退出的状态来判断是否需要设置夜间模式,提前在SharedPreference中存了一个是否是夜间模式的boolean值
        boolean isNightMode = WParam.getBoolean(NIGHT_MODE);
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }


    }

    public static Context getAppContext() {
        return context;
    }
}
