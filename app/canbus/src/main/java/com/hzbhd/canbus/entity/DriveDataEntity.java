package com.hzbhd.canbus.entity;

import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class DriveDataEntity {
    private String headTitleStrRes;
    private List<DriveDataItem> itemList;

    public DriveDataEntity(String str, DriveDataItem[] driveDataItemArr) {
        this.headTitleStrRes = str;
        this.itemList = Arrays.asList(driveDataItemArr);
    }

    public String getHeadTitleStrRes() {
        return this.headTitleStrRes;
    }

    public void setHeadTitleStrRes(String str) {
        this.headTitleStrRes = str;
    }

    public List<DriveDataItem> getItemList() {
        return this.itemList;
    }

    public void setItemList(List<DriveDataItem> list) {
        this.itemList = list;
    }
}
