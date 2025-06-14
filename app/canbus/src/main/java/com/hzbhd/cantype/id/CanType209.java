package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType209.kt */

/* loaded from: classes2.dex */
public final class CanType209 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("比纳瑞", "本田", "雅阁", "2.0 2016(Gen9)", "Binary", "Honda", "Accord", "2.0 2016(Gen9)", HotKeyConstant.K_AVM, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("比纳瑞", "本田", "雅阁", "2.4 2016(Gen9)", "Binary", "Honda", "Accord", "2.4 2016(Gen9)", HotKeyConstant.K_AVM, 0, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
