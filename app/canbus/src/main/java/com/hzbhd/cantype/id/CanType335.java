package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType335.kt */

/* loaded from: classes2.dex */
public final class CanType335 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("比纳瑞", "雷克萨斯", "IS", "2006-2012 (Landscape)", "Binary", "Lexus", "IS", "2006-2012 (Landscape)", 335, 0, 1, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("比纳瑞", "雷克萨斯", "ES/IS", "2006-2012 (Portrait)", "Binary", "Lexus", "ES/IS", "2006-2012 (Portrait)", 335, 0, 2, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("比纳瑞", "雷克萨斯", "RX", "2009-2014 (Landscape)", "Binary", "Lexus", "RX", "2009-2014 (Landscape)", 335, 0, 3, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
