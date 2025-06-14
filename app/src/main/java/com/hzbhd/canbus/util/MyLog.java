package com.hzbhd.canbus.util;

import android.util.Log;

/* loaded from: classes2.dex */
public class MyLog {
    private static String OoO = "ON";

    public static void turnOn() {
        OoO = "ON";
    }

    public static void turnOFF() {
        OoO = "OFF";
    }

    public static void v(String str, String str2) {
        if (OoO.equals("ON")) {
            Log.v("<OoO> " + str, "————————>" + str2);
        }
    }

    public static void d(String str, String str2) {
        if (OoO.equals("ON")) {
            Log.d("<OoO> " + str, "————————>" + str2);
        }
    }

    public static void i(String str, String str2) {
        if (OoO.equals("ON")) {
            Log.i("<OoO> " + str, "————————>" + str2);
        }
    }

    public static void w(String str, String str2) {
        if (OoO.equals("ON")) {
            Log.w("<OoO> " + str, "————————>" + str2);
        }
    }

    public static void e(String str, String str2) {
        if (OoO.equals("ON")) {
            Log.e("<OoO> " + str, "————————>" + str2);
        }
    }

    public static void s(String str) {
        if (OoO.equals("ON")) {
            System.out.println("<OoO> ————————>" + str);
        }
    }

    public static void temporaryTracking(String str) {
        if (OoO.equals("ON")) {
            Log.e("<OoO> TRACK", "————————>" + str);
        }
    }
}
