package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType255.kt */

/* loaded from: classes2.dex */
public final class CanType255 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("睿志诚", "宝骏系列", "510", "2017-2019", "Raise", "BaoJ", "510", "2017-2019", 255, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "宝骏系列", "530", "2018-2019", "Raise", "BaoJ", "530", "2018-2019", 255, 1, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "宝骏系列", "730", "2014-2017", "Raise", "BaoJ", "730", "2014-2017", 255, 2, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "宝骏系列", "560", "2015-2017", "Raise", "BaoJ", "560", "2015-2017", 255, 0, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
