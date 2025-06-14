package com.hzbhd.canbus.adapter.util;

import android.content.Context;
import android.content.res.Configuration;
import android.provider.Settings;
import android.util.Log;

/* loaded from: classes.dex */
public class ScreenLogic {
    static String TAG = "ScreenLogic";
    static int orientation = -1;

    public static boolean isRotateScreen() {
        return false;
    }

    public static boolean isRotateScreenByHand() {
        return false;
    }

    public static boolean isRotateScreenOrRotateScreenByHand() {
        return isRotateScreen() || isRotateScreenByHand();
    }

    public static boolean isLandUIAndPortUI(String str) {
        return str.contains("IKWE0101-PE5");
    }

    public static boolean isScreenOreitaionChanged(Configuration configuration) {
        if (orientation != configuration.orientation) {
            orientation = configuration.orientation;
            return true;
        }
        Log.d(TAG, "isScreenOreitaionChanged  " + configuration.orientation);
        return false;
    }

    public static void setOrientation(int i) {
        orientation = i;
    }

    public static int getScreenOrientation(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "user_rotation", -1);
    }

    public static boolean isLandScreen(Context context) {
        int screenOrientation = getScreenOrientation(context);
        return screenOrientation != 1 && screenOrientation != 3;
    }

    public static boolean isPortScreen(Context context) {
        int screenOrientation = getScreenOrientation(context);
        return screenOrientation == 1 || screenOrientation == 3;
    }
}
