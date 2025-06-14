package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType187.kt */

/* loaded from: classes2.dex */
public final class CanType187 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("睿志诚", "日产", "楼兰", "2015-2017", "Raise", "Nissan", "Murano", "2015-2017", HotKeyConstant.K_SPEECH, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "日产", "楼兰", "2019", "Raise", "Nissan", "Murano", "2019", HotKeyConstant.K_SPEECH, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("睿志诚", "日产", "西玛", "2016", "Raise", "Nissan", "Maxima", "2016", HotKeyConstant.K_SPEECH, 0, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
