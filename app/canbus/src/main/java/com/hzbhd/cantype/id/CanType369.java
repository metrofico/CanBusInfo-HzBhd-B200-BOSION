package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType369.kt */

/* loaded from: classes2.dex */
public final class CanType369 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "上汽", "荣威 950", " ", "Hiworld", "SAIC", "950", "", 369, 1, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "上汽", "荣威 550", "2011-2013", "Hiworld", "SAIC", "550", "2011-2013", 369, 2, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "上汽", "名爵 6", "2011-2013", "Hiworld", "SAIC", "6", "2011-2013", 369, 3, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "上汽", "荣威 550", "2013-至今", "Hiworld", "SAIC", "550", "2013-", 369, 4, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "上汽", "名爵 6", "2013-至今", "Hiworld", "SAIC", "6", "2013-", 369, 5, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "上汽", "荣威 360", " ", "Hiworld", "SAIC", "360", "", 369, 6, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "上汽", "锐腾", " ", "Hiworld", "SAIC", "", "", 369, 7, 0, 0, 1, 5, 1, 1, 1, " "));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
