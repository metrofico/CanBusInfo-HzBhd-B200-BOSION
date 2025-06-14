package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType272.kt */

/* loaded from: classes2.dex */
public final class CanType272 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "雷克萨斯", "ES", "2009-2012(Gen5,XV40)", "Hiworld", "Lexus", "ES", "2009-2012(Gen5,XV40)", 272, 0, 0, 2, 1, 12, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "雷克萨斯", "ES", "2012-2017(Gen6,XV60)", "Hiworld", "Lexus", "ES", "2012-2017(Gen6,XV60)", 272, 0, 0, 2, 1, 12, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
