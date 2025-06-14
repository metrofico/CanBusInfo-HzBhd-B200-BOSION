package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType288.kt */

/* loaded from: classes2.dex */
public final class CanType288 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("比纳瑞", "丰田", "霸道 普拉多", "2002-2009 (Portrait)(Low)", "Binary", "Toyota", "cruiser Prado", "2002-2009 (Portrait)(Low)", 288, 0, 64, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("比纳瑞", "丰田", "霸道 普拉多", "2002-2009 (Portrait)(High)", "Binary", "Toyota", "cruiser Prado", "2002-2009 (Portrait)(High)", 288, 0, 65, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
