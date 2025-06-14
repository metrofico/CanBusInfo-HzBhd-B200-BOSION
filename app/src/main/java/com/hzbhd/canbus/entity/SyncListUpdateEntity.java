package com.hzbhd.canbus.entity;

/* loaded from: classes2.dex */
public class SyncListUpdateEntity {
    private boolean enable;
    private int index;
    private String info;
    private int leftIconResId;
    private int rightIconResId;
    private boolean selected;

    public SyncListUpdateEntity(int i) {
        this.index = i;
    }

    public int getIndex() {
        return this.index;
    }

    public SyncListUpdateEntity setLeftIconResId(int i) {
        this.leftIconResId = i;
        return this;
    }

    public int getLeftIconResId() {
        return this.leftIconResId;
    }

    public SyncListUpdateEntity setInfo(String str) {
        this.info = str;
        return this;
    }

    public String getInfo() {
        return this.info;
    }

    public SyncListUpdateEntity setRightIconResId(int i) {
        this.rightIconResId = i;
        return this;
    }

    public int getRightIconResId() {
        return this.rightIconResId;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public SyncListUpdateEntity setEnable(boolean z) {
        this.enable = z;
        return this;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public SyncListUpdateEntity setSelected(boolean z) {
        this.selected = z;
        return this;
    }
}
