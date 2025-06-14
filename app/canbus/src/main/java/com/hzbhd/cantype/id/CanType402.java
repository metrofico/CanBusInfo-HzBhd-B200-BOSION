package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType402.kt */

/* loaded from: classes2.dex */
public final class CanType402 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "北汽", "幻速 S3L", " ", "Hiworld", "BAIC", "S3L", "", 402, 8, 0, 38400, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "北汽", "绅宝 X35", "2016", "Hiworld", "BAIC", "X35", "2016", 402, 5, 0, 38400, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "北汽", "M50F", " ", "Hiworld", "BAIC", "M50F", "", 402, 6, 0, 38400, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "北汽", "绅宝 X35", "2018", "Hiworld", "BAIC", "X35", "2018", 402, 7, 0, 38400, 1, 5, 1, 1, 1, " "));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
