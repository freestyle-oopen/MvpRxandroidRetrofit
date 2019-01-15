package com.ren.kai.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

public class ActivityLifecycleCallbacksImpl implements Application.ActivityLifecycleCallbacks {

    private List<Activity> activities;
    public ActivityLifecycleCallbacksImpl(List<Activity> activities){
        super();
        this.activities=activities;
    }
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
       addActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        removeActivity(activity);
    }

    /**
     * 添加一个activity
     */
    private void addActivity(Activity activity){
        if(activities==null){
            activities=new LinkedList<>();
        }
        if(!activities.contains(activity)){
            synchronized (activities) {
                activities.add(activity);
            }
        }
    }

    /**
     * 移除一个activity
     */
    private void removeActivity(Activity activity){
        if(activities.contains(activity)){
            synchronized (activities) {
                activities.remove(activity);
            }
        }
    }
}
