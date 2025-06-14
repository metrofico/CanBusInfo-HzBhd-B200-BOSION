package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType452.kt */

/* loaded from: classes2.dex */
public final class CanType452 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("网用", "豪华品牌", "奔驰", "通用", "Network use", "luxury brand", "Benz", "be current", 452, 0, 1, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("网用", "豪华品牌", "宝马", "通用", "Network use", "luxury brand", "BMW", "be current", 452, 0, 2, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("网用", "豪华品牌", "雷克萨斯", "通用", "Network use", "luxury brand", "Lexus", "be current", 452, 0, 3, 0, 1, 5, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
