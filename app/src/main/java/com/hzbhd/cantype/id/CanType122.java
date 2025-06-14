package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType122.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/hzbhd/cantype/id/CanType122;", "Lcom/hzbhd/cantype/CanTypeBase;", "()V", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/adapter/bean/CanTypeAllEntity;", "Lkotlin/collections/ArrayList;", "getList", "()Ljava/util/ArrayList;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class CanType122 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "克莱斯勒", "铂锐", "2007-2010(Gen3)", "Hiworld", "Chrysler", "Sebring", "2007-2010(Gen3)", 122, 0, 0, 0, 1, 6, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "克莱斯勒", "300C", "2005-2010(Gen1)", "Hiworld", "Chrysler", "300C", "2005-2010(Gen1)", 122, 0, 0, 0, 1, 6, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "吉普", "指南者", "2007-2016(Gen1/MK49)", "Hiworld", "Jeep", "Compass", "2007-2016(Gen1/MK49)", 122, 0, 0, 0, 1, 6, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "吉普", "大切诺基", "2005-2010(Gen3/WK)", "Hiworld", "Jeep", "Grand Cherokee", "2005-2010(Gen3/WK)", 122, 0, 0, 0, 1, 6, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "吉普", "大切诺基", "2011-Present(Gen4/WK2)", "Hiworld", "Jeep", "Grand Cherokee", "2011-Present(Gen4/WK2)", 122, 0, 0, 0, 1, 6, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "吉普", "自由客", "2011-2015", "Hiworld", "Jeep", "Patriot", "2011-2015", 122, 0, 0, 0, 1, 6, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "吉普", "牧马人", "2007-Present(Gen3/JK)", "Hiworld", "Jeep", "Wrangler", "2007-Present(Gen3/JK)", 122, 0, 0, 0, 1, 6, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
