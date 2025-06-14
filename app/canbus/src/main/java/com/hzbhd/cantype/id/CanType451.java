package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType451.kt */

/* loaded from: classes2.dex */
public final class CanType451 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("网用", "豪华品牌", "奥迪（A4/Q5）专用", "高配", "Network use", "luxury brand", "Audi（A4/Q5）", "High configuration", 451, 0, 0, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("网用", "豪华品牌", "奥迪（A4/Q5）专用", "低配", "Network use", "luxury brand", "Audi（A4/Q5）", "Low configuration", 451, 0, 1, 0, 1, 5, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
