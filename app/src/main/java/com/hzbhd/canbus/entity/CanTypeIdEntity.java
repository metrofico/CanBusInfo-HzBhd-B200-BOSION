package com.hzbhd.canbus.entity;

/* loaded from: classes2.dex */
public class CanTypeIdEntity {
    private int id;
    private boolean selected;

    public CanTypeIdEntity(int i, boolean z) {
        this.id = i;
        this.selected = z;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }
}
