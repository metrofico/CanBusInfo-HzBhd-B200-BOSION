package com.hzbhd.commontools.utils;

import android.annotation.SuppressLint;
import android.util.Log;

@SuppressLint({"PrivateApi", "DiscouragedPrivateApi"})
public class SystemPropertiesUtils {
    private static final String TAG = "SystemUtils";

    // Método para obtener un String usando reflexión
    public static String get(String key) {
        try {
            // Usar reflexión para invocar el método estático get(String) de SystemProperties
            Class<?> clazz = Class.forName("android.os.SystemProperties");
            Object result = clazz.getDeclaredMethod("get", String.class).invoke(null, key);

            if (result instanceof String) {
                return (String) result;
            }
        } catch (Exception e) {
            Log.e(TAG, "Error al invocar SystemProperties.get: " + e.getMessage(), e);
        }
        return ""; // Fallback si falla
    }

    public static void set(String key, String value) {
        try {
            Class<?> clazz = Class.forName("android.os.SystemProperties");
            clazz.getDeclaredMethod("set", String.class, String.class).invoke(null, key, value);
        } catch (Exception e) {
            Log.e(TAG, "Error al invocar SystemProperties.set: " + e.getMessage(), e);
        }
    }

    // Método para obtener un int usando reflexión
    public static int getInt(String key, int defaultValue) {
        try {
            // Usar reflexión para invocar el método estático getInt(String, int) de SystemProperties
            Class<?> clazz = Class.forName("android.os.SystemProperties");
            Object result = clazz.getDeclaredMethod("getInt", String.class, int.class)
                    .invoke(null, key, defaultValue);

            if (result instanceof Integer) {
                return (Integer) result;
            }
        } catch (Exception e) {
            Log.e(TAG, "Error al invocar SystemProperties.getInt: " + e.getMessage(), e);
        }
        return defaultValue; // Fallback si falla
    }
}
