package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType328.kt */

/* loaded from: classes2.dex */
public final class CanType328 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("睿志诚", "三菱", "奕歌", "2018", "Raise", "Mitsubishi", "Eclipse Cross", "2018", 328, 1, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "三菱", "欧蓝德", "2019", "Raise", "Mitsubishi", "OutLand Cruiserer", "2019", 328, 2, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "三菱", "帕杰罗", "2018", "Raise", "Mitsubishi", "Pajero", "2018", 328, 3, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "三菱", "奕歌", "2019 (Taiwan)", "Raise", "Mitsubishi", "Eclipse Cross", "2019 (Taiwan)", 328, 4, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "三菱", "帕杰罗", "2020 (Sport)", "Raise", "Mitsubishi", "Pajero", "2020 (Sport)", 328, 5, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "三菱", "帕杰罗 劲畅", "2020", "Raise", "Mitsubishi", "Pajero Sport", "2020", 328, 6, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
