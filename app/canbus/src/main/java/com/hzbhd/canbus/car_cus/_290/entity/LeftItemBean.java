package com.hzbhd.canbus.car_cus._290.entity;

/* loaded from: classes2.dex */
public class LeftItemBean {
    private int iconRes;
    private boolean isSelected;
    private int titleRes;

    public LeftItemBean(int i, int i2, boolean z) {
        this.titleRes = i;
        this.iconRes = i2;
        this.isSelected = z;
    }

    public int getTitleRes() {
        return this.titleRes;
    }

    public void setTitleRes(int i) {
        this.titleRes = i;
    }

    public int getIconRes() {
        return this.iconRes;
    }

    public void setIconRes(int i) {
        this.iconRes = i;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }
}
