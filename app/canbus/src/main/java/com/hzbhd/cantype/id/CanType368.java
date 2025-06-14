package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType368.kt */

/* loaded from: classes2.dex */
public final class CanType368 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "上汽", "荣威550", "2011-2015", "Hiworld", "SAIC", "550", "2011-2015", 368, 0, 0, 0, 1, 6, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "上汽", "名爵 6", "2011-2015", "Hiworld", "SAIC", "6", "2011-2015", 368, 0, 0, 0, 1, 6, 1, 1, 1, " "));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
