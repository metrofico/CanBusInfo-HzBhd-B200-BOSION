package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType181.kt */

/* loaded from: classes2.dex */
public final class CanType181 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("欧迪", "哈弗", "H9", "2017", "Oudi", "Haval", "H9", "2017", 181, 0, 1, 0, 1, 1, 1, 0, 0, "null"), new CanTypeAllEntity("欧迪", "哈弗", "H4", "2018-2019", "Oudi", "Haval", "H4", "2018-2019", 181, 0, 2, 0, 1, 1, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
