package com.hzbhd.canbus.adapter.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

/* loaded from: classes.dex */
public class SystemUtils {
    private static final FileFilter CPU_FILTER = new FileFilter() { // from class: com.hzbhd.canbus.adapter.util.SystemUtils.1
        @Override // java.io.FileFilter
        public boolean accept(File file) {
            String name = file.getName();
            if (!name.startsWith("cpu")) {
                return false;
            }
            for (int i = 3; i < name.length(); i++) {
                if (name.charAt(i) < '0' || name.charAt(i) > '9') {
                    return false;
                }
            }
            return true;
        }
    };
    private static final String HOURS_12 = "12";
    private static final String HOURS_24 = "24";

    public static void set24Hour(Context context, boolean z) {
        Settings.System.putString(context.getContentResolver(), "time_12_24", z ? HOURS_24 : HOURS_12);
    }

    public static boolean isForeground(Context context, String str) {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        return context != null && !TextUtils.isEmpty(str) && (runningTasks = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningTasks(1)) != null && runningTasks.size() > 0 && str.equals(runningTasks.get(0).topActivity.getClassName());
    }

    public static boolean isNaviForeground(Context context, String str) {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        HzbhdLog.d("isNaviForeground", context + " , isNaviForeground , " + str);
        if (context != null && !TextUtils.isEmpty(str) && (runningTasks = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningTasks(1)) != null && runningTasks.size() > 0) {
            ComponentName componentName = runningTasks.get(0).topActivity;
            HzbhdLog.d("isNaviForeground", componentName.getPackageName() + " , isNaviForeground , " + str);
            return componentName.getPackageName().equals(str);
        }
        return false;
    }

    public static int getCPUCoreNumber(int i) {
        try {
            return new File("/sys/devices/system/cpu/").listFiles(CPU_FILTER).length;
        } catch (NullPointerException | SecurityException unused) {
            return i;
        }
    }

    public static void setAirPlaneMode(Context context, boolean z) {
        if (Build.VERSION.SDK_INT <= 16) {
            Settings.System.putInt(context.getContentResolver(), "airplane_mode_on", z ? 1 : 0);
        } else {
            Settings.Global.putInt(context.getContentResolver(), "airplane_mode_on", z ? 1 : 0);
        }
        Intent intent = new Intent("android.intent.action.AIRPLANE_MODE");
        intent.putExtra("state", z);
        context.sendBroadcast(intent);
    }

    public static boolean getAirPlaneMode(Context context) {
        return Build.VERSION.SDK_INT <= 16 ? Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0) == 1 : Settings.Global.getInt(context.getContentResolver(), "airplane_mode_on", 0) == 1;
    }
}
