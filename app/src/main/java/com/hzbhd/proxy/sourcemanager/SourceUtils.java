package com.hzbhd.proxy.sourcemanager;

/* loaded from: classes2.dex */
public class SourceUtils {
    public static int getDispIdFromDispType(int i) {
        return i & 255;
    }

    public static int getDispListenerType(int i, int i2) {
        return i + (i2 << 8);
    }

    public static int getDispSource(int i, int i2) {
        return i + (i2 << 8);
    }

    public static int getTypeFromDispType(int i) {
        return i >> 8;
    }
}
