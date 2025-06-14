package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType283.kt */

/* loaded from: classes2.dex */
public final class CanType283 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "大众", "默认", "默认", "Hiworld", "VW", "Default", "Defualt", 283, 0, 0, 0, 1, 5, 0, 0, 0, "null"), new CanTypeAllEntity("威驰", "大众", "帕萨特 Hybird", "默认", "Hiworld", "VW", "Passat Hybird", "Defualt", 283, 1, 0, 0, 1, 5, 0, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
