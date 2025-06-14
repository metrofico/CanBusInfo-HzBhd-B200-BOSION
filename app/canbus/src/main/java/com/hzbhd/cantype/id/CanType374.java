package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType374.kt */

/* loaded from: classes2.dex */
public final class CanType374 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("睿志诚", "沃尔沃", "XC60", "2010-2013款", "Raise", "Volvo", "XC60", "2010-2013model", 374, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "沃尔沃", "XC60", "2014-2017款", "Raise", "Volvo", "XC60", "2014-2017model", 374, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "沃尔沃", "S60L", "14-17 款", "Raise", "Volvo", "S60L", "14-17 model", 374, 0, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
