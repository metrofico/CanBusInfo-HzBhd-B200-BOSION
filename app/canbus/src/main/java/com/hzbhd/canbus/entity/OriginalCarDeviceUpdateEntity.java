package com.hzbhd.canbus.entity;

/* loaded from: classes2.dex */
public class OriginalCarDeviceUpdateEntity {
    private int index;
    private String value;

    public OriginalCarDeviceUpdateEntity(int i, String str) {
        this.value = str;
        this.index = i;
    }

    public String getValue() {
        return this.value;
    }

    public int getIndex() {
        return this.index;
    }
}
