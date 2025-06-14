package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType228.kt */

/* loaded from: classes2.dex */
public final class CanType228 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("欧迪", "北汽", "绅宝D60", "默认", "Oudi", "BAIC", "Saab D60", "Default", HotKeyConstant.K_LONG_SIRI, 0, 1, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("欧迪", "北汽", "绅宝X55", "默认", "Oudi", "BAIC", "Saab X55", "Default", HotKeyConstant.K_LONG_SIRI, 0, 2, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("欧迪", "北汽", "绅宝X25", "默认", "Oudi", "BAIC", "SaabX25", "Default", HotKeyConstant.K_LONG_SIRI, 0, 3, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("欧迪", "北汽", "M50F", "默认", "Oudi", "BAIC", "M50F", "Default", HotKeyConstant.K_LONG_SIRI, 0, 4, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("欧迪", "北汽", "绅宝X35", "默认", "Oudi", "BAIC", "SaabX35", "Default", HotKeyConstant.K_LONG_SIRI, 0, 5, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("欧迪", "北汽", "BJ20", "默认", "Oudi", "BAIC", "BJ20", "Default", HotKeyConstant.K_LONG_SIRI, 0, 6, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("欧迪", "北汽", "BJ40", "默认", "Oudi", "BAIC", "BJ40", "Default", HotKeyConstant.K_LONG_SIRI, 0, 7, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
