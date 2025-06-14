package com.hzbhd.canbus.car_cus._290.entity;

/* loaded from: classes2.dex */
public class MediaItemBean {
    private int iconPressRes;
    private int iconReleaseRes;
    private int target;
    private int titleRes;

    public MediaItemBean(int i, int i2, int i3, int i4) {
        this.titleRes = i;
        this.iconPressRes = i2;
        this.iconReleaseRes = i3;
        this.target = i4;
    }

    public int getTitleRes() {
        return this.titleRes;
    }

    public int getIconPressRes() {
        return this.iconPressRes;
    }

    public int getIconReleaseRes() {
        return this.iconReleaseRes;
    }

    public int getTarget() {
        return this.target;
    }
}
