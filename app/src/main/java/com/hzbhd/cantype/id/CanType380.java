package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType380.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/hzbhd/cantype/id/CanType380;", "Lcom/hzbhd/cantype/CanTypeBase;", "()V", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/adapter/bean/CanTypeAllEntity;", "Lkotlin/collections/ArrayList;", "getList", "()Ljava/util/ArrayList;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class CanType380 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "欧宝", "雅特 Astra", "2004-2009", "Hiworld", "Opel", "Astra", "2004-2009", 380, 0, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "欧宝", "赛飞利 Zafira", "2005-2014", "Hiworld", "Opel", "Zafira", "2005-2014", 380, 0, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "欧宝", "安德拉 Andra", "2006-2015", "Hiworld", "Opel", "Andra", "2006-2015", 380, 0, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "欧宝", "威达 Vectra", "2002-2008", "Hiworld", "Opel", "Vectra", "2002-2008", 380, 0, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "欧宝", "可赛 Kesai", "2006-2014", "Hiworld", "Opel", "Kesai", "2006-2014", 380, 0, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "欧宝", "Meriva", "2003-2010", "Hiworld", "Opel", "Meriva", "2003-2010", 380, 0, 0, 0, 1, 5, 1, 1, 1, " "));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
