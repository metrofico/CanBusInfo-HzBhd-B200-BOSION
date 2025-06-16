package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;
import java.util.Arrays;

import kotlin.collections.CollectionsKt;

/* compiled from: CanType327.kt */

/* loaded from: classes2.dex */
public final class CanType481 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = new ArrayList<>(Arrays.asList(new CanTypeAllEntity("睿志诚", "斯威蒙", "G01", "2023-2025", "Raise", "SWM", "G01", "2023-2025", 481, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "斯威蒙", "G01F", "2023-2025", "Raise", "SWM", "G01F", "2023-2025", 481, 0, 1, 0, 1, 0, 1, 0, 0, "null")));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
