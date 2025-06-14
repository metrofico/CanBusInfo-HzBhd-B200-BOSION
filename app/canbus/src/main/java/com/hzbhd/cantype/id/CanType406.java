package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType406.kt */

/* loaded from: classes2.dex */
public final class CanType406 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "长安", "悦翔 V7 中高配", " ", "Hiworld", "Changan", "V7", "", 406, 5, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "长安", "悦翔 V7 低配", " ", "Hiworld", "Changan", "V7", "", 406, 6, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "长安", "逸动 DT 低配", " ", "Hiworld", "Changan", "DT", "", 406, 7, 0, 0, 1, 5, 1, 1, 1, " "));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
