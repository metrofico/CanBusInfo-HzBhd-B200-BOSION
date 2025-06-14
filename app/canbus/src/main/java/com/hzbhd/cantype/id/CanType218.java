package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType218.kt */

/* loaded from: classes2.dex */
public final class CanType218 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "福特", "嘉年华", "2008-2012(Gen7)", "Hiworld", "Ford", "Fiesta", "2008-2012(Gen7)", HotKeyConstant.K_NEWCANBUS_AIR_ACTIVITY, 0, 0, 0, 1, 6, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
