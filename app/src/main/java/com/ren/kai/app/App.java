package com.ren.kai.app;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;


/**
 * Created by renyukai on 2017/6/13.
 */

public class App extends Application {

    private static App app;
    @Override
    public void onCreate() {
        super.onCreate();
        this.app=this;

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    public static App getApp(){
        return app;
    }
}
