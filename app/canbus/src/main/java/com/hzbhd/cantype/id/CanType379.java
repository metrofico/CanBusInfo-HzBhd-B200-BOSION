package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType379.kt */

/* loaded from: classes2.dex */
public final class CanType379 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "福特", "F150", " ", "Hiworld", "Ford", "F150", "", 379, 0, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "福特", "F250", " ", "Hiworld", "Ford", "F250", "", 379, 0, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "福特", "F350", " ", "Hiworld", "Ford", "F350", "", 379, 0, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "福特", "F450", " ", "Hiworld", "Ford", "F450", "", 379, 0, 0, 0, 1, 5, 1, 1, 1, " "));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
