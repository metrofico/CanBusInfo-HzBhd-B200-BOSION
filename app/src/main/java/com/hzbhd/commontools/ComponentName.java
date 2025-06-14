package com.hzbhd.commontools;

/* loaded from: classes2.dex */
public class ComponentName {
    private String className;
    private String packageName;

    public ComponentName(String str, String str2) {
        this.packageName = str;
        this.className = str2;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String str) {
        this.className = str;
    }
}
