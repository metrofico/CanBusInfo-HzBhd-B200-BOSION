package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType25.kt */

/* loaded from: classes2.dex */
public final class CanType25 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("欣朴 ", "日产", "奇骏", "2017(3rd gen)(High with 360Camera)", "Simple", "Nissan", "X-Trail", "2017(3rd gen)(High with 360Camera)", 25, 2, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("宝谷", "日产", "奇骏", "2017(3rd gen)(High with 360Camera)", "BAOGOOD", "Nissan", "X-Trail", "2017(3rd gen)(High with 360Camera)", 25, 2, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
