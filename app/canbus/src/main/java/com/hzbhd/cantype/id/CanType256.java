package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType256.kt */

/* loaded from: classes2.dex */
public final class CanType256 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("睿志诚", "吉利", "全球鹰GC7", "2012-2013", "Raise", "Geely", "global hawk GC7", "2012-2013", 256, 0, 0, 2, 1, 11, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "中华", "H530", "2014", "Raise", "Brilliance", "H530", "2014", 256, 0, 0, 2, 1, 11, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "中华", "V5", "2014-2016", "Raise", "Brilliance", "V5", "2014-2016", 256, 0, 0, 2, 1, 11, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "奔腾", "B50", "2009-2013", "Raise", "Besturn", "B50", "2009-2013", 256, 0, 0, 2, 1, 11, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "奔腾", "B70", "2006-2013", "Raise", "Besturn", "B70", "2006-2013", 256, 0, 0, 2, 1, 11, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "Chery", "A3", "2010-2012", "Raise", "Chery", "A3", "2010-2012", 256, 0, 0, 2, 1, 11, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "马自达", "默认", "默认", "Raise", "Mazda", "Default", "Default", 256, 0, 0, 2, 1, 11, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "马自达", "CX-7", "2014-Present", "Raise", "Mazda", "CX-7", "2014-Present", 256, 1, 0, 2, 1, 11, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
