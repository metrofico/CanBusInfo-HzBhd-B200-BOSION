package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType305.kt */

/* loaded from: classes2.dex */
public final class CanType305 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("欧迪", "宝骏", "默认", "默认", "Oudi", "BAOJUN", "Default", "Defualt", 305, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("欧迪", "宝骏", "530", "18款", "Oudi", "BAOJUN", "530", "18model", 305, 1, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("欧迪", "宝骏", "730", "17款", "Oudi", "BAOJUN", "730", "17model", 305, 2, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
