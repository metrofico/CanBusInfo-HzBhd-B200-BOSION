package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType370.kt */

/* loaded from: classes2.dex */
public final class CanType370 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("比纳瑞", "吉普", "自由光", "2016", "Binary", "Jeep", "Cherokee", "2016", 370, 0, 1, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("比纳瑞", "吉普", "自由侠", "2016低配", "Binary", "Jeep", "Renegade", "2016Low configuration", 370, 1, 2, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("比纳瑞", "吉普", "自由侠", "2016高配", "Binary", "Jeep", "Renegade", "2016High configuration", 370, 2, 2, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("比纳瑞", "吉普", "指南者", "2017", "Binary", "Jeep", "Compass", "2017", 370, 0, 3, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
