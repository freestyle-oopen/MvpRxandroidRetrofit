package aiyagirl.nanchen.com.myapplication.utils;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import aiyagirl.nanchen.com.myapplication.app.App;


/**
 * Toastor
 */
public class Toastor {
    private Toastor(){}

    private static Handler sHandler = new Handler(Looper.getMainLooper());
    private static Context sContext = App.getApp();
    private static Toast sToast;

    public static void show(int resId) {
        show(sContext.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(int resId, int duration) {
        show(sContext.getResources().getText(resId), duration);
    }

    public static void show(CharSequence text) {
        show(text, Toast.LENGTH_SHORT);
    }

    public static void show(final CharSequence text, final int duration) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                if (sToast == null) {
                    sToast = Toast.makeText(sContext, text, duration);
                } else {
                    sToast.setText(text);
                }
                sToast.show();
            }
        });
    }

    public static void show(int resId, Object... args) {
        show(String.format(sContext.getResources().getString(resId), args),
                Toast.LENGTH_SHORT);
    }

    public static void show(String format, Object... args) {
        show(String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(int resId, int duration, Object... args) {
        show(String.format(sContext.getResources().getString(resId), args),
                duration);
    }

    public static void show(String format, int duration, Object... args) {
        show(String.format(format, args), duration);
    }
}
