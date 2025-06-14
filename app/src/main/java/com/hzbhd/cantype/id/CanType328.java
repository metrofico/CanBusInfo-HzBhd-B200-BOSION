package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType328.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/hzbhd/cantype/id/CanType328;", "Lcom/hzbhd/cantype/CanTypeBase;", "()V", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/adapter/bean/CanTypeAllEntity;", "Lkotlin/collections/ArrayList;", "getList", "()Ljava/util/ArrayList;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class CanType328 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("睿志诚", "三菱", "奕歌", "2018", "Raise", "Mitsubishi", "Eclipse Cross", "2018", 328, 1, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "三菱", "欧蓝德", "2019", "Raise", "Mitsubishi", "OutLand Cruiserer", "2019", 328, 2, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "三菱", "帕杰罗", "2018", "Raise", "Mitsubishi", "Pajero", "2018", 328, 3, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "三菱", "奕歌", "2019 (Taiwan)", "Raise", "Mitsubishi", "Eclipse Cross", "2019 (Taiwan)", 328, 4, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "三菱", "帕杰罗", "2020 (Sport)", "Raise", "Mitsubishi", "Pajero", "2020 (Sport)", 328, 5, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "三菱", "帕杰罗 劲畅", "2020", "Raise", "Mitsubishi", "Pajero Sport", "2020", 328, 6, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
