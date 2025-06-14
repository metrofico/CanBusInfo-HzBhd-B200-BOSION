package com.hzbhd.canbus.entity;

/* loaded from: classes2.dex */
public class CanDiffIdEntity {
    private int canDiffId;
    private String content;
    private int eachCanId;

    public CanDiffIdEntity(int i, int i2, String str) {
        this.canDiffId = i;
        this.content = str;
        this.eachCanId = i2;
    }

    public int getCanDiffId() {
        return this.canDiffId;
    }

    public void setCanDiffId(int i) {
        this.canDiffId = i;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public int getEachCanId() {
        return this.eachCanId;
    }

    public void setEachCanId(int i) {
        this.eachCanId = i;
    }
}
