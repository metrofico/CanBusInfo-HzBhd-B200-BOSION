package com.hzbhd.canbus.entity;

import android.view.View;

/* loaded from: classes2.dex */
public class ParkSettingEntity {
    private String title;
    private int type;
    private View view;

    public ParkSettingEntity(String str, View view, int i) {
        this.title = str;
        this.view = view;
        this.type = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public View getView() {
        return this.view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
