package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType292.kt */

/* loaded from: classes2.dex */
public final class CanType292 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("比纳瑞", "别克", "英朗", "2010-2014(Low)", "Binary", "Buick", "Excelle", "2010-2014(Low)", 292, 0, 14, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("比纳瑞", "别克", "英朗", "2010-2014(High)", "Binary", "Buick", "Excelle", "2010-2014(High)", 292, 1, 15, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
