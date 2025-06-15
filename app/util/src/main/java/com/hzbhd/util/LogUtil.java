package com.hzbhd.util;

import android.util.Log;

/* loaded from: classes3.dex */
public class LogUtil {
    private static final int LEVEL_1 = 1;
    private static final int LEVEL_2 = 2;
    private static final int LEVEL_3 = 3;
    private static final int LEVEL_4 = 4;
    private static final int LEVEL_5 = 5;
    private static final int LEVEL_6 = 6;
    private static final int LEVEL_7 = 7;
    private static final int LEVEL_8 = 8;
    private static final int LEVEL_9 = 9;

    public static boolean log9() {
        return true;
    }

    private static String createTag() {
        return Thread.currentThread().getName();
    }

    private static String createMsgPre() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String fileName = stackTrace[2].getFileName();
        int lineNumber = stackTrace[2].getLineNumber();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("(").append(fileName).append(":").append(lineNumber).append(")");
        return stringBuffer.toString();
    }

    public static int getInt(String key, int defaultValue) {
        try {
            // Usar reflexión para invocar el método estático getInt(String, int) de SystemProperties
            Class<?> clazz = Class.forName("android.os.SystemProperties");
            Object result = clazz.getDeclaredMethod("getInt", String.class, int.class).invoke(null, key, defaultValue);

            if (result instanceof Integer) {
                return (Integer) result;
            }
        } catch (Exception e) {
            Log.e("LogUtil", "Error al invocar SystemProperties.getInt: " + e.getMessage(), e);
        }
        return defaultValue; // Fallback si falla
    }

    public static int getLogLevel() {
        return getInt("persist.log", 5);
    }

    public static boolean log0() {
        return getLogLevel() < 1;
    }

    public static boolean log1() {
        return getLogLevel() < 2;
    }

    public static boolean log2() {
        return getLogLevel() < 3;
    }

    public static boolean log3() {
        return getLogLevel() < 4;
    }

    public static boolean log4() {
        return getLogLevel() < 5;
    }

    public static boolean log5() {
        return getLogLevel() < 6;
    }

    public static boolean log6() {
        return getLogLevel() < 7;
    }

    public static boolean log7() {
        return getLogLevel() < 8;
    }

    public static boolean log8() {
        return getLogLevel() < 9;
    }

    public static void d(String str) {
        Log.d(createTag(), createMsgPre() + str);
    }

    public static void e(String str) {
        Log.e(createTag(), createMsgPre() + str);
    }

    public static void w(String str) {
        Log.w(createTag(), createMsgPre() + str);
    }

    public static void v(String str) {
        Log.v(createTag(), createMsgPre() + str);
    }

    public static void i(String str) {
        Log.i(createTag(), createMsgPre() + str);
    }
}
