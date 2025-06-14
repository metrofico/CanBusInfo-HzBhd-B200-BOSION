package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType123.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/hzbhd/cantype/id/CanType123;", "Lcom/hzbhd/cantype/CanTypeBase;", "()V", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/adapter/bean/CanTypeAllEntity;", "Lkotlin/collections/ArrayList;", "getList", "()Ljava/util/ArrayList;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class CanType123 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "吉普", "自由光", "2015-Present", "Hiworld", "Jeep", "Cherokee", "2015-Present", 123, 3, 0, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "吉普", "自由侠", "2016-Present", "Hiworld", "Jeep", "Renegade", "2016-Present", 123, 4, 0, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "吉普", "指南者", "--", "Hiworld", "Jeep", "Compass", "--", 123, 5, 0, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "菲亚特", "500L", "2019", "Hiworld", "Fiat", "500L", "2019", 123, 6, 0, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "吉普", "自由侠", "2019", "Hiworld", "Jeep", "Renegade", "2019", 123, 7, 0, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "道奇", "酷威", "2013（全拆）", "Hiworld", "Dodge", "Journey", "2013（Complete disassembly）", 123, 16, 1, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "道奇", "酷威", "2013（保留主机）", "Hiworld", "Dodge", "Journey", "2013（Reserve Host）", 123, 17, 1, 0, 1, 5, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
