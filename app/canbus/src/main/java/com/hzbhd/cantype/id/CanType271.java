package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType271.kt */

/* loaded from: classes2.dex */
public final class CanType271 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "TATA", "NEXON", "默认", "Hiworld", "TATA", "NEXON", "Defualt", 271, 1, 0, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "TATA", "HEXA", "默认", "Hiworld", "TATA", "HEXA", "Defualt", 271, 0, 0, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "TATA", "Tiago", "默认", "Hiworld", "TATA", "Tiago", "Defualt", 271, 1, 0, 0, 1, 5, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
