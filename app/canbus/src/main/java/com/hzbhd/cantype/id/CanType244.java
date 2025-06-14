package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import nfore.android.bt.res.NfDef;

/* compiled from: CanType244.kt */

/* loaded from: classes2.dex */
public final class CanType244 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("睿志诚", "东风", "景逸X5", "2017-2019(Medium)", "Raise", "DFM", "Jingyi X5", "2017-2019(Medium)", NfDef.STATE_3WAY_CALL_M_ACT_S_HOLD, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "东风", "景逸X5", "2017-2019(High)", "Raise", "DFM", "Jingyi X6", "2017-2019(High)", NfDef.STATE_3WAY_CALL_M_ACT_S_HOLD, 1, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "东风", "景逸X5", "2019(Low)", "Raise", "DFM", "Jingyi X7", "2019(Low)", NfDef.STATE_3WAY_CALL_M_ACT_S_HOLD, 2, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "东风", "T5", "2020(Low)", "Raise", "DFM", "T5", "2020(Low)", NfDef.STATE_3WAY_CALL_M_ACT_S_HOLD, 3, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
