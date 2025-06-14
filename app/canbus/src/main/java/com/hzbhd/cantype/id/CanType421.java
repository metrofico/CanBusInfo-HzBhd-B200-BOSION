package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType421.kt */

/* loaded from: classes2.dex */
public final class CanType421 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "长安", "CS75", " ", "Hiworld", "Changan", "CS75", "", 421, 1, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "长安", "奔奔 Ben Ben", " ", "Hiworld", "Changan", "Ben Ben", "", 421, 2, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "长安", "CX70", " ", "Hiworld", "Changan", "CX70", "", 421, 3, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "长安", "欧尚 Auchan", " ", "Hiworld", "Changan", "Auchan", "", 421, 4, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "长安", "悦翔V7中高配", " ", "Hiworld", "Changan", "V7", "", 421, 5, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "长安", "悦翔V7低配", " ", "Hiworld", "Changan", "V7", "", 421, 6, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "长安", "18款逸动DT", " ", "Hiworld", "Changan", "18DT", "", 421, 7, 0, 0, 1, 5, 1, 1, 1, " "));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
