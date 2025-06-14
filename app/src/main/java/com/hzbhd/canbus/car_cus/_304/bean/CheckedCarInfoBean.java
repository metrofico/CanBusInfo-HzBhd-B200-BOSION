package com.hzbhd.canbus.car_cus._304.bean;

/* loaded from: classes2.dex */
public class CheckedCarInfoBean {
    private boolean isChecked;
    private int textSize;
    private String title;

    public CheckedCarInfoBean(String str) {
        this.title = str;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public int getTextSize() {
        return this.textSize;
    }

    public void setTextSize(int i) {
        this.textSize = i;
    }
}
