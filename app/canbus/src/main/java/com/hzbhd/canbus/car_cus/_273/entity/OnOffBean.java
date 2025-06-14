package com.hzbhd.canbus.car_cus._273.entity;

/* loaded from: classes2.dex */
public class OnOffBean {
    private boolean clickable = true;
    private int iconNoSelectRes;
    private int iconSelectRes;
    private boolean isSelected;
    private int titleRes;

    public OnOffBean(int i, int i2, int i3) {
        this.titleRes = i;
        this.iconSelectRes = i2;
        this.iconNoSelectRes = i3;
    }

    public int getTitleRes() {
        return this.titleRes;
    }

    public void setTitleRes(int i) {
        this.titleRes = i;
    }

    public int getIconSelectRes() {
        return this.iconSelectRes;
    }

    public void setIconSelectRes(int i) {
        this.iconSelectRes = i;
    }

    public int getIconNoSelectRes() {
        return this.iconNoSelectRes;
    }

    public void setIconNoSelectRes(int i) {
        this.iconNoSelectRes = i;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public boolean isClickable() {
        return this.clickable;
    }

    public void setClickable(boolean z) {
        this.clickable = z;
    }
}
