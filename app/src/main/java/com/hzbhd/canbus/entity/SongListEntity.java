package com.hzbhd.canbus.entity;

/* loaded from: classes2.dex */
public class SongListEntity {
    private boolean enable = true;
    private boolean isSelected;
    private String title;

    public SongListEntity(String str) {
        this.title = str;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public SongListEntity setSelected(boolean z) {
        this.isSelected = z;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public SongListEntity setEnable(boolean z) {
        this.enable = z;
        return this;
    }
}
