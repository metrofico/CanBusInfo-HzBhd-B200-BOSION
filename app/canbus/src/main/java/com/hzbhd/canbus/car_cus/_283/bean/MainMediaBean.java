package com.hzbhd.canbus.car_cus._283.bean;

import android.content.ComponentName;

/* loaded from: classes2.dex */
public class MainMediaBean {
    private int image;
    private ComponentName mComponentName;
    private String text;

    public MainMediaBean(int i, String str, ComponentName componentName) {
        this.image = i;
        this.text = str;
        this.mComponentName = componentName;
    }

    public int getImage() {
        return this.image;
    }

    public void setImage(int i) {
        this.image = i;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public ComponentName getmComponentName() {
        return this.mComponentName;
    }

    public void setmComponentName(ComponentName componentName) {
        this.mComponentName = componentName;
    }
}
