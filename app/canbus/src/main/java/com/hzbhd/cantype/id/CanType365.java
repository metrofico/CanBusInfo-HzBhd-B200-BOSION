package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType365.kt */

/* loaded from: classes2.dex */
public final class CanType365 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "通用", "SRX", " ", "Hiworld", "GM", "SRX", "", HotKeyConstant.K_AIR_AUTO, 21, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "通用", "ATS", " ", "Hiworld", "GM", "ATS", "", HotKeyConstant.K_AIR_AUTO, 43, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "通用", "XTS", " ", "Hiworld", "GM", "XTS", "", HotKeyConstant.K_AIR_AUTO, 44, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "通用", "君越", "2013-2014", "Hiworld", "GM", "", "2013-2014", HotKeyConstant.K_AIR_AUTO, 36, 0, 0, 1, 5, 1, 1, 1, " "));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
