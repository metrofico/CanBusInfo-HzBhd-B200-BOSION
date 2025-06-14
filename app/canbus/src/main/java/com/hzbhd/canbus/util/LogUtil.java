package com.hzbhd.canbus.util;

import android.util.Log;
import com.hzbhd.canbus.comm.Config;

/* loaded from: classes2.dex */
public class LogUtil {
    public static void showLog(String str) {
        Log.d(Config.LOG_TAG, str);
    }

    public static void showLog(String str, String str2) {
        Log.d(str, str2);
    }
}
