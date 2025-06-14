package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType356.kt */

/* loaded from: classes2.dex */
public final class CanType356 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("欣朴 ", "奥迪", "A3 / A4 / TT", " ", "Simple", "Audi", "A3 / A4 / TT", " ", 356, 0, 0, 0, 1, 8, 1, 0, 0, "null"), new CanTypeAllEntity("宝谷", "奥迪", "A3 / A4 / TT", " ", "BAOGOOD", "Audi", "A3 / A4 / TT", " ", 356, 0, 0, 0, 1, 8, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
