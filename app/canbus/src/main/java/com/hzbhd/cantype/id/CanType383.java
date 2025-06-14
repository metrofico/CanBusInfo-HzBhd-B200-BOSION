package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType383.kt */

/* loaded from: classes2.dex */
public final class CanType383 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("欧迪", "东南系列", "东南DX5（高配）", "15-20款", "Oudi", "Southeast series", "southeast China DX5（High configuration）", "15-20model", 383, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("欧迪", "东南系列", "东南DX7", "15-20款", "Oudi", "Southeast series", "southeast China DX7", "15-20model", 383, 1, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("欧迪", "东南系列", "东南DX3", "15-20款", "Oudi", "Southeast series", "southeast China DX3", "15-20model", 383, 2, 0, 0, 1, 0, 0, 0, 0, "null"), new CanTypeAllEntity("欧迪", "东南系列", "东南DX5（低配）", "15-20款", "Oudi", "Southeast series", "southeast China DX5（Low configuration）", "15-20model", 383, 3, 0, 0, 1, 0, 0, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
