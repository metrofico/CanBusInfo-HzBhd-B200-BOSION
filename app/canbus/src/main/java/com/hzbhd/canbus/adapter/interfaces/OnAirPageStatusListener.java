package com.hzbhd.canbus.adapter.interfaces;

/* loaded from: classes.dex */
public interface OnAirPageStatusListener {
    public static final int FRONT_ON_CREATE = 1;
    public static final int REAR_ON_CREATE = 5;

    void onStatusChange(int i);
}
