package com.hzbhd.canbus.entity;

import com.android.internal.util.ArrayUtils;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class TireUpdateEntity {
    public static final int NORMAL = 0;
    public static final int WARMING = 1;
    private List<String> list;
    private int tireStatus;
    private int whichTire;

    public TireUpdateEntity() {
    }

    public TireUpdateEntity(int i, int i2, String[] strArr) {
        this.whichTire = i;
        this.tireStatus = i2;
        if (ArrayUtils.isEmpty(strArr)) {
            return;
        }
        this.list = Arrays.asList(strArr);
    }

    public void setWhichTire(int i) {
        this.whichTire = i;
    }

    public void setTireStatus(int i) {
        this.tireStatus = i;
    }

    public int getWhichTire() {
        return this.whichTire;
    }

    public TireUpdateEntity setList(List<String> list) {
        this.list = list;
        return this;
    }

    public List<String> getList() {
        return this.list;
    }

    public int getTireStatus() {
        return this.tireStatus;
    }
}
