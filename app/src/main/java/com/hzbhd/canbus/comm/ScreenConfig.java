package com.hzbhd.canbus.comm;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

/* loaded from: classes2.dex */
public class ScreenConfig {
    public static int screenHeight;
    public static int screenWidth;

    public static void initScreenConfig(Application application) {
        try {
            WindowManager windowManager = (WindowManager) application.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            screenWidth = displayMetrics.widthPixels;
            screenHeight = displayMetrics.heightPixels;
        } catch (Exception e) {
            Log.e("ScreenConfig", e.toString());
        }
    }
}
