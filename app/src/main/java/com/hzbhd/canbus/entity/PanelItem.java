package com.hzbhd.canbus.entity;

public final class PanelItem {
    private String titleStrRes;

    public PanelItem(String str) {
        this.titleStrRes = str;
    }

    public final String getTitleStrRes() {
        return this.titleStrRes;
    }

    public final void setTitleStrRes(String str) {
        this.titleStrRes = str;
    }
}
