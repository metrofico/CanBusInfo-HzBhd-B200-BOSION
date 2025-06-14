package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType349.kt */

/* loaded from: classes2.dex */
public final class CanType349 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("比纳瑞", "福特", "翼虎 高配", "--", "Binary", "Ford", "Kuga/Escape高配", "--", 349, 1, 1, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("比纳瑞", "福特", "翼虎 低配", "--", "Binary", "Ford", "Kuga/Escape低配", "--", 349, 2, 2, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
