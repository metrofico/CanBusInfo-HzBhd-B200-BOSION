package com.hzbhd.canbus.util;

/* loaded from: classes2.dex */
public class MathUtils {
    public static int fm_thousands_hundreads(String str) {
        int i = (int) (Float.parseFloat(str) * 100.0f);
        return (thousands(i) * 10) + hundreads(i);
    }

    public static int am_thousands_hundreads(String str) throws NumberFormatException {
        int i = Integer.parseInt(str);
        return (thousands(i) * 10) + hundreads(i);
    }

    public static int am_tens_units(String str) {
        int i = (int) Float.parseFloat(str);
        return (tens(i) * 10) + units(i);
    }

    public static int tens_units(String str) {
        int i = (int) (Float.parseFloat(str) * 100.0f);
        return (tens(i) * 10) + units(i);
    }

    public static int thousands(int i) {
        return (i / 1000) % 10;
    }

    public static int hundreads(int i) {
        return (i / 100) % 10;
    }

    public static int tens(int i) {
        return (i / 10) % 10;
    }

    public static int units(int i) {
        return i % 10;
    }
}
