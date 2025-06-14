package com.hzbhd.canbus.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreUtil {
    public static String sharePreference = "hzbhd";

    public static String getStringValue(Context context, String str, String str2) {
        String string = context.getSharedPreferences(sharePreference, 0).getString(str, null);
        return string == null ? str2 : string;
    }

    public static boolean setStringValue(Context context, String str, String str2) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(sharePreference, 0).edit();
        editorEdit.putString(str, str2);
        return editorEdit.commit();
    }

    public static boolean setBoolValue(Context context, String str, boolean z) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(sharePreference, 0).edit();
        editorEdit.putBoolean(str, z);
        return editorEdit.commit();
    }

    public static boolean getBoolValue(Context context, String str, boolean z) {
        return context.getSharedPreferences(sharePreference, 0).getBoolean(str, z);
    }

    public static boolean setIntValue(Context context, String str, int i) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(sharePreference, 0).edit();
        editorEdit.putInt(str, i);
        return editorEdit.commit();
    }

    public static int getIntValue(Context context, String str, int i) {
        return context.getSharedPreferences(sharePreference, 0).getInt(str, i);
    }

    public static boolean setFloatValue(Context context, String str, float f) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(sharePreference, 0).edit();
        editorEdit.putFloat(str, f);
        return editorEdit.commit();
    }

    public static float getFloatValue(Context context, String str, float f) {
        return context.getSharedPreferences(sharePreference, 0).getFloat(str, f);
    }
}
