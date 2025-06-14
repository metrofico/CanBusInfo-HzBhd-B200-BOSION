package com.hzbhd.canbus.entity;

/* loaded from: classes2.dex */
public class WarningEntity {
    private String content;
    private int level;

    public WarningEntity(String str) {
        this.content = str;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }
}
