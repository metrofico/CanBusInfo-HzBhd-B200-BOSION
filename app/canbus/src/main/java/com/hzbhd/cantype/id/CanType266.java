package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType266.kt */

/* loaded from: classes2.dex */
public final class CanType266 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "中华", "H3", "2017-Present", "Hiworld", "CMC", "H3", "2017-Present", 266, 0, 2, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "中华", "V3", "2016-Present", "Hiworld", "CMC", "V3", "2016-Present", 266, 1, 2, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "中华", "V3 low", "2016-Present", "Hiworld", "CMC", "V3 low", "2016-Present", 266, 2, 2, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "华晨", "华颂HUASONG 7", "--", "Hiworld", "Brilliance", "华颂HUASONG 7", "--", 266, 3, 3, 0, 1, 5, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
