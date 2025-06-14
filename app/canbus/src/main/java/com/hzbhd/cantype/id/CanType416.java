package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType416.kt */

/* loaded from: classes2.dex */
public final class CanType416 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "东风", "启辰T90", "默认", "Hiworld", "DFM", "Qichen T90", "Defualt", 416, 7, 0, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "东风", "启辰D60", "默认", "Hiworld", "DFM", "Qichen D60", "Defualt", 416, 8, 0, 0, 1, 5, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
