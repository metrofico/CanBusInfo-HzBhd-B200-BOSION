package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType431.kt */

/* loaded from: classes2.dex */
public final class CanType431 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "奔腾", "X80", "2017款", "Hiworld", "Besturn", "X80", "2017model", 431, 1, 9, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "红旗", "H5", "2019款", "Hiworld", "Red Flag", "H5", "2019model", 431, 2, 17, 0, 1, 5, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
