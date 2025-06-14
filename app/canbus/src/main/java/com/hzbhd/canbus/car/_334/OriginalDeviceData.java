package com.hzbhd.canbus.car._334;

import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import java.util.List;

/* loaded from: classes2.dex */
public class OriginalDeviceData {
    private final String[] bottomBtn;
    private final List<OriginalCarDevicePageUiSet.Item> list;
    private final String[] topBtn;

    public OriginalDeviceData(List<OriginalCarDevicePageUiSet.Item> list) {
        this(list, null, null);
    }

    public OriginalDeviceData(List<OriginalCarDevicePageUiSet.Item> list, String[] strArr) {
        this.list = list;
        this.topBtn = null;
        this.bottomBtn = strArr;
    }

    public OriginalDeviceData(List<OriginalCarDevicePageUiSet.Item> list, String[] strArr, String[] strArr2) {
        this.list = list;
        this.topBtn = strArr2;
        this.bottomBtn = strArr;
    }

    public List<OriginalCarDevicePageUiSet.Item> getItemList() {
        return this.list;
    }

    public String[] getBottomBtn() {
        return this.bottomBtn;
    }

    public String[] getTopBtn() {
        return this.topBtn;
    }
}
