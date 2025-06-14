package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType351.kt */

/* loaded from: classes2.dex */
public final class CanType351 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("欣朴 ", "宝马", "E46(BM-SS-01)", "默认", "Simple", "BMW", "E46(BM-SS-01)", "Default", 351, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("宝谷", "宝马", "E46(BM-SS-01)", "默认", "BAOGOOD", "BMW", "E46(BM-SS-01)", "Default", 351, 0, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
