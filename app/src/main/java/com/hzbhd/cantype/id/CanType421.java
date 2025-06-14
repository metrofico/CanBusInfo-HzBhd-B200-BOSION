package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType421.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/hzbhd/cantype/id/CanType421;", "Lcom/hzbhd/cantype/CanTypeBase;", "()V", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/adapter/bean/CanTypeAllEntity;", "Lkotlin/collections/ArrayList;", "getList", "()Ljava/util/ArrayList;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class CanType421 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "长安", "CS75", " ", "Hiworld", "Changan", "CS75", "", 421, 1, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "长安", "奔奔 Ben Ben", " ", "Hiworld", "Changan", "Ben Ben", "", 421, 2, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "长安", "CX70", " ", "Hiworld", "Changan", "CX70", "", 421, 3, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "长安", "欧尚 Auchan", " ", "Hiworld", "Changan", "Auchan", "", 421, 4, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "长安", "悦翔V7中高配", " ", "Hiworld", "Changan", "V7", "", 421, 5, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "长安", "悦翔V7低配", " ", "Hiworld", "Changan", "V7", "", 421, 6, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "长安", "18款逸动DT", " ", "Hiworld", "Changan", "18DT", "", 421, 7, 0, 0, 1, 5, 1, 1, 1, " "));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
