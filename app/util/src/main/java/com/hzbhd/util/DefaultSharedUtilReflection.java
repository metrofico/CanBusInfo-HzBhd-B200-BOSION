package com.hzbhd.util;

import android.util.Log;

import java.lang.reflect.Method;

public class DefaultSharedUtilReflection {
    public static int getInt(String key, int defaultValue) {
        try {
            Class<?> clazz = Class.forName("android.app.DefaultSharedUtil");
            // Buscar el método estático getInt(String, int)
            Object result = clazz.getDeclaredMethod("getInt", String.class, int.class).invoke(null, key, defaultValue); // null porque es método estático

            if (result instanceof Integer) {
                return (Integer) result;
            }
        } catch (Exception e) {
            Log.e("DefaultSharedUtilReflection.java", "Error al invocar DefaultSharedUtil.getInt: " + e.getMessage(), e);
        }
        return defaultValue; // fallback si falla
    }

    public static void setBool(String key, boolean value) {
        try {
            Class<?> clazz = Class.forName("android.app.DefaultSharedUtil");
            clazz.getDeclaredMethod("setBool", String.class, boolean.class).invoke(null, key, value);
        } catch (Exception e) {
            Log.e("DefaultSharedUtilReflection.java", "Error al invocar DefaultSharedUtil.setBool: " + e.getMessage(), e);
        }
    }

    public static void setStr(String key, String value) {
        try {
            Class<?> clazz = Class.forName("android.app.DefaultSharedUtil");
            clazz.getDeclaredMethod("setStr", String.class, String.class).invoke(null, key, value);
        } catch (Exception e) {
            Log.e("DefaultSharedUtilReflection.java", "Error al invocar DefaultSharedUtil.setStr: " + e.getMessage(), e);
        }
    }

    public static String getStr(String key, String defaultValue) {
        try {
            // Obtiene la clase de "DefaultSharedUtil"
            Class<?> clazz = Class.forName("android.app.DefaultSharedUtil");

            // Obtiene el método getStr de la clase DefaultSharedUtil
            Method method = clazz.getDeclaredMethod("getStr", String.class, String.class);

            // Invoca el método y obtiene el resultado
            return (String) method.invoke(null, key, defaultValue);
        } catch (Exception e) {
            // En caso de error, se imprime en log y devuelve el valor por defecto
            Log.e("DefaultSharedUtilReflection.java", "Error al invocar DefaultSharedUtil.getStr: " + e.getMessage(), e);
            return defaultValue;
        }
    }

    public static void setInt(String key, int value) {
        try {
            Class<?> clazz = Class.forName("android.app.DefaultSharedUtil");
            clazz.getDeclaredMethod("setInt", String.class, int.class).invoke(null, key, value);
        } catch (Exception e) {
            Log.e("DefaultSharedUtilReflection.java", "Error al invocar DefaultSharedUtil.setInt: " + e.getMessage(), e);
        }
    }

    public static boolean getBool(String key, boolean defaultValue) {
        try {
            Class<?> clazz = Class.forName("android.app.DefaultSharedUtil");
            Object result = clazz.getDeclaredMethod("getBool", String.class, boolean.class).invoke(null, key, defaultValue);
            if (result instanceof Boolean) {
                return (Boolean) result;
            }
        } catch (Exception e) {
            Log.e("DefaultSharedUtilReflection.java", "Error al invocar DefaultSharedUtil.getBool: " + e.getMessage(), e);
        }
        return defaultValue;
    }

    public static void commit() {
        try {
            Class<?> clazz = Class.forName("android.app.DefaultSharedUtil");
            // Buscar el método estático commit()
            clazz.getDeclaredMethod("commit").invoke(null); // null porque es método estático
        } catch (Exception e) {
            Log.e("DefaultSharedUtilReflection.java", "Error al invocar DefaultSharedUtil.commit: " + e.getMessage(), e);
        }
    }
}
