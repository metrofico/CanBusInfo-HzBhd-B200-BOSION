package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType359.kt */

/* loaded from: classes2.dex */
public final class CanType359 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("比纳瑞", "雷克萨斯", "IS 250", "2006低配", "Binary", "Lexus", "IS", "2006Low configuration", HotKeyConstant.K_AIR_WIND_DEC, 0, 1, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("比纳瑞", "雷克萨斯", "IS 250", "2006高配", "Binary", "Lexus", "IS", "2006High configuration", HotKeyConstant.K_AIR_WIND_DEC, 0, 2, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
