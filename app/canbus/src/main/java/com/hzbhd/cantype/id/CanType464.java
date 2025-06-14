package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType464.kt */

/* loaded from: classes2.dex */
public final class CanType464 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("路征", "丰田", "兰德酷路泽（High）", "07_15款", "Luzheng", "Toyota", "Land Cruiser Cruise （High）", "07_15model", HotKeyConstant.K_MUTE_PHONE_ON_OUT, 0, 1, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("路征", "丰田", "兰德酷路泽（Low）", "07_15款", "Luzheng", "Toyota", "Land Cruiser Cruise （Low）", "07_15model", HotKeyConstant.K_MUTE_PHONE_ON_OUT, 0, 2, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("路征", "丰田", "雷克萨斯", "--", "Luzheng", "Toyota", "Lexus 570", "--", HotKeyConstant.K_MUTE_PHONE_ON_OUT, 0, 3, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
