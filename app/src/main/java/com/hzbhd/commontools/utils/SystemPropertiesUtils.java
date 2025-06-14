package com.hzbhd.commontools.utils;

import android.util.Log;

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
