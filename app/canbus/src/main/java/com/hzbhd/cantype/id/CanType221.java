package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType221.kt */

/* loaded from: classes2.dex */
public final class CanType221 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "哈弗", "H2 (Red)", "2014-Present", "Hiworld", "Haval", "H2(Red)", "2014-Present", HotKeyConstant.K_BRIGHTNESS_INCREASE, 0, 0, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "哈弗", "H2(Blue)", "2016-Present", "Hiworld", "Haval", "H2(Blue)", "2016-Present", HotKeyConstant.K_BRIGHTNESS_INCREASE, 0, 0, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "哈弗", "H2S", "2016-Present", "Hiworld", "Haval", "H2S", "2016-Present", HotKeyConstant.K_BRIGHTNESS_INCREASE, 0, 0, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "哈弗", "H2S", "2017-Present", "Hiworld", "Haval", "H2S", "2017-Present", HotKeyConstant.K_BRIGHTNESS_INCREASE, 7, 0, 0, 1, 5, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
