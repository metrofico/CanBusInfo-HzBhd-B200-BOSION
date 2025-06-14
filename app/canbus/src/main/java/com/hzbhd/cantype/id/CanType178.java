package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType178.kt */

/* loaded from: classes2.dex */
public final class CanType178 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("欣朴 ", "保时捷", "卡宴", "2009 GTS Porsche Design Edition III", "Simple", "Porsche", "Cayenne", "2009 GTS Porsche Design Edition III", 178, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("宝谷", "保时捷", "卡宴", "2009 GTS Porsche Design Edition III", "BAOGOOD", "Porsche", "Cayenne", "2009 GTS Porsche Design Edition III", 178, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("路征", "保时捷", "卡宴", "2009 GTS Porsche Design Edition III", "Luzheng", "Porsche", "Cayenne", "2009 GTS Porsche Design Edition III", 178, 0, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
