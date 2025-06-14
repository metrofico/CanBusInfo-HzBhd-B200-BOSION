package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType387.kt */

/* loaded from: classes2.dex */
public final class CanType387 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "上汽", "大通MAXUS T60", " ", "Hiworld", "SAIC", "MAXUS T60", "", 387, 1, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "上汽", "名爵MG ZS", " ", "Hiworld", "SAIC", "MG ZS", "", 387, 2, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "上汽", "大通MAXUS T60 海外", " ", "Hiworld", "SAIC", "MAXUS T60", "", 387, 3, 0, 0, 1, 5, 1, 1, 1, " "));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
