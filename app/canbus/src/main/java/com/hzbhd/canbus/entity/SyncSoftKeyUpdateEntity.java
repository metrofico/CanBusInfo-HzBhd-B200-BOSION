package com.hzbhd.canbus.entity;

/* loaded from: classes2.dex */
public class SyncSoftKeyUpdateEntity {
    private int imageResId;
    private int index;
    private boolean isSelected;
    private boolean isVisible = true;
    private String name;

    public SyncSoftKeyUpdateEntity(int i) {
        this.index = i;
    }

    public int getIndex() {
        return this.index;
    }

    public String getName() {
        return this.name;
    }

    public SyncSoftKeyUpdateEntity setName(String str) {
        this.name = str;
        return this;
    }

    public int getImageResId() {
        return this.imageResId;
    }

    public SyncSoftKeyUpdateEntity setImageResId(int i) {
        this.imageResId = i;
        return this;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public SyncSoftKeyUpdateEntity setVisible(boolean z) {
        this.isVisible = z;
        return this;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public SyncSoftKeyUpdateEntity setSelected(boolean z) {
        this.isSelected = z;
        return this;
    }
}
