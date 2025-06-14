package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType175.kt */

/* loaded from: classes2.dex */
public final class CanType175 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("欣朴 ", "现代", "索纳塔", "2018(9th gen)(10panel Key)", "Simple", "Hyundai", "Sonata", "2018(9th gen)(10panel Key)", 175, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("宝谷", "现代", "索纳塔", "2018(9th gen)(10panel Key)", "BAOGOOD", "Hyundai", "Sonata", "2018(9th gen)(10panel Key)", 175, 0, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
