package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType135.kt */

/* loaded from: classes2.dex */
public final class CanType135 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "海马", "S7", "2013-Present", "Hiworld", "Haima", "S7 ", "2013-Present", 135, 0, 0, 0, 1, 11, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "海马", "M3", "2014-Present", "Hiworld", "Haima", "M3 ", "2014-Present", 135, 0, 0, 0, 1, 11, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "海马", "Family", "2010-2013(Gen3)", "Hiworld", "Haima", "Family ", "2010-2013(Gen3)", 135, 0, 0, 0, 1, 11, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
