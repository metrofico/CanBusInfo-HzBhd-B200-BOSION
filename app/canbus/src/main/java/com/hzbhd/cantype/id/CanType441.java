package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType441.kt */

/* loaded from: classes2.dex */
public final class CanType441 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("内部协议", "MD", "小康", "500", "Internal", "MD", "Xiaokang ", "500", 441, 0, 1, 0, 1, 1, 0, 0, 0, "null"), new CanTypeAllEntity("内部协议", "MD", "小康", "560", "Internal", "MD", "Xiaokang ", "560", 441, 0, 2, 0, 1, 1, 0, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
