package com.hzbhd.canbus.interfaces;

/* loaded from: classes2.dex */
public interface OnDriveDataPageStatusListener {
    public static final int ON_CREATE = -1;
    public static final int ON_DESTORY = -2;

    void onStatusChange(int i);
}
