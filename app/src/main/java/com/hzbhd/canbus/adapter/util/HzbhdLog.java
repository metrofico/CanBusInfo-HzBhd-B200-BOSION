package com.hzbhd.canbus.adapter.util;

import android.util.Log;

import com.hzbhd.commontools.utils.SystemPropertiesUtils;

/* loaded from: classes.dex */
public class HzbhdLog {
    private static String createTag() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String fileName = stackTrace[2].getFileName();
        int lineNumber = stackTrace[2].getLineNumber();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("(").append(fileName).append(":").append(lineNumber).append(")");
        return stringBuffer.toString();
    }

    public static void logWarn(String str, String str2) {
        boolean z = SystemPropertiesUtils.getInt("PrintLogFlag", 0) == 1;
        if (SystemConstant.LOG_WARM) {
            Log.w(createTag(), getThreadNameAndId(str2));
        } else if (z) {
            Log.w(str, getThreadNameAndId(str2));
        }
    }

    public static void logError(String str, String str2) {
        boolean z = SystemPropertiesUtils.getInt("PrintLogFlag", 0) == 1;
        if (SystemConstant.DEBUG) {
            Log.e(createTag(), getThreadNameAndId(str2));
        } else if (z) {
            Log.e(str, getThreadNameAndId(str2));
        }
    }

    public static void logInfo(String str, String str2) {
        boolean z = SystemPropertiesUtils.getInt("PrintLogFlag", 0) == 1;
        if (SystemConstant.LOG_INFO) {
            Log.i(createTag(), getThreadNameAndId(str2));
        } else if (z) {
            Log.i(str, getThreadNameAndId(str2));
        }
    }

    public static void w(String str, String str2) {
        boolean z = SystemPropertiesUtils.getInt("PrintLogFlag", 0) == 1;
        if (SystemConstant.LOG_WARM) {
            Log.w(createTag(), getThreadNameAndId(str2));
        } else if (z) {
            Log.w(str, getThreadNameAndId(str2));
        }
    }

    public static void e(String str, String str2) {
        boolean z = SystemPropertiesUtils.getInt("PrintLogFlag", 0) == 1;
        if (SystemConstant.LOG_ERROR) {
            Log.e(createTag(), getThreadNameAndId(str2));
        } else if (z) {
            Log.e(str, getThreadNameAndId(str2));
        }
    }

    public static void e(String str, String str2, Throwable th) {
        boolean z = SystemPropertiesUtils.getInt("PrintLogFlag", 0) == 1;
        if (SystemConstant.LOG_ERROR) {
            Log.e(createTag(), getThreadNameAndId(str2));
        } else if (z) {
            Log.e(str, getThreadNameAndId(str2));
        }
    }

    public static void i(String str, String str2) {
        boolean z = SystemPropertiesUtils.getInt("PrintLogFlag", 0) == 1;
        if (SystemConstant.LOG_INFO) {
            Log.i(createTag(), getThreadNameAndId(str2));
        } else if (z) {
            Log.i(str, getThreadNameAndId(str2));
        }
    }

    public static void d(String str, String str2) {
        boolean z = SystemPropertiesUtils.getInt("PrintLogFlag", 0) == 1;
        if (str.contains("Tuner")) {
            return;
        }
        if (str.contains("opengl")) {
            if (SystemPropertiesUtils.getInt("opengl", 0) == 1) {
                Log.d(str, getThreadNameAndId(str2));
            }
        } else if (SystemConstant.LOG_DEBUG) {
            Log.d(createTag(), getThreadNameAndId(str2));
        } else if (z) {
            Log.d(str, getThreadNameAndId(str2));
        }
    }

    public static void d(String str) {
        boolean z = SystemPropertiesUtils.getInt("PrintLogFlag", 0) == 1;
        if (SystemConstant.LOG_DEBUG) {
            Log.d(createTag(), getThreadNameAndId(str));
        } else if (z) {
            Log.d(createTag(), getThreadNameAndId(str));
        }
    }

    public static void v(String str, String str2) {
        boolean z = SystemPropertiesUtils.getInt("PrintLogFlag", 0) == 1;
        if (SystemConstant.LOG_VERBOSE) {
            Log.v(createTag(), getThreadNameAndId(str2));
        } else if (z) {
            Log.v(str, getThreadNameAndId(str2));
        }
    }

    public static String getThreadNameAndId(String str) {
        return "[" + Thread.currentThread().getName() + "," + Thread.currentThread().getId() + "] " + str;
    }
}
