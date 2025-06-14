package com.hzbhd.canbus.car_cus._273.entity;

/* loaded from: classes2.dex */
public class OnOffUpdateEntity {
    private int index;
    private boolean isSelect;

    public OnOffUpdateEntity(int i, boolean z) {
        this.index = i;
        this.isSelect = z;
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
    }

    public int getIndex() {
        return this.index;
    }

    public boolean isSelect() {
        return this.isSelect;
    }
}
