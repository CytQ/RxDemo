package com.mrli.example.rxdemo;

import android.app.Application;
import android.content.Context;

/**
 * Application class
 *
 * Created by lizhongquan on 16-7-26.
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getGlobalContext() {
        return context;
    }
}
