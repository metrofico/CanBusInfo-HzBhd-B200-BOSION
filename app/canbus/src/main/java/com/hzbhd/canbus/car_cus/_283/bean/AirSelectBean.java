package com.hzbhd.canbus.car_cus._283.bean;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;

/* loaded from: classes2.dex */
public class AirSelectBean {
    private Drawable drawable;
    private boolean isSelect;
    private String text;

    public AirSelectBean(String str, Drawable drawable) {
        this.text = str;
        this.drawable = drawable;
    }

    public int getSelectVisible() {
        return this.isSelect ? 0 : 8;
    }

    public int getDrawablwVisible() {
        return this.drawable == null ? 8 : 0;
    }

    public int getTextVisible() {
        return TextUtils.isEmpty(this.text) ? 8 : 0;
    }

    public int getViewVisible() {
        return (this.drawable == null || TextUtils.isEmpty(this.text)) ? 8 : 0;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public Drawable getDrawable() {
        return this.drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
    }
}
