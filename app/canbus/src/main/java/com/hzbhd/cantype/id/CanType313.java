package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType313.kt */

/* loaded from: classes2.dex */
public final class CanType313 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("路征", "宝马", "E39", "默认", "Luzheng", "BMW", "E39", "Default", 313, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("路征", "宝马", "E46", "默认", "Luzheng", "BMW", "E46", "Default", 313, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("路征", "宝马", "E53", "默认", "Luzheng", "BMW", "E53", "Default", 313, 0, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
