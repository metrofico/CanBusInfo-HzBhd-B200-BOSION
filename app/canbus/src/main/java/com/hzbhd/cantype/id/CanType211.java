package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType211.kt */

/* loaded from: classes2.dex */
public final class CanType211 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "福特", "锐界", "2011-2014(Gen1)", "Hiworld", "Ford", "Edge", "2011-2014(Gen1)", 211, 0, 0, 0, 1, 7, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "福特", "探险者", "2011-Present(Gen5)", "Hiworld", "Ford", "Explorer", "2011-Present(Gen5)", 211, 1, 0, 0, 1, 7, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
