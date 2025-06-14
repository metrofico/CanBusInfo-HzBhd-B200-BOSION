package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType384.kt */

/* loaded from: classes2.dex */
public final class CanType384 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "丰田", "皇冠", "(TYP004)", "Hiworld", "Toyota", "Crown", "(TYP004)", 384, 0, 0, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "雷克萨斯", "LX470", "2005", "Hiworld", "Lexus", "LX470", "2005", 384, 0, 0, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "丰田", "霸道", "2010", "Hiworld", "Toyota", "cruiser", "2010", 384, 0, 0, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "雷克萨斯", "RX300", "2008", "Hiworld", "Lexus", "RX300", "2008", 384, 0, 0, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "雷克萨斯", "RX350", "2008", "Hiworld", "Lexus", "RX350", "2008", 384, 0, 0, 0, 1, 5, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
