package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType304.kt */

/* loaded from: classes2.dex */
public final class CanType304 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("内部协议", "YD", "π3", "低配 Low configuration", "Internal", "YD", "π3", "Low configuration Low configuration", 304, 0, 0, 0, 1, 0, 0, 0, 0, "null"), new CanTypeAllEntity("内部协议", "YD", "π3", "高配 High configuration", "Internal", "YD", "π3", "High configuration High configuration", 304, 1, 0, 0, 1, 0, 0, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
