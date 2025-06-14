package com.hzbhd.canbus.car_cus._283.bean;

/* loaded from: classes2.dex */
public class TextViewBean {
    private String text;
    private int textSize;

    public TextViewBean(String str, int i) {
        this.text = str;
        this.textSize = i;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public int getTextSize() {
        int i = this.textSize;
        if (i < 0) {
            return 0;
        }
        return i;
    }

    public void setTextSize(int i) {
        this.textSize = i;
    }
}
