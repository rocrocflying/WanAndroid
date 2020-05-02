package utils;

import android.content.Context;
import android.content.SharedPreferences;

import application.WanApplication;

public class WParam {


    private static SharedPreferences.Editor editor;
    private static SharedPreferences sp;

    public static final String NIGHT_MODE = "night_mode";


    public static void init() {
        sp = WanApplication.getAppContext().getSharedPreferences("wan_android_sp", Context.MODE_PRIVATE);
        editor = sp.edit();

    }

    public static void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static Boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }
}
