package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType104.kt */

/* loaded from: classes2.dex */
public final class CanType104 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("欣朴 ", "宝马", "E90", "默认", "Simple", "BMW", "E90", "Defualt", 104, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("宝谷", "宝马", "E90", "默认", "BAOGOOD", "BMW", "E90", "Defualt", 104, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("欧迪", "宝马", "E90", "默认", "Oudi", "BMW", "E90", "Defualt", 104, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("路征", "宝马", "E90", "默认", "Luzheng", "BMW", "E90", "Defualt", 104, 0, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
