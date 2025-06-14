package com.hzbhd.canbus.car_cus._283.bean;

import android.text.TextUtils;
import com.hzbhd.canbus.R;

/* loaded from: classes2.dex */
public class SettingDialogBean {
    private boolean isSelect;
    private String unit;
    private String value;

    public SettingDialogBean(String str) {
        this.value = str;
    }

    public SettingDialogBean(String str, String str2) {
        this.value = str;
        this.unit = str2;
    }

    public String getUnit() {
        return TextUtils.isEmpty(this.unit) ? "" : this.unit;
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public int getSelectVisible() {
        return this.isSelect ? 0 : 4;
    }

    public int getTextColor() {
        return this.isSelect ? R.color._283_color_blue : R.color.white;
    }
}
