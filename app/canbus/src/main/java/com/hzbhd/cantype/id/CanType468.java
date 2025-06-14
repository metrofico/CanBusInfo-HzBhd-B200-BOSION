package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType468.kt */

/* loaded from: classes2.dex */
public final class CanType468 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("路征", "本田", "思域", "06-11款 (高配 High Config)", "Luzheng", "Honda", "Civic ", "06-11model (High configuration High Config)", HotKeyConstant.K_2_HANGUP, 0, 1, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("路征", "本田", "思域", "06-11款 (低配 Low Config)", "Luzheng", "Honda", "Civic ", "06-11model (Low configuration Low Config)", HotKeyConstant.K_2_HANGUP, 0, 2, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
