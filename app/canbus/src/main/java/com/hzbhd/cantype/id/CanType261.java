package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType261.kt */

/* loaded from: classes2.dex */
public final class CanType261 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("欣朴 ", "雷诺", "科雷傲", "2017", "Simple", "Renault", "Koleos2（Airmanual）", "2017", 261, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("欣朴 ", "雷诺", "梅甘娜4", "2017", "Simple", "Renault", "Megane4（Air manual）", "2017", 261, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("欣朴 ", "雷诺", "塔里斯曼", "2017", "Simple", "Renault", "Talisman（Air manual）", "2017", 261, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("宝谷", "雷诺", "科雷傲", "2017", "BAOGOOD", "Renault", "Koleos2（Air manual）", "2017", 261, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("宝谷", "雷诺", "梅甘娜4", "2017", "BAOGOOD", "Renault", "Megane", "2017", 261, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("宝谷", "雷诺", "塔里斯曼", "2017", "BAOGOOD", "Renault", "Talisman（Air manual）", "2017", 261, 0, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
