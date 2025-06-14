package com.hzbhd.canbus.adapter.util;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public final class DispUtility {
    public static Resources disabledDisplayDpiChange(Resources resources) {
        Configuration configuration = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= 23) {
            if (resources.getConfiguration().fontScale != 1.0f) {
                configuration.fontScale = 1.0f;
            }
            configuration.densityDpi = getDefaultDisplayDensity();
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        } else if (resources.getConfiguration().fontScale != 1.0f) {
            configuration.fontScale = 1.0f;
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
        return resources;
    }

    private static int getDefaultDisplayDensity() throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            Class<?> cls = Class.forName("android.view.WindowManagerGlobal");
            Method method = cls.getMethod("getWindowManagerService", new Class[0]);
            method.setAccessible(true);
            Object objInvoke = method.invoke(cls, new Object[0]);
            Method method2 = objInvoke.getClass().getMethod("getInitialDisplayDensity", Integer.TYPE);
            method2.setAccessible(true);
            return ((Integer) method2.invoke(objInvoke, 0)).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
