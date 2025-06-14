package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import nfore.android.bt.res.NfDef;

/* compiled from: CanType244.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/hzbhd/cantype/id/CanType244;", "Lcom/hzbhd/cantype/CanTypeBase;", "()V", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/adapter/bean/CanTypeAllEntity;", "Lkotlin/collections/ArrayList;", "getList", "()Ljava/util/ArrayList;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class CanType244 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("睿志诚", "东风", "景逸X5", "2017-2019(Medium)", "Raise", "DFM", "Jingyi X5", "2017-2019(Medium)", NfDef.STATE_3WAY_CALL_M_ACT_S_HOLD, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "东风", "景逸X5", "2017-2019(High)", "Raise", "DFM", "Jingyi X6", "2017-2019(High)", NfDef.STATE_3WAY_CALL_M_ACT_S_HOLD, 1, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "东风", "景逸X5", "2019(Low)", "Raise", "DFM", "Jingyi X7", "2019(Low)", NfDef.STATE_3WAY_CALL_M_ACT_S_HOLD, 2, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "东风", "T5", "2020(Low)", "Raise", "DFM", "T5", "2020(Low)", NfDef.STATE_3WAY_CALL_M_ACT_S_HOLD, 3, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
