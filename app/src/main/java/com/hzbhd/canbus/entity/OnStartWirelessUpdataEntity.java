package com.hzbhd.canbus.entity;

/* loaded from: classes2.dex */
public class OnStartWirelessUpdataEntity {
    private int position;
    private String value;

    public OnStartWirelessUpdataEntity(int i, String str) {
        this.position = i;
        this.value = str;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }
}
