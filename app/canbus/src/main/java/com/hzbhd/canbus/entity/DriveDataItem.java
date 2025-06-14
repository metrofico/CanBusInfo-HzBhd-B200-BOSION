package com.hzbhd.canbus.entity;

/* loaded from: classes2.dex */
public class DriveDataItem {
    private String defaultValue;
    private String titleStrRes;

    public DriveDataItem(String str, String str2) {
        this.titleStrRes = str;
        this.defaultValue = str2;
    }

    public String getTitleStrRes() {
        return this.titleStrRes;
    }

    public void setTitleStrRes(String str) {
        this.titleStrRes = str;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(String str) {
        this.defaultValue = str;
    }
}
