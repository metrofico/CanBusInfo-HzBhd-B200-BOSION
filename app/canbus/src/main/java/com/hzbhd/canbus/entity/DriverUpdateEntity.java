package com.hzbhd.canbus.entity;

/* loaded from: classes2.dex */
public class DriverUpdateEntity {
    private int index;
    private int page;
    private String value;

    public DriverUpdateEntity(int i, int i2, String str) {
        this.page = i;
        this.index = i2;
        this.value = str;
    }

    public int getPage() {
        return this.page;
    }

    public int getIndex() {
        return this.index;
    }

    public DriverUpdateEntity setValue(String str) {
        this.value = str;
        return this;
    }

    public String getValue() {
        return this.value;
    }
}
