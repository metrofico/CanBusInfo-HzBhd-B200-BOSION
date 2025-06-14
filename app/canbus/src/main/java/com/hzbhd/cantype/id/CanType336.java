package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType336.kt */

/* loaded from: classes2.dex */
public final class CanType336 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "比亚迪", "F3", "默认", "Hiworld", "BYD", "F3", "Default", 336, 0, 1, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "比亚迪", "S6", "默认", "Hiworld", "BYD", "S6", "Default", 336, 0, 2, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "比亚迪", "元", "默认", "Hiworld", "BYD", "Yuan", "Default", 336, 0, 3, 0, 1, 5, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
