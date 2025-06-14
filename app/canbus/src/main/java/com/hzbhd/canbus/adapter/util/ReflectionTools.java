package com.hzbhd.canbus.adapter.util;

/* loaded from: classes.dex */
public class ReflectionTools {
    public static Boolean getFieldBoolean(Object obj, Class cls, String str) {
        try {
            return Boolean.valueOf(cls.getField(str).getBoolean(obj));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Integer getFieldInteger(Object obj, Class cls, String str) {
        try {
            return Integer.valueOf(cls.getField(str).getInt(obj));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
