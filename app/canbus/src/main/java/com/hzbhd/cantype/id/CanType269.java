package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.canbus.adapter.util.SystemConstant;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType269.kt */

/* loaded from: classes2.dex */
public final class CanType269 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("内部协议", "吉普", "四门版", "默认", "Internal", "Jeep", "四门版 Four doors", "Defualt", SystemConstant.CAN_TYPE_PENGLING_JEEP, 0, 0, 3, 1, 1, 1, 0, 0, "null"), new CanTypeAllEntity("内部协议", "吉普", "两门版", "默认", "Internal", "Jeep", "两门版 Two doors", "Defualt", SystemConstant.CAN_TYPE_PENGLING_JEEP, 1, 0, 3, 1, 1, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
