package com.azazellj.weatherforecast;

import android.app.Application;
import android.content.Context;

/**
 * Created by azazellj on 27.05.16.
 */
public class App extends Application {
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return appContext;
    }
}

