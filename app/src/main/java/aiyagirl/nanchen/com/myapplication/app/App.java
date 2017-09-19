package aiyagirl.nanchen.com.myapplication.app;

import android.app.Application;


/**
 * Created by renyukai on 2017/6/13.
 */

public class App extends Application {

    private static App app;
    @Override
    public void onCreate() {
        super.onCreate();
        this.app=this;
    }

    public static App getApp(){
        return app;
    }
}
