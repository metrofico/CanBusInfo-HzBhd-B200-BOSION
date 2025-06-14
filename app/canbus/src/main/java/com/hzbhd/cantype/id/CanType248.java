package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType248.kt */

/* loaded from: classes2.dex */
public final class CanType248 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "传祺", "GE3", "默认", "Hiworld", "Trumpchi", "GE3", "Defualt", 248, 0, 7, 0, 1, 4, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "传祺", "GA4", "默认", "Hiworld", "Trumpchi", "GA4", "Defualt", 248, 1, 6, 0, 1, 4, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "传祺", "GA6", "默认", "Hiworld", "Trumpchi", "GA6", "Defualt", 248, 2, 1, 0, 1, 4, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "传祺", "GS3", "默认", "Hiworld", "Trumpchi", "GS3", "Defualt", 248, 3, 4, 0, 1, 4, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "传祺", "GS4", "默认", "Hiworld", "Trumpchi", "GS4", "Defualt", 248, 4, 2, 0, 1, 4, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "传祺", "GS5", "默认", "Hiworld", "Trumpchi", "GS5", "Defualt", 248, 5, 3, 0, 1, 4, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
