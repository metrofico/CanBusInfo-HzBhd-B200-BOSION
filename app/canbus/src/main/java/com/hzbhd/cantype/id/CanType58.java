package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType58.kt */

/* loaded from: classes2.dex */
public final class CanType58 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "本田", "思域", "2012-2014(Gen9)", "Hiworld", "Honda", "Civic", "2012-2014(Gen9)", 58, 0, 0, 2, 1, 7, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "本田", "思域", "2014-2015(Gen9)(Low)", "Hiworld", "Honda", "Civic", "2014-2015(Gen9)(Low)", 58, 0, 0, 2, 1, 7, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
