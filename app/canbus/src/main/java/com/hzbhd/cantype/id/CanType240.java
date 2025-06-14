package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import nfore.android.bt.res.NfDef;

/* compiled from: CanType240.kt */

/* loaded from: classes2.dex */
public final class CanType240 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("睿志诚", "海马", "福美来", "2017", "Raise", "Haima", "familia Family", "2017", NfDef.STATE_3WAY_M_HOLD, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "海马", "S7", "2017", "Raise", "Haima", "S7", "2017", NfDef.STATE_3WAY_M_HOLD, 1, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "海马", "S5", "2017", "Raise", "Haima", "S5", "2017", NfDef.STATE_3WAY_M_HOLD, 2, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "海马", "S5", "2019", "Raise", "Haima", "S5", "2019", NfDef.STATE_3WAY_M_HOLD, 3, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
