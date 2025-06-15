package com.hzbhd.canbus.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.hzbhd.canbus.config.CanbusConfig;

/* loaded from: classes2.dex */
public class ActivityUtils {
    private static boolean temp_tempshow_10_000_ms = false;

    public static boolean isTemp_tempshow_10_000_ms() {
        return temp_tempshow_10_000_ms;
    }

    public static void setTemp_tempshow_10_000_ms(boolean z) {
        temp_tempshow_10_000_ms = z;
    }

    public static boolean isPrecameraActivity(Context context) {
        if (getTopActivityInfo(context) != null) {
            Log.d("mww", "getTopActivityInfo().getClassName()= " + getTopActivityInfo(context).getClassName());
            if ("com.hzbhd.misc.camera.PreCameraActivity".equals(getTopActivityInfo(context).getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAuxinActivity(Context context) {
        if (getTopActivityInfo(context) != null) {
            Log.d("mww", "getTopActivityInfo().getClassName()= " + getTopActivityInfo(context).getClassName());
            if ("com.hzbhd.misc.auxin.MainActivity".equals(getTopActivityInfo(context).getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static ComponentName getTopActivityInfo(Context context) {
        return ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningTasks(1).get(0).topActivity;
    }

    public static Intent newIntent(Context context, Class cls) {
        Intent intent = new Intent(context, (Class<?>) cls);
        intent.putExtra("temp_show_10_000_ms", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    public static void startCanbusMainActivity(Context context) {
        Class cls = com.hzbhd.canbus.activity.MainActivity.class;
        int canType = CanbusConfig.INSTANCE.getCanType();
        if (canType == 283) {
            //cls = MainActivity.class;
        } else if (canType == 291) {
            //cls = com.hzbhd.canbus.car_cus._291.MainActivity.class;
        } else {
            // cls = com.hzbhd.canbus.activity.MainActivity.class;
        }
        Intent intent = new Intent(context, (Class<?>) cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
