package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType134.kt */

/* loaded from: classes2.dex */
public final class CanType134 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("鑫巴斯", "马自达", "Mazda3", "2014(Low)", "Xinbas", "Mazda", "Mazda3 Axela", "2014(Low)", HotKeyConstant.K_SLEEP, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "马自达", "Mazda3", "2017", "Xinbas", "Mazda", "Mazda3 Axela", "2017", HotKeyConstant.K_SLEEP, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "马自达", "Mazda6", "2017(Gen3)(Low)", "Xinbas", "Mazda", "Mazda6 Atenza", "2017(Gen3)(Low)", HotKeyConstant.K_SLEEP, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "马自达", "CX-4", "2016(Medium)", "Xinbas", "Mazda", "CX-4", "2016(Medium)", HotKeyConstant.K_SLEEP, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "马自达", "CX-4", "2016(High)", "Xinbas", "Mazda", "CX-4", "2016(High)", HotKeyConstant.K_SLEEP, 0, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
