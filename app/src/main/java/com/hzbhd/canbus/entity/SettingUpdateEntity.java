package com.hzbhd.canbus.entity;

/* loaded from: classes2.dex */
public class SettingUpdateEntity<T> {
    private boolean enable;
    private boolean isValueStr;
    private int leftListIndex;
    private int progress;
    private int rightListIndex;
    private T value;

    public SettingUpdateEntity(int i, int i2) {
        this(i, i2, null);
    }

    public SettingUpdateEntity(int i, int i2, T t) {
        this.enable = true;
        this.leftListIndex = i;
        this.rightListIndex = i2;
        this.value = t;
    }

    public boolean isValueStr() {
        return this.isValueStr;
    }

    public SettingUpdateEntity setValueStr(boolean z) {
        this.isValueStr = z;
        return this;
    }

    public int getLeftListIndex() {
        return this.leftListIndex;
    }

    public int getRightListIndex() {
        return this.rightListIndex;
    }

    public T getValue() {
        return this.value;
    }

    public SettingUpdateEntity setValue(T t) {
        this.value = t;
        return this;
    }

    public int getProgress() {
        return this.progress;
    }

    public SettingUpdateEntity setProgress(int i) {
        this.progress = i;
        return this;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public SettingUpdateEntity setEnable(boolean z) {
        this.enable = z;
        return this;
    }
}
