package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType204.kt */

/* loaded from: classes2.dex */
public final class CanType204 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "哈弗", "H2", " ", "Hiworld", "Haval", "H2", "", 204, 1, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "哈弗", "H6", " ", "Hiworld", "Haval", "H6", "", 204, 2, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "哈弗", "H6 Coupe", " ", "Hiworld", "Haval", "H6 Coupe", "", 204, 3, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "哈弗", "H6 Coupe", "2018", "Hiworld", "Haval", "H6 Coupe", "2018", 204, 7, 0, 0, 1, 5, 1, 1, 1, " "));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
