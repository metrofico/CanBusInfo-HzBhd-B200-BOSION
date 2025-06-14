package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType423.kt */

/* loaded from: classes2.dex */
public final class CanType423 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "潍柴", "D50/U60V高配", "2018", "Hiworld", "WEICHAI", "D50/U60V", "2018", 423, 1, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "潍柴", "U60V中配", " ", "Hiworld", "WEICHAI", "U60V", "", 423, 2, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "潍柴", "U60V低配", " ", "Hiworld", "WEICHAI", "U60V", "", 423, 3, 0, 0, 1, 5, 1, 1, 1, " "));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
