package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType377.kt */

/* loaded from: classes2.dex */
public final class CanType377 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "本田", "艾力绅 Elysion", "2012-2015", "Hiworld", "Honda", "Elysion", "2012-2015", 377, 0, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "本田", "奥德赛 Odyssey", "2009-2014", "Hiworld", "Honda", "Odyssey", "2009-2014", 377, 0, 0, 0, 1, 5, 1, 1, 1, " "));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
