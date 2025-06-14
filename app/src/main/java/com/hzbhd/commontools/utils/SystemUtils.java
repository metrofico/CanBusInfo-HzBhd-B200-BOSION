package com.hzbhd.commontools.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;
import java.util.List;

/* loaded from: classes2.dex */
public class SystemUtils {
    public static boolean isForeground(Context context, String str) {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        if (context == null || TextUtils.isEmpty(str) || (runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1)) == null || runningTasks.size() <= 0) {
            return false;
        }
        return str.equals(runningTasks.get(0).topActivity.getClassName());
    }
}
