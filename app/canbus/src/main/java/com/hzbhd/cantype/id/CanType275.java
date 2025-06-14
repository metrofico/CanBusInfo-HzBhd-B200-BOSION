package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType275.kt */

/* loaded from: classes2.dex */
public final class CanType275 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "众泰", "T600", "默认", "Hiworld", "ZOTYE", "T600", "Defualt", 275, 0, 1, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "众泰", "X5", "默认", "Hiworld", "ZOTYE", "X5", "Defualt", 275, 1, 2, 0, 1, 5, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
