package com.hzbhd.canbus.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.List;

/* loaded from: classes2.dex */
public class SystemUtil {
    public static void printCanbusParameter() {
    }

    public static boolean isForeground(Context context, String str) throws SecurityException {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        if (runningTasks.isEmpty()) {
            return false;
        }
        return runningTasks.get(0).topActivity.getClassName().equals(str);
    }

    public static boolean isForeground(Context context, String[] strArr) throws SecurityException {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        if (runningTasks.isEmpty()) {
            return false;
        }
        ComponentName componentName = runningTasks.get(0).topActivity;
        for (String str : strArr) {
            if (componentName.getClassName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static void cleanLauncher(Context context) {
        try {
            Log.i("clearData", "Clear launcher3 data");
            clearAppData(context);
            Intent intent = new Intent();
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addCategory("android.intent.category.MONKEY");
            context.startActivity(intent);
        } catch (Exception e) {
            Log.i("clearData", "Exception e" + e.toString());
        }
    }

    public static void clearAppData(Context context) {
        try {
            Object activityManager = context.getSystemService(Context.ACTIVITY_SERVICE);

            Method method = ActivityManager.class.getMethod("clearApplicationUserData", String.class, android.os.UserHandle.class);
            method.invoke(activityManager, "com.android.launcher3", null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getTopActivityPackageName(Context context) {
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningAppProcesses().get(0);
        return (runningAppProcessInfo == null || runningAppProcessInfo.importance != 100) ? "" : runningAppProcessInfo.processName;
    }
}
