package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType3.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/hzbhd/cantype/id/CanType3;", "Lcom/hzbhd/cantype/CanTypeBase;", "()V", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/adapter/bean/CanTypeAllEntity;", "Lkotlin/collections/ArrayList;", "getList", "()Ljava/util/ArrayList;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class CanType3 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("睿志诚", "大众", "途锐", "2013款", "Raise", "VW（MQB）", "Touareg", "Default", 3, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "大众", "朗逸纯电动", "2019款", "Raise", "VW（MQB）", "Langyi pure electric", "2013model", 3, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "大众", "宝来纯电动", "2020款", "Raise", "VW（MQB）", "Bora pure electric", "2019model", 3, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "大众", "VILORAN", "默认", "Raise", "VW（MQB）", "VILORAN", "2020model", 3, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "大众", "帕萨特混动", "19-20款", "Raise", "VW（MQB）", "Passat hybrid", "Default", 3, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "大众", "途观L混动", "2020款", "Raise", "VW（MQB）", "Tiguan l hybrid", "19-20model", 3, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "大众", "Hui", "2021款", "Raise", "VW（MQB）", "Hui ang", "2020model", 3, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "大众", "高尔夫8", "2021款", "Raise", "VW（MQB）", "Golf 8", "2021model", 3, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "大众", "帕萨特 B8", "默认", "Raise", "VW（MQB）", "Passat B8", "Default", 3, 0, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
