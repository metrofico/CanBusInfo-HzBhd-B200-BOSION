package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType346.kt */

/* loaded from: classes2.dex */
public final class CanType346 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("睿志诚", "比速", "M3", "默认", "Raise", "BISU", "M3", "Default", 346, 1, 1, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "比速", "T3", "默认", "Raise", "BISU", "T3", "Default", 346, 2, 2, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "比速", "T5（中低配）LOW/MID", "默认", "Raise", "BISU", "T5（Medium to low configuration）LOW/MID", "Default", 346, 3, 3, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "比速", "T5（高配）HIGH", "默认", "Raise", "BISU", "T5（High configuration）HIGH", "Default", 346, 4, 4, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
