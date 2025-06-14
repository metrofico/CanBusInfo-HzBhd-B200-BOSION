package com.hzbhd.canbus.car_cus._304.bean;

import android.view.View;

/* loaded from: classes2.dex */
public class MainPageEntity {
    private String title;
    private View view;

    public MainPageEntity(String str, View view) {
        this.title = str;
        this.view = view;
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
