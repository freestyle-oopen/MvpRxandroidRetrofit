package com.ren.kai.app;

import android.app.Activity;
import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import java.util.List;



public class App extends Application {

    private static App app;
    private List<Activity> activities;

    @Override
    public void onCreate() {
        super.onCreate();
        this.app = this;

        //监听所有activity
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacksImpl(activities));
        initLeakCanary();


    }


    /**
     * 初始化LeakCanary
     */
    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }


    /**
     * 一鍵退出軟件
     */
    public void exitAPP() {
        if(activities!=null){
            synchronized (activities){
                for (Activity activity :activities){
                    activity.finish();
                }
            }
        }
        System.exit(0);
    }

    /**
     * 退出到指定页面
     * @param activity
     */
    public void removeAllExclude(Activity activity){
        if(activities!=null){
            synchronized (activities){
                for (Activity act:activities){
                    if(act!=activity){
                        act.finish();
                    }
                }
            }
        }
    }
    public static App getApp() {
        return app;
    }
}
