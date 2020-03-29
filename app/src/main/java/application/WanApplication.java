package application;

import android.app.Application;

import network.RetrofitManager;

public class WanApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitManager.getInstance().init();
    }
}
